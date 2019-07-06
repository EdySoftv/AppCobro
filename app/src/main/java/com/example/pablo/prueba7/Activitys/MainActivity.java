package com.example.pablo.prueba7.Activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Fragments.HorasReportes;
import com.example.pablo.prueba7.Fragments.TrabajosOrdenes;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.Fragments.EjecutarOrdenes;
import com.example.pablo.prueba7.Fragments.HorasOrdenes;
import com.example.pablo.prueba7.Fragments.MaterialesOrdenes;
import com.example.pablo.prueba7.sampledata.Util;

import org.json.JSONObject;

import androidx.annotation.RequiresApi;

import static com.example.pablo.prueba7.Adapters.OrdenesAdapter.clvor;
import static com.example.pablo.prueba7.Adapters.OrdenesAdapter.noOrden;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.TecSec;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.ejecutada;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.visita;
import static com.example.pablo.prueba7.Request.Request.stringValidaTrabajos;


public class MainActivity extends AppCompatActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
    public ViewPager mViewPager;
    ScrollView hzScrollView;
    Button info;

    public  int positionTab;
    ConstraintLayout layoutAnimado;
    public static TextView NombreTec, Contrato, Status, Nombre, Direccion, InfoServicios;
    boolean visitaValida=false;
    public static String Estatus;

    Request request = new Request();

    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setRetainInstance(true);
        validar_Permiso();
        setContentView(R.layout.activity_swipe_ordenes);
        info= findViewById(R.id.info);
        layoutAnimado= findViewById(R.id.animado);
        hzScrollView= findViewById(R.id.scv);
        //NombreTec= findViewById(R.id.tecniconame);
        Contrato= findViewById(R.id.contrato);
        Status= findViewById(R.id.status);
        Nombre= findViewById(R.id.infonombre);
        Direccion= findViewById(R.id.infodireccion);
        InfoServicios= findViewById(R.id.infoservicios);
        setTitle("No. de Orden: " + noOrden);

        ejecutada = 0;
        visita = 0;

       // NombreTec.setText(nombre_tecnico);
        Contrato.setText(request.contraroMA);
        Status.setText(request.statusMA);
//        NombreTec.setText(Util.getNombreTecnicoPreference(Util.preferences));
        //* Boton de informacion
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request.getInfoCliente(getApplicationContext());
                    request.getServicios(getApplicationContext());
                if(layoutAnimado.getVisibility()==View.GONE) {
                    layoutAnimado.setVisibility(View.VISIBLE);
                    hzScrollView.setVisibility(View.VISIBLE);

                    info.setText("Ocultar");
                }
                else{
                    layoutAnimado.setVisibility(View.GONE);
                    hzScrollView.setVisibility(View.GONE);
                    info.setText("Datos Cliente");
                }
            }
        });

        //* Swipe
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);


        mViewPager.setAdapter(adapter);

        mViewPager.setOnPageChangeListener(this);


        ActionBar actionBar = getSupportActionBar();


        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        ActionBar.Tab tab = actionBar.newTab().setText("Estatus").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Trabajo").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Material").setTabListener(this);
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Finalizar").setTabListener(this);
        actionBar.addTab(tab);



    }


    private void setRetainInstance(boolean b) {

    }

    public class PagerAdapter extends FragmentPagerAdapter {


        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int arg0) {
            switch (arg0) {
                case 0:
                    return new HorasOrdenes();
                case 1:
                    return new TrabajosOrdenes();
                case 2:
                    return new MaterialesOrdenes();
                case 3:
                    return new EjecutarOrdenes();
                default:
                    return null;
            }
        }


            FragmentManager manager = getSupportFragmentManager();



        public int getCount() {
            return 4;
        }
    }
    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        getSupportActionBar().setSelectedNavigationItem(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // mViewPager.setCurrentItem(tab.getPosition());
        positionTab = tab.getPosition();


        if (ejecutada == 1) {
            try{
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("CLV_ORDEN", clvor);
                jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                jsonObject.put("OP2", 0);
                jsonObject.put("OPCION", "M");
                jsonObject.put("STATUS", "E");
                request.getValidaTrabajos(getApplicationContext(),jsonObject);
            }catch (Exception e){}

            if(positionTab == 2){
                if(stringValidaTrabajos.length() == 2 ){
                    Toast.makeText(getApplicationContext(), "Sección de trabajos completa", Toast.LENGTH_SHORT).show();
                    mViewPager.setCurrentItem(positionTab);
                }else{

                    Toast.makeText(getApplicationContext(), "Error: " + stringValidaTrabajos, Toast.LENGTH_LONG).show();
                    mViewPager.setCurrentItem(1);

                    //positionTab--;
                }
            }
            else{
                if(positionTab ==3){
                    mViewPager.setCurrentItem(positionTab);
                }
                else{
            mViewPager.setCurrentItem(positionTab);
                }

                }

        } else {

            if (ejecutada == 0 && visita == 0) {
                Toast.makeText(this, "Seleccione un estatus", Toast.LENGTH_SHORT).show();
                mViewPager.setCurrentItem(0);
            } else {

                if (visitaValida == false) {
                    mViewPager.setCurrentItem(3);
                    visitaValida = true;
                    HorasReportes.statusHora = "V";
                    Estatus="V";
                } else {
                    mViewPager.setCurrentItem(0);
                    visitaValida = false;
                    HorasReportes.statusHora = "E";
                }

            }

        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    public void regresar(){
        if((positionTab-1)>=0){
            mViewPager.setCurrentItem(positionTab-1);
        }
            else{
                if(layoutAnimado.getVisibility()==View.VISIBLE){
                    layoutAnimado.setVisibility(View.GONE);
                    hzScrollView.setVisibility(View.GONE);
                    info.setText("Datos Cliente");
                }else{
                    Intent intento = new Intent(MainActivity.this, Orden.class);
                    intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intento);
                    finish();
                    TecSec.setSelection(0);
                }

            }
            }

            public void onBackPressed(){
        regresar();
            }

    private void validar_Permiso() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1008);
        } else {

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1008) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            } else {
                Toast.makeText(this, "Debe aceptar los permisos", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

}