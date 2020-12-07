package com.Softv.SoftvApp.SoftvApp.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.Adapters.TablaAdapter;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Modelos.DescripcionArticuloModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.DetalleBitacoraModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetGetDescargaMaterialArticulosByIdClvOrdenListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.LlenaExtencionesModel;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.Softv.SoftvApp.SoftvApp.Adapters.QuejasAdapter.statusQueja;
import static com.Softv.SoftvApp.SoftvApp.Request.Request.extencionesMat;

public class MaterialesOrdenes extends Fragment {

    private LayoutInflater inflater;
    private ViewGroup container;
    public static HorizontalScrollView scrollViewM;
    public static TableLayout tabla;
    public static RecyclerView elimarMaterialesList;
    Request request = new Request();
    public static EditText pieza,mII,mIE,mFI,mFE;
    public static int clvTipoDescMat,idArticuloDM,cantidadDM,idInventarioMD,piezaSer, metros, totalDM,IIDM,IFDM,EIMD,EFDM;
    public static int extSer;
    public static String descripcionMaterial="";
    public static TextView  tvInterior,tvExterior;
    public static int esFibra;
    public static Spinner descripcionMat,clasificacionMat,spinnerExtMat;
    public static ConstraintLayout extMat, piezasMat,metrosMat;
    Button agragarDM;
    public static int posDescMat,posClasMat,posExtMat;
    int seleccion,seleccionExte;
    public static ImageButton imageButton;


