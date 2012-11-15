package de.gebit.integrity.runner;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Semaphore;

import org.eclipse.xtext.common.types.JvmType;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import de.gebit.integrity.dsl.Call;
import de.gebit.integrity.dsl.ConstantDefinition;
import de.gebit.integrity.dsl.DateValue;
import de.gebit.integrity.dsl.DslFactory;
import de.gebit.integrity.dsl.ForkDefinition;
import de.gebit.integrity.dsl.ForkParameter;
import de.gebit.integrity.dsl.MethodReference;
import de.gebit.integrity.dsl.NamedCallResult;
import de.gebit.integrity.dsl.NamedResult;
import de.gebit.integrity.dsl.NullValue;
import de.gebit.integrity.dsl.ResultTableHeader;
import de.gebit.integrity.dsl.StaticValue;
import de.gebit.integrity.dsl.Suite;
import de.gebit.integrity.dsl.SuiteDefinition;
import de.gebit.integrity.dsl.SuiteParameter;
import de.gebit.integrity.dsl.SuiteStatement;
import de.gebit.integrity.dsl.SuiteStatementWithResult;
import de.gebit.integrity.dsl.TableTest;
import de.gebit.integrity.dsl.TableTestRow;
import de.gebit.integrity.dsl.Test;
import de.gebit.integrity.dsl.TimeValue;
import de.gebit.integrity.dsl.ValueOrEnumValueOrOperation;
import de.gebit.integrity.dsl.ValueOrEnumValueOrOperationCollection;
import de.gebit.integrity.dsl.Variable;
import de.gebit.integrity.dsl.VariableDefinition;
import de.gebit.integrity.dsl.VariableEntity;
import de.gebit.integrity.dsl.VariantDefinition;
import de.gebit.integrity.dsl.VisibleDivider;
import de.gebit.integrity.dsl.VisibleMultiLineComment;
import de.gebit.integrity.dsl.VisibleSingleLineComment;
import de.gebit.integrity.fixtures.FixtureWrapper;
import de.gebit.integrity.forker.ForkerParameter;
import de.gebit.integrity.operations.OperationWrapper.UnexecutableException;
import de.gebit.integrity.parameter.conversion.UnresolvableVariableHandling;
import de.gebit.integrity.parameter.conversion.ValueConverter;
import de.gebit.integrity.parameter.resolving.ParameterResolver;
import de.gebit.integrity.parameter.variables.VariableManager;
import de.gebit.integrity.remoting.IntegrityRemotingConstants;
import de.gebit.integrity.remoting.entities.setlist.SetList;
import de.gebit.integrity.remoting.entities.setlist.SetListEntry;
import de.gebit.integrity.remoting.entities.setlist.SetListEntryTypes;
import de.gebit.integrity.remoting.server.IntegrityRemotingServer;
import de.gebit.integrity.remoting.server.IntegrityRemotingServerListener;
import de.gebit.integrity.remoting.transport.Endpoint;
import de.gebit.integrity.remoting.transport.enums.BreakpointActions;
import de.gebit.integrity.remoting.transport.enums.ExecutionCommands;
import de.gebit.integrity.remoting.transport.enums.ExecutionStates;
import de.gebit.integrity.remoting.transport.messages.BreakpointUpdateMessage;
import de.gebit.integrity.remoting.transport.messages.IntegrityRemotingVersionMessage;
import de.gebit.integrity.remoting.transport.messages.SetListBaselineMessage;
import de.gebit.integrity.runner.callbacks.CompoundTestRunnerCallback;
import de.gebit.integrity.runner.callbacks.TestRunnerCallback;
import de.gebit.integrity.runner.callbacks.remoting.SetListCallback;
import de.gebit.integrity.runner.forking.DefaultForker;
import de.gebit.integrity.runner.forking.Fork;
import de.gebit.integrity.runner.forking.ForkCallback;
import de.gebit.integrity.runner.forking.ForkException;
import de.gebit.integrity.runner.forking.ForkResultSummary;
import de.gebit.integrity.runner.forking.Forker;
import de.gebit.integrity.runner.results.Result;
import de.gebit.integrity.runner.results.SuiteResult;
import de.gebit.integrity.runner.results.SuiteSummaryResult;
import de.gebit.integrity.runner.results.call.CallResult;
import de.gebit.integrity.runner.results.call.CallResult.UpdatedVariable;
import de.gebit.integrity.runner.results.test.TestComparisonFailureResult;
import de.gebit.integrity.runner.results.test.TestComparisonResult;
import de.gebit.integrity.runner.results.test.TestComparisonSuccessResult;
import de.gebit.integrity.runner.results.test.TestComparisonUndeterminedResult;
import de.gebit.integrity.runner.results.test.TestExceptionSubResult;
import de.gebit.integrity.runner.results.test.TestExecutedSubResult;
import de.gebit.integrity.runner.results.test.TestResult;
import de.gebit.integrity.runner.results.test.TestSubResult;
import de.gebit.integrity.utils.DateUtil;
import de.gebit.integrity.utils.IntegrityDSLUtil;
import de.gebit.integrity.utils.ParameterUtil;
import de.gebit.integrity.utils.ParameterUtil.UnresolvableVariableException;
import de.gebit.integrity.wrapper.WrapperFactory;

/**
 * The test runner executes tests. This class is the core of the Integrity runtime system.
 * 
 * 
 * @author Rene Schneider
 * 
 */
@Singleton
public class DefaultTestRunner implements TestRunner {

	/**
	 * The test model being executed.
	 */
	protected TestModel model;

	/**
	 * The current setlist.
	 */
	protected SetList setList;

	/**
	 * A waiter object used by remoting while waiting for the setlist to be created.
	 */
	protected Object setListWaiter = new Object();

	/**
	 * A semaphore used for single-stepping tests.
	 */
	protected Semaphore executionWaiter = new Semaphore(0);

	/**
	 * Whether the test runner shall pause before executing the next step.
	 */
	protected boolean shallWaitBeforeNextStep;

	/**
	 * All enabled breakpoints.
	 */
	protected Set<Integer> breakpoints = Collections.synchronizedSet(new HashSet<Integer>());

	/**
	 * The callback provided by the creator of the {@link TestRunner}.
	 */
	protected TestRunnerCallback callback;

	/**
	 * The setlist callback (used to create/update the setlist).
	 */
	protected SetListCallback setListCallback;

	/**
	 * The currently used callback, that is, the callback that gets directly called during execution.
	 */
	protected TestRunnerCallback currentCallback;

	/**
	 * The current execution phase.
	 */
	protected Phase currentPhase;

	/**
	 * The variable manager, which keeps track of the variable values (local and global).
	 */
	@Inject
	protected VariableManager variableManager;

	/**
	 * The value converter.
	 */
	@Inject
	protected ValueConverter valueConverter;

	/**
	 * The parameter resolver.
	 */
	@Inject
	protected ParameterResolver parameterResolver;

	/**
	 * The wrapper factory.
	 */
	@Inject
	protected WrapperFactory wrapperFactory;

	/**
	 * The Guice injector.
	 */
	@Inject
	protected Injector injector;

	/**
	 * The remoting server.
	 */
	protected IntegrityRemotingServer remotingServer;

	/**
	 * The remoting listener, which allows the remoting server to influence test execution.
	 */
	protected RemotingListener remotingListener;

	/**
	 * Maps fork definitions to actual fork instances.
	 */
	protected Map<ForkDefinition, Fork> forkMap = new LinkedHashMap<ForkDefinition, Fork>();

	/**
	 * Collects all forks that have died. If forks die after they have executed their last statement, this is perfectly
	 * fine, but if forks die earlier, this set is used to detect that erroneous situation.
	 */
	protected Set<ForkDefinition> diedForks = new HashSet<ForkDefinition>();

	/**
	 * The original command line arguments, as given to the test runner by the test runner creator.
	 */
	protected String[] commandLineArguments;

	/**
	 * If this JVM instance is executing a fork, the name is stored here.
	 */
	protected static final String MY_FORK_NAME = System.getProperty(Forker.SYSPARAM_FORK_NAME);

	/**
	 * The system property that allows to override the timeout used when connecting to forks.
	 */
	protected static final String FORK_CONNECTION_TIMEOUT_PROPERTY = "integrity.fork.timeout";

	/**
	 * The default connection timeout, in seconds.
	 */
	protected static final int FORK_CONNECTION_TIMEOUT_DEFAULT = 180;

	/**
	 * The timeout in milliseconds used for a single connection attempt to a fork. If this timeout is hit, but the total
	 * timeout for connecting is not yet over, another attempt is being started.
	 */
	protected static final int FORK_SINGLE_CONNECT_TIMEOUT = 10000;

