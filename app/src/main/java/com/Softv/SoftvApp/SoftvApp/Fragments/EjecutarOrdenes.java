package com.Softv.SoftvApp.SoftvApp.Fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.LocationManager;
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

import com.Softv.SoftvApp.SoftvApp.Activitys.Inicio;
import com.Softv.SoftvApp.SoftvApp.Activitys.Orden;
import com.Softv.SoftvApp.SoftvApp.Activitys.TecnicosSecundarios;
import com.Softv.SoftvApp.SoftvApp.Adapters.TrabajosAdapter;
import com.Softv.SoftvApp.SoftvApp.Dibujo.Firma;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Modelos.DeepConsModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.ObtieneNapModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.ObtieneTapModel;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import static com.Softv.SoftvApp.SoftvApp.Adapters.TrabajosAdapter.retiro;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes.cordLat;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes.cordLong;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes.obsTec;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes.observacionesTecnico;
import static com.Softv.SoftvApp.SoftvApp.Request.Request.validaExisteFirmaBool;


/**
 * A simple {@link Fragment} subclass.
 */
public class EjecutarOrdenes extends Fragment {

    public static Button reiniciar;
    public static Button eject, firmar,cuadrilla;
    public static String fechaHoy, horaHoy;
    public static View ejecutar;
    public static TextView msgEjecutarOrd,txtTap,txtNap;
    public static TextView tv_textTecSec;
    public static int añoE, mesE, diaE, horaE, minutoE;
    public static  int posTec=0,TecSecSelecc = -1;
    public static Spinner TecSec,spinnerTap,spinnerNap;
    public HorasOrdenes horas = new HorasOrdenes();
    private Request request = new Request();
    public static ProgressDialog dialogEjecutar;
    public static String ejecutarStatus;
    public static String fechaActual,fechasolodias;
    public static String horaFin;
    public static double latitudeEjec;
    public static double longitudEjec;
    private LocationManager locationManager;
    Date objDate = new Date();
    DateFormat hourFormat = new SimpleDateFormat("HH:mm");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static String ClavetecnicaN,ClavetecnicaT;
    public static  int IdTapN=0,IdTapT=0;


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
        cuadrilla = view.findViewById(R.id.tecCuadrilla);

        request.getTecSec(getContext(),TecSec);
        latitudeEjec=0;
        longitudEjec=0;
        /////////////

        HorasOrdenes.setearCoordenadas(latitudeEjec,longitudEjec);
        Log.d("error", String.valueOf(latitudeEjec));
        ///////////////
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
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("clvOrden",DeepConsModel.Clv_Orden);
            jsonObject.put("clvReporte",0);
            request.validaExisteFirma(getContext(),jsonObject,getActivity());
        }catch (Exception e){}


