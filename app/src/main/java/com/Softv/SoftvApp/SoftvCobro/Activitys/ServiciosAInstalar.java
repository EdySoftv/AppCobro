package com.Softv.SoftvApp.SoftvCobro.Activitys;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvCobro.Adapters.DetallesAdapter;
import com.Softv.SoftvApp.SoftvCobro.Listas.Array;
import com.Softv.SoftvApp.SoftvCobro.R;
import com.Softv.SoftvApp.SoftvCobro.Request.Request;
import com.Softv.SoftvApp.SoftvCobro.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Util;

import org.json.JSONObject;


public class ServiciosAInstalar extends AppCompatActivity {
    private Request request = new Request();
    private RecyclerView Detalles;
    private TextView Total;
    private EditText abono;
    private DetallesAdapter DetAdapter;
    private Button Abonar;
    private float PorAbonar = 0;
    private ProgressDialog dialogInicio;

    protected void onCreate(Bundle onSaveInstanceState) {
        super.onCreate(onSaveInstanceState);
        setContentView(R.layout.activity_serviciosinstalar);

        dialogInicio = new BarraCargar().showDialog(this);

        Detalles = findViewById(R.id.listaDetalles);
        Total = findViewById(R.id.saldo);
        Abonar = findViewById(R.id.cerrar);
        abono = findViewById(R.id.editTextNumberDecimal);

        Total.setText(request.TotalSaldo);

        Detalles.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ServiciosAInstalar.this);
        DetAdapter=new DetallesAdapter(ServiciosAInstalar.this, Array.DescripcionSaldo,Array.OperacionSaldo,Array.MontoSaldo);
        Detalles.setLayoutManager(layoutManager);
        Detalles.setAdapter(DetAdapter);

        Abonar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PorAbonar = Float.parseFloat(String.valueOf(abono.getText()));
                } catch (NumberFormatException ex) {
                }
                if(PorAbonar <= 0){
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Debe ingresar una cantidad mayor a cero", Toast.LENGTH_SHORT);toast1.show();
                }else{
                    Mensaje();
                }
                //Toast toast1 = Toast.makeText(getApplicationContext(), String.valueOf(PorAbonar), Toast.LENGTH_SHORT);toast1.show();

            }
        });

    }

    public void onBackPressed() {
        finish();
    }

    private void Mensaje(){
        new AlertDialog.Builder(this)
                .setTitle("ATENCIÓN")
                .setMessage("Se abonará $ " + String.valueOf(PorAbonar) + " al saldo consultado")
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("Clv_Session", Integer.valueOf(request.Session));
                                    jsonObject.put("AbonoCuenta", PorAbonar);
                                    dialogInicio.show();
                                    request.GuardarAbono(getApplicationContext(), jsonObject, dialogInicio, PorAbonar);
                                    //dialogoGuardar(ctx);
                                }catch (Exception x){
                                    Toast toast2 = Toast.makeText(getApplicationContext(), "Error al actualizar la session", Toast.LENGTH_SHORT);
                                    toast2.show();
                                }
                            }
                        })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();
    }

}
