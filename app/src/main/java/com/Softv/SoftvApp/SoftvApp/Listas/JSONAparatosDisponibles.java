package com.Softv.SoftvApp.SoftvApp.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraAparatosDisponiblesListResult;

import java.util.List;

public class JSONAparatosDisponibles {
    @SerializedName("GetMuestraAparatosDisponiblesListResult")
    @Expose
    private List<GetMuestraAparatosDisponiblesListResult> GetMuestraAparatosDisponiblesListResult = null;

    public List<GetMuestraAparatosDisponiblesListResult> GetMuestraAparatosDisponiblesListResult() {
        return GetMuestraAparatosDisponiblesListResult;
    }

}