    public MaterialesOrdenes() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.activity_descarga_reporte, container, false);
        request.getChecaExt(getContext());
        descripcionMat = view.findViewById(R.id.descripcionArticuloDescR);
        clasificacionMat = view.findViewById(R.id.clasificacionMatDescR);
        extMat = view.findViewById(R.id.constrain_ExtencionesR);
        piezasMat = view.findViewById(R.id.constrain_CantidadR);
        metrosMat = view.findViewById(R.id.constrain_MetrajeR);
        spinnerExtMat = view.findViewById(R.id.extencionesDescargaR);
        agragarDM=view.findViewById(R.id.agregarMaterialR);
        pieza = view.findViewById(R.id.piezaMDR);
        mII = view.findViewById(R.id.InicialIDMR);
        mFI = view.findViewById(R.id.FinalIDMR);
        mIE = view.findViewById(R.id.InicialEDMR);
        mFE=view.findViewById(R.id.FinalEDMR);
        tvInterior = view.findViewById(R.id.textView15);
        elimarMaterialesList = view.findViewById(R.id.eliminarMaterialesListR);
        tvExterior = view.findViewById(R.id.textViewExternoR);


        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ClvOrdSer",Util.getClvOrden(Util.preferences) );
            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
            jsonObject.put("NoExt", 0);

            request.getDescargaDirecta(getActivity(),getContext(),jsonObject);
        }catch (Exception e){}

        try{
            JSONObject jsonObjectDirecta = new JSONObject();
            JSONObject jsonObjectDirecta1 = new JSONObject();
            jsonObjectDirecta.put("Op",3);
            jsonObjectDirecta.put("idcompania",3);
            jsonObjectDirecta.put("ClvTecnicoMandar",Util.getClvTec(Util.preferences));
            jsonObjectDirecta1.put("obj",jsonObjectDirecta);
            request.getPermisosDirecta(getContext(),jsonObjectDirecta1);
        }catch (Exception e){}


        if(extencionesMat==true){
            spinnerExtMat.setVisibility(View.VISIBLE);
        }else{

            Array.detalleBit.clear();
            Array.detalleBit.add(0, "---Seleccionar---");
            Array.detalleBit.add(1, "1");
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, Array.detalleBit);
            descripcionMat.setAdapter(arrayAdapter);
        }
        request.getPredescarga(getActivity(),getContext());
        descripcionMat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posDescMat=position;
                if(position!=0){
                    Iterator<List<DetalleBitacoraModel>> itData = Array.dataDetBit.iterator();
                    List<DetalleBitacoraModel> dat = itData.next();
                    clvTipoDescMat=dat.get(position-1).catTipoArticuloClave;
                    esFibra = dat.get(position -1).esFibra;
                    request.DetalleBit(getContext());
                    if(extencionesMat==true){
                        spinnerExtMat.setVisibility(View.VISIBLE);
                    }else {
                        spinnerExtMat.setVisibility(View.GONE);
                    }
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
                    if(extencionesMat==false){
                        request.getPredescarga(getActivity(), getContext());
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
                    //extSerR=(position-1);
                    //seleccionExteR=position;
                    request.getPredescarga(getActivity(),getContext());
                    Iterator<List<LlenaExtencionesModel>> itData = Array.dataLlenaExt.iterator();
                    List<LlenaExtencionesModel> dat = itData.next();
                    extSer=dat.get(position-1).ID;

                    if((position-1)!=0){
                        mIE.setVisibility(View.GONE);
                        mFE.setVisibility(View.GONE);
                        EIMD = 0;
                        EFDM = 0;
                        tvExterior.setVisibility(View.INVISIBLE);

                    }else{
                        mIE.setVisibility(View.VISIBLE);
                        mFE.setVisibility(View.VISIBLE);
                        tvExterior.setVisibility(View.VISIBLE);
                    }

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
                        if(spinnerExtMat.getSelectedItemPosition()==0){
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



                    GetGetDescargaMaterialArticulosByIdClvOrdenListResult nuevo = new GetGetDescargaMaterialArticulosByIdClvOrdenListResult();
                    nuevo.setCANTIDADUTILIZADA(MaterialesReportes.totalDMR);
                    nuevo.setClvOrdSer(Util.getClvOrden(Util.preferences));
                    nuevo.setDescripcion(MaterialesReportes.descripcionMaterialR);
                    nuevo.setESCABLE(request.escable);
                    nuevo.setMETRAJEFIN(MaterialesReportes.IFDMR);
                    nuevo.setMETRAJEFINEXTERIOR(MaterialesReportes.EFDMR);
                    nuevo.setMETRAJEINICIO(MaterialesReportes.IIDMR);
                    nuevo.setMETRAJEINICIOEXTERIOR(MaterialesReportes.EIMDR);
                    nuevo.setNOARTICULO(MaterialesReportes.idInventarioMDR);
                    nuevo.setNoExt(MaterialesReportes.extSerR);
                    try{
                        nuevo.setTecnico(Util.getNombreTecnicoPreference(Util.preferences));
                    }catch (Exception e){
                        nuevo.setTecnico("");
                    }
                    nuevo.setTipoDescarga(Util.getTipoDescarga(Util.preferences));
                    nuevo.setIdDescarga(0);

                    Array.dataDescargaDirecta.get(0).add(nuevo);

                    Iterator<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>> itdata = Array.dataDescargaDirecta.iterator();
                    List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult> dat = itdata.next();





                    request.getValidaPreDesR(getActivity(), getContext());
                    descripcionMat.setSelection(0);
                    clasificacionMat.setSelection(0);
                    //clasificacionMatR.setEnabled(false);
                    piezasMat.setVisibility(View.GONE);
                    extMat.setVisibility(View.GONE);
                    metrosMat.setVisibility(View.GONE);
                    //piezaR,mIIR,mIER,mFIR,mFER;
                    pieza.setText("");
                    mII.setText("");
                    mIE.setText("");
                    mFI.setText("");
                    mFE.setText("");

                    request.getChecaExt(getContext());
                } else {
                    Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }

            }

        } else {
            if(ValidacionDescarga()== true){
                Toast.makeText(getContext(),"Seleccione metraje",Toast.LENGTH_SHORT).show();

            }else {
                try{
                    IIDM = Integer.parseInt(String.valueOf(mII.getText()));
                    IFDM = Integer.parseInt(String.valueOf(mFI.getText()));
                }catch (Exception e){
                    IIDM=0;
                    IFDM=0;
                }
                try{
                    EIMD = Integer.parseInt(String.valueOf(mIE.getText()));
                    EFDM = Integer.parseInt(String.valueOf(mFE.getText()));
                }catch (Exception r){
                    EIMD=0;
                    EFDM=0;
                }


                metros = (IFDM - IIDM) + (EFDM - EIMD);
                totalDM = metros;
                if (cantidadDM >= totalDM) {


                    GetGetDescargaMaterialArticulosByIdClvOrdenListResult nuevo = new GetGetDescargaMaterialArticulosByIdClvOrdenListResult();
                    nuevo.setCANTIDADUTILIZADA(MaterialesReportes.totalDMR);
                    nuevo.setClvOrdSer(Util.getClvOrden(Util.preferences));
                    nuevo.setDescripcion(MaterialesReportes.descripcionMaterialR);
                    nuevo.setESCABLE(request.escable);
                    nuevo.setMETRAJEFIN(MaterialesReportes.IFDMR);
                    nuevo.setMETRAJEFINEXTERIOR(MaterialesReportes.EFDMR);
                    nuevo.setMETRAJEINICIO(MaterialesReportes.IIDMR);
                    nuevo.setMETRAJEINICIOEXTERIOR(MaterialesReportes.EIMDR);
                    nuevo.setNOARTICULO(MaterialesReportes.idInventarioMDR);
                    nuevo.setNoExt(MaterialesReportes.extSerR);
                    try{
                        nuevo.setTecnico(Util.getNombreTecnicoPreference(Util.preferences));
                    }catch (Exception e){
                        nuevo.setTecnico("");
                    }
                    nuevo.setTipoDescarga(Util.getTipoDescarga(Util.preferences));
                    nuevo.setIdDescarga(0);

                    Array.dataDescargaDirecta.get(0).add(nuevo);

                    Iterator<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>> itdata = Array.dataDescargaDirecta.iterator();
                    List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult> dat = itdata.next();



                    request.getValidaPreDesR(getActivity(), getContext());
                    descripcionMat.setSelection(0);
                    clasificacionMat.setSelection(0);
                    //clasificacionMatR.setEnabled(false);
                    piezasMat.setVisibility(View.GONE);
                    extMat.setVisibility(View.GONE);
                    metrosMat.setVisibility(View.GONE);
                    //piezaR,mIIR,mIER,mFIR,mFER;
                    pieza.setText("");
                    mII.setText("");
                    mIE.setText("");
                    mFI.setText("");
                    mFE.setText("");
                    request.getChecaExt(getContext());
                } else {
                    Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
    public boolean ValidacionDescarga() {
        boolean ok=false;
        if(request.extencionesMat == true){
            if((posExtMat-1)!=0){
                if(mII.getText().toString().length() == 0 || mFI.getText().toString().length() == 0){
                    EIMD = 0;
                    EFDM = 0;
                    ok=true;
                }else{
                    ok=false;
                }
            }else{
                if(mII.getText().toString().length() == 0 || mFI.getText().toString().length() == 0 || mIE.getText().toString().length() == 0 || mFE.getText().toString().length() == 0){
                    ok=true;
                }else{
                    ok=false;
                }
            }

        }else if(esFibra==1){
            if(mIE.getText().toString().length() == 0 || mFE.getText().toString().length() == 0){
                IIDM=0;
                IFDM=0;
                ok=true;
            }else{
                ok=false;
            }
        } else{
            if(mII.getText().toString().length() == 0 || mFI.getText().toString().length() == 0 || mIE.getText().toString().length() == 0 || mFE.getText().toString().length() == 0){
                ok=true;
            }else{
                ok=false;
            }
        }

        return ok;
    }
}