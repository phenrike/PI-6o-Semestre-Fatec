package com.psantos.pizzajit.pizzajit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TelaMontaPizza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_monta_pizza);

        Intent intent = getIntent();
        Pizza pizza = (Pizza) intent.getSerializableExtra("pizza");

        ListView lvIngredientes = (ListView) findViewById(R.id.lvIngredientes);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TelaMontaPizza.this, android.R.layout.simple_list_item_1, pizza.getIngredientesNome());
        lvIngredientes.setAdapter(arrayAdapter);
    }


}
