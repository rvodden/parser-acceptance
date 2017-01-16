package com.vodden.math.parser.acceptance.pages;

import javax.inject.Inject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeImpl implements Home {
	
	private static final String DEFAULT_URL = "http://localhost:8080/parser-ui/faces/parser.xhtml";
	
	private String url;
	private WebDriver webDriver;

	@FindBy(id="form:expression")
	WebElement formExpression;
	
	@FindBy(id="form:submit")
	WebElement submitButton;
	
	@FindBy(id="form:result")
	WebElement resultField;
	
	public HomeImpl(WebDriver webDriver, String url) {
		this.url = url;
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}
	
	@Inject
	public HomeImpl(WebDriver webDriver) {
		this(webDriver,DEFAULT_URL);
	}
	
	/* (non-Javadoc)
	 * @see com.vodden.math.parser.acceptance.pages.Home#open()
	 */
	@Override
	public void open() {
		webDriver.get(url);
	}

    /* (non-Javadoc)
	 * @see com.vodden.math.parseracceptance.pages.Home#calculate(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.vodden.math.parser.acceptance.pages.Home#calculate(java.lang.String)
	 */
	@Override
	public void calculate(String expression) {
    	formExpression.clear();
    	formExpression.sendKeys(expression);
    	submitButton.click();
    }
    
    /* (non-Javadoc)
	 * @see com.vodden.math.parseracceptance.pages.Home#getResult()
	 */
	/* (non-Javadoc)
	 * @see com.vodden.math.parser.acceptance.pages.Home#getResult()
	 */
	@Override
	public Boolean checkResult(Double value) {
    	return value.equals(Double.parseDouble( resultField.getText() ));
    }

}