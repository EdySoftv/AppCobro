package com.Softv.SoftvApp.SoftvCobro.Listas;

import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelDetallesList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelHistorialDePagoList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HistorialDePagoList {

    @SerializedName("GetHistorialDePagoResult")
    @Expose
    private List<ModelHistorialDePagoList> ListadoDePagos = null;

    public List<ModelHistorialDePagoList> GetHistorialDePagoResult() {
        return ListadoDePagos;
    }
}