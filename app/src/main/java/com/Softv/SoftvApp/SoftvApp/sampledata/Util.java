package com.Softv.SoftvApp.SoftvApp.sampledata;

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

    public static int getClvOrden(SharedPreferences preferences){
        return preferences.getInt("clvOrden", 0);
    }

    public static int getClvQueja(SharedPreferences preferences){
        return preferences.getInt("clvQueja", 0);
    }

    public static int getClvUsuario(SharedPreferences preferences){
        return preferences.getInt("clvUsuario", 0);
    }

    public static String getNombreTecnicoPreference(SharedPreferences preferences){
        return preferences.getString("nombre_Tecnico", "");

    }
    public static int getClvTec(SharedPreferences preferences){
        return preferences.getInt("clvTec", 0);

    }
    public static String getTipoDescarga(SharedPreferences preferences){
        return preferences.getString("TipoDescarga", "");}

}
