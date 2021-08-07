 package com.Softv.SoftvApp.SoftvCobro.sampledata;

public class Constants {
    //////////NUEVA IP PUBLICA 187.190.125.85

    //public static final String URL="http://192.168.0.4:1160/";

    //public static final String URL="http://915009d10274.sn.mynetname.net:1160/";
    public static final String URL="http://45.5.94.66:9600/"; //Eii

    public static final String NEW_URL = URL + "SoftvWCFService.svc/";
    public static final String URL_GET_USER = "Usuario/LogOn";
    public static final String URL_GET_TECNICO = "AplicacionMovil/Get_ClvCajero";

    public static final String URL_ADD_DESCARGA_DIRECTA = "TblDescargaMaterial/GetAddDescargaMaterialArt";
    public static final String URL_GET_DESCARGA_DIRECTA = "GetDescargaMaterialArticulosByIdClvOrden/GetGetDescargaMaterialArticulosByIdClvOrdenList";
    public static final String URL_GET_PERMISO_DIRECTA = "TblDescargaMaterial/Muestra_TecnicosDescargaMaterial";


    public static final String URL_VCOD_REG = "AplicacionMovil/ValidarCodigoRegistro";
    public static final String URL_SCOD_REG = "AplicacionMovil/SetCodigoRegistro"; 

    public static final String URL_LCL_SL = "AplicacionMovil/DatosClientesSaldoList";
    public static final String URL_CL_SR = "AplicacionMovil/ClienteServiciosSaldo";
    public static final String URL_CL_DT = "AplicacionMovil/ClienteCobroClienteSaldo";
    public static final String URL_CL_DTS = "AplicacionMovil/ClienteCobroClienteSaldoSession";
    public static final String URL_GR_PG = "AplicacionMovil/GuardaPagoMOVIL";
    public static final String URL_DIR = "AplicacionMovil/DireccionesCAMDO";
    public static final String URL_GT_TK = "CrearTicketTable/GetTicket";
    public static final String URL_VEN_REN = "AplicacionMovil/VentaRenta";
    public static final String FORMATO = "$0.00";
    public static final String URL_REPORTES = URL + "Reportes/";

}