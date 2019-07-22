package com.example.pablo.prueba7.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Activitys.MainActivity;
import com.example.pablo.prueba7.Activitys.Orden;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.DeepConsModel;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;
import com.example.pablo.prueba7.sampledata.Util;

import org.json.JSONObject;

import static android.content.Context.LOCATION_SERVICE;
import static com.example.pablo.prueba7.Activitys.MainActivity.mViewPager;
import static com.example.pablo.prueba7.Adapters.TrabajosAdapter.retiro;
import static com.example.pablo.prueba7.Fragments.EjecutarOrdenes.TecSecSelecc;
import static com.example.pablo.prueba7.Fragments.EjecutarOrdenes.dialogEjecutar;
import static com.example.pablo.prueba7.Fragments.EjecutarOrdenes.fechaActual;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class HorasOrdenes extends Fragment implements View.OnClickListener, LocationListener {

    public static TextView selectDate,  selectDate1, selectDate2;
    public static String latitud, longitud, diaI, mesI, añoI, diaV1, mesV1, añoV1, diaV2, mesV2, añoV2;
    public static int ejecutada = 0, visita = 0, visita1 = 0;
    public static  String observacionesTecnico;
    private int mYear, mMonth, mDay ;
   // private View contenedorParticular;
    private View contenedorCorporativo;
    private View contenedorObservacionesTecnico;
    public static TextView cordLat, cordLong;
    public static TextView Obs;
    public   EjecutarOrdenes ejecOrd;
    private Request request = new Request();
    private RadioButton btn1, bt2;
    public static   Button ejecVisita;
    public static EditText obsTec;
    public String fechaActualVisita;
    public static ProgressDialog dialogVisitaOrd;
    public String valorObsTec = null;
    private ConstraintLayout todo;
    private ViewGroup container;
    private Bundle onsavedInstanceState;
    private LocationManager locationManager;
    public MainActivity mainAct = new MainActivity();
    private View touchViewEstatus;

    public HorasOrdenes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // TecSec.setSelection(posTec);

        // Inflate the layout for this fragment
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


           // mainAct.mViewPager.setCurrentItem(positionTab);

     /*       if((horas.ejecutada == 1||horas.visita == 1)){
                Toast.makeText(getContext(), "Seleccione un estatus", Toast.LENGTH_SHORT).show();
                mainAct.mViewPager.setCurrentItem(3);
            }*/
            /*selectDate.setText("");
            selectDate1.setText("");
            selectDate2.setText("");
            selectDate2.setEnabled(false);*/


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
       /*     selectDate.setText("");
            selectDate1.setText("");
            selectDate2.setText("");*/
            ejecutada = 1;
            visita = 0;
            mViewPager.setCurrentItem(1);
            Toast.makeText(getContext(), "Sección de trabajos", Toast.LENGTH_SHORT).show();
        }



/*
        if (view == selectDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    if (monthOfYear <= 9) {
                        if (dayOfMonth < 10) {
                            selectDate.setText("0" + dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                            diaI = "0" + String.valueOf(dayOfMonth);
                            mesI = "0" + String.valueOf((monthOfYear + 1));
                            añoI = String.valueOf(year);
                        } else {
                            selectDate.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                            diaI = String.valueOf(dayOfMonth);
                            mesI = "0" + String.valueOf((monthOfYear + 1));
                            añoI = String.valueOf(year);
                        }
                    } else {
                        selectDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        diaI = String.valueOf(dayOfMonth);
                        mesI = String.valueOf((monthOfYear + 1));
                        añoI = String.valueOf(year);
                    }


                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
*/

   /*     if (view == selectDate1) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    if (monthOfYear < 10) {
                        if (dayOfMonth < 10) {
                            selectDate1.setText("0" + dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                            diaV1 = "0" + String.valueOf(dayOfMonth);
                            mesV1 = "0" + String.valueOf((monthOfYear + 1));
                            añoV1 = String.valueOf(year);
                        } else {
                            selectDate1.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                            diaV1 = String.valueOf(dayOfMonth);
                            mesV1 = "0" + String.valueOf((monthOfYear + 1));
                            añoV1 = String.valueOf(year);
                        }
                    } else {
                        selectDate1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        diaV1 = String.valueOf(dayOfMonth);
                        mesV1 = String.valueOf((monthOfYear + 1));
                        añoV1 = String.valueOf(year);
                    }

                }
            }, mYear, mMonth, mDay);
            datePickerDialog1.show();
        }*/


 /*       if (view == selectDate2) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    if (monthOfYear < 10) {
                        if (dayOfMonth < 10) {
                            selectDate2.setText("0" + dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                            diaV2 = "0" + String.valueOf(dayOfMonth);
                            mesV2 = "0" + String.valueOf((monthOfYear + 1));
                            añoV2 = String.valueOf(year);
                        } else {
                            selectDate2.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                            diaV2 = String.valueOf(dayOfMonth);
                            mesV2 = "0" + String.valueOf((monthOfYear + 1));
                            añoV2 = String.valueOf(year);
                        }
                    } else {
                        selectDate2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        diaV2 = String.valueOf(dayOfMonth);
                        mesV2 = String.valueOf((monthOfYear + 1));
                        añoV2 = String.valueOf(year);
                    }
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }*/
    }

    private void mostrarParticular(boolean b) {
        //contenedorParticular.setVisibility(b ? View.VISIBLE : View.GONE);
       // contenedorCorporativo.setVisibility(b ? View.GONE : View.VISIBLE);
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
                setearCoordenadas();
                return true;
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @SuppressLint("MissingPermission")
    private void setearCoordenadas() {
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (location != null) {
            double latitude = location.getLatitude();
            // editor.putFloat("latitud", (float) latitude).commit();
            double longitud = location.getLongitude();
            //editor.putFloat("longitud", (float) longitud).commit();
            cordLat.setText(String.valueOf(latitude));
            cordLong.setText(String.valueOf(longitud));
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

    private boolean isCoordenadas = false;

    @Override
    public void onLocationChanged(Location location) {
        if (cordLat.getText().equals("") | cordLong.getText().equals("")) {
            isCoordenadas = false;
        }

        if (isCoordenadas == false) {
            setearCoordenadas();
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