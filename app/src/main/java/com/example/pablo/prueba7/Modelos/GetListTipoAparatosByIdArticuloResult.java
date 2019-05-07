package com.example.pablo.prueba7.Modelos;

public class GetListTipoAparatosByIdArticuloResult {
    public String Nombre;
    public int IdArticulo;

    public GetListTipoAparatosByIdArticuloResult(String nombre, int idArticulo) {
        this.Nombre = nombre;
        this.IdArticulo = idArticulo;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getIdArticulo() {
        return IdArticulo;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

}