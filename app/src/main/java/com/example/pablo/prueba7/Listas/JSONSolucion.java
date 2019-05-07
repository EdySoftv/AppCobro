package com.example.pablo.prueba7.Listas;

import com.example.pablo.prueba7.Modelos.GetMUESTRATRABAJOSQUEJASListResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONSolucion {
    @SerializedName("GetMUESTRATRABAJOSQUEJASListResult")
    @Expose
    private List<GetMUESTRATRABAJOSQUEJASListResult> getMUESTRATRABAJOSQUEJASListResult = null;

    public List<GetMUESTRATRABAJOSQUEJASListResult> getGetMUESTRATRABAJOSQUEJASListResult() {
        return getMUESTRATRABAJOSQUEJASListResult;
    }
}
