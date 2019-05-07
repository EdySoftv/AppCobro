package com.example.pablo.prueba7.Modelos;

public class GetMuestraRelOrdenesTecnicosListResult {
    public String NOMBRE;
    public int CLV_TECNICO;



    public GetMuestraRelOrdenesTecnicosListResult(String NOMBRE, int CLV_TECNICO) {
        this.NOMBRE = NOMBRE;
        this.CLV_TECNICO = CLV_TECNICO;

    }

    public  String getNOMBRE() {
        return NOMBRE;
    }
    public int getCLV_TECNICO() {
        return CLV_TECNICO;
    }
}
