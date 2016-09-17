/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */

package br.ufg.inf.es.saep.sandbox.dominio.regra;

import br.ufg.inf.es.saep.sandbox.dominio.Entidade;
import br.ufg.inf.es.saep.sandbox.dominio.excecoes.CampoExigidoNaoFornecido;

import java.util.Date;
import java.util.List;

/**
 * Reúne regras para a avaliação de relatórios.
 *
 */
public class Configuracao extends Entidade {

    /**
     * Data da configuração de regras.
     */
    private Date data;

    /**
     * Identificador da configuração.
     * Ou seja, é empregado aqui como um "nome de fantasia".
     * Contraste com {@link #id}.
     */
    private String nome;

    /**
     * Descrição ou informação adicional sobre
     * a configuração.
     */
    private String descricao;

    /**
     * Conjunto de regras definido pela configuração.
     */
    private List<Regra> regras;

    /**
     * Recupera o nome da configuração.
     *
     * @return O nome da configuração.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Recupera a descrição da configuração.
     *
     * @return A descrição da configuração.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Recupera a data de criação da configuração.
     *
     * @return Data de criação da resolução.
     */
    public Date getData() {
        return data;
    }

    /**
     * Recupera o conjunto de regras definido
     * pela configuração.
     *
     * @return Conjunto de regras definido pela configuração.
     */
    public List<Regra> getRegras() {
        return regras;
    }

    /**
     * Cria uma configuração.
     * @param id O identificador único da configuração.
     * @param nome O nome pelo qual seres humanos identificam a configuração.
     * @param descricao A descrição da configuração.
     * @param data A data de criação da configuração.
     * @param regras Conjunto de regras que definem a configuração.
     */
    public Configuracao(String id, String nome, String descricao, Date data, List<Regra> regras) {
        super(id);

        if (descricao == null || descricao.isEmpty()) {
            throw new CampoExigidoNaoFornecido("descricao");
        }

        if (data == null) {
            throw new CampoExigidoNaoFornecido("data");
        }

        if (regras == null || regras.size() < 1) {
            throw new CampoExigidoNaoFornecido("regras");
        }

        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.regras = regras;
    }

    /**
     * Prepara a configuração para uso.
     */
    public void startUp() {

        // Estabelece ordem adequada para execução.
        this.regras = OrdenacaoService.ordena(regras);
    }
}
