package com.example.pablo.prueba7.Activitys;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pablo.prueba7.Adapters.ArbolAdapter;
import com.example.pablo.prueba7.Adapters.EliminarAparatosAdapter;
import com.example.pablo.prueba7.Adapters.OrdenesAdapter;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.DeepConsModel;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraMedioPorServicoContratadoListResult;
import com.example.pablo.prueba7.Modelos.mediosPregunta;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;
import com.example.pablo.prueba7.sampledata.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReporteAsignacion extends AppCompatActivity {

    Request request = new Request();
    Spinner spinerMedio;
    Button agregarAparato, guardarAparatos;
    public static RecyclerView reporteAsignacion;
    public static EliminarAparatosAdapter adapter;
    int posicionSpinnerSelect;
    public static ProgressDialog dialogReporteAsignacion;
    public static boolean guardarAparatosProgresBar=false;

    public static ArbolAdapter adapterReporte;

    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_reporteasignacion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.include4);
        spinerMedio = findViewById(R.id.spinnerMedioFinal);
        agregarAparato = findViewById(R.id.agregarAparato);
        guardarAparatos = findViewById(R.id.guardarAparato);
        reporteAsignacion = findViewById(R.id.eliminarAparatosList);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intento = new Intent(getApplicationContext(), ServiciosAInstalar.class);
                startActivity(intento);*/
                //GuardarAparatos(getApplicationContext());
                ServiciosAInstalar.Asignacion.refreshDrawableState();
                finish();
            }
        });
        setTitle(ArbolAdapter.nombreToolBar);
        //

        if(guardarAparatos.isEnabled()==false){
            guardarAparatos.setTextColor(Color.GRAY);
            if(DeepConsModel.STATUS.equals("E")){
                guardarAparatos.setEnabled(false);
                guardarAparatos.setTextColor(Color.GRAY);
                agregarAparato.setEnabled(false);
                agregarAparato.setTextColor(Color.GRAY);
            }
        }else{
            if(DeepConsModel.STATUS.equals("E")){
                guardarAparatos.setEnabled(false);
                guardarAparatos.setTextColor(Color.GRAY);
            }else{
                guardarAparatos.setTextColor(Color.WHITE);
            }
        }


        //Barra de cargando
        dialogReporteAsignacion = new BarraCargar().showDialog(this);

        //Arbol
        Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = Array.dataArbSer.iterator();
        final List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = itData.next();

        //Llenamos la lista de los hijos
        try {
            Array.children.clear();
        } catch (Exception e) {
        }
        for (int a = 0; a < dat4.get(ArbolAdapter.posicionArbol).children.size(); a++) {
            Array.children.add(a, dat4.get(ArbolAdapter.posicionArbol).children.get(a).Nombre + "-" + dat4.get(ArbolAdapter.posicionArbol).children.get(a).getDetalle());
        }

        //Revisamos si tiene hijos

        if (dat4.get(ArbolAdapter.posicionArbol).children.size() == 0) {
            //si no tiene hijos habilitamos el spinner
            spinerMedio.setEnabled(true);
            //si no tiene hijos deshabilitamos el boton de guarda
            agregarAparato.setText("Agregar aparato");
            guardarAparatos.setEnabled(false);
            guardarAparatos.setTextColor(Color.GRAY);
            if(dat4.get(ArbolAdapter.posicionArbol).Clv_TipSer==1){
                if(dat4.get(ArbolAdapter.posicionArbol).Detalle.equals("COAXIAL")){
                    guardarAparatos.setEnabled(true);
                    guardarAparatos.setTextColor(Color.WHITE);
                    agregarAparato.setEnabled(false);
                    agregarAparato.setTextColor(Color.GRAY);
                }
            }
        } else {
            //si tiene hijos habilitamos el boton de guarda
            agregarAparato.setText("Agregar otro aparato");
            guardarAparatos.setEnabled(true);
            guardarAparatos.setTextColor(Color.WHITE);
            if(DeepConsModel.STATUS.equals("E")){
                guardarAparatos.setEnabled(false);
                guardarAparatos.setTextColor(Color.GRAY);
            }
            if(dat4.get(ArbolAdapter.posicionArbol).Detalle.equals("COAXIAL")){
                guardarAparatos.setEnabled(true);
                guardarAparatos.setTextColor(Color.WHITE);
            }else{
                //si tiene hijos bloqueamos el spinner porque no se puede cambiar el medio con hijos ya asignados
                spinerMedio.setEnabled(false);
                //si tiene hijos, llenamos lista
                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
                reporteAsignacion.setLayoutManager(layoutManager);
                adapter = new EliminarAparatosAdapter(Array.children,getApplicationContext(),this,ArbolAdapter.posicionArbol,
                        spinerMedio,guardarAparatos);
                reporteAsignacion.setAdapter(adapter);
            }

        }


        //verificar si todos los servicios llevan el mismo medio 1=si 0=no
        if (ServiciosAInstalar.todosLosMedios == 1) {
            try {
                //Intentamos llenar el spinner con el request que nos mandaba la pregunta
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Array.medioPregunta);
                spinerMedio.setAdapter(adapter);
                spinerMedio.setSelection(obtenerPosicionSpinnerMedioSI(dat4.get(ArbolAdapter.posicionArbol).IdMedio));

            }catch (Exception e){
                //Si no se mando el request porque los medios ya venian definidos llenamos el spinner con el medio definido
                ArrayList<String> mediolista=new ArrayList<>();
                mediolista.add(dat4.get(ArbolAdapter.posicionArbol).getDetalle());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mediolista);
                spinerMedio.setAdapter(adapter);
            }
            spinerMedio.setEnabled(false);
        }
        if (ServiciosAInstalar.todosLosMedios == 0) {
            try {
                //Si la respuesta fue no, llenamos el spinner con los medios disponibles para ese servicio
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ClvUnicaNet", ArbolAdapter.clv_unicaNet);
                request.getMedSer(getApplicationContext(), jsonObject, spinerMedio, ArbolAdapter.posicionArbol);
            } catch (Exception e) {
            }
        }
        //Seleccionar medio en el spinner
        spinerMedio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int positionSpinner, long id) {
                if(ServiciosAInstalar.todosLosMedios==1){

                }else{
                    if (positionSpinner != 0) {
                        //seleccionar medio
                        Iterator<List<GetMuestraMedioPorServicoContratadoListResult>> itdata3 = Array.dataMedSer.iterator();
                        List<GetMuestraMedioPorServicoContratadoListResult> dat3 = itdata3.next();
                        dat4.get(ArbolAdapter.posicionArbol).setIdMedio(dat3.get(positionSpinner - 1).getIdMedio());
                        dat4.get(ArbolAdapter.posicionArbol).setDetalle(dat3.get(positionSpinner - 1).getDescripcion());

                    } else {
                        //no se ha seleccionado ningun medio.
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Mandar a la pantalla de agregar nuevo aparato
        agregarAparato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validar que se haya seleccionado un medio
                if (spinerMedio.getSelectedItemPosition() == 0) {
                    if(ServiciosAInstalar.todosLosMediosValidacion==true){
                        Intent intento = new Intent(ReporteAsignacion.this, AsignarAparato.class);
                        intento.putExtra("Clv_UnicaNet", dat4.get(ArbolAdapter.posicionArbol).Clv_UnicaNet);
                        intento.putExtra("idMedio", dat4.get(ArbolAdapter.posicionArbol).IdMedio);
                        intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intento);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "No se ha seleccionado ningun medio", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Intent intento = new Intent(ReporteAsignacion.this, AsignarAparato.class);
                    intento.putExtra("Clv_UnicaNet", dat4.get(ArbolAdapter.posicionArbol).Clv_UnicaNet);
                    intento.putExtra("idMedio", dat4.get(ArbolAdapter.posicionArbol).IdMedio);
                    intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intento);
                    finish();

                }

            }
        });
        //Guardamos aparato
        guardarAparatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarAparatos(getApplicationContext());
                finish();
            }
        });


    }


        public static int obtenerPosicionSpinnerMedio ( int idMedio){
            int position = 0;
            //Arbol
            Iterator<List<GetMuestraMedioPorServicoContratadoListResult>> itdata3 = Array.dataMedSer.iterator();
            List<GetMuestraMedioPorServicoContratadoListResult> dat3 = itdata3.next();
            for (int i = 0; i < dat3.size(); i++) {
                if (dat3.get(i).idMedio == idMedio) {
                    position = i + 1;
                }
            }
            return position;
        }
    public static int obtenerPosicionSpinnerMedioSI ( int idMedio){
        int position = 0;
        //Arbol
        Iterator<List<mediosPregunta>> itdata3 = Array.dataMediosPregunta.iterator();
        List<mediosPregunta> dat3 = itdata3.next();
        for (int i = 0; i < dat3.size(); i++) {
            if (dat3.get(i).getIdMedio() == idMedio) {
                position = i + 1;
            }
        }
        return position;
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_asignacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.siguiente:

                /*Intent intento = new Intent(ReporteAsignacion.this, ServiciosAInstalar.class);
                startActivity(intento);*/
                //guardarAparatosProgresBar=false;
                //GuardarAparatos(getApplicationContext());
                ServiciosAInstalar.Asignacion.refreshDrawableState();
                finish();

                break;
        }
        return true;
    }
    public void GuardarAparatos(final Context context){
        dialogReporteAsignacion.show();
        Request request = new Request();
        JSONObject jsonObject3;
        JSONArray jsonArray3;
        JSONObject jsonObject4 ;
        JSONArray jsonArray2 = new JSONArray();
        for (int a = 0; a < Array.dataArbSer.get(0).size(); a++) {
            Array.dataArbSer.get(0).get(a).setClv_orden( Util.getClvOrden(Util.preferences));
        }
        Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = Array.dataArbSer.iterator();
        List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData.next();
        for (int c = 0; c < dat.size(); c++) {
            jsonObject3 = new JSONObject();
            jsonArray3 = new JSONArray();
            try {
                jsonObject3.put("BaseIdUser", dat.get(c).BaseIdUser);
                jsonObject3.put("BaseRepoteIp", JSONObject.NULL);
                jsonObject3.put("Clv_TipSer", dat.get(c).Clv_TipSer);
                jsonObject3.put("Clv_UnicaNet", dat.get(c).Clv_UnicaNet);
                jsonObject3.put("Contrato", JSONObject.NULL);
                jsonObject3.put("Detalle", dat.get(c).Detalle);
                jsonObject3.put("Expanded", dat.get(c).Expanded);
                jsonObject3.put("IdMedio", dat.get(c).IdMedio);
                jsonObject3.put("Nombre", dat.get(c).Nombre);
                jsonObject3.put("Tipo", dat.get(c).Tipo);
                jsonObject3.put("Type", dat.get(c).Type);
                int hijo = dat.get(c).children.size();
                for (int b = 0; b < hijo; b++) {
                    jsonObject4 = new JSONObject();
                    jsonObject4.put("BaseIdUser", dat.get(c).children.get(b).baseIdUser);
                    jsonObject4.put("BaseRemoteIp", JSONObject.NULL);
                    jsonObject4.put("Clv_Aparato", dat.get(c).children.get(b).Clv_Aparato);
                    //jsonObject4.put("Clv_UnicaNet", JSONObject.NULL);
                    jsonObject4.put("ContratoNet", dat.get(c).children.get(b).ContratoNet);
                    jsonObject4.put("Detalle", dat.get(c).children.get(b).Detalle);
                    jsonObject4.put("Nombre", dat.get(c).children.get(b).Nombre);
                    jsonObject4.put("Tipo", dat.get(c).children.get(b).Tipo);
                    jsonObject4.put("Type", dat.get(c).children.get(b).Type);
                    jsonArray3.put(jsonObject4);
                }
                jsonObject3.put("children", jsonArray3);
                jsonObject3.put("clv_orden", dat.get(c).clv_orden);
                jsonArray2.put(c, jsonObject3);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error", Toast.LENGTH_LONG);
                dialogReporteAsignacion.dismiss();
            }
        }

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject.put("id", 0);
            jsonObject1.put("obj",jsonObject);
            jsonObject1.put("Lst",jsonArray2);
            request.getAceptatAsignacino(context,jsonObject1);
        }catch (Exception e){}
        /*ServiciosAInstalar.Asignacion.setAdapter(ServiciosAInstalar.adapter);*/
        adapterReporte = new ArbolAdapter(dat,context);
        ServiciosAInstalar.Asignacion.setAdapter(adapterReporte);
        ServiciosAInstalar.Asignacion.refreshDrawableState();
        guardarAparatosProgresBar=true;

    }

    public void onBackPressed() {
        ServiciosAInstalar.Asignacion.refreshDrawableState();
        finish();
    }

    }
