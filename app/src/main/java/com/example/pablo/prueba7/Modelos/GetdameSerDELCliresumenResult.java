package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetdameSerDELCliresumenResult {

    @SerializedName("Resumen")
    @Expose
    private String resumen;

    public String getResumen() {
        return resumen;
    }
}
