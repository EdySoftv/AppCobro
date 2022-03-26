package com.Softv.SoftvApp.SoftvCobro.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MuestraVendedoresResult {
    @SerializedName("Nombre")
    @Expose
    private String Nombre;
    @SerializedName("Clv_Vendedor")
    @Expose
    private Integer Clv_Vendedor;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Integer getClv_Vendedor() {
        return Clv_Vendedor;
    }

    public void setClv_Vendedor(Integer clv_Vendedor) {
        Clv_Vendedor = clv_Vendedor;
    }
}
