package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetuspConsultaTblClasificacionProblemasListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONClasificacionProblm {
    @SerializedName("GetuspConsultaTblClasificacionProblemasListResult")
    @Expose
    private List<GetuspConsultaTblClasificacionProblemasListResult> getuspConsultaTblClasificacionProblemasListResult = null;

    public List<GetuspConsultaTblClasificacionProblemasListResult> getGetuspConsultaTblClasificacionProblemasListResult() {
        return getuspConsultaTblClasificacionProblemasListResult;
    }

}
