package com.Softv.SoftvApp.SoftvCobro.Listas;

import com.Softv.SoftvApp.SoftvCobro.Modelos.DatosClientesSaldoList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.Get_ClvCajeroResult;
import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelDetallesList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelServiciosList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.Muestra_TecnicosDescargaMaterialResult;

import java.util.ArrayList;
import java.util.List;

public class Array {

    //////////////////////////////////////////////////////
    public static  ArrayList<List<Get_ClvCajeroResult>> datatec;

    public static ArrayList <List<Muestra_TecnicosDescargaMaterialResult>>dataPermisosDirecta;
/////////////////////////////////////////////////
    public static ArrayList<List<ModelServiciosList>> DataServicios;
    public static ArrayList<String>ServicioSaldo=new ArrayList<>();
    public static ArrayList<String>StatusSaldo=new ArrayList<>();
    public static ArrayList<String>TipServSaldo=new ArrayList<>();

    public static ArrayList<List<ModelDetallesList>> DataDetalles;
    public static ArrayList<String>DescripcionSaldo=new ArrayList<>();
    public static ArrayList<String>FechaConsultaSaldo=new ArrayList<>();
    public static ArrayList<Float>MontoSaldo=new ArrayList<>();
    public static ArrayList<String>OperacionSaldo=new ArrayList<>();

    public static ArrayList<Integer>SessionSaldo=new ArrayList<>();

    public static ArrayList<List<DatosClientesSaldoList>> DataClientes;
    public static ArrayList<String>ContratoCompuestoList=new ArrayList<>();
    public static ArrayList<String>NombreList=new ArrayList<>();
    public static ArrayList<String>TelefonoList=new ArrayList<>();
    public static ArrayList<String>CalleNumeroList=new ArrayList<>();
    public static ArrayList<String>ColoniaList=new ArrayList<>();
    public static ArrayList<String>ContratoList=new ArrayList<>();


}

