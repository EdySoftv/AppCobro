package com.Softv.SoftvApp.SoftvApp.Activitys;


import android.app.ProgressDialog;
import android.graphics.Color;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.Adapters.TrabajosAdapter;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Modelos.DeepConsModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListAparatosDisponiblesByIdArticuloResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListClienteAparatosResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListTipoAparatosByIdArticuloResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetSP_StatusAparatosListResult;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;




public class CambioAparato extends AppCompatActivity {

    public static Spinner aparato, estado,tipoAparato, aparatoAsignar;
    public static int idArticulo, contratoNetCam, idArticulo2, clvAparatoCAPAT;
    public static String statusAparato,nombreSpinnerCambioAparato;
    private Request request = new Request();
    ConstraintLayout aa;
    private Array array = new Array();
    private Button aceptarCambioAparato, Finish;
    public static ProgressDialog dialogCAPAT;
    boolean aparatoClienteValidar=false,tipoAparatoClienteValidar=false;
    public static EditText MACWAMTextCambioAparato;
    public static TextView textView35;
    JSONArray jsonArrayMACCambioAparato= new JSONArray();
    String clvMACWAMCambioAparato = "";

    @Override
    protected void onCreate(@Nullable final Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_cambio_aparato);
        aceptarCambioAparato =  findViewById(R.id.uno);

        aparato = findViewById(R.id.aparato);
        estado = findViewById(R.id.estadoaparato);
        tipoAparato = findViewById(R.id.tipo_aparato1);
        aparatoAsignar = findViewById(R.id.aparatoAsignar);
        aa=findViewById(R.id.aa);
        Finish= findViewById(R.id.dos);
        dialogCAPAT= new BarraCargar().showDialog(this);


        setTitle("No. de Orden: " + Util.getClvOrden(Util.preferences));
        textView35 = findViewById(R.id.textView35);
        MACWAMTextCambioAparato = findViewById(R.id.cambioaparatMacwan);
        //filtro para la macwan
        MACWAMTextCambioAparato.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(12)});

        request.getDeepCAPAT(getApplicationContext());
        if(TrabajosAdapter.ftth==0){
            aa.setVisibility(View.VISIBLE);
        }else{
            aa.setVisibility(View.GONE);
        }

