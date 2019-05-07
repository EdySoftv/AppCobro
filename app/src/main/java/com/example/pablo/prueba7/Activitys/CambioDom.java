package com.example.pablo.prueba7.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;


public class CambioDom extends AppCompatActivity {
    private Button aceptar,  regresar;
    public static TextView Ciudad, Localidad, Colonia, Calle, Numero, Numero_i, Telefono, CalleN, CalleS, CallleE, CalleO;
    public static ImageView CasaNorte, CasaSur, CasaEste, CasaOeste;


    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_cambio_domicilio);
        aceptar = findViewById(R.id.aceptar);
        Ciudad = findViewById(R.id.CiudadCAMDO);
        Localidad = findViewById(R.id.localidad);
        Colonia = findViewById(R.id.colonia);
        Calle = findViewById(R.id.Calledom);
        Numero = findViewById(R.id.numero);
        Numero_i = findViewById(R.id.numeroi);
        Telefono = findViewById(R.id.telefono);
        CalleN = findViewById(R.id.callenorte);
        CalleS = findViewById(R.id.callesur);
        CallleE = findViewById(R.id.calleeste);
        CalleO = findViewById(R.id.calleoeste);
        CasaNorte = findViewById(R.id.casanorte);
        CasaSur = findViewById(R.id.casasur);
        CasaEste = findViewById(R.id.casaeste);
        CasaOeste = findViewById(R.id.casaoeste);
        regresar=findViewById(R.id.regresa);
        Ciudad.setText(Request.ciudadcmdo);
        Localidad.setText(Request.localidadcmdo);
        Colonia.setText(Request.coloniacmdo);
        Calle.setText(Request.callecmdo);
        Numero.setText(Request.numerocmdo);
        Numero_i.setText(Request.numeroicmdo);
        Telefono.setText(Request.telefonocmdo);
        CalleN.setText(Request.callencmdo);
        CalleS.setText(Request.callescmdo);
        CallleE.setText(Request.calleecmdo);
        CalleO.setText(Request.calleocmdo);
        if (Request.casacmdo.equals("N")) {
            CasaNorte.setVisibility(View.VISIBLE);
        }
        if (Request.casacmdo.equals("S")) {
            CasaSur.setVisibility(View.VISIBLE);
        }
        if (Request.casacmdo.equals("E")) {
            CasaEste.setVisibility(View.VISIBLE);
        }
        if (Request.casacmdo.equals("O")) {
            CasaOeste.setVisibility(View.VISIBLE);
        }
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento1 = new Intent(CambioDom.this, Orden.class);
                startActivity(intento1);
                finish();
            }
        });


    }
}
