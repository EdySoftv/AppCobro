package com.example.pablo.prueba7.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Activitys.Inicio;
import com.example.pablo.prueba7.Activitys.Orden;
import com.example.pablo.prueba7.Dibujo.Firma;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.DeepConsModel;
import com.example.pablo.prueba7.Modelos.ObtieneNapModel;
import com.example.pablo.prueba7.Modelos.ObtieneTapModel;
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
import java.util.Iterator;
import java.util.List;


import static com.example.pablo.prueba7.Adapters.TrabajosAdapter.retiro;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.obsTec;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.observacionesTecnico;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.visita1;
import static com.example.pablo.prueba7.Request.Request.validaExisteFirmaBool;


/**
 * A simple {@link Fragment} subclass.
 */
public class EjecutarOrdenes extends Fragment {

    public static Button reiniciar;
    public static Button eject, firmar;
    public static String fechaHoy, horaHoy;
    public static View ejecutar;
    public static TextView msgEjecutarOrd,txtTap,txtNap;
    public static TextView tv_textTecSec;
    public static int añoE, mesE, diaE, horaE, minutoE;
    public static  int posTec,TecSecSelecc = -1;
    public static Spinner TecSec,spinnerTap,spinnerNap;
    public HorasOrdenes horas = new HorasOrdenes();
    private Request request = new Request();
    public static ProgressDialog dialogEjecutar;
    public static String ejecutarStatus;
    public static String fechaActual;
    public static String horaFin;
    Date objDate = new Date();
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static String ClavetecnicaN,ClavetecnicaT;
    public static  int IdTapN,IdTapT;


    Inicio in;
    Button salir;
    static SharedPreferences preferences;

    public EjecutarOrdenes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        dialogEjecutar = new BarraCargar().showDialog(getContext());

        View view = inflater.inflate(R.layout.activity_ejecutar_orden, container, false);
        //reiniciar = view.findViewById(R.id.restart);
        eject = view.findViewById(R.id.ejec);
        tv_textTecSec =view.findViewById(R.id.tv_textTecSec);
        // msgEjecutarOrd = view.findViewById(R.id.msgEjecutarOrd);
        //ejecutar = view.findViewById(R.id.ejecutarLay);
        firmar = view.findViewById(R.id.firmarOrd);
        TecSec = view.findViewById(R.id.spinnerTecnicoSec);
//        reiniciar.setEnabled(false);
        salir = view.findViewById(R.id.salirEjecutarOrd);
        spinnerNap = view.findViewById(R.id.spinnerNap);
        spinnerTap = view.findViewById(R.id.spinnerTap);
        txtNap = view.findViewById(R.id.txtNap);
        txtTap = view.findViewById(R.id.txtTap);

        request.getTecSec(getContext(),TecSec);
        if(horas.visita == 1){
            firmar.setVisibility(View.GONE);
            TecSec.setVisibility(View.GONE);
            tv_textTecSec.setVisibility(View.GONE);
        } else {
            if (request.firma == true) {
                firmar.setVisibility(View.VISIBLE);
                TecSec.setVisibility(View.VISIBLE);
                tv_textTecSec.setVisibility(View.VISIBLE);

            } else if (request.firma == false) {
                firmar.setVisibility(View.GONE);
            }
        }
        if(DeepConsModel.STATUS.equals("E")){
            eject.setEnabled(false);
            eject.setTextColor(Color.GRAY);
            firmar.setEnabled(false);
            firmar.setTextColor(Color.GRAY);
        }else{
            eject.setEnabled(true);
            eject.setTextColor(Color.WHITE);
            firmar.setEnabled(true);
            firmar.setTextColor(Color.WHITE);
        }
try{
    request.getArbSerValidar(getContext(),spinnerTap,spinnerNap,txtNap,txtTap);
}catch (Exception e){
    Log.d("error","no hay instalacion");
}
try{
    spinnerNap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Iterator<List<ObtieneNapModel>> itdatan = Array.dataNap.iterator();
            List<ObtieneNapModel> datn = itdatan.next();
            IdTapN=datn.get(position).getIdTap();
            ClavetecnicaN=datn.get(position).getClavetecnica();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
}catch (Exception e){}



        try{
            spinnerTap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Iterator<List<ObtieneTapModel>> itdatat = Array.dataTap.iterator();
                    List<ObtieneTapModel> datt = itdatat.next();
                    IdTapT=datt.get(position).getIdTap();
                    ClavetecnicaT=datt.get(position).getClavetecnica();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }catch (Exception e){}



        fechaActual = (dateFormat.format(objDate)) + " " + (hourFormat.format(objDate));
        horaFin = (hourFormat.format(objDate));
       /* if(request.isnet==true){
            ejecutar.setVisibility(View.VISIBLE);
        }else{
            ejecutar.setVisibility(View.GONE);
        }*/


