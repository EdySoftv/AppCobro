package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMuestraServiciosRelTipoAparatoListResult {
    @SerializedName("BaseIdUser")
    @Expose
    public Integer baseIdUser;
    @SerializedName("Clv_UnicaNet")
    @Expose
    public Integer clv_UnicaNet;
    @SerializedName("nombre")
    @Expose
    public String nombre;

    public void setBaseIdUser(Integer baseIdUser) {
        this.baseIdUser = baseIdUser;
    }

    public Integer getBaseIdUser() {
        return baseIdUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
