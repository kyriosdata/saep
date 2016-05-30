/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.persistencia;

import br.ufg.inf.es.saep.sandbox.dominio.Resolucao;
import br.ufg.inf.es.saep.sandbox.dominio.ResolucaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Implementação JSON.
 */
public class ResolucaoRepositoryJson implements ResolucaoRepository {

    public Resolucao identificadaPor(String identificador) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            File src = new File(identificador);
            Resolucao res = mapper.readValue(src, Resolucao.class);
            return res;
        } catch (IOException exp) {
            System.out.println(exp.toString());
            return null;
        }
    }

    public String persiste(Resolucao resolucao) {
        return null;
    }
}