	/**
	 * The delay until connection attempts are made to a newly started fork.
	 */
	protected static final int FORK_CONNECT_DELAY = 5000;

	/**
	 * The interval in which the forks' execution state is checked on first connect.
	 */
	protected static final int FORK_PAUSE_WAIT_INTERVAL = 200;

	/**
	 * The fork that is currently being executed.
	 */
	protected ForkDefinition forkInExecution;

	/**
	 * The currently executed test variant.
	 */
	protected VariantDefinition variantInExecution;

	/**
	 * The setup suites that have been executed.
	 */
	protected Map<ForkDefinition, Set<SuiteDefinition>> setupSuitesExecuted = new HashMap<ForkDefinition, Set<SuiteDefinition>>();

	/**
	 * Initializes a fresh test runner instance.
	 * 
	 * @param aModel
	 *            the model to execute
	 * @param aCallback
	 *            the callback to use to report test results
	 * @param aRemotingPort
	 *            the port on which the remoting server should listen, or null if remoting should be disabled
	 * @param aRemotingBindHost
	 *            the host name (or IP) to which the remoting server should bind
	 * @param someCommandLineArguments
	 *            all command line arguments as given to the original Java programs' main routine (required for
	 *            forking!)
	 * @throws IOException
	 *             if the remoting server startup fails
	 */
	public void initialize(TestModel aModel, TestRunnerCallback aCallback, Integer aRemotingPort,
			String aRemotingBindHost, String[] someCommandLineArguments) throws IOException {
		model = aModel;
		callback = aCallback;

		if (callback instanceof CompoundTestRunnerCallback) {
			((CompoundTestRunnerCallback) callback).injectDependencies(injector);
		} else {
			injector.injectMembers(callback);
		}

		commandLineArguments = someCommandLineArguments;
		Integer tempRemotingPort = aRemotingPort;
		if (isFork()) {
			tempRemotingPort = Integer.parseInt(System.getProperty(Forker.SYSPARAM_FORK_REMOTING_PORT));
		}
		if (tempRemotingPort != null) {
			remotingListener = new RemotingListener();
			remotingServer = new IntegrityRemotingServer(aRemotingBindHost, tempRemotingPort, remotingListener);
		}
	}

	/**
	 * Shuts down this test runner instance.
	 * 
	 * @param anEmptyRemotingOutputQueueFlag
	 *            true if the remoting server shall be given time to send all remaining messages to clients while
	 *            closing connections
	 */
	@Override
	public void shutdown(boolean anEmptyRemotingOutputQueueFlag) {
		if (remotingServer != null) {
			remotingServer.closeAll(anEmptyRemotingOutputQueueFlag);
		}
	}

	@Override
	public SuiteSummaryResult run(SuiteDefinition aRootSuite, VariantDefinition aVariant, boolean aBlockForRemotingFlag) {
		Suite tempRootSuiteCall = DslFactory.eINSTANCE.createSuite();
		tempRootSuiteCall.setDefinition(aRootSuite);

		return run(tempRootSuiteCall, aVariant, aBlockForRemotingFlag);
	}

	/**
	 * Executes a specific suite call.
	 * 
	 * @param aRootSuiteCall
	 *            the suite call to execute
	 * @param aBlockForRemotingFlag
	 *            whether execution should pause before actually starting until execution is resumed via remoting
	 * @return the suite execution result
	 */
	public SuiteSummaryResult run(Suite aRootSuiteCall, VariantDefinition aVariant, boolean aBlockForRemotingFlag) {
		variantInExecution = aVariant;
		boolean tempBlockForRemoting = isFork() ? false : aBlockForRemotingFlag;

		try {
			if (remotingServer != null) {
				currentPhase = Phase.DRY_RUN;
				SetList tempSetList = new SetList();
				reset();
				setListCallback = new SetListCallback(tempSetList, remotingServer);
				injector.injectMembers(setListCallback);
				currentCallback = setListCallback;

				currentCallback.setDryRun(true);
				runInternal(aRootSuiteCall);
				currentCallback.setDryRun(false);

				synchronized (setListWaiter) {
					setList = tempSetList;
					setListWaiter.notify();
				}

				if (tempBlockForRemoting) {
					try {
						waitForContinue(false);
					} catch (InterruptedException exc) {
						if (remotingServer != null) {
							remotingServer.closeAll(false);
						}
						return null;
					}
				}
				tempSetList.rewind();
				currentCallback = new CompoundTestRunnerCallback(setListCallback, callback);
			} else {
				currentCallback = callback;
			}

			currentPhase = Phase.TEST_RUN;
			reset();

			if (isFork()) {
				// the callback will require the remoting server to be able to push stuff to the master
				currentCallback.setRemotingServer(remotingServer);

				// we start out in "dry run" mode if we're a fork
				currentCallback.setDryRun(true);
			}

			return runInternal(aRootSuiteCall);
		} finally {
			if (remotingServer != null) {
				remotingServer.closeAll(true);
			}
			killAllForks();
		}
	}

	/**
	 * Resets the internal variable state.
	 */
	protected void reset() {
		variableManager.clear();
		setupSuitesExecuted.clear();
	}

	/**
	 * Actually executes a root suite call.
	 * 
	 * @param aRootSuiteCall
	 *            the suite call to execute
	 * @return the result
	 */
	protected SuiteSummaryResult runInternal(Suite aRootSuiteCall) {
		variableManager.clear();

		if (currentCallback != null) {
			currentCallback.onExecutionStart(model, variantInExecution);
		}

		for (VariableDefinition tempVariableDef : model.getVariableDefinitionsInPackages()) {
			defineVariable(tempVariableDef, null);
		}
		for (ConstantDefinition tempConstantDef : model.getConstantDefinitionsInPackages()) {
			defineConstant(tempConstantDef, null);
		}

		SuiteSummaryResult tempResult = callSuiteSingle(aRootSuiteCall);

		if (currentCallback != null) {
			currentCallback.onExecutionFinish(model, tempResult);
		}

		return tempResult;
	}

	/**
	 * Performs a specified suite call.
	 * 
	 * @param aSuiteCall
	 *            the suite call to execute
	 * @return the suite results (multiple if the suite has an execution multiplier)
	 */
	protected List<SuiteSummaryResult> callSuite(Suite aSuiteCall) {
		int tempCount = 1;
		if (aSuiteCall.getMultiplier() != null && aSuiteCall.getMultiplier().getCount() != null) {
			tempCount = aSuiteCall.getMultiplier().getCount().intValue();
		}

		List<SuiteSummaryResult> tempResults = new ArrayList<SuiteSummaryResult>();
		for (int i = 0; i < tempCount; i++) {
			tempResults.add(callSuiteSingle(aSuiteCall));
		}

		return tempResults;
	}

	/**
	 * Kills all forks. 'Nuff said.
	 */
	protected void killAllForks() {
		for (Fork tempFork : forkMap.values()) {
			tempFork.kill();
		}
	}

