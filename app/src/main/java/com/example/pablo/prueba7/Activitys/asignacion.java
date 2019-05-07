package com.example.pablo.prueba7.Activitys;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.pablo.prueba7.Activitys.asignado.jsonArrayMAC;
import static com.example.pablo.prueba7.Activitys.asignado.listaDeMac;
import static com.example.pablo.prueba7.Adapters.Arbol_Adapter.validacionSiguiente;
import static com.example.pablo.prueba7.Adapters.ordenes_adapter_result.clvor;

import com.example.pablo.prueba7.Adapters.Arbol_Adapter;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;


public class asignacion extends AppCompatActivity {
    Array array = new Array();
    Request request = new Request();

    public static Button siguiente, aceptarAsignacion, eliminarAparato, cancelarAsigancion;
    public static Button aceptarmedio, cancelarmedio;
    public static ListView Asignacion;
    public static Spinner spinnerMedio;
    public static ConstraintLayout layoutMedio;
    int c, e;
    String f;
    public static ProgressDialog dialogAsignacion;
    public static JSONArray jsonArray = new JSONArray();
    public static JSONArray jsonArray2 = new JSONArray();
    public static JSONArray jsonArray3 = new JSONArray();
    public static JSONObject jsonObject2 = new JSONObject();
    public static JSONObject jsonObject3 = new JSONObject();
    public static JSONObject jsonObject4 = new JSONObject();
    public static Arbol_Adapter adapter;
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_asignacion);
        siguiente = findViewById(R.id.siguiente);
        Asignacion = findViewById(R.id.Asignacion);
        eliminarAparato = findViewById(R.id.eliminarAparatos);
        aceptarmedio = findViewById(R.id.aceptarMedio);
        cancelarmedio = findViewById(R.id.cancelarMedio);
        spinnerMedio = findViewById(R.id.spinnerMedio);
        aceptarAsignacion = findViewById(R.id.aceptarAsignacion);
        layoutMedio = findViewById(R.id.poiuyt);
        cancelarAsigancion = findViewById(R.id.cancelarAsignacion);
        dialogAsignacion= new BarraCargar().showDialog(this);
        adapter = new Arbol_Adapter(getApplicationContext());
        Asignacion.setAdapter(adapter);
        Asignacion.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        Asignacion.refreshDrawableState();
        if(Asignacion.getAdapter()!=null){
            dialogAsignacion.dismiss();
        }
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("asd", String.valueOf(validacionSiguiente));
                if(validacionSiguiente==true){
                    Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = array.dataArbSer.iterator();
                    List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat = itData.next();
                    for (int a = 0; a < dat.size(); a++) {
                        try {
                            jsonObject2 = new JSONObject();
                            jsonObject2.put("Clv_UnicaNet", dat.get(a).Clv_UnicaNet);
                            jsonObject2.put("idMedio", dat.get(a).IdMedio);
                            jsonArray.put(a, jsonObject2);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Intent intento = new Intent(asignacion.this, asignado.class);
                    startActivity(intento);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"No se ha seleccionado nigun medio",Toast.LENGTH_LONG).show();
                }

            }
        });

        aceptarAsignacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAsignacion.show();
                for (int a = 0; a < Array.dataArbSer.get(0).size(); a++) {
                    Array.dataArbSer.get(0).get(a).setClv_orden(clvor);
                }
                Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = array.dataArbSer.iterator();
                List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData.next();
                for (int c = 0; c < dat.size(); c++) {
                    jsonObject3 = new JSONObject();
                    jsonArray3 = new JSONArray();
                    try {
                        jsonObject3.put("BaseIdUser", dat.get(c).BaseIdUser);
                        jsonObject3.put("BaseRepoteIp", JSONObject.NULL);
                        jsonObject3.put("Clv_TipSer", dat.get(c).Clv_TipSer);
                        jsonObject3.put("Clv_UnicaNet", dat.get(c).Clv_UnicaNet);
                        jsonObject3.put("Contrato", JSONObject.NULL);
                        jsonObject3.put("Detalle", dat.get(c).Detalle);
                        jsonObject3.put("Expanded", dat.get(c).Expanded);
                        jsonObject3.put("IdMedio", dat.get(c).IdMedio);
                        jsonObject3.put("Nombre", dat.get(c).Nombre);
                        jsonObject3.put("Tipo", dat.get(c).Tipo);
                        jsonObject3.put("Type", dat.get(c).Type);
                        int hijo=dat.get(c).children.size();
                        for (int b = 0; b < hijo; b++) {
                            jsonObject4 = new JSONObject();
                            jsonObject4.put("BaseIdUser", dat.get(c).children.get(b).baseIdUser);
                            jsonObject4.put("BaseRemoteIp", JSONObject.NULL);
                            jsonObject4.put("Clv_Aparato", dat.get(c).children.get(b).Clv_Aparato);
                            jsonObject4.put("Clv_UnicaNet", JSONObject.NULL);
                            jsonObject4.put("ContratoNet", dat.get(c).children.get(b).ContratoNet);
                            jsonObject4.put("Detalle", dat.get(c).children.get(b).Detalle);
                            jsonObject4.put("Nombre", dat.get(c).children.get(b).Nombre);
                            jsonObject4.put("Tipo", dat.get(c).children.get(b).Tipo);
                            jsonObject4.put("Type", dat.get(c).children.get(b).Type);
                            jsonArray3.put(jsonObject4);
                        }
                        jsonObject3.put("children", jsonArray3);
                        jsonObject3.put("clv_orden", dat.get(c).clv_orden);
                        jsonArray2.put(c, jsonObject3);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(asignacion.this, "Error", Toast.LENGTH_LONG);
                        dialogAsignacion.dismiss();
                    }
                }
                request.getAceptatAsignacino(getApplicationContext());
                finish();
            }
        });
        eliminarAparato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int macvariable=0;
                Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = array.dataArbSer.iterator();
                List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData.next();
                while (Arbol_Adapter.DeletChildren.isEmpty() == false) {
                    for (c = 0; c < dat.size(); c++) {
                        f = String.valueOf(c);
                        e = 0;
                        if (dat.get(c).children != null) {
                            for (int d = 0; d < dat.get(c).children.size(); d++) {
                                String abc = dat.get(c).children.get(d).getClv_Aparato() + f;
                                try{
                                    for(int m=0; m<listaDeMac.size();m++){
                                        if(listaDeMac.get(0).equals(dat.get(c).children.get(m).getClv_Aparato())){
                                            jsonArrayMAC.remove(0);
                                            listaDeMac.remove(0);
                                            // macvariable=macvariable+1;
                                        }
                                    }
                                }catch (Exception e){}
                                try {
                                    if (Integer.parseInt(abc) == (Arbol_Adapter.DeletChildren.get(0))) {
                                        dat.get(c).children.remove(d);
                                        Arbol_Adapter.DeletChildren.remove(0);
                                    }
                                } catch (Exception x) {

                                }
                            }
                        }

                    }
                }

                do{
                    for (c = 0; c < dat.size(); c++) {
                        if (dat.get(c).IdMedio != null && dat.get(c).Detalle != null) {
                            String abc = dat.get(c).IdMedio + dat.get(c).Detalle;
                            try {
                                if ((abc).equals(Arbol_Adapter.DeletMedio.get(0))) {
                                    dat.get(c).setDetalle(null);
                                    dat.get(c).setIdMedio(0);
                                    Arbol_Adapter.DeletMedio.remove(0);
                                }

                            } catch (Exception x) {
                            }
                        }
                    }
                }while (Arbol_Adapter.DeletMedio.isEmpty()==false);



                Asignacion.setAdapter(adapter);

            }
        });
        cancelarAsigancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
