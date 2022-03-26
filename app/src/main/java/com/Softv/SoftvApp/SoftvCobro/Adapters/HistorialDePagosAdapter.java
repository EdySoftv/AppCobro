package com.Softv.SoftvApp.SoftvCobro.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvCobro.Listas.Array;
import com.Softv.SoftvApp.SoftvCobro.R;
import com.Softv.SoftvApp.SoftvCobro.Request.Request;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Constants;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HistorialDePagosAdapter extends RecyclerView.Adapter<HistorialDePagosAdapter.ListClientViewHolder> implements AdapterView.OnItemClickListener {

    private LayoutInflater inflater;
    private Context mContext;
    public static ArrayList<String> ContratoCompuesto;
    public static ArrayList<String>HoraConsulta;
    public static ArrayList<Float>Monto;
    private Request request=new Request();


    public static  class ListClientViewHolder extends  RecyclerView.ViewHolder{
        private TextView ContratoCompuesto,HoraConsulta,Monto,Toque;
        public ListClientViewHolder( View v) {
            super(v);
            ContratoCompuesto=(TextView)itemView.findViewById(R.id.ContratoCompuesto);
            HoraConsulta=(TextView)itemView.findViewById(R.id.Telefono);
            Monto=(TextView)itemView.findViewById(R.id.Calle_Numero);
        }
    }

    public HistorialDePagosAdapter(Context mContext, ArrayList<String> ContratoCompuesto, ArrayList<String> HoraConsulta, ArrayList<Float> Monto){
        this.ContratoCompuesto=ContratoCompuesto;
        this.HoraConsulta=HoraConsulta;
        this.Monto=Monto;
        this.mContext=mContext;
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("APP","Click");
    }
    @Override
    public int getItemCount() {
        return Array.ContratoHistorial.size();
    }

    @NonNull
    @Override
    public ListClientViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_historial,viewGroup,false);

        return new ListClientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListClientViewHolder viewHolder, final int position) {

        viewHolder.ContratoCompuesto.setText(Array.ContratoHistorial.get(position));
        viewHolder.HoraConsulta.setText(Array.HoraConsultaHistorial.get(position));

        DecimalFormat formato = new DecimalFormat(Constants.FORMATO);
        viewHolder.Monto.setText(formato.format(Array.MontoHistorial.get(position)));



    }
}
