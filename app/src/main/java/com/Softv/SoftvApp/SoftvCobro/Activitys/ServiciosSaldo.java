package com.Softv.SoftvApp.SoftvCobro.Activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvCobro.Adapters.DetallesAdapter;
import com.Softv.SoftvApp.SoftvCobro.Adapters.ServiciosAdapter;
import com.Softv.SoftvApp.SoftvCobro.Listas.Array;
import com.Softv.SoftvApp.SoftvCobro.R;
import com.Softv.SoftvApp.SoftvCobro.Request.Request;
import com.Softv.SoftvApp.SoftvCobro.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Util;

import org.json.JSONObject;


public class ServiciosSaldo extends AppCompatActivity {
    TextView ContratoCompuesto, Nombre, Telefono, Calle_Numero_Colonia, Contrato, Placa, FechaConsulta, Total;
    public Button cerrarSesion,detalleSaldo, abono;
    View view;
    private Request rqs=new Request();
    private ServiciosAdapter ServAdapter;
    private DetallesAdapter DetAdapter;
    private RecyclerView Servicios, Detalles;
    private ConstraintLayout servicios, detalles;

    //Area de Serie y Folio
    private ConstraintLayout SerieYFolio;
    private TextView TextViewVendedor, TextViewSerie, TextViewFolioTv, TextViewFolioInternet;
    private Spinner SpinnerVendedor, SpinnerSerie, SpinnerFolioTv, SpinnerFolioInternet;
    private String Tipo_json = "C";
    private int Clv_Vendedor_json = 0;
    private String Serie_V_json = "";
    private int Folio_V_json = 0;
    private int Folio_VInt_json = 0;

