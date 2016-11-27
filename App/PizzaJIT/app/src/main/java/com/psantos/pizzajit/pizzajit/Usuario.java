package com.psantos.pizzajit.pizzajit;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Paulo Santos on 24/11/2016.
 */
public class Usuario implements Serializable {

    private int id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String telefone;
    private String dadosCartao;
    private String endereco;

    public void carregarUsuario(JSONObject jsonUsuario){
        try {
            this.id = jsonUsuario.getInt("id");
            this.nome = jsonUsuario.getString("nome");
            this.email = jsonUsuario.getString("email");
            this.login = jsonUsuario.getString("login");
            this.senha = jsonUsuario.getString("senha");
            this.telefone = jsonUsuario.getString("telefone");
            this.dadosCartao = jsonUsuario.getString("dadosCartao");
            this.endereco = jsonUsuario.getString("endereco");
        }catch (Exception e) {
            Log.e("Classe Usuario", "Falha ao carregar perfil", e);
        }

    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDadosCartao() {
        return dadosCartao;
    }

    public void setDadosCartao(String dadosCartao) {
        this.dadosCartao = dadosCartao;
    }
}
