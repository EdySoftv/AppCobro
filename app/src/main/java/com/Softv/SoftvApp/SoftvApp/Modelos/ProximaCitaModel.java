package com.Softv.SoftvApp.SoftvApp.Modelos;

public class ProximaCitaModel {
    public  String Calle;
    public int Clave;
    public String Colonia;
    public String Contrato;
    public String Hora;
    public  String NUMERO;
    public String Tipo;




    public String getCalle() {
        return Calle;
    }

    public String getColonia() {
        return Colonia;
    }

    public String getContrato() {
        return Contrato;
    }

    public String getHora() {
        return Hora;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setCalle(String calle) {
        Calle = calle;
    }

    public void setColonia(String colonia) {
        Colonia = colonia;
    }

    public void setContrato(String contrato) {
        Contrato = contrato;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}