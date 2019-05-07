package com.example.pablo.prueba7.Dibujo;

import android.graphics.Bitmap;
import android.view.View;

public class Screenshot {
    // Función Bitmap encargada de guardar el contenido de un View
    public static Bitmap TakeScreenshot(View view) {

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }
}
