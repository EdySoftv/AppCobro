package com.Softv.SoftvApp.SoftvApp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Activitys.MainReportes;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetallesAdapter extends RecyclerView.Adapter<DetallesAdapter.DetallesViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<String> DetalleSaldo;
    private ArrayList<String> OperacionSaldo;
    private ArrayList<String> ImporteSaldo;
    public  Request request = new Request();

    public static  class DetallesViewHolder extends  RecyclerView.ViewHolder{
        public TextView DetalleSaldo, OperacionSaldo, ImporteSaldo;
        public DetallesViewHolder( View v) {
            super(v);

            DetalleSaldo=(TextView)itemView.findViewById(R.id.txtConcepto);
            OperacionSaldo=(TextView) itemView.findViewById(R.id.textView60);
            ImporteSaldo=(TextView) itemView.findViewById(R.id.txtImporte);

        }
    }

    public DetallesAdapter(Context mContext, ArrayList<String>DescripcionSaldo, ArrayList<String>OperacionSaldo, ArrayList<Float>MontoSaldo){
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        return Array.DescripcionSaldo.size();
    }

    @NonNull
    @Override
    public DetallesViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_detalle,viewGroup,false);

        return new DetallesViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(DetallesViewHolder viewHolder, final int position) {

        viewHolder.DetalleSaldo.setText(Array.DescripcionSaldo.get(position));
        viewHolder.OperacionSaldo.setText(Array.OperacionSaldo.get(position));
        DecimalFormat formato = new DecimalFormat("$0.00");
        viewHolder.ImporteSaldo.setText(formato.format(Array.MontoSaldo.get(position)).toString());

    }
}
