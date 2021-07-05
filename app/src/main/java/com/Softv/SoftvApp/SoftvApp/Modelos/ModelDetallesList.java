package com.Softv.SoftvApp.SoftvApp.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDetallesList {
    @SerializedName("Descripcion")
    @Expose
    private String Descripcion;

    @SerializedName("FechaConsulta")
    @Expose
    private String FechaConsulta;

    @SerializedName("Monto")
    @Expose
    private float Monto;

    @SerializedName("Operacion")
    @Expose
    private String Operacion;

    @SerializedName("Session")
    @Expose
    private Integer Session;

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFechaConsulta() {
        return FechaConsulta;
    }

    public void setFechaConsulta(String fechaConsulta) {
        FechaConsulta = fechaConsulta;
    }

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float monto) {
        Monto = monto;
    }

    public String getOperacion() {
        return Operacion;
    }

    public void setOperacion(String operacion) {
        Operacion = operacion;
    }

    public Integer getSession() {
        return Session;
    }

    public void setSession(Integer session) {
        Session = session;
    }
}
