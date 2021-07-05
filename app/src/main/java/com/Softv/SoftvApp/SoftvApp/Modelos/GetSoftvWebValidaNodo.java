package com.Softv.SoftvApp.SoftvApp.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSoftvWebValidaNodo {
    @SerializedName("idMedio")
    @Expose
    public int idMedio;
    @SerializedName("Clv_TipSer")
    @Expose
    public int Clv_TipSer;

    public int getIdMedio() {
        return idMedio;
    }

    public int getClv_TipSer() {
        return Clv_TipSer;
    }
}
