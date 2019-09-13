package com.Softv.Bolivia.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequierePregunta {
    @SerializedName("RequierePregunta")
    @Expose
    public boolean RequierePregunta;


    public boolean getRequierePregunta() {
        return RequierePregunta;
    }


}
