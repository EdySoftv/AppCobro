package com.example.pablo.prueba7.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.pablo.prueba7.Adapters.quejas_adapter_result;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;
import com.example.pablo.prueba7.sampledata.Util;

import static com.example.pablo.prueba7.Activitys.Inicio.pieChart;
import static com.example.pablo.prueba7.Services.Services.clavequeja;
import static com.example.pablo.prueba7.Services.Services.clvorden;
import static com.example.pablo.prueba7.Services.Services.cont;
import static com.example.pablo.prueba7.Services.Services.opcion;

public class Reportes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   private Request request = new Request();
   private ListView reportes;
   private Button breporte,bcontrato;
   private EditText reportesearch,contratosearch;

   private quejas_adapter_result adapterqueja;
    NavigationView barra;
    TextView nombreTec;
    public static ProgressDialog dialogReportes;
    BarraCargar barraCargar = new BarraCargar();
    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_reportes);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reportes=findViewById(R.id.listreporte);
        breporte=findViewById(R.id.breporte);
        bcontrato=findViewById(R.id.bcontrato);
        reportesearch=findViewById(R.id.reportesearch);
        contratosearch=findViewById(R.id.contsearch);
        barra = findViewById(R.id.nav_view);
        View barra1 = barra.getHeaderView(0);
        nombreTec=barra1.findViewById(R.id.tv_NombreTecnico);
        nombreTec.setText(Util.getNombreTecnicoPreference(Util.preferences));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        adapterqueja=new quejas_adapter_result(Reportes.this,Array.Queja,Array.nombreQ,Array.statusQ,Array.contratoQ,Array.Direccion);
        reportes.setAdapter(adapterqueja);    //Asignacion del adapatador a la listView
        dialogReportes= new BarraCargar().showDialog(this);
        barraCargar.terminarBarra();
        breporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reportesearch.getText().toString().trim().equalsIgnoreCase("")){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Campo de reporte vacío", Toast.LENGTH_SHORT);
                    Array.Queja.clear();
                    Array.nombreQ.clear();
                    Array.statusQ.clear();
                    Array.contratoQ.clear();
                    Array.Direccion.clear();
                    toast1.show();
                    clavequeja=0;
                    opcion=1;
                    request.getListQuejas(getApplicationContext());
                } else {
                    Array.Queja.clear();
                    Array.nombreQ.clear();
                    Array.statusQ.clear();
                    Array.contratoQ.clear();
                    Array.Direccion.clear();
                    opcion=2;
                    clavequeja=Integer.parseInt(reportesearch.getText().toString().toLowerCase().trim());
                    request.getListQuejas(getApplicationContext());
                    Toast toast1 =Toast.makeText(getApplicationContext(), "Reporte encontrado", Toast.LENGTH_SHORT);toast1.show();
                    reportes.setAdapter(adapterqueja);
                }
            }
        });
        //Busqueda de Contrato//
        bcontrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contratosearch.getText().toString().trim().equalsIgnoreCase("")){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Campo de contrato vacío", Toast.LENGTH_SHORT);
                    Array.Queja.clear();
                    Array.nombreQ.clear();
                    Array.statusQ.clear();
                    Array.contratoQ.clear();
                    Array.Direccion.clear();
                    clavequeja=0;
                    opcion=1;
                    request.getListQuejas(getApplicationContext());
                    toast1.show();
                } else {
                    Array.Queja.clear();
                    Array.nombreQ.clear();
                    Array.statusQ.clear();
                    Array.contratoQ.clear();
                    Array.Direccion.clear();
                    opcion=3;
                    cont=(contratosearch.getText().toString().toLowerCase().trim());
                    request.getListQuejas(getApplicationContext());
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Contrato encontrado", Toast.LENGTH_SHORT);toast1.show();
                    reportes.setAdapter(adapterqueja);
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intento1=new Intent(Reportes.this,Inicio.class);
            startActivity(intento1);
        }

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.Inicio) {
            dialogReportes.show();
            request.getOrdenes(getBaseContext());
        } else if (id == R.id.Ordenes_menu) {
            dialogReportes.show();
            clvorden=0;
            opcion=1;
            request.getListOrd(getApplicationContext());
        } else if (id == R.id.Reportes) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        } else if (id == R.id.Configuraciones) {
            Intent intent1 = new Intent(Reportes.this, Configuracion.class);
            startActivity(intent1);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

