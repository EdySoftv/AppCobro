package com.Softv.SoftvApp.SoftvApp.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


import com.Softv.SoftvApp.SoftvApp.Activitys.MainReportes;
import com.Softv.SoftvApp.SoftvApp.Activitys.Reportes;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.Softv.SoftvApp.SoftvApp.Adapters.QuejasAdapter.statusQueja;
import static com.Softv.SoftvApp.SoftvApp.Fragments.TrabajosReportes.Clv_Sol;
import static com.Softv.SoftvApp.SoftvApp.Request.Request.clvP;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.ClvTrabajoRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class HorasReportes extends Fragment  implements View.OnClickListener {
    Request request = new Request();
    public static int reporteEjecutada = 0, repotteVisita = 0, reporteVisita1 = 0, reporteVisita2 = 0, TecSecSelecc1 = -1;
    private View contenedorParticular;
    private RadioButton btn1, bt2;
    public Button salirReporte,guardarReporte;
    public static ProgressDialog dialogVisitaRepo;
    public static int tecPosRepo;
    public static String statusHora;
    public String fechaVisitaReporte,horaVisitaR,fechaVisitaR;
    public String observacionesTecReportes = null;
    public EditText obsTecReporte;

    public HorasReportes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_horas_reporte, container, false);
        Date objDate = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fechaVisitaR= dateFormat.format(objDate);
        horaVisitaR=(hourFormat.format(objDate));
        dialogVisitaRepo =  new BarraCargar().showDialog(getContext());
        fechaVisitaReporte = (dateFormat.format(objDate)) + " " + (hourFormat.format(objDate));

        ///////////////////////////////////////////////////////

        ///////////contenedores y acciones de radiobuttons////
        contenedorParticular = view.findViewById(R.id.RV7);
        btn1 = view.findViewById(R.id.ejutada1);
        bt2 = view.findViewById(R.id.visitada1);
        guardarReporte = view.findViewById(R.id.guardarReporte);
        salirReporte = view.findViewById(R.id.salirReporte);
        obsTecReporte = view.findViewById(R.id.obsTecReporte);
        /////////////////////////////////////////////////////

        ////////// fecaha, hora y radio buttons/////////

        bt2.setOnClickListener(this);
        btn1.setOnClickListener(this);
        guardarReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observacionesTecReportes = obsTecReporte.getText().toString();
                if (observacionesTecReportes.equals(null)||observacionesTecReportes.equals("")){
                    Toast.makeText(getContext(), "Escriba sus observaciones", Toast.LENGTH_SHORT).show();
                }else{

                    DialogoejecutarVisita();
                }

            }
        });
        salirReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoSalida();
            }
        });

        if(statusQueja.equals("E")){
            Toast.makeText(getContext(), "Reporte ya ejecutado", Toast.LENGTH_LONG).show();
            bt2.setChecked(true);
            bt2.setEnabled(false);
            btn1.setEnabled(false);
        }


        return view;
    }

    @Override
    public void onClick(View view) {

                if (btn1.isChecked() == true) {
                    repotteVisita = 1;
                    statusHora = "V";
                    reporteEjecutada = 0;
                    mostrarParticular(false);
                    Toast.makeText(getContext(), "Escriba sus observaciones", Toast.LENGTH_SHORT).show();
                }


                if (bt2.isChecked() == true) {
                    reporteEjecutada = 1;
                    repotteVisita = 0;
                    statusHora = "E";
                    mostrarParticular(true);
                    MainReportes.mViewPager.setCurrentItem(1);
                }

    }

    private void mostrarParticular(boolean b) {

        contenedorParticular.setVisibility(b ? View.GONE : View.VISIBLE);
    }
    private void DialogoejecutarVisita() {
        new AlertDialog.Builder(getContext())
                .setTitle("GUARDAR")
                .setMessage("La visita será registrada con fecha: " + fechaVisitaReporte )
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
                                dialogVisitaRepo.show();
                                envioJsonVisita(getContext());
                                Intent intento = new Intent(getActivity(), Reportes.class);
                                intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intento);

                            }
                        }).show();
    }


    private void dialogoSalida() {
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
                                Toast.makeText(getActivity(), "Respore no ejecutado", Toast.LENGTH_LONG).show();
                            }
                        }).show();
    }

    public void envioJsonVisita(final Context context){

        JSONObject objQuejas = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try{
            objQuejas.put("Clv_Queja", String.valueOf(Util.getClvQueja(Util.preferences)));
            objQuejas.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
            objQuejas.put("FechaProceso", "");
            objQuejas.put("Fecha_Ejecucion", "");
            objQuejas.put("HP", "");
            objQuejas.put("IdUsuario", 2);
            objQuejas.put("Observaciones", request.ObsR + " " + observacionesTecReportes);
            objQuejas.put("Solucion", "");
            objQuejas.put("Status", "V");
            objQuejas.put("Visita", false);
            ////////////////////
            if (request.reporteVisita1==null || request.reporteVisita1.equals(" ")){
                objQuejas.put("HV1", horaVisitaR);
                objQuejas.put("HV2", "");
                objQuejas.put("HV3", "");
                objQuejas.put("Visita1", fechaVisitaR);
                objQuejas.put("Visita2", "");
                objQuejas.put("Visita3", "");
            }else{
                if (request.reporteVisita2==null ||request.reporteVisita2.equals(" ")) {
                    objQuejas.put("HV1", request.reporteHora1);
                    objQuejas.put("HV2", horaVisitaR);
                    objQuejas.put("HV3", "");
                    objQuejas.put("Visita1", request.reporteVisita1);
                    objQuejas.put("Visita2", fechaVisitaR);
                    objQuejas.put("Visita3", "");
                    request.reporteHora1=null;
                    request.reporteVisita1=null;
                }
                else  {
                    objQuejas.put("HV1", request.reporteHora1);
                    objQuejas.put("HV2", request.reporteHora2);
                    objQuejas.put("HV3", horaVisitaR);
                    objQuejas.put("Visita1", request.reporteVisita1);
                    objQuejas.put("Visita2", request.reporteVisita2);
                    objQuejas.put("Visita3", fechaVisitaR);
                    request.reporteHora1=null;
                    request.reporteVisita1=null;
                    request.reporteHora2=null;
                    request.reporteVisita2=null;
                }
            }

            ////////////////////
            objQuejas.put("clvPrioridadQueja", clvP);//error
            objQuejas.put("clvProblema",0 );
            objQuejas.put("clvProblema2", 3);//error
            jsonObject1.put("objQuejas", objQuejas);

            Log.d("visita",jsonObject1.toString());
            request.getGuardaCampos(context,jsonObject1);

        }catch (Exception e){}

    }




    public void envioJsonVisita2(final Context context){
        JSONObject objQuejas = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try{
            objQuejas.put("Clv_Queja", String.valueOf(Util.getClvQueja(Util.preferences)));
            objQuejas.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
            objQuejas.put("FechaProceso", "");
            objQuejas.put("Fecha_Ejecucion", "");
            objQuejas.put("HP", "");
            objQuejas.put("IdUsuario", 1);
            objQuejas.put("Observaciones", request.ObsR + " " + observacionesTecReportes);
            objQuejas.put("Solucion", TrabajosReportes.proble.getText());
            objQuejas.put("Status", "V");
            objQuejas.put("Visita", false);
            if(request.reporteVisita1!=null){
                if(request.reporteVisita2!=null){
                    if(request.reporteVisita3!=null){
                        objQuejas.put("HV1", request.reporteHora1);
                        objQuejas.put("HV2", request.reporteHora2);
                        objQuejas.put("HV3", horaVisitaR);
                        objQuejas.put("Visita1", request.reporteVisita1);
                        objQuejas.put("Visita2", request.reporteVisita2);
                        objQuejas.put("Visita3", fechaVisitaR);
                    }else{
                        objQuejas.put("HV1", request.reporteHora1);
                        objQuejas.put("HV2", request.reporteHora2);
                        objQuejas.put("HV3", horaVisitaR);
                        objQuejas.put("Visita1", request.reporteVisita1);
                        objQuejas.put("Visita2", request.reporteVisita2);
                        objQuejas.put("Visita3", fechaVisitaR);
                    }
                }else{
                    objQuejas.put("HV1", request.reporteHora1);
                    objQuejas.put("HV2", horaVisitaR);
                    objQuejas.put("HV3", "");
                    objQuejas.put("Visita1", request.reporteVisita1);
                    objQuejas.put("Visita2", fechaVisitaR);
                    objQuejas.put("Visita3", "");
                }
            }else{
                objQuejas.put("HV1", horaVisitaR);
                objQuejas.put("HV2", "");
                objQuejas.put("HV3", "");
                objQuejas.put("Visita1", fechaVisitaR);
                objQuejas.put("Visita2", "");
                objQuejas.put("Visita3", "");
            }



            objQuejas.put("clvPrioridadQueja", clvP);
            objQuejas.put("clvProblema",ClvTrabajoRequest );
            objQuejas.put("clvProblemaS", Clv_Sol);
            jsonObject1.put("objQuejas", objQuejas);
            request.getGuardaCampos(context,jsonObject1);
            Toast.makeText(getContext(), "Reporte guardado correctamente", Toast.LENGTH_LONG).show();
        }catch (Exception e){}

    }
}








