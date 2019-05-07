package com.example.pablo.prueba7.sampledata;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.example.pablo.prueba7.Activitys.Inicio;
import com.example.pablo.prueba7.Activitys.Orden;
import com.example.pablo.prueba7.Activitys.Reportes;

import java.net.SocketException;

public class BarraCargar {
    public ProgressDialog showDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Cargando...");
        return progressDialog;

    }
    public void terminarBarra() {
        try {
            Inicio.dialogInicio.dismiss();
        } catch (Exception e){}
        try {
            Reportes.dialogReportes.dismiss();
        } catch (Exception e){}
        try {
            Orden.dialogOrdenes.dismiss();
        } catch (Exception e){}
    }
}