	/**
	 * Performs a specified suite call (doesn't honor the multiplier!).
	 * 
	 * @param aSuiteCall
	 *            the suite call to execute
	 * @return the suite result
	 */
	protected SuiteSummaryResult callSuiteSingle(Suite aSuiteCall) {
		boolean tempForkInExecutionOnEntry = forkInExecution != null;

		if (aSuiteCall.getFork() != null && !tempForkInExecutionOnEntry) {
			if (!isFork() && forkInExecution != null && aSuiteCall.getFork() != forkInExecution) {
				throw new UnsupportedOperationException(
						"It is not supported to execute another fork while inside a fork ("
								+ aSuiteCall.getFork().getName() + " inside " + forkInExecution.getName() + ").");
			}
			forkInExecution = aSuiteCall.getFork();
			currentCallback.setForkInExecution(forkInExecution);
		}

		if (currentPhase == Phase.TEST_RUN && !tempForkInExecutionOnEntry) {
			// all of this only has to be done in case of a real test run
			if (!isFork()) {
				// we're the master
				if (forkInExecution != null) {
					// set the currently executed entry to the suite call entry that will be created next
					// this signifies that a fork is about to be started executing the highlighted suite
					if (remotingServer != null) {
						remotingServer.updateSetList(setList.getEntryListPosition(), new SetListEntry[0]);
					}
					// we may need to start a new fork
					if (!forkMap.containsKey(aSuiteCall.getFork())) {
						// but first see if this fork has already died once. if true, then the fork has died
						// prematurely, which means we cannot continue execution at all
						if (diedForks.contains(aSuiteCall.getFork())) {
							killAllForks();
							throw new RuntimeException("Fork " + aSuiteCall.getFork().getName()
									+ " has died prematurely!");
						}
						try {
							forkMap.put(aSuiteCall.getFork(), createFork(aSuiteCall));
						} catch (ForkException exc) {
							// forking failed -> cannot continue at all :( kill all other still-living forks and
							// then exit with a runtime exception
							killAllForks();
							throw new RuntimeException(exc);
						}
					}
					// the master will perform all of this in dry run mode
					currentCallback.setDryRun(true);
				}
			} else {
				if (forkInExecution != null) {
					// now see if this is a job for us
					if (forkInExecution.getName().equals(MY_FORK_NAME)) {
						// we're a fork, and we are at a point where we're gonna execute some stuff
						// but we have to wait until our master gives us the 'go'!
						shallWaitBeforeNextStep = true;
						pauseIfRequiredByRemoteClient(true);

						// and now we leave dry run mode
						currentCallback.setDryRun(false);
					}
				}
			}
		}

		Map<SuiteDefinition, Result> tempSetupResults = new HashMap<SuiteDefinition, Result>();
		Map<SuiteDefinition, Result> tempTearDownResults = new HashMap<SuiteDefinition, Result>();

		if (currentCallback != null) {
			currentCallback.onSuiteStart(aSuiteCall);
		}

		List<SuiteDefinition> tempSetupSuitesExecuted = new ArrayList<SuiteDefinition>();
		Set<SuiteDefinition> tempSetupsAlreadyRun = setupSuitesExecuted.get(forkInExecution);
		if (tempSetupsAlreadyRun == null) {
			tempSetupsAlreadyRun = new HashSet<SuiteDefinition>();
			setupSuitesExecuted.put(forkInExecution, tempSetupsAlreadyRun);
		}
		for (SuiteDefinition tempSetupSuite : aSuiteCall.getDefinition().getDependencies()) {
			if (!tempSetupsAlreadyRun.contains(tempSetupSuite)) {
				tempSetupsAlreadyRun.add(tempSetupSuite);
				tempSetupSuitesExecuted.add(tempSetupSuite);
				if (currentCallback != null) {
					currentCallback.onSetupStart(tempSetupSuite);
				}

				long tempStart = System.nanoTime();
				Map<SuiteStatementWithResult, List<? extends Result>> tempSuiteResults = executeSuite(tempSetupSuite);
				SuiteResult tempSetupResult = (!shouldExecuteFixtures()) ? null : new SuiteResult(tempSuiteResults,
						null, null, System.nanoTime() - tempStart);
				tempSetupResults.put(tempSetupSuite, tempSetupResult);

				if (currentCallback != null) {
					currentCallback.onSetupFinish(tempSetupSuite, tempSetupResult);
				}
			}
		}

		for (SuiteParameter tempParam : aSuiteCall.getParameters()) {
			if (tempParam.getValue() instanceof Variable) {
				Variable tempVariable = (Variable) tempParam.getValue();
				defineVariable(tempParam.getName(), variableManager.get(tempVariable.getName()),
						aSuiteCall.getDefinition());
			} else {
				defineVariable(tempParam.getName(), tempParam.getValue(), aSuiteCall.getDefinition());
			}
		}

		long tempSuiteDuration = System.nanoTime();
		Map<SuiteStatementWithResult, List<? extends Result>> tempResults = executeSuite(aSuiteCall.getDefinition());
		tempSuiteDuration = System.nanoTime() - tempSuiteDuration;

		for (int i = tempSetupSuitesExecuted.size() - 1; i >= 0; i--) {
			SuiteDefinition tempSetupSuite = tempSetupSuitesExecuted.get(i);
			for (SuiteDefinition tempTearDownSuite : tempSetupSuite.getFinalizers()) {
				if (currentCallback != null) {
					currentCallback.onTearDownStart(tempTearDownSuite);
				}

				long tempStart = System.nanoTime();
				Map<SuiteStatementWithResult, List<? extends Result>> tempSuiteResults = executeSuite(tempTearDownSuite);
				SuiteResult tempTearDownResult = (!shouldExecuteFixtures()) ? null : new SuiteResult(tempSuiteResults,
						null, null, System.nanoTime() - tempStart);
				tempTearDownResults.put(tempTearDownSuite, tempTearDownResult);

				if (currentCallback != null) {
					currentCallback.onTearDownFinish(tempTearDownSuite, tempTearDownResult);
				}
			}

			tempSetupsAlreadyRun.remove(tempSetupSuite);
		}

		SuiteSummaryResult tempResult = (!shouldExecuteFixtures()) ? null : new SuiteResult(tempResults,
				tempSetupResults, tempTearDownResults, tempSuiteDuration);

		if (currentCallback != null) {
			currentCallback.onSuiteFinish(aSuiteCall, tempResult);
		}

		if (forkInExecution != null && forkInExecution.equals(aSuiteCall.getFork())) {
			if (currentPhase == Phase.TEST_RUN) {
				// all of this only has to be done in case of a real test run

				if (!isFork()) {
					// we're the master and need to kick off the fork, which then actually executes the stuff we've just
					// jumped over
					Fork tempFork = forkMap.get(forkInExecution);
					tempSuiteDuration = System.nanoTime();
					ForkResultSummary tempForkResultSummary = tempFork.executeNextSegment();
					tempSuiteDuration = System.nanoTime() - tempSuiteDuration;

					if (tempForkResultSummary != null) {
						tempResult = new SuiteSummaryResult(tempForkResultSummary.getSuccessCount(),
								tempForkResultSummary.getFailureCount(), tempForkResultSummary.getExceptionCount(),
								tempSuiteDuration);
					}

					// and afterwards we'll switch back to real test mode
					currentCallback.setDryRun(false);
				} else {
					// we're a fork and will return to dry run mode
					currentCallback.setDryRun(true);
				}
			}
			forkInExecution = null;
			currentCallback.setForkInExecution(null);
		}

		return tempResult;
	}

	/**
	 * Executes a suite.
	 * 
	 * @param aSuite
	 *            the suite to execute
	 * @return a map that maps statements to results
	 */
	protected Map<SuiteStatementWithResult, List<? extends Result>> executeSuite(SuiteDefinition aSuite) {
		Map<SuiteStatementWithResult, List<? extends Result>> tempResults = new LinkedHashMap<SuiteStatementWithResult, List<? extends Result>>();

		for (SuiteStatement tempStatement : aSuite.getStatements()) {
			if (tempStatement instanceof Suite) {
				Suite tempSuite = (Suite) tempStatement;
				boolean tempExecute = false;
				if (tempSuite.getVariants().size() > 0) {
					for (VariantDefinition tempVariant : tempSuite.getVariants()) {
						if (tempVariant == variantInExecution) {
							tempExecute = true;
							break;
						}
					}
				} else {
					tempExecute = true;
				}

				if (tempExecute) {
					tempResults.put((Suite) tempStatement, callSuite((Suite) tempStatement));
				}
			} else if (tempStatement instanceof Test) {
				List<Result> tempInnerList = new ArrayList<Result>();
				tempInnerList.add(executeTest((Test) tempStatement));
				tempResults.put((Test) tempStatement, tempInnerList);
			} else if (tempStatement instanceof TableTest) {
				List<Result> tempInnerList = new ArrayList<Result>();
				tempInnerList.add(executeTableTest((TableTest) tempStatement));
				tempResults.put((TableTest) tempStatement, tempInnerList);
			} else if (tempStatement instanceof Call) {
				List<Result> tempInnerList = new ArrayList<Result>();
				tempInnerList.addAll(executeCall((Call) tempStatement));
				tempResults.put((Call) tempStatement, tempInnerList);
			} else if (tempStatement instanceof VariableDefinition) {
				defineVariable((VariableDefinition) tempStatement, aSuite);
			} else if (tempStatement instanceof ConstantDefinition) {
				defineConstant((ConstantDefinition) tempStatement, aSuite);
			} else if (tempStatement instanceof VisibleSingleLineComment) {
				if (currentCallback != null) {
					currentCallback.onVisibleComment(IntegrityDSLUtil
							.cleanSingleLineComment((VisibleSingleLineComment) tempStatement));
				}
			} else if (tempStatement instanceof VisibleMultiLineComment) {
				if (currentCallback != null) {
					currentCallback.onVisibleComment(IntegrityDSLUtil
							.cleanMultiLineComment((VisibleMultiLineComment) tempStatement));
				}
			} else if (tempStatement instanceof VisibleDivider) {
				if (currentCallback != null) {
					currentCallback.onVisibleDivider(((VisibleDivider) tempStatement).getContent());
				}
			}
		}

		return tempResults;
	}

	/**
	 * Defines a variable.
	 * 
	 * @param aDefinition
	 *            the variable definition
	 * @param aSuite
	 *            the suite in which the variable is defined
	 */
	protected void defineVariable(VariableDefinition aDefinition, SuiteDefinition aSuite) {
		defineVariable(aDefinition.getName(), aDefinition.getInitialValue(), aSuite);
	}

