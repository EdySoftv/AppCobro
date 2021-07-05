package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetListaNapTapResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONAPTAP {
    @SerializedName("GetListaNapTapResult")
    @Expose
    private List<com.Softv.SoftvApp.SoftvApp.Modelos.GetListaNapTapResult> GetListaNapTapResult = null;

    public List<GetListaNapTapResult> GetListaNapTapResult() {
        return GetListaNapTapResult;
    }
}
