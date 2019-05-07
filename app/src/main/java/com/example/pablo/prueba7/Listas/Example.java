package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetDameOrdenesQuejasTotalesResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {
    //Lista donde se guarla la informacion que regresa el servidor

    @SerializedName("GetDameOrdenesQuejasTotalesResult")
    @Expose
    public GetDameOrdenesQuejasTotalesResult getDameOrdenesQuejasTotalesResult;
}