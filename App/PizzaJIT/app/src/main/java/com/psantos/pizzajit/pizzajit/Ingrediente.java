package com.psantos.pizzajit.pizzajit;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Paulo Santos on 26/11/2016.
 */
public class Ingrediente {

    private int id;
    private String nome;
    private Double precoPorGrama;

    public void carregarIngrediente(JSONObject jsonUsuario){
        try {
            this.id = jsonUsuario.getInt("id");
            this.nome = jsonUsuario.getString("nome");
            this.precoPorGrama = jsonUsuario.getDouble("precoPorGrama");
        }catch (Exception e) {
            Log.e("Classe Usuario", "Falha ao carregar perfil", e);
        }

    }

    public int getId() {
        return id;
    }

    public Double getPrecoPorGrama() {
        return precoPorGrama;
    }

    public String getNome() {
        return nome;
    }
}
