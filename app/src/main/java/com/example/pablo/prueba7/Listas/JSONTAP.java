package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.ObtieneNapModel;
import com.example.pablo.prueba7.Modelos.ObtieneTapModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONTAP {
    @SerializedName("GetObtieneTapResult")
    @Expose
    private List<ObtieneTapModel> ObtieneTapModel = null;

    public List<ObtieneTapModel> obtieneTapModel() {
        return ObtieneTapModel;
    }
}
