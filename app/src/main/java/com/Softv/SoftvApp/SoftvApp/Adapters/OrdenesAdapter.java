package com.Softv.SoftvApp.SoftvApp.Adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONObject;

import java.util.ArrayList;


public class OrdenesAdapter extends RecyclerView.Adapter<OrdenesAdapter.ordensrcViewHolder> implements AdapterView.OnItemClickListener {

    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<String> ordensrc;
    private ArrayList<String>contratosrc;
    private ArrayList<String>nombresrc;
    private ArrayList<String>statusrc;
    private ArrayList<String>direccionsrc;
    private ArrayList<String>precintosrc;
    private Request request=new Request();
    public static ArrayList<Integer> tecnicosSelect=new ArrayList<>();


    public static  class ordensrcViewHolder extends  RecyclerView.ViewHolder{
        private TextView status,contrato1,nombre,direccionOrd,orden,control,noOrden,txtnap,txttap,txttrabajo,napordenes,tapordenes,trabajoordenes,fecha, precinto, txtpre;

        public ordensrcViewHolder( View v) {
            super(v);
            status=(TextView)itemView.findViewById(R.id.tv_estatus);
            orden=(TextView)itemView.findViewById(R.id.tv_NOrden);
            contrato1=(TextView)itemView.findViewById(R.id.tv_NContrato);
            nombre=(TextView)itemView.findViewById(R.id.tv_Nombre);
            direccionOrd=(TextView)itemView.findViewById(R.id.id_direccion);
            control=(TextView)itemView.findViewById(R.id.controla);
            noOrden=itemView.findViewById(R.id.noOrden);
            txtnap = itemView.findViewById(R.id.txtNap);
            txttap = itemView.findViewById(R.id.txtTap);
            txttrabajo = itemView.findViewById(R.id.txtTrabajo);
            napordenes = itemView.findViewById(R.id.id_NapOrdenes);
            tapordenes = itemView.findViewById(R.id.id_TapOrdenes);
            trabajoordenes = itemView.findViewById(R.id.id_TrabajoOrdenes);
            fecha  = itemView.findViewById(R.id.id_FechaListado);
            precinto  = itemView.findViewById(R.id.id_PrecintoListado);
            txtpre = itemView.findViewById(R.id.txtFechaListado5);
        }
    }

    public OrdenesAdapter(Context mContext, ArrayList<String>ordensrc, ArrayList<String>nombrex, ArrayList<String>contratosrc, ArrayList<String>statusrc, ArrayList<String>direccionsrc, ArrayList<String>precintosrc){
        this.ordensrc=ordensrc;
        this.contratosrc=contratosrc;
        this.nombresrc=nombrex;
        this.statusrc=statusrc;
        this.direccionsrc=direccionsrc;
        this.precintosrc=precintosrc;
        this.mContext=mContext;
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("APP","Click");
    }
    @Override
    public int getItemCount() {
        return ordensrc.size();
    }

    @NonNull
    @Override
    public ordensrcViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_oq,viewGroup,false);

        return new ordensrcViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ordensrcViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        viewHolder.noOrden.setText("No. de Orden");
        viewHolder.nombre.setText(Array.nombresrc.get(position));
        viewHolder.orden.setText(Array.ordensrc.get(position));
        viewHolder.contrato1.setText(Array.contratosrc.get(position));
        viewHolder.status.setText(Array.statusrc.get(position));
        viewHolder.direccionOrd.setText(Array.direccionsrc.get(position));
        viewHolder.fecha.setText(Array.fechasrc.get(position));
        viewHolder.precinto.setText(Array.precintosrc.get(position));


        if(Array.trabajosrc.get(position).equals("")||Array.trabajosrc.get(position).equals(null)){
            viewHolder.txttrabajo.setVisibility(View.GONE);
            viewHolder.trabajoordenes.setVisibility(View.GONE);
        }else{
            viewHolder.txttrabajo.setVisibility(View.VISIBLE);
            viewHolder.trabajoordenes.setVisibility(View.VISIBLE);
            viewHolder.trabajoordenes.setText(Array.trabajosrc.get(position));
        }


        if(Array.napsrc.get(position).equals("")||Array.napsrc.get(position).equals(null)){
            viewHolder.txtnap.setVisibility(View.GONE);
            viewHolder.napordenes.setVisibility(View.GONE);
        }else{
            viewHolder.txtnap.setVisibility(View.VISIBLE);
            viewHolder.napordenes.setVisibility(View.VISIBLE);
            viewHolder.napordenes.setText(Array.napsrc.get(position));
        }

        if(Array.tapsrc.get(position).equals("")||Array.tapsrc.get(position).equals(null)){
            viewHolder.txttap.setVisibility(View.GONE);
            viewHolder.tapordenes.setVisibility(View.GONE);
        }else{
            viewHolder.txttap.setVisibility(View.VISIBLE);
            viewHolder.tapordenes.setVisibility(View.VISIBLE);
            viewHolder.tapordenes.setText(Array.tapsrc.get(position));
        }

        /*if(request.PermPlaca == false){
            //viewHolder.txtpre.setVisibility(View.GONE);
            //viewHolder.precinto.setVisibility(View.GONE);
            viewHolder.txtpre.setVisibility(View.GONE);
        }else{
            viewHolder.txtpre.setVisibility(View.VISIBLE);
            viewHolder.precinto.setVisibility(View.VISIBLE);

        }*/

        viewHolder.control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tecnicosSelect.clear();

                Util.preferences = mContext.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                Util.editor = Util.preferences.edit();
                Util.editor.putInt("clvOrden", Integer.valueOf(ordensrc.get(position)));
                Util.editor.commit();

                if(Array.trabajosrc.get(position).contains("CAMDO")){
                    request.Cambio = 1;
                }

                request.NombreGeneral = Array.nombresrc.get(position);
                request.getDeepCons(mContext);
                request.getValidaFirma(mContext);

            }
        });

    }
}