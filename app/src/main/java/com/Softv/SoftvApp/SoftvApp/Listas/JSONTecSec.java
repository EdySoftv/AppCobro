package com.Softv.SoftvApp.SoftvApp.Listas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraRelOrdenesTecnicosListResult;

import java.util.List;

public class JSONTecSec {
  @SerializedName("GetMuestraRelOrdenesTecnicosListResult")
  @Expose
  private List<GetMuestraRelOrdenesTecnicosListResult> GetMuestraRelOrdenesTecnicosListResult = null;

  public List<GetMuestraRelOrdenesTecnicosListResult> GetMuestraRelOrdenesTecnicosListResult() {
    return GetMuestraRelOrdenesTecnicosListResult;
  }
}