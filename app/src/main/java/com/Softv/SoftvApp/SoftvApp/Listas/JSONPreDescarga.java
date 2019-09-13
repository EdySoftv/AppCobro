package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.dameTblPreDescargaMaterialResultModel;
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