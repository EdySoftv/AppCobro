package com.example.pablo.prueba7.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Adapters.ArbolAdapter;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.GetMuestraAparatosDisponiblesListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraMedioPorServicoContratadoListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraServiciosRelTipoAparatoListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraTipoAparatoListResult;
import com.example.pablo.prueba7.Modelos.children;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.Util;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.pablo.prueba7.Adapters.OrdenesAdapter.clvor;

public class AsignarAparato extends AppCompatActivity {

    private Button escanear, agragar, cancelar;
    private TextView codigo;
    private String contents;
    public static ListView serviciosAparato;
    public static Spinner spinnerAparato, spinneraparatoDisponible;
    private Request request = new Request();
    private Array array = new Array();
    public static int idArticuloasignado, clveAparatoSpinner;
    public static String detalleSpinner, nombreSpinner;
    public static ArrayList<Integer> selectedStrings = new ArrayList<Integer>();
    public static ConstraintLayout constraintLayoutMACWAM;
    public static EditText MACWAMText;
    public static JSONArray jsonArrayMAC= new JSONArray();
    String clvMACWAM = "";

    public static ArrayList<Integer> listaDeMac = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_asignar_aparato);
        Toolbar toolbar = (Toolbar) findViewById(R.id.includeAsignarAparatos);
        spinnerAparato = findViewById(R.id.tipo_aparato);
        spinneraparatoDisponible = findViewById(R.id.aparatoDisponible);
        serviciosAparato = findViewById(R.id.Servicios123);
        agragar = findViewById(R.id.agregarAsignacionAparato);
        cancelar = findViewById(R.id.cancelarAsignacionAparato);
        constraintLayoutMACWAM = findViewById(R.id.MACWAMConstraint);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(AsignarAparato.this, ReporteAsignacion.class);
                startActivity(intento);
                finish();
            }
        });
        setTitle(ArbolAdapter.nombreToolBar);
        final Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
        final List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();


        //Recibimos datos
        final Bundle datos = this.getIntent().getExtras();

        MACWAMText = findViewById(R.id.MacWam);
        //filtro para la macwan
        MACWAMText.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(12)});


        if(agragar.isEnabled()==false){
            agragar.setTextColor(Color.GRAY);
        }



        //request llenar tipo de aparato
        request.getTipoAparatos(getApplicationContext(),LlenarSpinnerTipoDeAparato(datos.getInt("Clv_UnicaNet"),datos.getInt("idMedio"),0),spinnerAparato);





        constraintLayoutMACWAM = findViewById(R.id.MACWAMConstraint);
        MACWAMText = findViewById(R.id.MacWam);
        MACWAMText.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(12)});
        selectedStrings.clear();


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(AsignarAparato.this, ReporteAsignacion.class);
                startActivity(intento);
                finish();
            }
        });


        spinnerAparato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (position != 0) {
                    Iterator<List<GetMuestraTipoAparatoListResult>> itdata = array.dataTipoAparatos.iterator();
                    List<GetMuestraTipoAparatoListResult> dat = itdata.next();
                    detalleSpinner = dat.get(position - 1).getCategoria();

                    //Request Aparatos Disponibles
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden", clvor);
                        jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                        jsonObject.put("idArticulo", dat.get(position - 1).getIdArticulo());
                        request.getAparatosDisponibles(getApplicationContext(),
                                jsonObject,spinneraparatoDisponible);
                    }catch (Exception e){}

                    //Request Servicios
                    request.getServiciosAparatos(getApplicationContext(),
                            LlenarSpinnerTipoDeAparato(datos.getInt("Clv_UnicaNet"),datos.getInt("idMedio"),
                                    dat.get(position-1).getIdArticulo()),serviciosAparato,agragar);



                    try {
                        JSONObject jsonObject = new JSONObject();
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject.put("Letra", dat.get(position - 1).letra);
                        jsonObject1.put("ObjRelMacwan", jsonObject);
                        request.ValidaMACWAM(getApplicationContext(), jsonObject1);
                    } catch (Exception e) {
                    }
                    serviciosAparato.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    serviciosAparato.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                            Iterator<List<GetMuestraServiciosRelTipoAparatoListResult>> itData2 = array.dataserviciosAparatos.iterator();
                            final List<GetMuestraServiciosRelTipoAparatoListResult> dat2 = itData2.next();

                            if (clveAparatoSpinner == 0) {
                                Toast.makeText(getApplicationContext(), "Seleccione un tipo de aparato", Toast.LENGTH_LONG).show();
                            } else {
                                if (dat2.get(position1).baseIdUser == 0) {
                                    dat2.get(position1).setBaseIdUser(1);
                                } else {
                                    dat2.get(position1).setBaseIdUser(0);
                                }
                                if (dat2.get(position1).baseIdUser == 1) {
                                    selectedStrings.add(dat2.get(position1).clv_UnicaNet);
                                    agragar.setEnabled(true);
                                    agragar.setTextColor(Color.WHITE);
                                } else {
                                    selectedStrings.remove(dat2.get(position1).clv_UnicaNet);
                                    if(selectedStrings.size()==0){
                                        agragar.setEnabled(false);
                                        agragar.setTextColor(Color.GRAY);
                                    }
                                }
                            }
                        }

                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinneraparatoDisponible.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
                if (position1 != 0) {
                    Iterator<List<GetMuestraAparatosDisponiblesListResult>> itData1 = array.dataAparatosDisponibles.iterator();
                    List<GetMuestraAparatosDisponiblesListResult> dat1 = itData1.next();
                    clveAparatoSpinner = dat1.get(position1 - 1).getClv_Aparato();
                    nombreSpinner = dat1.get(position1 - 1).getDescripcion();
                    serviciosAparato.setEnabled(true);
                } else {
                    clveAparatoSpinner = 0;
                    nombreSpinner = "";
                    Toast.makeText(getApplicationContext(), "Seleccione un aparato", Toast.LENGTH_LONG).show();
                    serviciosAparato.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        agragar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clvMACWAM = String.valueOf(MACWAMText.getText());

                if (clveAparatoSpinner == 0) {
                    Toast.makeText(getApplicationContext(), "Seleccione un aparato", Toast.LENGTH_LONG).show();
                } else {
                    if (selectedStrings.size() == 0) {
                        Toast.makeText(getApplicationContext(), "No se ha seleccionado nigun servicio", Toast.LENGTH_LONG).show();
                    } else {
                        if (request.MACWAM == true) {
                            if (clvMACWAM.equals("") == true) {
                                Toast.makeText(getApplicationContext(), "Escriba MACWAN", Toast.LENGTH_LONG).show();
                            } else {
                                if (clvMACWAM.equals(nombreSpinner) == false) {
                                    if (clvMACWAM.length() == 12) {
                                        JSONObject jsonObjectMACWAM = new JSONObject();
                                        listaDeMac.add(clveAparatoSpinner);
                                        try {
                                            jsonObjectMACWAM.put("Clv_Aparato", clveAparatoSpinner);
                                            jsonObjectMACWAM.put("MacLan", nombreSpinner);
                                            jsonObjectMACWAM.put("MacWan", MACWAMText.getText());
                                            jsonObjectMACWAM.put("Clv_Orden", clvor);


                                        } catch (Exception e) {
                                        }
                                        jsonArrayMAC.put(jsonObjectMACWAM);
                                        EjecutarAsignacion();

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "La MACWAN debe de ser 12 caracteres", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(getApplicationContext(), "La MACWAN no puede ser igual que la MacLan", Toast.LENGTH_SHORT).show();
                                }

                            }
                        } else {
                            EjecutarAsignacion();
                        }


                    }
                }


            }
        });
      /*  escanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator scanIntegrator = new IntentIntegrator(AsignarAparato.this);
                scanIntegrator.initiateScan();
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (scanningResult != null) {
            contents = data.getStringExtra("SCAN_RESULT");
            codigo.setText(contents);
            codigo.setVisibility(TextView.VISIBLE);
        }
    }



    public void EjecutarAsignacion() {
        Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = Array.dataArbSer.iterator();
        List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = itData4.next();
        for (int c = 0; c < dat4.size(); c++) {
            int asd=dat4.get(c).getClv_UnicaNet();
            for (int d = 0; d <= selectedStrings.size(); d++ ) {
                int abc ;
                try{
                    if (selectedStrings.get(d) == asd) {
                        children dataChild = new children();
                        dataChild.setBaseIdUser(0);
                        dataChild.setBaseRemoteIp(null);
                        dataChild.setClv_Aparato(clveAparatoSpinner);
                        dataChild.setClv_UnicaNet(null);
                        dataChild.setContratoNet(0);
                        dataChild.setDetalle(detalleSpinner);
                        dataChild.setNombre(nombreSpinner);
                        dataChild.setTipo("A");
                        dataChild.setType("file");
                        dat4.get(c).children.add(dataChild);
                        selectedStrings.remove(d);
                }
                }
                catch (Exception e){}


            }
        }
          Intent intento = new Intent(AsignarAparato.this, ReporteAsignacion.class);
        startActivity(intento);
        finish();
    }

    public JSONObject LlenarSpinnerTipoDeAparato(Integer clvUnicaNet,Integer idMedio,Integer idArticuloasignado){
        //datos arbol
        Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = array.dataArbSer.iterator();
        List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat = itData.next();
        JSONObject jsonObject1 = new JSONObject();
        //Se manda todas las ClvUnicaNet para mostrar todos los servicios servicio
        if(ServiciosAInstalar.todosLosMedios==1){
            try{
                //llenar array
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject2;
                for(int a=0; a< dat.size(); a++){
                    jsonObject2 = new JSONObject();
                    jsonObject2.put("Clv_UnicaNet",dat.get(a).Clv_UnicaNet);
                    jsonObject2.put("idMedio",dat.get(a).IdMedio);
                    jsonArray.put(a,jsonObject2);
                }
                //llenar lista para mandar request
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Id",idArticuloasignado);
                jsonObject1.put("obj",jsonObject);
                jsonObject1.put("Lst",jsonArray);

            }catch (Exception e){}
        }
        //Se manda solo una ClvUnicaNet para mostrar solo un servicio
        if(ServiciosAInstalar.todosLosMedios==0){
            try{
                //llenar array
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("Clv_UnicaNet",clvUnicaNet);
                jsonObject2.put("idMedio",idMedio);
                jsonArray.put(0,jsonObject2);


                //llenar lista para mandar request
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Id",idArticuloasignado);
                jsonObject1.put("obj",jsonObject);
                jsonObject1.put("Lst",jsonArray);

            }catch (Exception e){}
        }

        return jsonObject1;

    }


    public void onBackPressed() {
        Intent intento = new Intent(AsignarAparato.this, ReporteAsignacion.class);
        startActivity(intento);
        finish();
    }


    InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int i = start; i < end; ++i)
            {
                if (!Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890]*").matcher(String.valueOf(source.charAt(i))).matches())
                {
                    return "";
                }
            }
            return null;
        }
    };

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_asignacion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.siguiente:

                Intent intento = new Intent(AsignarAparato.this, ReporteAsignacion.class);
                startActivity(intento);
                finish();

                break;
        }
        return true;
    }

    public static int obtenerPosicionLista ( int clv_UnicaNet,Button agregar){
        int position = 0;
        //Arbol
        Iterator<List<GetMuestraServiciosRelTipoAparatoListResult>> itData = Array.dataserviciosAparatos.iterator();
            List<GetMuestraServiciosRelTipoAparatoListResult> dat = (List<GetMuestraServiciosRelTipoAparatoListResult>) itData.next();
        for (int i = 0; i < dat.size(); i++) {
            if (dat.get(i).clv_UnicaNet == clv_UnicaNet) {
                position = i;
            }
        }
        dat.get(position).baseIdUser = 1;
        selectedStrings.add(dat.get(position).clv_UnicaNet);
        agregar.setEnabled(true);
        agregar.setTextColor(Color.WHITE);
        return position;
    }

}