	/**
	 * Defines a constant.
	 * 
	 * @param aDefinition
	 *            the constant definition
	 * @param aSuite
	 *            the suite in which the constant is defined
	 */
	protected void defineConstant(ConstantDefinition aDefinition, SuiteDefinition aSuite) {
		StaticValue tempValue = parameterResolver.resolveConstantValue(aDefinition, variantInExecution);
		if (tempValue != null) {
			defineVariable(aDefinition.getName(), tempValue, aSuite);
		}
	}

	/**
	 * Defines a variable.
	 * 
	 * @param anEntity
	 *            the variable entity
	 * @param anInitialValue
	 *            the initial variable value, or null if the variable is not initialized
	 * @param aSuite
	 *            the suite in which the variable is defined
	 */
	protected void defineVariable(VariableEntity anEntity, Object anInitialValue, SuiteDefinition aSuite) {
		Object tempInitialValue = null;
		if (anInitialValue instanceof Variable) {
			tempInitialValue = variableManager.get(((Variable) anInitialValue).getName());
		} else {
			tempInitialValue = anInitialValue;
		}

		variableManager.set(anEntity, tempInitialValue);
		if (currentCallback != null) {
			currentCallback.onVariableDefinition(anEntity, aSuite, tempInitialValue);
		}
	}

	/**
	 * Sets the value of a variable.
	 * 
	 * @param anEntity
	 *            the variable entity to update
	 * @param aValue
	 *            the new value
	 * @param aDoSendUpdateFlag
	 *            whether this update should be sent to connected master/slaves
	 */
	protected void setVariableValue(VariableEntity anEntity, Object aValue, boolean aDoSendUpdateFlag) {
		variableManager.set(anEntity, aValue);
		if (aDoSendUpdateFlag) {
			if (isFork()) {
				// A fork will have to send updates to its master
				if (remotingServer != null) {
					String tempName = IntegrityDSLUtil.getQualifiedVariableEntityName(anEntity, true);
					if (aValue == null || (aValue instanceof Serializable)) {
						remotingServer.sendVariableUpdate(tempName, (Serializable) aValue);
					} else {
						System.err.println("SKIPPED SYNCING OF VARIABLE '" + tempName + "' TO MASTER - VALUE '"
								+ aValue + "' OF TYPE '" + aValue.getClass().getName() + "' IS NOT SERIALIZABLE!");
					}
				}
			} else {
				// The master will have to update all active forks.
				for (Entry<ForkDefinition, Fork> tempEntry : forkMap.entrySet()) {
					tempEntry.getValue().updateVariableValue(anEntity, aValue);
				}
			}
		}
	}

	/**
	 * Updates a variables' value.
	 * 
	 * @param aQualifiedVariableName
	 *            the name of the variable to update
	 * @param aValue
	 *            the new value
	 * @param aDoSendUpdateFlag
	 *            whether this update should be sent to connected master/slaves
	 */
	protected void setVariableValue(String aQualifiedVariableName, Object aValue, boolean aDoSendUpdateFlag) {
		VariableEntity tempEntity = variableManager.findEntity(aQualifiedVariableName);
		if (tempEntity != null) {
			setVariableValue(tempEntity, aValue, aDoSendUpdateFlag);
		}
	}

	/**
	 * Executes a test (doesn't pay attention to the multiplier).
	 * 
	 * @param aTest
	 *            the test to execute
	 * @return the result
	 */
	protected TestResult executeTest(Test aTest) {
		TestModel.ensureModelPartConsistency(aTest);

		if (currentCallback != null) {
			currentCallback.onTestStart(aTest);
		}

		TestResult tempReturn = null;
		TestComparisonResult tempComparisonResult;
		Throwable tempException = null;
		Long tempDuration = null;

		Map<String, TestComparisonResult> tempComparisonMap = new LinkedHashMap<String, TestComparisonResult>();
		if (!shouldExecuteFixtures()) {
			if (aTest.getResults() != null && aTest.getResults().size() > 0) {
				for (NamedResult tempNamedResult : aTest.getResults()) {
					String tempParameter = IntegrityDSLUtil
							.getExpectedResultNameStringFromTestResultName(tempNamedResult.getName());
					tempComparisonResult = new TestComparisonUndeterminedResult(tempParameter,
							tempNamedResult.getValue());
					tempComparisonMap.put(tempParameter, tempComparisonResult);
				}
			} else {
				tempComparisonResult = new TestComparisonUndeterminedResult(ParameterUtil.DEFAULT_PARAMETER_NAME,
						aTest.getResult());
				tempComparisonMap.put(ParameterUtil.DEFAULT_PARAMETER_NAME, tempComparisonResult);
			}
		} else {
			pauseIfRequiredByRemoteClient(false);

			long tempStart = System.nanoTime();

			FixtureWrapper<?> tempFixtureInstance = null;
			try {
				Map<String, Object> tempParameters = parameterResolver.createParameterMap(aTest, true, false);

				tempFixtureInstance = wrapperFactory.newFixtureWrapper(aTest.getDefinition().getFixtureMethod()
						.getType());

				tempStart = System.nanoTime();
				Object tempFixtureResult = executeFixtureMethod(tempFixtureInstance, aTest.getDefinition()
						.getFixtureMethod(), tempParameters);
				tempDuration = System.nanoTime() - tempStart;

				if (aTest.getResults() != null && aTest.getResults().size() > 0) {
					Map<String, Object> tempFixtureResultMap = ParameterUtil
							.getValuesFromNamedResultContainer(tempFixtureResult);

					for (NamedResult tempNamedResult : aTest.getResults()) {
						String tempResultName = IntegrityDSLUtil
								.getExpectedResultNameStringFromTestResultName(tempNamedResult.getName());
						Object tempSingleFixtureResult = tempFixtureResultMap.get(tempResultName);
						if (compareResult(tempSingleFixtureResult, tempNamedResult.getValue(), tempFixtureInstance,
								aTest.getDefinition().getFixtureMethod())) {
							tempComparisonResult = new TestComparisonSuccessResult(tempResultName,
									tempSingleFixtureResult, tempNamedResult.getValue());
						} else {
							tempComparisonResult = new TestComparisonFailureResult(tempResultName,
									tempSingleFixtureResult, tempNamedResult.getValue());
						}
						tempComparisonMap.put(tempResultName, tempComparisonResult);
					}
				} else {
					if (compareResult(tempFixtureResult, aTest.getResult(), tempFixtureInstance, aTest.getDefinition()
							.getFixtureMethod())) {
						tempComparisonResult = new TestComparisonSuccessResult(ParameterUtil.DEFAULT_PARAMETER_NAME,
								tempFixtureResult, aTest.getResult());
					} else {
						tempComparisonResult = new TestComparisonFailureResult(ParameterUtil.DEFAULT_PARAMETER_NAME,
								tempFixtureResult, aTest.getResult());
					}
					tempComparisonMap.put(ParameterUtil.DEFAULT_PARAMETER_NAME, tempComparisonResult);
				}
				// SUPPRESS CHECKSTYLE IllegalCatch
			} catch (Throwable exc) {
				tempDuration = System.nanoTime() - tempStart;
				tempException = exc;
				tempComparisonResult = new TestComparisonUndeterminedResult(ParameterUtil.DEFAULT_PARAMETER_NAME,
						aTest.getResult());
				tempComparisonMap.put(ParameterUtil.DEFAULT_PARAMETER_NAME, tempComparisonResult);
			} finally {
				if (tempFixtureInstance != null) {
					tempFixtureInstance.release();
				}
			}
		}

		List<TestSubResult> tempSubResults = new LinkedList<TestSubResult>();
		if (tempException != null) {
			tempSubResults.add(new TestExceptionSubResult(tempException, tempComparisonMap, tempDuration));
		} else {
			tempSubResults.add(new TestExecutedSubResult(tempComparisonMap, tempDuration));
		}
		tempReturn = new TestResult(tempSubResults, tempDuration);

		if (currentCallback != null) {
			currentCallback.onTestFinish(aTest, tempReturn);
		}

		return tempReturn;
	}