    private static int vis = 0;
    private ProgressDialog dialogInicio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        vis = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios_saldo);
        view = findViewById(R.id.ContenidoInicio);

        dialogInicio = new BarraCargar().showDialog(this);

        Util.preferences = getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        Util.editor = Util.preferences.edit();

        detalleSaldo = findViewById(R.id.detallesaldo);
        cerrarSesion =  findViewById(R.id.cerrar);
        abono = findViewById(R.id.adelantar);
        abono.setVisibility(View.GONE);
        servicios =  findViewById(R.id.ListaDeServicios);
        detalles =  findViewById(R.id.ListaDeDetalles);
        servicios.setVisibility(View.VISIBLE);
        detalles.setVisibility(View.GONE);
        detalleSaldo.setText("Detalle");

        Servicios = findViewById(R.id.listaServicios);
        Detalles = findViewById(R.id.listaDetalles);

        FechaConsulta = findViewById(R.id.consultadoDia);
        Total = findViewById(R.id.saldo);

        ContratoCompuesto =  findViewById(R.id.contrato);
        Nombre =  findViewById(R.id.nombre);
        Telefono =  findViewById(R.id.telefono);
        Calle_Numero_Colonia =  findViewById(R.id.direccion);
        Contrato =  findViewById(R.id.contrato);
        Placa =  findViewById(R.id.placa);

        SerieYFolio = findViewById(R.id.SerieYFolio);
        TextViewVendedor = findViewById(R.id.TextViewVendedor);
        TextViewSerie = findViewById(R.id.TextViewSerie);
        TextViewFolioTv = findViewById(R.id.TextViewFolioTv);
        TextViewFolioInternet = findViewById(R.id.TextViewFolioInternet);
        SpinnerVendedor = findViewById(R.id.SpinnerVendedor);
        SpinnerSerie = findViewById(R.id.SpinnerSerie);
        SpinnerFolioTv = findViewById(R.id.SpinnerFolioTv);
        SpinnerFolioInternet = findViewById(R.id.SpinnerFolioInternet);

        ContratoCompuesto.setText(rqs.ContratoCompuestoSaldo);
        Nombre.setText(rqs.NombreSaldo);
        Telefono.setText(rqs.TelefonoSaldo);
        Calle_Numero_Colonia.setText(rqs.Calle_NumeroSaldo + " " + rqs.ColoniaSaldo );
        Placa.setText(rqs.PlacaSaldo);
        FechaConsulta.setText(rqs.FechaCosultaSaldo);
        Total.setText(rqs.TotalSaldo);
        //Contrato.setText(rqs.ContratoSaldo);
        //ContratoCompuestoSaldo,NombreSaldo,TelefonoSaldo,Calle_NumeroSaldo,ColoniaSaldo,ContratoSaldo


        Servicios.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ServiciosSaldo.this);
        ServAdapter=new ServiciosAdapter(ServiciosSaldo.this, Array.ServicioSaldo,Array.StatusSaldo);
        Servicios.setLayoutManager(layoutManager);
        Servicios.setAdapter(ServAdapter);

        Detalles.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ServiciosSaldo.this);
        DetAdapter=new DetallesAdapter(ServiciosSaldo.this, Array.DescripcionSaldo,Array.OperacionSaldo,Array.MontoSaldo);
        Detalles.setLayoutManager(layoutManager);
        Detalles.setAdapter(DetAdapter);

        final int Clv_Tecnico = Util.getClvTec(Util.preferences);
        final int Session = Integer.valueOf(rqs.Session);
        final int Contrato = Integer.valueOf(rqs.ContratoSaldo);
        final int Clave = Util.getClvUsuario(Util.preferences);
        final String Serie = Util.getUsuarioPreference(Util.preferences);

        if(rqs.PermEii == true && Session > 0){
            dialogInicio.show();
            rqs.ValidarVendedor(dialogInicio, getApplicationContext(), SerieYFolio, TextViewVendedor, TextViewSerie, TextViewFolioTv, TextViewFolioInternet, SpinnerVendedor, SpinnerSerie, SpinnerFolioTv, SpinnerFolioInternet, Clv_Tecnico, Session, Contrato, Serie, Clave);
            //SerieYFolio.setVisibility(View.VISIBLE);
        }else{
            SerieYFolio.setVisibility(View.GONE);
        }

        SpinnerVendedor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (position != 0) {
                    dialogInicio.show();
                    SpinnerSerie.setSelection(0);
                    Array.FolioDisponibleIntLista.clear();
                    Array.FolioDisponibleIntLista.add("<Seleccionar>");
                    SpinnerFolioTv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Array.FolioDisponibleIntLista));
                    SpinnerFolioTv.setSelection(0);
                    SpinnerFolioInternet.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Array.FolioDisponibleIntLista));
                    SpinnerFolioInternet.setSelection(0);
                    //Array.dataPostes.get(0).get(SpinnerPoste.getSelectedItemPosition()-1).getID())
                    int Clv_Vendedor = Array.dataVendedores.get(0).get(position-1).getClv_Vendedor();
                    Clv_Vendedor_json = Clv_Vendedor;
                    JSONObject jsonObject = new JSONObject();
                    try{
                        jsonObject.put("CLV_VENDEDOR", Clv_Vendedor);
                        jsonObject.put("CONTRATO", Contrato);
                        rqs.UltimoSerieYFolio(dialogInicio, getApplicationContext(),jsonObject,SpinnerSerie,SpinnerFolioTv,SpinnerFolioInternet);
                    }catch (Exception x){
                    }

                }else{

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        SpinnerSerie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (position != 0) {
                    dialogInicio.show();
                    SpinnerFolioTv.setSelection(0);
                    SpinnerFolioInternet.setSelection(0);
                    JSONObject jsonObject = new JSONObject();
                    //constrain_Clasificacion_spinner.getSelectedItemPosition()
                    //Serie_V_json = SpinnerSerie.toString();
                    Serie_V_json = Array.dataUltimoSerieYFolio.get(0).get(position-1).getSerie();
                    try{
                        jsonObject.put("CLV_VENDEDOR", Array.dataVendedores.get(0).get(SpinnerVendedor.getSelectedItemPosition()-1).getClv_Vendedor());
                        jsonObject.put("SERIE", Array.dataUltimoSerieYFolio.get(0).get(position-1).getSerie());
                        jsonObject.put("CONTRATO", Contrato);
                        if(rqs.ServicioVentas == 25){
                            rqs.Folio_DisponibleRecu(dialogInicio, getApplicationContext(),jsonObject,SpinnerFolioTv,SpinnerFolioInternet);
                        }else{
                            rqs.Folio_Disponible(dialogInicio, getApplicationContext(),jsonObject,SpinnerFolioTv,SpinnerFolioInternet);
                        }
                    }catch (Exception x){
                    }

                }else{

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        SpinnerFolioTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (position != 0) {
                    SpinnerFolioInternet.setSelection(0);
                    Folio_VInt_json = Integer.parseInt(SpinnerFolioTv.toString());
                }else{

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        SpinnerFolioInternet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (position != 0) {
                    Folio_V_json = Integer.parseInt(SpinnerFolioInternet.toString());
                }else{

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rqs.PermEii == true && rqs.Vendedor > 0){
                    if(rqs.ServicioVentas == 1){
                        if(SpinnerVendedor.getSelectedItemPosition() >= 1){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione vendedor", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerSerie.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione serie", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerFolioTv.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione folio de Tv", Toast.LENGTH_SHORT);toast1.show();
                        }else{
                            //Toast toast1 = Toast.makeText(getApplicationContext(), "Todo bien", Toast.LENGTH_SHORT);toast1.show();
                            dialogoSalida(getApplicationContext());
                        }
                    }else if(rqs.ServicioVentas == 2){
                        if(SpinnerVendedor.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione vendedor", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerSerie.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione serie", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerFolioInternet.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione folio de Internet", Toast.LENGTH_SHORT);toast1.show();
                        }else{
                            //Toast toast1 = Toast.makeText(getApplicationContext(), "Todo bien", Toast.LENGTH_SHORT);toast1.show();
                            dialogoSalida(getApplicationContext());
                        }
                    }else if(rqs.ServicioVentas == 12){
                        if(SpinnerVendedor.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione vendedor", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerSerie.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione serie", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerFolioTv.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione folio de Tv", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerFolioInternet.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione folio de Internet", Toast.LENGTH_SHORT);toast1.show();
                        }else{
                            //Toast toast1 = Toast.makeText(getApplicationContext(), "Todo bien", Toast.LENGTH_SHORT);toast1.show();
                            dialogoSalida(getApplicationContext());
                        }
                    }else if(rqs.ServicioVentas == 25){
                        if(SpinnerVendedor.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione vendedor", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerSerie.getSelectedItemPosition() > 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione serie", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerFolioTv.getSelectedItemPosition() > 0){//recuperacion
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione folio de Recuperación", Toast.LENGTH_SHORT);toast1.show();
                        }else{
                            //Toast toast1 = Toast.makeText(getApplicationContext(), "Todo bien", Toast.LENGTH_SHORT);toast1.show();
                            dialogoSalida(getApplicationContext());
                        }
                    }else{
                        if(SpinnerVendedor.getSelectedItemPosition() == 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione vendedor", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerSerie.getSelectedItemPosition() == 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione serie", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerFolioTv.getSelectedItemPosition() == 0){//recuperacion
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione folio de Recuperación", Toast.LENGTH_SHORT);toast1.show();
                        }else if(SpinnerFolioInternet.getSelectedItemPosition() == 0){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione folio de Internet", Toast.LENGTH_SHORT);toast1.show();
                        }else{
                            //Toast toast1 = Toast.makeText(getApplicationContext(), "Todo bien", Toast.LENGTH_SHORT);toast1.show();
                            dialogoSalida(getApplicationContext());
                        }
                    }
                }else{
                    //Toast toast1 = Toast.makeText(getApplicationContext(), "Todo bien", Toast.LENGTH_SHORT);toast1.show();
                    dialogoSalida(getApplicationContext());
                }
            }
        });


        detalleSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vis == 0){
                    servicios.setVisibility(View.GONE);
                    detalles.setVisibility(View.VISIBLE);
                    detalleSaldo.setText("Servicio");
                    vis = 1;
                }else{
                    servicios.setVisibility(View.VISIBLE);
                    detalles.setVisibility(View.GONE);
                    detalleSaldo.setText("Detalle");
                    vis = 0;
                }
            }
        });

        abono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento25 = new Intent(getApplicationContext(), ServiciosAInstalar.class);
                intento25.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intento25);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    public void dialogoSalida(final Context ctx) {
        new AlertDialog.Builder(this)
                .setTitle("CONFIRMAR PAGO")
                .setMessage("¿Está seguro que desea registrar el cobro de los servicios?")
                .setPositiveButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                .setNegativeButton("CONFIRMAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast toast1 = Toast.makeText(ctx, "CONFIRMADO", Toast.LENGTH_SHORT);toast1.show();
                                try{
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("Clv_Session", Integer.valueOf(rqs.Session));
                                    jsonObject.put("Contrato", Integer.valueOf(rqs.ContratoSaldo));
                                    jsonObject.put("ClvUsuario", Util.getUsuarioPreference(Util.preferences));
                                    jsonObject.put("Saldo", rqs.TotalSaldo);
                                    jsonObject.put("Tipo", Tipo_json);
                                    jsonObject.put("Serie_V", Serie_V_json);
                                    jsonObject.put("Folio_V", Folio_V_json);
                                    jsonObject.put("Clv_Vendedor", Clv_Vendedor_json);
                                    jsonObject.put("Folio_VInt", Folio_VInt_json);
                                    dialogInicio.show();
                                    rqs.GuardarPago(ctx, jsonObject,dialogInicio);
                                    //dialogoGuardar(ctx);
                                }catch (Exception x){
                                    Toast toast2 = Toast.makeText(ctx, "Error al registrar el pago", Toast.LENGTH_SHORT);
                                    toast2.show();
                                }
                            }
                        }).show();
        //System.exit(0);
    }



}
