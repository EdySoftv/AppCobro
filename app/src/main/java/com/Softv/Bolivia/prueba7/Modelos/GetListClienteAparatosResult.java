package com.Softv.Bolivia.prueba7.Modelos;

public class GetListClienteAparatosResult {
    public  int ControNet;
    public  String Descripcion;
    public  int idArticulo;
    public String Mac;
    public String Letra;
    public int Clv_Aparato;

    public GetListClienteAparatosResult(int ControlNet, String Descripcion, int idArticulo, String mac, int clv_Aparato,String Letra){
        this.ControNet = ControlNet;
        this.Descripcion = Descripcion;
        this.idArticulo = idArticulo;
        this.Mac = mac;
        this.Clv_Aparato = clv_Aparato;
        this.Letra = Letra;

    }

    public String getLetra() {
        return Letra;
    }

    public  int getControNet() {
        return ControNet;
    }

    public  String getDescripcion() {
        return Descripcion;
    }

    public  int getIdArticulo() {
        return idArticulo;
    }


    public  void setDescripcion(String Descripcion) {
        Descripcion = Descripcion;
    }


    public String getMac() {
        return Mac;
    }

}
