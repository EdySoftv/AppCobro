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

import java.util.ArrayList;
import java.util.List;

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

    public static  class QuejaViewHolder extends  RecyclerView.ViewHolder{
        TextView statusq,contratoq,nombreq,direccion,quejaq,control;
        public QuejaViewHolder( View v) {
            super(v);

            statusq=(TextView)itemView.findViewById(R.id.tv_estatus);
            quejaq=(TextView) itemView.findViewById(R.id.tv_NOrden);
            contratoq=(TextView)itemView.findViewById(R.id.tv_NContrato);
            nombreq=(TextView)itemView.findViewById(R.id.tv_Nombre);
            direccion=(TextView)itemView.findViewById(R.id.id_direccion);
            control=(TextView)itemView.findViewById(R.id.controla);

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
                mContext.startActivity(intento1);
                clvReport=Integer.valueOf(Array.Queja.get(position));
                contratoReport=String.valueOf(Array.contratoQ.get(position));
            }
        });
    }
}
