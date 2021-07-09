package com.Softv.SoftvApp.SoftvCobro.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelServiciosList {
    @SerializedName("Servicio")
    @Expose
    private String Servicio;

    @SerializedName("Status")
    @Expose
    private String Status;

    @SerializedName("TipServ")
    @Expose
    private String TipServ;

    public String getServicio() {
        return Servicio;
    }

    public void setServicio(String servicio) {
        Servicio = servicio;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTipServ() {
        return TipServ;
    }

    public void setTipServ(String tipServ) {
        TipServ = tipServ;
    }
}

