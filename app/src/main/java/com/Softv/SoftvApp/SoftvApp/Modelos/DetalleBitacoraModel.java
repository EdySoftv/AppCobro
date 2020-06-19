package com.Softv.SoftvApp.SoftvApp.Modelos;

public class DetalleBitacoraModel {
    public String Descripcion;
    public int catTipoArticuloClave;
    public int esFibra;

    public DetalleBitacoraModel(String descripcion, int catTipoArticuloClave,int esFibra) {
        this.Descripcion = descripcion;
        this.catTipoArticuloClave = catTipoArticuloClave;
        this.esFibra = esFibra;
    }

    public int getEsFibra() {
        return esFibra;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

}
