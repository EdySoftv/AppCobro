package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetuspBuscaContratoSeparado2ListResult {
    @SerializedName("BaseIdUser")
    @Expose
    private Long baseIdUser;
    @SerializedName("BaseRemoteIp")
    @Expose
    private Object baseRemoteIp;
    @SerializedName("Activo")
    @Expose
    private Long activo;
    @SerializedName("Apellido_Materno")
    @Expose
    private String apellidoMaterno;
    @SerializedName("Apellido_Paterno")
    @Expose
    private String apellidoPaterno;
    @SerializedName("CALLE")
    @Expose
    private String cALLE;
    @SerializedName("CIUDAD")
    @Expose
    private String cIUDAD;
    @SerializedName("COLONIA")
    @Expose
    private String cOLONIA;
    @SerializedName("CONTRATO")
    @Expose
    private String cONTRATO;
    @SerializedName("ClvColonia")
    @Expose
    private Object clvColonia;
    @SerializedName("ContratoBueno")
    @Expose
    public Long contratoBueno;
    @SerializedName("ContratoCom")
    @Expose
    private Object contratoCom;
    @SerializedName("ESHOTEL")
    @Expose
    private Boolean eSHOTEL;
    @SerializedName("IdDistribuidor")
    @Expose
    private Object idDistribuidor;
    @SerializedName("IdUsuario")
    @Expose
    private Object idUsuario;
    @SerializedName("NUMERO")
    @Expose
    private String nUMERO;
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Op")
    @Expose
    private Object op;
    @SerializedName("SOLOINTERNET")
    @Expose
    private Boolean sOLOINTERNET;
    @SerializedName("SetupBox")
    @Expose
    private Object setupBox;
    @SerializedName("TipoSer")
    @Expose
    private Object tipoSer;

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

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getCALLE() {
        return cALLE;
    }

    public String getCIUDAD() {
        return cIUDAD;
    }

    public String getCOLONIA() {
        return cOLONIA;
    }

    public String getCONTRATO() {
        return cONTRATO;
    }

    public String getNUMERO() {
        return nUMERO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    }

