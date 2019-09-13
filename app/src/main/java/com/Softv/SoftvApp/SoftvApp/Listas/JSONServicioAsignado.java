package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetDameSerDelCliFacListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONServicioAsignado {
    @SerializedName("GetDameSerDelCliFacListResult")
    @Expose
    private List<GetDameSerDelCliFacListResult> getDameSerDelCliFacListResult = null;

    public List<GetDameSerDelCliFacListResult> getGetDameSerDelCliFacListResult() {
        return getDameSerDelCliFacListResult;
    }
}
