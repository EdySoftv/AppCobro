package com.Softv.SoftvApp.SoftvCobro.Listas;

import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelDetallesList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DetallesList {

    @SerializedName("GetClienteCobroClienteSessionResult")
    @Expose
    private List<ModelDetallesList> ListadoDetallesAgendadas = null;

    public List<ModelDetallesList> GetClienteCobroClienteSessionResult() {
        return ListadoDetallesAgendadas;
    }
}