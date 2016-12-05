package com.vodden.math.parser.acceptance.pages;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://localhost:8080/parser-ui/faces/parser.xhtml")
public class HomeImpl extends PageObject  {

	@FindBy(id="form:expression")
	WebElement formExpression;
	
	@FindBy(id="form:submit")
	WebElement submitButton;
	
	@FindBy(id="form:result")
	WebElement resultField;

    /* (non-Javadoc)
	 * @see com.vodden.math.parseracceptance.pages.Home#calculate(java.lang.String)
	 */
	public void calculate(String expression) {
    	formExpression.clear();
    	formExpression.sendKeys(expression);
    	submitButton.click();
    }
    
    /* (non-Javadoc)
	 * @see com.vodden.math.parseracceptance.pages.Home#getResult()
	 */
	public Double getResult() {
    	return Double.parseDouble( resultField.getText() );
    }

}