	/**
	 * Executes a table test.
	 * 
	 * @param aTest
	 *            the test to execute
	 * @return the result
	 */
	protected TestResult executeTableTest(TableTest aTest) {
		TestModel.ensureModelPartConsistency(aTest);

		if (currentCallback != null) {
			currentCallback.onTableTestStart(aTest);
		}

		if (currentPhase == Phase.TEST_RUN) {
			pauseIfRequiredByRemoteClient(false);
		}

		List<TestSubResult> tempSubResults = new LinkedList<TestSubResult>();
		long tempOuterStart = System.nanoTime();

		FixtureWrapper<?> tempFixtureInstance = null;
		try {
			for (TableTestRow tempRow : aTest.getRows()) {
				if (currentCallback != null) {
					currentCallback.onTableTestRowStart(aTest, tempRow);
				}

				Map<String, TestComparisonResult> tempComparisonMap = new LinkedHashMap<String, TestComparisonResult>();
				TestComparisonResult tempComparisonResult = null;
				Throwable tempException = null;
				Long tempDuration = null;

				if (!shouldExecuteFixtures()) {
					if (aTest.getResultHeaders() != null && aTest.getResultHeaders().size() > 0) {
						int tempColumn = aTest.getParameterHeaders().size();
						for (ResultTableHeader tempNamedResultHeader : aTest.getResultHeaders()) {
							String tempParameter = IntegrityDSLUtil
									.getExpectedResultNameStringFromTestResultName(tempNamedResultHeader.getName());
							ValueOrEnumValueOrOperationCollection tempExpectedValue = (tempColumn < tempRow.getValues()
									.size()) ? tempRow.getValues().get(tempColumn).getValue() : null;
							tempComparisonResult = new TestComparisonUndeterminedResult(tempParameter,
									tempExpectedValue);
							tempComparisonMap.put(tempParameter, tempComparisonResult);

							tempColumn++;
						}
					} else {
						ValueOrEnumValueOrOperationCollection tempExpectedValue = null;
						if (aTest.getDefaultResultColumn() != null) {
							// the last column MUST be the result column
							tempExpectedValue = tempRow.getValues().get(tempRow.getValues().size() - 1).getValue();
						}
						tempComparisonResult = new TestComparisonUndeterminedResult(
								ParameterUtil.DEFAULT_PARAMETER_NAME, tempExpectedValue);
						tempComparisonMap.put(ParameterUtil.DEFAULT_PARAMETER_NAME, tempComparisonResult);
					}
				} else {
					long tempStart = System.nanoTime();
					try {
						Map<String, Object> tempParameters = parameterResolver.createParameterMap(aTest, tempRow, true,
								false);

						if (tempFixtureInstance == null) {
							// only instantiate on first pass
							tempFixtureInstance = wrapperFactory.newFixtureWrapper(aTest.getDefinition()
									.getFixtureMethod().getType());
						}

						tempStart = System.nanoTime();
						Object tempFixtureResult = executeFixtureMethod(tempFixtureInstance, aTest.getDefinition()
								.getFixtureMethod(), tempParameters);
						tempDuration = System.nanoTime() - tempStart;

						if (aTest.getResultHeaders() != null && aTest.getResultHeaders().size() > 0) {
							Map<String, Object> tempFixtureResultMap = ParameterUtil
									.getValuesFromNamedResultContainer(tempFixtureResult);

							int tempColumn = aTest.getParameterHeaders().size();
							for (ResultTableHeader tempNamedResultHeader : aTest.getResultHeaders()) {
								String tempResultName = IntegrityDSLUtil
										.getExpectedResultNameStringFromTestResultName(tempNamedResultHeader.getName());
								ValueOrEnumValueOrOperationCollection tempExpectedValue = (tempColumn < tempRow
										.getValues().size()) ? tempRow.getValues().get(tempColumn).getValue() : null;

								Object tempSingleFixtureResult = tempFixtureResultMap.get(tempResultName);

								if (compareResult(tempSingleFixtureResult, tempExpectedValue, tempFixtureInstance,
										aTest.getDefinition().getFixtureMethod())) {
									tempComparisonResult = new TestComparisonSuccessResult(tempResultName,
											tempSingleFixtureResult, tempExpectedValue);
								} else {
									tempComparisonResult = new TestComparisonFailureResult(tempResultName,
											tempSingleFixtureResult, tempExpectedValue);
								}
								tempComparisonMap.put(tempResultName, tempComparisonResult);

								tempColumn++;
							}
						} else {
							ValueOrEnumValueOrOperationCollection tempExpectedValue = null;
							if (aTest.getDefaultResultColumn() != null) {
								// the last column MUST be the result column
								tempExpectedValue = tempRow.getValues().get(tempRow.getValues().size() - 1).getValue();
							}

							if (compareResult(tempFixtureResult, tempExpectedValue, tempFixtureInstance, aTest
									.getDefinition().getFixtureMethod())) {
								tempComparisonResult = new TestComparisonSuccessResult(
										ParameterUtil.DEFAULT_PARAMETER_NAME, tempFixtureResult, tempExpectedValue);
							} else {
								tempComparisonResult = new TestComparisonFailureResult(
										ParameterUtil.DEFAULT_PARAMETER_NAME, tempFixtureResult, tempExpectedValue);
							}
							tempComparisonMap.put(ParameterUtil.DEFAULT_PARAMETER_NAME, tempComparisonResult);
						}
						// SUPPRESS CHECKSTYLE IllegalCatch
					} catch (Throwable exc) {
						tempDuration = System.nanoTime() - tempStart;
						tempException = exc;
						// add undetermined result entries for all comparisons
						if (aTest.getResultHeaders() != null && aTest.getResultHeaders().size() > 0) {
							int tempColumn = aTest.getParameterHeaders().size();
							for (ResultTableHeader tempNamedResultHeader : aTest.getResultHeaders()) {
								String tempResultName = IntegrityDSLUtil
										.getExpectedResultNameStringFromTestResultName(tempNamedResultHeader.getName());
								ValueOrEnumValueOrOperationCollection tempExpectedValue = (tempColumn < tempRow
										.getValues().size()) ? tempRow.getValues().get(tempColumn).getValue() : null;
								tempComparisonResult = new TestComparisonUndeterminedResult(tempResultName,
										tempExpectedValue);
								tempComparisonMap.put(tempResultName, tempComparisonResult);
								tempColumn++;
							}
						} else {
							ValueOrEnumValueOrOperationCollection tempExpectedValue = null;
							if (aTest.getDefaultResultColumn() != null) {
								// the last column MUST be the result column
								tempExpectedValue = tempRow.getValues().get(tempRow.getValues().size() - 1).getValue();
							}
							tempComparisonResult = new TestComparisonUndeterminedResult(
									ParameterUtil.DEFAULT_PARAMETER_NAME, tempExpectedValue);
							tempComparisonMap.put(ParameterUtil.DEFAULT_PARAMETER_NAME, tempComparisonResult);
						}
					}
				}

				TestSubResult tempSubResult;

				if (tempException != null) {
					tempSubResult = new TestExceptionSubResult(tempException, tempComparisonMap, tempDuration);
				} else {
					tempSubResult = new TestExecutedSubResult(tempComparisonMap, tempDuration);
				}
				tempSubResults.add(tempSubResult);

				if (currentCallback != null) {
					currentCallback.onTableTestRowFinish(aTest, tempRow, tempSubResult);
				}
			}
		} finally {
			if (tempFixtureInstance != null) {
				tempFixtureInstance.release();
			}
		}

		Long tempOuterDuration = System.nanoTime() - tempOuterStart;

		TestResult tempReturn = new TestResult(tempSubResults, currentPhase == Phase.DRY_RUN ? null : tempOuterDuration);

		if (currentCallback != null) {
			currentCallback.onTableTestFinish(aTest, tempReturn);
		}

		return tempReturn;
	}

	/**
	 * Calls a given method on a fixture instance.
	 * 
	 * @param aFixtureInstance
	 *            the fixture
	 * @param aMethod
	 *            the method reference to execute
	 * @param someParameters
	 *            the parameters to use for the call
	 * @return the return value
	 * @throws Exception
	 */
	protected Object executeFixtureMethod(FixtureWrapper<?> aFixtureInstance, MethodReference aMethod,
			Map<String, Object> someParameters) throws Exception {
		return aFixtureInstance.execute(aMethod.getMethod().getSimpleName(), someParameters);
	}

	/**
	 * Loads a class by resolving a given {@link JvmType}.
	 * 
	 * @param aType
	 *            the type to load
	 * @return the class
	 * @throws ClassNotFoundException
	 */
	protected Class<?> getClassForJvmType(JvmType aType) throws ClassNotFoundException {
		return getModelClassLoader().loadClass(aType.getQualifiedName());
	}

	/**
	 * Returns the classloader defined in the model. This one should be used for instantiation of fixtures and
	 * operations.
	 * 
	 * @return the classloader defined in the test model
	 */
	protected ClassLoader getModelClassLoader() {
		return model.getInjector().getInstance(ClassLoader.class);
	}

