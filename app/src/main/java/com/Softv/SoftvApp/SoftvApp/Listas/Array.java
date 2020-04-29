package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.DescripcionArticuloModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.DetalleBitacoraModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetBUSCADetOrdSerListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetConTecnicoAgendaResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetConsultaClientesListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetDameDatosCAMDOResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetDameListadoOrdenesAgendadasResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetDameSerDelCliFacListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetGetDescargaMaterialArticulosByIdClvOrdenListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListAparatosDisponiblesByIdArticuloResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListClienteAparatosResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListTipoAparatosByIdArticuloResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMUESTRATRABAJOSQUEJASListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraAparatosDisponiblesListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraMedioPorServicoContratadoListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraRelOrdenesTecnicosListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraServiciosRelTipoAparatoListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraTecnicosAlmacenListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraTipoAparatoListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetQuejasListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetSP_StatusAparatosListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.Get_ClvTecnicoResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetdameSerDELCliresumenResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetuspBuscaContratoSeparado2ListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.ListadoQuejasAgendadas;
import com.Softv.SoftvApp.SoftvApp.Modelos.LlenaExtencionesModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.Muestra_TecnicosDescargaMaterialResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.ObtieneNapModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.ObtieneTapModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.OrdSer;
import com.Softv.SoftvApp.SoftvApp.Modelos.RequierePregunta;
import com.Softv.SoftvApp.SoftvApp.Modelos.SelectRelTecnicoCuadrillaResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.dameTblPreDescargaMaterialResultModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.Queja;
import com.Softv.SoftvApp.SoftvApp.Modelos.mediosPregunta;

import java.util.ArrayList;
import java.util.List;

public class Array {
    public static ArrayList<List<GetdameSerDELCliresumenResult>> dataclientes;
    public static ArrayList<List<Queja>> dataque;
    public static  ArrayList<List<OrdSer>> dataord;
    //////////////////////ARRAY ORDENES//////////////////////
    public static ArrayList<List<GetDameListadoOrdenesAgendadasResult>> dataagenda;
    public static ArrayList<String>contratosrc=new ArrayList<>();
    public static ArrayList<String>ordensrc=new ArrayList<>();
    public static ArrayList<String>nombresrc=new ArrayList<>();
    public static ArrayList<String>statusrc=new ArrayList<>();
    public static ArrayList<String>direccionsrc=new ArrayList<>();
    public static ArrayList<String>napsrc=new ArrayList<>();
    public static ArrayList<String>tapsrc=new ArrayList<>();
    public static ArrayList<String>trabajosrc=new ArrayList<>();
    //----ARRAY QUEJAS----//
    public static ArrayList<List<ListadoQuejasAgendadas>> dataquejas;
    public static ArrayList<String>contratoQ=new ArrayList<>();
    public static ArrayList<String>Queja=new ArrayList<>();
    public static ArrayList<String>nombreQ=new ArrayList<>();
    public static ArrayList<String>statusQ=new ArrayList<>();
    public static ArrayList<String> Direccion= new ArrayList<String>();
    ///////////////////////ARRAY TRABAJOS///////////////////
    public static ArrayList <String>trabajox=new ArrayList<>();
    public static ArrayList <String>observacionesx=new ArrayList<>();
    public static ArrayList<String>accionx=new ArrayList<>();
    public static ArrayList<Integer>clavex=new ArrayList<>();
    public static ArrayList<Integer>clv_trabajox=new ArrayList<>();
    public static ArrayList<Boolean>recibixnew=new ArrayList<>();
    public static ArrayList<Boolean> recibix =  new ArrayList<Boolean>();
    //////////////////////////////////////////////////////
    public static ArrayList <String>nombreArbol=new ArrayList<>();
    public static ArrayList <String>children=new ArrayList<>();
    public static ArrayList <ArrayList<String>>children1=new ArrayList<ArrayList<String>>();
    public static ArrayList<String>medio=new ArrayList<>();
    public static ArrayList<String>tipoAparato=new ArrayList<>();
    public static ArrayList<String>tipoAparatoLetra=new ArrayList<>();
    public static ArrayList<String>aparatoAsignacion=new ArrayList<>();
    public static ArrayList<String>aparatoDisponibles=new ArrayList<>();
    public static ArrayList<String>serviciosAparatos=new ArrayList<>();
    //////////////////////////////////////////////////////
    public static  ArrayList<List<Get_ClvTecnicoResult>> datatec;
    public static ArrayList<List<GetBUSCADetOrdSerListResult>> dataTrabajos;
    public static ArrayList<List<GetMuestraRelOrdenesTecnicosListResult>> dataTecSec;

