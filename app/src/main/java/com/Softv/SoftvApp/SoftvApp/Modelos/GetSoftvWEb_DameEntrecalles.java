package com.Softv.SoftvApp.SoftvApp.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSoftvWEb_DameEntrecalles {
    @SerializedName("Casa")
    @Expose
    private String Casa;
    @SerializedName("Este")
    @Expose
    private String Este;
    @SerializedName("Norte")
    @Expose
    public String Norte;
    @SerializedName("Oeste")
    @Expose
    private String Oeste;
    @SerializedName("Sur")
    @Expose
    public String Sur;
    @SerializedName("referencia")
    @Expose
    private String referencia;



    public String getCasa() {
        return Casa;
    }

    public String getEste() {
        return Este;
    }

    public String getNorte() {
        return Norte;
    }

    public String getOeste() {
        return Oeste;
    }

    public String getSur() {
        return Sur;
    }

    public String getReferencia() {
        return referencia;
    }
}
