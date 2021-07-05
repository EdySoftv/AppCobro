package com.Softv.SoftvApp.SoftvApp.sampledata;

import android.app.ProgressDialog;
import android.content.Context;

import com.Softv.SoftvApp.SoftvApp.Activitys.Orden;
import com.Softv.SoftvApp.SoftvApp.Activitys.Reportes;

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
            Reportes.dialogReportes.dismiss();
        } catch (Exception e){}
        try {
            Orden.dialogOrdenes.dismiss();
        } catch (Exception e){}
    }
}
