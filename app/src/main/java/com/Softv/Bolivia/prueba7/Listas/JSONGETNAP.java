package com.Softv.Bolivia.prueba7.Listas;

import com.Softv.Bolivia.prueba7.Modelos.ObtieneNapModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONGETNAP {
    @SerializedName("GetObtieneNapResult")
    @Expose
    private List<ObtieneNapModel> ObtieneNapModel = null;

    public List<ObtieneNapModel> obtieneNapModel() {
        return ObtieneNapModel;
    }
}
