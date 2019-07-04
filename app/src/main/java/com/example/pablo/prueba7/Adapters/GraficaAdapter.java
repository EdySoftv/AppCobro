package com.example.pablo.prueba7.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pablo.prueba7.R;

import java.util.ArrayList;

public class GraficaAdapter extends RecyclerView.Adapter<GraficaAdapter.GrafiacaViewHolder> {
    private Context mContext;
    private ArrayList<String> nombreGrafica;
    private ArrayList<Integer>coloresGrafica;
    public static  class GrafiacaViewHolder extends  RecyclerView.ViewHolder{
        private TextView textgrafica;
        private ImageButton colorGrafica;
        public GrafiacaViewHolder( View v) {
            super(v);
            textgrafica=(TextView)itemView.findViewById(R.id.textgrafica);
            colorGrafica=itemView.findViewById(R.id.colorGrafica);
        }
    }
    public GraficaAdapter(Context mContext, ArrayList<String> nombreGrafica, ArrayList<Integer>coloresGrafica){
        this.nombreGrafica=nombreGrafica;
        this.coloresGrafica=coloresGrafica;
        this.mContext=mContext;
    }
    @Override
    public int getItemCount() {
        return nombreGrafica.size();
    }

    @NonNull
    @Override
    public GraficaAdapter.GrafiacaViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_grafica_list,viewGroup,false);

        return new GraficaAdapter.GrafiacaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GraficaAdapter.GrafiacaViewHolder viewHolder, final int position) {
        viewHolder.textgrafica.setText(nombreGrafica.get(position));
    }

}
