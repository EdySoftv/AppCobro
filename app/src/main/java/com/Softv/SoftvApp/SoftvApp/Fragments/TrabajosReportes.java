package com.Softv.SoftvApp.SoftvApp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrabajosReportes extends Fragment implements View.OnClickListener {
    public static Spinner solucion;
    public static TextView desc, problm,prioridad,clasific;
    public static  EditText proble;
    private Request request = new Request();
    public static int Clv_Sol=-1,posSolucionRepo=0;
    private TextView car1;
    private EditText cadena;


    public TrabajosReportes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.activity_trabajos_reportes, container, false);
        request.getnombretec(getContext());

        request.getReportesC(getContext());
        request.getReportes(getContext());
        proble=view.findViewById(R.id.problema);
        solucion = view.findViewById(R.id.tiposol);

        car1=(TextView) view.findViewById(R.id.textView38);
        cadena=(EditText) view.findViewById(R.id.problema);

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

        cadena.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                car1.setText(String.valueOf(cadena.length()));
                //Toast.makeText(getApplicationContext(), "No cuenta con conexión a Internet", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

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


