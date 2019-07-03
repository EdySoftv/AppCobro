package com.example.pablo.prueba7.Fragments;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pablo.prueba7.Activitys.Inicio;
import com.example.pablo.prueba7.Dibujo.Firma;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Activitys.Reportes;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;

import org.json.JSONObject;

import java.util.Calendar;

import static com.example.pablo.prueba7.Adapters.QuejasAdapter.clvReport;
import static com.example.pablo.prueba7.Fragments.TrabajosReportes.proble;


/**
 * A simple {@link Fragment} subclass.
 */

//REPORTES
public class EjecutarReportes extends Fragment {

    private Button eject,salir,firmaRep;
    private Request request = new Request();
    private HorasReportes horas = new HorasReportes();
    public static String solution;
    public static int añoE, mesE, diaE;
    public static int mHour;
    public static int mMinute;
    public static String year, horas12;
    public static   String month;
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

        dialogReportes = new BarraCargar().showDialog(getContext());
        View view = inflater.inflate(R.layout.activity_ejecutar_reporte, container, false);
        eject = view.findViewById(R.id.ejecutarR);
        salir= view.findViewById(R.id.SalirR);
        firmaRep = view.findViewById(R.id.firmarRep);
        solution = proble.getText().toString();
        final Calendar c = Calendar.getInstance();
        añoE = c.get(Calendar.YEAR);
        mesE = c.get(Calendar.MONTH);
        diaE = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR);
        mMinute = c.get(Calendar.MINUTE);

        request.validaExisteFirmaBool=false;

       //final  String month;  //6565656fdgsdfg
        if(mesE<10){
            month= "0"+mesE;
        }else{
            month=String.valueOf(mesE);
        }

       // final String minute;
        if(mMinute<10){
            minute= "0"+mMinute;
        }else{
            minute=String.valueOf(mMinute);
        }

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dialogSalir();
            }
        });
        eject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoEjecutar(getContext());
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
                    .setMessage("¿Desea Ejecutar Reporte?")
                    .setPositiveButton("CANCELAR",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        EjecutarReportes.this.finalize();
                                    } catch (Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
                                }
                            })
                    .setNegativeButton("ACEPTAR",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {


                                    try{
                                        JSONObject jsonObject = new JSONObject();
                                        jsonObject.put("clvOrden",0);
                                        jsonObject.put("clvReporte",clvReport);
                                        request.validaExisteFirmaReporte(getContext(),jsonObject);
                                    }catch (Exception e){}
                                }
                            }).show();






    }

    public void validacionReporte (){
        if (TrabajosReportes.solucion.getSelectedItem().toString().trim().equals("Seleccione tipo de solución")) {
            Toast.makeText(getContext(), "Seleccione un tipo de solución", Toast.LENGTH_SHORT).show();
        }else {
            if(TrabajosReportes.proble.getText().toString().isEmpty()){

                Toast.makeText(getContext(),"Campo Problema real vacío", Toast.LENGTH_LONG).show();


            }else {
                dialogReportes.show();
                if (horas.reporteEjecutada == 1) {
                    year = añoE + "" + month + "" + diaE;
                    horas12 = mHour + ":" + minute;
                    request.getGuardaHoraReporte(getContext());


                }
            }


            if (horas.repotteVisita == 1) {
                try {
                    request.getGuardaHoraReporte(getContext());


                } catch (Exception e) {
                    Toast.makeText(getContext(), "La Fecha es obligatoria", Toast.LENGTH_SHORT).show();
                }

            }
            if (horas.reporteVisita1 == 1) {
                try {
                    request.getGuardaHoraReporte(getContext());

                } catch (Exception e) {
                    Toast.makeText(getContext(), "La Fecha es obligatoria", Toast.LENGTH_SHORT).show();
                }
            }
            if (horas.reporteVisita2 == 1) {
                try {
                    request.getGuardaHoraReporte(getContext());

                } catch (Exception e) {
                    Toast.makeText(getContext(), "La Fecha es obligatoria", Toast.LENGTH_SHORT).show();
                }
            }
            ////////*************************
        }


    }


}