	/**
	 * Compares the expected result with the actual result obtained from a test fixture.
	 * 
	 * @param aFixtureResult
	 *            the test fixture result
	 * @param anExpectedResult
	 *            the expected result as specified in the test script
	 * @param aFixtureInstance
	 *            the fixture instance that was used
	 * @param aFixtureMethod
	 *            the fixture method that was called
	 * @return true if the comparison was successful, false if the results are not considered equal
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws UnexecutableException
	 */
	protected boolean compareResult(Object aFixtureResult, ValueOrEnumValueOrOperationCollection anExpectedResult,
			FixtureWrapper<?> aFixtureInstance, MethodReference aFixtureMethod) throws ClassNotFoundException,
			UnexecutableException, InstantiationException {
		if (anExpectedResult != null) {
			if (aFixtureResult == null) {
				if (anExpectedResult.getMoreValues().size() > 0) {
					// if there's more than one value expected, this can never equal a single null value
					return false;
				} else {
					return (anExpectedResult.getValue() instanceof NullValue);
				}
			} else {
				if (aFixtureInstance.isCustomComparatorFixture()) {
					// Custom comparators will get whole arrays at once if arrays are used
					Object tempConvertedResult;
					Class<?> tempConversionTargetType = aFixtureResult.getClass().isArray() ? aFixtureResult.getClass()
							.getComponentType() : aFixtureResult.getClass();
					if (anExpectedResult.getMoreValues().size() > 0) {
						// multiple result values given -> we're going to put them into an array of the same type
						// as the fixture result
						tempConvertedResult = Array.newInstance(tempConversionTargetType, anExpectedResult
								.getMoreValues().size() + 1);
						for (int i = 0; i < Array.getLength(tempConvertedResult); i++) {
							ValueOrEnumValueOrOperation tempSingleExpectedResult = (i == 0 ? anExpectedResult
									.getValue() : anExpectedResult.getMoreValues().get(i - 1));
							Array.set(tempConvertedResult, i, valueConverter.convertEncapsulatedValueToParamType(
									tempConversionTargetType, tempSingleExpectedResult,
									UnresolvableVariableHandling.RESOLVE_TO_NULL_VALUE));
						}
					} else {
						tempConvertedResult = valueConverter.convertEncapsulatedValueToParamType(
								tempConversionTargetType, anExpectedResult.getValue(),
								UnresolvableVariableHandling.RESOLVE_TO_NULL_VALUE);
					}

					return aFixtureInstance.performCustomComparation(tempConvertedResult, aFixtureResult,
							aFixtureMethod.getMethod().getSimpleName());
				} else {
					// Standard comparation compares each value for itself in case of arrays
					if (anExpectedResult.getMoreValues().size() > 0) {
						// multiple result values were given -> fixture result must be an array of same size
						if (!(aFixtureResult.getClass().isArray() && Array.getLength(aFixtureResult) == anExpectedResult
								.getMoreValues().size() + 1)) {
							return false;
						}
						// now compare all values
						for (int i = 0; i < Array.getLength(aFixtureResult); i++) {
							Object tempSingleFixtureResult = Array.get(aFixtureResult, i);
							ValueOrEnumValueOrOperation tempSingleExpectedResult = (i == 0 ? anExpectedResult
									.getValue() : anExpectedResult.getMoreValues().get(i - 1));
							if (tempSingleFixtureResult == null) {
								// The fixture returned a null, we need to expect a null
								if (!(tempSingleExpectedResult instanceof NullValue)) {
									return false;
								}
							} else {
								Object tempConvertedExpectedResult = valueConverter
										.convertEncapsulatedValueToParamType(tempSingleFixtureResult.getClass(),
												tempSingleExpectedResult,
												UnresolvableVariableHandling.RESOLVE_TO_NULL_VALUE);

								if (!performEqualityCheck(tempSingleFixtureResult, tempConvertedExpectedResult,
										tempSingleExpectedResult)) {
									return false;
								}
							}
						}
						return true;
					} else {
						// If we arrive here, the expected result is a simple, single value. But the fixture might still
						// return an array
						Object tempSingleFixtureResult = aFixtureResult;
						// if the expected type is an array, we don't want to convert to that array, but to the
						// component type, of course
						Class<?> tempConversionTargetType = tempSingleFixtureResult.getClass().isArray() ? tempSingleFixtureResult
								.getClass().getComponentType() : tempSingleFixtureResult.getClass();

						ValueOrEnumValueOrOperation tempSingleExpectedResult = anExpectedResult.getValue();
						Object tempConvertedExpectedResult = valueConverter.convertEncapsulatedValueToParamType(
								tempConversionTargetType, tempSingleExpectedResult,
								UnresolvableVariableHandling.RESOLVE_TO_NULL_VALUE);

						// Even though we assume that there's a single expected result after this point, the converted
						// result might still be an array (because an operation has returned an array, for example).
						// Therefore we need to allow for both, fixture result and expected result, to be arrays.
						if (aFixtureResult.getClass().isArray()) {
							if (tempConvertedExpectedResult == null) {
								// the fixture may still be returning an array that has to be unpacked
								if (Array.getLength(aFixtureResult) != 1) {
									return false;
								}
								tempSingleFixtureResult = Array.get(aFixtureResult, 0);
								return (tempSingleFixtureResult == null);
							} else {
								if (!tempConvertedExpectedResult.getClass().isArray()) {
									// the fixture may be returning an array that has to be unpacked
									if (Array.getLength(aFixtureResult) != 1) {
										return false;
									}
									tempSingleFixtureResult = Array.get(aFixtureResult, 0);
								} else {
									if (Array.getLength(aFixtureResult) != Array.getLength(tempConvertedExpectedResult)) {
										return false;
									}
									// both are converted arrays -> compare all values!
									for (int i = 0; i < Array.getLength(aFixtureResult); i++) {
										if (!performEqualityCheck(Array.get(tempConvertedExpectedResult, i),
												Array.get(aFixtureResult, i), tempSingleExpectedResult)) {
											return false;
										}
									}
									return true;
								}
							}
						} else {
							// This is the super-simple case where we basically have only one value to compare
							if (tempConvertedExpectedResult == null) {
								// ...but even that can be null, and we need to handle this case separately in order to
								// not get into NPEs
								return (tempSingleFixtureResult instanceof NullValue);
							} else {
								if (tempConvertedExpectedResult.getClass().isArray()) {
									// the converted result may still be an array
									if (Array.getLength(tempConvertedExpectedResult) != 1) {
										return false;
									}
									tempConvertedExpectedResult = Array.get(tempConvertedExpectedResult, 0);
								}
							}
						}
						return performEqualityCheck(tempConvertedExpectedResult, tempSingleFixtureResult,
								tempSingleExpectedResult);
					}
				}
			}
		} else {
			if (aFixtureInstance.isCustomComparatorFixture()) {
				return aFixtureInstance.performCustomComparation(null, aFixtureResult, aFixtureMethod.getMethod()
						.getSimpleName());
			} else {
				if (aFixtureResult instanceof Boolean) {
					return (Boolean) aFixtureResult;
				} else {
					throw new IllegalArgumentException(
							"If no expected test result is given and the fixture is not a CustomComparatorFixture, "
									+ "the test fixture must return a boolean result!");
				}
			}
		}
	}

	/**
	 * Perform the actual equality check between a real result returned from a fixture and a converted result gathered
	 * from the test scripts. A few special cases are handled here, but if no special case applies, this just runs a
	 * standard equals() comparison.
	 * 
	 * @param aConvertedResult
	 *            the actual result
	 * @param aConvertedExpectedResult
	 *            the expected result from the scripts, converted to the same type as the actual result
	 * @param aRawExpectedResult
	 *            the raw expected result object from the scripts
	 * @return true if equal, false otherwise
	 */
	protected boolean performEqualityCheck(Object aConvertedResult, Object aConvertedExpectedResult,
			ValueOrEnumValueOrOperation aRawExpectedResult) {
		if (aConvertedExpectedResult == null) {
			return (aConvertedResult == null);
		} else {
			if (aConvertedResult instanceof Date && aConvertedExpectedResult instanceof Date) {
				if (aRawExpectedResult instanceof DateValue) {
					// compare only the date part
					return DateUtil.stripTimeFromDate((Date) aConvertedExpectedResult).equals(
							DateUtil.stripTimeFromDate((Date) aConvertedResult));
				} else if (aRawExpectedResult instanceof TimeValue) {
					// compare only the time part
					return DateUtil.stripDateFromTime((Date) aConvertedExpectedResult).equals(
							DateUtil.stripDateFromTime((Date) aConvertedResult));
				}
			}

			// If no special cases apply, perform standard equals comparison
			return aConvertedExpectedResult.equals(aConvertedResult);
		}
	}

