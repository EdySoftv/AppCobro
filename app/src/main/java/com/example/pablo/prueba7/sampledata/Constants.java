package com.example.pablo.prueba7.sampledata;

public class Constants {
    //public static final String NEW_URL="http://192.168.50.116:5600/SoftvWCFService.svc/";
    public static final String NEW_URL="http://45.226.32.2:5600/SoftvWCFService.svc/";
    public static final String URL_GET_USER = "Usuario/LogOn";
    public static final String URL_GET_TECNICO = "AplicacionMovil/Get_ClvTecnico";
    public static final String URL_GET_PROX = "AplicacionMovil/GetDameSiguienteCita";
    public static final String URL_GET_ORDQUE="AplicacionMovil/GetDameOrdenesQuejasTotales";
    public static final String URL_GET_LIST_ORD="AplicacionMovil/GetDameListadoOrdenesAgendadas";
    public static final String URL_GET_DEEP_CONS="ConsultaOrdSer/GetDeepConsultaOrdSer";
    public static final String URL_GET_INFO_CLIENTE="BUSCLIPORCONTRATO_OrdSer/GetDeepBUSCLIPORCONTRATO_OrdSer";
    public static final String URL_GET_SERVICIOS="AplicacionMovil/GetdameSerDELCliresumen";
    public static final String URL_GET_ORDENES = "BUSCADetOrdSer/GetBUSCADetOrdSerList";
    public static final String URL_GET_TEC_SEC ="MuestraRelOrdenesTecnicos/GetMuestraRelOrdenesTecnicosList";
    public static final String URL_GET_CLI_APA = "MuestraAparatosDisponibles/GetListClienteAparatos";
    public static final String URL_GET_STATUS = "MuestraAparatosDisponibles/GetSP_StatusAparatosList";
    public static final String URL_GET_APA_TIPO = "MuestraAparatosDisponibles/GetListTipoAparatosByIdArticulo";
    public static final String URL_GET_APA_TIP_DIS = "MuestraAparatosDisponibles/GetListAparatosDisponiblesByIdArticulo";
    public static final String URL_GET_DAT_CAMDO = "AplicacionMovil/GetDameDatosCAMDO";
    public static final String URL_GET_ARB_SER = "MuestraArbolServiciosAparatosPorinstalar/GetMuestraArbolServiciosAparatosPorinstalarList";
    public static final String URL_GET_MEDIO_SER = "MuestraMedioPorServicoContratado/GetMuestraMedioPorServicoContratadoList";
    public static final String URL_GET_TIPO_APARATOS = "MuestraTipoAparato/GetMuestraTipoAparatoList";
    public static final String URL_GET_APARATOS_DISPONIBLES="MuestraAparatosDisponibles/GetMuestraAparatosDisponiblesList";
    public static final String URL_GET_SERVICIOS_APARATOS = "MuestraServiciosRelTipoAparato/GetMuestraServiciosRelTipoAparatoList";
    public static final String URL_ACEPTAR_ASIG = "AsignaAparatosAlServicio/GetAsignaAparatosAlServicioList";
    public static final String URL_GET_QUEJAS_AGENDADAS = "AplicacionMovil/GetDameListadoQuejasAgendadas";
    public static final String URL_GET_CONCON = "OrdSer/GetCONCONEX";
    //////////////////////////////////////////////////////////////Reportes////////////////////////////////////////////////////////////////
    public static final String URL_GET_CLIT_REPOR ="uspBuscaContratoSeparado2/GetuspBuscaContratoSeparado2List";
    public static final String URL_GET_SOL="MUESTRATRABAJOSQUEJAS/GetMUESTRATRABAJOSQUEJASList";
    public static final String URL_GET_REPCL="Quejas/GetQuejasList";
    public static final String URL_GET_PRIORI="Softv_GetPrioridadQueja/GetSoftv_GetPrioridadQuejaList";
    public static final String URL_GET_CLASIF="uspConsultaTblClasificacionProblemas/GetuspConsultaTblClasificacionProblemasList";
    public static final String URL_GET_ITEC="OrdSer/GetConTecnicoAgenda";
    public static final String URL_GET_ASERVICE="DameSerDelCliFac/GetDameSerDelCliFacList";
    public static final String URL_GET_TSECR="Muestra_Tecnicos_Almacen/GetMuestra_Tecnicos_AlmacenList";
    //////////////////////////////////////////EjecutarOrdenInstalacion////////////////////////////////////////////
    public static final String URL_GET_VALI_OrdSer="OrdSer/GetSP_ValidaGuardaOrdSerAparatos";
    public static final String URL_GET_CHECA_CAMDO="Checa_si_tiene_camdo/GetCheca_si_tiene_camdo";
    public static final String URL_ADD_REL_ORDUSU="NueRelOrdenUsuario/AddNueRelOrdenUsuario";
    public static final String URL_GET_MODORDSER="MODORDSER/GetDeepMODORDSER";
    public static final String URL_GET_GUARDA_HORA="OrdSer/GetGuardaHoraOrden";
    public static final String URL_GET_GUARDAOrdSerAparatos="SP_GuardaOrdSerAparatos/GetDeepSP_GuardaOrdSerAparatos";
    //public static final String URL_ADD_LLENA_BITACORA_ORD="SP_LLena_Bitacora_Ordenes/AddSP_LLena_Bitacora_Ordenes";
    public static final String URL_GET_GUARDA_COORDENADAS="CLIENTES_New/GetGuardaCoordendasCli";
    public static final String URL_ADD_LLENA_BITACORA_ORD="SP_LLena_Bitacora_Ordenes/AddSP_LLena_Bitacora_Ordenes";
    public static final String URL_GET_CONSULTA_IP="OrdSer/GetConsultaIpPorContrato";
    public static final String URL_GET_REINTENTA_COMANDO="OrdSer/GetReintentarComando";
    ///////////////////////////////////////EjecutarReportes/////////////////
    public static final String URL_Get_HIHF="OrdSer/GetGuardaHoraOrden";
    public static final String URL_GET_UPDATE="Quejas/UpdateQuejas";
    public static final String URL_GET_VALIDA="ValidaQuejaCompaniaAdic/GetDeepValidaQuejaCompaniaAdic";
    /////////////////////////////////////////////////
    public static final String URL_GET_CAPAT="MuestraAparatosDisponibles/GetSetCambioAparato";
    public static final String URL_GET_DEEP_CAPAT="MuestraAparatosDisponibles/GetCambioAparatoDeep";
    public static final String URL_SEND_NOENTREGADOS="SP_InsertaTbl_NoEntregados/GetSP_InsertaTbl_NoEntregados";
    ////////////
    public static final String URL_GET_MUESTRABIT="Muestra_Detalle_Bitacora_2/GetMuestra_Detalle_Bitacora_2List";
    public static final String URL_GET_DETALLEBIT="Muestra_Descripcion_Articulo_2/GetMuestra_Descripcion_Articulo_2List";
    public static final String URL_GET_CHECAEXT="UspChecaSiTieneExtensiones/GetUspChecaSiTieneExtensiones";
    public static final String URL_GET_LLENAEXT="UspChecaSiTieneExtensiones/GetUspLlenaComboExtensionesList";
    public static final String URL_GET_TIPOMAT="OrdSer/GetSoftv_ObtenTipoMaterial";
    public static final String URL_VALIDAPRE="AplicacionMovil/ValidaExisteTblPreDescargaMaterial";
    public static final String URL_ADD_PREDESCARGA="AplicacionMovil/InsertaTblPreDescargaMaterial";
    public static final String URL_GET_PREDESCARGA="AplicacionMovil/dameTblPreDescargaMaterial";
    public static final String URL_ADD_FIRMA="AplicacionMovil/InsertaTblFirmaCliente";
    public static final String URL_VALIDA_FIRMA = "AplicacionMovil/TrabajosFirma";
    ////////MACWAM////////////
    public static final String URL_VALIDA_MACWAM = "AsignaAparatosAlServicio/GetValidaRequiereMacWan";
    public static final String URL_GET_MACWAM = "AsignaAparatosAlServicio/ConMacWanbyClv_Aparato";
    public static final String URL_ASIGNA_MACWAM = "AsignaAparatosAlServicio/GetSaveRelMacsTemp";
}