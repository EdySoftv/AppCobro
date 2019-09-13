package com.Softv.Bolivia.prueba7.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Softv.Bolivia.prueba7.Modelos.GetMuestraMedioPorServicoContratadoListResult;

import java.util.List;

public class JSONMediosSer {
    @SerializedName("GetMuestraMedioPorServicoContratadoListResult")
    @Expose
    private List<GetMuestraMedioPorServicoContratadoListResult> GetMuestraMedioPorServicoContratadoListResult = null;

    public List<GetMuestraMedioPorServicoContratadoListResult> GetMuestraMedioPorServicoContratadoListResult() {
        return GetMuestraMedioPorServicoContratadoListResult;
    }
}
