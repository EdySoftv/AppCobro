package com.example.pablo.prueba7.Activitys;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pablo.prueba7.Adapters.ArbolAdapter;
import com.example.pablo.prueba7.Adapters.OrdenesAdapter;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.example.pablo.prueba7.Modelos.mediosPregunta;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;
import com.example.pablo.prueba7.sampledata.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;


public class ServiciosAInstalar extends AppCompatActivity {
    Array array = new Array();
    Request request = new Request();

    public static Button siguiente, aceptarAsignacion, eliminarAparato, cancelarAsigancion;
    public static Button aceptarmedio, cancelarmedio;
    public static ListView Asignacion;
    public static Spinner spinnerMedio;
    public static ConstraintLayout layoutMedio;
    int c, e;
    String f;
    public static ProgressDialog dialogAsignacion;
    public static JSONArray jsonArray = new JSONArray();
    public static JSONArray jsonArray2 = new JSONArray();
    public static JSONArray jsonArray3 = new JSONArray();
    public static JSONObject jsonObject2 = new JSONObject();
    public static JSONObject jsonObject3 = new JSONObject();
    public static JSONObject jsonObject4 = new JSONObject();
    public static ArbolAdapter adapter;
    CheckBox checkBoxSi,checkBoxNo;
    TextView txtMedio;
    public static int idMedioSI,todosLosMedios=2; //2 es no haber responidido la pregunta
    public static String detalleSI;
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_serviciosinstalar);
        layoutMedio = findViewById(R.id.constraintLayout5);
        siguiente = findViewById(R.id.siguiente);
        Asignacion = findViewById(R.id.Asignacion);
        spinnerMedio = findViewById(R.id.spinnerMedio);
        aceptarAsignacion = findViewById(R.id.aceptarAsignacion);
        checkBoxSi = findViewById(R.id.todosMediosSi);
        checkBoxNo = findViewById(R.id.todosMediosNo);
        txtMedio = findViewById(R.id.textView26);
        Toolbar toolbar = (Toolbar) findViewById(R.id.includeServicios);
/*
        Boton regresar en toolbar
 */
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //regresar...
                finish();
            }
        });
        setTitle("Servicios a Instalar");
        /////
        dialogAsignacion = new BarraCargar().showDialog(this);

        //Request pregunta
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("clv_orden", OrdenesAdapter.clvor);
            request.PreguntaMedios(getApplicationContext(),jsonObject);
        }catch (Exception e){}




        //Llenar lista
        adapter = new ArbolAdapter(getApplicationContext());
        Asignacion.setAdapter(adapter);
        if (Asignacion.getAdapter() != null) {
            dialogAsignacion.dismiss();
        }
        /*
        Revisar si los servicios tiene minimo un medio
 */
        c = 0;
        final Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
        final List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();
        for (int a = 0; a < dat4.size(); a++) {
            if (dat4.get(a).IdMedio == 0 || dat4.get(a).IdMedio == null) {
                c = c + 1;
            }
        }

        if (request.requierePregunta == true) {
        }else{
            if (c != dat4.size()) {
                layoutMedio.setVisibility(View.GONE);
                int contador=0;
                for(int b=0; b<dat4.size(); b++){
                    try{
                        if(dat4.get(b).IdMedio==dat4.get(b+1).IdMedio){
                            contador=contador+1;
                        }
                    }catch (Exception e){}
                }
                if(contador==(dat4.size()-1)){
                    todosLosMedios=1;
                }else{
                    todosLosMedios=0;
                }
            }
        }
        //Pregunta si todos los servicios van por el mismo medio 1=si 0=no

        checkBoxSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxNo.setChecked(false);
                todosLosMedios=1;
                spinnerMedio.setEnabled(true);
                spinnerMedio.setVisibility(View.VISIBLE);
                txtMedio.setVisibility(View.VISIBLE);
            }
        });
        checkBoxNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxSi.setChecked(false);
                todosLosMedios=0;
                spinnerMedio.setVisibility(View.GONE);
                txtMedio.setVisibility(View.GONE);

            }
        });
try{
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Array.medioPregunta);
    spinnerMedio.setAdapter(adapter);
    spinnerMedio.setSelection(ReporteAsignacion.obtenerPosicionSpinnerMedioSI(dat4.get(ArbolAdapter.posicionArbol).IdMedio));
    spinnerMedio.setEnabled(false);
}catch (Exception e){}

        spinnerMedio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int positionSpinnerSi, long id) {
                if (positionSpinnerSi != 0) {
                    //seleccionar medio
                    Iterator<List<mediosPregunta>> itdata3 = Array.dataMediosPregunta.iterator();
                    List<mediosPregunta> dat3 = itdata3.next();
                    idMedioSI=dat3.get(positionSpinnerSi-1).getIdMedio();
                    detalleSI=dat3.get(positionSpinnerSi-1).getDescripcion();

                } else {
                    //no se ha seleccionado ningun medio.
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_asignacion,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.siguiente:

                //regresar...
                finish();

                break;
        }
        return true;
    }
}
