package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.DetalleBitacoraModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONDetalleBitacora {
    @SerializedName("Muestra_Detalle_Bitacora_2_esfibraListResult")
    @Expose
    private List<DetalleBitacoraModel> DetalleBitacoraModel = null;

    public List<DetalleBitacoraModel> detalleBitacoraModel() {
        return DetalleBitacoraModel;
    }

}
