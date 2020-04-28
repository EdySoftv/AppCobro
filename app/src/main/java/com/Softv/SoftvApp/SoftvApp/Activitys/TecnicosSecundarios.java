package com.Softv.SoftvApp.SoftvApp.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.Softv.SoftvApp.SoftvApp.Adapters.TecnicosSecundariosAdapter;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

public class TecnicosSecundarios extends AppCompatActivity {

    RecyclerView recyclerViewTecCuadrilla;
    TecnicosSecundariosAdapter adapter;
    Button aceptar,cancelar;
    public static ProgressDialog dialogTecCuadrilla;
    Request request = new Request();
    Activity activity = this;
    @Override
    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_tecnicos_secundarios);

        setTitle("No. Orden: " + Util.getClvOrden(Util.preferences));
        recyclerViewTecCuadrilla = findViewById(R.id.recyclerViewTecCuadrilla);
        aceptar = findViewById(R.id.aceptarTecCuadrilla);
        cancelar = findViewById(R.id.cancelarTecCuadrilla);
        dialogTecCuadrilla = new BarraCargar().showDialog(this);

        dialogTecCuadrilla.show();
        TecnicosSecundariosAdapter.tecnicosSelect.clear();


        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerViewTecCuadrilla.setLayoutManager(layoutManager);
        adapter= new TecnicosSecundariosAdapter(getApplicationContext(),getParent());
        recyclerViewTecCuadrilla.setAdapter(adapter);

        dialogTecCuadrilla.dismiss();
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONArray jsonArray = new JSONArray();

                    JSONObject jsonObject1 = new JSONObject();
                    for (int i = 0; i < TecnicosSecundariosAdapter.tecnicosSelect.size(); i++) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("ClvOrden",Util.getClvOrden(Util.preferences));
                        jsonObject.put("ClvTecnico",TecnicosSecundariosAdapter.tecnicosSelect.get(i));
                        jsonArray.put(i,jsonObject);
                    }
                    jsonObject1.put("xml",jsonArray);

                        request.addCuadrilla(getApplicationContext(),jsonObject1,activity);
                }catch (Exception e){}
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void onBackPressed() {
        finish();
    }
}
