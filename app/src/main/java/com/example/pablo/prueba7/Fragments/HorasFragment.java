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


import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class HorasFragment extends Fragment  implements View.OnClickListener {
    Request request = new Request();
    public static String diaV11, mesV11, añoV11, diaV21, mesV21, añoV21, diaV3, mesV3, añoV3;
    public static TextView reportesselectDate1, reportesselectDate2, reportesselectDate3;
    public static int reporteEjecutada = 1, repotteVisita = 0, reporteVisita1 = 0, reporteVisita2 = 0, TecSecSelecc1 = -1;
    private int mYear, mMonth, mDay;
    private View contenedorParticular;
    private View contenedorCorporativo;
    public static Spinner TecSec1;
    private ConstraintLayout todo;
    private RadioButton btn1, bt2;
    public static int tecPosRepo;
    public static String statusHora = "E";

    public HorasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_horas, container, false);
        request.getTecSecR(getContext());
        todo = view.findViewById(R.id.todo);
        TecSec1 = view.findViewById(R.id.tecnicosec1);
        request.getTecSecR(getContext());

        TecSec1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TecSecSelecc1 = Array.Clv_TecSecR.get(position);
                tecPosRepo=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //////////// acciones de botones de hora y fecha//////

        reportesselectDate1 = view.findViewById(R.id.visita11);
        reportesselectDate2 = view.findViewById(R.id.visita21);
        reportesselectDate3 = view.findViewById(R.id.visita31);
        ///////////////////////////////////////////////////////

        ///////////contenedores y acciones de radiobuttons////
        contenedorParticular = view.findViewById(R.id.RE1);
        contenedorCorporativo = view.findViewById(R.id.RV1);

        btn1 = view.findViewById(R.id.ejutada1);
        bt2 = view.findViewById(R.id.visitada1);
        /////////////////////////////////////////////////////

        ////////// fecaha, hora y radio buttons/////////
        reportesselectDate1.setOnClickListener(this);
        reportesselectDate2.setOnClickListener(this);
        reportesselectDate3.setOnClickListener(this);
        bt2.setOnClickListener(this);
        btn1.setOnClickListener(this);

        reportesselectDate2.setEnabled(false);
        reportesselectDate3.setEnabled(false);

        return view;
    }

    @Override
    public void onClick(View v) {

        btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (btn1.isChecked() == true) {
                    reporteEjecutada = 0;
                    repotteVisita = 1;
                    statusHora = "V";
                    reportesselectDate1.setText("");
                    reportesselectDate2.setText("");
                    reportesselectDate3.setText("");
                    reporteEjecutada = 0;
                    repotteVisita = 1;
                    statusHora = "V";
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
                    reportesselectDate1.setText("");
                    reportesselectDate2.setText("");
                    reportesselectDate3.setText("");
                    reporteEjecutada = 1;
                    repotteVisita = 0;
                    statusHora = "E";
                }

            }
        });
        switch (v.getId()) {
            case R.id.ejutada1: {
                mostrarParticular(false);
                reportesselectDate1.setText("");
                reportesselectDate2.setText("");
                reportesselectDate3.setText("");
                reporteEjecutada = 0;
                repotteVisita = 1;
                statusHora = "V";
            }
            break;
            case R.id.visitada1: {
                mostrarParticular(true);
                reportesselectDate2.setText("");
                reportesselectDate3.setText("");
                reporteEjecutada = 1;
                repotteVisita = 0;
                statusHora = "E";
            }
            break;
        }
            if (v == reportesselectDate1) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog1 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (monthOfYear < 10) {
                            if (dayOfMonth < 10) {
                                reportesselectDate1.setText("0" + dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                diaV11 = "0" + String.valueOf(dayOfMonth);
                                mesV11 = "0" + String.valueOf((monthOfYear + 1));
                                añoV11 = String.valueOf(year);
                            } else {
                                reportesselectDate1.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                diaV11 = String.valueOf(dayOfMonth);
                                mesV11 = "0" + String.valueOf((monthOfYear + 1));
                                añoV11 = String.valueOf(year);
                            }
                        } else {
                            reportesselectDate1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            diaV11 = String.valueOf(dayOfMonth);
                            mesV11 = String.valueOf((monthOfYear + 1));
                            añoV11 = String.valueOf(year);
                        }

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog1.show();
            }


            if (v == reportesselectDate2) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        if (monthOfYear < 10) {
                            if (dayOfMonth < 10) {
                                reportesselectDate2.setText("0" + dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                diaV21 = "0" + String.valueOf(dayOfMonth);
                                mesV21 = "0" + String.valueOf((monthOfYear + 1));
                                añoV21 = String.valueOf(year);
                            } else {
                                reportesselectDate2.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                diaV21 = String.valueOf(dayOfMonth);
                                mesV21 = "0" + String.valueOf((monthOfYear + 1));
                                añoV21 = String.valueOf(year);
                            }
                        } else {
                            reportesselectDate2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            diaV21 = String.valueOf(dayOfMonth);
                            mesV21 = String.valueOf((monthOfYear + 1));
                            añoV21 = String.valueOf(year);
                        }

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }

            if (v == reportesselectDate3) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (monthOfYear < 10) {
                            if (dayOfMonth < 10) {
                                reportesselectDate3.setText("0" + dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                diaV3 = "0" + String.valueOf(dayOfMonth);
                                mesV3 = "0" + String.valueOf((monthOfYear + 1));
                                añoV3 = String.valueOf(year);
                            } else {
                                reportesselectDate3.setText(dayOfMonth + "/0" + (monthOfYear + 1) + "/" + year);
                                diaV3 = String.valueOf(dayOfMonth);
                                mesV3 = "0" + String.valueOf((monthOfYear + 1));
                                añoV3 = String.valueOf(year);
                            }
                        } else {
                            reportesselectDate3.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            diaV3 = String.valueOf(dayOfMonth);
                            mesV3 = String.valueOf((monthOfYear + 1));
                            añoV3 = String.valueOf(year);
                        }

                    }

                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        }
    private void mostrarParticular(boolean b) {
        contenedorParticular.setVisibility(b ? View.VISIBLE: View.GONE);
        contenedorCorporativo.setVisibility(b ? View.GONE: View.VISIBLE);
    }
}








