package com.example.pablo.prueba7.Modelos;


public class Get_ClvTecnicoResult {
    public String clv_tecnico;
    public String tecnico;



    public Get_ClvTecnicoResult(String clv_tecnico,String tecnico) {
        this.clv_tecnico = clv_tecnico;
        this.tecnico=tecnico;
    }
    public  String getNombre_tec() {
        return tecnico;
    }

}