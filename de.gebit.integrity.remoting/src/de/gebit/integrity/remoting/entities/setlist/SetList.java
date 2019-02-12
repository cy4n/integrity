/*******************************************************************************
 * Copyright (c) 2013 Rene Schneider, GEBIT Solutions GmbH and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package de.gebit.integrity.remoting.entities.setlist;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Setlist is a data structure to contain a test execution plan as well as test results. Its name refers to the
 * setlists used by bands to write down the songs played during a concert (okay, it's a stupid reason to name it like
 * that, but hey, I didn't have any better idea ;-) ).<br>
 * <br>
 * Setlists are stored in a flat way, with the entries actually resembling a tree structure by referencing each other
 * via their IDs. This allows for easy incremental update of single entries regardless of the size of the subtrees below
 * them, as long as the IDs of each entry stay the same.
 * 
 * @author Rene Schneider - initial API and implementation
 * 
 */
@SuppressWarnings("unchecked")
public class SetList implements Serializable {

	/**
	 * Serialization.
	 */
	private static final long serialVersionUID = -5710551695226775511L;

	/**
	 * A flat list of all stored entries. The position in the list is the entries' ID and allows for quick retrieval.
	 */
	private List<SetListEntry> entries = new ArrayList<SetListEntry>();

	/**
	 * The reference to the entry that is currently being executed.
	 */
	private Integer entryInExecutionReference;

	/**
	 * The path of entries in execution, starting at the root.
	 */
	private transient List<SetListEntry> pathOfEntriesInExecution = new ArrayList<SetListEntry>();

	/**
	 * A map of entries that have a "result" in terms of having failed or succeeded. This maps sets to their results.
	 */
	private transient HashMap<SetListEntry, SetListEntryResultStates> resultBearingEntryResultMap;

	/**
	 * A map of entries that are executable. The value is the number of the entry in the total list of executable
	 * entries.
	 */
	private transient HashMap<SetListEntry, Integer> executableEntryResultIndex;

	/**
	 * The results of executable entries. Indexed by the numeric value in {@link #executableEntryResultIndex}.
	 */
	private transient ArrayList<SetListEntryResultStates> executableEntryResultStates;

	/**
	 * The current position in the entry list. Used when creating new entries.
	 */
	private transient int entryListPosition;

	/**
	 * The last ID value given to an entry of each type.
	 */
	private transient Map<SetListEntryTypes, Integer> lastCreatedEntryIdMap = new HashMap<SetListEntryTypes, Integer>();

	/**
	 * A counter counting the results.
	 */
	private transient Map<SetListEntryResultStates, Integer> executableEntryResultStateCounts = new HashMap<SetListEntryResultStates, Integer>();

	/**
	 * The fully qualified names of entries. This is a name which is calculated by using the name of an entry plus its
	 * parents names (recursively). It is intended to provide a "best-effort" way to map entries from one set list with
	 * entries from another set list from a different run of the same (or roughly the same) test scripts. Having said
	 * this, there are NO GUARANTEES WHATSOEVER that this mapping is actually successful in a particular scenario.
	 */
	private transient Map<SetListEntry, String> fullyQualifiedNameMap = new HashMap<SetListEntry, String>();

	/**
	 * This is the reverse of {@link #fullyQualifiedNameMap}.
	 */
	private transient Map<String, SetListEntry> fullyQualifiedNameReverseMap = new HashMap<String, SetListEntry>();

