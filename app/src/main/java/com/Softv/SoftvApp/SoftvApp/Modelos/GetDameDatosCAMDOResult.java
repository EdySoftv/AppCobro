package com.Softv.SoftvApp.SoftvApp.Modelos;

public class GetDameDatosCAMDOResult {
    public  String Casa;
    public  String Ciudad;
    public  String NUMERO;
    public  String Num_int;
    public  String TELEFONO;
    public  String calle;
    public  String calleEste;
    public  String calleNorte;
    public  String calleOeste;
    public  String calleSur;
    public  String colonia;
    public  String localidad;
    public  String Referencia;

    public GetDameDatosCAMDOResult(String casa, String ciudad, String numero, String num_int, String telefono, String calle, String calleEste
            , String calleNorte, String calleOeste, String calleSur, String colonia, String localidad,String referencias){
        this.Casa = casa;
        this.Ciudad = ciudad;
        this.NUMERO = numero;
        this.Num_int = num_int;
        this.TELEFONO = telefono;
        this.calle = calle;
        this.calleEste = calleEste;
        this.calleNorte = calleNorte;
        this.calleOeste = calleOeste;
        this.calleSur = calleSur;
        this.colonia = colonia;
        this.localidad = localidad;
        this.Referencia = referencias;
    }

    public String getReferencias() {
        return Referencia;
    }

    public  String getCalle() {
        return calle;
    }

    public  String getColonia() {
        return colonia;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
}
