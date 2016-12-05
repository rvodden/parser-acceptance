package com.vodden.math.parser.acceptance.steps;

import com.vodden.math.parser.acceptance.pages.HomeImpl;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class ParserStepsImpl implements ParserSteps {

	private HomeImpl home;

	public ParserStepsImpl(HomeImpl home) {
		this.home = home;
	}
	
	public ParserStepsImpl() {
		this(new HomeImpl() );
	}

	@Override
	@Given("I visit the webpage")
	public void theUserVisitsTheWebpage() {
		home.open();
	}

	@Override
	@When("I submit the expression (.*)")
	public void theySubmitTheExpression(String value) {
		home.calculate(value);
	}

	@Override
	@Then("the result should be (\\d+)")
	public void theResultShouldBe(Double value) {
		Double result = home.getResult();
		assertEquals(value, result, 0.01);
	}

}
