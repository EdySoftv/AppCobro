package com.example.pablo.prueba7.sampledata;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.pablo.prueba7.Activitys.Inicio;
import com.example.pablo.prueba7.Activitys.Login;
import com.example.pablo.prueba7.Request.Request;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    public static boolean LoginShare=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        Intent intentLogin = new Intent(this, Login.class);
        Intent intentinicio = new Intent(this, Inicio.class);
        if (!TextUtils.isEmpty(Util.getTokenPreference(preferences))) {
            LoginShare=true;
            startActivity(intentinicio);
            /*startActivity(intentLogin);
            LoginShare=false;*/

        } else {

            startActivity(intentLogin);
            LoginShare=false;
            /*LoginShare=true;
            startActivity(intentinicio);*/

        }
        finish();
    }
}
