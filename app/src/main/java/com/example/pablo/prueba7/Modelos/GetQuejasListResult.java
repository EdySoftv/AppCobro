package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetQuejasListResult {

    @SerializedName("BaseIdUser")
    @Expose
    private Long baseIdUser;
    @SerializedName("BaseRemoteIp")
    @Expose
    private Object baseRemoteIp;
    @SerializedName("COMENTARIO")
    @Expose
    private Object cOMENTARIO;
    @SerializedName("CallCenter")
    @Expose
    private Object callCenter;
    @SerializedName("Clasificacion")
    @Expose
    private String clasificacion;
    @SerializedName("ClasificacionProblema")
    @Expose
    private String clasificacionProblema;
    @SerializedName("Clave_Tecnico")
    @Expose
    private Long claveTecnico;
    @SerializedName("Clv_Cita")
    @Expose
    private Object clvCita;
    @SerializedName("Clv_Queja")
    @Expose
    private Long clvQueja;
    @SerializedName("Clv_Tecnico")
    @Expose
    private Object clvTecnico;
    @SerializedName("Clv_TipSer")
    @Expose
    private Long clvTipSer;
    @SerializedName("Clv_TipoQueja")
    @Expose
    private Object clvTipoQueja;
    @SerializedName("Clv_Trabajo")
    @Expose
    private Long clvTrabajo;
    @SerializedName("ComentarioAge")
    @Expose
    private Object comentarioAge;
    @SerializedName("Contrato")
    @Expose
    private Object contrato;
    @SerializedName("ContratoCom")
    @Expose
    private String contratoCom;
    @SerializedName("EjecucuionReal")
    @Expose
    private Object ejecucuionReal;
    @SerializedName("FechaAge")
    @Expose
    private String fechaAge;
    @SerializedName("FechaProceso")
    @Expose
    private Object fechaProceso;
    @SerializedName("Fecha_Actualizacion")
    @Expose
    private Object fechaActualizacion;
    @SerializedName("Fecha_Captura")
    @Expose
    private Object fechaCaptura;
    @SerializedName("Fecha_Ejecucion")
    @Expose
    private String fechaEjecucion;
    @SerializedName("Fecha_Soliciutud")
    @Expose
    public  String fechaSoliciutud;
    @SerializedName("HP")
    @Expose
    private Object hP;
    @SerializedName("HV1")
    @Expose
    private Object hV1;
    @SerializedName("HV2")
    @Expose
    private Object hV2;
    @SerializedName("HV3")
    @Expose
    private Object hV3;
    @SerializedName("Hora_Ejecucion")
    @Expose
    private Object horaEjecucion;
    @SerializedName("IdUsuario")
    @Expose
    private Object idUsuario;
    @SerializedName("Imp")
    @Expose
    private Object imp;
    @SerializedName("Impresa")
    @Expose
    private Object impresa;
    @SerializedName("Nivel")
    @Expose
    private Long nivel;
    @SerializedName("NombreTecAge")
    @Expose
    private String nombreTecAge;
    @SerializedName("NombreTecnicoCuadrilla")
    @Expose
    private Object nombreTecnicoCuadrilla;
    @SerializedName("Observaciones")
    @Expose
    public String observaciones;
    @SerializedName("Prioridad")
    @Expose
    private String prioridad;
    @SerializedName("Problema")
    @Expose
    public String problema;
    @SerializedName("Solucion")
    @Expose
    private Object solucion;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("TecnicoCuadrilla")
    @Expose
    public int tecnicoCuadrilla;
    @SerializedName("TipSer")
    @Expose
    private String tipSer;
    @SerializedName("TipoSolucion")
    @Expose
    private Object tipoSolucion;
    @SerializedName("Turno")
    @Expose
    private Object turno;
    @SerializedName("TurnoAge")
    @Expose
    private String turnoAge;
    @SerializedName("Usuario")
    @Expose
    private Object usuario;
    @SerializedName("UsuarioEjecuto")
    @Expose
    private Object usuarioEjecuto;
    @SerializedName("UsuarioGenero")
    @Expose
    private String usuarioGenero;
    @SerializedName("Visita")
    @Expose
    private Boolean visita;
    @SerializedName("Visita1")
    @Expose
    private Object visita1;
    @SerializedName("Visita2")
    @Expose
    private Object visita2;
    @SerializedName("Visita3")
    @Expose
    private Object visita3;
    @SerializedName("clvPrioridadQueja")
    @Expose
    public int clvPrioridadQueja;
    @SerializedName("clvProblema")
    @Expose
    private Long clvProblema;
    @SerializedName("clv_llamada")
    @Expose
    private Object clvLlamada;

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

    public String getClasificacionProblema() {
        return clasificacionProblema;
    }

    public Object getContrato() {
        return contrato;
    }

    public void setContrato(Object contrato) {
        this.contrato = contrato;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getProblema() {
        return problema;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getUsuario() {
        return usuario;
    }

    public void setUsuario(Object usuario) {
        this.usuario = usuario;
    }

    public Boolean getVisita() {
        return visita;
    }

    public void setVisita(Boolean visita) {
        this.visita = visita;
    }

   }
