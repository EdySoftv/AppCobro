package com.example.pablo.prueba7.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;

public class ExtensionesAdi extends AppCompatActivity {
    private Button aceptar,salir;
    public static TextView txtExtencion;
    Request request= new Request();

    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_extencion);
        aceptar = findViewById(R.id.extencionAceptar);
        txtExtencion = findViewById(R.id.txtExtencion);
        salir=findViewById(R.id.extencionSalir);
        txtExtencion.setText(request.extencionesE);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        aceptar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    }
}
