package com.Softv.Bolivia.prueba7.Listas;

import com.Softv.Bolivia.prueba7.Modelos.ListadoQuejasAgendadas;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class QuejasList {

    @SerializedName("GetDameListadoQuejasAgendadasResult")
    @Expose
    private List<com.Softv.Bolivia.prueba7.Modelos.ListadoQuejasAgendadas> ListadoQuejasAgendadas = null;

    public List<ListadoQuejasAgendadas> GetDameListadoQuejasAgendadasResult() {
        return ListadoQuejasAgendadas;
    }
}