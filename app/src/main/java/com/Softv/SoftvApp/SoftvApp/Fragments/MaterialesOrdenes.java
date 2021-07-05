package com.Softv.SoftvApp.SoftvApp.Fragments;

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
import com.Softv.SoftvApp.SoftvApp.Services.Services;
import com.Softv.SoftvApp.SoftvApp.sampledata.Service;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.Softv.SoftvApp.SoftvApp.Request.Request.extencionesMat;

public class MaterialesOrdenes extends Fragment {

    private LayoutInflater inflater;
    private ViewGroup container;
    public static HorizontalScrollView scrollViewM;
    public static TableLayout tabla;
    public static RecyclerView elimarMaterialesList;
    Request request = new Request();
    public static EditText pieza, mII, mIE, mFI, mFE, EDPL;
    public static TextView tvExterior, tvInterior,textViewInicioTitulo,textViewFinTitulo, txtclas, TVPL;
    public static int clvTipoDescMat, idArticuloDM, cantidadDM, idInventarioMD, piezaSer, metros, totalDM, IIDM, IFDM, EIMD, EFDM;
    public static int extSer, aux;
    public static String descripcionMaterial = "", STB;
    public static Spinner descripcionMat, clasificacionMat, spinnerExtMat;
    public static ConstraintLayout extMat, piezasMat, metrosMat;
    public boolean fibra = false, conectores = false;
    Button agragarDM;
    ArrayList<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>> asd;

    int seleccion, seleccionExte;
    public static int posDescMat, posClasMat, posExtMat;


