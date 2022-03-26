package com.Softv.SoftvApp.SoftvCobro.Listas;

import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelServiciosList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.UltimoSerieYFolioUnica;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class UltimoSerieYFolio {
    @SerializedName("UltimoSerieYFolioUnicaResult")
    @Expose
    private List<UltimoSerieYFolioUnica> UltimoSerieYFolioUnicaResult = null;

    public List<UltimoSerieYFolioUnica> UltimoSerieYFolioUnicaResult() {
        return UltimoSerieYFolioUnicaResult;
    }
}