	/**
	 * Executes a call.
	 * 
	 * @param aCall
	 *            the call to execute
	 * @return the results (multiple if the call has an execution multiplier)
	 */
	protected List<CallResult> executeCall(Call aCall) {
		int tempCount = 1;
		if (aCall.getMultiplier() != null && aCall.getMultiplier().getCount() != null) {
			tempCount = aCall.getMultiplier().getCount().intValue();
		}

		List<CallResult> tempResults = new ArrayList<CallResult>();
		for (int i = 0; i < tempCount; i++) {
			tempResults.add(executeCallSingle(aCall));
		}

		return tempResults;
	}

	/**
	 * Executes a call (a single time, doesn't honor the multiplier).
	 * 
	 * @param aCall
	 *            the call to execute
	 * @return the result
	 */
	protected CallResult executeCallSingle(Call aCall) {
		TestModel.ensureModelPartConsistency(aCall);

		if (currentCallback != null) {
			currentCallback.onCallStart(aCall);
		}

		List<UpdatedVariable> tempUpdatedVariables = new ArrayList<UpdatedVariable>();
		if (aCall.getResults() != null && aCall.getResults().size() > 0) {
			for (NamedCallResult tempNamedResult : aCall.getResults()) {
				String tempResultName = IntegrityDSLUtil.getExpectedResultNameStringFromTestResultName(tempNamedResult
						.getName());
				tempUpdatedVariables.add(new UpdatedVariable(tempNamedResult.getTarget().getName(), tempResultName,
						null));
			}
		} else if (aCall.getResult() != null) {
			tempUpdatedVariables.add(new UpdatedVariable(aCall.getResult().getName(), null, null));
		}

		CallResult tempReturn;
		if (!shouldExecuteFixtures()) {
			tempReturn = new de.gebit.integrity.runner.results.call.UndeterminedResult(tempUpdatedVariables);
		} else {
			pauseIfRequiredByRemoteClient(false);

			long tempStart = System.nanoTime();
			FixtureWrapper<?> tempFixtureInstance = null;
			try {
				Map<String, Object> tempParameters = parameterResolver.createParameterMap(aCall, true, false);

				tempFixtureInstance = wrapperFactory.newFixtureWrapper(aCall.getDefinition().getFixtureMethod()
						.getType());

				tempStart = System.nanoTime();
				Object tempResult = executeFixtureMethod(tempFixtureInstance, aCall.getDefinition().getFixtureMethod(),
						tempParameters);
				long tempDuration = System.nanoTime() - tempStart;

				if (aCall.getResults() != null && aCall.getResults().size() > 0) {
					Map<String, Object> tempFixtureResultMap = ParameterUtil
							.getValuesFromNamedResultContainer(tempResult);
					for (UpdatedVariable tempUpdatedVariable : tempUpdatedVariables) {
						Object tempSingleFixtureResult = tempFixtureResultMap.get(tempUpdatedVariable
								.getParameterName());
						tempUpdatedVariable.setValue(tempSingleFixtureResult);
						setVariableValue(tempUpdatedVariable.getTargetVariable().getName(), tempSingleFixtureResult,
								true);
					}
					tempReturn = new de.gebit.integrity.runner.results.call.SuccessResult(tempUpdatedVariables,
							tempDuration);
				} else if (aCall.getResult() != null) {
					tempUpdatedVariables.get(0).setValue(tempResult);
					tempReturn = new de.gebit.integrity.runner.results.call.SuccessResult(tempUpdatedVariables,
							tempDuration);
					setVariableValue(aCall.getResult().getName(), tempResult, true);
				} else {
					tempReturn = new de.gebit.integrity.runner.results.call.SuccessResult(tempUpdatedVariables,
							tempDuration);
				}
				// SUPPRESS CHECKSTYLE IllegalCatch
			} catch (Throwable exc) {
				tempReturn = new de.gebit.integrity.runner.results.call.ExceptionResult(exc, tempUpdatedVariables,
						System.nanoTime() - tempStart);
			} finally {
				if (tempFixtureInstance != null) {
					tempFixtureInstance.release();
				}
			}
		}

		if (currentCallback != null) {
			currentCallback.onCallFinish(aCall, tempReturn);
		}

		return tempReturn;
	}

	/**
	 * Test execution is splitted in phases.
	 * 
	 * 
	 * @author Rene Schneider
	 * 
	 */
	protected enum Phase {

		/**
		 * The dry run is used to build up the {@link SetList}. In this phase, the whole model is walked, but no forks
		 * are being started and no test/call fixtures are being executed.
		 */
		DRY_RUN,

		/**
		 * The actual test run.
		 */
		TEST_RUN;

	}

	/**
	 * Determines whether we should actually execute fixture method calls at the moment.
	 * 
	 * @return true if calls should be executed, false otherwise
	 */
	protected boolean shouldExecuteFixtures() {
		if (currentPhase == Phase.DRY_RUN) {
			return false;
		} else {
			if (MY_FORK_NAME != null) {
				return (forkInExecution != null && forkInExecution.getName().equals(MY_FORK_NAME));
			} else {
				return (forkInExecution == null);
			}
		}
	}

	/**
	 * Pauses execution (blocks the method call) if the remoting client requested to do so via execution control or
	 * breakpoints.
	 * 
	 * @param aForkSyncFlag
	 *            true if we are pausing for fork synchronization reasons
	 */
	protected void pauseIfRequiredByRemoteClient(boolean aForkSyncFlag) {
		if (remotingServer == null) {
			return;
		}

		Integer tempLastTestOrCallEntryRef = setList.getLastCreatedEntryId(SetListEntryTypes.CALL,
				SetListEntryTypes.TEST);

		if (tempLastTestOrCallEntryRef != null && breakpoints.contains(tempLastTestOrCallEntryRef)) {
			removeBreakpoint(tempLastTestOrCallEntryRef);
		} else if (shallWaitBeforeNextStep) {
			shallWaitBeforeNextStep = false;
		} else {
			// do not wait
			return;
		}

		try {
			waitForContinue(aForkSyncFlag);
		} catch (InterruptedException exc) {
			// just continue
		}
	}

	/**
	 * Pause execution (blocks method call) until continuation is triggered by remoting.
	 * 
	 * @param aForkSyncFlag
	 *            true if we are pausing for fork synchronization reasons
	 * @throws InterruptedException
	 */
	protected void waitForContinue(boolean aForkSyncFlag) throws InterruptedException {
		if (aForkSyncFlag) {
			remotingServer.updateExecutionState(ExecutionStates.PAUSED_SYNC);
		} else {
			remotingServer.updateExecutionState(ExecutionStates.PAUSED);
		}
		executionWaiter.acquire();
		executionWaiter.drainPermits();
		remotingServer.updateExecutionState(ExecutionStates.RUNNING);
	}

	/**
	 * Removes a specific breakpoint.
	 * 
	 * @param anEntryReference
	 *            the setlist entry reference at which the breakpoint is set
	 */
	protected void removeBreakpoint(int anEntryReference) {
		// forward to forks
		for (Entry<ForkDefinition, Fork> tempForkEntry : forkMap.entrySet()) {
			tempForkEntry.getValue().getClient().createBreakpoint(anEntryReference);
		}

		// then perform for ourself
		if (breakpoints.remove(anEntryReference)) {
			remotingServer.confirmBreakpointRemoval(anEntryReference);
		}
	}

	/**
	 * Creates a new breapoint.
	 * 
	 * @param anEntryReference
	 *            the setlist entry reference at which the breakpoint will be created
	 */
	protected void createBreakpoint(int anEntryReference) {
		// forward to forks
		for (Entry<ForkDefinition, Fork> tempForkEntry : forkMap.entrySet()) {
			tempForkEntry.getValue().getClient().createBreakpoint(anEntryReference);
		}

		// then perform for ourself
		if (breakpoints.add(anEntryReference)) {
			remotingServer.confirmBreakpointCreation(anEntryReference);
		}
	}

	/**
	 * The listener used to respond on actions triggered by remoting clients.
	 * 
	 * 
	 * @author Rene Schneider
	 * 
	 */
	protected class RemotingListener implements IntegrityRemotingServerListener {

		@Override
		public void onConnectionSuccessful(IntegrityRemotingVersionMessage aRemoteVersion, Endpoint anEndpoint) {
			// nothing to do
		}

		@Override
		public void onSetListRequest(Endpoint anEndpoint) {
			synchronized (setListWaiter) {
				while (setList == null) {
					try {
						setListWaiter.wait();
					} catch (InterruptedException exc) {
						// don't care
					}
				}

				anEndpoint.sendMessage(new SetListBaselineMessage(setList));
				for (Integer tempBreakpoint : breakpoints) {
					anEndpoint.sendMessage(new BreakpointUpdateMessage(BreakpointActions.CREATE, tempBreakpoint));
				}
			}
		}

