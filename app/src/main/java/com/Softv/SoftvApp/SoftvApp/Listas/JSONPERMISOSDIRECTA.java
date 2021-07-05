package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.Muestra_TecnicosDescargaMaterialResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONPERMISOSDIRECTA {

    @SerializedName("Muestra_TecnicosDescargaMaterialResult")
    @Expose
    private List<Muestra_TecnicosDescargaMaterialResult> Muestra_TecnicosDescargaMaterialResult = null;

    public List<Muestra_TecnicosDescargaMaterialResult> Muestra_TecnicosDescargaMaterialResult() {
        return Muestra_TecnicosDescargaMaterialResult;
    }
}
