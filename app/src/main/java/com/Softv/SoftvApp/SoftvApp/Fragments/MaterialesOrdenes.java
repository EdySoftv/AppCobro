package com.Softv.SoftvApp.SoftvApp.Fragments;
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
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.Adapters.TablaAdapter;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Modelos.DescripcionArticuloModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.DetalleBitacoraModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.LlenaExtencionesModel;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import static com.Softv.SoftvApp.SoftvApp.Request.Request.extencionesMat;

public class MaterialesOrdenes extends Fragment {

    private LayoutInflater inflater;
    private ViewGroup container;
    public static HorizontalScrollView scrollViewM;
    public static TableLayout tabla;
    public static RecyclerView elimarMaterialesList;
    Request request = new Request();
    EditText pieza,mII,mIE,mFI,mFE;
    public static int clvTipoDescMat,idArticuloDM,cantidadDM,idInventarioMD,piezaSer, metros, totalDM,IIDM,IFDM,EIMD,EFDM;
    public static int extSer;
    public static String descripcionMaterial="";
    public static Spinner descripcionMat,clasificacionMat,spinnerExtMat;
    public static ConstraintLayout extMat, piezasMat,metrosMat;
    Button agragarDM;
    public static boolean directa=true;

    int seleccion,seleccionExte;
    public static int posDescMat,posClasMat,posExtMat;


    public MaterialesOrdenes() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        View view  = inflater.inflate(R.layout.activity_descarga_ordenes, container, false);
        request.getChecaExt(getContext());
        descripcionMat = view.findViewById(R.id.descripcionArticuloDesc);
        clasificacionMat = view.findViewById(R.id.clasificacionMatDesc);
        extMat = view.findViewById(R.id.constrain_Extenciones);
        piezasMat = view.findViewById(R.id.constrain_Cantidad);
        metrosMat = view.findViewById(R.id.constrain_Metraje);
        spinnerExtMat = view.findViewById(R.id.extencionesDescarga);
        elimarMaterialesList = view.findViewById(R.id.eliminarMaterialesList);
        agragarDM=view.findViewById(R.id.agregarMaterial);
        pieza = view.findViewById(R.id.piezaMD);
        mII = view.findViewById(R.id.InicialIDM);
        mFI = view.findViewById(R.id.FinalIDM);
        mIE = view.findViewById(R.id.InicialEDM);
        mFE=view.findViewById(R.id.FinalEDM);


        //scrollViewM = view.findViewById(R.id.scrollhorizontal);
        final TablaAdapter tablaAdapter = new TablaAdapter(getActivity(), tabla);

        if(request.extencionesMat==true){
            spinnerExtMat.setVisibility(View.VISIBLE);
        }else{
            extSer=0;
            Array.detalleBit.clear();
            Array.detalleBit.add(0, "---Seleccionar---");
            Array.detalleBit.add(1, "1");
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, Array.detalleBit);
            descripcionMat.setAdapter(arrayAdapter);
        }

        if(directa==true){
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ClvOrdSer",Util.getClvOrden(Util.preferences) );
                jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));

                request.DamebitacoraDirecta(getContext(),jsonObject);
            }catch (Exception e){}
        }else{
            request.getPredescarga(getActivity(),getContext());
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
                    if(request.extencionesMat==true){
                        spinnerExtMat.setVisibility(View.VISIBLE);
                    }else{
                        spinnerExtMat.setVisibility(View.GONE);
                        //extSer=0;
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
                    descripcionMaterial=dat.get(position-1).Nombre;
                    request.getTipoMat(getContext());
                    seleccion=position;
                    posClasMat=position;
                    if(request.extencionesMat==false){
                        if(directa==true){
                            try {
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("ClvOrdSer",Util.getClvOrden(Util.preferences) );
                                jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                                jsonObject.put("NoExt", 0);

                                request.getDescargaDirecta(getActivity(),getContext(),jsonObject);
                            }catch (Exception e){}
                        }else{
                            request.getPredescarga(getActivity(),getContext());
                        }

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
                    //extSer=(position-1);
                    //seleccionExte=position;
                    Iterator<List<LlenaExtencionesModel>> itData = Array.dataLlenaExt.iterator();
                    List<LlenaExtencionesModel> dat = itData.next();
                    extSer=dat.get(position-1).ID;
                    if(directa==true){
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("ClvOrdSer",Util.getClvOrden(Util.preferences) );
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                            jsonObject.put("NoExt", extSer);

                            request.getDescargaDirecta(getActivity(),getContext(),jsonObject);
                        }catch (Exception e){}
                    }else{
                        request.getPredescarga(getActivity(),getContext());
                    }

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
        ///////////////////

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
                    if(directa==false) {
                        request.getValidaPreDes(getActivity(), getContext());
                        descripcionMat.setSelection(0);
                        clasificacionMat.setSelection(0);
                        clasificacionMat.setEnabled(false);
                        piezasMat.setVisibility(View.GONE);
                        extMat.setVisibility(View.GONE);
                        metrosMat.setVisibility(View.GONE);
                        pieza.setText("");
                        mII.setText("");
                        mIE.setText("");
                        mFI.setText("");
                        mFE.setText("");
                        request.getChecaExt(getContext());
                    }else{
                        try{
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("ClvOrdSer", Util.getClvOrden(Util.preferences));
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                            request.bitacoraDirecta(getActivity(),getContext(),jsonObject);
                        }catch (Exception e){}
                        descripcionMat.setSelection(0);
                        clasificacionMat.setSelection(0);
                        clasificacionMat.setEnabled(false);
                        piezasMat.setVisibility(View.GONE);
                        extMat.setVisibility(View.GONE);
                        metrosMat.setVisibility(View.GONE);
                        pieza.setText("");
                        mII.setText("");
                        mIE.setText("");
                        mFI.setText("");
                        mFE.setText("");
                    }

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
                    if(directa==false) {
                        request.getValidaPreDes(getActivity(), getContext());
                        descripcionMat.setSelection(0);
                        clasificacionMat.setSelection(0);
                        clasificacionMat.setEnabled(false);
                        piezasMat.setVisibility(View.GONE);
                        extMat.setVisibility(View.GONE);
                        metrosMat.setVisibility(View.GONE);
                        pieza.setText("");
                        mII.setText("");
                        mIE.setText("");
                        mFI.setText("");
                        mFE.setText("");
                        request.getChecaExt(getContext());
                    }else{
                        try{
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("ClvOrdSer", Util.getClvOrden(Util.preferences));
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                            request.bitacoraDirecta(getActivity(),getContext(),jsonObject);
                        }catch (Exception e){}
                        descripcionMat.setSelection(0);
                        clasificacionMat.setSelection(0);
                        clasificacionMat.setEnabled(false);
                        piezasMat.setVisibility(View.GONE);
                        extMat.setVisibility(View.GONE);
                        metrosMat.setVisibility(View.GONE);
                        pieza.setText("");
                        mII.setText("");
                        mIE.setText("");
                        mFI.setText("");
                        mFE.setText("");
                    }
                } else {
                    Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
