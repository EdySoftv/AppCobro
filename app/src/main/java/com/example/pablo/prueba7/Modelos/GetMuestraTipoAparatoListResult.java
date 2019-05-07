package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetMuestraTipoAparatoListResult {
    @SerializedName("Nombre")
    @Expose
    private  String nombre;
    @SerializedName("IdArticulo")
    @Expose
    private  Integer idArticulo;
    @SerializedName("Categoria")
    @Expose
    public  String categoria;
    @SerializedName("Letra")
    @Expose
    public  String letra;

    public String getLetra() {
        return letra;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }
}
