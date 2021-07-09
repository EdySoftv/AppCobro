
package com.Softv.SoftvApp.SoftvCobro.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DatosClientesSaldoList {

    @SerializedName("ContratoCompuesto")
    @Expose
    private String ContratoCompuesto;

    @SerializedName("Nombre")
    @Expose
    private String Nombre;

    @SerializedName("Telefono")
    @Expose
    private String Telefono;

    @SerializedName("Calle")
    @Expose
    private String Calle;

    @SerializedName("Numero")
    @Expose
    private String Numero;

    @SerializedName("Colonia")
    @Expose
    private String Colonia;

    @SerializedName("Contrato")
    @Expose
    private String Contrato;

    public String getContratoCompuesto() {
        return ContratoCompuesto;
    }

    public void setContratoCompuesto(String contratoCompuesto) {
        ContratoCompuesto = contratoCompuesto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getColonia() {
        return Colonia;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }

    public String getContrato() {
        return Contrato;
    }

    public void setContrato(String contrato) {
        Contrato = contrato;
    }
}
