 package com.Softv.SoftvApp.SoftvApp.sampledata;

public class Constants {
    //////////NUEVA IP PUBLICA 187.190.125.85

    //public static final String URL="http://192.168.0.4:1160/";

    //public static final String URL="http://915009d10274.sn.mynetname.net:1560/"; // Fresnill
    public static final String URL="http://177.229.197.2:5600/"; //

    public static final String NEW_URL = URL + "SoftvWCFService.svc/";
    public static final String URL_GET_USER = "Usuario/LogOn";
    public static final String URL_GET_TECNICO = "AplicacionMovil/Get_ClvTecnico";
    //public static final String URL_GET_TECNICO = "AplicacionMovil/Get_ClvCajero";
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
    public static final String URL_ACEPTAR_ASIG2 = "AsignaAparatosAlServicio/GetAsignaAparatosAlServicioList2";
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
    public static final String URL_GET_VALIDA_NODO="OrdSer/GetSoftvWebValidaNodo";
    ///////////////////////////////////////EjecutarReportes/////////////////
    public static final String URL_Get_HIHF="OrdSer/GetGuardaHoraOrden";
    public static final String URL_GET_UPDATE="Quejas/UpdateQuejas";
    public static final String URL_GET_VALIDA="ValidaQuejaCompaniaAdic/GetDeepValidaQuejaCompaniaAdic";
    /////////////////////////////////////////////////
    public static final String URL_GET_CAPAT="MuestraAparatosDisponibles/GetSetCambioAparato";
    public static final String URL_GET_DEEP_CAPAT="MuestraAparatosDisponibles/GetAparatoCambioDeep";
    public static final String URL_SEND_NOENTREGADOS="SP_InsertaTbl_NoEntregados/GetSP_InsertaTbl_NoEntregados";
    public static final String URL_ENVIO_TOKENFIREBASE="AplicacionMovil/AnadirToken";

    ////////////
    public static final String URL_GET_MUESTRABIT="Muestra_Detalle_Bitacora_2/Muestra_Detalle_Bitacora_2_esfibraList";
    public static final String URL_GET_DETALLEBIT="Muestra_Descripcion_Articulo_2/GetMuestra_Descripcion_Articulo_2List";
    public static final String URL_GET_CHECAEXT="UspChecaSiTieneExtensiones/GetUspChecaSiTieneExtensiones";
    public static final String URL_GET_LLENAEXT="UspChecaSiTieneExtensiones/GetUspLlenaComboExtensionesList";
    public static final String URL_GET_TIPOMAT="OrdSer/GetSoftv_ObtenTipoMaterial";
    public static final String URL_VALIDAPRE="AplicacionMovil/ValidaExisteTblPreDescargaMaterial";
    public static final String URL_ELIMINA_PREDESCARGA="AplicacionMovil/EliminarTblPreDescargaMaterial";
    public static final String URL_ADD_PREDESCARGA="AplicacionMovil/InsertaTblPreDescargaMaterial";
    public static final String URL_GET_PREDESCARGA="AplicacionMovil/dameTblPreDescargaMaterial";
    public static final String URL_ADD_FIRMA="AplicacionMovil/InsertaTblFirmaCliente";
    public static final String URL_VALIDA_FIRMA = "AplicacionMovil/TrabajosFirma";
    public static final String URL_ADD_DESCARGA_DIRECTA = "TblDescargaMaterial/GetAddDescargaMaterialArt";
    public static final String URL_BITACORA_DIRECTA = "Softv_DimeSiTieneBitacora/GetchecaBitacoraTecnico";
    public static final String URL_GET_DESCARGA_DIRECTA = "GetDescargaMaterialArticulosByIdClvOrden/GetGetDescargaMaterialArticulosByIdClvOrdenList";
    public static final String URL_GET_PERMISO_DIRECTA = "TblDescargaMaterial/Muestra_TecnicosDescargaMaterial";

    ////////MACWAM////////////
    public static final String URL_VALIDA_MACWAM = "AsignaAparatosAlServicio/GetValidaRequiereMacWan";
    public static final String URL_GET_MACWAM = "AsignaAparatosAlServicio/ConMacWanbyClv_Aparato";
    public static final String URL_ASIGNA_MACWAM = "AsignaAparatosAlServicio/GetSaveRelMacsTemp";//---

    public static final String URL_GET_PREGUNTA = "AplicacionMovil/GetDamePreguntaMedioPorOrden";
    public static final String URL_VALIDA_EXISTE_FIRMA = "AplicacionMovil/ValidaExisteTblFirmaCliente";


    ///NAP-TAP/////////
    public static final String URL_GET_NAP = "Ordser/GetObtieneNap";
    public static final String URL_GET_NAP_CONTRATO = "OrdSer/GetObtieneNapContrato";
    public static final String URL_GUARDA_NAP = "Ordser/GetGuardarRelClienteNap";

    public static final String URL_GET_TAP = "Ordser/GetObtieneTap";
    public static final String URL_GUARDA_TAP = "Ordser/GetGuardarRelClienteTap";


    public static final String URL_GET_COLONIA = "AplicacionMovil/GetColonia";

    public static final String URL_GET_NAPTAP = "AplicacionMovil/GetListaNapTap";
    public static final String URL_ADD_NAPC = "AplicacionMovil/AddRelNapCoordenadas";
    public static final String URL_ADD_TAPC = "AplicacionMovil/AddRelTapCoordenadas";

    public static final String URL_GET_SERVICIOSCAMDO = "AplicacionMovil/GetServicios";

    public static final String URL_GET_DATOS_CLIENTE  = "CLIENTES_New/GetConsultaClientesList";

    public static final String URL_GET_PLACA = "OrdSer/GetConRelCtePlacabyContrato";
    public static final String URL_ADD_PLACA = "OrdSer/GetInsertRelCtePlacabyContrato";

    //Hotel

   /* public static final String URL_ADD_CUADRILLA = "AplicacionMovil/InsertRelTecnicoCuadrilla";
    public static final String URL_GET_CUADRILLA  = "AplicacionMovil/SelectRelTecnicoCuadrilla";
    public static final String URL_GET_DATOS_CLIENTE  = "CLIENTES_New/GetConsultaClientesList";*/

    public static final String URL_GET_DATOS_CALLES  = "CLIENTES_New/GetSoftvWEb_DameEntrecalles";
    public static final String URL_PERMISOS = "AplicacionMovil/ConsultaPermisos";
    public static final String URL_COD_REG = "AplicacionMovil/GetCodigoRegistro";
    public static final String URL_VCOD_REG = "AplicacionMovil/ValidarCodigoRegistro";
    public static final String URL_SCOD_REG = "AplicacionMovil/SetCodigoRegistro"; 

    public static final String URL_CL_SL = "AplicacionMovil/DatosClientesSaldo";
    public static final String URL_LCL_SL = "AplicacionMovil/DatosClientesSaldoList";
    public static final String URL_CL_SR = "AplicacionMovil/ClienteServiciosSaldo";
    public static final String URL_CL_DT = "AplicacionMovil/ClienteCobroClienteSaldo";
    public static final String URL_CL_DTS = "AplicacionMovil/ClienteCobroClienteSaldoSession";
    public static final String URL_GR_PG = "AplicacionMovil/GuardaPagoMOVIL";
    public static final String URL_DIR = "AplicacionMovil/DireccionesCAMDO";
    public static final String URL_GT_TK = "CrearTicketTable/GetTicket";
    public static final String URL_VEN_REN = "AplicacionMovil/VentaRenta";
    public static final String URL_REPORTES = URL + "Reportes/";


}