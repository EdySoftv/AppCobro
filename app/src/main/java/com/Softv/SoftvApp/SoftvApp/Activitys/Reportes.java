package com.Softv.SoftvApp.SoftvApp.Activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.Softv.SoftvApp.SoftvApp.Adapters.QuejasAdapter;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import static com.Softv.SoftvApp.SoftvApp.Listas.Array.statusQ;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.clavequeja;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.clvorden;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.cont;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.opcion;

public class Reportes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   private Request request = new Request();
   private RecyclerView reportes;
   private Button breporte,bcontrato;
   private EditText reportesearch,contratosearch;
    public static boolean statusBusquedaReporte = false,statusBusquedaContRepo = false;
   private QuejasAdapter adapterqueja;
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
        adapterqueja=new QuejasAdapter(Reportes.this,Array.Queja,Array.nombreQ, statusQ,Array.contratoQ,Array.Direccion);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        reportes.setLayoutManager(layoutManager);
        reportes.setAdapter(adapterqueja);    //Asignacion del adapatador a la listView
        dialogReportes= new BarraCargar().showDialog(this);
        barraCargar.terminarBarra();
        breporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusBusquedaReporte = true;
                if (reportesearch.getText().toString().trim().equalsIgnoreCase("")){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Campo de reporte vacío", Toast.LENGTH_SHORT);
                    Array.Queja.clear();
                    Array.nombreQ.clear();
                    statusQ.clear();
                    Array.contratoQ.clear();
                    Array.Direccion.clear();
                    toast1.show();
                    clavequeja=0;
                    opcion=1;
                    request.getListQuejas(getApplicationContext());
                } else {
                    Array.Queja.clear();
                    Array.nombreQ.clear();
                    statusQ.clear();
                    Array.contratoQ.clear();
                    Array.Direccion.clear();
                    opcion=2;
                    clavequeja=Integer.parseInt(reportesearch.getText().toString().toLowerCase().trim());
                    request.getListQuejas(getApplicationContext());
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
                    reportes.setLayoutManager(layoutManager);
                    reportes.setAdapter(adapterqueja);
                }


            }
        });
        //Busqueda de Contrato//
        bcontrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusBusquedaContRepo = true;
                if (contratosearch.getText().toString().trim().equalsIgnoreCase("")){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Campo de contrato vacío", Toast.LENGTH_SHORT);
                    Array.Queja.clear();
                    Array.nombreQ.clear();
                    statusQ.clear();
                    Array.contratoQ.clear();
                    Array.Direccion.clear();
                    clavequeja=0;
                    opcion=1;
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
                    reportes.setLayoutManager(layoutManager);
                    request.getListQuejas(getApplicationContext());
                    toast1.show();
                } else {
                    Array.Queja.clear();
                    Array.nombreQ.clear();
                    statusQ.clear();
                    Array.contratoQ.clear();
                    Array.Direccion.clear();
                    opcion=3;
                    cont=(contratosearch.getText().toString().toLowerCase().trim());
                    request.getListQuejas(getApplicationContext());
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
                    reportes.setLayoutManager(layoutManager);
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
            intento1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intento1);
        }

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.Inicio) {
            Intent intento = new Intent(getApplicationContext(), Inicio.class);
            intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intento);

        } else if (id == R.id.Ordenes_menu) {
            Util.preferences = getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            Util.editor = Util.preferences.edit();
            Util.editor.putString("TipoDescarga", "O");
            Util.editor.commit();
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
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        }else if (id == R.id.MenuCoordenadasNAP) {
            Intent intent1 = new Intent(Reportes.this, NAPTAP.class);
            intent1.putExtra("dato", "NAP");
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();
        } else if (id == R.id.MenuCoordenadasTAP) {
            Intent intent1 = new Intent(Reportes.this, NAPTAP.class);
            intent1.putExtra("dato", "TAP");
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

