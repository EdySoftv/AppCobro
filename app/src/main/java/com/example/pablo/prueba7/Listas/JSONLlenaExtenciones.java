package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.LlenaExtencionesModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONLlenaExtenciones {
    @SerializedName("GetUspLlenaComboExtensionesListResult")
    @Expose
    private List<LlenaExtencionesModel> LlenaExtencionesModel = null;

    public List<LlenaExtencionesModel> llenaExtencionesModel() {
        return LlenaExtencionesModel;
    }
}
