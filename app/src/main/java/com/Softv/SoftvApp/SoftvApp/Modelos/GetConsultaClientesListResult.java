package com.Softv.SoftvApp.SoftvApp.Modelos;

public class GetConsultaClientesListResult {
    int TipoCliente;

    public GetConsultaClientesListResult(int tipoCliente) {
        TipoCliente = tipoCliente;
    }

    public int getTipoCliente() {
        return TipoCliente;
    }
}
