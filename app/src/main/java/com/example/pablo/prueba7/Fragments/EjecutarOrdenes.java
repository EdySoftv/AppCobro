package com.example.pablo.prueba7.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Activitys.Inicio;
import com.example.pablo.prueba7.Activitys.Orden;
import com.example.pablo.prueba7.Dibujo.Firma;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.DeepConsModel;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;
import com.example.pablo.prueba7.sampledata.Util;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.pablo.prueba7.Adapters.OrdenesAdapter.clvor;
import static com.example.pablo.prueba7.Adapters.TrabajosAdapter.retiro;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.obsTec;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.observacionesTecnico;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.visita1;
import static com.example.pablo.prueba7.Services.Services.claveTecnico;


/**
 * A simple {@link Fragment} subclass.
 */
public class EjecutarOrdenes extends Fragment {

    public static Button reiniciar;
    public static Button eject,firmar;
    public static String fechaHoy,horaHoy;
    public static View ejecutar;
    public static TextView msgEjecutarOrd;
    public static int añoE, mesE, diaE,horaE,minutoE;
    private HorasOrdenes horas = new HorasOrdenes();
    private Request request = new Request();
    public static ProgressDialog dialogEjecutar;
    public static String ejecutarStatus;
    public static String fechaActual;
    Date objDate = new Date();
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    Inicio in;
    Button salir;

    public EjecutarOrdenes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        in = new Inicio();
        dialogEjecutar= new BarraCargar().showDialog(getContext());

        View view = inflater.inflate(R.layout.activity_ejecutar_orden, container, false);
        //reiniciar = view.findViewById(R.id.restart);
        eject = view.findViewById(R.id.ejec);
       // msgEjecutarOrd = view.findViewById(R.id.msgEjecutarOrd);
        //ejecutar = view.findViewById(R.id.ejecutarLay);
        firmar = view.findViewById(R.id.firmarOrd);
//        reiniciar.setEnabled(false);
        salir = view.findViewById(R.id.salirEjecutarOrd);

        request.validaExisteFirmaBool=false;
        if(horas.visita == 1){
            firmar.setVisibility(View.GONE);

        }else {
            if (request.firma==true){
                firmar.setVisibility(View.VISIBLE);

            }else if (request.firma == false){
                firmar.setVisibility(View.GONE);

            }
        }

        fechaActual = ( dateFormat.format(objDate)) +" " + (hourFormat.format(objDate));

       /* if(request.isnet==true){
            ejecutar.setVisibility(View.VISIBLE);
        }else{
            ejecutar.setVisibility(View.GONE);
        }*/
        eject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogEjecutar.show();
                observacionesTecnico = obsTec.getText().toString();

                        try {

                            if(horas.ejecutada==1){
                                if(request.firma==true){
                                        request.ejecutarStatus="E";

                                    try{
                                        JSONObject jsonObject = new JSONObject();
                                        jsonObject.put("clvOrden",DeepConsModel.Clv_Orden);
                                        jsonObject.put("clvReporte",0);
                                        request.validaExisteFirma(getContext(),jsonObject,getActivity());
                                    }catch (Exception e){}
                                }
                                if(request.firma==false){
                                    Ejecutar();
                                    request.ejecutarStatus="E";
                                }
                            }else{
                                Ejecutar();
                                request.ejecutarStatus="V";
                            }

                        } catch (Exception e) {
                            dialogEjecutar.dismiss();
                        }
                    }
                /*}else {
                    if(horas.ejecutada==1){
                        try{
                            request.ejecutarStatus="E";
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("clvOrden",DeepConsModel.Clv_Orden);
                            jsonObject.put("clvReporte",0);
                            request.validaExisteFirma(getContext(),jsonObject,getActivity());
                        }catch (Exception e){}
                        if(request.validaExisteFirmaBool==true){
                            Ejecutar();
                            request.ejecutarStatus="E";
                        }
                    }else{
                        request.ejecutarStatus="V";
                        Ejecutar();
                    }

                }
            }*/

        });
