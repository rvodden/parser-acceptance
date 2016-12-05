package com.vodden.math.parser.acceptance;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.startup.Tomcat;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Injector;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.annotations.Managed;

import cucumber.api.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.vodden.math.parser.acceptance.steps")
public class ParserTest {

	@Managed
	private WebDriver webDriver;
	private static Injector injector;

	final static Logger LOGGER = LoggerFactory.getLogger(CucumberWithSerenity.class);

	private static Tomcat tomcat;
	private static final String workingDir = System.getProperty("java.io.tmpdir") + "/tomcattmp";

	@BeforeClass
	public static void setupTestEnvironment() throws LifecycleException {
		setupDependencyInjection();
		setupTomcat();
	}
	
	private static void setupDependencyInjection() {
		 
	}
	
	@Before
	public void setup(){
		injector.injectMembers(this);
	}
	
	private static void setupTomcat() throws LifecycleException {
		tomcat = new Tomcat();
		tomcat.setPort(8080);
		LOGGER.info("Using " + workingDir + "as working directory.");
		// clear out the working directory
		deleteDir(workingDir);
		tomcat.setBaseDir(workingDir);
		tomcat.getHost().setAppBase(workingDir);
		tomcat.getHost().setAutoDeploy(true);
		tomcat.getHost().setDeployOnStartup(true);

		try {
			tomcat.start();
		} catch (LifecycleException e) {
			LOGGER.error("Tomcat could not be started.", e);
		}

		LOGGER.info("Tomcat started on " + tomcat.getHost());

		deployWarFile("/parser-ui", getUIWarFilePath()     );
		deployWarFile("/parser",    getParserWarFilePath() );
	}
	
	private static void deployWarFile(String context, String warFile) {
		Context uiAppContext = tomcat.addWebapp(tomcat.getHost(), context, warFile);
		LOGGER.info("Deployed " + uiAppContext.getBaseName() + " as " + uiAppContext.getBaseName());
	}

	@AfterClass
	public static final void teardown() throws Throwable {
		if (tomcat.getServer() != null && tomcat.getServer().getState() != LifecycleState.DESTROYED) {
			if (tomcat.getServer().getState() != LifecycleState.STOPPED) {
				tomcat.stop();
			}
			tomcat.destroy();
		}
	}

	private static String getUIWarFilePath() {
		return getWarFilePath("parser-ui.warfile.location");
	}
	
	private static String getParserWarFilePath() {
		return getWarFilePath("parser.warfile.location");
	}
	
	private static String getWarFilePath(String property) {
		String loc = System.getProperty(property);
		LOGGER.info(String.format("Found %s at location %s", property, loc));
		return loc;
	}
	
	public static boolean deleteDir(String dirPath) {
		File dir = new File(dirPath);
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i=0; i<children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]).getPath());
	            if (!success) {
	                return false;
	            }
	        }
	    }
	    return dir.delete();
	}
	


}