package com.example.pablo.prueba7.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Activitys.ReporteAsignacion;
import com.example.pablo.prueba7.Activitys.ServiciosAInstalar;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraMedioPorServicoContratadoListResult;
import com.example.pablo.prueba7.Modelos.mediosPregunta;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.pablo.prueba7.Activitys.ServiciosAInstalar.Asignacion;
import static com.example.pablo.prueba7.Activitys.ServiciosAInstalar.aceptarAsignacion;
import static com.example.pablo.prueba7.Activitys.ServiciosAInstalar.aceptarmedio;
import static com.example.pablo.prueba7.Activitys.ServiciosAInstalar.layoutMedio;
import static com.example.pablo.prueba7.Activitys.ServiciosAInstalar.siguiente;
//import static com.example.pablo.prueba7.Adapters.ArbolAdapter.viewHolder.medio;


public class ArbolAdapter extends BaseAdapter {
    private Request request = new Request();
    private LayoutInflater inflater;
    private Context mcontext;
    public static int clv_unicaNet, clv_Medio, posicionArbol, d, f,h;
    public static int c=0;
    public static String nombreToolBar;
    private Array array = new Array();
    public static int a=0;
    public static boolean validacionSiguiente;
    public static ArrayList<Integer> DeletChildren = new ArrayList<Integer>();
    public static ArrayList<String> DeletMedio = new ArrayList<String>();



