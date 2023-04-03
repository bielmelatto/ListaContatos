package br.edu.ifsp.dmos5.model;

public class Contact {

    private String apelido;
    private String nome;
    private String telefone;

    public Contact(String apelido, String nome, String telefone){
        this.apelido = apelido;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getApelido() {
        return apelido;
    }
    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

}
