package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class mediosPregunta {
    @SerializedName("Descripcion")
    @Expose
    private String Descripcion;
    @SerializedName("IdMedio")
    @Expose
    private Integer IdMedio;
    @SerializedName("RequiereAcometida")
    @Expose
    public boolean RequiereAcometida;


    public boolean getRequiereAcometida() {
        return RequiereAcometida;
    }

    public String getDescripcion() {
        return Descripcion;
    }


    public Integer getIdMedio() {
        return IdMedio;
    }
}
