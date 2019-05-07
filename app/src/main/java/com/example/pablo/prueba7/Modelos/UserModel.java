package com.example.pablo.prueba7.Modelos;

public class UserModel {
    private String Usuario;
    public static String Token;
    public static String Codigo;
    public static int Id_Usuario;


    public UserModel(String Usuario, String Token, String codigo, int id_Usuario) {
        this.Usuario = Usuario;
        this.Token = Token;
        this.Codigo=codigo;
        this.Id_Usuario = id_Usuario;

    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    public String getCodigo() {
        return Codigo;
    }

}