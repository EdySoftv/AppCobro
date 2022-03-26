package com.Softv.SoftvApp.SoftvCobro.Activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvCobro.Adapters.DetallesAdapter;
import com.Softv.SoftvApp.SoftvCobro.Adapters.HistorialDePagosAdapter;
import com.Softv.SoftvApp.SoftvCobro.Listas.Array;
import com.Softv.SoftvApp.SoftvCobro.R;
import com.Softv.SoftvApp.SoftvCobro.Request.Request;
import com.Softv.SoftvApp.SoftvCobro.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Util;

public class Pagos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Request request = new Request();
    public static ProgressDialog dialog;
    private View view;

    public static HistorialDePagosAdapter adaptercl;
    private HistorialDePagosAdapter DetAdapter;

    private static TextView Fecha, Total;
    public static RecyclerView HistorialDePagos;

    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_reportes);
        view = (View) findViewById(R.id.ConstraintPagos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Util.preferences = getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        Util.editor = Util.preferences.edit();

        dialog = new BarraCargar().showDialog(this);

        Fecha = findViewById(R.id.textViewFecha);

        Total = findViewById(R.id.Total);

        HistorialDePagos = findViewById(R.id.ListaCobros);

        dialog.show();
        request.getHistorialDePagos(getApplicationContext(), Util.getUsuarioPreference(Util.preferences),dialog,Fecha,Total);

        HistorialDePagos.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Pagos.this);
        DetAdapter=new HistorialDePagosAdapter(Pagos.this, Array.DescripcionSaldo,Array.OperacionSaldo,Array.MontoSaldo);
        HistorialDePagos.setLayoutManager(layoutManager);
        HistorialDePagos.setAdapter(DetAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().setGroupVisible(R.id.HisPag,Request.PermCobro);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intento1=new Intent(Pagos.this, Saldo.class);
            intento1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intento1);
            finish();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.Configuraciones) {
            Intent intent1 = new Intent(Pagos.this, Configuracion.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        }else if (id == R.id.Saldo) {
            Intent intent1 = new Intent(Pagos.this, Saldo.class);
            intent1.putExtra("dato", "TAP");
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();
        }else if (id == R.id.Pagos) {
            Intent intent1 = new Intent(Pagos.this, Pagos.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
