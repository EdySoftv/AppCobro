package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class children {
        @SerializedName("BaseIdUser")
        @Expose
        public Integer baseIdUser;
        @SerializedName("BaseRemoteIp")
        @Expose
        public Integer baseRemoteIp;
        @SerializedName("Clv_Aparato")
        @Expose
        public Integer Clv_Aparato;
        @SerializedName("Clv_UnicaNet")
        @Expose
        public Integer clv_UnicaNet;
        @SerializedName("ContratoNet")
        @Expose
        public Integer ContratoNet;
        @SerializedName("Detalle")
        @Expose
        public String Detalle;
        @SerializedName("Nombre")
        @Expose
        public String Nombre;
        @SerializedName("Tipo")
        @Expose
        public String Tipo;
        @SerializedName("Type")
        @Expose
        public String Type;

    public void setClv_Aparato(Integer clv_Aparato) {
        Clv_Aparato = clv_Aparato;
    }

    public void setContratoNet(Integer contratoNet) {
        ContratoNet = contratoNet;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setBaseIdUser(Integer baseIdUser) {
        this.baseIdUser = baseIdUser;
    }

    public void setBaseRemoteIp(Integer baseRemoteIp) {
        this.baseRemoteIp = baseRemoteIp;
    }

    public void setClv_UnicaNet(Integer clv_UnicaNet) {
        this.clv_UnicaNet = clv_UnicaNet;
    }

    public Integer getClv_Aparato() {
        return Clv_Aparato;
    }

    public Integer getContratoNet() {
        return ContratoNet;
    }

    public String getDetalle() {
        return Detalle;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getTipo() {
        return Tipo;
    }
    public Integer getBaseIdUser() {
        return baseIdUser;
    }

    public Integer getBaseRemoteIp() {
        return baseRemoteIp;
    }
}

