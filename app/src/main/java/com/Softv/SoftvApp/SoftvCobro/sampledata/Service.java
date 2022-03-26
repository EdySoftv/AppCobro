package com.Softv.SoftvApp.SoftvCobro.sampledata;


import com.Softv.SoftvApp.SoftvCobro.Listas.DetallesList;
import com.Softv.SoftvApp.SoftvCobro.Listas.HistorialDePagoList;
import com.Softv.SoftvApp.SoftvCobro.Listas.JSONPERMISOSDIRECTA;
import com.Softv.SoftvApp.SoftvCobro.Listas.JSONResponseTecnico;
import com.Softv.SoftvApp.SoftvCobro.Listas.JSONVendedores;
import com.Softv.SoftvApp.SoftvCobro.Listas.ListaClientesSaldos;
import com.Softv.SoftvApp.SoftvCobro.Listas.ServiciosList;
import com.Softv.SoftvApp.SoftvCobro.Listas.UltimoSerieYFolio;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface Service {

    @GET(Constants.URL_GET_USER)
    Call<JsonObject> getDataUser();

    @POST(Constants.URL_GET_TECNICO)
    Call<JSONResponseTecnico> getDataTec();

    @POST(Constants.URL_GET_PERMISO_DIRECTA)
    Call<JSONPERMISOSDIRECTA> getPermisosDirecta();


    @POST(Constants.URL_LCL_SL)
    Call<ListaClientesSaldos> getDataListClSal();

    @POST(Constants.URL_CL_SR)
    Call<ServiciosList> getDataServiciosSaldos();

    @POST(Constants.URL_CL_DT)
    Call<DetallesList> getDataDetallesSaldos();

    @POST(Constants.URL_CL_DTS)
    Call<DetallesList> getDataDetallesSaldosSession();

    @POST(Constants.URL_HI_PA)
    Call<HistorialDePagoList> getHistorialDePago();

    @POST(Constants.URL_GR_PG)
    Call<JsonObject> GuardarPago();

    @POST(Constants.URL_GR_BN)
    Call<JsonObject> GuardarAbono();

    @POST(Constants.URL_GT_TK)
    Call<JsonObject> GetTicketNombre();

    @POST(Constants.URL_VEN_REN)
    Call<JsonObject> GetVENTARENTA();

    @POST(Constants.URL_DIR)
    Call<JsonObject> GetDireccionesCAMDO();

    @POST(Constants.URL_OVT)
    Call<JsonObject> VendedorTecnico();

    @POST(Constants.URL_DSVA)
    Call<JsonObject> ServicioVentas();

    @POST(Constants.URL_MV)
    Call<JSONVendedores> Vendedores();

    @POST(Constants.URL_USYF)
    Call<UltimoSerieYFolio> SerieYFolio();

    @POST(Constants.URL_FDR)
    Call<JsonObject> FolioDisponibleR();

    @POST(Constants.URL_FD)
    Call<JsonObject> FolioDisponible();

    @POST(Constants.URL_FDI)
    Call<JsonObject> FolioDisponibleI();
}

