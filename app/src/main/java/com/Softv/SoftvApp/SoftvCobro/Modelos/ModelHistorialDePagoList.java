package com.Softv.SoftvApp.SoftvCobro.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelHistorialDePagoList {
    @SerializedName("Contrato")
    @Expose
    private String Contrato;

    @SerializedName("HoraConsulta")
    @Expose
    private String HoraConsulta;

    @SerializedName("Monto")
    @Expose
    private float Monto;

    public String getContrato() {
        return Contrato;
    }

    public void setContrato(String contrato) {
        Contrato = contrato;
    }

    public String getHoraConsulta() {
        return HoraConsulta;
    }

    public void setHoraConsulta(String horaConsulta) {
        HoraConsulta = horaConsulta;
    }

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float monto) {
        Monto = monto;
    }
}