try{
    request.getArbSerValidar(getContext(),spinnerTap,spinnerNap,txtNap,txtTap);
}catch (Exception e){
    Log.d("error","no hay instalacion");
}
try{
    spinnerNap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position!=0){
                Iterator<List<ObtieneNapModel>> itdatan = Array.dataNap.iterator();
                List<ObtieneNapModel> datn = itdatan.next();
                IdTapN=datn.get(position).getIdTap();
                ClavetecnicaN=datn.get(position).getClavetecnica();
            }else{
                IdTapN=0;
                ClavetecnicaN="<Seleccionar>";
            }

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
                    if(position!=0){
                        Iterator<List<ObtieneTapModel>> itdatat = Array.dataTap.iterator();
                        List<ObtieneTapModel> datt = itdatat.next();
                        IdTapT=datt.get(position).getIdTap();
                        ClavetecnicaT=datt.get(position).getClavetecnica();
                    }else{
                        IdTapT=0;
                        ClavetecnicaT="<Seleccionar>";
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }catch (Exception e){}



        fechaActual = (dateFormat.format(objDate)) + " " + (hourFormat.format(objDate));
        horaFin = (hourFormat.format(objDate));
        fechasolodias =dateFormat.format(objDate);
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

        cuadrilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("ClvOrden", Util.getClvOrden(Util.preferences));
                    request.getCuadrilla(getContext(),jsonObject);

                }catch(Exception e){}

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
                                posTec=0;
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

                                if(TrabajosAdapter.validarCoordenadas==true){
                                    if(cordLat.getText().equals("---")|| cordLong.getText().equals("---")){
                                        dialogoCoordenadas();
                                    }else{
                                        Ejecutar();
                                    }
                                }else{
                                    Ejecutar();
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

    private void dialogoCoordenadas() {
        new AlertDialog.Builder(getContext())
                .setTitle("ADVERTENCIA")
                .setMessage("Las coordenadas no se han registrado ¿Desea seguir ejecutando?")
                .setPositiveButton("Ejecutar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Ejecutar();
                            }
                        })
                .setNegativeButton("NO Ejecutar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialogEjecutar.dismiss();
                            }
                        }).show();
    }


    public void Ejecutar(){

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        if(Util.getPermisisDescarga(Util.preferences)==true) {
            if (Array.dataDescargaDirecta.get(0).size() != 0 ) {
                try {
                    JSONObject jsonDescarga = new JSONObject();
                    JSONObject jsonDescarga2 = new JSONObject();
                    JSONArray jsonArrayD = new JSONArray();
                    jsonDescarga.put("IdTecnico", Util.getClvTec(Util.preferences));
                    jsonDescarga.put("ClvOrden", Util.getClvOrden(Util.preferences));
                    jsonDescarga.put("IdAlmacen", 0);
                    jsonDescarga.put("Accion", "Agregar");
                    jsonDescarga.put("IdBitacora", request.NoBitacora);
                    jsonDescarga.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                    jsonDescarga.put("Usuario", Util.getClvTec(Util.preferences));
                    jsonDescarga.put("Fecha", fechasolodias);
                    for (int i = 0; i < Array.dataDescargaDirecta.get(0).size(); i++) {
                        JSONObject articulo = new JSONObject();
                        articulo.put("NoArticulo", Array.dataDescargaDirecta.get(0).get(i).NOARTICULO);
                        articulo.put("Cantidad", Array.dataDescargaDirecta.get(0).get(i).CANTIDADUTILIZADA);
                        articulo.put("EsCable", Array.dataDescargaDirecta.get(0).get(i).ESCABLE);
                        articulo.put("MetrajeInicio", Array.dataDescargaDirecta.get(0).get(i).METRAJEINICIO);
                        articulo.put("MetrajeFin", Array.dataDescargaDirecta.get(0).get(i).METRAJEFIN);
                        articulo.put("MetrajeInicioExt", Array.dataDescargaDirecta.get(0).get(i).METRAJEINICIOEXTERIOR);
                        articulo.put("MetrajeFinExt", Array.dataDescargaDirecta.get(0).get(i).METRAJEFINEXTERIOR);
                        try {
                            articulo.put("NumExt", Array.dataDescargaDirecta.get(0).get(i).NoExt);
                        } catch (Exception e) {
                            articulo.put("NumExt", 0);
                        }
                        jsonArrayD.put(i, articulo);
                    }


                    jsonDescarga2.put("ObjDescargaMat", jsonDescarga);
                    jsonDescarga2.put("Articulos", jsonArrayD);
                    jsonDescarga2.put("Autorizacion", 3);

                    request.addDescarga(getActivity(), getContext(), jsonDescarga2);
                } catch (Exception e) {
                }
            }
        }


        if(request.TAP==true){
            if(IdTapT>0){
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
        }
        if(request.NAP==true){
            if(IdTapN>0){
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
                posTec=0;
                if (retiro == true){
                    request.send_aparat(getContext());
                }
            }catch (Exception e){}


        }


    }
}


