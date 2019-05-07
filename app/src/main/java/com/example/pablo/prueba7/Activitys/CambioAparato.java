package com.example.pablo.prueba7.Activitys;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import com.example.pablo.prueba7.Adapters.trabajos_adapter_result;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.GetListAparatosDisponiblesByIdArticuloResult;
import com.example.pablo.prueba7.Modelos.GetListClienteAparatosResult;
import com.example.pablo.prueba7.Modelos.GetListTipoAparatosByIdArticuloResult;
import com.example.pablo.prueba7.Modelos.GetSP_StatusAparatosListResult;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;

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

    @Override
    protected void onCreate(@Nullable final Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_cambio_aparato);
        aceptarCambioAparato =  findViewById(R.id.uno);
        aparato = findViewById(R.id.aparato);
        estado = findViewById(R.id.estadoaparato);
        tipoAparato = findViewById(R.id.tipo_aparato1);
        aparatoAsignar = findViewById(R.id.aparatoAsignar);
        aa=findViewById(R.id.aa);
        Finish= findViewById(R.id.dos);
        dialogCAPAT= new BarraCargar().showDialog(this);

        request.getDeepCAPAT(getApplicationContext());
        if(trabajos_adapter_result.ftth==0){
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
                request.SetCambioAparato(getApplicationContext());
                dialogCAPAT.show();
                    finish();
            }
        });
        estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Iterator<List<GetSP_StatusAparatosListResult>> itdata1 = Array.dataStaApa.iterator();
                    List<GetSP_StatusAparatosListResult> dat1 = itdata1.next();
                    statusAparato=dat1.get(position-1).getClv_StatusCableModem();
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
                            if(trabajos_adapter_result.ftth==1){
                                idArticulo2 = dat.get(position-1).getIdArticulo();
                            contrato = dat.get(position-1).getControNet();
                                request.getApaTipDis(getApplicationContext());
                            }else{
                                idArticulo = dat.get(position-1).getIdArticulo();
                                contrato = dat.get(position-1).getControNet();
                                request.getApaTipo(getApplicationContext());
                            }

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
