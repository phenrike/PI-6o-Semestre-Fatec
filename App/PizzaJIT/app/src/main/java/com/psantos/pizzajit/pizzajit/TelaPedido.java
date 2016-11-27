package com.psantos.pizzajit.pizzajit;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class TelaPedido extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_pedido);

        Button btnChamaPizza = (Button) findViewById(R.id.btAddPizza);
        btnChamaPizza.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        new Thread(new Runnable()

        {
            public void run() {
                Intent telaPizza = new Intent(TelaPedido.this, TelaMontaPizza.class);
                Pizza pizza = new Pizza();
                pizza.carregarOpcoesIngredientes();
                telaPizza.putExtra("pizza", pizza);
                TelaPedido.this.startActivity(telaPizza);
            }
        }).start();
    }
}
