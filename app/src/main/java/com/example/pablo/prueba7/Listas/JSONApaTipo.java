package com.example.pablo.prueba7.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.pablo.prueba7.Modelos.GetListTipoAparatosByIdArticuloResult;

import java.util.List;

public class JSONApaTipo {
    @SerializedName("GetListTipoAparatosByIdArticuloResult")
    @Expose
    private List<GetListTipoAparatosByIdArticuloResult> GetListTipoAparatosByIdArticuloResult = null;

    public List<GetListTipoAparatosByIdArticuloResult> GetListTipoAparatosByIdArticuloResult() {
        return GetListTipoAparatosByIdArticuloResult;
    }
}
