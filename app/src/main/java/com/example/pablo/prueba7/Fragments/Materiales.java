package com.example.pablo.prueba7.Fragments;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
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

import static com.example.pablo.prueba7.Request.Request.extencionesMat;

public class Materiales extends Fragment {

    private LayoutInflater inflater;
    private ViewGroup container;
    public static HorizontalScrollView scrollViewM;
    public static TableLayout tabla;
    Request request = new Request();
    EditText pieza,mII,mIE,mFI,mFE;
    public static int clvTipoDescMat,idArticuloDM,cantidadDM,idInventarioMD,piezaSer, metros, totalDM,IIDM,IFDM,EIMD,EFDM;
    public static int extSer=0;
    public static Spinner descripcionMat,clasificacionMat,spinnerExtMat;
    public static ConstraintLayout extMat, piezasMat,metrosMat;
    Button agragarDM;
    int seleccion,seleccionExte;
    public static int posDescMat,posClasMat,posExtMat;


    public Materiales() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        View view  = inflater.inflate(R.layout.activity_descarga, container, false);
        request.getChecaExt(getContext());
        descripcionMat = view.findViewById(R.id.descripcionArticuloDesc);
        clasificacionMat = view.findViewById(R.id.clasificacionMatDesc);
        extMat = view.findViewById(R.id.constrain_Extenciones);
        piezasMat = view.findViewById(R.id.constrain_Cantidad);
        metrosMat = view.findViewById(R.id.constrain_Metraje);
        spinnerExtMat = view.findViewById(R.id.extencionesDescarga);
        agragarDM=view.findViewById(R.id.agregarMaterial);
        pieza = view.findViewById(R.id.piezaMD);
        mII = view.findViewById(R.id.InicialIDM);
        mFI = view.findViewById(R.id.FinalIDM);
        mIE = view.findViewById(R.id.InicialEDM);
        mFE=view.findViewById(R.id.FinalEDM);
        tabla = view.findViewById(R.id.tabla);
        scrollViewM = view.findViewById(R.id.scrollhorizontal);
        final TablaAdapter tablaAdapter = new TablaAdapter(getActivity(),Materiales.tabla);
        tablaAdapter.agregarCabecera(R.array.cabecera_tabla);
        if(request.extencionesMat==true){
            spinnerExtMat.setVisibility(View.VISIBLE);
        }else{

            Array.detalleBit.clear();
            Array.detalleBit.add(0, "---Seleccionar---");
            Array.detalleBit.add(1, "1");
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, Array.detalleBit);
            descripcionMat.setAdapter(arrayAdapter);
        }


    descripcionMat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posDescMat=position;
                if(position!=0){
                    Iterator<List<DetalleBitacoraModel>> itData = Array.dataDetBit.iterator();
                        List<DetalleBitacoraModel> dat = itData.next();
                        clvTipoDescMat=dat.get(position-1).catTipoArticuloClave;
                        request.DetalleBit(getContext());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        clasificacionMat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    Iterator<List<DescripcionArticuloModel>> itData = Array.dataDetArtBit.iterator();
                        List<DescripcionArticuloModel> dat = itData.next();
                        idArticuloDM=dat.get(position-1).IdArticulo;
                    cantidadDM=dat.get(position-1).Cantidad;
                    idInventarioMD=dat.get(position-1).IdInventario;
                    request.getTipoMat(getContext());
                    seleccion=position;
                    posClasMat=position;
                    if(request.extencionesMat==false){
                        request.getPredescarga(getActivity(),getContext());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerExtMat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posExtMat=position;
                if(position!=0){
                    extSer=(position-1);
                    seleccionExte=position;
                    request.getPredescarga(getActivity(),getContext());
                }else{
                    extSer=(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
agragarDM.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(seleccion==0){
            Toast.makeText(getContext(),"Seleccione un articulo",Toast.LENGTH_SHORT).show();
        }else {
           if(extencionesMat==true){
               if(seleccionExte==0){
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
            if(pieza.getText().toString().length()==0){
                Toast.makeText(getContext(),"Seleccione cantidad",Toast.LENGTH_SHORT).show();
            }else {
                piezaSer = Integer.parseInt(String.valueOf(pieza.getText()));
                totalDM = piezaSer;
                IIDM = 0;
                IFDM = 0;
                EIMD = 0;
                EFDM = 0;
                if (cantidadDM >= totalDM) {
                    request.getValidaPreDes(getActivity(), getContext());


                } else {
                    Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }

            }

        } else {
            if(mII.getText().toString().length()==0||mFI.getText().toString().length()==0||mIE.getText().toString().length()==0||mFE.getText().toString().length()==0){
                Toast.makeText(getContext(),"Seleccione metraje",Toast.LENGTH_SHORT).show();

            }else {
                IIDM = Integer.parseInt(String.valueOf(mII.getText()));
                IFDM = Integer.parseInt(String.valueOf(mFI.getText()));
                EIMD = Integer.parseInt(String.valueOf(mIE.getText()));
                EFDM = Integer.parseInt(String.valueOf(mFE.getText()));
                metros = (IFDM - IIDM) + (EFDM - EIMD);
                totalDM = metros;
                if (cantidadDM >= totalDM) {
                    request.getValidaPreDes(getActivity(), getContext());

                } else {
                    Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
