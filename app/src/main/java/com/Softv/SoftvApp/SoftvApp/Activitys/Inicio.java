package com.Softv.SoftvApp.SoftvApp.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;
import com.google.firebase.iid.FirebaseInstanceId;


import org.json.JSONException;
import org.json.JSONObject;

import static com.Softv.SoftvApp.SoftvApp.Services.Services.clavequeja;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.clvorden;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.jsonTokenFirebase;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.opcion;


public class Inicio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private View view;
    private NavigationView barra;
    private DrawerLayout drawer;
    private Request request = new Request();
    private TextView nombreTec;
    private ProgressDialog dialogInicio;
    private Request rqs = new Request();


    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_inicio);
        view = (View) findViewById(R.id.ContenidoInicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        barra = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);

        dialogInicio = new BarraCargar().showDialog(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        dialogInicio.show();
        Util.preferences = getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        if (!isOnline()) {
            dialogInicio.dismiss();
            EstatusInternet(getApplicationContext());
           // Toast.makeText(getApplicationContext(), "No cuenta con conexión a Internet", Toast.LENGTH_LONG).show();
           // finish();

        }else{
            try{
                JSONObject jsonObjectDirecta = new JSONObject();
                JSONObject jsonObjectDirecta1 = new JSONObject();
                jsonObjectDirecta.put("Op",3);
                jsonObjectDirecta.put("idcompania",3);
                jsonObjectDirecta.put("ClvTecnicoMandar",Util.getClvTec(Util.preferences));
                jsonObjectDirecta1.put("obj",jsonObjectDirecta);
                request.getPermisosDirecta(getApplicationContext(),jsonObjectDirecta1);
            }catch (Exception e){}
            try{
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("clv_tecnico", Util.getClvTec(Util.preferences));
                request.getProximaCita(getApplicationContext(),jsonObject,view,dialogInicio,this);
                request.getOrdenes(getApplicationContext(),jsonObject,view,dialogInicio,this);
            }catch (Exception x){dialogInicio.dismiss();}
        }




        View barra1 = barra.getHeaderView(0);
        nombreTec = barra1.findViewById(R.id.tv_NombreTecnico);
        nombreTec.setText(Util.getNombreTecnicoPreference(Util.preferences));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        try{
            jsonTokenFirebase = new JSONObject();
            jsonTokenFirebase.put("clv_tecnico",Util.getClvTec(Util.preferences));
            jsonTokenFirebase.put("token",FirebaseInstanceId.getInstance().getToken());
            rqs.envioTokenTecnicoRequest(this,jsonTokenFirebase);
        }
        catch (JSONException e) { }


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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //  if (drawer.isDrawerOpen(GravityCompat.START)) {
        //    drawer.closeDrawer(GravityCompat.START);
        // } else {
        dialogoSalida(this);
        //}
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


    public void dialogoSalida(final Context ctx) {
        new AlertDialog.Builder(ctx)
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
                                ((Activity) ctx).finishAffinity();
                            }
                        }).show();
        //System.exit(0);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Util.preferences = getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        Util.editor = Util.preferences.edit();
        int id = item.getItemId();

        if (id == R.id.Inicio) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            Intent intento = new Intent(getApplicationContext(), Inicio.class);
            intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intento);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }

        } else if (id == R.id.Ordenes_menu) {
            dialogInicio.show();
            clvorden = 0;
            opcion = 1;
            Util.editor.putString("TipoDescarga", "O");
            Util.editor.commit();
            request.getListOrd(getApplicationContext());


        } else if (id == R.id.Reportes) {
            dialogInicio.show();
            clavequeja = 0;
            opcion = 1;
            Util.editor.putString("TipoDescarga", "Q");
            Util.editor.commit();
            request.getListQuejas(getApplicationContext());

        } else if (id == R.id.Configuraciones) {
            Intent intent1 = new Intent(Inicio.this, Configuracion.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();
        } else if (id == R.id.MenuCoordenadasNAP) {
            Toast.makeText(getApplicationContext(), "NAP", Toast.LENGTH_LONG).show();
    } else if (id == R.id.MenuCoordenadasTAP) {
            Toast.makeText(getApplicationContext(), "TAP", Toast.LENGTH_LONG).show();
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

}
