package com.Softv.SoftvApp.SoftvApp.Fragments;


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
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Modelos.DescripcionArticuloModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.DetalleBitacoraModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetGetDescargaMaterialArticulosByIdClvOrdenListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.LlenaExtencionesModel;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import static com.Softv.SoftvApp.SoftvApp.Adapters.QuejasAdapter.statusQueja;
import static com.Softv.SoftvApp.SoftvApp.Request.Request.extencionesMat;


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
    public static EditText piezaR,mIIR,mIER,mFIR,mFER;
    public static int clvTipoDescMatR,idArticuloDMR,cantidadDMR,idInventarioMDR,piezaSerR, metrosR, totalDMR,IIDMR,IFDMR,EIMDR,EFDMR;
    public static int extSerR;
    public static String descripcionMaterialR="";
    public static TextView  tvInteriorR;
    public static int esFibraR;
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
        tvInteriorR = view.findViewById(R.id.textView15);
        elimarMaterialesListR = view.findViewById(R.id.eliminarMaterialesListR);


        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ClvOrdSer",Util.getClvOrden(Util.preferences) );
            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
            jsonObject.put("NoExt", 0);

            request.getDescargaDirectaR(getActivity(),getContext(),jsonObject);
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

        /*if(Util.getPermisisDescarga(Util.preferences)==true){
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ClvOrdSer", Util.getClvOrden(Util.preferences) );
                jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));

                request.DamebitacoraDirectaR(getContext(),jsonObject);
            }catch (Exception e){}
        }else {
            request.getPredescargaR(getActivity(),getContext());
        }*/
        request.getPredescargaR(getActivity(),getContext());
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
                    esFibraR = dat.get(position -1).esFibra;
                    request.DetalleBitR(getContext());
                    if(extencionesMat==true){
                        spinnerExtMatR.setVisibility(View.VISIBLE);
                    }else {
                        spinnerExtMatR.setVisibility(View.GONE);
                    }
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
                       /* if(Util.getPermisisDescarga(Util.preferences)==true){
                            try {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("ClvOrdSer",Util.getClvOrden(Util.preferences) );
                                jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                                jsonObject.put("NoExt", 0);

                                request.getDescargaDirectaR(getActivity(),getContext(),jsonObject);
                            }catch (Exception e){}
                        }else {
                            request.getPredescargaR(getActivity(), getContext());
                        }*/
                        request.getPredescargaR(getActivity(), getContext());
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
                    //extSerR=(position-1);
                    //seleccionExteR=position;
                    request.getPredescargaR(getActivity(),getContext());
                    Iterator<List<LlenaExtencionesModel>> itData = Array.dataLlenaExt.iterator();
                    List<LlenaExtencionesModel> dat = itData.next();
                    extSerR=dat.get(position-1).ID;

