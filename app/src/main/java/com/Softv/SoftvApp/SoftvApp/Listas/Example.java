package com.Softv.SoftvApp.SoftvApp.Listas;

import com.Softv.SoftvApp.SoftvApp.Modelos.GetDameOrdenesQuejasTotalesResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {
    //Lista donde se guarla la informacion que regresa el servidor

    @SerializedName("GetDameOrdenesQuejasTotalesResult")
    @Expose
    public GetDameOrdenesQuejasTotalesResult getDameOrdenesQuejasTotalesResult;
}