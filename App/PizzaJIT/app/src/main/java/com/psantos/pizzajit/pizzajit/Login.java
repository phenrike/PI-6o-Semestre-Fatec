package com.psantos.pizzajit.pizzajit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 * Created by Paulo Santos on 24/11/2016.
 */
public class Login {

    public void logar(Activity activity,String login, String senha){
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://192.168.0.17:8080/logar?login="+login+"&senha="+senha+"");

        try {
            HttpResponse response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();

            if (entity != null) {

                String retSrc = EntityUtils.toString(entity);

                JSONObject jObect = new JSONObject(retSrc); //Convert String to JSON Object

                if(jObect.has("id")){

                    Intent telaUsuario = new Intent(activity, TelaUsuario.class);
                    Usuario usuario = new Usuario();
                    usuario.carregarUsuario(jObect);
                    telaUsuario.putExtra("usuario",usuario);
                    activity.startActivity(telaUsuario);

                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                    alertDialog.setTitle("Erro");
                    alertDialog.setMessage("Não foi possível realizar o login. Confirme se o login e senha estão corretos!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            }
        } catch (Exception e) {
            Log.e("Classe login", "Falha ao tentar realizar login", e);
        }
    }
}
