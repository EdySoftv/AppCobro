package com.example.pablo.prueba7.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrabajosFragment extends Fragment implements View.OnClickListener {
    public static Spinner solucion;
    public static TextView desc, problm,prioridad,clasific;
    public static  EditText proble;
    private Request request = new Request();
    public static int Clv_Sol=-1,posSolucionRepo=0;


    public TrabajosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_trabajos2, container, false);
        request.getnombretec(getContext());
        request.getSolucuion(getContext());
        request.getReportesC(getContext());
        request.getReportes(getContext());
        proble=view.findViewById(R.id.problema);
        solucion = view.findViewById(R.id.tiposol);
        solucion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Clv_Sol = Array.clv_Soluc.get(position);
                posSolucionRepo=position;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        clasific= view.findViewById(R.id.Sp1);
        prioridad= view.findViewById(R.id.Sp2);
        desc= view.findViewById(R.id.observa);
        problm= view.findViewById(R.id.report);
        return view;
    }
    @Override
    public void onClick(View view) {

    }
}