	/**
	 * Recreates transient data from the list of entries. Used after deserialization of the whole structure in order to
	 * prepare it for being actually used. Transient entries are redundant and not transmitted for size reasons.
	 */
	public void recreateTransientData() {
		resultBearingEntryResultMap = new HashMap<SetListEntry, SetListEntryResultStates>();
		executableEntryResultIndex = new HashMap<SetListEntry, Integer>();
		executableEntryResultStates = new ArrayList<SetListEntryResultStates>();
		executableEntryResultStateCounts = new HashMap<SetListEntryResultStates, Integer>();
		lastCreatedEntryIdMap = new HashMap<SetListEntryTypes, Integer>();
		fullyQualifiedNameMap = new HashMap<SetListEntry, String>();
		fullyQualifiedNameReverseMap = new HashMap<String, SetListEntry>();

		int tempPosition = 0;
		for (SetListEntry tempEntry : entries) {
			SetListEntryResultStates tempResultState = determineEntryResultState(tempEntry);

			if (tempResultState != null) {
				resultBearingEntryResultMap.put(tempEntry, tempResultState);

				if (SetListEntryTypes.SUITE != tempEntry.getType() && SetListEntryTypes.RESULT != tempEntry.getType()) {
					// Skip suites here - we don't consider those "executable". Only the sub-elements are considered
					// executable. And skip results themselves - these aren't executable at all.
					executableEntryResultIndex.put(tempEntry, tempPosition);
					executableEntryResultStates.add(tempResultState);
					executableEntryResultStateCounts.put(tempResultState,
							getNumberOfEntriesInResultState(tempResultState) + 1);
					tempPosition++;
				}
			}

			String tempFullyQualifiedBaseName = calculateFullyQualifiedBaseName(tempEntry);
			String tempFullyQualifiedName = null;
			int tempCounter = 0;
			do {
				tempFullyQualifiedName = tempFullyQualifiedBaseName + (tempCounter > 0 ? "#" + tempCounter : "");
				tempCounter++;
			} while (fullyQualifiedNameReverseMap.containsKey(tempFullyQualifiedName));
			fullyQualifiedNameMap.put(tempEntry, tempFullyQualifiedName);
			fullyQualifiedNameReverseMap.put(tempFullyQualifiedName, tempEntry);
		}

		pathOfEntriesInExecution = new ArrayList<SetListEntry>();
		setEntryInExecutionReference(entryInExecutionReference);
	}

	/**
	 * Determines the result state for a specific entry.
	 * 
	 * @param anEntry
	 *            the entry
	 * @return the result state or null in case the entry doesn't have a result
	 */
	protected SetListEntryResultStates determineEntryResultState(SetListEntry anEntry) {
		boolean tempEntryIsResultOfTableTestRow = (anEntry.getType() == SetListEntryTypes.RESULT
				&& getParent(anEntry).getType() == SetListEntryTypes.TABLETEST);
		List<SetListEntry> tempResultEntries = tempEntryIsResultOfTableTestRow ? null
				: resolveReferences(anEntry, SetListEntryAttributeKeys.RESULT);
		if (tempEntryIsResultOfTableTestRow || tempResultEntries.size() > 0) {
			SetListEntry tempResultEntry = tempEntryIsResultOfTableTestRow ? anEntry : tempResultEntries.get(0);

			switch (anEntry.getType()) {
			case SUITE:
				if (tempResultEntry.getAttribute(SetListEntryAttributeKeys.SUCCESS_COUNT) != null) {
					int tempFailureCount = (Integer) tempResultEntry
							.getAttribute(SetListEntryAttributeKeys.FAILURE_COUNT);
					int tempTestExceptionCount = (Integer) tempResultEntry
							.getAttribute(SetListEntryAttributeKeys.TEST_EXCEPTION_COUNT);
					int tempCallExceptionCount = (Integer) tempResultEntry
							.getAttribute(SetListEntryAttributeKeys.CALL_EXCEPTION_COUNT);
					int tempExceptionCount = tempTestExceptionCount + tempCallExceptionCount;
					if (tempExceptionCount > 0) {
						return SetListEntryResultStates.EXCEPTION;
					} else if (tempFailureCount > 0) {
						return SetListEntryResultStates.FAILED;
					} else {
						return SetListEntryResultStates.SUCCESSFUL;
					}
				}
				return SetListEntryResultStates.UNKNOWN;
			case CALL:
			case TIMESET:
				if (tempResultEntry.getAttribute(SetListEntryAttributeKeys.RESULT_SUCCESS_FLAG) != null) {
					if (Boolean.TRUE
							.equals(tempResultEntry.getAttribute(SetListEntryAttributeKeys.RESULT_SUCCESS_FLAG))) {
						return SetListEntryResultStates.SUCCESSFUL;
					} else {
						return SetListEntryResultStates.EXCEPTION;
					}
				}
				return SetListEntryResultStates.UNKNOWN;
			case TEST:
			case RESULT:
				if (tempResultEntry.getAttribute(SetListEntryAttributeKeys.RESULT_SUCCESS_FLAG) != null) {
					if (Boolean.TRUE
							.equals(tempResultEntry.getAttribute(SetListEntryAttributeKeys.RESULT_SUCCESS_FLAG))) {
						return SetListEntryResultStates.SUCCESSFUL;
					} else if (Boolean.FALSE
							.equals(tempResultEntry.getAttribute(SetListEntryAttributeKeys.RESULT_SUCCESS_FLAG))) {
						if (tempResultEntry.getAttribute(SetListEntryAttributeKeys.EXCEPTION) != null) {
							return SetListEntryResultStates.EXCEPTION;
						} else {
							return SetListEntryResultStates.FAILED;
						}
					}
				}
				return SetListEntryResultStates.UNKNOWN;
			case TABLETEST:
				boolean tempHasException = false;
				boolean tempHasFailure = false;
				boolean tempHasAnyResult = false;
				for (int i = 0; i < tempResultEntries.size(); i++) {
					tempResultEntry = tempResultEntries.get(i);
					if (tempResultEntry.getAttribute(SetListEntryAttributeKeys.RESULT_SUCCESS_FLAG) != null) {
						tempHasAnyResult = true;
						if (Boolean.FALSE
								.equals(tempResultEntry.getAttribute(SetListEntryAttributeKeys.RESULT_SUCCESS_FLAG))) {
							if (tempResultEntry.getAttribute(SetListEntryAttributeKeys.EXCEPTION) != null) {
								tempHasException = true;
								break;
							} else {
								tempHasFailure = true;
							}
						}
					}
				}
				String tempPostInvocationResult = (String) anEntry
						.getAttribute(SetListEntryAttributeKeys.POST_INVOCATION_RESULT);
				if (tempPostInvocationResult != null) {
					if (anEntry.getAttribute(SetListEntryAttributeKeys.POST_INVOCATION_EXCEPTION) != null) {
						tempHasException = true;
					} else {
						tempHasFailure = true;
					}
				}
				if (tempHasException) {
					return SetListEntryResultStates.EXCEPTION;
				} else if (tempHasFailure) {
					return SetListEntryResultStates.FAILED;
				} else if (tempHasAnyResult) {
					return SetListEntryResultStates.SUCCESSFUL;
				} else {
					return SetListEntryResultStates.UNKNOWN;
				}
			case VARIABLE_ASSIGNMENT:
				if (Boolean.TRUE.equals(tempResultEntry.getAttribute(SetListEntryAttributeKeys.RESULT_SUCCESS_FLAG))) {
					return SetListEntryResultStates.SUCCESSFUL;
				}
				return SetListEntryResultStates.UNKNOWN;
			default:
				return null;
			}
		}
		return null;
	}

