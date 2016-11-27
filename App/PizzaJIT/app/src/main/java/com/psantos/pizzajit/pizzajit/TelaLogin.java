package com.psantos.pizzajit.pizzajit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TelaLogin extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        Button btnlogar = (Button) findViewById(R.id.btLogar);
        btnlogar.setOnClickListener(this);

    }

    public void onClick(View v)
    {
        new Thread(new Runnable()

        {
            public void run() {
                Login login = new Login();
                EditText edtLogin = (EditText) findViewById(R.id.edtLogin);
                EditText edtSenha = (EditText) findViewById(R.id.edtSenha);
                if(edtLogin.getText() != null && edtSenha.getText() != null){
                    login.logar(TelaLogin.this,edtLogin.getText().toString(),edtSenha.getText().toString());
                }
            }
        }).start();
    }
}