    public MaterialesOrdenes() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.activity_descarga_ordenes, container, false);
        request.getChecaExt(getContext());

        descripcionMat = view.findViewById(R.id.descripcionArticuloDesc);

        txtclas = view.findViewById(R.id.textView9);
        clasificacionMat = view.findViewById(R.id.clasificacionMatDesc);

        extMat = view.findViewById(R.id.constrain_Extenciones); // Area Extenciones
        piezasMat = view.findViewById(R.id.constrain_Cantidad); // Area Cantidad
        metrosMat = view.findViewById(R.id.constrain_Metraje); // Area Metricas

        /*Area de metricas - Inicio*/
        tvExterior = view.findViewById(R.id.textView16);
        tvInterior = view.findViewById(R.id.textView15);
        mII = view.findViewById(R.id.InicialIDM);
        mIE = view.findViewById(R.id.InicialEDM);
        mFI = view.findViewById(R.id.FinalIDM);
        mFE = view.findViewById(R.id.FinalEDM);
        /*Area de metricas - Fin*/

        pieza = view.findViewById(R.id.piezaMD);

        spinnerExtMat = view.findViewById(R.id.extencionesDescarga);
        elimarMaterialesList = view.findViewById(R.id.eliminarMaterialesList);
        agragarDM = view.findViewById(R.id.agregarMaterial);

        //Placa
        EDPL = view.findViewById(R.id.EdPla);
        TVPL = view.findViewById(R.id.TvPla);

        //Ocultamos lo que no se ocupa
        extMat.setVisibility(View.GONE);
        piezasMat.setVisibility(View.GONE);
        txtclas.setVisibility(View.GONE);
        clasificacionMat.setVisibility(View.GONE);
        metrosMat.setVisibility(View.GONE);
        EDPL.setVisibility(View.GONE);
        TVPL.setVisibility(View.GONE);


        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ClvOrdSer", Util.getClvOrden(Util.preferences));
            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
            jsonObject.put("NoExt", 0);

            request.getDescargaDirecta(getActivity(), getContext(), jsonObject);
        } catch (Exception e) {
        }

        try {
            JSONObject jsonObjectDirecta = new JSONObject();
            JSONObject jsonObjectDirecta1 = new JSONObject();
            jsonObjectDirecta.put("Op", 3);
            jsonObjectDirecta.put("idcompania", 3);
            jsonObjectDirecta.put("ClvTecnicoMandar", Util.getClvTec(Util.preferences));
            jsonObjectDirecta1.put("obj", jsonObjectDirecta);
            request.getPermisosDirecta(getContext(), jsonObjectDirecta1);
        } catch (Exception e) {
        }

        if (request.extencionesMat == false) {
            extSer = 0;
            Array.detalleBit.clear();
            Array.detalleBit.add(0, "---Seleccionar---");
            Array.detalleBit.add(1, "1");
            ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, Array.detalleBit);
            descripcionMat.setAdapter(arrayAdapter);
        }

        request.getPredescarga(getActivity(), getContext());

        descripcionMat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posDescMat = position;
                aux=0;
                if (position != 0) {
                    request.pieza = false;
                    Iterator<List<DetalleBitacoraModel>> itData = Array.dataDetBit.iterator();
                    List<DetalleBitacoraModel> dat = itData.next();
                    clvTipoDescMat = dat.get(position - 1).catTipoArticuloClave;
                    //Toast.makeText(getContext(), clvTipoDescMat, Toast.LENGTH_SHORT).show();
                    request.DetalleBit(getContext());
                    request.LlenaExt(getContext());
                    STB=dat.get(position-1).Descripcion.toString();
                    if(dat.get(position-1).Descripcion.indexOf("ibra") != -1 || dat.get(position-1).Descripcion.indexOf("IBRA") != -1){
                        fibra = true;
                        conectores = false;
                        //Toast.makeText(getContext(), "Fibra", Toast.LENGTH_SHORT).show();
                        piezasMat.setVisibility(View.GONE);
                        metrosMat.setVisibility(View.VISIBLE);
                        extMat.setVisibility(View.VISIBLE);
                        spinnerExtMat.setVisibility(View.VISIBLE);
                        txtclas.setVisibility(View.VISIBLE);
                        clasificacionMat.setVisibility(View.VISIBLE);
                        EDPL.setVisibility(View.GONE);
                        TVPL.setVisibility(View.GONE);
                    }
                    else if(dat.get(position-1).Descripcion.indexOf("onecto") != -1 ||
                            dat.get(position-1).Descripcion.indexOf("iviso") != -1 ||
                            dat.get(position-1).Descripcion.indexOf("ujetaci") != -1||
                            dat.get(position-1).Descripcion.indexOf("ujetaci") != -1 ||
                            dat.get(position-1).Descripcion.indexOf("ONECTO") != -1 ||
                            dat.get(position-1).Descripcion.indexOf("IVISO") != -1 ||
                            dat.get(position-1).Descripcion.indexOf("UJETACI") != -1||
                            dat.get(position-1).Descripcion.indexOf("UJETACI") != -1 ||
                            clvTipoDescMat==9
                            || clvTipoDescMat==2283 || clvTipoDescMat==2286 || clvTipoDescMat==2286 || clvTipoDescMat==2287 || clvTipoDescMat==2300 || clvTipoDescMat==2302
                    ) {
                        fibra = false;
                        conectores = true;

                        //Toast.makeText(getContext(), "Conectores", Toast.LENGTH_SHORT).show();
                        piezasMat.setVisibility(View.VISIBLE);
                        metrosMat.setVisibility(View.GONE);
                        extMat.setVisibility(View.VISIBLE);
                        spinnerExtMat.setVisibility(View.VISIBLE);
                        txtclas.setVisibility(View.VISIBLE);
                        clasificacionMat.setVisibility(View.VISIBLE);
                        EDPL.setVisibility(View.GONE);
                        TVPL.setVisibility(View.GONE);
                    }
                    else{
                        fibra = false;
                        conectores = false;
                        //Toast.makeText(getContext(), "Ninguna", Toast.LENGTH_SHORT).show();
                        piezasMat.setVisibility(View.GONE);
                        metrosMat.setVisibility(View.VISIBLE);
                        extMat.setVisibility(View.VISIBLE);
                        spinnerExtMat.setVisibility(View.VISIBLE);
                        txtclas.setVisibility(View.VISIBLE);
                        clasificacionMat.setVisibility(View.VISIBLE);
                        EDPL.setVisibility(View.GONE);
                        TVPL.setVisibility(View.GONE);
                    }
                    request.DetalleBit(getContext());
                }else{
                    txtclas.setVisibility(View.GONE);
                    clasificacionMat.setVisibility(View.GONE);
                    extMat.setVisibility(View.GONE);
                    spinnerExtMat.setVisibility(View.GONE);
                    piezasMat.setVisibility(View.GONE);
                    metrosMat.setVisibility(View.GONE);
                    EDPL.setVisibility(View.GONE);
                    TVPL.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        clasificacionMat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posClasMat = position;
                aux=0;
                if (position != 0) {
                    Iterator<List<DescripcionArticuloModel>> itData = Array.dataDetArtBit.iterator();
                    List<DescripcionArticuloModel> dat = itData.next();
                    idArticuloDM = dat.get(position - 1).IdArticulo;
                    cantidadDM = dat.get(position - 1).Cantidad;
                    idInventarioMD = dat.get(position - 1).IdInventario;
                    descripcionMaterial = dat.get(position - 1).Nombre;
                    request.getTipoMat(getContext());
                    seleccion = position;
                    posClasMat = position;
                    if (request.pieza == true) {
                        fibra = false;
                        conectores = true;
                        //Toast.makeText(getContext(), "Conectores", Toast.LENGTH_SHORT).show();
                        piezasMat.setVisibility(View.VISIBLE);
                        metrosMat.setVisibility(View.GONE);
                        extMat.setVisibility(View.VISIBLE);
                        spinnerExtMat.setVisibility(View.VISIBLE);
                        txtclas.setVisibility(View.VISIBLE);
                        clasificacionMat.setVisibility(View.VISIBLE);
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
                posExtMat = position;
                aux=0;
                if (posExtMat != 0) {
                    Iterator<List<LlenaExtencionesModel>> itData = Array.dataLlenaExt.iterator();
                    List<LlenaExtencionesModel> dat = itData.next();
                    extSer = dat.get(position - 1).ID;
                    //metrosMat.setVisibility(View.VISIBLE);
                    if(request.PermBolivia==true && fibra==true && conectores==false  && request.pieza == false && STB.equals("IDENTIFICADORES")==false){
                        tvInterior.setText("Metraje");

                        tvExterior.setVisibility(View.GONE);
                        tvInterior.setVisibility(View.VISIBLE);
                        mII.setVisibility(View.VISIBLE);
                        mIE.setVisibility(View.GONE);
                        mFI.setVisibility(View.VISIBLE);
                        mFE.setVisibility(View.GONE);
                        aux=2;
                    }else if(extSer == 0 && fibra==true && conectores==false  && request.pieza == false){
                        tvExterior.setVisibility(View.VISIBLE);
                        tvInterior.setVisibility(View.GONE);
                        mII.setVisibility(View.GONE);
                        mIE.setVisibility(View.VISIBLE);
                        mFI.setVisibility(View.GONE);
                        mFE.setVisibility(View.VISIBLE);
                        aux=1;
                        Services.Band = 1;
                    }else if(extSer > 0 && fibra==true && conectores==false && request.pieza == false){
                        tvExterior.setVisibility(View.VISIBLE);
                        tvInterior.setVisibility(View.GONE);
                        mII.setVisibility(View.GONE);
                        mIE.setVisibility(View.VISIBLE);
                        mFI.setVisibility(View.GONE);
                        mFE.setVisibility(View.VISIBLE);
                        aux=1;
                        Services.Band = 1;
                    }else if(extSer == 0 && fibra==false && conectores==false && request.pieza == false){
                        if(request.PermBolivia==true){
                            tvInterior.setText("Metraje");

                            tvExterior.setVisibility(View.GONE);
                            tvInterior.setVisibility(View.VISIBLE);
                            mII.setVisibility(View.VISIBLE);
                            mIE.setVisibility(View.GONE);
                            mFI.setVisibility(View.VISIBLE);
                            mFE.setVisibility(View.GONE);
                            aux=2;
                        }else{
                            tvExterior.setVisibility(View.VISIBLE);
                            tvInterior.setVisibility(View.VISIBLE);
                            mII.setVisibility(View.VISIBLE);
                            mIE.setVisibility(View.VISIBLE);
                            mFI.setVisibility(View.VISIBLE);
                            mFE.setVisibility(View.VISIBLE);
                            aux=3;
                        }
                    }else if(extSer > 0 && fibra==false && conectores==false && request.pieza == false){
                        tvExterior.setVisibility(View.GONE);
                        tvInterior.setVisibility(View.VISIBLE);
                        mII.setVisibility(View.VISIBLE);
                        mIE.setVisibility(View.GONE);
                        mFI.setVisibility(View.VISIBLE);
                        mFE.setVisibility(View.GONE);
                        aux=4;
                    }else if (fibra==false && request.pieza == true) {
                        fibra = false;
                        conectores = true;
                        //Toast.makeText(getContext(), "Conectores", Toast.LENGTH_SHORT).show();
                        piezasMat.setVisibility(View.VISIBLE);
                        metrosMat.setVisibility(View.GONE);
                        extMat.setVisibility(View.VISIBLE);
                        spinnerExtMat.setVisibility(View.VISIBLE);
                        txtclas.setVisibility(View.VISIBLE);
                        clasificacionMat.setVisibility(View.VISIBLE);
                    }
                    request.getPredescarga(getActivity(), getContext());
                    if(STB.indexOf("TIFICADO") != -1){
                        //Toast.makeText(getContext(), "Seleccione artículo", Toast.LENGTH_SHORT).show();
                        EDPL.setVisibility(View.VISIBLE);
                        TVPL.setVisibility(View.VISIBLE);

                    }

                }else{
                    //metrosMat.setVisibility(View.GONE);
                    extSer = (position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        agragarDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(STB.indexOf("TIFICADO") != -1){
                    request.PermPlaca=false;
                    EjecutarOrdenes.ActualizarPlaca();
                    if(EDPL.getText().length()==0){
                        Toast.makeText(getContext(), "Escriba la placa", Toast.LENGTH_SHORT).show();
                    }else{
                        try{
                            JSONObject jsonPlaca = new JSONObject();
                            jsonPlaca.put("Contrato", request.ContratoReal);
                            jsonPlaca.put("Placa", EDPL.getText());
                            jsonPlaca.put("Clv_Orden",Util.getClvOrden(Util.preferences));
                            JSONObject PlacaEntity = new JSONObject();
                            PlacaEntity.put("ObjPlaca", jsonPlaca);
                            request.addPlaca(getContext(),PlacaEntity);
                        }catch (Exception e){

                        }
                        Ejecturar();
                    }
                }else{
                    Ejecturar();
                }

            }
        });

        return view;
    }

    private void Ejecturar(){
        if(posDescMat==0){
            Toast.makeText(getContext(), "Seleccione artículo", Toast.LENGTH_SHORT).show();
        }else if(posClasMat==0){
            Toast.makeText(getContext(), "Seleccione clasificación", Toast.LENGTH_SHORT).show();
        }else if(posExtMat==0){
            Toast.makeText(getContext(), "Seleccione Extención", Toast.LENGTH_SHORT).show();
        }else if(conectores == true && pieza.getText().length()==0){
            Toast.makeText(getContext(), "Ingrese cantidad", Toast.LENGTH_SHORT).show();
        }else if(aux==1 && (mIE.getText().toString().length() == 0 || mFE.getText().toString().length() == 0)){
            Toast.makeText(getContext(), "Ingrese la métrica exterior", Toast.LENGTH_SHORT).show();
        }else if(aux==2 && (mII.getText().toString().length() == 0 || mFI.getText().toString().length() == 0)){
            Toast.makeText(getContext(), "Ingrese las métricas", Toast.LENGTH_SHORT).show();
        }else if(aux==3 && (mII.getText().toString().length() == 0 || mFI.getText().toString().length() == 0 || mIE.getText().toString().length() == 0 || mFE.getText().toString().length() == 0)){
            Toast.makeText(getContext(), "Ingrese todas las métricas", Toast.LENGTH_SHORT).show();
        }else if(aux==4 && (mII.getText().toString().length() == 0 || mFI.getText().toString().length() == 0)){
            Toast.makeText(getContext(), "Ingrese las métrica interior", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(getContext(), "Todo bien", Toast.LENGTH_SHORT).show();
            EjecutarDescargaMaterial();
        }/**/
    }

    public void EjecutarDescargaMaterial() {
        if (request.pieza == true) {
            try{
                piezaSer = Integer.parseInt(String.valueOf(pieza.getText()));
            }catch (Exception e){
                piezaSer = Integer.parseInt(mFE.getText().toString());
            }
            totalDM = piezaSer;
            IIDM = 0;
            IFDM = 0;
            EIMD = 0;
            EFDM = 0;
            if (cantidadDM >= totalDM) {
                GetGetDescargaMaterialArticulosByIdClvOrdenListResult nuevo = new GetGetDescargaMaterialArticulosByIdClvOrdenListResult();
                nuevo.setCANTIDADUTILIZADA(MaterialesOrdenes.totalDM);
                nuevo.setClvOrdSer(Util.getClvOrden(Util.preferences));
                nuevo.setDescripcion(MaterialesOrdenes.descripcionMaterial);//Descripcion de articulo
                nuevo.setESCABLE(request.escable);
                nuevo.setMETRAJEFIN(MaterialesOrdenes.IFDM);
                nuevo.setMETRAJEFINEXTERIOR(MaterialesOrdenes.EFDM);
                nuevo.setMETRAJEINICIO(MaterialesOrdenes.IIDM);
                nuevo.setMETRAJEINICIOEXTERIOR(MaterialesOrdenes.EIMD);
                nuevo.setNOARTICULO(MaterialesOrdenes.idInventarioMD);
                nuevo.setNoExt(MaterialesOrdenes.extSer);
                try {
                    nuevo.setTecnico(Util.getNombreTecnicoPreference(Util.preferences));
                } catch (Exception e) {
                    nuevo.setTecnico("");
                }
                nuevo.setTipoDescarga(Util.getTipoDescarga(Util.preferences));
                nuevo.setIdDescarga(0);

                Array.dataDescargaDirecta.get(0).add(nuevo);

                Iterator<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>> itdata = Array.dataDescargaDirecta.iterator();
                List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult> dat = itdata.next();

                Log.d("asd", Array.dataDescargaDirecta.toString());
                Log.d("asd", nuevo.toString());

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
                fibra = false;
                conectores = false;
                posDescMat=0;
                posClasMat=0;
                posExtMat=0;
                aux=0;
                request.getChecaExt(getContext());
            } else {
                Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            if((aux==1 || aux==3)&&(Integer.parseInt(mIE.getText().toString()) >  Integer.parseInt(mFE.getText().toString()))){
                Toast.makeText(getContext(), "El inicio es mas grande que el final", Toast.LENGTH_SHORT).show();
            }else if((aux==2 || aux==3 || aux==4)&&(Integer.parseInt(mII.getText().toString()) >  Integer.parseInt(mFI.getText().toString()))){
                Toast.makeText(getContext(), "El inicio es mas grande que el final", Toast.LENGTH_SHORT).show();
            }else{
                //Toast.makeText(getContext(), "Todo bien", Toast.LENGTH_SHORT).show();
                try {
                    IIDM = Integer.parseInt(String.valueOf(mII.getText()));
                    IFDM = Integer.parseInt(String.valueOf(mFI.getText()));
                }catch (Exception e){
                    EIMD = 0;
                    EFDM = 0;
                }
                try {
                    EIMD = Integer.parseInt(String.valueOf(mIE.getText()));
                    EFDM = Integer.parseInt(String.valueOf(mFE.getText()));
                }catch (Exception e){
                    EIMD = 0;
                    EFDM = 0;
                }
                metros = (IFDM - IIDM) + (EFDM - EIMD);
                totalDM = metros;
                if (cantidadDM >= totalDM) {
                    GetGetDescargaMaterialArticulosByIdClvOrdenListResult nuevo = new GetGetDescargaMaterialArticulosByIdClvOrdenListResult();
                    nuevo.setCANTIDADUTILIZADA(MaterialesOrdenes.totalDM);
                    nuevo.setClvOrdSer(Util.getClvOrden(Util.preferences));
                    nuevo.setDescripcion(MaterialesOrdenes.descripcionMaterial);
                    nuevo.setESCABLE(request.escable);
                    nuevo.setMETRAJEFIN(MaterialesOrdenes.IFDM);
                    nuevo.setMETRAJEFINEXTERIOR(MaterialesOrdenes.EFDM);
                    nuevo.setMETRAJEINICIO(MaterialesOrdenes.IIDM);
                    nuevo.setMETRAJEINICIOEXTERIOR(MaterialesOrdenes.EIMD);
                    nuevo.setNOARTICULO(MaterialesOrdenes.idInventarioMD);
                    nuevo.setNoExt(MaterialesOrdenes.extSer);
                    try {
                        nuevo.setTecnico(Util.getNombreTecnicoPreference(Util.preferences));
                    } catch (Exception e) {
                        nuevo.setTecnico("");
                    }
                    nuevo.setTipoDescarga(Util.getTipoDescarga(Util.preferences));
                    nuevo.setIdDescarga(0);
                    Array.dataDescargaDirecta.get(0).add(nuevo);

                    Iterator<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>> itdata = Array.dataDescargaDirecta.iterator();
                    List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult> dat = itdata.next();

                    Log.d("asd", Array.dataDescargaDirecta.toString());
                    Log.d("asd", nuevo.toString());

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
                    fibra = false;
                    conectores = false;
                    posDescMat=0;
                    posClasMat=0;
                    posExtMat=0;
                    aux=0;
                    request.getChecaExt(getContext());
                } else {
                    Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        }
        EDPL.setVisibility(View.GONE);
        TVPL.setVisibility(View.GONE);
        STB="";
    }
}