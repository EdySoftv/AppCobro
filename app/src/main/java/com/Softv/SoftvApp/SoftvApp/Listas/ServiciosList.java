package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.ModelServiciosList;
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