package com.Softv.SoftvApp.SoftvApp.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetServiciosResult {
    @SerializedName("Contrato")
    @Expose
    private Integer Contrato;
    @SerializedName("IdMedio")
    @Expose
    private Integer IdMedio;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getContrato() {
        return Contrato;
    }

    public Integer getIdMedio() {
        return IdMedio;
    }

    public String getStatus() {
        return status;
    }
}
