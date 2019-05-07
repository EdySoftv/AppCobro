package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSoftvGetPrioridadQuejaListResult {
    @SerializedName("BaseIdUser")
    @Expose
    private Long baseIdUser;
    @SerializedName("BaseRemoteIp")
    @Expose
    private Object baseRemoteIp;
    @SerializedName("Descripcion")
    @Expose
    public String descripcion;
    @SerializedName("clvPrioridadQueja")
    @Expose
    private Long clvPrioridadQueja;

    public GetSoftvGetPrioridadQuejaListResult(Long clv_trabajo, String descripcion){
        this. clvPrioridadQueja= clv_trabajo;
        this.descripcion = descripcion;
    }




    public Long getBaseIdUser() {
        return baseIdUser;
    }

    public void setBaseIdUser(Long baseIdUser) {
        this.baseIdUser = baseIdUser;
    }

    public Object getBaseRemoteIp() {
        return baseRemoteIp;
    }

    public void setBaseRemoteIp(Object baseRemoteIp) {
        this.baseRemoteIp = baseRemoteIp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}






