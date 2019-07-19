package com.example.pablo.prueba7.Activitys;

import android.content.Intent;
import android.os.Bundle;
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

//import androidx.annotation.StyleableRes;

import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.Fragments.EjecutarReportes;
import com.example.pablo.prueba7.Fragments.HorasReportes;
import com.example.pablo.prueba7.Fragments.MaterialesReportes;
import com.example.pablo.prueba7.Fragments.TrabajosReportes;
import com.example.pablo.prueba7.sampledata.Util;

import static com.example.pablo.prueba7.Adapters.QuejasAdapter.clvReport;

import static com.example.pablo.prueba7.Fragments.EjecutarReportes.TecSecu;
import static com.example.pablo.prueba7.Fragments.EjecutarReportes.tecSecPosRepo;
import static com.example.pablo.prueba7.Fragments.HorasReportes.tecPosRepo;
import static com.example.pablo.prueba7.Fragments.TrabajosReportes.posSolucionRepo;
import static com.example.pablo.prueba7.Fragments.TrabajosReportes.proble;

public class MainReportes extends AppCompatActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
    public static ViewPager mViewPager;
    private ScrollView hzScrollView;
    private Button info;
    private ConstraintLayout layoutAnimado;
    public  int positionTab;
    public String valorProblema;
    private boolean cambioRepo = false;
    int position;
    public static TextView Nombre1, Direccion1,NombreTec1,infoA,contrato1,ciudad1;
    Request request = new Request();
    boolean visitaValidaReporte=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_reporte);
        info=findViewById(R.id.info);
        layoutAnimado = findViewById(R.id.animado);
        hzScrollView=(ScrollView)findViewById(R.id.scv);
        Nombre1= findViewById(R.id.infonombre1);
        Direccion1= findViewById(R.id.infodireccion1);
        NombreTec1= findViewById(R.id.tecnico1);
        infoA = findViewById(R.id.infoservicios1);
        contrato1=findViewById(R.id.contrato1);
        ciudad1=findViewById(R.id.infoempresa1);
        setTitle("No. de Reporte: " + Util.getClvQueja(Util.preferences));
//* Boton de informacion
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        MainReportes.PagerAdapter adapter = new MainReportes.PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab = actionBar.newTab().setText("Estatus").setTabListener(this);
        actionBar.addTab(tab);
        tab = actionBar.newTab().setText("Reporte").setTabListener(this);
        actionBar.addTab(tab);
        tab = actionBar.newTab().setText("Material").setTabListener(this);
        actionBar.addTab(tab);
        tab = actionBar.newTab().setText("Finalizar").setTabListener(this);
        actionBar.addTab(tab);
    }
    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }
        public Fragment getItem(int arg0) {
            switch (arg0) {
                case 0:
                    return new HorasReportes();
                case 1:
                    return new TrabajosReportes();
                case 2:
                    return new MaterialesReportes();
                case 3:
                    return new EjecutarReportes();
                default:
                    return null;
            }
        }
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
        position=tab.getPosition();
        mViewPager.setCurrentItem(position);
        positionTab = tab.getPosition();
        if (HorasReportes.repotteVisita == 0 && HorasReportes.reporteEjecutada == 0) {
            Toast.makeText(this, "Seleccione un estatus", Toast.LENGTH_SHORT).show();
            mViewPager.setCurrentItem(0);
        }else if (HorasReportes.reporteEjecutada == 1) {
            mViewPager.setCurrentItem(positionTab);
            if (positionTab == 2){
                valorProblema =  proble.getText().toString();
                if(posSolucionRepo == 0 || valorProblema.equals(null) || valorProblema.equals("")){
                    if(posSolucionRepo == 0) {
                        Toast.makeText(this, "Seleccione un tipo de solución.", Toast.LENGTH_SHORT).show();
                    }
                    if( valorProblema.equals(null) || valorProblema.equals("")) {
                        Toast.makeText(this, "Escriba el problema real.", Toast.LENGTH_SHORT).show();
                    }
                    mViewPager.setCurrentItem(1);

                    cambioRepo = false;
                }
                else {
                    cambioRepo = true;
                    if (cambioRepo==true) {
                        mViewPager.setCurrentItem(positionTab);
                    }

                }
            }
            if (positionTab ==3 && cambioRepo == false){
                mViewPager.setCurrentItem(1);
            }
        } else if(HorasReportes.repotteVisita == 1) {
            mViewPager.setCurrentItem(0);
        }
    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }
    public void onBackPressed(){
        regresar();
    }
    public void regresar(){
        if((position-1)>=0){
            mViewPager.setCurrentItem(position-1);}
        else{

            if(layoutAnimado.getVisibility()==View.VISIBLE){
                layoutAnimado.setVisibility(View.GONE);
                hzScrollView.setVisibility(View.GONE);
                info.setText("Datos Cliente");
            }else{
                TecSecu.setSelection(tecSecPosRepo);
                finish();
            }


        }
    }
}
