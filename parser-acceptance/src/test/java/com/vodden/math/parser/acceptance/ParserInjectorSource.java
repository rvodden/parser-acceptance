package com.vodden.math.parser.acceptance;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

public class ParserInjectorSource implements InjectorSource {

	@Override
	public Injector getInjector() {
		return Guice.createInjector(Stage.PRODUCTION, CucumberModules.SCENARIO, new ParserModule());
	}

}
