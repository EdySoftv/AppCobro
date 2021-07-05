package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetConRelCtePlacabyContrato;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONPlaca {
    @SerializedName("GetConRelCtePlacabyContrato")
    @Expose
    private GetConRelCtePlacabyContrato getConRelCtePlacabyContrato;
    public GetConRelCtePlacabyContrato getConRelCtePlacabyContrato() {
        return getConRelCtePlacabyContrato;
    }
}
