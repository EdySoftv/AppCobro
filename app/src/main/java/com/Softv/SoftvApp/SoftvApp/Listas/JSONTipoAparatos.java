package com.Softv.SoftvApp.SoftvApp.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraTipoAparatoListResult;

import java.util.List;

public class JSONTipoAparatos {
    //Lista donde se guarla la informacion que regresa el servidor
    @SerializedName("GetMuestraTipoAparatoListResult")
    @Expose
    private List<GetMuestraTipoAparatoListResult> GetMuestraTipoAparatoListResult = null;

    public List<GetMuestraTipoAparatoListResult> GetMuestraTipoAparatoListResult() {
        return GetMuestraTipoAparatoListResult;
    }
}
