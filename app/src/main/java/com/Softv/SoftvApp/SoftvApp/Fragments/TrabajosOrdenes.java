package com.Softv.SoftvApp.SoftvApp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.Softv.SoftvApp.SoftvApp.Adapters.TrabajosAdapter;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;

import static com.Softv.SoftvApp.SoftvApp.Listas.Array.recibix;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrabajosOrdenes extends Fragment{
    public static TrabajosAdapter adaptertrabajos;
    public static ListView trabajos;
    public static boolean ValidaRequiereTrabajo=false,validaCAMDO=false;
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
        for(int x=0; x<Array.trabajox.size(); x++){
            String palabra = Array.trabajox.get(x);
            String[] caracteres = palabra.split(" ");
            Log.d("caracteres0",caracteres[0]);
            Log.d("caracteres1",caracteres[1]);
            if (caracteres[0].equals("ISTVA")) {
                TrabajosAdapter.validarCoordenadas=true;
                TrabajosAdapter.retiro = false;
                ValidaRequiereTrabajo=true;

            }
            if (caracteres[0].equals("ISNET")) {
                TrabajosAdapter.validarCoordenadas=true;
                TrabajosAdapter.retiro = false;
                TrabajosAdapter.isnet=1;
                ValidaRequiereTrabajo=true;
            }
            if (caracteres[0].equals("ISDIG")) {
                TrabajosAdapter.validarCoordenadas=true;
                TrabajosAdapter.retiro = false;
                TrabajosAdapter.ISDIG=true;
                ValidaRequiereTrabajo=true;

            }
            if (caracteres[0].equals("CTCTV")) {
                TrabajosAdapter.validarCoordenadas=true;
                TrabajosAdapter.retiro = false;
                ValidaRequiereTrabajo=true;

            }
            if (caracteres[0].equals("CTCIN")) {
                TrabajosAdapter.validarCoordenadas=true;
                TrabajosAdapter.retiro = false;
                ValidaRequiereTrabajo=true;

            }
            if (caracteres[0].equals("CTCDG")) {
                TrabajosAdapter.validarCoordenadas=true;
                TrabajosAdapter.retiro = false;
                ValidaRequiereTrabajo=true;

            }
            if (caracteres[0].equals("RSTVA")) {
                TrabajosAdapter.validarCoordenadas=true;
                TrabajosAdapter.retiro = false;
                ValidaRequiereTrabajo=true;

            }
            if (caracteres[0].equals("RSNET")) {
                TrabajosAdapter.validarCoordenadas=true;
                TrabajosAdapter.retiro = false;
                ValidaRequiereTrabajo=true;

            }
            if (caracteres[0].equals("RSDIG")) {
                TrabajosAdapter.validarCoordenadas=true;
                TrabajosAdapter.retiro = false;
                ValidaRequiereTrabajo=true;

            }


            //Cambio Aparato
            if (caracteres[0].equals("CAPAG")) {
                TrabajosAdapter.retiro = false;
                TrabajosAdapter.ftth=1;
                ValidaRequiereTrabajo=true;

            }
            if (caracteres[0].equals("CAPAT")) {
                TrabajosAdapter.retiro = false;
                ValidaRequiereTrabajo=true;

            }

            //Cambio Domicilio
            if (caracteres[0].equals("CAMDO")) {
                validaCAMDO=true;
                TrabajosAdapter.validarCoordenadas=true;
                TrabajosAdapter.retiro = false;

            }

            //Extenciones Adicionales
            if (caracteres[0].equals("CONEX")) {
                TrabajosAdapter.retiro = false;
            }
        }
        return view;
    }
}

