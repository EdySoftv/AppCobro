package com.example.pablo.prueba7.sampledata;


import com.example.pablo.prueba7.Listas.Example;
import com.example.pablo.prueba7.Listas.Example1;
import com.example.pablo.prueba7.Listas.Example2;
import com.example.pablo.prueba7.Listas.Example3;
import com.example.pablo.prueba7.Listas.JSONApaTipDis;
import com.example.pablo.prueba7.Listas.JSONApaTipo;
import com.example.pablo.prueba7.Listas.JSONAparatosDisponibles;
import com.example.pablo.prueba7.Listas.JSONArbolServicios;
import com.example.pablo.prueba7.Listas.JSONCAMDO;
import com.example.pablo.prueba7.Listas.JSONCLIAPA;
import com.example.pablo.prueba7.Listas.JSONClasificacionProblm;
import com.example.pablo.prueba7.Listas.JSONDescripcionArticulosBit;
import com.example.pablo.prueba7.Listas.JSONDetalleBitacora;
import com.example.pablo.prueba7.Listas.JSONLlenaExtenciones;
import com.example.pablo.prueba7.Listas.JSONMediosSer;
import com.example.pablo.prueba7.Listas.JSONNombreTecnico;
import com.example.pablo.prueba7.Listas.JSONPreDescarga;
import com.example.pablo.prueba7.Listas.JSONPrioridad;
import com.example.pablo.prueba7.Listas.JSONReporteCliente;
import com.example.pablo.prueba7.Listas.JSONReportes;
import com.example.pablo.prueba7.Listas.JSONResponseTecnico;
import com.example.pablo.prueba7.Listas.JSONServicioAsignado;
import com.example.pablo.prueba7.Listas.JSONServiciosAparatos;
import com.example.pablo.prueba7.Listas.JSONSolucion;
import com.example.pablo.prueba7.Listas.JSONStatusApa;
import com.example.pablo.prueba7.Listas.JSONTecSec;
import com.example.pablo.prueba7.Listas.JSONTecSecReport;
import com.example.pablo.prueba7.Listas.JSONTipoAparatos;
import com.example.pablo.prueba7.Listas.QuejasList;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface Service {

    @GET(Constants.URL_GET_USER)
    Call<JsonObject> getDataUser();

    @POST(Constants.URL_GET_TECNICO)
    Call<JSONResponseTecnico> getDataTec();

    @POST(Constants.URL_GET_PROX)
    Call<JsonObject> getDataProx();

    @POST(Constants.URL_GET_ORDQUE)
    Call<Example> getDataOrdenes();

    @POST(Constants.URL_GET_CONCON)
    Call<JsonObject> getDataExtencionAdi();

    @POST(Constants.URL_GET_LIST_ORD)
    Call<Example1> getDataListOrd();

    @POST(Constants.URL_GET_DEEP_CONS)
    Call<JsonObject> getDataDeepCons();

    @POST(Constants.URL_GET_INFO_CLIENTE)
    Call<JsonObject> getDataInfoCliente();

    @POST(Constants.URL_GET_SERVICIOS)
    Call<Example2> getDataServicios();

    @POST(Constants.URL_GET_ORDENES)
    Call<Example3> getDataTrabajos();

    @POST(Constants.URL_GET_TEC_SEC)
    Call<JSONTecSec> getDataTecSec();
    @POST(Constants.URL_GET_CLI_APA)
    Call<JSONCLIAPA> getDataCliApa();
    @GET(Constants.URL_GET_STATUS)
    Call<JSONStatusApa> getDataStatusApa();
    @POST(Constants.URL_GET_APA_TIPO)
    Call<JSONApaTipo> getDataApaTipo();
    @POST(Constants.URL_GET_APA_TIP_DIS)
    Call<JSONApaTipDis> getDataApaTipDis();
    @POST(Constants.URL_GET_DAT_CAMDO)
    Call<JSONCAMDO> getDataCAMDO();
    @POST(Constants.URL_GET_ARB_SER)
    Call<JSONArbolServicios> getDataArbSer();
    @POST(Constants.URL_GET_MEDIO_SER)
    Call<JSONMediosSer> getDataMedSer();
    @POST(Constants.URL_GET_TIPO_APARATOS)
    Call<JSONTipoAparatos> getDataTipoAparatos();
    @POST(Constants.URL_GET_APARATOS_DISPONIBLES)
    Call<JSONAparatosDisponibles> getDataAparatosDisponibles();
    @POST(Constants.URL_GET_SERVICIOS_APARATOS)
    Call<JSONServiciosAparatos> getDataServiciosAparatos();
    @POST(Constants.URL_ACEPTAR_ASIG)
    Call<JsonObject> getDataAceptarAsig();

    @POST(Constants.URL_GET_QUEJAS_AGENDADAS)
    Call<QuejasList>getQuejasAgendadas();
    /////////////////////////////////Reportes///////////////////////////////////
    @POST(Constants.URL_GET_CLIT_REPOR)
    Call<JSONReportes> getReport();
    @POST(Constants.URL_GET_SOL)
    Call<JSONSolucion> getSolut();
    @POST(Constants.URL_GET_REPCL)
    Call<JSONReporteCliente>getRPC();
    @GET(Constants.URL_GET_PRIORI)
    Call<JSONPrioridad>getprior();
    @GET(Constants.URL_GET_CLASIF)
    Call<JSONClasificacionProblm>getclas();
    @POST(Constants.URL_GET_ITEC)
    Call<JSONNombreTecnico>getNom();
    @POST(Constants.URL_GET_ASERVICE)
    Call<JSONServicioAsignado>getServ();
    @POST(Constants.URL_GET_TSECR)
    Call<JSONTecSecReport>getTec();
    ///////////////////EjecucionInstalacion/////////////////
    @POST(Constants.URL_GET_VALI_OrdSer)
    Call<JsonObject> getVALIOrdSer();
    @POST(Constants.URL_GET_CHECA_CAMDO)
    Call<JsonObject> getChecaCAMDO();
    @POST(Constants.URL_ADD_REL_ORDUSU)
    Call<JsonObject> getADDRELORDUSU();
    @POST(Constants.URL_GET_MODORDSER)
    Call<JsonObject> getMODORDSER();
    @POST(Constants.URL_GET_GUARDA_HORA)
    Call<JsonObject> getGuardaHora();
    @POST(Constants.URL_GET_GUARDAOrdSerAparatos)
    Call<JsonObject> getGUARDAOrdSerAparatos();
    @POST(Constants.URL_ADD_LLENA_BITACORA_ORD)
    Call<JsonObject> getLLENABITACORA_ORD();
    @POST(Constants.URL_GET_GUARDA_COORDENADAS)
    Call<JsonObject> getGuardaCoordenadas();
    @POST(Constants.URL_GET_CONSULTA_IP)
    Call<JsonObject> getConsultaIp();
    @POST(Constants.URL_GET_REINTENTA_COMANDO)
    Call<JsonObject> getReintentaComando();
    /////////////////////EjecutarReportes/////////////////
    @POST(Constants.URL_Get_HIHF)
    Call<JsonObject>getHiHf();
    @POST(Constants.URL_GET_UPDATE)
    Call<JsonObject>getLLenaReporte();
    @POST(Constants.URL_GET_VALIDA)
    Call<JsonObject>getValidaRep();
    ///////////////////////////////////
    @POST(Constants.URL_GET_CAPAT)
    Call<JsonObject> getCAPAT();
    @POST(Constants.URL_GET_DEEP_CAPAT)
    Call<JsonObject> getDeepCAPAT();
    @POST(Constants.URL_SEND_NOENTREGADOS)
    Call<JsonObject>noent();
    /////////////////
    @POST(Constants.URL_GET_MUESTRABIT)
    Call<JSONDetalleBitacora> getMuestraBit();
    @POST(Constants.URL_GET_DETALLEBIT)
    Call<JSONDescripcionArticulosBit>getDetalleBit();
    @POST(Constants.URL_GET_CHECAEXT)
    Call<JsonObject> getChecaExt();
    @POST(Constants.URL_GET_LLENAEXT)
    Call<JSONLlenaExtenciones>getLlenaExt();
    @POST(Constants.URL_GET_TIPOMAT)
    Call<JsonObject> getTipoMat();
    @POST(Constants.URL_VALIDAPRE)
    Call<JsonObject> getValidaPre();
    @POST(Constants.URL_ADD_PREDESCARGA)
    Call<JsonObject> addPreDescarga();
    @POST(Constants.URL_GET_PREDESCARGA)
    Call<JSONPreDescarga> getPreDescarga();
    @POST(Constants.URL_ADD_FIRMA)
    Call<JsonObject> addFirma();

    @POST(Constants.URL_VALIDA_FIRMA)
    Call<JsonObject> validFirma();


    /////MACWAM///////
    @POST(Constants.URL_VALIDA_MACWAM)
    Call<JsonObject> validaMACWAM();
    @POST(Constants.URL_GET_MACWAM)
    Call<JsonObject> getMACWAM();
    @POST(Constants.URL_ASIGNA_MACWAM)
    Call<JsonObject> asignaMACWAM();

}

