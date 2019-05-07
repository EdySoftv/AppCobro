package com.example.pablo.prueba7.Listas;


import com.example.pablo.prueba7.Modelos.Get_ClvTecnicoResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JSONResponseTecnico {
  //Lista donde se guarla la informacion que regresa el servidor
  @SerializedName("Get_ClvTecnicoResult")
  @Expose
  private List<Get_ClvTecnicoResult> Get_ClvTecnicoResult = null;

  public List<Get_ClvTecnicoResult> Get_ClvTecnicoResult() {
    return Get_ClvTecnicoResult;
  }
}