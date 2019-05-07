package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMuestraMedioPorServicoContratadoListResult {
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("IdMedio")
    @Expose
    public Integer idMedio;
    @SerializedName("RequiereAcometida")
    @Expose
    private boolean requiereAcometida;

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getIdMedio() {
        return idMedio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
