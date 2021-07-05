package com.Softv.SoftvApp.SoftvApp.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONObject;



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

        setTitle("No. de Orden: " +  Util.getClvOrden(Util.preferences));


        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
                    jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                    jsonObject.put("OP2", 0);
                    jsonObject.put("OPCION", "M");
                    jsonObject.put("STATUS", "E");
                    request.getValidaTrabajos(getApplicationContext(),jsonObject);
                }catch (Exception e){}
                finish();
            }
        });


        aceptar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
                jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                jsonObject.put("OP2", 0);
                jsonObject.put("OPCION", "M");
                jsonObject.put("STATUS", "E");
                request.getValidaTrabajos(getApplicationContext(),jsonObject);
            }catch (Exception e){}
            finish();
        }
    });
    }
}
