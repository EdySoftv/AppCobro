package com.example.pablo.prueba7.Activitys;

import android.os.Bundle;
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

import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.Fragments.Ejecutar1Fragment;
import com.example.pablo.prueba7.Fragments.HorasFragment;
import com.example.pablo.prueba7.Fragments.MaterialesFragment;
import com.example.pablo.prueba7.Fragments.TrabajosFragment;

import static com.example.pablo.prueba7.Fragments.HorasFragment.TecSec1;
import static com.example.pablo.prueba7.Fragments.HorasFragment.tecPosRepo;

public class MainReportes extends AppCompatActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private ScrollView hzScrollView;
    private Button info;
    private RelativeLayout layoutAnimado;
    int position;
    public static TextView Nombre1, Direccion1,NombreTec1,infoA,contrato1,ciudad1;
    Request request = new Request();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportess);
        info=findViewById(R.id.info);
        layoutAnimado = (RelativeLayout) findViewById(R.id.animado);
        hzScrollView=(ScrollView)findViewById(R.id.scv);
        Nombre1= findViewById(R.id.infonombre1);
        Direccion1= findViewById(R.id.infodireccion1);
        NombreTec1= findViewById(R.id.tecnico1);
        infoA = findViewById(R.id.infoservicios1);
        contrato1=findViewById(R.id.contrato1);
        ciudad1=findViewById(R.id.infoempresa1);
        setTitle(null);
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
                    info.setText("Info");
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
        ActionBar.Tab tab = actionBar.newTab().setText("Reporte").setTabListener(this);
        actionBar.addTab(tab);
        tab = actionBar.newTab().setText("Horas").setTabListener(this);
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
                    return new TrabajosFragment();
                case 1:
                    return new HorasFragment();
                case 2:
                    return new MaterialesFragment();
                case 3:
                    return new Ejecutar1Fragment();
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
            TecSec1.setSelection(tecPosRepo);
            finish();
        }
    }
}
