package com.Softv.SoftvApp.SoftvApp.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListTipoAparatosByIdArticuloResult;

import java.util.List;

public class JSONApaTipo {
    @SerializedName("GetListTipoAparatosByIdArticuloResult")
    @Expose
    private List<GetListTipoAparatosByIdArticuloResult> GetListTipoAparatosByIdArticuloResult = null;

    public List<GetListTipoAparatosByIdArticuloResult> GetListTipoAparatosByIdArticuloResult() {
        return GetListTipoAparatosByIdArticuloResult;
    }
}
