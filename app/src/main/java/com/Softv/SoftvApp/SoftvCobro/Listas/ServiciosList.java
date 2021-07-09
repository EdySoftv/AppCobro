package com.Softv.SoftvApp.SoftvCobro.Listas;

import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelServiciosList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ServiciosList {

    @SerializedName("GetClienteServiciosResult")
    @Expose
    private List<ModelServiciosList> ListadoServiciosAgendadas = null;

    public List<ModelServiciosList> GetClienteServiciosResult() {
        return ListadoServiciosAgendadas;
    }
}