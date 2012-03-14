package de.gebit.integrity.tests.junit;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import de.gebit.integrity.runner.TestModel;
import de.gebit.integrity.runner.TestRunner;
import de.gebit.integrity.runner.callbacks.CompoundTestRunnerCallback;
import de.gebit.integrity.runner.callbacks.TestRunnerCallback;
import de.gebit.integrity.runner.callbacks.console.ConsoleTestCallback;
import de.gebit.integrity.runner.callbacks.xml.XmlWriterTestCallback;
import de.gebit.integrity.runner.exceptions.ModelLoadException;
import de.gebit.integrity.runner.providers.FilesystemTestResourceProvider;
import de.gebit.integrity.runner.providers.TestResourceProvider;

public abstract class IntegrityJUnitTest {

	private static final String REFERENCE_RESULT_DIRECTORY = "results/";

	protected Document executeIntegritySuite(String aSuiteName) throws ModelLoadException, IOException, JDOMException {
		List<File> tempFileList = new ArrayList<File>();
		tempFileList.add(new File("integrity"));

		TestResourceProvider tempResourceProvider = new FilesystemTestResourceProvider(tempFileList, true);
		TestModel tempModel = TestModel.loadTestModel(tempResourceProvider, false);

		File tempXmlFile = File.createTempFile("integrityJUnit", ".xml");

		TestRunnerCallback tempCallback = new CompoundTestRunnerCallback(new ConsoleTestCallback(getClass()
				.getClassLoader()), new XmlWriterTestCallback(getClass().getClassLoader(), tempXmlFile,
				"Integrity JUnit Testing", false));

		TestRunner tempRunner = new TestRunner(tempModel, tempCallback, null, null);
		tempRunner.run(tempModel.getSuiteByName(aSuiteName), false);

		SAXBuilder tempBuilder = new SAXBuilder(false);
		return tempBuilder.build(tempXmlFile);
	}

	protected void assertDocumentMatchesReference(Document aDoc) throws JDOMException, IOException {
		assertNotNull(aDoc);

		String tempRootSuiteName = aDoc.getRootElement().getChild("suite").getAttribute("name").getValue();

		SAXBuilder tempBuilder = new SAXBuilder(false);
		Document tempRef = tempBuilder.build(new File(REFERENCE_RESULT_DIRECTORY + tempRootSuiteName + ".xml"));

		removeDurationsAndTimes(aDoc.getRootElement());
		removeDurationsAndTimes(tempRef.getRootElement());

		ByteArrayOutputStream tempDocStream = new ByteArrayOutputStream();
		ByteArrayOutputStream tempRefStream = new ByteArrayOutputStream();

		XMLOutputter tempSerializer = new XMLOutputter(Format.getCompactFormat());
		tempSerializer.output(aDoc, tempDocStream);
		tempSerializer.output(tempRef, tempRefStream);

		try {
			assertTrue(Arrays.equals(tempDocStream.toByteArray(), tempRefStream.toByteArray()));
		} catch (AssertionFailedError exc) {
			System.out.println("--- Suite " + tempRootSuiteName + " failed comparison to reference data!");
			System.out.print("Reference: ");
			System.out.println(new String(tempRefStream.toByteArray(), "UTF-8"));
			System.out.print("Actual: ");
			System.out.println(new String(tempDocStream.toByteArray(), "UTF-8"));
			throw exc;
		}
	}

	private void removeDurationsAndTimes(Element anElement) throws JDOMException {
		clearAttribute(anElement, "duration");
		clearAttribute(anElement, "timestamp");
		clearAttribute(anElement, "isotimestamp");

		for (Object tempChild : anElement.getChildren()) {
			if (tempChild instanceof Element) {
				removeDurationsAndTimes((Element) tempChild);
			}
		}
	}

	private void clearAttribute(Element anElement, String anAttributeName) {
		Attribute tempAttr = anElement.getAttribute(anAttributeName);
		if (tempAttr != null) {
			tempAttr.setValue("");
		}
	}

}
