package com.example.pablo.prueba7.Fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.pablo.prueba7.Activitys.MainReportes;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;

import java.util.Calendar;




/**
 * A simple {@link Fragment} subclass.
 */
public class HorasReportes extends Fragment  implements View.OnClickListener {
    Request request = new Request();
    public static int reporteEjecutada = 0, repotteVisita = 0, reporteVisita1 = 0, reporteVisita2 = 0, TecSecSelecc1 = -1;
    private View contenedorParticular;
    private RadioButton btn1, bt2;
    public static int tecPosRepo;
    public static String statusHora;

    public HorasReportes() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_horas_reporte, container, false);


        ///////////////////////////////////////////////////////

        ///////////contenedores y acciones de radiobuttons////
        contenedorParticular = view.findViewById(R.id.RV7);
        btn1 = view.findViewById(R.id.ejutada1);
        bt2 = view.findViewById(R.id.visitada1);
        /////////////////////////////////////////////////////

        ////////// fecaha, hora y radio buttons/////////

        bt2.setOnClickListener(this);
        btn1.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (btn1.isChecked() == true) {
                    mostrarParticular(false);
                    repotteVisita = 1;
                    statusHora = "V";
                    reporteEjecutada = 0;

                }

            }
        });
        bt2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (bt2.isChecked() == true) {
                    reporteEjecutada = 1;
                    repotteVisita = 0;
                    statusHora = "E";
                    mostrarParticular(true);
                    MainReportes.mViewPager.setCurrentItem(1);
                }

            }
        });

        switch (v.getId()) {
            case R.id.ejutada1: {
                mostrarParticular(false);
                repotteVisita = 1;
                statusHora = "V";
                reporteEjecutada = 0;
            }
            break;
            case R.id.visitada1: {
                reporteEjecutada = 1;
                repotteVisita = 0;
                statusHora = "E";
                mostrarParticular(true);
                MainReportes.mViewPager.setCurrentItem(1);
            }
            break;
        }
    }

    private void mostrarParticular(boolean b) {

        contenedorParticular.setVisibility(b ? View.GONE : View.VISIBLE);
    }
}








