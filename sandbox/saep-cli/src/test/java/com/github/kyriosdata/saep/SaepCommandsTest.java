/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.saep;

import org.junit.Test;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.CommandResult;
import org.springframework.shell.core.JLineShellComponent;

import static org.junit.Assert.assertFalse;

public class SaepCommandsTest {

	@Test
	public void testSimple() {
		Bootstrap bootstrap = new Bootstrap();
		
		JLineShellComponent shell = bootstrap.getJLineShellComponent();
		
		CommandResult cr = shell.executeCommand("configuracao abcd.123-3.y");
		assertFalse(cr.isSuccess());
	}
}
