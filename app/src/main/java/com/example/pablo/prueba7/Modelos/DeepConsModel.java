package com.example.pablo.prueba7.Modelos;


public class DeepConsModel {
    public static int Clv_FACTURA;
    public static int Contrato;
    public static String ContatoCom;
    public static String STATUS;
    public static String Obs;
    public static int Clv_Orden;
    public static int Clv_TipSer;
    public static String Fec_Sol;
    public static String Visita1;

    public DeepConsModel(int clv_FACTURA,int Contrato, String ContratoCom, String STATUS, String Obs, Integer Clv_Orden, int clv_TipSer, String fec_Sol, String Visita1){
        this.Contrato = Contrato;
        this.ContatoCom = ContratoCom;
        this.STATUS = STATUS;
        this.Obs = Obs;
        this.Clv_Orden = Clv_Orden;
        this.Clv_TipSer = clv_TipSer;
        this.Fec_Sol = fec_Sol;
        this.Clv_FACTURA = clv_FACTURA;
        this.Visita1 = Visita1;

    }

    public static int getContrato() {
        return Contrato;
    }
    public void setContrato(int contrato) {
        Contrato = contrato;
    }

    public static String getContatoCom() {
        return ContatoCom;
    }

   }
