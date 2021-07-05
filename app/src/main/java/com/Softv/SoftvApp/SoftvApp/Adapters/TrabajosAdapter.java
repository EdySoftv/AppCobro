package com.Softv.SoftvApp.SoftvApp.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.Activitys.CambioAparato;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetBUSCADetOrdSerListResult;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import static com.Softv.SoftvApp.SoftvApp.Listas.Array.clavex;
import static com.Softv.SoftvApp.SoftvApp.Listas.Array.recibixnew;

import static com.Softv.SoftvApp.SoftvApp.Services.Services.jsonArrayap;
import static com.Softv.SoftvApp.SoftvApp.Services.Services.jsonObject;

public class TrabajosAdapter extends BaseAdapter {
    private LayoutInflater inflatertrab;
    private Context Cmcontext;
    public Activity activity;
    public static int Clave, isnet,clvTra;
    public String observacionesRwtiro;
    public static  int lugar;
    public static boolean stat, validarCoordenadas=false;
    public static int ClaveTrabajo;
    public static int ftth=0;
    public static  boolean retiro = false, ISDIG=false, ASIG=false;
    public static String descr;
    public static boolean rapg =false;
    public static ProgressDialog dialogTrabajos;
    private Request request=new Request();
    BarraCargar barraCargar = new BarraCargar();


