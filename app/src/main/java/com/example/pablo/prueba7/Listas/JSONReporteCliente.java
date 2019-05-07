package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetQuejasListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONReporteCliente {
    @SerializedName("GetQuejasListResult")
    @Expose
    private List<GetQuejasListResult> getQuejasListResult = null;

    public List<GetQuejasListResult> getGetQuejasListResult() {
        return getQuejasListResult;
    }
}