/*                    if(Util.getPermisisDescarga(Util.preferences)==true){
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("ClvOrdSer",Util.getClvOrden(Util.preferences) );
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                            jsonObject.put("NoExt", extSerR);

                            request.getDescargaDirectaR(getActivity(),getContext(),jsonObject);
                        }catch (Exception e){}
                    }else{
                        request.getPredescargaR(getActivity(),getContext());
                    }*/
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
                        if(spinnerExtMatR.getSelectedItemPosition()==0){
                            Toast.makeText(getContext(),"Seleccione una extensión",Toast.LENGTH_SHORT).show();
                        }else{
                            EjecutarDescargaMaterial();
                        }
                    }else{
                        EjecutarDescargaMaterial();
                    }
                }

                /////////////////////////////////


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
                    descripcionMatR.setSelection(0);
                    clasificacionMatR.setSelection(0);
                    //clasificacionMatR.setEnabled(false);
                    piezasMatR.setVisibility(View.GONE);
                    extMatR.setVisibility(View.GONE);
                    metrosMatR.setVisibility(View.GONE);
                    //piezaR,mIIR,mIER,mFIR,mFER;
                    piezaR.setText("");
                    mIIR.setText("");
                    mIER.setText("");
                    mFIR.setText("");
                    mFER.setText("");

                    request.getChecaExt(getContext());
                    /*if(Util.getPermisisDescarga(Util.preferences)==false) {
                    request.getValidaPreDesR(getActivity(), getContext());
                    descripcionMatR.setSelection(0);
                    clasificacionMatR.setSelection(0);
                    //clasificacionMatR.setEnabled(false);
                    piezasMatR.setVisibility(View.GONE);
                    extMatR.setVisibility(View.GONE);
                    metrosMatR.setVisibility(View.GONE);
                    //piezaR,mIIR,mIER,mFIR,mFER;
                    piezaR.setText("");
                    mIIR.setText("");
                    mIER.setText("");
                    mFIR.setText("");
                    mFER.setText("");

                    request.getChecaExt(getContext());
                    }else{
                        try{
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("ClvOrdSer", Util.getClvOrden(Util.preferences));
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                            request.bitacoraDirectaR(getActivity(),getContext(),jsonObject);
                        }catch (Exception e){}
                        descripcionMatR.setSelection(0);
                        clasificacionMatR.setSelection(0);
                        clasificacionMatR.setEnabled(false);
                        piezasMatR.setVisibility(View.GONE);
                        extMatR.setVisibility(View.GONE);
                        metrosMatR.setVisibility(View.GONE);
                        piezaR.setText("");
                        mIIR.setText("");
                        mIER.setText("");
                        mFIR.setText("");
                        mFER.setText("");
                    }*/
                } else {
                    Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }

            }

        } else {
            if(ValidacionDescarga()== true){
                Toast.makeText(getContext(),"Seleccione metraje",Toast.LENGTH_SHORT).show();

            }else {
                try{
                    IIDMR = Integer.parseInt(String.valueOf(mIIR.getText()));
                    IFDMR = Integer.parseInt(String.valueOf(mFIR.getText()));
                }catch (Exception e){
                    IIDMR=0;
                    IFDMR=0;
                }
                try{
                    EIMDR = Integer.parseInt(String.valueOf(mIER.getText()));
                    EFDMR = Integer.parseInt(String.valueOf(mFER.getText()));
                }catch (Exception r){
                    EIMDR=0;
                    EFDMR=0;
                }


                metrosR = (IFDMR - IIDMR) + (EFDMR - EIMDR);
                totalDMR = metrosR;
                if (cantidadDMR >= totalDMR) {


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
                    descripcionMatR.setSelection(0);
                    clasificacionMatR.setSelection(0);
                    //clasificacionMatR.setEnabled(false);
                    piezasMatR.setVisibility(View.GONE);
                    extMatR.setVisibility(View.GONE);
                    metrosMatR.setVisibility(View.GONE);
                    //piezaR,mIIR,mIER,mFIR,mFER;
                    piezaR.setText("");
                    mIIR.setText("");
                    mIER.setText("");
                    mFIR.setText("");
                    mFER.setText("");
                    request.getChecaExt(getContext());
                   /* if(Util.getPermisisDescarga(Util.preferences)==false) {
                    request.getValidaPreDesR(getActivity(), getContext());
                    descripcionMatR.setSelection(0);
                    clasificacionMatR.setSelection(0);
                    //clasificacionMatR.setEnabled(false);
                    piezasMatR.setVisibility(View.GONE);
                    extMatR.setVisibility(View.GONE);
                    metrosMatR.setVisibility(View.GONE);
                    //piezaR,mIIR,mIER,mFIR,mFER;
                    piezaR.setText("");
                    mIIR.setText("");
                    mIER.setText("");
                    mFIR.setText("");
                    mFER.setText("");
                    request.getChecaExt(getContext());
                    }else{
                        try{
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("ClvOrdSer", Util.getClvOrden(Util.preferences));
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                            request.bitacoraDirectaR(getActivity(),getContext(),jsonObject);
                        }catch (Exception e){}
                        descripcionMatR.setSelection(0);
                        clasificacionMatR.setSelection(0);
                        clasificacionMatR.setEnabled(false);
                        piezasMatR.setVisibility(View.GONE);
                        extMatR.setVisibility(View.GONE);
                        metrosMatR.setVisibility(View.GONE);
                        piezaR.setText("");
                        mIIR.setText("");
                        mIER.setText("");
                        mFIR.setText("");
                        mFER.setText("");
                    }*/
                } else {
                    Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
    public boolean ValidacionDescarga() {
        boolean ok=false;
         if(esFibraR==1){
            if(mIER.getText().toString().length() == 0 || mFER.getText().toString().length() == 0){
                IIDMR=0;
                IFDMR=0;
                ok=true;
            }else{
                ok=false;
            }
        } else{
            if(mIIR.getText().toString().length() == 0 || mFIR.getText().toString().length() == 0 || mIER.getText().toString().length() == 0 || mFER.getText().toString().length() == 0){
                ok=true;
            }else{
                ok=false;
            }
        }

        return ok;
    }
}
