package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetDameSerDelCliFacListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONServicioAsignado {
    @SerializedName("GetDameSerDelCliFacListResult")
    @Expose
    private List<GetDameSerDelCliFacListResult> getDameSerDelCliFacListResult = null;

    public List<GetDameSerDelCliFacListResult> getGetDameSerDelCliFacListResult() {
        return getDameSerDelCliFacListResult;
    }
}
