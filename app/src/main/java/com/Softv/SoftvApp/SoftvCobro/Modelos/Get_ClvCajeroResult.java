package com.Softv.SoftvApp.SoftvCobro.Modelos;


public class Get_ClvCajeroResult {
    public String clv_tecnico;
    public String tecnico;



    public Get_ClvCajeroResult(String clv_tecnico, String tecnico) {
        this.clv_tecnico = clv_tecnico;
        this.tecnico = tecnico;
    }
    public  String getNombre_tec() {
        return tecnico;
    }

}