package com.Softv.SoftvApp.SoftvApp.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;

import java.util.List;

public class JSONArbolServicios {
    @SerializedName("GetMuestraArbolServiciosAparatosPorinstalarListResult")
    @Expose
    public List<GetMuestraArbolServiciosAparatosPorinstalarListResult> getMuestraArbolServiciosAparatosPorinstalarListResult;

    public List<GetMuestraArbolServiciosAparatosPorinstalarListResult> GetMuestraArbolServiciosAparatosPorinstalarListResult() {
        return getMuestraArbolServiciosAparatosPorinstalarListResult;
    }
}
