package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetGetDescargaMaterialArticulosByIdClvOrdenListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONDESCARGADIRECTA {
    @SerializedName("GetGetDescargaMaterialArticulosByIdClvOrdenListResult")
    @Expose
    private List<com.Softv.SoftvApp.SoftvApp.Modelos.GetGetDescargaMaterialArticulosByIdClvOrdenListResult> GetGetDescargaMaterialArticulosByIdClvOrdenListResult = null;

    public List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult> GetGetDescargaMaterialArticulosByIdClvOrdenListResult() {
        return GetGetDescargaMaterialArticulosByIdClvOrdenListResult;
    }
}
