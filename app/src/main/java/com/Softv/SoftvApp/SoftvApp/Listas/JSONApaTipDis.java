package com.Softv.SoftvApp.SoftvApp.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListAparatosDisponiblesByIdArticuloResult;
import java.util.List;

public class JSONApaTipDis {
    @SerializedName("GetListAparatosDisponiblesByIdArticuloResult")
    @Expose
    private List<GetListAparatosDisponiblesByIdArticuloResult> GetListAparatosDisponiblesByIdArticuloResult = null;

    public List<GetListAparatosDisponiblesByIdArticuloResult> GetListAparatosDisponiblesByIdArticuloResult() {
        return GetListAparatosDisponiblesByIdArticuloResult;
    }

}
   