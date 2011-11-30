package de.integrity.eclipse.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.integrity.remoting.entities.setlist.SetList;
import de.integrity.remoting.entities.setlist.SetListEntry;
import de.integrity.remoting.entities.setlist.SetListEntryResultStates;

public class TestTreeContentDrawer {

	private SetList setList;

	private Color nullColor;
	private Color suiteSuccessColor;
	private Color suiteFailureColor;
	private Color suiteExceptionColor;
	private Color testSuccessColor;
	private Color testFailureColor;
	private Color testExceptionColor;
	private Color callExceptionColor;
	private Color callSuccessColor;

	private Listener measureListener;
	private Listener eraseListener;

	private int INDENT_PIXELS = 19;

	private int INDENT_BASE = 42;

	private int GRADIENT_WIDTH = 32;

	private int GRADIENT_OFFSET = -16;

	public TestTreeContentDrawer(SetList aSetList, Display aDisplay) {
		setList = aSetList;
		nullColor = new Color(aDisplay, 255, 255, 255);
		suiteSuccessColor = new Color(aDisplay, 156, 255, 189);
		suiteFailureColor = new Color(aDisplay, 255, 130, 130);
		suiteExceptionColor = new Color(aDisplay, 255, 248, 136);
		testSuccessColor = suiteSuccessColor;
		testFailureColor = suiteFailureColor;
		testExceptionColor = suiteExceptionColor;
		callExceptionColor = suiteExceptionColor;
		callSuccessColor = new Color(aDisplay, 205, 255, 222);
	}

	public void dispose(Tree aTree) {
		nullColor.dispose();
		suiteSuccessColor.dispose();
		suiteFailureColor.dispose();
		suiteExceptionColor.dispose();
		testSuccessColor.dispose();
		testFailureColor.dispose();
		testExceptionColor.dispose();
		callSuccessColor.dispose();
		callExceptionColor.dispose();
		aTree.removeListener(SWT.MeasureItem, measureListener);
		aTree.removeListener(SWT.EraseItem, eraseListener);
	}

	public void attachToTree(final Tree aTree) {
		measureListener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				TreeItem tempItem = (TreeItem) event.item;
				SetListEntry tempEntry = (SetListEntry) tempItem.getData();

				int tempMinWidth = aTree.getClientArea().width - event.x;
				if (event.width < tempMinWidth) {
					event.width = tempMinWidth;
				}
			}
		};
		aTree.addListener(SWT.MeasureItem, measureListener);

		eraseListener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				TreeItem tempItem = (TreeItem) event.item;
				SetListEntry tempEntry = (SetListEntry) tempItem.getData();
				SetListEntryResultStates tempResultState = setList.getResultStateForExecutableEntry(tempEntry);

				if (tempResultState != null && tempResultState != SetListEntryResultStates.UNKNOWN) {
					int tempInset = getTreeItemIndentation(tempItem);
					Color tempOldForeground = event.gc.getForeground();
					Color tempOldBackground = event.gc.getBackground();

					event.gc.setForeground(nullColor);
					Color tempBackground = tempOldBackground;

					switch (tempEntry.getType()) {
					case SUITE:
						switch (tempResultState) {
						case EXCEPTION:
							tempBackground = suiteExceptionColor;
							break;
						case FAILED:
							tempBackground = suiteFailureColor;
							break;
						case SUCCESSFUL:
							tempBackground = suiteSuccessColor;
							break;
						}
						break;
					case CALL:
						switch (tempResultState) {
						case SUCCESSFUL:
							tempBackground = callSuccessColor;
							break;
						case EXCEPTION:
							tempBackground = callExceptionColor;
							break;
						}
						break;
					case TEST:
						switch (tempResultState) {
						case SUCCESSFUL:
							tempBackground = testSuccessColor;
							break;
						case EXCEPTION:
							tempBackground = testExceptionColor;
							break;
						case FAILED:
							tempBackground = testFailureColor;
							break;
						}
						break;
					}

					event.gc.setBackground(tempBackground);

					event.gc.fillGradientRectangle(event.x + tempInset + GRADIENT_OFFSET, event.y, GRADIENT_WIDTH,
							event.height, false);
					event.gc.fillRectangle(event.x + tempInset + GRADIENT_WIDTH + GRADIENT_OFFSET, event.y, event.width
							- (tempInset + GRADIENT_WIDTH + GRADIENT_OFFSET), event.height);

					event.gc.setForeground(tempOldForeground);
					event.gc.setBackground(tempOldBackground);

					event.detail &= ~SWT.BACKGROUND;
					event.detail &= ~SWT.HOT;
				}
			}
		};
		aTree.addListener(SWT.EraseItem, eraseListener);
	}

	private int getTreeItemIndentation(TreeItem anItem) {
		int tempIndent = 0;

		TreeItem tempParent = anItem.getParentItem();
		while (tempParent != null) {
			tempIndent++;
			tempParent = tempParent.getParentItem();
		}

		return tempIndent * INDENT_PIXELS + INDENT_BASE;
	}
}
