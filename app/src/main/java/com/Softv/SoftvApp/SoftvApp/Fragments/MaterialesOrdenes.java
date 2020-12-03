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
    public static EditText pieza, mII, mIE, mFI, mFE;
    public static TextView tvExterior, tvInterior,textViewInicioTitulo,textViewFinTitulo, txtclas;
    public static int clvTipoDescMat, idArticuloDM, cantidadDM, idInventarioMD, piezaSer, metros, totalDM, IIDM, IFDM, EIMD, EFDM;
    public static int extSer, esFibra;
    public static String descripcionMaterial = "";
    public static Spinner descripcionMat, clasificacionMat, spinnerExtMat;
    public static ConstraintLayout extMat, piezasMat, metrosMat;
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
        textViewInicioTitulo= view.findViewById(R.id.textViewInicioTitulo);
        textViewFinTitulo= view.findViewById(R.id.textViewFinTitulo);

        //Escondemos lo que no se ocupa
        extMat.setVisibility(View.GONE);
        piezasMat.setVisibility(View.GONE);
        txtclas.setVisibility(View.GONE);
        clasificacionMat.setVisibility(View.GONE);
        metrosMat.setVisibility(View.GONE);

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
                if (position != 0) {
                    Iterator<List<DetalleBitacoraModel>> itData = Array.dataDetBit.iterator();
                    List<DetalleBitacoraModel> dat = itData.next();
                    clvTipoDescMat = dat.get(position - 1).catTipoArticuloClave;
                    request.DetalleBit(getContext());
                    if (clvTipoDescMat == 3) {
                        extMat.setVisibility(View.VISIBLE);
                        piezasMat.setVisibility(View.GONE);
                        txtclas.setVisibility(View.VISIBLE);
                        clasificacionMat.setVisibility(View.VISIBLE);
                        metrosMat.setVisibility(View.GONE);
                    }else if (clvTipoDescMat == 4) {
                        extMat.setVisibility(View.GONE);
                        piezasMat.setVisibility(View.VISIBLE);
                        txtclas.setVisibility(View.VISIBLE);
                        clasificacionMat.setVisibility(View.VISIBLE);
                        metrosMat.setVisibility(View.GONE);
                    }else{
                        extMat.setVisibility(View.VISIBLE);
                        piezasMat.setVisibility(View.GONE);
                        txtclas.setVisibility(View.VISIBLE);
                        clasificacionMat.setVisibility(View.VISIBLE);
                        metrosMat.setVisibility(View.GONE);
                    }
                }else{
                    txtclas.setVisibility(View.GONE);
                    clasificacionMat.setVisibility(View.GONE);
                    extMat.setVisibility(View.GONE);
                    piezasMat.setVisibility(View.GONE);
                    metrosMat.setVisibility(View.GONE);
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
                if (position != 0) {
                    Iterator<List<LlenaExtencionesModel>> itData = Array.dataLlenaExt.iterator();
                    List<LlenaExtencionesModel> dat = itData.next();
                    extSer = dat.get(position - 1).ID;
                    metrosMat.setVisibility(View.VISIBLE);
                    if(extSer == 0 && clvTipoDescMat==3){
                        tvExterior.setVisibility(View.VISIBLE);
                        tvInterior.setVisibility(View.GONE);
                        mII.setVisibility(View.GONE);
                        mIE.setVisibility(View.VISIBLE);
                        mFI.setVisibility(View.GONE);
                        mFE.setVisibility(View.VISIBLE);
                    }else if(extSer == 0 && clvTipoDescMat!=3){
                        tvExterior.setVisibility(View.VISIBLE);
                        tvInterior.setVisibility(View.VISIBLE);
                        mII.setVisibility(View.VISIBLE);
                        mIE.setVisibility(View.VISIBLE);
                        mFI.setVisibility(View.VISIBLE);
                        mFE.setVisibility(View.VISIBLE);
                    }else if(extSer != 0){
                        tvExterior.setVisibility(View.GONE);
                        tvInterior.setVisibility(View.VISIBLE);
                        mII.setVisibility(View.VISIBLE);
                        mIE.setVisibility(View.GONE);
                        mFI.setVisibility(View.VISIBLE);
                        mFE.setVisibility(View.GONE);
                    }
                    request.getPredescarga(getActivity(), getContext());
                }else{
                    metrosMat.setVisibility(View.GONE);
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
                if(posDescMat==0){
                    Toast.makeText(getContext(), "Seleccione artículo", Toast.LENGTH_SHORT).show();
                }else if(posClasMat==0){
                    Toast.makeText(getContext(), "Seleccione clasificación", Toast.LENGTH_SHORT).show();
                }else if(clvTipoDescMat!=4 && posExtMat==0){
                    Toast.makeText(getContext(), "Seleccione extensión", Toast.LENGTH_SHORT).show();
                }else if(clvTipoDescMat!=4 && extSer == 0 && clvTipoDescMat==3 && (mIE.getText().toString().length() == 0 || mFE.getText().toString().length() == 0)){
                    Toast.makeText(getContext(), "Ingrese la métrica exterior", Toast.LENGTH_SHORT).show();
                }else if(clvTipoDescMat!=4 && extSer == 0 && clvTipoDescMat!=3 &&(mII.getText().toString().length() == 0 || mFI.getText().toString().length() == 0 || mIE.getText().toString().length() == 0 || mFE.getText().toString().length() == 0)){
                    Toast.makeText(getContext(), "Ingrese las métricas", Toast.LENGTH_SHORT).show();
                }else if(clvTipoDescMat!=4 && extSer != 0 && (mII.getText().toString().length() == 0 || mFI.getText().toString().length() == 0)){
                    Toast.makeText(getContext(), "Ingrese la métrica interior", Toast.LENGTH_SHORT).show();
                }else if(clvTipoDescMat==4 && pieza.getText().length()==0){
                    Toast.makeText(getContext(), "Ingrese cantidad", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Todo bien", Toast.LENGTH_SHORT).show();
                    EjecutarDescargaMaterial();
                }
            }
        });

        return view;
    }

    public void EjecutarDescargaMaterial() {
        if (request.pieza == true) {
            piezaSer = Integer.parseInt(String.valueOf(pieza.getText()));
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
                request.getChecaExt(getContext());
            } else {
                Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
            }
        } else {

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
                request.getChecaExt(getContext());
            } else {
                Toast.makeText(getContext(), "Cantidad incorrecta", Toast.LENGTH_SHORT).show();
            }
        }
    }
}