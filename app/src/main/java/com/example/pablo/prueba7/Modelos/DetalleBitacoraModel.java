package com.example.pablo.prueba7.Modelos;

public class DetalleBitacoraModel {
    public String Descripcion;
    public int catTipoArticuloClave;

    public DetalleBitacoraModel(String descripcion, int catTipoArticuloClave) {
        this.Descripcion = descripcion;
        this.catTipoArticuloClave = catTipoArticuloClave;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

}
