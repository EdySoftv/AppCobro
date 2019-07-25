package com.example.pablo.prueba7.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.pablo.prueba7.Adapters.TablaAdapter;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.DescripcionArticuloModel;
import com.example.pablo.prueba7.Modelos.DetalleBitacoraModel;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;

import java.util.Iterator;
import java.util.List;

import static com.example.pablo.prueba7.Adapters.QuejasAdapter.statusQueja;
import static com.example.pablo.prueba7.Request.Request.extencionesMat;


/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialesReportes extends Fragment {
    private LayoutInflater inflater;
    private ViewGroup container;
    public static HorizontalScrollView horizontalScrollViewR;
    public static TableLayout tablaR;
    public static RecyclerView elimarMaterialesListR;
    Request request = new Request();
    EditText piezaR,mIIR,mIER,mFIR,mFER;
    public static int clvTipoDescMatR,idArticuloDMR,cantidadDMR,idInventarioMDR,piezaSerR, metrosR, totalDMR,IIDMR,IFDMR,EIMDR,EFDMR;
    public static int extSerR;
    public static Spinner descripcionMatR,clasificacionMatR,spinnerExtMatR;
    public static ConstraintLayout extMatR, piezasMatR,metrosMatR;
    Button agragarDMR;
    public static int posDescMatR,posClasMatR,posExtMatR;
    int seleccionR,seleccionExteR;
    public static ImageButton imageButtonR;


    public MaterialesReportes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.activity_descarga_reporte, container, false);
        request.getChecaExt(getContext());
        descripcionMatR = view.findViewById(R.id.descripcionArticuloDescR);
        clasificacionMatR = view.findViewById(R.id.clasificacionMatDescR);
        extMatR = view.findViewById(R.id.constrain_ExtencionesR);
        piezasMatR = view.findViewById(R.id.constrain_CantidadR);
        metrosMatR = view.findViewById(R.id.constrain_MetrajeR);
        spinnerExtMatR = view.findViewById(R.id.extencionesDescargaR);
        agragarDMR=view.findViewById(R.id.agregarMaterialR);
        piezaR = view.findViewById(R.id.piezaMDR);
        mIIR = view.findViewById(R.id.InicialIDMR);
        mFIR = view.findViewById(R.id.FinalIDMR);
        mIER = view.findViewById(R.id.InicialEDMR);
        mFER=view.findViewById(R.id.FinalEDMR);
        tablaR = view.findViewById(R.id.tablaR);
        horizontalScrollViewR = view.findViewById(R.id.scrollhorizontalR);
        elimarMaterialesListR = view.findViewById(R.id.eliminarMaterialesListR);


       /* final TablaAdapter tablaAdapter = new TablaAdapter(getActivity(), tablaR);
        tablaAdapter.agregarCabecera(R.array.cabecera_tabla);*/

            if(statusQueja.equals("E")){
                agragarDMR.setEnabled(false);
                descripcionMatR.setEnabled(false);
                agragarDMR.setTextColor(Color.GRAY);

        }

        if(extencionesMat==true){
            spinnerExtMatR.setVisibility(View.VISIBLE);
        }else{

            Array.detalleBit.clear();
            Array.detalleBit.add(0, "---Seleccionar---");
            Array.detalleBit.add(1, "1");
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, Array.detalleBit);
            descripcionMatR.setAdapter(arrayAdapter);
        }


        descripcionMatR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posDescMatR=position;
                if(position!=0){
                    Iterator<List<DetalleBitacoraModel>> itData = Array.dataDetBit.iterator();
                    List<DetalleBitacoraModel> dat = itData.next();
                    clvTipoDescMatR=dat.get(position-1).catTipoArticuloClave;
                    request.DetalleBitR(getContext());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        clasificacionMatR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Iterator<List<DescripcionArticuloModel>> itData = Array.dataDetArtBit.iterator();
                    List<DescripcionArticuloModel> dat = itData.next();
                    idArticuloDMR=dat.get(position-1).IdArticulo;
                    cantidadDMR=dat.get(position-1).Cantidad;
                    idInventarioMDR=dat.get(position-1).IdInventario;
                    request.getTipoMatR(getContext());
                    seleccionR=position;
                    posClasMatR=position;
                    if(extencionesMat==false){
                        request.getPredescargaR(getActivity(),getContext());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerExtMatR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posExtMatR=position;
                if(position!=0){
                    extSerR=(position-1);
                    seleccionExteR=position;
                    request.getPredescargaR(getActivity(),getContext());
                }else{
                    extSerR=(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        agragarDMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(seleccionR==0){
                    Toast.makeText(getContext(),"Seleccione un articulo",Toast.LENGTH_SHORT).show();
                }else {
                    if(extencionesMat==true){
                        if(seleccionExteR==0){
                            Toast.makeText(getContext(),"Seleccione una extensión",Toast.LENGTH_SHORT).show();
                        }else{
                            EjecutarDescargaMaterial();
                        }
                    }else{
                        EjecutarDescargaMaterial();
                    }
                }
            }
        });

        return view;
    }
    public void EjecutarDescargaMaterial(){
        if (request.pieza == true) {
            if(piezaR.getText().toString().length()==0){
                Toast.makeText(getContext(),"Seleccione cantidad",Toast.LENGTH_SHORT).show();
            }else {
                piezaSerR = Integer.parseInt(String.valueOf(piezaR.getText()));
                totalDMR = piezaSerR;
                IIDMR = 0;
                IFDMR = 0;
                EIMDR = 0;
                EFDMR = 0;
                if (cantidadDMR >= totalDMR) {
                    request.getValidaPreDesR(getActivity(), getContext());


                } else {
                    Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }

            }

        } else {
            if(mIIR.getText().toString().length()==0||mFIR.getText().toString().length()==0||mIER.getText().toString().length()==0||mFER.getText().toString().length()==0){
                Toast.makeText(getContext(),"Seleccione metraje",Toast.LENGTH_SHORT).show();

            }else {
                IIDMR = Integer.parseInt(String.valueOf(mIIR.getText()));
                IFDMR = Integer.parseInt(String.valueOf(mFIR.getText()));
                EIMDR = Integer.parseInt(String.valueOf(mIER.getText()));
                EFDMR = Integer.parseInt(String.valueOf(mFER.getText()));
                metrosR = (IFDMR - IIDMR) + (EFDMR - EIMDR);
                totalDMR = metrosR;
                if (cantidadDMR >= totalDMR) {
                    request.getValidaPreDesR(getActivity(), getContext());

                } else {
                    Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
