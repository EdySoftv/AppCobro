package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetuspConsultaTblClasificacionProblemasListResult;
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
