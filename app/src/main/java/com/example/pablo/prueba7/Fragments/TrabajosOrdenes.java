package com.example.pablo.prueba7.Fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pablo.prueba7.Activitys.MainActivity;
import com.example.pablo.prueba7.Adapters.TrabajosAdapter;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.Util;

import org.json.JSONObject;

import static com.example.pablo.prueba7.Listas.Array.recibix;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrabajosOrdenes extends Fragment{
    public static TrabajosAdapter adaptertrabajos;
    public static ListView trabajos;
    Request request = new Request();

    public TrabajosOrdenes() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.activity_trabajos_ordenes, container, false);
        trabajos=view.findViewById(R.id.listTrabajos);
        adaptertrabajos = new TrabajosAdapter(getContext(), Array.trabajox, Array.accionx, recibix);
        trabajos.setAdapter(adaptertrabajos);
        return view;
    }
}

