package com.Softv.SoftvApp.SoftvCobro.Listas;

import com.Softv.SoftvApp.SoftvCobro.Modelos.DatosClientesSaldoList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.Get_ClvCajeroResult;
import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelDetallesList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelHistorialDePagoList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelServiciosList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.MuestraVendedoresResult;
import com.Softv.SoftvApp.SoftvCobro.Modelos.Muestra_TecnicosDescargaMaterialResult;
import com.Softv.SoftvApp.SoftvCobro.Modelos.UltimoSerieYFolioUnica;

import org.json.JSONArray;

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

    public static ArrayList<List<ModelHistorialDePagoList>> DataHistorial;
    public static ArrayList<String>ContratoHistorial=new ArrayList<>();
    public static ArrayList<String>HoraConsultaHistorial=new ArrayList<>();
    public static ArrayList<Float>MontoHistorial=new ArrayList<>();

    public static ArrayList<Integer>SessionSaldo=new ArrayList<>();

    public static ArrayList<List<DatosClientesSaldoList>> DataClientes;
    public static ArrayList<String>ContratoCompuestoList=new ArrayList<>();
    public static ArrayList<String>NombreList=new ArrayList<>();
    public static ArrayList<String>TelefonoList=new ArrayList<>();
    public static ArrayList<String>CalleNumeroList=new ArrayList<>();
    public static ArrayList<String>ColoniaList=new ArrayList<>();
    public static ArrayList<String>ContratoList=new ArrayList<>();
    public static ArrayList<String>PlacaList=new ArrayList<>();

    public static ArrayList<List<MuestraVendedoresResult>> dataVendedores;
    public static ArrayList<String>vendedoresLista=new ArrayList<>();

    public static ArrayList<List<UltimoSerieYFolioUnica>> dataUltimoSerieYFolio;
    public static ArrayList<String>UltimoSerieYFolioLista=new ArrayList<>();

    public static JSONArray dataFolioDisponibleRecu;
    public static ArrayList<String>FolioDisponibleRecuLista=new ArrayList<>();

    public static JSONArray dataFolioDisponibleInt;
    public static ArrayList<String>FolioDisponibleIntLista=new ArrayList<>();

    public static JSONArray dataFolioDisponible;
    public static ArrayList<String>FolioDisponibleLista=new ArrayList<>();

}