	public int getNumberOfExecutableEntries() {
		return executableEntryResultStates.size();
	}

	/**
	 * Returns the number of entries in the given result state.
	 * 
	 * @param aState
	 *            the state to query
	 * @return the number
	 */
	public int getNumberOfEntriesInResultState(SetListEntryResultStates aState) {
		Integer tempResult = executableEntryResultStateCounts.get(aState);
		return tempResult != null ? tempResult : 0;
	}

	/**
	 * Returns the result state of an entry from the map.
	 * 
	 * @param anEntry
	 *            the entry
	 * @return the result state, or null if the entry doesn't have one
	 */
	public SetListEntryResultStates getResultStateForEntry(SetListEntry anEntry) {
		return resultBearingEntryResultMap.get(anEntry);
	}

	/**
	 * Returns the result state of an executable entry at the specified position in the list of executable entries.
	 * 
	 * @param aPosition
	 *            the position
	 * @return the result state, or null if the position doesn't have one
	 */
	public SetListEntryResultStates getResultStateForExecutableEntry(int aPosition) {
		if (aPosition < 0 || aPosition >= executableEntryResultStates.size()) {
			return null;
		} else {
			return executableEntryResultStates.get(aPosition);
		}
	}

	/**
	 * Creates a new entry. Uses up an entry ID in the process.<br>
	 * <br>
	 * Note that this doesn't necessarily create a new entry, it may also reuse an already existing one! It's part of
	 * the concept of the {@link SetList} to actually create entries on the first (dry) run through the tests, and reuse
	 * old entries during the second (actual) test run, updating their result status etc. in the process.
	 * 
	 * @param aType
	 *            the type of entry
	 * @return the new entry
	 */
	public SetListEntry createEntry(SetListEntryTypes aType) {
		if (entries.size() > entryListPosition) {
			lastCreatedEntryIdMap.put(aType, entryListPosition);
			SetListEntry tempNextEntry = entries.get(entryListPosition);
			if (tempNextEntry.getType() != aType) {
				System.err.println("> SETLIST ENTRY TYPE INCONSISTENCY DETECTED! IS " + aType + ", SHOULD BE "
						+ tempNextEntry.getType());
				System.err.println("> PRINTING ALL SETLIST ENTRIES UP TO CURRENT POSITION " + entryListPosition);
				for (int tempPosition = 0; tempPosition <= entryListPosition; tempPosition++) {
					System.err.println("> " + entries.get(tempPosition));
				}
				throw new IllegalStateException(
						"Severe internal data inconsistency detected! Cannot continue test execution.");
			}
			entryListPosition++;
			return tempNextEntry;
		} else {
			SetListEntry tempNewEntry = new SetListEntry(entryListPosition, aType);
			entries.add(tempNewEntry);
			lastCreatedEntryIdMap.put(aType, tempNewEntry.getId());
			entryListPosition++;
			return tempNewEntry;
		}
	}

