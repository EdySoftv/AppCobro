package com.Softv.SoftvApp.SoftvApp.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListaOnusVallarta {
    @SerializedName("Onu")
    @Expose
    private String Onu;
    @SerializedName("Inte")
    @Expose
    private int Inte;
    @SerializedName("Tel")
    @Expose
    private int Tel;


    public String getOnu() {
        return Onu;
    }

    public int getInt() {
        return Inte;
    }

    public int getTel() {
        return Tel;
    }

    public void setOnu(String Onu) {
        this.Onu=Onu;
    }

    public void setInt(int Inte) {
        this.Inte=Inte;
    }

    public void setTel(int Tel) {
        this.Tel=Tel;
    }
}
