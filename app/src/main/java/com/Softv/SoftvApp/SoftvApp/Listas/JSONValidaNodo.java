package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetSoftvWebValidaNodo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONValidaNodo {
    @SerializedName("GetSoftvWebValidaNodoResult")
    @Expose
    private List<GetSoftvWebValidaNodo> GetSoftvWebValidaNodo = null;

    public List<GetSoftvWebValidaNodo> GetSoftvWebValidaNodo() {
        return GetSoftvWebValidaNodo;
    }

}
