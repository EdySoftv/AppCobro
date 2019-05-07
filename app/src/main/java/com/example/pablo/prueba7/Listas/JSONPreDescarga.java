package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.dameTblPreDescargaMaterialResultModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONPreDescarga {
    @SerializedName("dameTblPreDescargaMaterialResult")
    @Expose
    private List<dameTblPreDescargaMaterialResultModel> getdameTblPreDescargaMaterialResultModel = null;

    public List<dameTblPreDescargaMaterialResultModel> getdameTblPreDescargaMaterialResultModel() {
        return getdameTblPreDescargaMaterialResultModel;
    }
}