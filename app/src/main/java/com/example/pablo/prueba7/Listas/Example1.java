package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetDameListadoOrdenesAgendadasResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Example1 {
    //Lista donde se guarla la informacion que regresa el servidor
    @SerializedName("GetDameListadoOrdenesAgendadasResult")
    @Expose
    private List<GetDameListadoOrdenesAgendadasResult> getDameListadoOrdenesAgendadasResult = null;

    public List<GetDameListadoOrdenesAgendadasResult> getGetDameListadoOrdenesAgendadasResult() {
        return getDameListadoOrdenesAgendadasResult;
    }
}
