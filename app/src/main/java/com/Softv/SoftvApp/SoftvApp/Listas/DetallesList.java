package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.ModelDetallesList;
import com.Softv.SoftvApp.SoftvApp.Modelos.ModelServiciosList;
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