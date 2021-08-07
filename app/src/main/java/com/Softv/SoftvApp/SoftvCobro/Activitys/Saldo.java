package com.Softv.SoftvApp.SoftvCobro.Activitys;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvCobro.Adapters.ClientesAdapter;
import com.Softv.SoftvApp.SoftvCobro.R;
import com.Softv.SoftvApp.SoftvCobro.Request.Request;
import com.Softv.SoftvApp.SoftvCobro.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Util;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Saldo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Request request = new Request();
    public static ClientesAdapter adaptercl;
    private Button contratob,telefonob,nombreb, sig, ant;
    public static RecyclerView clientList;
    public static boolean statusBusquedaContrato = false;
    private EditText contsearch,nombresearch, unoapellido, dosapellido ,telefonosearch;
    NavigationView barra;
    private TextView nombreTec, txt_ant, txt_pre;
    public static ProgressDialog dialog;
    BarraCargar barraCargar = new BarraCargar();
    private Request rqs=new Request();
    private View view;
    private int VariableBusqueda = 0;
    private ConstraintLayout Cont, Plac, Nomb;

    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_saldo);
        view = (View) findViewById(R.id.ContenidoSaldo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!isOnline()) {
            dialog.dismiss();
            EstatusInternet(getApplicationContext());
            Toast.makeText(getApplicationContext(), "No cuenta con conexión a Internet", Toast.LENGTH_LONG).show();
            // finish();

        }
        else{
            try{
                JSONObject jsonObjectDirecta = new JSONObject();
                JSONObject jsonObjectDirecta1 = new JSONObject();
                jsonObjectDirecta.put("Op",3);
                jsonObjectDirecta.put("idcompania",3);
                jsonObjectDirecta.put("ClvTecnicoMandar",Util.getClvTec(Util.preferences));
                jsonObjectDirecta1.put("obj",jsonObjectDirecta);
                request.getPermisosDirecta(getApplicationContext(),jsonObjectDirecta1);
            }catch (Exception e){}
            request.getReviews(Saldo.this,dialog,view,false,this);
        }

        dialog = new BarraCargar().showDialog(this);
        //dialog.show();

        txt_ant=findViewById(R.id.textView61);
        txt_pre=findViewById(R.id.textView65);

        Cont=findViewById(R.id.Contrato);
        Plac=findViewById(R.id.Placa);
        Nomb=findViewById(R.id.Nombre);

        clientList=findViewById(R.id.clienteslist);
        contsearch=findViewById(R.id.contsearchsaldo);
        contratob=findViewById(R.id.bcontrato);

        nombresearch=findViewById(R.id.nombresearchsaldo);
        unoapellido=findViewById(R.id.apellido1searchsaldo);
        dosapellido=findViewById(R.id.apellido2searchsaldo);
        nombreb=findViewById(R.id.bnombre);

        telefonosearch=findViewById(R.id.placasearchsaldo);
        telefonob=findViewById(R.id.bplaca);

        sig=findViewById(R.id.buttonizq);
        ant=findViewById(R.id.buttonder);

        barra = findViewById(R.id.nav_view);
        View barra1 = barra.getHeaderView(0);
        nombreTec=barra1.findViewById(R.id.tv_NombreTecnico);
        nombreTec.setText(Util.getNombreTecnicoPreference(Util.preferences));

        clientList.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Saldo.this);
        clientList.setLayoutManager(layoutManager);

        VaciarYOcultar(0);

        //Prueba();

        //Anterior//
        ant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VariableBusqueda == 0)
                    VariableBusqueda = 2;
                else if(VariableBusqueda == 1)
                    VariableBusqueda = 0;
                else if(VariableBusqueda == 2)
                    VariableBusqueda = 1;
                VaciarYOcultar(VariableBusqueda);
            }
        });

        //Siguiente//
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(VariableBusqueda == 0)
                    VariableBusqueda = 1;
                else if(VariableBusqueda == 1)
                    VariableBusqueda = 2;
                else if(VariableBusqueda == 2)
                    VariableBusqueda = 0;
                VaciarYOcultar(VariableBusqueda);
            }
        });

        //Busqueda de Contrato//
        contratob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if (contsearch.getText().toString().trim().equalsIgnoreCase("")){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Campo de orden vacío", Toast.LENGTH_SHORT);toast1.show();
                }else{
                    String contrato = contsearch.getText().toString();
                    rqs.getListClientesSaldo(getApplicationContext(), 1, contrato);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    clientList.setLayoutManager(layoutManager);
                    clientList.setAdapter(adaptercl);
                }
                dialog.dismiss();
            }
        });

        //Busqueda de Nombre//
        nombreb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if (nombresearch.getText().toString().trim().equalsIgnoreCase("") &&
                        unoapellido.getText().toString().trim().equalsIgnoreCase("") &&
                            dosapellido.getText().toString().trim().equalsIgnoreCase("")){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Al menos un campo debe estar lleno", Toast.LENGTH_SHORT);toast1.show();
                }else{
                    String nombre = "";
                    try{
                        nombre = nombre + nombresearch.getText().toString() + " ";
                    }catch (Exception e){
                        nombre = nombre + "";
                    }
                    try{
                        nombre = nombre + unoapellido.getText().toString() + " ";
                    }catch (Exception e){
                        nombre = nombre + "";
                    }
                    try{
                        nombre = nombre + dosapellido.getText().toString();
                    }catch (Exception e){
                        nombre = nombre + "";
                    }
                    rqs.getListClientesSaldo(getApplicationContext(), 2, nombre);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    clientList.setLayoutManager(layoutManager);
                    clientList.setAdapter(adaptercl);
                }
                dialog.dismiss();
            }
        });

        //Busqueda de Telefono//
        telefonob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                if (telefonosearch.getText().toString().trim().equalsIgnoreCase("")){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Campo de telefono vacío", Toast.LENGTH_SHORT);toast1.show();
                }else{
                    String telefono = telefonosearch.getText().toString();
                    rqs.getListClientesSaldo(getApplicationContext(), 3, telefono);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    clientList.setLayoutManager(layoutManager);
                    clientList.setAdapter(adaptercl);
                }
                dialog.dismiss();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().setGroupVisible(R.id.Cobro,Request.PermCobro);
        navigationView.setNavigationItemSelectedListener(this);


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intento1=new Intent(Saldo.this,Saldo.class);
            intento1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intento1);
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.Configuraciones) {
            Intent intent1 = new Intent(Saldo.this, Configuracion.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        }else if (id == R.id.Saldo) {
            Intent intent1 = new Intent(Saldo.this, Saldo.class);
            intent1.putExtra("dato", "TAP");
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public void EstatusInternet (Context ctx){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(getApplicationContext(), "Internet Activo", Toast.LENGTH_LONG).show();
        } else {
            dialogoSalidaInternet();

        }
    }

    private void dialogoSalidaInternet() {
        new AlertDialog.Builder(this)
                .setTitle("Sin conexión")
                .setMessage("Para continuar debes tener conexión a internet")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                .setNegativeButton("",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
    }

    private void VaciarYOcultar(int Variable){
        if(Variable == 0){
            Cont.setVisibility(View.VISIBLE);
            Plac.setVisibility(View.GONE);
            Nomb.setVisibility(View.GONE);
            txt_ant.setText("Nombre");
            txt_pre.setText("Placa");
        }else if(Variable == 1){
            Cont.setVisibility(View.GONE);
            Plac.setVisibility(View.VISIBLE);
            Nomb.setVisibility(View.GONE);
            txt_ant.setText("Contrato");
            txt_pre.setText("Nombre");
        }else if(Variable == 2){
            Cont.setVisibility(View.GONE);
            Plac.setVisibility(View.GONE);
            Nomb.setVisibility(View.VISIBLE);
            txt_ant.setText("Placa");
            txt_pre.setText("Contrato");
        }

        contsearch.setText("");

        nombresearch.setText("");
        unoapellido.setText("");
        dosapellido.setText("");

        telefonosearch.setText("");
    }

}