	/**
	 * Returns the ID that was last given to an entry of the specified type.
	 * 
	 * @param aType
	 *            the type
	 * @return the ID if available, or null if none has been given to an entry of that type
	 */
	public Integer getLastCreatedEntryId(SetListEntryTypes aType) {
		if (aType != null) {
			return lastCreatedEntryIdMap.get(aType);
		} else {
			if (entries.size() == 0) {
				return null;
			} else {
				return entries.get(entries.size() - 1).getId();
			}
		}
	}

	/**
	 * Returns the lowest ID given to any of the specified entry types.
	 * 
	 * @param someTypes
	 *            the types
	 * @return the ID if available, or null if none has been given to any entry of the types
	 */
	public Integer getLastCreatedEntryId(SetListEntryTypes... someTypes) {
		List<Integer> tempList = new ArrayList<Integer>();
		for (SetListEntryTypes tempType : someTypes) {
			Integer tempEntryRef = getLastCreatedEntryId(tempType);
			if (tempEntryRef != null) {
				tempList.add(tempEntryRef);
			}
		}

		if (tempList.size() == 0) {
			return null;
		} else {
			Collections.sort(tempList);
			return tempList.get(tempList.size() - 1);
		}
	}

	/**
	 * Adds a reference to another entry to a specified parent entry under a specified attribute key.
	 * 
	 * @param aReferringEntry
	 *            the parent entry
	 * @param anAttributeKey
	 *            the attribute key under which the reference shall be created
	 * @param aReferredEntry
	 *            the entry to refer
	 */
	public void addReference(SetListEntry aReferringEntry, SetListEntryAttributeKeys anAttributeKey,
			SetListEntry aReferredEntry) {
		LinkedList<Integer> tempList = (LinkedList<Integer>) aReferringEntry.getAttribute(LinkedList.class,
				anAttributeKey, new LinkedList<Integer>());
		if (!tempList.contains(aReferredEntry.getId())) {
			tempList.add(aReferredEntry.getId());
		}
		aReferredEntry.setAttribute(SetListEntryAttributeKeys.PARENT, aReferringEntry.getId());
	}

	/**
	 * Clears the entry list position.
	 */
	public void rewind() {
		entryListPosition = 0;
		lastCreatedEntryIdMap.clear();
	}

