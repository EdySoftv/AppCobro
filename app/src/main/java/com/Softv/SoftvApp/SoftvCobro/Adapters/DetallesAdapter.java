package com.Softv.SoftvApp.SoftvCobro.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvCobro.Listas.Array;
import com.Softv.SoftvApp.SoftvCobro.R;
import com.Softv.SoftvApp.SoftvCobro.Request.Request;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Constants;

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
        DecimalFormat formato = new DecimalFormat(Constants.FORMATO);
        viewHolder.ImporteSaldo.setText(formato.format(Array.MontoSaldo.get(position)).toString());

    }
}
