package com.example.pablo.prueba7.Adapters;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Activitys.ReporteAsignacion;

import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.example.pablo.prueba7.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.pablo.prueba7.Activitys.ReporteAsignacion.adapter;
import static com.example.pablo.prueba7.Activitys.ReporteAsignacion.reporteAsignacion;

public class EliminarAparatosAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context mcontext;
    private Activity activity;
    private int pos;
    Spinner spinnerMedio;
    Button guardarAparatos;
    int b=0;
    Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = Array.dataArbSer.iterator();
    List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData.next();

    Array array = new Array();
    public EliminarAparatosAdapter(Context context, final Activity activity, int pos, Spinner spinnerMedio,Button guardarAparatos){
        mcontext=context;
        inflater = LayoutInflater.from(mcontext);
        this.activity = activity;
        this.pos = pos;
        this.spinnerMedio = spinnerMedio;
        this.guardarAparatos = guardarAparatos;

    }
    public static class viewHolder{
        public static TextView nombre;
        ImageButton elimarAparato;



    }
    @Override
    public int getCount() {
        return array.children.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final EliminarAparatosAdapter.viewHolder holder;
        holder = new EliminarAparatosAdapter.viewHolder();
        convertView = inflater.inflate(R.layout.activity_reporte_asignacion_list,null);
        holder.nombre=convertView.findViewById(R.id.textelimnar);
        holder.elimarAparato = convertView.findViewById(R.id.eliminarAparatos);
        convertView.setTag(holder);

        holder.nombre.setText(array.children.get(position));
        holder.elimarAparato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogoReinicio(mcontext,array.children.get(position),position,activity,pos,spinnerMedio,guardarAparatos);
            }
        });







        return convertView;
    }
    public void dialogoReinicio(final Context context, String abc, final int posicion, final Activity activity,
                                final int pos, final Spinner spinnerMedio, final Button guardarAparatos) {
        new AlertDialog.Builder(activity,R.style.InvitationDialog)
                .setTitle("Confirmar")
                .setMessage("Desea eliminar el aparato: "+abc)
                .setPositiveButton("Eliminar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    for (int a=0;a<dat.get(pos).children.size(); a++){
                                        String elimar = dat.get(pos).children.get(a).Nombre+"-" + dat.get(pos).children.get(a).getDetalle();
                                        if(elimar.equals(array.children.get(posicion))){
                                            dat.get(pos).children.remove(posicion);
                                            array.children.remove(posicion);
                                        }
                                    }
                                }catch (Exception e){}

                                for(int a=0; a<dat.size();a++){
                                    if(dat.get(a).children.size()!=0){
                                        b=b+1;
                                    }
                                }
                                if(b==0){
                                    reporteAsignacion.setAdapter(adapter);
                                    //si no tiene hijos habilitamos el spinner
                                    spinnerMedio.setEnabled(true);
                                    //si no tiene hijos deshabilitamos el boton
                                    guardarAparatos.setEnabled(false);
                                    Toast.makeText(context, "Debes de asiganar minimo 1 aparato", Toast.LENGTH_LONG).show();
                                }else{
                                    reporteAsignacion.setAdapter(adapter);
                                    //si tiene hijos deshabilitamos el spinner
                                    spinnerMedio.setEnabled(false);
                                    //si tiene hijos habilitamos el boton
                                    guardarAparatos.setEnabled(true);
                                }

                            }
                        })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
    }
}