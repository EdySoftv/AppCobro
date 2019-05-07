package com.example.pablo.prueba7.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;

import java.util.List;

public class JSONArbolServicios {
    @SerializedName("GetMuestraArbolServiciosAparatosPorinstalarListResult")
    @Expose
    public List<GetMuestraArbolServiciosAparatosPorinstalarListResult> getMuestraArbolServiciosAparatosPorinstalarListResult;

    public List<GetMuestraArbolServiciosAparatosPorinstalarListResult> GetMuestraArbolServiciosAparatosPorinstalarListResult() {
        return getMuestraArbolServiciosAparatosPorinstalarListResult;
    }
}
