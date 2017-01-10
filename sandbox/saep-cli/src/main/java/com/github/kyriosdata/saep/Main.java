/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.saep;

import org.springframework.shell.Bootstrap;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Driver class to run the helloworld example. 
 * 
 * @author Mark Pollack
 *
 */
public class Main {

	/**
	 * Main class that delegates to Spring Shell's Bootstrap class in order to simplify debugging inside an IDE
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getAnonymousLogger();
        globalLogger.setLevel(Level.OFF);

		Bootstrap.main(args);

	}

}
