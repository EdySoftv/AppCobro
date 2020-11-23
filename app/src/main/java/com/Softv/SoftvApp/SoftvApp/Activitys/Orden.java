package com.Softv.SoftvApp.SoftvApp.Activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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

import com.Softv.SoftvApp.SoftvApp.Adapters.OrdenesAdapter;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import static com.Softv.SoftvApp.SoftvApp.Listas.Array.statusrc;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.clavequeja;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.clvorden;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.cont;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.opcion;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.precinto;

public class Orden extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Request request = new Request();
    private OrdenesAdapter adapterord;
    private Button ordenb,contratob,precintob;
    public static RecyclerView ordenes;
    public static boolean statusBusquedaOrden = false, statusBusquedaContrato = false, statusBusquedaPresinto = false;
    private EditText ordsearch,contsearch,presearch;
    NavigationView barra;
    TextView nombreTec;
    public static ProgressDialog dialogOrdenes;
    BarraCargar barraCargar = new BarraCargar();
   private Request rqs=new Request();

    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_orden);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ordenes=findViewById(R.id.listorden);
        ordenb=findViewById(R.id.borden);
        contratob=findViewById(R.id.bcontrato);
        precintob=findViewById(R.id.bprecinto);
        ordsearch=findViewById(R.id.ordsearch);
        contsearch=findViewById(R.id.contsearch);
        presearch=findViewById(R.id.presearch);
        barra = findViewById(R.id.nav_view);
        View barra1 = barra.getHeaderView(0);
        nombreTec=barra1.findViewById(R.id.tv_NombreTecnico);
        try {
            nombreTec.setText(Util.getNombreTecnicoPreference(Util.preferences));
        }catch (Exception e){
            nombreTec.setText("");
        }
        clvorden=0;
        opcion=1;
        cont="";
        dialogOrdenes= new BarraCargar().showDialog(this);
        barraCargar.terminarBarra();
        ////////////////
        //////////////////
        adapterord=new OrdenesAdapter(Orden.this,Array.ordensrc,Array.nombresrc, statusrc,Array.contratosrc,Array.direccionsrc,Array.precintosrc);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        ordenes.setLayoutManager(layoutManager);
        ordenes.setAdapter(adapterord);    //Asignacion del adapatador a la listView
        ordenes.refreshDrawableState();
        //if(ordenes.getAdapter()!=null){
        //    progressBarOrdenes.setVisibility(View.INVISIBLE);
       // }
 //Busqueda de orden////
        ordenb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusBusquedaOrden = true;
                if (ordsearch.getText().toString().trim().equalsIgnoreCase("")){
                    Array.ordensrc.clear();
                    Array.nombresrc.clear();
                    statusrc.clear();
                    Array.contratosrc.clear();
                    Array.direccionsrc.clear();
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Campo de orden vacío", Toast.LENGTH_SHORT);
                    clvorden=0;
                    opcion=1;
                    request.getListOrd(getApplicationContext());
                    toast1.show();
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
                    ordenes.setLayoutManager(layoutManager);
                    ordenes.setAdapter(adapterord);
                } else {
                    Array.ordensrc.clear();
                    Array.nombresrc.clear();
                    statusrc.clear();
                    Array.contratosrc.clear();
                    Array.direccionsrc.clear();
                    opcion = 2;
                    clvorden = Integer.parseInt(ordsearch.getText().toString().toLowerCase().trim());
                    rqs.getListOrd(getApplicationContext());
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    ordenes.setLayoutManager(layoutManager);
                    ordenes.setAdapter(adapterord);
                }
            }
        });
//Busqueda de Contrato//
        contratob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusBusquedaContrato = true;
                if (contsearch.getText().toString().trim().equalsIgnoreCase("")){
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Campo de contrato vacío", Toast.LENGTH_SHORT);
                    Array.ordensrc.clear();
                    Array.nombresrc.clear();
                    statusrc.clear();
                    Array.contratosrc.clear();
                    Array.direccionsrc.clear();
                    clvorden=0;
                    opcion=1;
                    request.getListOrd(getApplicationContext());
                    toast1.show();
                    ordenes.setAdapter(adapterord);
                    toast1.show();
                } else {
                    Array.ordensrc.clear();
                    Array.nombresrc.clear();
                    statusrc.clear();
                    Array.contratosrc.clear();
                    Array.direccionsrc.clear();
                    opcion=3;
                    cont=(contsearch.getText().toString().toLowerCase().trim());
                    rqs.getListOrd(getApplicationContext());
                   // Toast toast1 = Toast.makeText(getApplicationContext(), "Contrato encontrado", Toast.LENGTH_SHORT);toast1.show();
                    ordenes.setAdapter(adapterord);

                }
            }
        });
//Busqueda de precinto//
        precintob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusBusquedaPresinto = true;
                if (presearch.getText().toString().trim().equalsIgnoreCase("")){
                    Array.ordensrc.clear();
                    Array.nombresrc.clear();
                    statusrc.clear();
                    Array.contratosrc.clear();
                    Array.direccionsrc.clear();
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Campo de precinto vacío", Toast.LENGTH_SHORT);
                    clvorden=0;
                    opcion=1;
                    request.getListOrd(getApplicationContext());
                    toast1.show();
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
                    ordenes.setLayoutManager(layoutManager);
                    ordenes.setAdapter(adapterord);
                } else {
                    Array.ordensrc.clear();
                    Array.nombresrc.clear();
                    statusrc.clear();
                    Array.contratosrc.clear();
                    Array.direccionsrc.clear();
                    opcion=4;
                    cont=(contsearch.getText().toString().toLowerCase().trim());
                    precinto=(presearch.getText().toString().toLowerCase().trim());
                    rqs.getListOrd(getApplicationContext());
                    ordenes.setAdapter(adapterord);
                }
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Ordenes");


    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intento1=new Intent(Orden.this,Inicio.class);
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
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
        } else if (id == R.id.Reportes) {
            Util.preferences = getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            Util.editor = Util.preferences.edit();
            Util.editor.putString("TipoDescarga", "Q");
            Util.editor.commit();
            dialogOrdenes.show();
            clavequeja=0;
            opcion=1;
            request.getListQuejas(getApplicationContext());
        } else if (id == R.id.Configuraciones) {
            Intent intent1 = new Intent(Orden.this, Configuracion.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
        } else if (id == R.id.MenuCoordenadasNAP) {
        Intent intent1 = new Intent(Orden.this, NAPTAP.class);
        intent1.putExtra("dato", "NAP");
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1);
        finish();
    } else if (id == R.id.MenuCoordenadasTAP) {
        Intent intent1 = new Intent(Orden.this, NAPTAP.class);
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
