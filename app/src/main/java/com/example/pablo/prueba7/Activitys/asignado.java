package com.example.pablo.prueba7.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.GetMuestraAparatosDisponiblesListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraServiciosRelTipoAparatoListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraTipoAparatoListResult;
import com.example.pablo.prueba7.Modelos.children;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import static com.example.pablo.prueba7.Adapters.ordenes_adapter_result.clvor;

public class asignado extends AppCompatActivity {

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
        setContentView(R.layout.activity_asignado);
        spinnerAparato = findViewById(R.id.tipo_aparato);
        spinneraparatoDisponible = findViewById(R.id.aparatoDisponible);
        serviciosAparato = findViewById(R.id.Servicios123);
        agragar = findViewById(R.id.agregarAsignacionAparato);
        cancelar = findViewById(R.id.cancelarAsignacionAparato);
        constraintLayoutMACWAM = findViewById(R.id.MACWAMConstraint);
        MACWAMText = findViewById(R.id.MacWam);
        MACWAMText.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(12)});
        request.getTipoAparatos(getApplicationContext());
        constraintLayoutMACWAM = findViewById(R.id.MACWAMConstraint);
        MACWAMText = findViewById(R.id.MacWam);
        MACWAMText.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(12)});
        selectedStrings.clear();
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(asignado.this, asignacion.class);
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
                    idArticuloasignado = dat.get(position - 1).getIdArticulo();
                    request.getAparatosDisponibles(getApplicationContext());
                    request.getServiciosAparatos(getApplicationContext());
                    JSONObject jsonObject = new JSONObject();
                    JSONObject jsonObject1 = new JSONObject();
                    try {
                        jsonObject.put("Letra", dat.get(position - 1).letra);
                        jsonObject1.put("ObjRelMacwan", jsonObject);
                    } catch (Exception e) {
                    }
                    request.ValidaMACWAM(getApplicationContext(), jsonObject1);
                    serviciosAparato.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    serviciosAparato.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                            Iterator<List<GetMuestraServiciosRelTipoAparatoListResult>> itData2 = array.dataserviciosAparatos.iterator();
                            List<GetMuestraServiciosRelTipoAparatoListResult> dat2 = itData2.next();

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
                                } else {
                                    selectedStrings.remove(dat2.get(position1).clv_UnicaNet);
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
                        Toast.makeText(getApplicationContext(), "No se ha seleccionado nigun medio", Toast.LENGTH_LONG).show();
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
                IntentIntegrator scanIntegrator = new IntentIntegrator(asignado.this);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intento = new Intent(asignado.this, asignacion.class);
            startActivity(intento);
            finish();
        }
        return true;
    }

    public void EjecutarAsignacion() {
        Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = Array.dataArbSer.iterator();
        List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = itData4.next();
        for (int c = 0; c < dat4.size(); c++) {
            int asd;
            for (int d = 0; d <= selectedStrings.size(); d++ ) {
                int abc ;
                try{
                    if (selectedStrings.get(d) == dat4.get(c).getClv_UnicaNet()) {
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
        asignacion.aceptarAsignacion.setVisibility(View.VISIBLE);

        Intent intento = new Intent(asignado.this, asignacion.class);
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
}