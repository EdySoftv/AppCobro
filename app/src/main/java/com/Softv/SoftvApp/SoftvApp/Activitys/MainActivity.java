package com.Softv.SoftvApp.SoftvApp.Activitys;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.Adapters.TrabajosAdapter;
import com.Softv.SoftvApp.SoftvApp.Fragments.HorasReportes;
import com.Softv.SoftvApp.SoftvApp.Fragments.TrabajosOrdenes;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarOrdenes;
import com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes;
import com.Softv.SoftvApp.SoftvApp.Fragments.MaterialesOrdenes;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONArray;
import org.json.JSONObject;

//import androidx.annotation.RequiresApi;


import static com.Softv.SoftvApp.SoftvApp.Adapters.TrabajosAdapter.retiro;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes.ejecutada;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes.visita;
import static com.Softv.SoftvApp.SoftvApp.Listas.Array.recibixnew;
import static com.Softv.SoftvApp.SoftvApp.Request.Request.stringValidaTrabajos;


public class MainActivity extends AppCompatActivity implements ActionBar.TabListener, ViewPager.OnPageChangeListener {
    public static ViewPager mViewPager;
    ScrollView hzScrollView;
    Button info;
    private Boolean cambio = false;
    public  int positionTab;
    ConstraintLayout layoutAnimado;
    public static TextView NombreTec, Contrato, Status, Nombre, Direccion, InfoServicios;
    public static String Estatus;
    public static boolean DescargaAgregar=false;
    int ValidaRegreso=0,ValidaHora=0;

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
        setTitle("No. de Orden: " +  Util.getClvOrden(Util.preferences));

