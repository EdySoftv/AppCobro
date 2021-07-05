package com.Softv.SoftvApp.SoftvApp.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.Adapters.DetallesAdapter;
import com.Softv.SoftvApp.SoftvApp.Adapters.QuejasAdapter;
import com.Softv.SoftvApp.SoftvApp.Adapters.ServiciosAdapter;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONObject;

import static com.Softv.SoftvApp.SoftvApp.Listas.Array.statusQ;


public class ServiciosSaldo extends AppCompatActivity {
    TextView ContratoCompuesto, Nombre, Telefono, Calle_Numero_Colonia, Contrato, FechaConsulta, Total;
    public Button cerrarSesion,detalleSaldo;
    View view;
    private Request rqs=new Request();
    private ServiciosAdapter ServAdapter;
    private DetallesAdapter DetAdapter;
    private RecyclerView Servicios, Detalles;
    private ConstraintLayout servicios, detalles;
    private static int vis = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        vis = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios_saldo);
        view = findViewById(R.id.ContenidoInicio);
        detalleSaldo = findViewById(R.id.detallesaldo);
        cerrarSesion =  findViewById(R.id.cerrar);

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

        ContratoCompuesto.setText(rqs.ContratoCompuestoSaldo);
        Nombre.setText(rqs.NombreSaldo);
        Telefono.setText(rqs.TelefonoSaldo);
        Calle_Numero_Colonia.setText(rqs.Calle_NumeroSaldo + " " + rqs.ColoniaSaldo );
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



        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoSalida(getApplicationContext());
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
                                    rqs.GuardarPago(ctx, jsonObject);
                                    //dialogoGuardar(ctx);
                                }catch (Exception x){
                                    Toast toast2 = Toast.makeText(ctx, "Error al registrar el pago", Toast.LENGTH_SHORT);
                                    toast2.show();
                                }
                            }
                        }).show();
        //System.exit(0);
    }

    public void dialogoGuardar(final Context ctx) {
        rqs.Monto = 0;
        rqs.Session = 0;
        //rqs.CLV_FACTURA = 0 ;
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Clv_Factura", rqs.CLV_FACTURA);
            rqs.GetTicketNom(ctx, jsonObject);
        }catch (Exception x){
            Toast toast2 = Toast.makeText(ctx, "Error al registrar el pago", Toast.LENGTH_SHORT);
            toast2.show();
        }

    }

}
