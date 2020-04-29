package com.Softv.SoftvApp.SoftvApp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Modelos.SelectRelTecnicoCuadrillaResult;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;

public class TecnicosSecundariosAdapter extends RecyclerView.Adapter<TecnicosSecundariosAdapter.tecnicoCuadrillaViewHolder>
implements AdapterView.OnItemClickListener {

    private LayoutInflater inflater;
    private Context mContext;
    private Request request=new Request();
    public static ArrayList<Integer> tecnicosSelect=new ArrayList<>();
    Activity activity;
    //String Nombre="";

    public static class tecnicoCuadrillaViewHolder extends RecyclerView.ViewHolder{
        private CheckBox selecion;
        public tecnicoCuadrillaViewHolder (View v){
            super(v);
            selecion = itemView.findViewById(R.id.checkBox);


        }
    }

    public TecnicosSecundariosAdapter(Context mContext,Activity activity){
        this.mContext = mContext;
        this.activity= activity;
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("APP","Click");
    }
    @Override
    public int getItemCount() {
        return Array.nom_tecnicoSecundario.size();
    }

    @NonNull
    @Override
    public tecnicoCuadrillaViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_tecnicos_secundarios_list,viewGroup,false);

        return new tecnicoCuadrillaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(tecnicoCuadrillaViewHolder viewHolder, final int position) {

        if(Array.nom_tecnicoSecundario.size()!=0){
            if(position==0){
                viewHolder.selecion.setVisibility(View.GONE);
            }else{
                Iterator<List<SelectRelTecnicoCuadrillaResult>> itdata = Array.dataCuadrilla.iterator();
                    List<SelectRelTecnicoCuadrillaResult> dat = itdata.next();
                for(int a=0;a<dat.size();a++){
                    if(Array.clv_tecnicoSecundario.get(position)==dat.get(a).ClvTecnico){
                        viewHolder.selecion.setChecked(true);
                        tecnicosSelect.add(Array.clv_tecnicoSecundario.get(position));
                    }
                }
                viewHolder.selecion.setText(Array.nom_tecnicoSecundario.get(position));
            }

            viewHolder.selecion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        tecnicosSelect.add(Array.clv_tecnicoSecundario.get(position));
                    }else{
                        tecnicosSelect.remove(Array.clv_tecnicoSecundario.get(position));
                    }
                }
            });
           /* viewHolder.poste.setText(Array.tapFiltradoPoste.get(position));

            viewHolder.controlaNAPTAP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(NAPTAP.cordLatTN.equals("")||NAPTAP.cordLatTN.equals(null)||NAPTAP.cordLongTN.equals("")||NAPTAP.cordLongTN.equals(null)){
                        dialogocoordenadas(activity);
                    }else{
                        dialogomandarCoordenadas(mContext,activity,Array.tapFiltrado.get(position), Integer.valueOf(Array.idTapCoordenadas.get(position)));
                    }
                }
            });*/
        }else{
            Toast.makeText(mContext,"No existen tecnicos secundarios", Toast.LENGTH_LONG).show();
        }



    }

}
