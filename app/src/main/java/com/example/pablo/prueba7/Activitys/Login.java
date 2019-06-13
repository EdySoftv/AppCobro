package com.example.pablo.prueba7.Activitys;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;
import com.example.pablo.prueba7.sampledata.BarraCargar;
import com.example.pablo.prueba7.sampledata.Util;
import com.google.firebase.iid.FirebaseInstanceId;


public class Login extends AppCompatActivity {

    public static EditText usurio, contraseña;
    public static Button entrar;
    public static ImageButton viewPassword;
    private String user;
    public static String enco;
    private Request request = new Request();
    public final static String CHANNEL_ID = "NOTIFICACION";
    public final static int NOTIFICACION_ID = 0;
    public static TextView clave;
    public static ProgressDialog dialogLogin;
BarraCargar barraCargar = new BarraCargar();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usurio = (EditText) findViewById(R.id.usuario);
        contraseña = (EditText) findViewById(R.id.contrasenia);
        entrar = (Button)findViewById(R.id.btnLogin);

        viewPassword = (ImageButton) findViewById(R.id.viewPassword);
        setTitle(null);
        dialogLogin= new BarraCargar().showDialog(this);

        //Log.d("asd", FirebaseInstanceId.getInstance().getToken());

        viewPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        contraseña.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        contraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                return false;
            }
        });


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usurio.getText().toString().length()==0){
    Toast.makeText(getApplicationContext(), "Introduzca usuario", Toast.LENGTH_LONG).show();
     }else{
                    if(contraseña.getText().toString().length()==0){
        Toast.makeText(getApplicationContext(), "Introduzca contraseña", Toast.LENGTH_LONG).show();
    }else{
if(!isOnline()){
   Toast.makeText(getApplicationContext(), "No cuenta con conexión a Internet", Toast.LENGTH_LONG).show();
}else{
    user = usurio.getText().toString() + ":" + contraseña.getText().toString();
    enco = (android.util.Base64.encodeToString(user.getBytes(), android.util.Base64.NO_WRAP));
    guardarPre(usurio.getText().toString(),enco);
    request.getReviews(Login.this);
    /////////////

dialogLogin.show();
    /////////////
}

    }
}
            }
        });

    }



    public void guardarPre(String usario,String encode){
    Util.preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
    Util.editor = Util.preferences.edit();
    Util.editor.putString("usuario",usario);
    Util.editor.putString("enco","Basic: " +encode);
    Util.editor.commit();
}
    //Notificaciones

    //Metodo para hacer detener el codigo
    public static void esperar(int segundos){
        try {
            Thread.sleep(segundos * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
    @Override
    public void onBackPressed() {

    }

}
