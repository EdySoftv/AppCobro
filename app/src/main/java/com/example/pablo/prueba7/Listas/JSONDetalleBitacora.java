package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.DetalleBitacoraModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONDetalleBitacora {
    @SerializedName("GetMuestra_Detalle_Bitacora_2ListResult")
    @Expose
    private List<DetalleBitacoraModel> DetalleBitacoraModel = null;

    public List<DetalleBitacoraModel> detalleBitacoraModel() {
        return DetalleBitacoraModel;
    }

}