    public TrabajosAdapter(Context context, ArrayList<String>trabajox, ArrayList<String>accionx, ArrayList<Boolean>recibix){
       // activity=activity;
        Cmcontext=context;
        inflatertrab=LayoutInflater.from(Cmcontext);
        inflatertrab=LayoutInflater.from(context);
    }
    public class viewHolder{
        TextView trabajo,control,observacionesTrabajo;
        CheckBox recibi;
        TextView recibitext;
    }
    @Override
    public int getCount() {
        return Array.trabajox.size();
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
        lugar=position;
        dialogTrabajos= new BarraCargar().showDialog(Cmcontext);
try{
    dialogTrabajos.dismiss();
}catch (Exception e){}
        if (convertView == null) {
            holder = new viewHolder();
            convertView=inflatertrab.inflate(R.layout.recycler_trabajo,null);
            holder.trabajo=(TextView)convertView.findViewById(R.id.tv_Descripcion);
            //holder.accion=(TextView)convertView.findViewById(R.id.tv_Accion);
            holder.recibi=(CheckBox)convertView.findViewById(R.id.check_recibi);
            holder.control=(TextView)convertView.findViewById(R.id.click);
            holder.recibitext = (TextView)convertView.findViewById(R.id.recibitext);
            holder.observacionesTrabajo = (TextView)convertView.findViewById(R.id.observacionesTrabajo);
            convertView.setTag(holder);
        }
        else {
            holder=(viewHolder)convertView.getTag();
        }
        holder.trabajo.setText(Array.trabajox.get(position));
        //holder.accion.setText(Array.accionx.get(position));
        holder.observacionesTrabajo.setText(Array.observacionesx.get(position));
        String palabra = holder.trabajo.getText().toString();
        String[] caracteres = palabra.split(" ");

        if(caracteres[0].equals("DESCO")||
                caracteres[0].equals("DPAQU")||
                caracteres[0].equals("DPAQT")||
                caracteres[0].equals("DESTD")||
                caracteres[0].equals("DESFIS")||
                caracteres[0].equals("DESCB")||
                caracteres[0].equals("RSTVA")||
                caracteres[0].equals("RSNET")||
                caracteres[0].equals("RSDIG")||
                caracteres[0].equals("RECON")){
            request.Desc = true;
        }

        if (caracteres[0].equals("RAPAG")){
            recibixnew.clear();
            retiro = true;
            holder.recibi.setChecked(false);
            holder.recibi.setVisibility(View.VISIBLE);
            holder.recibitext.setVisibility(View.VISIBLE);
            rapg=false;
        }

//        if (caracteres[0].equals("RETLI")){
//            recibixnew.clear();
//            retiro = true;
//            holder.recibi.setChecked(true);
//            holder.recibi.setVisibility(View.INVISIBLE);
//            holder.recibitext.setVisibility(View.INVISIBLE);
//        }

        holder.recibi.setChecked(Array.recibix.get(position));
        rapg=true;



        holder.recibi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Iterator<List<GetBUSCADetOrdSerListResult>> itData1 = Array.dataTrabajos.iterator();
        List<GetBUSCADetOrdSerListResult> dat1 = (List<GetBUSCADetOrdSerListResult>) itData1.next();




        if(holder.recibi.isChecked()){
            dat1.get(position).setSeRealiza(true);
            recibixnew=new ArrayList<>();
            for(int i=0;i<dat1.size();i++){
                if (retiro==true){
                    recibixnew.add(dat1.get(i).getSeRealiza());
                }
            }
        }else{
            dat1.get(position).setSeRealiza(false);
            recibixnew=new ArrayList<>();
            for(int i=0;i<dat1.size();i++){
                if (retiro==true){
                    recibixnew.add(dat1.get(i).getSeRealiza());
                }
            }
        }

    }
});

        holder.control.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                request.Cambio = 0;
                isnet=0;
                ISDIG=false;
                ClaveTrabajo = Array.clavex.get(position);

                String palabra = holder.trabajo.getText().toString();
                String[] caracteres = palabra.split(" ");
                Log.d("caracteres0",caracteres[0]);
                Log.d("caracteres1",caracteres[1]);
                Request request = new Request();

                //Instalacion
                //Instalacion
                //ISTVA - Instalación de Servicio de TV
                if (caracteres[0].equals("ISTVA") || caracteres[0].equals("ASTVA")) {
                    validarCoordenadas=true;
                    retiro = false;
                    dialogTrabajos.show();
                    if(caracteres[0].equals("ASTVA"))ASIG = true;
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                }
                //ISNET - Instalación de Servicio de Internet
                if (caracteres[0].equals("ISNET") || caracteres[0].equals("ASNET")) {
                    validarCoordenadas=true;
                    retiro = false;
                    dialogTrabajos.show();
                    if(caracteres[0].equals("ASNET"))ASIG = true;
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                    isnet=1;
                }
                //ISDIG - Instalación de Servicio Digital
                if (caracteres[0].equals("ISDIG") || caracteres[0].equals("ASDIG")) {
                    validarCoordenadas=true;
                    retiro = false;
                    ISDIG=true;
                    dialogTrabajos.show();
                    if(caracteres[0].equals("ASDIG"))ASIG = true;
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                }
                //CTCTV - Cambio Tecnologia Tv
                if (caracteres[0].equals("CTCTV")) {
                    validarCoordenadas=true;
                    retiro = false;
                    dialogTrabajos.show();
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                }
                //CTCIN - Cambio Tecnologia Internet
                if (caracteres[0].equals("CTCIN")) {
                    validarCoordenadas=true;
                    retiro = false;
                    dialogTrabajos.show();
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                }
                //CTCDG - Cambio de Tecnología de Servicio Digital
                if (caracteres[0].equals("CTCDG")) {
                    validarCoordenadas=true;
                    retiro = false;
                    dialogTrabajos.show();
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                }
                //RSTVA - Reinstalacion de servicio de TV
                if (caracteres[0].equals("RSTVA")) {
                    validarCoordenadas=true;
                    retiro = false;
                    dialogTrabajos.show();
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                }
                //RSNET - Reinstalacion de servicio de Internet
                if (caracteres[0].equals("RSNET")) {
                    validarCoordenadas=true;
                    retiro = false;
                    dialogTrabajos.show();
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                }
                //RSDIG - Reinstalacion de servicio de Tv Digital
                if (caracteres[0].equals("RSDIG")) {
                    validarCoordenadas=true;
                    retiro = false;
                    dialogTrabajos.show();
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                }


                //Cambio Aparato
                //CAPAG - Cambio de aparato
                if (caracteres[0].equals("CAPAG")) {
                    validarCoordenadas=false;
                    retiro = false;
                    dialogTrabajos.show();
                    Intent intento = new Intent(Cmcontext, CambioAparato.class);
                    ftth=1;
                    intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Cmcontext.startActivity(intento);
                }
                //CAPAT - Cambio de Tipo de Aparato
                if (caracteres[0].equals("CAPAT")) {
                    validarCoordenadas=false;
                    retiro = false;
                    dialogTrabajos.show();
                    Intent intento = new Intent(Cmcontext, CambioAparato.class);
                    intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Cmcontext.startActivity(intento);
                    ftth=0;
                }

                //Cambio Domicilio
                //CAMDO - Traslado Externo de Servicio
                if (caracteres[0].equals("CAMDO")) {
                    validarCoordenadas=true;
                    retiro = false;
                    dialogTrabajos.show();
                    request.getCAMDO(Cmcontext);
                }

                //Extenciones Adicionales
                //CANEX - Cancelación De Extensión
                if (caracteres[0].equals("CONEX")) {
                    validarCoordenadas = false;
                    retiro = false;
                    dialogTrabajos.show();
                    if (request.PermCableCentro == true){
                        //Toast.makeText(Cmcontext, "Cambio", Toast.LENGTH_LONG).show();
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden", Util.getClvOrden(Util.preferences));
                        request.PreguntaMedios(Cmcontext, jsonObject);
                    } catch (Exception e) {
                    }
                }else
                        request.getExtencionesAdicionales(Cmcontext);
                }

            }
        });
        return convertView;
    }
    public void norec(){
        Iterator<List<GetBUSCADetOrdSerListResult>> itData1 = Array.dataTrabajos.iterator();
        List<GetBUSCADetOrdSerListResult> dat1 = (List<GetBUSCADetOrdSerListResult>) itData1.next();
        for(int i=0;i<dat1.size();i++){
        stat=(recibixnew.get(i));
            Clave =Integer.valueOf( clavex.get(i));
            clvTra=Integer.valueOf(Array.clv_trabajox.get(i));
            observacionesRwtiro = (Array.observacionesx.get(i));
            descr=String.valueOf(Array.trabajox.get(i));


        if (stat==false ){
            System.out.println("statusx"+stat);
        try{
            jsonObject = new JSONObject();
            jsonObject.put("Clave", Clave);
            jsonObject.put("Clv_Orden",  Util.getClvOrden(Util.preferences));
            jsonObject.put("Clv_Trabajo", clvTra);
            jsonObject.put("Descripcion", descr);
            jsonObject.put("Obs", observacionesRwtiro);
            jsonObject.put("SeRealiza", true);
            jsonObject.put("recibi", stat);
            jsonArrayap.put(jsonObject);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
    }

}
