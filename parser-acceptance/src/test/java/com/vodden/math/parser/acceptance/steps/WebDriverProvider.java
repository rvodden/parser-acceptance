package com.vodden.math.parser.acceptance.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverProvider {
	
	private static WebDriver webDriver = null;
	
	public static WebDriver getWebdriver() {
		if(webDriver == null) {
			webDriver = new ChromeDriver();
		}
		
		return webDriver;
	}
	
	public static void closeWebDriver() {
		if(webDriver != null) {
			webDriver.quit();
		}
	}
}
