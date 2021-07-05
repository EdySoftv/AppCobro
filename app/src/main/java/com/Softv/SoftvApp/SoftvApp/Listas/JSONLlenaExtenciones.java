package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.LlenaExtencionesModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONLlenaExtenciones {
    @SerializedName("GetUspLlenaComboExtensionesListResult")
    @Expose
    private List<LlenaExtencionesModel> LlenaExtencionesModel = null;

    public List<LlenaExtencionesModel> llenaExtencionesModel() {
        return LlenaExtencionesModel;
    }
}
