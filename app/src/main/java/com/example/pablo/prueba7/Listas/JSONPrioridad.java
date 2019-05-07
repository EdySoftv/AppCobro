package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetSoftvGetPrioridadQuejaListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONPrioridad {
    @SerializedName("GetSoftv_GetPrioridadQuejaListResult")
    @Expose
    private List<GetSoftvGetPrioridadQuejaListResult> getSoftvGetPrioridadQuejaListResult = null;

    public List<GetSoftvGetPrioridadQuejaListResult> getGetSoftvGetPrioridadQuejaListResult() {
        return getSoftvGetPrioridadQuejaListResult;
    }

}




