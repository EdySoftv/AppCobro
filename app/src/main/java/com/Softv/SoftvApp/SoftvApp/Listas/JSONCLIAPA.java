package com.Softv.SoftvApp.SoftvApp.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListClienteAparatosResult;

import java.util.List;

public class JSONCLIAPA {
  @SerializedName("GetListClienteAparatosResult")
  @Expose
  private List<GetListClienteAparatosResult> GetListClienteAparatosResult = null;

  public List<GetListClienteAparatosResult> GetListClienteAparatosResult() {
    return GetListClienteAparatosResult;
  }

}