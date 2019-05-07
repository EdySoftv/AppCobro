package com.example.pablo.prueba7.Modelos;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetDameOrdenesQuejasTotalesResult {

    @SerializedName("OrdSer")
    @Expose
    private List<OrdSer> OrdSer = null;
    @SerializedName("Queja")
    @Expose
    private List<Queja> Queja = null;

    public List<OrdSer> getOrdSer() {
        return OrdSer;
    }

    public List<Queja> getQueja() {
        return Queja;
    }

}
