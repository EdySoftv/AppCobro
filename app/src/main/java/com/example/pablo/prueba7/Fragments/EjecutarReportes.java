package com.example.pablo.prueba7.Fragments;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pablo.prueba7.Activitys.Inicio;
import com.example.pablo.prueba7.Dibujo.Firma;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Activitys.Reportes;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;
import com.example.pablo.prueba7.sampledata.Util;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.pablo.prueba7.Adapters.QuejasAdapter.statusQueja;
import static com.example.pablo.prueba7.Fragments.TrabajosReportes.proble;
import static com.example.pablo.prueba7.Request.Request.validaExisteFirmaBool;


/**
 * A simple {@link Fragment} subclass.
 */

//REPORTES
public class EjecutarReportes extends Fragment {

    private Button eject,salir,firmaRep;
    private Request request = new Request();
    private HorasReportes horas = new HorasReportes();
    public static String solution;
    public static String fechaHoy,horaHoy;
    public static int añoE, mesE, diaE;
    public static int horaE,minutoE;
    public static String year, horas12;
    public static   String month,fechaEjecujtar, horaEjecutar;
    public static Spinner TecSecu;
    public static  int TecSecSeleccion = -1;
    public static int tecSecPosRepo;
   public static  String minute;
    public static ProgressDialog dialogReportes;
   Inicio in;
    public EjecutarReportes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        in = new Inicio();
        Date objDate = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        horaEjecutar = (hourFormat.format(objDate));
        fechaEjecujtar = (dateFormat.format(objDate));
        dialogReportes = new BarraCargar().showDialog(getContext());
        View view = inflater.inflate(R.layout.activity_ejecutar_reporte, container, false);
        eject = view.findViewById(R.id.ejecutarR);
        salir= view.findViewById(R.id.SalirR);
        TecSecu = view.findViewById(R.id.tecnicosecundario4);
        firmaRep = view.findViewById(R.id.firmarRep);
        solution = proble.getText().toString();
        final Calendar c = Calendar.getInstance();
        añoE = c.get(Calendar.YEAR);
        mesE = c.get(Calendar.MONTH);
        diaE = c.get(Calendar.DAY_OF_MONTH);
        horaE = c.get(Calendar.HOUR);
        minutoE = c.get(Calendar.MINUTE);

        request.validaExisteFirmaBool=false;
        request.getTecSecR(getContext(), TecSecu);

        if(statusQueja.equals("E")){
          eject.setEnabled(false);
          salir.setEnabled(false);
          TecSecu.setEnabled(false);
          eject.setTextColor(Color.GRAY);
          salir.setTextColor(Color.GRAY);
        }

        if (HorasReportes.reporteEjecutada == 0) {
            firmaRep.setVisibility(View.GONE);
        }



       //final  String month;  //6565656fdgsdfg
        if(mesE<10){
            month= "0"+mesE;
        }else{
            month=String.valueOf(mesE);
        }

       // final String minute;
        if(minutoE<10){
            minute= "0"+minutoE;
        }else{
            minute=String.valueOf(minutoE);
        }



        TecSecu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TecSecSeleccion = Array.Clv_TecSecR.get(position);
                tecSecPosRepo=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dialogSalir();
            }
        });
        eject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("clvOrden",0);
                    jsonObject.put("clvReporte", Util.getClvQueja(Util.preferences));
                    request.validaExisteFirma(getContext(),jsonObject,getActivity());
                }catch (Exception e){}

                        if(validaExisteFirmaBool == true){
                            dialogoEjecutar(getContext());
                            validaExisteFirmaBool = true;
                        }else if (validaExisteFirmaBool == false ){
                            dialogoRequiereFirma();
                        }
            }

        });
        firmaRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Firma.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }

    private void dialogSalir() {
        new AlertDialog.Builder(getContext())
                .setTitle("SALIR")
                .setMessage("¿Desea salir del reporte?")
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
                                Intent intento = new Intent(getActivity(), Reportes.class);
                                intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intento);
                                Toast.makeText(getActivity(), "Reporte no ejecutado", Toast.LENGTH_LONG).show();
                            }
                        }).show();

    }

    private void dialogoEjecutar(Context onClickListener) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Ejecutar Reporte")
                    .setMessage("¿Desea ejecutar el reporte?")
                    .setPositiveButton("CANCELAR",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                 /*   try {
                                        EjecutarReportes.this.finalize();
                                    } catch (Throwable throwable) {
                                        throwable.printStackTrace();
                                    }*/
                                }
                            })
                    .setNegativeButton("ACEPTAR",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialogReportes.show();

                                    try{
                                        JSONObject jsonObject = new JSONObject();
                                        jsonObject.put("clvOrden",0);
                                        jsonObject.put("clvReporte", Util.getClvQueja(Util.preferences));
                                        request.validaExisteFirmaReporte(getContext(),jsonObject);
                                    }catch (Exception e){}
                                }
                            }).show();

    }

    private void dialogoRequiereFirma() {
        new AlertDialog.Builder(getContext())
                .setTitle("ADVERTENCIA")
                .setMessage("Para completar el reporte se requiere la firma del cliente")
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
/*    public void validacionReporte (){
        if (TrabajosReportes.solucion.getSelectedItem().toString().trim().equals("Seleccione tipo de solución")) {
            Toast.makeText(getContext(), "Seleccione un tipo de solución", Toast.LENGTH_SHORT).show();
        }else {
            if(TrabajosReportes.proble.getText().toString().isEmpty()){

                Toast.makeText(getContext(),"Campo Problema real vacío", Toast.LENGTH_LONG).show();


            }else {
                dialogReportes.show();
                if (horas.reporteEjecutada == 1) {
                    year = añoE + "" + month + "" + diaE;
                    horas12 = horaE  + ":" + minute;
                    //request.getGuardaHoraReporte(getContext());


                }
            }


            if (horas.repotteVisita == 1) {
                try {
                    //request.getGuardaHoraReporte(getContext());


                } catch (Exception e) {
                    Toast.makeText(getContext(), "La Fecha es obligatoria", Toast.LENGTH_SHORT).show();
                }

            }
            if (horas.reporteVisita1 == 1) {
                try {
                    //request.getGuardaHoraReporte(getContext());

                } catch (Exception e) {
                    Toast.makeText(getContext(), "La Fecha es obligatoria", Toast.LENGTH_SHORT).show();
                }
            }
            if (horas.reporteVisita2 == 1) {
                try {
                    //request.getGuardaHoraReporte(getContext());

                } catch (Exception e) {
                    Toast.makeText(getContext(), "La Fecha es obligatoria", Toast.LENGTH_SHORT).show();
                }
            }
            ////////*************************
        }


    }*/


}




