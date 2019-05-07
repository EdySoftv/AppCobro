package com.example.pablo.prueba7.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;
import com.example.pablo.prueba7.sampledata.SplashActivity;
import com.example.pablo.prueba7.sampledata.Util;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;

import static com.example.pablo.prueba7.Services.Services.clavequeja;
import static com.example.pablo.prueba7.Services.Services.clvorden;
import static com.example.pablo.prueba7.Services.Services.opcion;


public class Inicio extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int OE, OP, OV, OEP, OO, RE, RP, REP, RV, RO;
    public static String tipodeDescarga;
    NavigationView barra;
    public static DrawerLayout drawer;
    public static PieChart pieChart;
    static SharedPreferences preferences;
    private Request request = new Request();
    public static TextView tipoTrabajo, contratoTrabajo, horaTrabajo, calleDireccion, numeroDireccion, coloniaDireccion, nombreTec;
    public static ProgressDialog dialogInicio;
    BarraCargar barraCargar = new BarraCargar();

    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_inicio);

        preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        pieChart = (PieChart) findViewById(R.id.graficaPastel);
        tipoTrabajo = (TextView) findViewById(R.id.tipoDeTrabajo);
        contratoTrabajo = (TextView) findViewById(R.id.contrato);
        horaTrabajo = (TextView) findViewById(R.id.hora);
        calleDireccion = (TextView) findViewById(R.id.calle);
        numeroDireccion = (TextView) findViewById(R.id.numero);
        coloniaDireccion = (TextView) findViewById(R.id.colonia);
        barra = findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        dialogInicio = new BarraCargar().showDialog(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        try {
            if (getIntent().getStringExtra("Reiniciar").equals("1")) {
                dialogoReinicio();
            }
        } catch (Exception e) {
            Inicar();

        }

        barraCargar.terminarBarra();
        View barra1 = barra.getHeaderView(0);
        nombreTec = barra1.findViewById(R.id.tv_NombreTecnico);
        nombreTec.setText(Util.getNombreTecnicoPreference(preferences));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tipoTrabajo.setText(request.sigueinteTipo);
        contratoTrabajo.setText(request.siguenteContrato);
        horaTrabajo.setText(request.sigueinteHora);
        calleDireccion.setText(request.siguenteCalle);
        numeroDireccion.setText(request.sigueinteNumero);
        coloniaDireccion.setText(request.siguenteColonia);

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

    public void dialogoReinicio() {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Error al inciar aplicación")
                .setPositiveButton("Intentar otra vez",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Inicar();
                            }
                        })
                .setNegativeButton("Cerrar aplicación",
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
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            request.getOrdenes(getBaseContext());


            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }

        } else if (id == R.id.Ordenes_menu) {
            dialogInicio.show();
            clvorden = 0;
            opcion = 1;
            tipodeDescarga = "O";
            request.getListOrd(getApplicationContext());


        } else if (id == R.id.Reportes) {
            dialogInicio.show();
            clavequeja = 0;
            opcion = 1;
            tipodeDescarga = "Q";
            request.getListQuejas(getApplicationContext());

        } else if (id == R.id.Configuraciones) {
            Intent intent1 = new Intent(Inicio.this, Configuracion.class);
            startActivity(intent1);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Grafica de pastel
    public static void Grafica(PieChart pieChart) {
        //Propiedades de la grafica
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(1f);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(android.R.color.white);
        pieChart.setTransparentCircleRadius(1f);

        //Datos de la grafica
        ArrayList<PieEntry> yValues = new ArrayList<>();
        if (Util.OE(preferences) == 0 && Util.OP(preferences) == 0 && Util.OV(preferences) == 0 && Util.RP(preferences) == 0 && Util.OEP(preferences) == 0 && Util.OO(preferences) == 0 && Util.RE(preferences) == 0 && Util.RV(preferences) == 0 && Util.REP(preferences) == 0 && Util.RO(preferences) == 0) {

            yValues.add(new PieEntry(100f, "Completado"));
            PieDataSet dataSet = new PieDataSet(yValues, "");
            dataSet.setSliceSpace(7f);
            dataSet.setSelectionShift(10f);
            dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            dataSet.setHighlightEnabled(true);
            PieData data = new PieData((dataSet));
            data.setValueTextSize(15f);
            data.setValueTextColor(Color.BLACK);
            pieChart.setData(data);

        } else {
            if (Util.OE(preferences) != 0) {
                yValues.add(new PieEntry(Util.OE(preferences), "OrdenEjecutada"));
            }
            if (Util.OP(preferences) != 0) {
                yValues.add(new PieEntry(Util.OP(preferences), "OrdenPendiente"));
            }
            if (Util.OV(preferences) != 0) {
                yValues.add(new PieEntry(Util.OV(preferences), "OrdenEnVisita"));
            }
            if (Util.OEP(preferences) != 0) {
                yValues.add(new PieEntry(Util.OEP(preferences), "OrdenEnProceso"));
            }
            if (Util.OO(preferences) != 0) {
                yValues.add(new PieEntry(Util.OO(preferences), "Otros"));
            }
            if (Util.RE(preferences) != 0) {
                yValues.add(new PieEntry(Util.RE(preferences), "ReportesEjecutadas"));
            }
            if (Util.RP(preferences) != 0) {
                yValues.add(new PieEntry(Util.RP(preferences), "ReportesPendiente"));
            }
            if (Util.REP(preferences) != 0) {
                yValues.add(new PieEntry(Util.REP(preferences), "ReportesEnProceso"));
            }
            if (Util.RV(preferences) != 0) {
                yValues.add(new PieEntry(Util.RV(preferences), "ReportesEnVisita"));
            }
            if (Util.RO(preferences) != 0) {
                yValues.add(new PieEntry(Util.RO(preferences), "Otros"));
            }
        }

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(7f);
        dataSet.setSelectionShift(10f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setHighlightEnabled(true);
        PieData data = new PieData((dataSet));
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);
        pieChart.animateXY(2000, 2000);
        pieChart.setData(data);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    public void Inicar() {
        Util.preferences = getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        if (SplashActivity.LoginShare == true) {
            if (!isOnline()) {
                Toast.makeText(getApplicationContext(), "No cuenta con conexión a Internet", Toast.LENGTH_LONG).show();
                finish();

            } else {
                dialogInicio.show();

                request.getProximaCita(getBaseContext());
                drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        } else {
            pieChart.setVisibility(View.VISIBLE);
            Grafica(pieChart);
        }
    }
}
