package com.psantos.pizzajit.pizzajit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TelaUsuario extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_usuario);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("usuario");
        TextView textView;
        textView = (TextView) findViewById(R.id.tvNomeUsuario);
        textView.setText(user.getNome()+", clique no botão para fazer seu pedido.");

        Button btnChamaPedido = (Button) findViewById(R.id.btFazerPedido);
        btnChamaPedido.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        Intent telaPedido = new Intent(this, TelaPedido.class);
        this.startActivity(telaPedido);
    }
}
