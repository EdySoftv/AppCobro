package com.Softv.SoftvApp.SoftvApp.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.Activitys.Orden;
import com.Softv.SoftvApp.SoftvApp.Modelos.DeepConsModel;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONObject;

import static android.content.Context.LOCATION_SERVICE;
import static com.Softv.SoftvApp.SoftvApp.Activitys.MainActivity.mViewPager;
import static com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarOrdenes.TecSecSelecc;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class HorasOrdenes extends Fragment implements View.OnClickListener, LocationListener {


    public static int ejecutada = 0, visita = 0, visita1 = 0;
    public static  String observacionesTecnico;
    private View contenedorObservacionesTecnico;
    public static TextView cordLat, cordLong;
    public static TextView Obs;
    private Request request = new Request();
    private RadioButton btn1, bt2;
    public static   Button ejecVisita;
    public static EditText obsTec;
    public String fechaActualVisita;
    public static ProgressDialog dialogVisitaOrd;
    public String valorObsTec = null;
    private ConstraintLayout todo;
    public static LocationManager locationManager;
    public static boolean isCoordenadas = false;
    double latitude;
    double longitud;

    public HorasOrdenes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.activity_hora_ordenes, container, false);
        dialogVisitaOrd = new BarraCargar().showDialog(getContext());
        cordLat = (TextView) view.findViewById(R.id.tv_Latitud);
        cordLong = (TextView) view.findViewById(R.id.tv_Longitud);
        Obs = view.findViewById(R.id.tv_Observaciones);
        todo = view.findViewById(R.id.todo);
        contenedorObservacionesTecnico=view.findViewById(R.id.contraintObservacionesTecnico);
        btn1 = view.findViewById(R.id.rb_Visita);
        bt2 = view.findViewById(R.id.rb_Ejecutada);
        obsTec = view.findViewById(R.id.observaciones_Tecnico);

        ejecVisita = view.findViewById(R.id.ejecVisita);



        if(DeepConsModel.STATUS.equals("E")){
            bt2.setChecked(true);
            bt2.setEnabled(false);
            btn1.setEnabled(false);
            ejecVisita.setVisibility(View.GONE);
            ejecutada = 1;
        }else{
            bt2.setEnabled(true);
            btn1.setEnabled(true);
        }

/////////////////////////
        Date objDate = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
/////////////////////////
        locationManager = (LocationManager)
                getActivity().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);

        fechaActualVisita = (dateFormat.format(objDate)) + " " + (hourFormat.format(objDate));
        Obs.setText(request.obsMA);
        bt2.setOnClickListener(this);
        btn1.setOnClickListener(this);
        ejecVisita.setOnClickListener(this);
        comprobarGPSActivo();
        ejecVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valorObsTec=obsTec.getText().toString();
                if( valorObsTec.equals(null) || valorObsTec.equals("")) {
                    Toast.makeText(getContext(), "Escriba sus observaciones", Toast.LENGTH_SHORT).show();
                }else if (visita == 1){
                    dialogoEjecutarVisita();

                }
            }
        });
        return view;

    }


    public void onClick(View view) {
        if (btn1.isChecked() == true) {
            Toast.makeText(getContext(), "Escriba sus observaciones y presione GUARDAR", Toast.LENGTH_LONG).show();
            contenedorObservacionesTecnico.setVisibility(View.VISIBLE);
            ejecVisita.setVisibility(View.VISIBLE);
            ejecutada = 0;
            visita = 1;
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) todo.getLayoutParams();
            params.setMargins(0, 8, 0, 0);
            todo.setLayoutParams(params);
            mostrarParticular(false);


        }
        if (bt2.isChecked() == true) {

            contenedorObservacionesTecnico.setVisibility(View.GONE);
            ejecVisita.setVisibility(View.GONE);
            ejecutada = 1;
            visita = 0;
            mostrarParticular(true);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) todo.getLayoutParams();
            params.setMargins(0, 8, 0, 0);
            todo.setLayoutParams(params);
            ejecutada = 1;
            visita = 0;
            mViewPager.setCurrentItem(1);
            Toast.makeText(getContext(), "Sección de trabajos", Toast.LENGTH_SHORT).show();
        }


    }

    private void mostrarParticular(boolean b) {
        contenedorObservacionesTecnico.setVisibility(b ? View.GONE : View.VISIBLE);
    }


    /////////////////////////GPS///////////////////
    private boolean comprobarGPSActivo() {
        try {
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE);
            if (gpsSignal == 0) {
                //No hay señal de gps(esta desactivado)
                mostrarInformacionDeAlertaGPS();
            } else {
                setearCoordenadas(latitude,longitud);
                return true;
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @SuppressLint("MissingPermission")
    public static void setearCoordenadas(double latitude,double longitud) {
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (location != null) {
            latitude = location.getLatitude();
            // editor.putFloat("latitud", (float) latitude).commit();
            longitud = location.getLongitude();
            //editor.putFloat("longitud", (float) longitud).commit();
            try {
                cordLat.setText(String.valueOf(latitude));
                cordLong.setText(String.valueOf(longitud));
            }catch (Exception e){}
            try {
                EjecutarOrdenes.latitudeEjec=location.getLatitude();

                EjecutarOrdenes.longitudEjec=location.getLongitude();

                cordLong.setText(String.valueOf(longitud));
            }catch (Exception e){}

            //cordLat.setText(String.valueOf(latitude));
            //cordLong.setText(String.valueOf(longitud));
            isCoordenadas = true;
        }
    }

    private void mostrarInformacionDeAlertaGPS() {
        new AlertDialog.Builder(getContext())
                .setTitle("Señal de GPS")
                .setMessage("El GPS esta desactivado. ¿Deseas activarlo?")
                .setPositiveButton("Activar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       mostrarInformacionDeAlertaGPS();
                    }
                })
                .show();
    }

    private void dialogoEjecutarVisita() {
        new android.support.v7.app.AlertDialog.Builder(getContext())
                .setTitle("GUARDAR")
                .setMessage("La visita será registrada con fecha:  " + fechaActualVisita)
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
                                EjecutarVisita();
                                Intent intento = new Intent(getActivity(), Orden.class);
                                intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intento);

                              //  Toast.makeText(getActivity(), "Orden enviada como visita.", Toast.LENGTH_LONG).show();
                            }
                        }).show();


    }

    public void EjecutarVisita(){
        dialogVisitaOrd.show();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        observacionesTecnico = obsTec.getText().toString();
        if (visita == 1) {
            request.ejecutarStatus = "V";
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
                jsonObject.put("TecnicoCuadrilla", TecSecSelecc);
                if (DeepConsModel.Visita1.equals("null") || DeepConsModel.Visita1.equals(" ") ){
                    jsonObject.put("Visita1", fechaActualVisita);
                    jsonObject.put("Visita2", "");
                }else{
                    jsonObject.put("Visita1", DeepConsModel.Visita1);
                    jsonObject.put("Visita2", fechaActualVisita);
                }
                jsonObject1.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
                jsonObject1.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                jsonObject1.put("OP2", 0);
                jsonObject1.put("OPCION", "M");
                jsonObject1.put("STATUS", "E");

                request.getValidaOrdSer(getActivity(),jsonObject,jsonObject1);
            }catch (Exception e){}

        }

    }

    //private boolean isCoordenadas = false;

    @Override
    public void onLocationChanged(Location location) {
        if (cordLat.getText().equals("") | cordLong.getText().equals("")) {
            isCoordenadas = false;
        }

        if (isCoordenadas == false) {
            setearCoordenadas(latitude,longitud);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////


}