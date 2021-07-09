package com.Softv.SoftvApp.SoftvCobro.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvCobro.Listas.Array;
import com.Softv.SoftvApp.SoftvCobro.R;
import com.Softv.SoftvApp.SoftvCobro.Request.Request;

import java.util.ArrayList;

public class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ServiciosViewHolder> {


    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<String> ServicioSaldo;
    private ArrayList<String> StatusSaldo;
    public  Request request = new Request();

    public static  class ServiciosViewHolder extends  RecyclerView.ViewHolder{
        public TextView ServicioSaldo, StatusSaldo;
        public ImageView ServicioImagen;
        public ServiciosViewHolder( View v) {
            super(v);

            ServicioSaldo=(TextView)itemView.findViewById(R.id.servicio);
            StatusSaldo=(TextView) itemView.findViewById(R.id.status);
            ServicioImagen=(ImageView)itemView.findViewById(R.id.imageservicio);
            ServicioImagen.setBackgroundDrawable(null);

        }
    }

    public ServiciosAdapter(Context mContext, ArrayList<String>ServicioSaldo, ArrayList<String>StatusSaldo){
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        return Array.ServicioSaldo.size();
    }

    @NonNull
    @Override
    public ServiciosViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_servicios,viewGroup,false);

        return new ServiciosViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ServiciosViewHolder viewHolder, final int position) {

        viewHolder.ServicioSaldo.setText(Array.ServicioSaldo.get(position));
        viewHolder.StatusSaldo.setText(Array.StatusSaldo.get(position));
        if(Array.TipServSaldo.get(position).equals("1") || Array.TipServSaldo.get(position).equals("3")){
            viewHolder.ServicioImagen.setImageResource(R.drawable.tele);
        }else if (Array.TipServSaldo.get(position).equals("2")){
            viewHolder.ServicioImagen.setImageResource(R.drawable.internet);
        }else{
            viewHolder.ServicioImagen.setImageResource(R.drawable.tele);
        }

    }
}
