package com.Softv.SoftvApp.SoftvApp.Modelos;

public class GetConsultaClientesListResult {
    int TipoCliente;
    String CELULAR;
    String TELEFONO;
    String Calle;
    String NUMERO;
    String NumInt;
    String Col;

    public GetConsultaClientesListResult(int tipoCliente, String celular,String telefono, String calle, String numero, String numint, String col) {
        TipoCliente = tipoCliente;
        CELULAR = celular;
        TELEFONO = telefono;
        Calle = calle;
        NUMERO = numero;
        NumInt =numint;
        Col = col;

    }

    public void setCELULAR(String CELULAR) {
        this.CELULAR = CELULAR;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getCELULAR() {
        return CELULAR;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public int getTipoCliente() {
        return TipoCliente;
    }

    public String getCalle() {
        return Calle;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public String getNumInt() {
        return NumInt;
    }

    public String getCol() {
        return Col;
    }
}
