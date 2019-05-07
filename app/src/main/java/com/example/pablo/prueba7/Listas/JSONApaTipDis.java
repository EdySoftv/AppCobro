package com.example.pablo.prueba7.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.pablo.prueba7.Modelos.GetListAparatosDisponiblesByIdArticuloResult;
import java.util.List;

public class JSONApaTipDis {
    @SerializedName("GetListAparatosDisponiblesByIdArticuloResult")
    @Expose
    private List<GetListAparatosDisponiblesByIdArticuloResult> GetListAparatosDisponiblesByIdArticuloResult = null;

    public List<GetListAparatosDisponiblesByIdArticuloResult> GetListAparatosDisponiblesByIdArticuloResult() {
        return GetListAparatosDisponiblesByIdArticuloResult;
    }

}
   