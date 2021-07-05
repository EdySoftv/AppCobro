package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetGetDescargaMaterialArticulosByIdClvOrdenListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.SelectRelTecnicoCuadrillaResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONCUADRILLA {
    @SerializedName("SelectRelTecnicoCuadrillaResult")
    @Expose
    private List<com.Softv.SoftvApp.SoftvApp.Modelos.SelectRelTecnicoCuadrillaResult> SelectRelTecnicoCuadrillaResult = null;

    public List<com.Softv.SoftvApp.SoftvApp.Modelos.SelectRelTecnicoCuadrillaResult> SelectRelTecnicoCuadrillaResult() {
        return SelectRelTecnicoCuadrillaResult;
    }
}
