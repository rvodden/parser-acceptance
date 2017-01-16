package com.vodden.math.parser.acceptance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.inject.Singleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.inject.AbstractModule;
import com.vodden.math.parser.acceptance.pages.Home;
import com.vodden.math.parser.acceptance.pages.HomeImpl;

public class ParserModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
			loadSystemProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bind(Home.class).to(HomeImpl.class);
		bind(WebDriver.class).to(ChromeDriver.class).in(Singleton.class);
	}

	
	private static void loadSystemProperties() throws IOException {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("parser-acceptance.properties");
        Properties properties = new Properties(System.getProperties());
        properties.load(propertiesFile);

        // set the system properties
        System.setProperties(properties);
        // display new properties
        System.getProperties().list(System.out);
		
	}
}
