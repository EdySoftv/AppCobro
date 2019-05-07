package com.example.pablo.prueba7.Modelos;

public class dameTblPreDescargaMaterialResultModel {
    public int NoExt;
    public int cantidadUtilizada;
    public int clvOrden;
    public int clvTecnico;
    public int contrato;
    public boolean esCable;
    public int idAlmacenEmpresa;
    public int idPreDescarga;
    public int metrajeFin;
    public int metrajeFinExterior;
    public int metrajeInicio;
    public int metrajeInicioExterior;
    public int noArticulo;
    public String tipoDescarga;
    public String Nombre;

    public dameTblPreDescargaMaterialResultModel(int noExt, int cantidadUtilizada, int clvOrden, int clvTecnico, int contrato, boolean esCable, int idAlmacenEmpresa, int idPreDescarga, int metrajeFin, int metrajeFinExterior, int metrajeInicio, int metrajeInicioExterior, int noArticulo, String tipoDescarga, String nombre) {
        this.NoExt = noExt;
        this.cantidadUtilizada = cantidadUtilizada;
        this.clvOrden = clvOrden;
        this.clvTecnico = clvTecnico;
        this.contrato = contrato;
        this.esCable = esCable;
        this.idAlmacenEmpresa = idAlmacenEmpresa;
        this.idPreDescarga = idPreDescarga;
        this.metrajeFin = metrajeFin;
        this.metrajeFinExterior = metrajeFinExterior;
        this.metrajeInicio = metrajeInicio;
        this.metrajeInicioExterior = metrajeInicioExterior;
        this.noArticulo = noArticulo;
        this.tipoDescarga = tipoDescarga;
        this.Nombre=nombre;
    }
    public String getNombre() {
        return Nombre;
    }
    public int getNoExt() {
        return NoExt;
    }

    public int getContrato() {
        return contrato;
    }

}
