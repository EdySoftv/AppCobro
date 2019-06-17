package com.example.pablo.prueba7.Activitys;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pablo.prueba7.Adapters.OrdenesAdapter;
import com.example.pablo.prueba7.Adapters.TrabajosAdapter;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.GetListAparatosDisponiblesByIdArticuloResult;
import com.example.pablo.prueba7.Modelos.GetListClienteAparatosResult;
import com.example.pablo.prueba7.Modelos.GetListTipoAparatosByIdArticuloResult;
import com.example.pablo.prueba7.Modelos.GetSP_StatusAparatosListResult;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

public class CambioAparato extends AppCompatActivity {

    public static Spinner aparato, estado,tipoAparato, aparatoAsignar;
    public static int idArticulo, contrato, idArticulo2, clvAparatoCAPAT;
    public static String statusAparato;
    private Request request = new Request();
    ConstraintLayout aa;
    private Array array = new Array();
    private Button aceptarCambioAparato, Finish;
    public static ProgressDialog dialogCAPAT;
    boolean aparatoClienteValidar=false,tipoAparatoClienteValidar=false;

    @Override
    protected void onCreate(@Nullable final Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_cambio_aparato);
        aceptarCambioAparato =  findViewById(R.id.uno);
        Toolbar toolbar = (Toolbar) findViewById(R.id.includeCambioAparato);
        aparato = findViewById(R.id.aparato);
        estado = findViewById(R.id.estadoaparato);
        tipoAparato = findViewById(R.id.tipo_aparato1);
        aparatoAsignar = findViewById(R.id.aparatoAsignar);
        aa=findViewById(R.id.aa);
        Finish= findViewById(R.id.dos);
        dialogCAPAT= new BarraCargar().showDialog(this);

        setTitle("No. de Orden: " + OrdenesAdapter.noOrden);



