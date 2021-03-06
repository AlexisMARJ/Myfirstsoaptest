package org.eql.Myfirstsoaptest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import com.eviware.soapui.SoapUI;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.settings.HttpSettings;
import com.eviware.soapui.support.SoapUIException;

public class SoapTest {
    Logger LOGGER = LoggerFactory.getLogger(SoapTest.class);  // debut
    

	@Test
	public void JunitTest1()
	        throws XmlException, IOException, SoapUIException {
		SoapUI.getSettings().setBoolean(HttpSettings.RESPONSE_COMPRESSION, false);
	    // Créer une nouvelle instance de WsdlProject en spécifiant le chemin absolu du projet SoapUI
	    WsdlProject project = new WsdlProject(
	            "src/test/resources/NOAA.xml");
	    // Récupère tous les TestSuites inclus dans le projet SoapUI
	    List<TestSuite> testSuiteList = project.getTestSuiteList();
	    // Itération sur tous les TestSuites du projet
	    for (TestSuite ts : testSuiteList) {
	        LOGGER.info("******Running " + ts.getName() + "***********");
	        // Récupère tous les TestCases d'une TestSuite
	        List<TestCase> testCaseList = ts.getTestCaseList();
	        // Itération sur tous les TestCases d'un TestSuite particulier
	        for (TestCase testcase : testCaseList) {
	        	LOGGER.info("******Running " + testcase.getName() + "***********");
	            // Exécute le TestCase spécifié
	            TestRunner testCaseRunner = testcase.run(new PropertiesMap(), false);
	            // Vérifie si le TestCase s'est terminé correctement
	            // ou s'il a échoué à cause d'un échec d'assertion
	            assertEquals(TestRunner.Status.FINISHED, testCaseRunner.getStatus());
	        }
	    }
	}

	
}