    public static  ArrayList<Integer> clv_tecnicoSecundario= new ArrayList<>();
    public static  ArrayList<String> nom_tecnicoSecundarioQ= new ArrayList<>();

    public static  ArrayList<String> nom_tecnicoSecundario= new ArrayList<>();
    public static ArrayList<List<GetListClienteAparatosResult>> dataCliApa;
    public static ArrayList<List<GetSP_StatusAparatosListResult>> dataStaApa;
    public static  ArrayList<List<GetListTipoAparatosByIdArticuloResult>> dataApaTipo;
    public static  ArrayList<List<GetListAparatosDisponiblesByIdArticuloResult>> dataApaTipDis;
    public static  ArrayList<List<GetDameDatosCAMDOResult>> dataCAMDO;
    public static  ArrayList<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> dataArbSer;
    public static  ArrayList<List<GetMuestraMedioPorServicoContratadoListResult>> dataMedSer;
    public static  ArrayList<List<GetMuestraTipoAparatoListResult>> dataTipoAparatos;
    public static  ArrayList<List<GetMuestraAparatosDisponiblesListResult>> dataAparatosDisponibles;
    public static  ArrayList<List<GetMuestraServiciosRelTipoAparatoListResult>> dataserviciosAparatos;

    /////////////////////////////////Pantalla_Reportes/////////////////////////////////////////////////////////////////////////
    public static ArrayList<List<GetuspBuscaContratoSeparado2ListResult>> dataRep;
    public static ArrayList<List<GetMUESTRATRABAJOSQUEJASListResult>> dataSOL;
    public static ArrayList<List<GetQuejasListResult>>dataReport;
    public static ArrayList<List<GetConTecnicoAgendaResult>>dataNom;
    public static ArrayList<List<GetDameSerDelCliFacListResult>>dataServ;
    public static  ArrayList<Integer> Clv_TecSecR;
    public static  ArrayList<Integer> clv_Soluc;


    public static ArrayList<List<GetMuestraTecnicosAlmacenListResult>>dataTECSEC;
    public static ArrayList<String> Asigna= new ArrayList<String>();
    public static ArrayList<String> Asigna1= new ArrayList<String>();
    public static ArrayList<String> Asigna2= new ArrayList<String>();
    /////////
    public static ArrayList<List<DetalleBitacoraModel>>dataDetBit;
    public static ArrayList<String> detalleBit= new ArrayList<String>();
    public static ArrayList<List<DescripcionArticuloModel>>dataDetArtBit;
    public static ArrayList<String> descripcionArtBit= new ArrayList<String>();
    public static ArrayList<List<LlenaExtencionesModel>>dataLlenaExt;
    public static ArrayList <List<dameTblPreDescargaMaterialResultModel>>dataPreDescarga;
    public static ArrayList<String> descripcionExt= new ArrayList<String>();
    public static ArrayList<ArrayList<String>> listaTabla= new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> listaTablaDircta= new ArrayList<ArrayList<String>>();
    public static  ArrayList<Integer> clv_Material;
    public static ArrayList <List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>>dataDescargaDirecta;
    public static ArrayList <List<Muestra_TecnicosDescargaMaterialResult>>dataPermisosDirecta;

    /////////////////
    public static ArrayList<List<mediosPregunta>> dataMediosPregunta;
    public static  ArrayList<List<RequierePregunta>> dataPregunta;
    public static ArrayList<String>medioPregunta=new ArrayList<>();

    //TAP-NAP
    public static  ArrayList<List<ObtieneNapModel>> dataNap;
    public static  ArrayList<List<ObtieneTapModel>> dataTap;
    public static ArrayList<String>ClvTenica=new ArrayList<>();

    //hotel

    public static  ArrayList<List<SelectRelTecnicoCuadrillaResult>> dataCuadrilla;
    public static  ArrayList<List<GetConsultaClientesListResult>> dataClientes;

}
