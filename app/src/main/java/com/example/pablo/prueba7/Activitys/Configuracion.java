package com.example.pablo.prueba7.Activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.SplashActivity;
import com.example.pablo.prueba7.sampledata.Util;



public class Configuracion extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button CS;
    private TextView nombreConfi;
    private Request request = new Request();
    NavigationView barra;
    TextView nombreTec;

    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_configuracion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CS = (Button)findViewById(R.id.btnCerrarSesion);
        nombreConfi = findViewById(R.id.nombreTecnico);
        barra = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        View barra1 = barra.getHeaderView(0);
        nombreTec=barra1.findViewById(R.id.tv_NombreTecnico);
        nombreTec.setText(Util.getNombreTecnicoPreference(Util.preferences));
        //Boton para cerrar sesion
        nombreConfi.setText(Util.getNombreTecnicoPreference(Util.preferences));
        CS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Util.preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                    Util.preferences.edit().clear().commit();
                    SplashActivity.LoginShare=false;
                    Intent intento = new Intent(Configuracion.this, Login.class);
                    startActivity(intento);
                }
                catch (Exception e){

                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            dialogoSalida();
        }
    }
    public void dialogoSalida() {
        new AlertDialog.Builder(this)
                .setTitle("SALIR")
                .setMessage("¿Desea salir de la aplicación?")
                .setPositiveButton("CANCELAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                .setNegativeButton("ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Inicio) {
            //Actualizar la siguente cita y la grafica
           request.getOrdenes(getApplicationContext());

        } else if (id == R.id.Ordenes_menu) {
            Intent intent1 = new Intent(Configuracion.this, Orden.class);
            startActivity(intent1);

        } else if (id == R.id.Reportes) {
            Intent intent1 = new Intent(Configuracion.this, Reportes.class);
            startActivity(intent1);

        } else if (id == R.id.Configuraciones) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
