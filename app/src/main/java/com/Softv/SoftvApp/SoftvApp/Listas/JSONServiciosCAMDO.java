package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetColoniaResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetServiciosResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONServiciosCAMDO {
    @SerializedName("GetServiciosResult")
    @Expose
    private List<com.Softv.SoftvApp.SoftvApp.Modelos.GetServiciosResult> GetServiciosResult = null;

    public List<com.Softv.SoftvApp.SoftvApp.Modelos.GetServiciosResult> getServiciosResult() {
        return GetServiciosResult;
    }
}
