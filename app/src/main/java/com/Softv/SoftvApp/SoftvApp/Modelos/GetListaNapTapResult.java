package com.Softv.SoftvApp.SoftvApp.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetListaNapTapResult {
    @SerializedName("NapTap")
    @Expose
    private String NapTap;
    @SerializedName("Poste")
    @Expose
    private String Poste;
    @SerializedName("IdNapTap")
    @Expose
    private Integer IdNapTap;

    public String getNapTap() {
        return NapTap;
    }

    public String getPoste() {
        return Poste;
    }

    public Integer getIdNapTap() {
        return IdNapTap;
    }
}
