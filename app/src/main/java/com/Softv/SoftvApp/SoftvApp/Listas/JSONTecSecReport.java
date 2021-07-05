package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraTecnicosAlmacenListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONTecSecReport {
    @SerializedName("GetMuestra_Tecnicos_AlmacenListResult")
    @Expose
    private List<GetMuestraTecnicosAlmacenListResult> getMuestraTecnicosAlmacenListResult = null;

    public List<GetMuestraTecnicosAlmacenListResult> getGetMuestraTecnicosAlmacenListResult() {
        return getMuestraTecnicosAlmacenListResult;
    }
}

