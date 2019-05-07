package com.example.pablo.prueba7.sampledata;

import android.content.SharedPreferences;

public class Util {
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;

    public static String getEncoPreference(SharedPreferences preferences){
        return preferences.getString("enco", "");
    }

    public static String getUsuarioPreference(SharedPreferences preferences){
        return preferences.getString("usuario", "");
    }

    public static String getTokenPreference(SharedPreferences preferences){
        return preferences.getString("token", "");
    }

    public static int getPositionTecSec(SharedPreferences preferences){
        return preferences.getInt("posicion", 0);
    }

    public static String getNombreTecnicoPreference(SharedPreferences preferences){
        return preferences.getString("nombre_Tecnico", "");

    }
    public static int getClvTec(SharedPreferences preferences){
        return preferences.getInt("clvTec", 0);

    }
    public static int OE(SharedPreferences preferences){
        return preferences.getInt("OE", 0);}
    public static int OP(SharedPreferences preferences){
        return preferences.getInt("OP", 0);}
    public static int OV(SharedPreferences preferences){
        return preferences.getInt("OV", 0);}
    public static int OEP(SharedPreferences preferences){
        return preferences.getInt("OEP", 0);}
    public static int OO(SharedPreferences preferences){
        return preferences.getInt("OO", 0);}
    public static int RE(SharedPreferences preferences){
        return preferences.getInt("RE", 0);}
    public static int RP(SharedPreferences preferences){
        return preferences.getInt("RP", 0);}
    public static int RV(SharedPreferences preferences){
        return preferences.getInt("RV", 0);}
    public static int REP(SharedPreferences preferences){
        return preferences.getInt("REP", 0);}
    public static int RO(SharedPreferences preferences){
        return preferences.getInt("RO", 0);}
}