        request.getDeepCAPAT(getApplicationContext());
        if(TrabajosAdapter.ftth==0){
            aa.setVisibility(View.VISIBLE);
        }else{
            aa.setVisibility(View.GONE);
        }

        Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        aceptarCambioAparato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clvAparatoCAPAT==0){
                    Toast.makeText(getApplicationContext(),"Seleccione el nuevo aparato a asignar",Toast.LENGTH_SHORT).show();
                }else{
                    if(statusAparato.equals("")){
                        Toast.makeText(getApplicationContext(),"Seleccione el estado del aparato",Toast.LENGTH_SHORT).show();
                    }else{
                        if(TrabajosAdapter.ftth==0){
                            if(tipoAparatoClienteValidar==false){
                                Toast.makeText(getApplicationContext(),"Seleccione el tipo de aparato a asignar",Toast.LENGTH_SHORT).show();
                            }else{
                                if(aparatoClienteValidar==false){
                                    Toast.makeText(getApplicationContext(),"Seleccione el aparato a asignar",Toast.LENGTH_SHORT).show();
                                }else{
                                    try{
                                        JSONObject jsonObject = new JSONObject();
                                        JSONObject jsonObject1 = new JSONObject();
                                        jsonObject.put("ClvAparato", clvAparatoCAPAT);
                                        jsonObject.put("ClvOrden", OrdenesAdapter.clvor);
                                        jsonObject.put("ContratoNet", contrato);
                                        jsonObject.put("Status", statusAparato);
                                        jsonObject.put("Trabajo", "CAPAT");
                                        jsonObject1.put("ObjCambioAparato", jsonObject);
                                        request.SetCambioAparato(getApplicationContext(), jsonObject1);
                                        dialogCAPAT.show();
                                        finish();
                                    }catch (Exception e){}
                                }
                            }
                        }else{
                            if(aparatoClienteValidar==false){
                                Toast.makeText(getApplicationContext(),"Seleccione el aparato a asignar",Toast.LENGTH_SHORT).show();
                            }else{
                                try{
                                    JSONObject jsonObject = new JSONObject();
                                    JSONObject jsonObject1 = new JSONObject();
                                    jsonObject.put("ClvAparato", clvAparatoCAPAT);
                                    jsonObject.put("ClvOrden", OrdenesAdapter.clvor);
                                    jsonObject.put("ContratoNet", contrato);
                                    jsonObject.put("Status", statusAparato);
                                    jsonObject.put("Trabajo", "CAPAT");
                                    jsonObject1.put("ObjCambioAparato", jsonObject);
                                    request.SetCambioAparato(getApplicationContext(), jsonObject1);
                                    dialogCAPAT.show();
                                    finish();
                                }catch (Exception e){}
                            }
                        }
                    }
                }


            }
        });
        estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Iterator<List<GetSP_StatusAparatosListResult>> itdata1 = Array.dataStaApa.iterator();
                    List<GetSP_StatusAparatosListResult> dat1 = itdata1.next();
                    statusAparato=dat1.get(position-1).getClv_StatusCableModem();
                }else{
                    statusAparato="";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        aparatoAsignar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Iterator<List<GetListAparatosDisponiblesByIdArticuloResult>> itdata1 = Array.dataApaTipDis.iterator();
                    List<GetListAparatosDisponiblesByIdArticuloResult> dat1 = itdata1.next();
                    clvAparatoCAPAT=dat1.get(position-1).getClv_Aparato();
                }else{
                    clvAparatoCAPAT=0;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        aparato.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position!=0){
                            Iterator<List<GetListClienteAparatosResult>> itdata = array.dataCliApa.iterator();
                            List<GetListClienteAparatosResult> dat = itdata.next();
                            if(TrabajosAdapter.ftth==1){
                                idArticulo2 = dat.get(position-1).getIdArticulo();
                            contrato = dat.get(position-1).getControNet();
                                request.getApaTipDis(getApplicationContext());
                            }else{
                                idArticulo = dat.get(position-1).getIdArticulo();
                                contrato = dat.get(position-1).getControNet();
                                request.getApaTipo(getApplicationContext());
                            }
                            aparatoClienteValidar=true;
                        }else{
                            aparatoClienteValidar=false;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
                );
        tipoAparato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    Iterator<List<GetListTipoAparatosByIdArticuloResult>> itdata = array.dataApaTipo.iterator();
                    List<GetListTipoAparatosByIdArticuloResult> dat = itdata.next();
                    idArticulo2 = dat.get(position-1).getIdArticulo();
                    request.getApaTipDis(getApplicationContext());
                    tipoAparatoClienteValidar=true;
                }else{
                    tipoAparatoClienteValidar=false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public static int obtenerPosicionAC(int abc){
            int position=0;
            Iterator<List<GetListClienteAparatosResult>> itdata = Array.dataCliApa.iterator();
            List<GetListClienteAparatosResult> dat = itdata.next();
            for(int i=0; i<dat.size(); i++){
                if(dat.get(i).Clv_Aparato==abc){
                    position = i+1;
                }
            }
            return position;
        }
        public static int obtenerPosicionSA( String abc){
            int position=0;
            Iterator<List<GetSP_StatusAparatosListResult>> itdata = Array.dataStaApa.iterator();
            List<GetSP_StatusAparatosListResult> dat = itdata.next();
        for(int i=0; i<dat.size(); i++){
            if(dat.get(i).Clv_StatusCableModem.equalsIgnoreCase(abc)){
                position = i+1;
            }
        }
        return position;
    }
    public static int obtenerPosicionTA(int abc){
        int position=0;
        Iterator<List<GetListTipoAparatosByIdArticuloResult>> itdata = Array.dataApaTipo.iterator();
        List<GetListTipoAparatosByIdArticuloResult> dat = itdata.next();
        for(int i=0; i<dat.size(); i++){
            if(dat.get(i).IdArticulo==(abc)){
                position = i+1;
            }
        }
        return position;
    }
    public static int obtenerPosicionA(int abc){
        int position=0;
        Iterator<List<GetListAparatosDisponiblesByIdArticuloResult>> itdata = Array.dataApaTipDis.iterator();
        List<GetListAparatosDisponiblesByIdArticuloResult> dat = itdata.next();
        for(int i=0; i<dat.size(); i++){
            if(dat.get(i).Clv_Aparato==(abc)){
                position = i+1;
            }
        }
        return position;
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
