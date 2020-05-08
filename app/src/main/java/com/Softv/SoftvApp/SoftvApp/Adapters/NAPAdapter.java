package com.Softv.SoftvApp.SoftvApp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.Activitys.NAPTAP;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;

import org.json.JSONObject;

public class NAPAdapter extends RecyclerView.Adapter<NAPAdapter.datoscoloniaNViewHolder> implements AdapterView.OnItemClickListener {

    private LayoutInflater inflater;
    private Context mContext;
    private Request request=new Request();
    Activity activity;
    String Nombre="";



    public static  class datoscoloniaNViewHolder extends  RecyclerView.ViewHolder{
        private TextView clvTecnica,poste,controlaNAPTAP,txtLatitud,txtLongitud,latitud,logitud;
        public datoscoloniaNViewHolder( View v) {
            super(v);
            clvTecnica=(TextView)itemView.findViewById(R.id.tv_ClvTecnica);
            poste=(TextView)itemView.findViewById(R.id.tv_Poste);
            controlaNAPTAP= (TextView)itemView.findViewById(R.id.controlaNAPTAP);

            txtLatitud=(TextView)itemView.findViewById(R.id.textview81);
            txtLongitud=(TextView)itemView.findViewById(R.id.textview82);
            latitud= (TextView)itemView.findViewById(R.id.tv_LatitudNapTap);
            logitud= (TextView)itemView.findViewById(R.id.tv_LogitudNapTap);

        }
    }

    public NAPAdapter(Context mContext,Activity activity,String nombre){
        this.mContext=mContext;
        this.activity=activity;
        this.Nombre = nombre;
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("APP","Click");
    }
    @Override
    public int getItemCount() {
        return Array.tapFiltrado.size();
    }

    @NonNull
    @Override
    public datoscoloniaNViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_naptap,viewGroup,false);

        return new datoscoloniaNViewHolder(v);
    }

    @Override
    public void onBindViewHolder(datoscoloniaNViewHolder viewHolder, final int position) {


        if(Array.tapFiltrado.size()!=0){
            viewHolder.clvTecnica.setText(Array.tapFiltrado.get(position));
            viewHolder.poste.setText(Array.tapFiltradoPoste.get(position));

            if(Array.tapLatitud.get(position)=="0"){
                viewHolder.txtLatitud.setVisibility(View.GONE);
                viewHolder.latitud.setVisibility(View.GONE);
                //viewHolder.latitud.setText("No cuenta con latitud");
            }else{
                viewHolder.txtLatitud.setVisibility(View.VISIBLE);
                viewHolder.latitud.setVisibility(View.VISIBLE);
                viewHolder.latitud.setText(Array.tapLatitud.get(position));
            }



            if(Array.tapLongitud.get(position)=="0"){
                viewHolder.txtLongitud.setVisibility(View.GONE);
                viewHolder.logitud.setVisibility(View.GONE);
                //viewHolder.logitud.setText("No cuenta con longitud");
            }else{
                viewHolder.txtLongitud.setVisibility(View.VISIBLE);
                viewHolder.logitud.setVisibility(View.VISIBLE);
                viewHolder.logitud.setText(Array.tapLongitud.get(position));
            }

            viewHolder.controlaNAPTAP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(NAPTAP.cordLatTN.equals("")||NAPTAP.cordLatTN.equals(null)||NAPTAP.cordLongTN.equals("")||NAPTAP.cordLongTN.equals(null)){
                        dialogocoordenadas(activity);
                    }else{

                        if(Array.tapLatitud.get(position).equals("0") && Array.tapLongitud.get(position).equals("0")){
                            dialogomandarCoordenadas(mContext,activity,Array.tapFiltrado.get(position), Integer.valueOf(Array.idTapCoordenadas.get(position)));
                        }else{
                            dialogoactualizarCoordenadas(mContext,activity,Array.tapFiltrado.get(position), Integer.valueOf(Array.idTapCoordenadas.get(position)));
                        }

                    }
                }
            });
        }else{
            Toast.makeText(mContext,"Seleccione una colonia",Toast.LENGTH_LONG).show();
        }



    }
    public void dialogocoordenadas(final Activity activity) {
        new AlertDialog.Builder(activity,R.style.InvitationDialog)
                .setTitle("Coordenadas")
                .setMessage("Aun no se han obtenido las coordenadas")
                .setPositiveButton("Esperar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                            }
                        })
                .setNegativeButton("",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
    }

    public void dialogomandarCoordenadas(final Context context,final Activity activity,final String clvTecnica,final Integer idnap) {
        final String Latitud = NAPTAP.cordLatTN;
        final String Logitud = NAPTAP.cordLongTN;
        new AlertDialog.Builder(activity,R.style.InvitationDialog)
                .setTitle("Agregar Coordenadas")
                .setMessage("Desea agregar las coordenadas ( Latitud: "+Latitud + " Logitud: "+ Logitud+" ) para el "+Nombre+" "+clvTecnica)
                .setPositiveButton("Confirmar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            try{
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("idNap",idnap);
                                jsonObject.put("latitud",Latitud);
                                jsonObject.put("longitud",Logitud);
                                request.setNAPCoo(context,jsonObject);
                            }catch (Exception e){}

                            }
                        })
                .setNegativeButton("Negar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
    }

    public void dialogoactualizarCoordenadas(final Context context,final Activity activity,final String clvTecnica,final Integer idnap) {
        final String Latitud = NAPTAP.cordLatTN;
        final String Logitud = NAPTAP.cordLongTN;

        new AlertDialog.Builder(activity, R.style.InvitationDialog)
                .setTitle("Actualizar Coordenadas")
                .setMessage("Desea actualizar las coordenadas (Nuevas coordenadas Latitud: " + Latitud + " Logitud: " + Logitud + " ) para el " + Nombre + " " + clvTecnica)
                .setPositiveButton("Confirmar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("idNap",idnap);
                                    jsonObject.put("latitud",Latitud);
                                    jsonObject.put("longitud",Logitud);
                                    request.setNAPCoo(context,jsonObject);
                                }catch (Exception e){}

                            }
                        })
                .setNegativeButton("Negar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
    }
}
