 package com.Softv.SoftvApp.SoftvCobro.sampledata;

public class Constants {
    //////////NUEVA IP PUBLICA 187.190.125.85

    //public static final String URL="http://192.168.0.4:1160/";
    //public static  final String URL="http://127.0.0.1:8080/";//LOCAL
    //public static  final String URL="http://915009d10274.sn.mynetname.net:2260/";//Cucuta
    //public static final String URL="http://186.96.50.182:4760/"; //Cable Exito
    //public static final String URL="http://915009d10274.sn.mynetname.net:420/"; //Cable Exito
    //public static final String URL="http://915009d10274.sn.mynetname.net:1230/"; //Cucuta
    //public static final String URL="http://915009d10274.sn.mynetname.net:5600/";
    //public static final String URL="http://45.5.94.66:9600/"; //Eii
    //public static final String URL="http://201.131.236.2:5600/"; //Fresnillo
    //public static final String URL = "http://192.168.0.11:9100/";
    //public static final String URL="http://915009d10274.sn.mynetname.net:2660/"; //Garden
    //public static final String URL="http://915009d10274.sn.mynetname.net:4760/"; //Cable Exito
    //public static final String URL="http://177.74.204.254:5788/"; //ALPAVISION SERVIDOR PRUEBAS
    //public static final String URL="http://189.206.35.69:2660/"; //GARDENTECH NUBE PRDO
    //public static final String URL="http://189.206.35.69:4760/"; //CABLEEXITO BARRANQUILLA NUBE PROD
    //public static final String URL="http://915009d10274.sn.mynetname.net:2460/"; //CABLEEXITO CUCUTA NUBE PROD
    //public static  final String URL="http://915009d10274.sn.mynetname.net:2460/";//CableCentro PRuebas 245
    //public static final String URL="http://190.115.203.244:5600/";//CableCentro PROD
    //public static final String URL="http://189.206.35.69:4760/"; //Cable Exito SERVIDOR DE LA NUBE
    //public static final String URL="http://915009d10274.sn.mynetname.net:520/"; //Cable Exito TEMPORAL
    //public static final String URL="http://189.206.35.69:2660/";
    public static final String URL="http://189.206.35.69:2660/"; //ROYALNET
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
    public static final String URL_GR_BN = "AplicacionMovil/GuardaAbonoMOVIL";
    public static final String URL_HI_PA = "AplicacionMovil/HistorialDePago";
    public static final String URL_DIR = "AplicacionMovil/DireccionesCAMDO";
    public static final String URL_GT_TK = "CrearTicketTable/GetTicket";
    public static final String URL_VEN_REN = "AplicacionMovil/VentaRenta";
    public static final String FORMATO = "$0.00";
    public static final String URL_REPORTES = URL + "Reportes/";

    public static final String URL_OVT = "AplicacionMovil/ObtieneVendedorTecnico";
    public static final String URL_DSVA = "AplicacionMovil/DameServicioVentasAPP";
    public static final String URL_MV = "AplicacionMovil/MuestraVendedores";
    public static final String URL_USYF = "AplicacionMovil/UltimoSerieYFolio";
    public static final String URL_FDR = "AplicacionMovil/FolioDisponibleRecu";
    public static final String URL_FD = "AplicacionMovil/FolioDisponible";
    public static final String URL_FDI = "AplicacionMovil/FolioDisponibleInt";

}