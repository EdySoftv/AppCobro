package com.example.pablo.prueba7.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.Util;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

public class EliminarMaterialAdapter extends RecyclerView.Adapter<EliminarMaterialAdapter.materialesViewHolder> {

    ArrayList<ArrayList<String>>material;
    Context context;
    Activity activity;
    Request request = new Request();
    public static int anchoD=0,anchoC=0,anchoE=0,anchoA=0,posi;
    public static final int type_head =0,type_list=1;
    public static boolean baccion=false,bdescripcion=false,bcantidad=false,bext=false;

    public static  class materialesViewHolder extends  RecyclerView.ViewHolder{

        int view_type;

        public static TextView descripcion,cantidad,ext,haccion,hdescripcion,hcantidad,hext;
        public static ImageButton elimarMaterial;
        public materialesViewHolder( View v, int viewtype) {
            super(v);
            if(viewtype==type_list){
                elimarMaterial = itemView.findViewById(R.id.eliminarMaterial);
                descripcion = itemView.findViewById(R.id.descripcionDescarga);
                cantidad = itemView.findViewById(R.id.cantidadDescarga);
                ext = itemView.findViewById(R.id.extencionDescarga);
                view_type=1;
            }else if(viewtype==type_head){
                haccion = itemView.findViewById(R.id.txtAccion);
                hdescripcion = itemView.findViewById(R.id.txtDescripcion);
                hcantidad = itemView.findViewById(R.id.txtCantidad);
                hext = itemView.findViewById(R.id.txtNoExt);
                view_type=0;
            }



            //anchoA = TextoGetTextSize("Acción", (int) haccion.getTextSize());
            //baccion=true;


            for(int i=0; i<Array.listaTabla.size();i++){
                if(anchoD<=obtenerLargoPixelesTexto(Array.listaTabla.get(i).get(0))){
                    anchoD=obtenerLargoPixelesTexto(Array.listaTabla.get(i).get(0));
                }
                if(anchoC<=obtenerLargoPixelesTexto(Array.listaTabla.get(i).get(1))){
                    anchoC=obtenerLargoPixelesTexto(Array.listaTabla.get(i).get(1));
                }
                if(anchoE<=obtenerLargoPixelesTexto(Array.listaTabla.get(i).get(2))){
                    anchoE=obtenerLargoPixelesTexto(Array.listaTabla.get(i).get(2));
                }
            }


        }
    }
    public EliminarMaterialAdapter(ArrayList<ArrayList<String>> material, Context context, Activity activity){
        this.material=material;
        this.context=context;
        this.activity=activity;
    }
    @Override
    public int getItemCount() {
        return material.size()+1;
    }

    @NonNull
    @Override
    public materialesViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {

        View v;
        materialesViewHolder materialesviewHolder;

        if(position==type_list){
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_tabla_eliminar_list,viewGroup,false);
            materialesviewHolder = new materialesViewHolder(v, position);
            return materialesviewHolder;
        }else if (position==type_head){
            v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_header_eliminar_list,viewGroup,false);
            materialesviewHolder = new materialesViewHolder(v, position);
            return materialesviewHolder;
        }
        return null;

    }

    @Override
    public void onBindViewHolder(materialesViewHolder viewHolder, final int position) {


        if(viewHolder.view_type==type_list){

            /*ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) materialesViewHolder.elimarMaterial.getLayoutParams();
            layoutParams.width=anchoA;
            materialesViewHolder.elimarMaterial.setLayoutParams(layoutParams);*/

            materialesViewHolder.descripcion.setText(Array.listaTabla.get(position-1).get(0));
            materialesViewHolder.descripcion.setWidth(anchoD);

            materialesViewHolder.cantidad.setText(Array.listaTabla.get(position-1).get(1));
            materialesViewHolder.cantidad.setWidth(anchoC);

            materialesViewHolder.ext.setText(Array.listaTabla.get(position-1).get(2));
            materialesViewHolder.ext.setWidth((anchoE));



            materialesViewHolder.elimarMaterial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try{
                        JSONObject jsonObject = new JSONObject();

                        if(Util.getTipoDescarga(Util.preferences).equals("Q")){
                            jsonObject.put("clvOrden", Util.getClvQueja(Util.preferences));
                        }else if(Util.getTipoDescarga(Util.preferences).equals("O")){
                            jsonObject.put("clvOrden", Util.getClvOrden(Util.preferences));
                        }
                        jsonObject.put("noArticulo",Array.listaTabla.get(position-1).get(3) );
                        request.eliminarPreDescarga(context,jsonObject,activity );
                    }catch (Exception e) {
                        /*try {
                            JSONObject jsonObject = new JSONObject();

                            if (Util.getTipoDescarga(Util.preferences).equals("Q")) {
                                jsonObject.put("clvOrden", Util.getClvQueja(Util.preferences));
                            } else if (Util.getTipoDescarga(Util.preferences).equals("O")) {
                                jsonObject.put("clvOrden", Util.getClvOrden(Util.preferences));
                            }
                            jsonObject.put("noArticulo", Array.listaTabla.get(0).get(3));
                            request.eliminarPreDescarga(context, jsonObject, activity);
                        }catch (Exception x){}*/
                    }


                }
            });
        }else if(viewHolder.view_type==type_head){
            if(anchoD<=obtenerLargoPixelesTexto("Descripcion")){
                anchoD=obtenerLargoPixelesTexto("Descripcion");
            }
            materialesViewHolder.hdescripcion.setWidth(anchoD);
            if(anchoC<=obtenerLargoPixelesTexto("Cantidad")){
                anchoC= obtenerLargoPixelesTexto("Cantidad");
            }
            materialesViewHolder.hcantidad.setWidth(anchoC);

            if(anchoE<=obtenerLargoPixelesTexto("No.--Ext")){
                anchoE=obtenerLargoPixelesTexto("No.--Ext");
            }
            materialesViewHolder.hext.setWidth(anchoE);
          /*  ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) materialesViewHolder.haccion.getLayoutParams();
            Log.d("with", String.valueOf(materialesViewHolder.haccion.getWidth()));

            anchoA=materialesViewHolder.haccion.getWidth();*/

        }




    }
    public static int obtenerLargoPixelesTexto(String texto)
    {
        Paint p = new Paint();
        Rect bounds = new Rect();
        p.setTextSize(65);
        p.getTextBounds(texto, 0, texto.length(), bounds);
        return bounds.width();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return type_head;
        return type_list;
    }
}