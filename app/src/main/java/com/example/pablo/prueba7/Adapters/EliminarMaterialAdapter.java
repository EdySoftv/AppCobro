package com.example.pablo.prueba7.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pablo.prueba7.R;

import java.util.ArrayList;

public class EliminarMaterialAdapter extends RecyclerView.Adapter<EliminarMaterialAdapter.materialesViewHolder> {

    ArrayList<Integer>material;
    Context context;
    ConstraintLayout constraintLayout;

    public static  class materialesViewHolder extends  RecyclerView.ViewHolder{

        public static ImageButton elimarMaterial;
        public materialesViewHolder( View v) {
            super(v);
            elimarMaterial = itemView.findViewById(R.id.eliminarMaterial);

        }
    }
    public EliminarMaterialAdapter(ArrayList<Integer> material, Context context){
        this.material=material;
        this.context=context;
    }
    @Override
    public int getItemCount() {
        return material.size();
    }

    @NonNull
    @Override
    public materialesViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_tabla_eliminar_list,viewGroup,false);

        return new materialesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(materialesViewHolder viewHolder, final int position) {

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) materialesViewHolder.elimarMaterial.getLayoutParams();
        layoutParams.height=130;
        materialesViewHolder.elimarMaterial.setLayoutParams(layoutParams);
        materialesViewHolder.elimarMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,String.valueOf(position) ,Toast.LENGTH_SHORT ).show();
            }
        });

    }

}
