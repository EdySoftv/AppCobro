package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetuspBuscaContratoSeparado2ListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONReportes {
    @SerializedName("GetuspBuscaContratoSeparado2ListResult")
    @Expose
    private List<GetuspBuscaContratoSeparado2ListResult> getuspBuscaContratoSeparado2ListResult = null;

    public List<GetuspBuscaContratoSeparado2ListResult> getGetuspBuscaContratoSeparado2ListResult() {
        return getuspBuscaContratoSeparado2ListResult;
    }
}


