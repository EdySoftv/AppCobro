
package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetDameListadoOrdenesAgendadasResult {


    @SerializedName("Clv_Orden")
    @Expose
    private Integer clvOrden;
    @SerializedName("Contrato")
    @Expose
    public String contrato;
    @SerializedName("NOMBRE")
    @Expose
    private String nombre;
    @SerializedName("Status")
    @Expose
    private String status;
/////////////////////////////////
    @SerializedName("Calle")
    @Expose
    private String calle;

    @SerializedName("NUMERO")
    @Expose
    private String NUMERO;

    @SerializedName("Colonia")
    @Expose
    private String colonia;




    public Integer getClvOrden() {
        return clvOrden;
    }
    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumero() {
        return NUMERO;
    }

    public void setNumero(String numero) {
        this.NUMERO = numero;
    }
}