		@Override
		public void onRunCommand(Endpoint anEndpoint) {
			if (!isFork() && forkInExecution != null) {
				// if we're the master and a fork is active, we're waiting for a fork, thus this command
				// is meant for the fork
				Fork tempFork = forkMap.get(forkInExecution);
				tempFork.getClient().controlExecution(ExecutionCommands.RUN);
			} else {
				executionWaiter.release();
			}
		}

		@Override
		public void onConnectionLost(Endpoint anEndpoint) {
			// I don't care
		}

		@Override
		public void onPauseCommand(Endpoint anEndpoint) {
			if (!isFork() && forkInExecution != null) {
				// if we're the master and a fork is active, we're waiting for a fork, thus this command
				// is meant for the fork
				forkMap.get(forkInExecution).getClient().controlExecution(ExecutionCommands.PAUSE);
			} else {
				shallWaitBeforeNextStep = true;
			}
		}

		@Override
		public void onStepIntoCommand(Endpoint anEndpoint) {
			if (!isFork() && forkInExecution != null) {
				// if we're the master and a fork is active, we're waiting for a fork, thus this command
				// is meant for the fork
				Fork tempFork = forkMap.get(forkInExecution);
				tempFork.getClient().controlExecution(ExecutionCommands.STEP_INTO);
			} else {
				shallWaitBeforeNextStep = true;
				executionWaiter.release();
			}
		}

		@Override
		public void onCreateBreakpoint(int anEntryReference, Endpoint anEndpoint) {
			createBreakpoint(anEntryReference);
		}

		@Override
		public void onRemoveBreakpoint(int anEntryReference, Endpoint anEndpoint) {
			removeBreakpoint(anEntryReference);
		}

		@Override
		public void onVariableUpdateRetrieval(String aVariableName, Serializable aValue) {
			setVariableValue(aVariableName, aValue, false);
		}
	}

	public static boolean isFork() {
		return MY_FORK_NAME != null;
	}

	/**
	 * Creates a new fork instance. This starts up the forked JVM and connects to it for remote control.
	 * 
	 * @param aSuiteCall
	 *            the suite call that shall be run on the fork
	 * @return the new fork
	 * @throws ForkException
	 *             if any problem arises during forking
	 */
	@SuppressWarnings("unchecked")
	protected Fork createFork(Suite aSuiteCall) throws ForkException {
		ForkDefinition tempForkDef = aSuiteCall.getFork();
		Class<? extends Forker> tempForkerClass = DefaultForker.class;
		if (tempForkDef.getForkerClass() != null) {
			try {
				tempForkerClass = (Class<? extends Forker>) getClassForJvmType(tempForkDef.getForkerClass().getType());
			} catch (ClassCastException exc) {
				throw new ForkException("Could not create fork '" + tempForkDef.getName()
						+ "': forker class not usable.", exc);
			} catch (ClassNotFoundException exc) {
				throw new ForkException("Could not create fork '" + tempForkDef.getName()
						+ "': forker class not found.", exc);
			}
		}

		if (tempForkerClass.getConstructors().length != 1) {
			throw new ForkException("Found illegal number of constructors in forker class (must be exactly one!)");
		}

		// Forker can be parameterized
		Constructor<? extends Forker> tempConstructor = (Constructor<? extends Forker>) tempForkerClass
				.getConstructors()[0];
		Object[] tempParameters = new Object[tempConstructor.getParameterTypes().length];
		try {
			for (int i = 0; i < tempConstructor.getParameterTypes().length; i++) {
				for (Annotation tempAnnotation : tempConstructor.getParameterAnnotations()[i]) {
					if (ForkerParameter.class.isAssignableFrom(tempAnnotation.annotationType())) {
						String tempName = ((ForkerParameter) tempAnnotation).name();
						if (tempName != null) {
							for (ForkParameter tempParameter : tempForkDef.getParameters()) {
								String tempParamName = IntegrityDSLUtil
										.getParamNameStringFromParameterName(tempParameter.getName());
								if (tempName.equals(tempParamName)) {
									Class<?> tempTargetType = tempConstructor.getParameterTypes()[i];
									tempParameters[i] = valueConverter.convertEncapsulatedValueToParamType(
											tempTargetType, tempParameter.getValue(),
											UnresolvableVariableHandling.EXCEPTION);
									break;
								}
							}
						}
					}
				}
			}
		} catch (InstantiationException exc) {
			throw new ForkException("Could not create fork '" + tempForkDef.getName()
					+ "': failed to resolve forker parameters.", exc);
		} catch (UnresolvableVariableException exc) {
			throw new ForkException("Could not create fork '" + tempForkDef.getName()
					+ "': failed to resolve forker parameters.", exc);
		} catch (ClassNotFoundException exc) {
			throw new ForkException("Could not create fork '" + tempForkDef.getName()
					+ "': failed to resolve forker parameters.", exc);
		} catch (UnexecutableException exc) {
			throw new ForkException("Could not create fork '" + tempForkDef.getName()
					+ "': failed to resolve forker parameters.", exc);
		}

		Forker tempForker = null;
		try {
			tempForker = tempConstructor.newInstance(tempParameters);
		} catch (InstantiationException exc) {
			throw new ForkException("Could not create fork '" + tempForkDef.getName()
					+ "': forker class not instantiable.", exc);
		} catch (IllegalAccessException exc) {
			throw new ForkException("Could not create fork '" + tempForkDef.getName()
					+ "': forker class not instantiable.", exc);
		} catch (IllegalArgumentException exc) {
			throw new ForkException("Could not create fork '" + tempForkDef.getName()
					+ "': forker class not instantiable.", exc);
		} catch (InvocationTargetException exc) {
			throw new ForkException("Could not create fork '" + tempForkDef.getName()
					+ "': forker class not instantiable.", exc);
		}

		Fork tempFork = new Fork(aSuiteCall.getFork(), tempForker, commandLineArguments,
				remotingServer != null ? remotingServer.getPort() : IntegrityRemotingConstants.DEFAULT_PORT,
				currentCallback, setList, remotingServer, new ForkCallback() {

					@Override
					public void onSetVariableValue(String aQualifiedVariableName, Object aValue,
							boolean aDoSendUpdateFlag) {
						setVariableValue(aQualifiedVariableName, aValue, aDoSendUpdateFlag);
					}

					@Override
					public void onForkExit(Fork aFork) {
						for (Entry<ForkDefinition, Fork> tempEntry : forkMap.entrySet()) {
							if (tempEntry.getValue() == aFork) {
								forkMap.remove(tempEntry.getKey());
								diedForks.add(tempEntry.getKey());
								return;
							}
						}
					}
				});

		long tempTimeout = System.getProperty(FORK_CONNECTION_TIMEOUT_PROPERTY) != null ? Integer.parseInt(System
				.getProperty(FORK_CONNECTION_TIMEOUT_PROPERTY)) : FORK_CONNECTION_TIMEOUT_DEFAULT;

		long tempStartTime = System.nanoTime();
		while (System.nanoTime() - tempStartTime < (tempTimeout * 1000 * 1000000)) {
			try {
				if (!tempFork.isAlive() || tempFork.connect(FORK_SINGLE_CONNECT_TIMEOUT)) {
					break;
				}
			} catch (IOException exc) {
				// this is expected -> will simply retry
			}

			try {
				Thread.sleep(FORK_CONNECT_DELAY);
			} catch (InterruptedException exc) {
				// ignored
			}
		}

		if (tempFork.isAlive() && tempFork.isConnected()) {
			// initially, we'll send a snapshot of all current non-encapsulated variable values to the fork
			// (encapsulated values are predefined in the test script and thus already known to the fork)
			for (Entry<VariableEntity, Object> tempEntry : variableManager.getAllEntries()) {
				if (!(tempEntry.getValue() instanceof ValueOrEnumValueOrOperation)) {
					tempFork.updateVariableValue(tempEntry.getKey(), tempEntry.getValue());
				}
			}

			// and the fork will also need all current breakpoints
			for (Integer tempBreakpoint : breakpoints) {
				tempFork.getClient().createBreakpoint(tempBreakpoint);
			}

			// and now we'll wait until the fork is paused
			while (tempFork.isAlive() && tempFork.isConnected()
					&& tempFork.getExecutionState() != ExecutionStates.PAUSED_SYNC) {
				try {
					Thread.sleep(FORK_PAUSE_WAIT_INTERVAL);
				} catch (InterruptedException exc) {
					// nothing to do here
				}
			}

			// a last sanity check
			if (tempFork.isAlive() && tempFork.isConnected()) {
				return tempFork;
			}
		}

		tempFork.kill();
		throw new ForkException("Could not successfully establish a control connection to the fork.");
	}
}
