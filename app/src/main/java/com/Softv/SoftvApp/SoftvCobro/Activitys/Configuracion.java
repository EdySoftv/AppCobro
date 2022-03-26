package com.Softv.SoftvApp.SoftvCobro.Activitys;

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
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvCobro.R;
import com.Softv.SoftvApp.SoftvCobro.Request.Request;
import com.Softv.SoftvApp.SoftvCobro.sampledata.SplashActivity;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Util;


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
                    intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        navigationView.getMenu().setGroupVisible(R.id.Cobro,Request.PermCobro);
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
        Util.preferences = getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        Util.editor = Util.preferences.edit();
        int id = item.getItemId();

        if (id == R.id.Configuraciones) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        }else if (id == R.id.Saldo) {
            Intent intent1 = new Intent(Configuracion.this, Saldo.class);
            intent1.putExtra("dato", "TAP");
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();
        }else if (id == R.id.Pagos) {
            Intent intent1 = new Intent(Configuracion.this, Pagos.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
