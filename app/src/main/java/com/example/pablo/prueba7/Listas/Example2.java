package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetdameSerDELCliresumenResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example2 {
    //Lista donde se guarla la informacion que regresa el servidor
    @SerializedName("GetdameSerDELCliresumenResult")
    @Expose
    private List<GetdameSerDELCliresumenResult> getdameSerDELCliresumenResult = null;

    public List<GetdameSerDELCliresumenResult> getdameSerDELCliresumenResult() {
        return getdameSerDELCliresumenResult;
    }
}
