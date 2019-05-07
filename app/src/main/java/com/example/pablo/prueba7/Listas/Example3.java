package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetBUSCADetOrdSerListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example3 {
    //Lista donde se guarla la informacion que regresa el servidor
    @SerializedName("GetBUSCADetOrdSerListResult")
    @Expose
    private List<GetBUSCADetOrdSerListResult> getBUSCADetOrdSerListResult = null;

    public List<GetBUSCADetOrdSerListResult> getGetBUSCADetOrdSerListResult() {
        return getBUSCADetOrdSerListResult;
    }
}