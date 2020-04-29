package com.Softv.SoftvApp.SoftvApp.Activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONObject;

import static com.Softv.SoftvApp.SoftvApp.Services.Services.clavequeja;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.clvorden;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.opcion;

public class NAPTAP extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    NavigationView barra;
    private Request request = new Request();
    public static Spinner spinnerColonia;
    String recuperamos_variable_string;
    TextView titulo;
    public static String cordLatTN="", cordLongTN="";
    private LocationManager locationManager;
    Activity activity;
    RecyclerView lista;

    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_naptap);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        activity = this;
        setSupportActionBar(toolbar);
        barra = findViewById(R.id.nav_view);
        lista = findViewById(R.id.listaNAPTAP);
        titulo = findViewById(R.id.NAPTAPTitulo);
        spinnerColonia = findViewById(R.id.spinnerColonia);

        request.getColonia(getApplicationContext());

        View barra1 = barra.getHeaderView(0);
        recuperamos_variable_string = getIntent().getStringExtra("dato");
        if(recuperamos_variable_string.equals("NAP")) {
            titulo.setText(getResources().getString(R.string.NAP));
            spinnerColonia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0){
                        Toast.makeText(getApplicationContext(),"Seleccione una colonia",Toast.LENGTH_LONG).show();
                    }else{
                        try{
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("op",0);
                            jsonObject.put("nombre_Colonia",spinnerColonia.getSelectedItem());
                            request.getNAPTAP(getApplicationContext(),jsonObject,lista,getResources().getString(R.string.NAP),activity);
                        }catch (Exception e){}
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });


        }else if(recuperamos_variable_string.equals("TAP")) {
            titulo.setText(getResources().getString(R.string.TAP));
            spinnerColonia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0){
                        Toast.makeText(getApplicationContext(),"Seleccione una colonia",Toast.LENGTH_LONG).show();
                    }else{
                        try{
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("op",1);
                            jsonObject.put("nombre_Colonia",spinnerColonia.getSelectedItem());
                            request.getNAPTAP(getApplicationContext(),jsonObject,lista,getResources().getString(R.string.TAP),activity);
                        }catch (Exception e){}
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        locationManager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 225);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locListener, Looper.getMainLooper());
        }



    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Util.preferences = getApplicationContext().getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        Util.editor = Util.preferences.edit();
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (id == R.id.Inicio) {
            Intent intento = new Intent(getApplicationContext(), Inicio.class);
            intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intento);


        } else if (id == R.id.Ordenes_menu) {
            clvorden = 0;
            opcion = 1;
            Util.editor.putString("TipoDescarga", "O");
            Util.editor.commit();
            request.getListOrd(getApplicationContext());

        } else if (id == R.id.Reportes) {
            clavequeja = 0;
            opcion = 1;
            Util.editor.putString("TipoDescarga", "Q");
            Util.editor.commit();
            request.getListQuejas(getApplicationContext());

        } else if (id == R.id.Configuraciones) {
            Intent intent1 = new Intent(NAPTAP.this, Configuracion.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent1);
            finish();
        }else if (id == R.id.MenuCoordenadasNAP) {
            if(recuperamos_variable_string.equals("NAP")) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }else {
                Intent intent1 = new Intent(NAPTAP.this, NAPTAP.class);
                intent1.putExtra("dato", "NAP");
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                finish();
            }
        } else if (id == R.id.MenuCoordenadasTAP) {
            if(recuperamos_variable_string.equals("TAP")) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }else {
                Intent intent1 = new Intent(NAPTAP.this, NAPTAP.class);
                intent1.putExtra("dato", "TAP");
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
                finish();
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @SuppressLint("MissingPermission")
    private void setearCoordenadas() {
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (location != null) {
            double latitude = location.getLatitude();
            // editor.putFloat("latitud", (float) latitude).commit();
            double longitud = location.getLongitude();
            //editor.putFloat("longitud", (float) longitud).commit();
            cordLatTN=String.valueOf(latitude);
            cordLongTN=String.valueOf(longitud);
            Toast.makeText(getApplicationContext(),"Coordenadas obtenidas",Toast.LENGTH_LONG).show();
            isCoordenadas = true;
        }
    }

    private void mostrarInformacionDeAlertaGPS() {
        new android.app.AlertDialog.Builder(activity)
                .setTitle("Señal de GPS")
                .setMessage("El GPS esta desactivado. ¿Deseas activarlo?")
                .setPositiveButton("Activar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mostrarInformacionDeAlertaGPS();
                    }
                })
                .show();
    }

    private boolean isCoordenadas = false;


    public LocationListener locListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            if (cordLatTN.equals("") | cordLongTN.equals("")) {
                isCoordenadas = false;
            }

            if (isCoordenadas == false) {
                double latitude = location.getLatitude();
                // editor.putFloat("latitud", (float) latitude).commit();
                double longitud = location.getLongitude();
                //editor.putFloat("longitud", (float) longitud).commit();
                cordLatTN=String.valueOf(latitude);
                cordLongTN=String.valueOf(longitud);
                Log.i("error", cordLatTN+"||||||"+cordLongTN);
                isCoordenadas = true;
            }
        }

        public void onProviderDisabled(String provider) {
            Log.i("error", "onProviderDisabled()");
            mostrarInformacionDeAlertaGPS();
        }

        public void onProviderEnabled(String provider) {
            Log.i("error", "onProviderEnabled()");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i("error", "onStatusChanged()");
        }
    };

}
