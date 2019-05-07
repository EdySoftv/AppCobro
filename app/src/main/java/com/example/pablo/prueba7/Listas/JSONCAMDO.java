package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetDameDatosCAMDOResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONCAMDO {
    @SerializedName("GetDameDatosCAMDOResult")
    @Expose
    private List<GetDameDatosCAMDOResult> GetDameDatosCAMDOResult = null;

    public List<GetDameDatosCAMDOResult> getDameDatosCAMDOResult() {
        return GetDameDatosCAMDOResult;
    }
}
