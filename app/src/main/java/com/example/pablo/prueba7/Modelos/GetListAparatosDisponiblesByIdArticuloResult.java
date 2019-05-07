package com.example.pablo.prueba7.Modelos;

public class GetListAparatosDisponiblesByIdArticuloResult {
  public int Clv_Aparato;
  public String Descripcion;

  public GetListAparatosDisponiblesByIdArticuloResult(int clv_Aparato, String descripcion){
    this.Clv_Aparato = clv_Aparato;
    this.Descripcion = descripcion;
  }


  public String getDescripcion() {
    return Descripcion;
  }

  public int getClv_Aparato() {
    return Clv_Aparato;
  }

  public void setDescripcion(String descripcion) {
    Descripcion = descripcion;
  }

}