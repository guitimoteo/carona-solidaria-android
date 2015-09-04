package br.org.carona.caronasolidaria.rest.model;


import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("nome")
    private String nome;
    @SerializedName("senha")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
