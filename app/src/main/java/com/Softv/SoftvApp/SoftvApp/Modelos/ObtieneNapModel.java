package com.Softv.SoftvApp.SoftvApp.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObtieneNapModel {
    @SerializedName("Clavetecnica")
    @Expose
    public String Clavetecnica;
    @SerializedName("IdTap")
    @Expose
    public Integer IdTap;

    public String getClavetecnica() {
        return Clavetecnica;
    }

    public Integer getIdTap() {
        return IdTap;
    }
}
