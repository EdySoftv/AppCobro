package com.Softv.SoftvApp.SoftvApp.Activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Request.Request;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;


public class Login extends AppCompatActivity {

    private EditText usurio, contraseña;
    private Button entrar;
    private ImageButton viewPassword;
    private String user;
    private String enco;
    private Request request = new Request();
    private View view;
    private ProgressDialog dialogLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        view = (View) findViewById(R.id.ContenidoLogin);
        usurio = (EditText) findViewById(R.id.usuario);
        contraseña = (EditText) findViewById(R.id.contrasenia);
        entrar = (Button) findViewById(R.id.btnLogin);
        viewPassword = (ImageButton) findViewById(R.id.viewPassword);
        //titulo del login 'null' para que se muestre pantalla completa
        setTitle(null);
        //se declara el dialog de carga
        dialogLogin = new BarraCargar().showDialog(this);

        //Metodo para mostrar contraseña
        viewPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        contraseña.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case MotionEvent.ACTION_UP:
                        contraseña.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                return false;
            }
        });

        //Boton de inicio de sesion
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verifica que el campo usuario este lleno
                if (usurio.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Introduzca usuario", Toast.LENGTH_LONG).show();
                } else {
                    //varifica que el campo contraseña este lleno
                    if (contraseña.getText().toString().length() == 0) {
                        Toast.makeText(getApplicationContext(), "Introduzca contraseña", Toast.LENGTH_LONG).show();
                    } else {
                        //verifica que el usuario tenga internet
                        if (!isOnline()) {
                            Toast.makeText(getApplicationContext(), "No cuenta con conexión a Internet", Toast.LENGTH_LONG).show();
                        } else {
                            //se encadena el usuario y contraseña
                            user = usurio.getText().toString() + ":" + contraseña.getText().toString();
                            //se codifica la cadena de texto en base64
                            enco = (android.util.Base64.encodeToString(user.getBytes(), android.util.Base64.NO_WRAP));
                            //se manda el campo usuario y enco para guardarlos en el SharedPreferences
                            guardarPre(usurio.getText().toString(), enco);
                            //manda request del login
                            request.getReviews(Login.this,dialogLogin,view);
                            //inicia el dialog de carga
                            dialogLogin.show();
                            /////////////
                        }

                    }
                }
            }
        });

    }

    //metodo que guarda datos en el preference
    public void guardarPre(String usario, String encode) {
        //se inicia el preferences
        Util.preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        Util.editor = Util.preferences.edit();
        //se guarda el usario
        Util.editor.putString("usuario", usario);
        //se guarda la cadena en base64
        Util.editor.putString("enco", "Basic: " + encode);
        Util.editor.commit();
    }

    //verifica que el usuario tenga internet
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
