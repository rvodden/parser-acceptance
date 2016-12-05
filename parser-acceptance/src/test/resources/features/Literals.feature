Feature: Literals story

Narrative:
In order to start to build up more complex expressions
As a parser user
I want to be able to use numbers directly in my expression string
					 
Scenario:  This is a selection of positive literals we can test with
	Given I visit the webpage
	When I submit the expression 5
	Then the result should be 5
	
	When I submit the expression 6
	Then the result should be 6
	
	When I submit the expression 11
	Then the result should be 11
	
	When I submit the expression 123
	Then the result should be 123

	When I submit the expression 452
	Then the result should be 452
	
	When I submit the expression 12
	Then the result should be 12