        TecSec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posTec=position;
                TecSecSelecc = Array.clv_tecnicoSecundario.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        eject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                observacionesTecnico = obsTec.getText().toString();

                try{
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("clvOrden",DeepConsModel.Clv_Orden);
                    jsonObject.put("clvReporte",0);
                    request.validaExisteFirma(getContext(),jsonObject,getActivity());
                }catch (Exception e){}

                            if(horas.ejecutada==1){
                                if(request.firma==true){
                                        request.ejecutarStatus="E";

                                        if(validaExisteFirmaBool == true){
                                            dialogoEjecutar1();
                                            validaExisteFirmaBool = true;

                                        }else if (validaExisteFirmaBool == false ){
                                            dialogoRequiereFirma();
                                        }
                                }else if (request.firma == false){
                                    request.ejecutarStatus="E";
                                    dialogoEjecutar1();
                                }

                            }
                            dialogEjecutar.dismiss();


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

    private void dialogoEjecutar1() {
        new AlertDialog.Builder(getContext())
                .setTitle("Ejecutar Orden")
                .setMessage("¿Desea ejecutar la orden?")
                .setPositiveButton("ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialogEjecutar.show();

                                if(request.TAP==true){
                                    if(spinnerTap.getSelectedItemPosition()!=0){
                                        if(request.NAP==true){
                                            if(spinnerNap.getSelectedItemPosition()!=0){
                                                Ejecutar();
                                                Intent intento = new Intent(getActivity(), Orden.class);
                                                intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intento);
                                                Toast.makeText(getActivity(), "Orden ejecutada.", Toast.LENGTH_LONG).show();
                                            }else{
                                                dialogEjecutar.dismiss();
                                                Toast.makeText(getContext(),"Seleccione CTO", Toast.LENGTH_LONG).show();
                                            }
                                        }else{
                                            Ejecutar();
                                            Intent intento = new Intent(getActivity(), Orden.class);
                                            intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intento);
                                            Toast.makeText(getActivity(), "Orden ejecutada.", Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        dialogEjecutar.dismiss();
                                        Toast.makeText(getContext(),"Seleccione Tap", Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    if(request.NAP==true){
                                        if(spinnerNap.getSelectedItemPosition()!=0){
                                            Ejecutar();
                                            Intent intento = new Intent(getActivity(), Orden.class);
                                            intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intento);
                                            Toast.makeText(getActivity(), "Orden ejecutada.", Toast.LENGTH_LONG).show();
                                        }else{
                                            dialogEjecutar.dismiss();
                                            Toast.makeText(getContext(),"Seleccione CTO", Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        Ejecutar();
                                        Intent intento = new Intent(getActivity(), Orden.class);
                                        intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intento);
                                        Toast.makeText(getActivity(), "Orden ejecutada.", Toast.LENGTH_LONG).show();
                                    }
                                }


                                //request.getListOrd(getContext());


                            }
                        })
                .setNegativeButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
    }

    private void dialogoRequiereFirma() {
        new AlertDialog.Builder(getContext())
                .setTitle("ADVERTENCIA")
                .setMessage("Para completar la orden se requiere la firma del cliente")
                .setPositiveButton("ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNegativeButton("",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
    }

    public void Ejecutar(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        if(request.TAP==true){
            try{
                JSONObject jsonObject2 = new JSONObject();
                JSONObject jsonObjectT = new JSONObject();
                jsonObjectT.put("CONTRATO",request.ContratoReal);
                jsonObjectT.put("TAP",ClavetecnicaT);
                jsonObjectT.put("idtap",IdTapT);
                jsonObject2.put("ObjTap",jsonObjectT);
                request.guardaTap(getContext(),jsonObjectT);

            }catch (Exception e){}
        }
        if(request.NAP==true){
            try{
                JSONObject jsonObject2 = new JSONObject();
                JSONObject jsonObjectN = new JSONObject();
                jsonObjectN.put("CONTRATO",request.ContratoReal);
                jsonObjectN.put("TAP",ClavetecnicaN);
                jsonObjectN.put("IDTAP",IdTapN);
                jsonObject2.put("ObjNap",jsonObjectN);
                request.guardaNap(getContext(),jsonObjectN);
            }catch (Exception e){}
        }




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
                jsonObject.put("TecnicoCuadrilla", TecSecSelecc);
                jsonObject.put("Visita1", "");
                jsonObject.put("Visita2", "");

                jsonObject1.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
                jsonObject1.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                jsonObject1.put("OP2", 0);
                jsonObject1.put("OPCION", "M");
                jsonObject1.put("STATUS", "E");

                request.getValidaOrdSer(getActivity(),jsonObject,jsonObject1);

                if (retiro == true){
                    request.send_aparat(getContext());
                }
            }catch (Exception e){}


        }


    }
}


