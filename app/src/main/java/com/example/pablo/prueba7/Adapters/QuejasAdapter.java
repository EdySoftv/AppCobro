package com.example.pablo.prueba7.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Activitys.MainReportes;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.Util;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class QuejasAdapter extends RecyclerView.Adapter<QuejasAdapter.QuejaViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<String> Queja;
    private ArrayList<String>contratoQ;
    private ArrayList<String>nombreQ;
    private ArrayList<String>statusQ;
    private ArrayList<String>Direccion;
    public static Integer clvReport;
    public static String contratoReport;
    public static String statusQueja;
    public  Request request = new Request();

    public static  class QuejaViewHolder extends  RecyclerView.ViewHolder{
        TextView statusq,contratoq,nombreq,direccion,quejaq,control,noOrden;
        public QuejaViewHolder( View v) {
            super(v);

            statusq=(TextView)itemView.findViewById(R.id.tv_estatus);
            quejaq=(TextView) itemView.findViewById(R.id.tv_NOrden);
            contratoq=(TextView)itemView.findViewById(R.id.tv_NContrato);
            nombreq=(TextView)itemView.findViewById(R.id.tv_Nombre);
            direccion=(TextView)itemView.findViewById(R.id.id_direccion);
            control=(TextView)itemView.findViewById(R.id.controla);
            noOrden=itemView.findViewById(R.id.noOrden);


        }
    }

    public QuejasAdapter(Context mContext, ArrayList<String>Queja, ArrayList<String>nombreQ, ArrayList<String>contratoQ, ArrayList<String>statusQ, ArrayList<String>Direccion){
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        return Array.Queja.size();
    }

    @NonNull
    @Override
    public QuejaViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_oq,viewGroup,false);

        return new QuejaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(QuejaViewHolder viewHolder, final int position) {

        viewHolder.noOrden.setText("No. de Reporte");
        viewHolder.nombreq.setText(Array.nombreQ.get(position));
        viewHolder.quejaq.setText(Array.Queja.get(position));
        viewHolder.contratoq.setText(Array.contratoQ.get(position));
        viewHolder.statusq.setText(Array.statusQ.get(position));
        viewHolder.direccion.setText(Array.Direccion.get(position));
        viewHolder.control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intento1 = new Intent(mContext, MainReportes.class);
                intento1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Util.preferences = mContext.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                Util.editor = Util.preferences.edit();
                Util.editor.putInt("clvQueja", Integer.valueOf(Array.Queja.get(position)));
                Util.editor.commit();
                contratoReport=String.valueOf(Array.contratoQ.get(position));
                statusQueja = String.valueOf(Array.statusQ.get(position));
                mContext.startActivity(intento1);
            }
        });
    }
}
