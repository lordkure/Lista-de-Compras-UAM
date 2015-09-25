package br.com.clebersantos.listadecontatosuam.pojo;

import java.io.Serializable;

/**
 * Created by Cleber Luiz da Silva dos Santos on 25/09/2015.
 * Universidade Anhembi Morumbi
 * RA: 20535680
 * Email: santos.cleber@gmail.com
 */
public class Contato implements Serializable {

    private String nome;
    private String telefone;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return getNome() + "[" + getTelefone() + "]";
    }
}
