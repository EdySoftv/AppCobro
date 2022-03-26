package com.Softv.SoftvApp.SoftvCobro.Listas;

import com.Softv.SoftvApp.SoftvCobro.Modelos.MuestraVendedoresResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class JSONVendedores {
    @SerializedName("MuestraVendedoresResult")
    @Expose
    private List<MuestraVendedoresResult> MuestraVendedoresResult = null;

    public List<MuestraVendedoresResult> MuestraVendedoresResult() {
        return MuestraVendedoresResult;
    }
}