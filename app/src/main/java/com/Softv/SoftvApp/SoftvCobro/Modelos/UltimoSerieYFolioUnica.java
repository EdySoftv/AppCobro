package com.Softv.SoftvApp.SoftvCobro.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UltimoSerieYFolioUnica {
    @SerializedName("Serie")
    @Expose
    private String Serie;
    @SerializedName("UltimoFolioUsado")
    @Expose
    private Integer UltimoFolioUsado;

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        Serie = Serie;
    }

    public Integer getUltimoFolioUsado() {
        return UltimoFolioUsado;
    }

    public void setUltimoFolioUsado(Integer UltimoFolioUsado) {
        UltimoFolioUsado = UltimoFolioUsado;
    }
}
