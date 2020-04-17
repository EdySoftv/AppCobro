package com.Softv.SoftvApp.SoftvApp.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Muestra_TecnicosDescargaMaterialResult {
    @SerializedName("Existe")
    @Expose
    public int Existe;

    public int getExiste() {
        return Existe;
    }
}
