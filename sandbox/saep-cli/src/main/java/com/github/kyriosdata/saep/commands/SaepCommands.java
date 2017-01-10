/*
 * Copyright (c) 2016 Fábio Nogueira de Lucena
 * Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package com.github.kyriosdata.saep.commands;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SaepCommands implements CommandMarker {

    private File configuracaoFile;
    private File relatosFile;
	
	@CliAvailabilityIndicator({"execute"})
	public boolean isProntoParaExecucao() {
        return configuracaoFile != null && relatosFile != null;
	}

    @CliCommand(value = "configuracao", help = "Fornece arquivo contendo regras")
    public String configuracao(
            @CliOption(
                    key = { "", "arquivo" },
                    mandatory = true,
                    help = "O arquivo de configuração") final File cfgFile) {
        if (cfgFile.exists()) {
	        configuracaoFile = cfgFile;
	        return "Arquivo de configuração a ser utilizado: " + cfgFile;
        } else {
            configuracaoFile = null;
            return "Arquivo inexistente: " + cfgFile;
        }
    }

    @CliCommand(value = "relatos", help = "Identifica arquivo contendo relatos")
    public String relatos(
            @CliOption(key = { "", "arquivo" }, mandatory = true, help = "O arquivo de relatos") final File relatos
    ) {
        if (relatos.exists()) {
            relatosFile = relatos;
            return "Arquivo de relatos: " + relatosFile;
        } else {
            relatosFile = null;
            return "Arquivo inexistente: " + relatos;
        }
    }

    @CliCommand(value = "execute", help = "Identifica arquivo contendo relatos")
    public String execute() {
        return "Ainda não implementada";
    }

    @CliCommand(value = "status", help = "Executa avaliação de relatos")
    public String status() {
        return statusConfiguracao() +
                "\n" + statusRelatos() +
                "\nPronto para 'execute'.";
    }

    private String statusRelatos() {
        return (relatosFile != null)
                ? "Relatos a serem empregados: " + relatosFile
                : "Relatos não fornecidos";
    }

    private String statusConfiguracao() {
        return (configuracaoFile != null) ? "configuracao definida: " + configuracaoFile : "configuração não fornecida";
    }

    @CliCommand(value = "tutorial", help = "Execute regras para a entrada")
    public String tutorial(
            @CliOption(key = { "" }, mandatory = false, help = "O arquivo de configuração") final File cfgFile
    ) {
        return "" +
                "  Esse programa permite executar um conjunto de regras\n" +
                "  sobre um conjunto de objetos de entrada (relatos).\n" +
                "  \n" +
                "  Parâmetros são obrigatórios: \n" +
                "      (a) configuracao <arquivo json contendo regras>\n" +
                "      (b) relatos <arquivo json contendo relatos>\n" +
                "      (c) execute";
    }
}
