package com.Softv.SoftvApp.SoftvCobro.Listas;

import com.Softv.SoftvApp.SoftvCobro.Modelos.DatosClientesSaldoList;
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
