package com.example.pablo.prueba7.Modelos;

public class DescripcionArticuloModel {
    public int Cantidad;
    public int IdArticulo;
    public int IdInventario;
    public String Nombre;

    public DescripcionArticuloModel(int cantidad, int idArticulo, int idInventario, String nombre) {
        this.Cantidad = cantidad;
        this.IdArticulo = idArticulo;
        this.IdInventario = idInventario;
        this.Nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public String getNombre() {
        return Nombre;
    }
}
