package com.Softv.SoftvApp.SoftvApp.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraServiciosRelTipoAparatoListResult;

import java.util.List;

public class JSONServiciosAparatos {
    @SerializedName("GetMuestraServiciosRelTipoAparatoListResult")
    @Expose
    private List<GetMuestraServiciosRelTipoAparatoListResult> GetMuestraServiciosRelTipoAparatoListResult = null;

    public List<GetMuestraServiciosRelTipoAparatoListResult> GetMuestraServiciosRelTipoAparatoListResult() {
        return GetMuestraServiciosRelTipoAparatoListResult;
    }
}
