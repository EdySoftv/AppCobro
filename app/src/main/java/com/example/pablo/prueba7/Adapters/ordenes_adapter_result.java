package com.example.pablo.prueba7.Adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;

import java.util.ArrayList;



public class ordenes_adapter_result extends BaseAdapter implements AdapterView.OnItemClickListener {

    public static Integer clvor;
    private LayoutInflater inflater;
    private Context mContext;
    private ArrayList<String> ordensrc;
    private ArrayList<String>contratosrc;
    private ArrayList<String>nombresrc;
    private ArrayList<String>statusrc;
    private ArrayList<String>direccionsrc;
    private Request request=new Request();

    public ordenes_adapter_result(Context context, ArrayList<String>ordensrc,ArrayList<String>nombrex,ArrayList<String>contratosrc,ArrayList<String>statusrc,ArrayList<String>direccionsrc){

        this.ordensrc=ordensrc;
        this.contratosrc=contratosrc;
        this.nombresrc=nombrex;
        this.statusrc=statusrc;
        this.direccionsrc=direccionsrc;

        mContext=context;
        inflater=LayoutInflater.from(mContext); }
        @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("APP","Click");
    }
    public  class viewHolder{
        private TextView status,contrato1,nombre,direccionOrd,orden,control;

    }
    @Override
    public int getCount() {
        return Array.ordensrc.size();

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
        final viewHolder holder;
        if (convertView == null) {
            holder = new viewHolder();

            convertView=inflater.inflate(R.layout.recycler_oq,null);

            holder.status=(TextView)convertView.findViewById(R.id.tv_estatus);
            holder.orden=(TextView)convertView.findViewById(R.id.tv_NOrden);
            holder.contrato1=(TextView)convertView.findViewById(R.id.tv_NContrato);
            holder.nombre=(TextView)convertView.findViewById(R.id.tv_Nombre);
            holder.direccionOrd=(TextView)convertView.findViewById(R.id.id_direccion);
            holder.control=(TextView)convertView.findViewById(R.id.controla);

            convertView.setTag(holder);
        }
        else {
            holder=(viewHolder)convertView.getTag();
        }

        holder.nombre.setText(Array.nombresrc.get(position));
        holder.orden.setText(Array.ordensrc.get(position));
        holder.contrato1.setText(Array.contratosrc.get(position));
        holder.status.setText(Array.statusrc.get(position));
        holder.direccionOrd.setText(Array.direccionsrc.get(position));
        holder.control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clvor = Integer.valueOf(ordensrc.get(position));
                request.getDeepCons(mContext);
                request.getValidaFirma(mContext);
            }
        });
        return convertView;
    }
}