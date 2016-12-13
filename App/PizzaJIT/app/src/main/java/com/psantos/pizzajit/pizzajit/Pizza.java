package com.psantos.pizzajit.pizzajit;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulo Santos on 26/11/2016.
 */
public class Pizza implements Serializable {

    private ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
    private Double preco;
    List<String> ingredientesNome = new ArrayList<String>();

    public Double getPreco() {
        for (int i = 0; i < ingredientes.size(); i++) {
            setPreco(this.preco + ingredientes.get(i).getPrecoPorGrama());
        }

        return preco;
    }

    private void setPreco(Double preco) {
        this.preco = preco;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void adicionarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    public void carregarOpcoesIngredientes() {

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://172.16.40.98:8080/ingredientes");
        ArrayList<Ingrediente> listaIngredientes = new ArrayList<Ingrediente>();

        try {
            HttpResponse response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();

            if (entity != null) {

                String retSrc = EntityUtils.toString(entity);

                JSONArray jObect = new JSONArray(retSrc);

                try {
                    for (int i = 0; i < jObect.length(); i++) {
                        Ingrediente ingrediente = new Ingrediente();
                        ingrediente.carregarIngrediente(jObect.getJSONObject(i));
                        listaIngredientes.add(ingrediente);
                    }

                    for (int i = 0; i < listaIngredientes.size(); i++) {
                        ingredientesNome.add(listaIngredientes.get(i).getNome());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Log.e(" Classe Pizza", "Falha ao listar opções de ingredientes", e);
        }
    }

    public List<String> getIngredientesNome() {
        return ingredientesNome;
    }
}
