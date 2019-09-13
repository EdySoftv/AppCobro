package com.Softv.SoftvApp.SoftvApp.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraMedioPorServicoContratadoListResult;

import java.util.List;

public class JSONMediosSer {
    @SerializedName("GetMuestraMedioPorServicoContratadoListResult")
    @Expose
    private List<GetMuestraMedioPorServicoContratadoListResult> GetMuestraMedioPorServicoContratadoListResult = null;

    public List<GetMuestraMedioPorServicoContratadoListResult> GetMuestraMedioPorServicoContratadoListResult() {
        return GetMuestraMedioPorServicoContratadoListResult;
    }
}
