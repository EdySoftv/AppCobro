package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.ObtieneNapModel;
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
