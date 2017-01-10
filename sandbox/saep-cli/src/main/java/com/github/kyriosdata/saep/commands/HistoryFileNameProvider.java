/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.saep.commands;

import org.springframework.shell.plugin.support.DefaultHistoryFileNameProvider;

// @Component
// @Order(Ordered.HIGHEST_PRECEDENCE)
public class HistoryFileNameProvider extends DefaultHistoryFileNameProvider {

	public String getHistoryFileName() {
		return null;
	}

	@Override
	public String getProviderName() {
		return "History (my.log)";
	}
	
}
