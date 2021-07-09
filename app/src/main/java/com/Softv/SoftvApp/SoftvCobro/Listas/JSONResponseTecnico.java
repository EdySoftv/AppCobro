package com.Softv.SoftvApp.SoftvCobro.Listas;

import com.Softv.SoftvApp.SoftvCobro.Modelos.Get_ClvCajeroResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONResponseTecnico {
    //Lista donde se guarla la informacion que regresa el servidor
    @SerializedName("Get_ClvCajeroResult")
    @Expose
    private List<Get_ClvCajeroResult> Get_ClvCajeroResult = null;

    public List<Get_ClvCajeroResult> Get_ClvCajeroResult() {
        return Get_ClvCajeroResult;
    }
}
