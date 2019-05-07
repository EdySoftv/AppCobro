package com.example.pablo.prueba7.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.pablo.prueba7.Modelos.GetListClienteAparatosResult;

import java.util.List;

public class JSONCLIAPA {
  @SerializedName("GetListClienteAparatosResult")
  @Expose
  private List<GetListClienteAparatosResult> GetListClienteAparatosResult = null;

  public List<GetListClienteAparatosResult> GetListClienteAparatosResult() {
    return GetListClienteAparatosResult;
  }

}