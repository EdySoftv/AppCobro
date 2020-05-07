package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetColoniaResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONColonia {
    @SerializedName("GetColoniaResult")
    @Expose
    private List<com.Softv.SoftvApp.SoftvApp.Modelos.GetColoniaResult> GetColoniaResult = null;

    public List<GetColoniaResult> getColoniaResult() {
        return GetColoniaResult;
    }
}