if(DeepConsModel.STATUS.equals("E")){
    aparato.setEnabled(false);
    estado.setEnabled(false);
    tipoAparato.setEnabled(false);
    aparatoAsignar.setEnabled(false);
    aceptarCambioAparato.setEnabled(false);
    aceptarCambioAparato.setTextColor(Color.GRAY);
    MACWAMTextCambioAparato.setEnabled(false);
}else{
    aparato.setEnabled(true);
    estado.setEnabled(true);
    tipoAparato.setEnabled(true);
    aparatoAsignar.setEnabled(true);
    aceptarCambioAparato.setTextColor(Color.WHITE);
    aceptarCambioAparato.setEnabled(true);
    MACWAMTextCambioAparato.setEnabled(true);
}


        aparato.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position!=0){
                            Iterator<List<GetListClienteAparatosResult>> itdata = array.dataCliApa.iterator();
                            List<GetListClienteAparatosResult> dat = itdata.next();
                            if(TrabajosAdapter.ftth==1){
                                try {
                                    JSONObject jsonObject = new JSONObject();
                                    JSONObject jsonObject1 = new JSONObject();
                                    jsonObject.put("Letra", dat.get(position - 1).Letra);
                                    jsonObject1.put("ObjRelMacwan", jsonObject);
                                    request.ValidaMACWAM(getApplicationContext(), jsonObject1);
                                } catch (Exception e) {
                                }
                                idArticulo2 = dat.get(position-1).getIdArticulo();
                                contratoNetCam = dat.get(position-1).getControNet();
                                request.getApaTipDis(getApplicationContext());
                            }else{
                                idArticulo = dat.get(position-1).getIdArticulo();
                                contratoNetCam = dat.get(position-1).getControNet();
                                request.getApaTipo(getApplicationContext());
                            }

                        }else{

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
        estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Iterator<List<GetSP_StatusAparatosListResult>> itdata1 = Array.dataStaApa.iterator();
                    List<GetSP_StatusAparatosListResult> dat1 = itdata1.next();
                    statusAparato=dat1.get(position-1).getClv_StatusCableModem();
                }else{
                    statusAparato="";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tipoAparato.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    Iterator<List<GetListTipoAparatosByIdArticuloResult>> itdata = array.dataApaTipo.iterator();
                    List<GetListTipoAparatosByIdArticuloResult> dat = itdata.next();
                    idArticulo2 = dat.get(position-1).getIdArticulo();

                    request.getApaTipDis(getApplicationContext());
                    tipoAparatoClienteValidar=true;

                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) MACWAMTextCambioAparato.getLayoutParams();
                    layoutParams.height=aparato.getHeight();
                    MACWAMTextCambioAparato.setLayoutParams(layoutParams);


                }else{
                    tipoAparatoClienteValidar=false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        aparatoAsignar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Iterator<List<GetListAparatosDisponiblesByIdArticuloResult>> itdata1 = Array.dataApaTipDis.iterator();
                    List<GetListAparatosDisponiblesByIdArticuloResult> dat1 = itdata1.next();
                    clvAparatoCAPAT=dat1.get(position-1).getClv_Aparato();

                    try{
                        JSONObject jsonObject1 = new JSONObject();
                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject1.put("Clv_Aparato",dat1.get(position-1).Clv_Aparato);
                        jsonObject2.put("ObjRelMacwan",jsonObject1);
                        request.GetMACWAM(getApplicationContext(),jsonObject2);
                    }catch (Exception e){}
                    nombreSpinnerCambioAparato = dat1.get(position-1).Descripcion;
                    aparatoClienteValidar=true;
                }else{
                    clvAparatoCAPAT=0;
                    aparatoClienteValidar=false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        aceptarCambioAparato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aparato.getSelectedItemPosition()==0){
                    Toast.makeText(getApplicationContext(),"Seleccione el aparato a cambiar",Toast.LENGTH_SHORT).show();
                }else{
                    if(statusAparato.equals("")){
                        Toast.makeText(getApplicationContext(),"Seleccione el estado del aparato",Toast.LENGTH_SHORT).show();
                    }else{
                        if(TrabajosAdapter.ftth==0){
                            if(tipoAparatoClienteValidar==false){
                                Toast.makeText(getApplicationContext(),"Seleccione el tipo de aparato a asignar",Toast.LENGTH_SHORT).show();
                            }else{
                                if(aparatoClienteValidar==false){
                                    Toast.makeText(getApplicationContext(),"Seleccione el aparato a asignar",Toast.LENGTH_SHORT).show();
                                }else{
                                    if(request.MACWAM==true){
                                        if (MACWAMTextCambioAparato.equals("") == true) {
                                            Toast.makeText(getApplicationContext(), "Escriba MACWAN", Toast.LENGTH_LONG).show();
                                        } else {
                                            if (MACWAMTextCambioAparato.getText().toString().equals(nombreSpinnerCambioAparato) == false) {
                                                if (MACWAMTextCambioAparato.length() == 12) {
                                                    try {
                                                        JSONObject jsonObjectMACWAM = new JSONObject();
                                                        jsonObjectMACWAM.put("Clv_Aparato", clvAparatoCAPAT);
                                                        jsonObjectMACWAM.put("MacLan", nombreSpinnerCambioAparato);
                                                        jsonObjectMACWAM.put("MacWan", MACWAMTextCambioAparato.getText());
                                                        jsonObjectMACWAM.put("Clv_Orden",  Util.getClvOrden(Util.preferences));

                                                        jsonArrayMACCambioAparato.put(jsonObjectMACWAM);
                                                        JSONObject jsonObject = new JSONObject();
                                                        jsonObject.put("RelMacwanList",jsonArrayMACCambioAparato);
                                                        request.AsignaMACWAMCA(getApplicationContext(),jsonObject);
                                                    } catch (Exception e) {
                                                    }
                                                    try{
                                                        JSONObject jsonObject = new JSONObject();
                                                        JSONObject jsonObject1 = new JSONObject();
                                                        jsonObject.put("ClvAparato", clvAparatoCAPAT);
                                                        jsonObject.put("ClvOrden",  Util.getClvOrden(Util.preferences));
                                                        jsonObject.put("ContratoNet", contratoNetCam);
                                                        jsonObject.put("Status", statusAparato);
                                                        jsonObject.put("Trabajo", "CAPAT");
                                                        jsonObject.put("Clave", TrabajosAdapter.ClaveTrabajo);
                                                        jsonObject1.put("ObjCambioAparato", jsonObject);
                                                        request.SetCambioAparato(getApplicationContext(), jsonObject1,CambioAparato.this);
                                                        dialogCAPAT.show();
                                                    }catch (Exception e){}

                                                }else{
                                                    Toast.makeText(getApplicationContext(), "La MACWAN debe de ser 12 caracteres", Toast.LENGTH_SHORT).show();
                                                }
                                            }else{
                                                Toast.makeText(getApplicationContext(), "La MACWAN no puede ser igual que la MacLan", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }else{
                                        try{
                                            JSONObject jsonObject = new JSONObject();
                                            JSONObject jsonObject1 = new JSONObject();
                                            jsonObject.put("ClvAparato", clvAparatoCAPAT);
                                            jsonObject.put("ClvOrden",  Util.getClvOrden(Util.preferences));
                                            jsonObject.put("ContratoNet", contratoNetCam);
                                            jsonObject.put("Status", statusAparato);
                                            jsonObject.put("Trabajo", "CAPAT");
                                            jsonObject.put("Clave", TrabajosAdapter.ClaveTrabajo);
                                            jsonObject1.put("ObjCambioAparato", jsonObject);
                                            request.SetCambioAparato(getApplicationContext(), jsonObject1,CambioAparato.this);
                                            dialogCAPAT.show();
                                        }catch (Exception e){}
                                    }


/*                                    */
                                }
                            }
                        }else{
                            if(aparatoClienteValidar==false){
                                Toast.makeText(getApplicationContext(),"Seleccione el aparato a asignar",Toast.LENGTH_SHORT).show();
                            }else{
                                if(request.MACWAM==true){
                                    if (MACWAMTextCambioAparato.equals("") == true) {
                                        Toast.makeText(getApplicationContext(), "Escriba MACWAN", Toast.LENGTH_LONG).show();
                                    } else {
                                        if (MACWAMTextCambioAparato.getText().toString().equals(nombreSpinnerCambioAparato) == false) {
                                            if (MACWAMTextCambioAparato.length() == 12) {
                                                try {
                                                    JSONObject jsonObjectMACWAM = new JSONObject();
                                                    jsonObjectMACWAM.put("Clv_Aparato", clvAparatoCAPAT);
                                                    jsonObjectMACWAM.put("MacLan", nombreSpinnerCambioAparato);
                                                    jsonObjectMACWAM.put("MacWan", MACWAMTextCambioAparato.getText());
                                                    jsonObjectMACWAM.put("Clv_Orden",  Util.getClvOrden(Util.preferences));

                                                    jsonArrayMACCambioAparato.put(jsonObjectMACWAM);
                                                    JSONObject jsonObject = new JSONObject();
                                                    jsonObject.put("RelMacwanList",jsonArrayMACCambioAparato);
                                                    request.AsignaMACWAMCA(getApplicationContext(),jsonObject);
                                                } catch (Exception e) {
                                                }
                                                try{
                                                    JSONObject jsonObject = new JSONObject();
                                                    JSONObject jsonObject1 = new JSONObject();
                                                    jsonObject.put("ClvAparato", clvAparatoCAPAT);
                                                    jsonObject.put("ClvOrden",  Util.getClvOrden(Util.preferences));
                                                    jsonObject.put("ContratoNet", contratoNetCam);
                                                    jsonObject.put("Status", statusAparato);
                                                    jsonObject.put("Trabajo", "CAPAT");
                                                    jsonObject.put("Clave", TrabajosAdapter.ClaveTrabajo);
                                                    jsonObject1.put("ObjCambioAparato", jsonObject);
                                                    request.SetCambioAparato(getApplicationContext(), jsonObject1,CambioAparato.this);
                                                    dialogCAPAT.show();
                                                }catch (Exception e){}

                                            }else{
                                                Toast.makeText(getApplicationContext(), "La MACWAN debe de ser 12 caracteres", Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(getApplicationContext(), "La MACWAN no puede ser igual que la MacLan", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }else{
                                    try{
                                        JSONObject jsonObject = new JSONObject();
                                        JSONObject jsonObject1 = new JSONObject();
                                        jsonObject.put("ClvAparato", clvAparatoCAPAT);
                                        jsonObject.put("ClvOrden",  Util.getClvOrden(Util.preferences));
                                        jsonObject.put("ContratoNet", contratoNetCam);
                                        jsonObject.put("Status", statusAparato);
                                        jsonObject.put("Trabajo", "CAPAT");
                                        jsonObject.put("Clave", TrabajosAdapter.ClaveTrabajo);
                                        jsonObject1.put("ObjCambioAparato", jsonObject);
                                        request.SetCambioAparato(getApplicationContext(), jsonObject1,CambioAparato.this);
                                        dialogCAPAT.show();
                                    }catch (Exception e){}
                                }
                            }
                        }
                    }
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


        Finish.setOnClickListener(new View.OnClickListener() {
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
            }
        });

    }
    public static int obtenerPosicionAC(int abc){
            int position=0;
            Iterator<List<GetListClienteAparatosResult>> itdata = Array.dataCliApa.iterator();
            List<GetListClienteAparatosResult> dat = itdata.next();
            for(int i=0; i<dat.size(); i++){
                if(dat.get(i).Clv_Aparato==abc){
                    position = i+1;
                }
            }
            return position;
        }
        public static int obtenerPosicionSA( String abc){
            int position=0;
            Iterator<List<GetSP_StatusAparatosListResult>> itdata = Array.dataStaApa.iterator();
            List<GetSP_StatusAparatosListResult> dat = itdata.next();
        for(int i=0; i<dat.size(); i++){
            if(dat.get(i).Clv_StatusCableModem.equalsIgnoreCase(abc)){
                position = i+1;
            }
        }
        return position;
    }
    public static int obtenerPosicionTA(int abc){
        int position=0;
        Iterator<List<GetListTipoAparatosByIdArticuloResult>> itdata = Array.dataApaTipo.iterator();
        List<GetListTipoAparatosByIdArticuloResult> dat = itdata.next();
        for(int i=0; i<dat.size(); i++){
            if(dat.get(i).IdArticulo==(abc)){
                position = i+1;
            }
        }
        return position;
    }
    public static int obtenerPosicionA(int abc){
        int position=0;
        Iterator<List<GetListAparatosDisponiblesByIdArticuloResult>> itdata = Array.dataApaTipDis.iterator();
        List<GetListAparatosDisponiblesByIdArticuloResult> dat = itdata.next();
        for(int i=0; i<dat.size(); i++){
            if(dat.get(i).Clv_Aparato==(abc)){
                position = i+1;
            }
        }
        return position;
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
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
        return super.onKeyDown(keyCode, event);
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