	/**
	 * Integrates a list of updated entries into this {@link SetList}.
	 * 
	 * @param someUpdatedEntries
	 *            the updated entries
	 */
	public void integrateUpdates(SetListEntry[] someUpdatedEntries) {
		for (SetListEntry tempEntry : someUpdatedEntries) {
			entries.set(tempEntry.getId(), tempEntry);
		}
		for (SetListEntry tempEntry : someUpdatedEntries) {
			SetListEntry tempEntryToUse = null;

			if (tempEntry.getType() == SetListEntryTypes.RESULT) {
				SetListEntry tempParent = getParent(tempEntry);

				if (tempParent.getType() == SetListEntryTypes.TEST || tempParent.getType() == SetListEntryTypes.CALL
						|| tempParent.getType() == SetListEntryTypes.TABLETEST) {
					tempEntryToUse = tempParent;
				}
			} else {
				tempEntryToUse = tempEntry;
			}

			if (tempEntryToUse != null) {
				if (resultBearingEntryResultMap != null && resultBearingEntryResultMap.containsKey(tempEntryToUse)) {
					SetListEntryResultStates tempResultState = determineEntryResultState(tempEntryToUse);
					resultBearingEntryResultMap.put(tempEntryToUse, tempResultState);
					Integer tempResultIndex = executableEntryResultIndex.get(tempEntryToUse);
					if (tempResultIndex != null) {
						SetListEntryResultStates tempPreviousState = executableEntryResultStates.set(tempResultIndex,
								tempResultState);

						// Decrement previous state counter, increment new state counter
						executableEntryResultStateCounts.put(tempPreviousState,
								getNumberOfEntriesInResultState(tempPreviousState) - 1);
						executableEntryResultStateCounts.put(tempResultState,
								getNumberOfEntriesInResultState(tempResultState) + 1);
					}
				}

				// In case of tabletest results, also store their result in the result map, since we display those
				// results directly in the tree. See also issue #78, which was fixed by this.
				if (resultBearingEntryResultMap != null && tempEntryToUse.getType() == SetListEntryTypes.TABLETEST
						&& tempEntry.getType() == SetListEntryTypes.RESULT) {
					SetListEntryResultStates tempResultState = determineEntryResultState(tempEntry);
					if (tempResultState != null) {
						resultBearingEntryResultMap.put(tempEntry, tempResultState);
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer tempBuffer = new StringBuffer();
		for (SetListEntry tempEntry : entries) {
			tempBuffer.append(tempEntry.toString() + "\n");
		}

		return tempBuffer.toString();
	}

	/**
	 * Returns a list of entries that are referred by the given entry under the given attribute key.
	 * 
	 * @param anEntry
	 *            the entry
	 * @param anAttributeKey
	 *            the attribute key
	 * @return the list of resolved entries
	 */
	public List<SetListEntry> resolveReferences(SetListEntry anEntry, SetListEntryAttributeKeys anAttributeKey) {
		return resolveReferences((List<Integer>) anEntry.getAttribute(anAttributeKey));
	}

	/**
	 * Resolves a list of entry IDs to the actual entries.
	 * 
	 * @param someItemIds
	 *            the IDs to resolve
	 * @return the list of entries
	 */
	public List<SetListEntry> resolveReferences(List<Integer> someItemIds) {
		List<SetListEntry> tempList = new LinkedList<SetListEntry>();

		if (someItemIds != null) {
			for (Integer tempItemId : someItemIds) {
				tempList.add(entries.get(tempItemId));
			}
		}

		return tempList;
	}

	/**
	 * Resolves a single entry reference (ID).
	 * 
	 * @param aReference
	 *            the ID of the entry
	 * @return the entry, or null if there is none with that ID
	 */
	public SetListEntry resolveReference(Integer aReference) {
		if (aReference == null || aReference < 0 || aReference >= entries.size()) {
			return null;
		} else {
			return entries.get(aReference);
		}
	}

	public SetListEntry getRootEntry() {
		return entries.get(0);
	}

	/**
	 * Returns the parent entry for a given entry.
	 * 
	 * @param anEntry
	 *            the child entry
	 * @return the parent, or null if none was found
	 */
	public SetListEntry getParent(SetListEntry anEntry) {
		if (anEntry == null) {
			return null;
		}
		Integer tempParent = (Integer) anEntry.getAttribute(SetListEntryAttributeKeys.PARENT);
		if (tempParent != null) {
			return resolveReference(tempParent);
		} else {
			return null;
		}
	}

	/**
	 * Gets the fully qualified name for an entry. For more info regarding this name, see
	 * {@link #fullyQualifiedNameMap}.
	 * 
	 * @param anEntry
	 *            the entry to look for
	 * @return the fully qualified name
	 */
	public String getFullyQualifiedName(SetListEntry anEntry) {
		return fullyQualifiedNameMap.get(anEntry);
	}

	/**
	 * Gets the fully qualified name for an entry. For more info regarding this name, see
	 * {@link #fullyQualifiedNameMap}.
	 * 
	 * @param anEntryReference
	 *            the entry to look for
	 * @return the fully qualified name
	 */
	public String getFullyQualifiedName(Integer anEntryReference) {
		return getFullyQualifiedName(resolveReference(anEntryReference));
	}

	/**
	 * Finds an entry based on a given fully qualified name. For more info regarding this name, see
	 * {@link #fullyQualifiedNameMap}.
	 * 
	 * @param aName
	 *            the name to search for
	 * @return the entry or null if none was found
	 */
	public SetListEntry findEntryByFullyQualifiedName(String aName) {
		return fullyQualifiedNameReverseMap.get(aName);
	}

	/**
	 * Determines the fully qualified base name for an entry (this entry is possibly extended with a counter to resolve
	 * duplicates, hence it is a base name). For more info regarding this name, see {@link #fullyQualifiedNameMap}.
	 * 
	 * @param anEntry
	 *            the entry to calculate the name for
	 * @return the name
	 */
	protected String calculateFullyQualifiedBaseName(SetListEntry anEntry) {
		String tempName = (String) anEntry.getAttribute(SetListEntryAttributeKeys.NAME);
		if (tempName == null) {
			tempName = Integer.toString(anEntry.getId());
		}

		SetListEntry tempParent = getParent(anEntry);
		String tempParentName = (tempParent != null ? calculateFullyQualifiedBaseName(tempParent) : "");
		return tempParentName + "|" + tempName;
	}

	/**
	 * Updates the reference to the entry that's currently in execution, recalculating the execution path.
	 * 
	 * @param anEntryReference
	 *            the new entry in execution
	 */
	public void setEntryInExecutionReference(Integer anEntryReference) {
		entryInExecutionReference = anEntryReference;
		List<SetListEntry> tempNewPathOfEntriesInExecution = new ArrayList<SetListEntry>();

		if (anEntryReference != null) {
			SetListEntry tempCurrent = resolveReference(anEntryReference);
			while (tempCurrent != null) {
				tempNewPathOfEntriesInExecution.add(tempCurrent);
				tempCurrent = getParent(tempCurrent);
			}
		}

		pathOfEntriesInExecution = tempNewPathOfEntriesInExecution;
	}

	public SetListEntry getEntryInExecution() {
		return resolveReference(entryInExecutionReference);
	}

	public int getEntryListPosition() {
		return entryListPosition;
	}

	public List<SetListEntry> getEntriesInExecution() {
		return pathOfEntriesInExecution;
	}

	/**
	 * Checks whether a given entry is currently being executed.
	 * 
	 * @param anEntry
	 *            the entry
	 * @return true if the entry is being executed, false otherwise
	 */
	public boolean isEntryInExecution(SetListEntry anEntry) {
		if (entryInExecutionReference == null) {
			return false;
		} else {
			switch (anEntry.getType()) {
			case TEST:
			case CALL:
			case TABLETEST:
			case VARIABLE_ASSIGNMENT:
			case TIMESET:
				return anEntry.getId() == entryInExecutionReference;
			case SETUP:
			case SUITE:
			case TEARDOWN:
				return anEntry.getId() == entryInExecutionReference || pathOfEntriesInExecution.contains(anEntry);
			case EXECUTION:
				return true;
			default:
				return false;
			}
		}
	}

	/**
	 * Returns the name of the fork that executes the given entry.
	 * 
	 * @param anEntry
	 *            the entry
	 * @return the forks' name (index 0) and description (index 1, if available, otherwise null) or null if the entry is
	 *         executed by the master
	 */
	public String[] getForkExecutingEntry(SetListEntry anEntry) {
		String[] tempForkName = new String[2];
		tempForkName[0] = (String) anEntry.getAttribute(SetListEntryAttributeKeys.FORK_NAME);
		if (tempForkName[0] != null) {
			tempForkName[1] = (String) anEntry.getAttribute(SetListEntryAttributeKeys.FORK_DESCRIPTION);
			return tempForkName;
		} else {
			SetListEntry tempParent = getParent(anEntry);
			if (tempParent != null) {
				return getForkExecutingEntry(tempParent);
			} else {
				return null;
			}
		}
	}

	/**
	 * Performs a full dump of all entries in the setlist into the given stream.
	 * 
	 * @param aStream
	 *            the stream to write into
	 */
	public void dumpEntries(PrintStream aStream) {
		for (SetListEntry tempEntry : entries) {
			aStream.println(tempEntry);
		}
	}
}
