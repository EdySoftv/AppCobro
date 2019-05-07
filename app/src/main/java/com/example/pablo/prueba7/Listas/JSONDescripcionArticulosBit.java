package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.DescripcionArticuloModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONDescripcionArticulosBit {
    @SerializedName("GetMuestra_Descripcion_Articulo_2ListResult")
    @Expose
    private List<DescripcionArticuloModel> DescripcionArticuloModel = null;

    public List<DescripcionArticuloModel> descripcionArticuloModel() {
        return DescripcionArticuloModel;
    }

}
