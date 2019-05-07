package com.example.pablo.prueba7.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.pablo.prueba7.Modelos.GetSP_StatusAparatosListResult;

import java.util.List;

public class JSONStatusApa {
    @SerializedName("GetSP_StatusAparatosListResult")
    @Expose
    private List<GetSP_StatusAparatosListResult> GetSP_StatusAparatosListResult = null;

    public List<GetSP_StatusAparatosListResult> GetSP_StatusAparatosListResult() {
        return GetSP_StatusAparatosListResult;
    }
}
