package com.Softv.SoftvApp.SoftvApp.Activitys;


import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvApp.Adapters.ArbolAdapter;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.RequierePregunta;
import com.Softv.SoftvApp.SoftvApp.Modelos.mediosPregunta;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;


import static com.Softv.SoftvApp.SoftvApp.Activitys.MainActivity.mViewPager;


public class ServiciosAInstalar extends AppCompatActivity {
    Array array = new Array();
    Request request = new Request();

    public static Button siguiente, aceptarAsignacion;
    public static RecyclerView Asignacion;
    public static Spinner spinnerMedio;
    public static ConstraintLayout layoutMedio;
    int c;
    public static ProgressDialog dialogAsignacion;
    public static JSONArray jsonArray2 = new JSONArray();
    public static ArbolAdapter adapter;
    CheckBox checkBoxSi,checkBoxNo;
    TextView txtMedio;
    public static int idMedioSI,todosLosMedios=2; //2 es no haber responidido la pregunta
    public static String detalleSI;
    public static boolean todosLosMediosValidacion=false;
    public int valida=0;
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
                final Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
                final List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();
                for(int a=0; a<dat4.size(); a++){
                    dat4.get(a).setIdMedio(0);
                    dat4.get(a).setDetalle("");
                }
                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
                    jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                    jsonObject.put("OP2", 0);
                    jsonObject.put("OPCION", "M");
                    jsonObject.put("STATUS", "E");
                    request.getValidaTrabajos(getApplicationContext(),jsonObject);
                }catch (Exception e){}
                finish();
            }
        });
        setTitle("Servicios a Instalar");
        /////
        dialogAsignacion = new BarraCargar().showDialog(this);






            if(aceptarAsignacion.isEnabled()==false){
                aceptarAsignacion.setTextColor(Color.GRAY);
                /*if(DeepConsModel.STATUS.equals("E")){
                    aceptarAsignacion.setEnabled(false);
                    aceptarAsignacion.setTextColor(Color.GRAY);
                }*/
            }else{
               /* if(DeepConsModel.STATUS.equals("E")){
                    aceptarAsignacion.setEnabled(false);
                    aceptarAsignacion.setTextColor(Color.GRAY);
                }else{*/
                    aceptarAsignacion.setTextColor(Color.WHITE);
                //}
            }



        final Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
        final List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();
        Iterator<List<RequierePregunta>> itData = array.dataPregunta.iterator();
        List<RequierePregunta> dat = (List<RequierePregunta>) itData.next();

        //Llenar lista

        //Asignacion.setHasFixedSize(true);
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ServiciosAInstalar.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        Asignacion.setLayoutManager(layoutManager);
        //Asignacion.setLayoutManager(linearLayoutManager);
        adapter = new ArbolAdapter(dat4,getApplicationContext());
        Asignacion.setAdapter(adapter);
        if (Asignacion.getAdapter() != null) {
            dialogAsignacion.dismiss();
        }
        /*
        Revisar si los servicios tiene minimo un medio
 */





        if(dat.get(0).RequierePregunta==true){
            ServiciosAInstalar.layoutMedio.setVisibility(View.VISIBLE);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, array.medioPregunta);
            ServiciosAInstalar.spinnerMedio.setAdapter(adapter);
            ServiciosAInstalar.spinnerMedio.setEnabled(false);
        }else{
            ServiciosAInstalar.layoutMedio.setVisibility(View.GONE);

        }








        c = 0;
        for (int a = 0; a < dat4.size(); a++) {
            if (dat4.get(a).IdMedio == 0 || dat4.get(a).IdMedio == null) {
                c = c + 1;
            }
        }
        todosLosMedios=2;
        if (dat.get(0).RequierePregunta == true) {
            checkBoxSi.setChecked(false);
            checkBoxNo.setChecked(false);
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
                    if(dat4.size()==1){

                        todosLosMedios=0;
                        checkBoxSi.setChecked(false);
                        checkBoxNo.setChecked(true);
                    }else{
                        todosLosMediosValidacion=true;
                        todosLosMedios=1;
                        checkBoxNo.setChecked(false);
                        checkBoxSi.setChecked(true);
                    }
                }else{
                    todosLosMedios=0;
                }
            }
            if(c==dat4.size()){
                todosLosMedios=0;
                checkBoxSi.setChecked(false);
                checkBoxNo.setChecked(true);
            }
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
                    if(dat4.size()==1){

                        todosLosMedios=0;
                        checkBoxSi.setChecked(false);
                        checkBoxNo.setChecked(true);
                    }else{
                        todosLosMediosValidacion=true;
                        todosLosMedios=1;
                        checkBoxNo.setChecked(false);
                        checkBoxSi.setChecked(true);
                    }
                }else{
                    todosLosMedios=0;
                }
            }
            if(c==dat4.size()){
                todosLosMedios=0;
                checkBoxSi.setChecked(false);
                checkBoxNo.setChecked(true);
            }
        }

        if(todosLosMedios==1){
            checkBoxNo.setChecked(false);
            checkBoxSi.setChecked(true);

        }
        if(todosLosMedios==0){
            checkBoxSi.setChecked(false);
            checkBoxNo.setChecked(true);

        }

        //verificar si la respuesta no esta contestada



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
                for(int a=0; a<dat4.size(); a++){
                    dat4.get(a).setIdMedio(0);
                    dat4.get(a).setDetalle("");
                }
                checkBoxSi.setChecked(false);
                todosLosMedios=0;
                spinnerMedio.setVisibility(View.GONE);
                txtMedio.setVisibility(View.GONE);

            }
        });
            int c=0;
        for(int a=0; a<dat4.size(); a++){
           if(dat4.get(a).children.size()==0){
               c=c+1;
           }
        }
        if(c==dat4.size()){
            spinnerMedio.setEnabled(true);
        }else{
            spinnerMedio.setEnabled(false);
        }








        aceptarAsignacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
                    jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                    jsonObject.put("OP2", 0);
                    jsonObject.put("OPCION", "M");
                    jsonObject.put("STATUS", "E");
                    request.getValidaTrabajos(getApplicationContext(),jsonObject);
                }catch (Exception e){}
                    finish();
                mViewPager.setCurrentItem(2);

            }
        });
        if (dat.get(0).RequierePregunta == true) {
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
                        todosLosMediosValidacion=false;
                    } else {
                        //no se ha seleccionado ningun medio.
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else{}


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
                final Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
                final List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();
                for(int a=0; a<dat4.size(); a++){
                    dat4.get(a).setIdMedio(0);
                    dat4.get(a).setDetalle("");
                }
                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
                    jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                    jsonObject.put("OP2", 0);
                    jsonObject.put("OPCION", "M");
                    jsonObject.put("STATUS", "E");
                    request.getValidaTrabajos(getApplicationContext(),jsonObject);
                }catch (Exception e){}
                finish();
                //regresar...


                break;
        }
        return true;
    }

    public void onBackPressed() {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
            jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
            jsonObject.put("OP2", 0);
            jsonObject.put("OPCION", "M");
            jsonObject.put("STATUS", "E");
            request.getValidaTrabajos(getApplicationContext(),jsonObject);
        }catch (Exception e){}
        finish();
    }

}
