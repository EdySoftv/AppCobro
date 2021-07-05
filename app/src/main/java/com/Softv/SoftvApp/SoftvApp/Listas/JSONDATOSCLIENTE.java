package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetConsultaClientesListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.SelectRelTecnicoCuadrillaResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONDATOSCLIENTE {
    @SerializedName("GetConsultaClientesListResult")
    @Expose
    private List<com.Softv.SoftvApp.SoftvApp.Modelos.GetConsultaClientesListResult> GetConsultaClientesListResult = null;

    public List<GetConsultaClientesListResult> GetConsultaClientesListResult() {
        return GetConsultaClientesListResult;
    }
}
