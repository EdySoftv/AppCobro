package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.DatosClientesSaldoList;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetDameListadoOrdenesAgendadasResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ListaClientesSaldos {
    //Lista donde se guarla la informacion que regresa el servidor
    @SerializedName("DatosClientesSaldoListResult")
    @Expose
    private List<DatosClientesSaldoList> getDatosClientesSaldoListResult = null;

    public List<DatosClientesSaldoList> DatosClientesSaldoListResult() {
        return getDatosClientesSaldoListResult;
    }
}
