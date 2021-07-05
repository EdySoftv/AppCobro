package com.Softv.SoftvApp.SoftvApp.Adapters;


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

import com.Softv.SoftvApp.SoftvApp.Activitys.Inicio;
import com.Softv.SoftvApp.SoftvApp.Activitys.ServiciosSaldo;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONObject;

import java.util.ArrayList;


public class ClientesAdapter extends RecyclerView.Adapter<ClientesAdapter.ListClientViewHolder> implements AdapterView.OnItemClickListener {

    private LayoutInflater inflater;
    private Context mContext;
    public static ArrayList<String>ContratoCompuesto;
    public static ArrayList<String>Nombre;
    public static ArrayList<String>Telefono;
    public static ArrayList<String>Calle_Numero;
    public static ArrayList<String>Colonia;
    public static ArrayList<String>Contrato;
    private Request request=new Request();


    public static  class ListClientViewHolder extends  RecyclerView.ViewHolder{
        private TextView ContratoCompuesto,Nombre,Telefono,Calle_Numero,Colonia;
        public ListClientViewHolder( View v) {
            super(v);
            ContratoCompuesto=(TextView)itemView.findViewById(R.id.ContratoCompuesto);
            Nombre=(TextView)itemView.findViewById(R.id.Nombre);
            Telefono=(TextView)itemView.findViewById(R.id.Telefono);
            Calle_Numero=(TextView)itemView.findViewById(R.id.Calle_Numero);
            Colonia=(TextView)itemView.findViewById(R.id.Colonia);
        }
    }

    public ClientesAdapter(Context mContext, ArrayList<String> ContratoCompuesto, ArrayList<String> Nombre, ArrayList<String> Telefono, ArrayList<String> Calle_Numero, ArrayList<String> Colonia, ArrayList<String> Contrato){
        this.ContratoCompuesto=ContratoCompuesto;
        this.Nombre=Nombre;
        this.Telefono=Telefono;
        this.Calle_Numero=Calle_Numero;
        this.Colonia=Colonia;
        this.Contrato=Contrato;
        this.mContext=mContext;
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("APP","Click");
    }
    @Override
    public int getItemCount() {
        return Array.ContratoCompuestoList.size();
    }

    @NonNull
    @Override
    public ListClientViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_clientes,viewGroup,false);

        return new ListClientViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListClientViewHolder viewHolder, final int position) {

        viewHolder.ContratoCompuesto.setText(Array.ContratoCompuestoList.get(position));
        viewHolder.Nombre.setText(Array.NombreList.get(position));
        viewHolder.Telefono.setText(Array.TelefonoList.get(position));
        viewHolder.Calle_Numero.setText(Array.CalleNumeroList.get(position));
        viewHolder.Colonia.setText(Array.ColoniaList.get(position));

        viewHolder.ContratoCompuesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    request.ContratoCompuestoSaldo=Array.ContratoCompuestoList.get(position);
                    request.NombreSaldo=Array.NombreList.get(position);
                    request.TelefonoSaldo=Array.TelefonoList.get(position);
                    request.Calle_NumeroSaldo=Array.CalleNumeroList.get(position);
                    request.ColoniaSaldo=Array.ColoniaList.get(position);
                    request.ContratoSaldo=Array.ContratoList.get(position);
                    request.getServiciosSaldos(mContext, Array.ContratoList.get(position));
                }catch (Exception x){
                    Toast toast1 = Toast.makeText(mContext.getApplicationContext(), "Error al conseguir los servicios", Toast.LENGTH_SHORT);toast1.show();
                }
            }
        });

        viewHolder.Nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    request.ContratoCompuestoSaldo=Array.ContratoCompuestoList.get(position);
                    request.NombreSaldo=Array.NombreList.get(position);
                    request.TelefonoSaldo=Array.TelefonoList.get(position);
                    request.Calle_NumeroSaldo=Array.CalleNumeroList.get(position);
                    request.ColoniaSaldo=Array.ColoniaList.get(position);
                    request.ContratoSaldo=Array.ContratoList.get(position);
                    request.getServiciosSaldos(mContext, Array.ContratoList.get(position));
                }catch (Exception x){
                    Toast toast1 = Toast.makeText(mContext.getApplicationContext(), "Error al conseguir los servicios", Toast.LENGTH_SHORT);toast1.show();
                }
            }
        });

        viewHolder.Telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    request.ContratoCompuestoSaldo=Array.ContratoCompuestoList.get(position);
                    request.NombreSaldo=Array.NombreList.get(position);
                    request.TelefonoSaldo=Array.TelefonoList.get(position);
                    request.Calle_NumeroSaldo=Array.CalleNumeroList.get(position);
                    request.ColoniaSaldo=Array.ColoniaList.get(position);
                    request.ContratoSaldo=Array.ContratoList.get(position);
                    request.getServiciosSaldos(mContext, Array.ContratoList.get(position));
                }catch (Exception x){
                    Toast toast1 = Toast.makeText(mContext.getApplicationContext(), "Error al conseguir los servicios", Toast.LENGTH_SHORT);toast1.show();
                }
            }
        });

        viewHolder.Calle_Numero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    request.ContratoCompuestoSaldo=Array.ContratoCompuestoList.get(position);
                    request.NombreSaldo=Array.NombreList.get(position);
                    request.TelefonoSaldo=Array.TelefonoList.get(position);
                    request.Calle_NumeroSaldo=Array.CalleNumeroList.get(position);
                    request.ColoniaSaldo=Array.ColoniaList.get(position);
                    request.ContratoSaldo=Array.ContratoList.get(position);
                    request.getServiciosSaldos(mContext, Array.ContratoList.get(position));
                }catch (Exception x){
                    Toast toast1 = Toast.makeText(mContext.getApplicationContext(), "Error al conseguir los servicios", Toast.LENGTH_SHORT);toast1.show();
                }
            }
        });

        viewHolder.Colonia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    request.ContratoCompuestoSaldo=Array.ContratoCompuestoList.get(position);
                    request.NombreSaldo=Array.NombreList.get(position);
                    request.TelefonoSaldo=Array.TelefonoList.get(position);
                    request.Calle_NumeroSaldo=Array.CalleNumeroList.get(position);
                    request.ColoniaSaldo=Array.ColoniaList.get(position);
                    request.ContratoSaldo=Array.ContratoList.get(position);
                    request.getServiciosSaldos(mContext, Array.ContratoList.get(position));
                }catch (Exception x){
                    Toast toast1 = Toast.makeText(mContext.getApplicationContext(), "Error al conseguir los servicios", Toast.LENGTH_SHORT);toast1.show();
                }
            }
        });

    }
}