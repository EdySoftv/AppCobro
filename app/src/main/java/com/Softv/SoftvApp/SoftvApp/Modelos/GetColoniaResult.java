package com.Softv.SoftvApp.SoftvApp.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetColoniaResult {
    @SerializedName("Nombre")
    @Expose
    private String Nombre;
    @SerializedName("clv_colonia")
    @Expose
    private Integer Clv_Colonia;

    public String getNombre() {
        return Nombre;
    }

    public Integer getClv_Colonia() {
        return Clv_Colonia;
    }
}
