package com.Softv.SoftvApp.SoftvApp.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDamePreguntaMedioPorOrdenResult {
    @SerializedName("RequierePregunta")
    @Expose
    private List<RequierePregunta> RequierePregunta;
    @SerializedName("mediosPregunta")
    @Expose
    private List<mediosPregunta> mediosPregunta;

    public List<RequierePregunta> getRequierePregunta() {
        return RequierePregunta;
    }

    public List<mediosPregunta> getmediosPregunta() {
        return mediosPregunta;
    }
}
