package com.Softv.SoftvApp.SoftvApp.Modelos;

public class GetConsultaClientesListResult {
    int TipoCliente;
    String CELULAR;
    String TELEFONO;

    public GetConsultaClientesListResult(int tipoCliente, String celular,String telefono) {
        TipoCliente = tipoCliente;
        CELULAR = celular;
        TELEFONO = telefono;
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

}