        ejecutada = 0;
        visita = 0;

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
            jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
            jsonObject.put("OP2", 0);
            jsonObject.put("OPCION", "M");
            jsonObject.put("STATUS", "E");
            request.getValidaTrabajos(getApplicationContext(),jsonObject);
        }catch (Exception e){}
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
        if (ejecutada == 0 && visita == 0) {
            Toast.makeText(this, "Seleccione un estatus", Toast.LENGTH_SHORT).show();
            mViewPager.setCurrentItem(0);
            getSupportActionBar().setSelectedNavigationItem(0);
        }
        if (ejecutada == 1) {
            HorasReportes.statusHora = "E";
           if(positionTab == 2){

               if (positionTab ==2 && retiro == true && recibixnew.size() > 0){
                   try{
                       ValidaTrabajos(0,getApplicationContext());
                   }catch (Exception e){
                       ValidaTrabajos(0,getApplicationContext());
                   }

               }else if (positionTab ==2 && retiro == true && recibixnew.size() == 0) {
                   recibixnew.clear();
                   Toast.makeText(this, "Debe seleccionar al menos un aparato", Toast.LENGTH_SHORT).show();
                   mViewPager.setCurrentItem(1);
               }
               else if (positionTab == 2){
                   try{
                       ValidaTrabajos(0,getApplicationContext());
                   }catch (Exception e){
                       ValidaTrabajos(0,getApplicationContext());
                   }
               }

            }

            if(positionTab == 3){
                try{
                    ValidaTrabajos(1,getApplicationContext());
                }catch (Exception e){
                    ValidaTrabajos(1,getApplicationContext());
                }

                DescargaAgregar=true;
/*                if(Util.getPermisisDescarga(Util.preferences)==true){
                    if(Array.dataDescargaDirecta.get(0).size()!=0 && request.NoBitacora!=0){
                        try {
                            JSONObject jsonObject = new JSONObject();
                            JSONObject jsonObject2 = new JSONObject();
                            JSONArray jsonArray = new JSONArray();
                            jsonObject.put("IdTecnico",Util.getClvTec(Util.preferences) );
                            jsonObject.put("ClvOrden",Util.getClvOrden(Util.preferences) );
                            jsonObject.put("IdAlmacen", 0);
                            jsonObject.put("Accion", "Modificar");
                            jsonObject.put("IdBitacora", request.NoBitacora);
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                            jsonObject.put("Usuario",Util.getClvTec(Util.preferences));
                            jsonObject.put("Fecha", Util.getTipoDescarga(Util.preferences));
                            for(int i=0; i< Array.dataDescargaDirecta.get(0).size(); i++){
                                JSONObject jsonObject1 = new JSONObject();
                                jsonObject1.put("NoArticulo",Array.dataDescargaDirecta.get(0).get(i).NOARTICULO );
                                jsonObject1.put("Cantidad",Array.dataDescargaDirecta.get(0).get(i).CANTIDADUTILIZADA);
                                jsonObject1.put("EsCable", Array.dataDescargaDirecta.get(0).get(i).ESCABLE);
                                jsonObject1.put("MetrajeInicio",Array.dataDescargaDirecta.get(0).get(i).METRAJEINICIO);
                                jsonObject1.put("MetrajeFin", Array.dataDescargaDirecta.get(0).get(i).METRAJEFIN);
                                jsonObject1.put("MetrajeInicioExt", Array.dataDescargaDirecta.get(0).get(i).METRAJEINICIOEXTERIOR);
                                jsonObject1.put("MetrajeFinExt", Array.dataDescargaDirecta.get(0).get(i).METRAJEFINEXTERIOR);
                                try {
                                    jsonObject1.put("NumExt", Array.dataDescargaDirecta.get(0).get(i).NoExt);
                                }catch (Exception e){
                                    jsonObject1.put("NumExt", 0);
                                }
                                jsonArray.put(i,jsonObject1);
                            }


                            jsonObject2.put("ObjDescargaMat",jsonObject);
                            jsonObject2.put("Articulos",jsonArray);

                            request.addDescarga(getParent(),getApplicationContext(),jsonObject2);
                        }catch (Exception e){}
                    }else{
                        Toast.makeText(getApplicationContext(),"Necesita agregar un articulo ya que la orden cuanta con una bitacora",Toast.LENGTH_LONG).show();
                        mViewPager.setCurrentItem(2);
                    }


            } else{
                mViewPager.setCurrentItem(positionTab);
                }*/

            if(positionTab == 4){

                }


            } else{
                mViewPager.setCurrentItem(positionTab);
            }

        } else {
            if(visita==1){
                HorasReportes.statusHora = "V";
                Estatus="V";
                mViewPager.setCurrentItem(0);
                getSupportActionBar().setSelectedNavigationItem(0);

          /*      if(ValidaRegreso==1){
                    mViewPager.setCurrentItem(0);
                    getSupportActionBar().setSelectedNavigationItem(0);
                    ValidaRegreso=0;

                }else{
                    if(positionTab == 0){
                        *//*mViewPager.setCurrentItem(0);
                        getSupportActionBar().setSelectedNavigationItem(0);*//*
                        ValidaRegreso=3;

                    }
                    if(positionTab == 1){
                        mViewPager.setCurrentItem(3);
                        getSupportActionBar().setSelectedNavigationItem(3);
                        ValidaRegreso=3;
                    }

                    if(positionTab == 2){
                        mViewPager.setCurrentItem(3);
                        getSupportActionBar().setSelectedNavigationItem(3);
                        ValidaRegreso=3;
                    }

                    if(positionTab==3){
                        mViewPager.setCurrentItem(3);
                        getSupportActionBar().setSelectedNavigationItem(3);
                        ValidaRegreso=1;
                    }
                }*/


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
            if(layoutAnimado.getVisibility()==View.VISIBLE){
                layoutAnimado.setVisibility(View.GONE);
                hzScrollView.setVisibility(View.GONE);
                info.setText("Datos Cliente");
            }else {
                mViewPager.setCurrentItem(positionTab - 1);
            }
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
public void ValidaTrabajos(final int a, final Context context){
        try {
         if(TrabajosOrdenes.ValidaRequiereTrabajo==true){
             try{
                 JSONObject jsonObject= new JSONObject();
                 jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
                 jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                 jsonObject.put("OP2", 0);
                 jsonObject.put("OPCION", "M");
                 jsonObject.put("STATUS", "E");
                 request.getValidaTrabajos(getApplicationContext(),jsonObject);


                 if (stringValidaTrabajos.length() == 2 ||stringValidaTrabajos.equals("")||stringValidaTrabajos.equals(null) || stringValidaTrabajos==null) {
                     if(a==1){
                         cambio = true;
                         if (cambio == false) {
                             mViewPager.setCurrentItem(2);
                             getSupportActionBar().setSelectedNavigationItem(2);
                         } else if (cambio == true) {
                             mViewPager.setCurrentItem(positionTab);
                             getSupportActionBar().setSelectedNavigationItem(positionTab);
                         }
                     }else{
                         Toast.makeText(getApplicationContext(), "Sección de trabajos completa", Toast.LENGTH_SHORT).show();
                         cambio = true;
                         mViewPager.setCurrentItem(positionTab);
                         getSupportActionBar().setSelectedNavigationItem(positionTab);
                     }
                 } else {

                     Toast.makeText(getApplicationContext(), "Error: " + stringValidaTrabajos, Toast.LENGTH_LONG).show();
                     mViewPager.setCurrentItem(1);
                     getSupportActionBar().setSelectedNavigationItem(1);
                     //positionTab--;
                 }

             }catch (Exception e){}
         }else {
             mViewPager.setCurrentItem(positionTab);
             getSupportActionBar().setSelectedNavigationItem(positionTab);
         }



        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }
}

}