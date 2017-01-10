/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.saep.commands;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BannerProvider extends DefaultBannerProvider  {

	public String getBanner() {
		StringBuffer buf = new StringBuffer();
		buf.append("SAEP (linha de comandos)" + OsUtils.LINE_SEPARATOR);
		buf.append("Version:" + this.getVersion());
		return buf.toString();
	}

	public String getVersion() {
		return "1.0.0";
	}

	public String getWelcomeMessage() {
		return "Bem-vindo ao SAEP CLI";
	}
	
	@Override
	public String getProviderName() {
		return "Bem-vindo ao SAEP (cli)";
	}
}