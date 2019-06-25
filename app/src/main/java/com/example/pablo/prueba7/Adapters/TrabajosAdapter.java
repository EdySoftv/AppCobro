package com.example.pablo.prueba7.Adapters;

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

import com.example.pablo.prueba7.Activitys.CambioAparato;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.GetBUSCADetOrdSerListResult;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.pablo.prueba7.Adapters.OrdenesAdapter.clvor;
import static com.example.pablo.prueba7.Listas.Array.clavex;
import static com.example.pablo.prueba7.Listas.Array.recibixnew;

import static com.example.pablo.prueba7.Services.Services.jsonArrayap;
import static com.example.pablo.prueba7.Services.Services.jsonObject;

public class TrabajosAdapter extends BaseAdapter {
    private LayoutInflater inflatertrab;
    private Context Cmcontext;
    private ArrayList<String>trabajox;
    private ArrayList<String>accionx;
    private ArrayList<Boolean>recibix;
    public static int Clave, isnet,clvTra;
    public static  int lugar;
    public static boolean stat;
    public static int ClaveTrabajo;
    public static int ftth=0;
    public static String descr;
    public static boolean rapg =false;
    public static ProgressDialog dialogTrabajos;
    BarraCargar barraCargar = new BarraCargar();


    public TrabajosAdapter(Context context, ArrayList<String>trabajox, ArrayList<String>accionx, ArrayList<Boolean>recibix){
        this.trabajox=trabajox;
        this.accionx=accionx;
        this.recibix=recibix;
        Cmcontext=context;
        inflatertrab=LayoutInflater.from(Cmcontext);
        inflatertrab=LayoutInflater.from(context);
    }
    public class viewHolder{
        TextView trabajo,accion,control;
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
            holder.accion=(TextView)convertView.findViewById(R.id.tv_Accion);
            holder.recibi=(CheckBox)convertView.findViewById(R.id.check_recibi);
            holder.control=(TextView)convertView.findViewById(R.id.click);
            holder.recibitext = (TextView)convertView.findViewById(R.id.recibitext);
            convertView.setTag(holder);
        }
        else {
            holder=(viewHolder)convertView.getTag();
        }
        holder.trabajo.setText(Array.trabajox.get(position));
        holder.accion.setText(Array.accionx.get(position));
        holder.recibi.setChecked(Array.recibix.get(position));
        rapg=true;
        holder.recibi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Iterator<List<GetBUSCADetOrdSerListResult>> itData1 = Array.dataTrabajos.iterator();
        List<GetBUSCADetOrdSerListResult> dat1 = (List<GetBUSCADetOrdSerListResult>) itData1.next();

        String palabra = holder.trabajo.getText().toString();
        String[] caracteres = palabra.split(" ");
        Log.d("caracteres0",caracteres[0]);
        Log.d("caracteres1",caracteres[1]);
        if(holder.recibi.isChecked()){
            dat1.get(position).setSeRealiza(true);
            recibixnew=new ArrayList<>();
            for(int i=0;i<dat1.size();i++){
               recibixnew.add(dat1.get(i).getSeRealiza());
            }
            if (!caracteres[0].equals("RAPAG")){
               // Toast.makeText(Cmcontext, "Acción no asignada", Toast.LENGTH_SHORT).show();
                holder.recibi.setChecked(false);
                holder.recibi.setVisibility(View.VISIBLE);
                holder.recibitext.setVisibility(View.VISIBLE);

                rapg=false;
            }
        }
    }
});
        ClaveTrabajo = Array.clavex.get(position);

        holder.control.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                isnet=0;

                String palabra = holder.trabajo.getText().toString();
                String[] caracteres = palabra.split(" ");
                Log.d("caracteres0",caracteres[0]);
                Log.d("caracteres1",caracteres[1]);
                Request request = new Request();
                if (caracteres[0].equals("ISTVA")) {
                    dialogTrabajos.show();
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden", OrdenesAdapter.clvor);
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                }
                if (caracteres[0].equals("ISNET")) {
                    dialogTrabajos.show();
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("clv_orden", OrdenesAdapter.clvor);
                        request.PreguntaMedios(Cmcontext,jsonObject);
                    }catch (Exception e){}
                    isnet=1;
                }
                if (caracteres[0].equals("CAPAG")) {
                    dialogTrabajos.show();
                    Intent intento = new Intent(Cmcontext, CambioAparato.class);
                    ftth=1;
                    Cmcontext.startActivity(intento);
                }
                if (caracteres[0].equals("CAMDO")) {
                    dialogTrabajos.show();
                    request.getCAMDO(Cmcontext);
                }
                if (caracteres[0].equals("CAPAT")) {
                    dialogTrabajos.show();
                    Intent intento = new Intent(Cmcontext, CambioAparato.class);
                    Cmcontext.startActivity(intento);
                    ftth=0;
                }
                if (caracteres[0].equals("CONEX")) {
                    dialogTrabajos.show();
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
            descr=String.valueOf(Array.trabajox.get(i));

        if (stat==false){
            System.out.println("statusx"+stat);
        try{

            jsonObject = new JSONObject();
            jsonObject.put("Clave", Clave);
            jsonObject.put("Clv_Orden", clvor);
            jsonObject.put("Clv_Trabajo", clvTra);
            jsonObject.put("Descripcion", descr);
            jsonObject.put("Obs", JSONObject.NULL);
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
