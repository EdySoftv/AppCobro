package com.Softv.SoftvApp.SoftvApp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Activitys.MainReportes;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import java.util.ArrayList;

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
        TextView statusq,contratoq,nombreq,direccion,quejaq,control,noOrden,txtnap,txttap,txttrabajo,napordenes,tapordenes,trabajoordenes,fecha;
        public QuejaViewHolder( View v) {
            super(v);

            statusq=(TextView)itemView.findViewById(R.id.tv_estatus);
            quejaq=(TextView) itemView.findViewById(R.id.tv_NOrden);
            contratoq=(TextView)itemView.findViewById(R.id.tv_NContrato);
            nombreq=(TextView)itemView.findViewById(R.id.tv_Nombre);
            direccion=(TextView)itemView.findViewById(R.id.id_direccion);
            control=(TextView)itemView.findViewById(R.id.controla);
            noOrden=itemView.findViewById(R.id.noOrden);
            txtnap = itemView.findViewById(R.id.txtNap);
            txttap = itemView.findViewById(R.id.txtTap);
            txttrabajo = itemView.findViewById(R.id.txtTrabajo);
            napordenes = itemView.findViewById(R.id.id_NapOrdenes);
            tapordenes = itemView.findViewById(R.id.id_TapOrdenes);
            trabajoordenes = itemView.findViewById(R.id.id_TrabajoOrdenes);
            fecha = itemView.findViewById(R.id.id_FechaListado);

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

        viewHolder.txtnap.setVisibility(View.GONE);
        viewHolder.napordenes.setVisibility(View.GONE);
        viewHolder.txttap.setVisibility(View.GONE);
        viewHolder.tapordenes.setVisibility(View.GONE);
        viewHolder.txttrabajo.setVisibility(View.GONE);
        viewHolder.trabajoordenes.setVisibility(View.GONE);

        viewHolder.noOrden.setText("No. de Reporte");
        viewHolder.nombreq.setText(Array.nombreQ.get(position));
        viewHolder.quejaq.setText(Array.Queja.get(position));
        viewHolder.contratoq.setText(Array.contratoQ.get(position));
        viewHolder.statusq.setText(Array.statusQ.get(position));
        viewHolder.direccion.setText(Array.Direccion.get(position));
        viewHolder.fecha.setText(Array.fechaQ.get(position));
        viewHolder.control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intento1 = new Intent(mContext, MainReportes.class);
                intento1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Util.preferences = mContext.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                Util.editor = Util.preferences.edit();
                Util.editor.putInt("clvQueja", Integer.valueOf(Array.Queja.get(position)));
                Util.editor.commit();
                request.NombreGeneral = Array.nombreQ.get(position);
                contratoReport=String.valueOf(Array.contratoQ.get(position));
                statusQueja = String.valueOf(Array.statusQ.get(position));
                mContext.startActivity(intento1);
            }
        });
    }
}
