package com.example.pablo.prueba7.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.pablo.prueba7.Modelos.GetMuestraServiciosRelTipoAparatoListResult;

import java.util.List;

public class JSONServiciosAparatos {
    @SerializedName("GetMuestraServiciosRelTipoAparatoListResult")
    @Expose
    private List<GetMuestraServiciosRelTipoAparatoListResult> GetMuestraServiciosRelTipoAparatoListResult = null;

    public List<GetMuestraServiciosRelTipoAparatoListResult> GetMuestraServiciosRelTipoAparatoListResult() {
        return GetMuestraServiciosRelTipoAparatoListResult;
    }
}
