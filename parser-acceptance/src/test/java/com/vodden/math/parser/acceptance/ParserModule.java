package com.vodden.math.parser.acceptance;

import com.google.inject.AbstractModule;
import com.vodden.math.parser.acceptance.steps.ParserSteps;
import com.vodden.math.parser.acceptance.steps.ParserStepsImpl;

public class ParserModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ParserSteps.class).to(ParserStepsImpl.class);
	}

}
