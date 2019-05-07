package com.example.pablo.prueba7.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdSer {


    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Total")
    @Expose
    private Integer total;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

}