package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.ListadoQuejasAgendadas;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class QuejasList {

    @SerializedName("GetDameListadoQuejasAgendadasResult")
    @Expose
    private List<com.Softv.SoftvApp.SoftvApp.Modelos.ListadoQuejasAgendadas> ListadoQuejasAgendadas = null;

    public List<ListadoQuejasAgendadas> GetDameListadoQuejasAgendadasResult() {
        return ListadoQuejasAgendadas;
    }
}