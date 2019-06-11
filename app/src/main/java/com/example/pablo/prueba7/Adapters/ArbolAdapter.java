package com.example.pablo.prueba7.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import static com.example.pablo.prueba7.Activitys.ServiciosAInstalar.todosLosMediosValidacion;
//import static com.example.pablo.prueba7.Adapters.ArbolAdapter.viewHolder.medio;


public class ArbolAdapter extends RecyclerView.Adapter<ArbolAdapter.ArbolViewHolder> {
    Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = Array.dataArbSer.iterator();
    List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = itData.next();



    private LayoutInflater inflater;
    private Context mcontext;
    public static int clv_unicaNet, posicionArbol;
    public static String nombreToolBar;
    private Array array = new Array();
    public static int a=0;

   public static  class ArbolViewHolder extends  RecyclerView.ViewHolder{
       public static Button Asignar;
       public static ProgressBar progressBar;
       public ArbolViewHolder( View v) {
           super(v);
           Asignar = itemView.findViewById(R.id.servicioAAsignar);
           progressBar = itemView.findViewById(R.id.progressBarServicio);
       }
   }

   public ArbolAdapter(List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4, final Context mcontext){
       this.dat4=dat4;
       this.mcontext = mcontext;
   }

    @Override
    public int getItemCount() {
        return dat4.size();
    }

    @NonNull
    @Override
    public ArbolViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_aparato_asignado_medio_list,viewGroup,false);

        return new ArbolViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ArbolViewHolder viewHolder, final int i) {

        viewHolder.Asignar.setText(dat4.get(i).Nombre);
        if(dat4.get(i).IdMedio!=0){
            viewHolder.progressBar.setProgress(50);

            if(dat4.get(i).children.size()!=0){
                viewHolder.progressBar.setProgress(100);
                viewHolder.Asignar.setBackgroundColor(Color.parseColor("#48C9B0"));
            }

        }



        viewHolder.Asignar.setOnClickListener(new View.OnClickListener() {
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
                        if(ServiciosAInstalar.todosLosMediosValidacion==true){

                        }else{
                            for(int a=0; a<dat4.size(); a++){
                                dat4.get(a).setIdMedio(ServiciosAInstalar.idMedioSI);
                                dat4.get(a).setDetalle(ServiciosAInstalar.detalleSI);
                            }
                        }



                    }
                    if(ServiciosAInstalar.todosLosMedios==0){
                        //Mandar en intent la clvunicanet para consultar los medios
                        clv_unicaNet=dat4.get(i).getClv_UnicaNet();
                    }
                    //Mandar el nombre del servicio para colocarlo en la toolbar, mandar posicion de servicio
                    nombreToolBar=dat4.get(i).Nombre;
                    posicionArbol=i;
                    mcontext.startActivity(intento);
                }



            }
        });
    }

}