/*        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogEjecutar.show();
                request.ReintentarComando(getActivity());
                reiniciar.setEnabled(false);
            }
        });*/
        firmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //in.dialogoSalida(getContext());
                Intent intent = new Intent(getActivity(), Firma.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoSalida();
            }
        });


        return view;
    }

    private void dialogoSalida() {
        new AlertDialog.Builder(getContext())
                .setTitle("SALIR")
                .setMessage("¿Desea salir de la orden?")
                .setPositiveButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNegativeButton("ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intento = new Intent(getActivity(), Orden.class);
                                intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intento);
                                Toast.makeText(getActivity(), "Orden no ejecutada", Toast.LENGTH_LONG).show();
                            }
                        }).show();



    }





    public  void Ejecutar(){
        System.out.println("Ejecutar");
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        final Calendar c = Calendar.getInstance();
        añoE = c.get(Calendar.YEAR);
        mesE = c.get(Calendar.MONTH);
        diaE = c.get(Calendar.DAY_OF_MONTH);
        horaE = c.get(Calendar.HOUR);
        minutoE = c.get(Calendar.MINUTE);
        String ab;
        if ((mesE+1) < 10) {
            ab = "0" + (mesE+1);
        } else {
            ab = String.valueOf(mesE+1);
        }
        fechaHoy = diaE + "/" + ab + "/" + añoE;
        horaHoy = horaE + ":" + minutoE;
        if (horas.ejecutada == 1) {

            ejecutarStatus="E";
            eject.setEnabled(false);
            try {
                jsonObject.put("ClvFactura", DeepConsModel.Clv_FACTURA);
                jsonObject.put("ClvOrden", DeepConsModel.Clv_Orden);
                jsonObject.put("ClvTecnico", Util.getClvTec(Util.preferences));
                jsonObject.put("ClvTipSer", DeepConsModel.Clv_TipSer);
                jsonObject.put("Contrato", DeepConsModel.Contrato);
                jsonObject.put("FecEje", fechaActual);
                jsonObject.put("FecSol", DeepConsModel.Fec_Sol);
                jsonObject.put("Impresa", 1);
                jsonObject.put("ListadeArticulos", "");
                jsonObject.put("Obs", DeepConsModel.Obs);
                jsonObject.put("Status", "E");
                jsonObject.put("TecnicoCuadrilla", HorasOrdenes.TecSecSelecc);
                jsonObject.put("Visita1", "");
                jsonObject.put("Visita2", "");
                jsonObject1.put("CLV_ORDEN", clvor);
                jsonObject1.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                jsonObject1.put("OP2", 0);
                jsonObject1.put("OPCION", "M");
                jsonObject1.put("STATUS", "E");

                //request.getValidaOrdSer(getActivity(),jsonObject,jsonObject1);
                if (retiro == true){
                    request.send_aparat(getContext());
                }
            }catch (Exception e){}


        }
        if (horas.visita == 1) {
            ejecutarStatus="V";
            try{
                jsonObject.put("ClvFactura", DeepConsModel.Clv_FACTURA);
                jsonObject.put("ClvOrden", DeepConsModel.Clv_Orden);
                jsonObject.put("ClvTecnico", Util.getClvTec(Util.preferences));
                jsonObject.put("ClvTipSer", DeepConsModel.Clv_TipSer);
                jsonObject.put("Contrato", DeepConsModel.Contrato);
                jsonObject.put("FecEje", "");
                jsonObject.put("FecSol", DeepConsModel.Fec_Sol);
                jsonObject.put("Impresa", 1);
                jsonObject.put("ListadeArticulos", "");
                jsonObject.put("Obs", DeepConsModel.Obs + " " + observacionesTecnico);
                jsonObject.put("Status", "V");
                jsonObject.put("TecnicoCuadrilla", HorasOrdenes.TecSecSelecc);
                if (DeepConsModel.Visita1.equals("null") || DeepConsModel.Visita1.equals(" ") ){
                    jsonObject.put("Visita1", fechaActual);
                    jsonObject.put("Visita2", "");
                }else{
                    jsonObject.put("Visita1", DeepConsModel.Visita1);
                    jsonObject.put("Visita2", fechaActual);
                }
                System.out.println("VAL 2");

                jsonObject1.put("CLV_ORDEN", clvor);
                jsonObject1.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                jsonObject1.put("OP2", 0);
                jsonObject1.put("OPCION", "M");
                jsonObject1.put("STATUS", "V");


                request.getValidaOrdSer(getActivity(),jsonObject,jsonObject1);
            }catch (Exception e){}

        }

    }
}


