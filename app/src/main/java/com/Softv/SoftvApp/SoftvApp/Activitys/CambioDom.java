package com.Softv.SoftvApp.SoftvApp.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONObject;




public class CambioDom extends AppCompatActivity {
    private Button aceptar,  regresar;
    public static TextView Ciudad, Localidad, Colonia, Calle, Numero, Numero_i, Telefono, CalleN, CalleS, CallleE, CalleO,ReferenciasCamdo,EntreCallesCamdo;
    public static TextView txtcalle,txtnum,txtnumi,txtrefere,txtentreca;
    public static ImageView CasaNorte, CasaSur, CasaEste, CasaOeste;
    Request request = new Request();


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

        txtcalle=findViewById(R.id.textView49);
        txtnum=findViewById(R.id.textView50);
        txtnumi=findViewById(R.id.textView51);
        txtrefere=findViewById(R.id.textReferenciasCAMDO);
        txtentreca=findViewById(R.id.textEntreCallesCAMDO);

        ReferenciasCamdo = findViewById(R.id.referenciasCAMDO);
        // Entre calles
        EntreCallesCamdo =  findViewById(R.id.EntreCallesCAMDO);
        setTitle("No. de Orden: " +  Util.getClvOrden(Util.preferences));

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
        ReferenciasCamdo.setText(Request.referenciascmd);

        EntreCallesCamdo.setText(Request.entrecallescmd);

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

        /*
        txtcalle=Calle
        txtnum=Numero
        txtnumi=Numero_i
        txtrefere=ReferenciasCamdo
        */
        if(request.PermCableCentro==true){
            txtcalle.setText("Dirección");
            Calle.setText(Request.referenciascmd);
            txtnum.setVisibility(View.GONE);
            Numero.setVisibility(View.GONE);
            txtnumi.setVisibility(View.GONE);
            Numero_i.setVisibility(View.GONE);
            txtrefere.setVisibility(View.GONE);
            ReferenciasCamdo.setVisibility(View.GONE);
            EntreCallesCamdo.setVisibility(View.GONE);
            txtentreca.setVisibility(View.GONE);
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
    @Override
    public void onBackPressed() {
  finish();
    }
}