    public ArbolAdapter(Context context){
        mcontext=context;
        inflater = LayoutInflater.from(mcontext);

    }
    public static class viewHolder{
        public static Button Asignar;
        ProgressBar progressBar;

    }
    @Override
    public int getCount() {
        return Array.nombreArbol.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public View getView(final int position, View convertView, ViewGroup parent) {
        final viewHolder holder;
        holder = new viewHolder();
        Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = array.dataArbSer.iterator();
        final List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = itData.next();
        convertView=inflater.inflate(R.layout.activity_aparato_asignado_medio_list,null);
        holder.Asignar = convertView.findViewById(R.id.servicioAAsignar);
        holder.progressBar = convertView.findViewById(R.id.progressBarServicio);
       /* holder.nombre=convertView.findViewById(R.id.textservicio);
        holder.medio=convertView.findViewById(R.id.medio);
        holder.checkBox=convertView.findViewById(R.id.chek);*/
        convertView.setTag(holder);


        holder.Asignar.setText(dat4.get(position).Nombre);


        if(dat4.get(position).IdMedio!=0){
            holder.progressBar.setProgress(50);

            if(dat4.get(position).children.size()!=0){
                holder.progressBar.setProgress(100);
            }
        }

        holder.Asignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Crear intento
                Intent intento = new Intent(mcontext, ReporteAsignacion.class);
                //Valida que la pregunta haya sido contestada

                if(ServiciosAInstalar.todosLosMedios==2){
                    Toast.makeText(mcontext,"No se ha responido la pregunta",Toast.LENGTH_LONG).show();
                }else{
                    //Verificar si van para todos los medios 1=si 0=no 2=no se ha respondido la pregunta
                    if(ServiciosAInstalar.todosLosMedios==1){
                        dat4.get(ArbolAdapter.posicionArbol).setIdMedio(ServiciosAInstalar.idMedioSI);
                        dat4.get(ArbolAdapter.posicionArbol).setDetalle(ServiciosAInstalar.detalleSI);

                    }
                    if(ServiciosAInstalar.todosLosMedios==0){
                        //Mandar en intent la clvunicanet para consultar los medios
                        clv_unicaNet=dat4.get(position).getClv_UnicaNet();
                    }
                    //Mandar el nombre del servicio para colocarlo en la toolbar, mandar posicion de servicio
                    nombreToolBar=dat4.get(position).Nombre;
                    posicionArbol=position;
                    mcontext.startActivity(intento);
                }



            }
        });




        /*if(dat4.get(position).getIdMedio()==0){
            holder.nombre.setText(array.nombreArbol.get(position));
            holder.checkBox.setVisibility(View.GONE);

        }else{

            holder.nombre.setText(dat4.get(position).getNombre()+" ("+dat4.get(position).getDetalle()+")");
            holder.medio.setVisibility(View.INVISIBLE);
            holder.checkBox.setVisibility(View.VISIBLE);
        }if(dat4.get(position).children.size()==0){
        }else{
            holder.listaAparatos.setVisibility(View.VISIBLE);
            array.children = new ArrayList<>();
            holder.checkBox.setVisibility(View.GONE);
            ArbolAdapter.DeletChildren.clear();
            for(d = 0; d<dat4.get(position).children.size(); d++){
                c=0;
                String hijo="";
                hijo = dat4.get(position).children.get(d).Nombre + dat4.get(position).children.get(d).getDetalle();
                array.children.add(hijo);
                ArrayAdapter arrayAdapter1 = new ArrayAdapter(mcontext, android.R.layout.simple_list_item_checked,array.children);
                holder.listaAparatos.setAdapter(arrayAdapter1);
                holder.listaAparatos.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                holder.listaAparatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position3, long id) {

                        if(holder.listaAparatos.isItemChecked(position3)==true){
                            String abc= String.valueOf(position);
                            DeletChildren.add(Integer.valueOf(dat4.get(position).children.get(position3).getClv_Aparato()+abc));
                        }
                        if(holder.listaAparatos.isItemChecked(position3)==false){
                            String abc= String.valueOf(position);
                            DeletChildren.remove(Integer.valueOf(dat4.get(position).children.get(position3).getClv_Aparato()+abc));
                           }
                    }
                });
                holder.listaAparatos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        }
        /////////////////////////////////////////////
            Validar(dat4);*/
        /////////////
     final int[] m = {1};
     /*holder.medio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiciosAInstalar.eliminarAparato.setVisibility(View.GONE);
                ServiciosAInstalar.aceptarAsignacion.setVisibility(View.GONE);
                ServiciosAInstalar.cancelarAsigancion.setVisibility(View.GONE);
                siguiente.setVisibility(View.GONE);
                Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData1 = array.dataArbSer.iterator();
                List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat1 =  itData1.next();
                clv_unicaNet = dat1.get(position).getClv_UnicaNet();
                request.getMedSer(mcontext);
                posi = position;
                ////
                layoutMedio.setVisibility(View.VISIBLE);
                Asignacion.setVisibility(View.GONE);
                //siguiente.setEnabled(false);
                validacionSiguiente=false;


            }
        });
        aceptarmedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (m[0] == 1) {
                    Toast.makeText(mcontext, "Debe de llenar el campo 'Medio'", Toast.LENGTH_LONG).show();
                } else {
                    ServiciosAInstalar.eliminarAparato.setVisibility(View.VISIBLE);
                    ServiciosAInstalar.aceptarAsignacion.setVisibility(View.VISIBLE);
                    ServiciosAInstalar.cancelarAsigancion.setVisibility(View.VISIBLE);
                    siguiente.setVisibility(View.VISIBLE);
                    layoutMedio.setVisibility(View.GONE);
                    Asignacion.setVisibility(View.VISIBLE);
                    medio.setVisibility(View.GONE);
                    a=0;
                    Asignacion.setAdapter(ArbolAdapter.this);
                    Validar(dat4);
                }
            }
        });*/
        /*
        ServiciosAInstalar.spinnerMedio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position1, long id) {
                if (position1 != 0) {
                    m[0] = 2;
                    Iterator<List<GetMuestraMedioPorServicoContratadoListResult>> itdata3 = array.dataMedSer.iterator();
                    List<GetMuestraMedioPorServicoContratadoListResult> dat3 = itdata3.next();
                    Iterator<List<GetMuestraMedioPorServicoContratadoListResult>> itData2 = array.dataMedSer.iterator();
                    List<GetMuestraMedioPorServicoContratadoListResult> dat2 = itData2.next();
                    dato = dat3.get(position1-1).getDescripcion();
                    try {
                        clv_Medio = dat2.get(position1 - 1).getIdMedio();
                    } catch (Exception e) {
                        clv_Medio = dat2.get(position1).getIdMedio();

                    }
                    dat4.get(posi).setIdMedio(clv_Medio);
                    dat4.get(posi).setDetalle(dato);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        */

        return convertView;
    }
    public static void Validar(List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4){
        int d=0;
        for(int c=0; c<dat4.size(); c++){
            if(dat4.get(c).Detalle==null||dat4.get(c).Detalle.equals("")){
                d=d+1;
            }
            if(d>=1){
                //siguiente.setEnabled(false);
                validacionSiguiente=false;
            }else{
               // siguiente.setEnabled(true);
                validacionSiguiente=true;
            }
        }
        int g=0;
        for(int f=0; f<dat4.size(); f++){
            if(dat4.get(f).children.size()==0){
                g=g+1;
            }
        }
        if(g>=1){
            aceptarAsignacion.setEnabled(false);
        }else{
            aceptarAsignacion.setEnabled(true);
        }
    }

}
