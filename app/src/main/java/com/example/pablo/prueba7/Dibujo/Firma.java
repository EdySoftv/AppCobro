package com.example.pablo.prueba7.Dibujo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Request.Request;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Firma extends AppCompatActivity {
     Dibujar dibujar;
    public static String imagenAEnviar="";
    public static String firma1;
    Request request = new Request();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_firma);
        dibujar= (Dibujar) findViewById(R.id.FirmaLayout);
        Toolbar toolbar;

    setTitle("Firma Cliente");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicio,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nuevoDibujo:
              dibujar.NuevoDibujo();
                break;
            case R.id.guardarDibujo:
                recortar(Screenshot.TakeScreenshot(dibujar.getRootView()));
                finish();
                request.addFirma(getApplicationContext());
        }
        return true;
    }




    public static String ConvertirImgString(Bitmap myBitmap) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, array);
        byte [] imagebyte = array.toByteArray();
        return Base64.encodeToString(imagebyte, Base64.DEFAULT);
    }
    private void recortar(Bitmap bmp){

        int recorte = (int) (bmp.getHeight() * .15);
        int alto = (bmp.getHeight() - recorte);

        Bitmap resizedbitmap1 = Bitmap.createBitmap(bmp, 0, recorte,bmp.getWidth(), alto);
        imagenAEnviar = ConvertirImgString(resizedbitmap1);
        //System.out.println(imagenAEnviar);


    }
    }


