/*
 * Copyright (c) 2016 Fábio Nogueira de Lucena
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.saep;

import com.github.kyriosdata.saep.commands.TableRenderer;
import org.junit.Test;
import org.springframework.shell.Bootstrap;
import org.springframework.shell.core.CommandResult;
import org.springframework.shell.core.JLineShellComponent;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class SaepCommandsTest {

	@Test
	public void testSimple() {
		Bootstrap bootstrap = new Bootstrap();
		
		JLineShellComponent shell = bootstrap.getJLineShellComponent();
		
		CommandResult cr = shell.executeCommand("configuracao abcd.123-3.y");
		assertTrue(cr.isSuccess());
		assertTrue(cr.getResult().toString().contains("inexistente"));
	}

	@Test
    public void testaTable() {
	    Map<String, String> tabela = new HashMap<String, String>();
	    tabela.put("João", "Goiânia");
        tabela.put("Pedro", "São Paulo");

        String out = TableRenderer.renderSingleMap(tabela, "NOMES", "CIDADE");
        System.out.println(out);
    }
}
