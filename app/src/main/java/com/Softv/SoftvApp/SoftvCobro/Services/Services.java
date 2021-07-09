package com.Softv.SoftvApp.SoftvCobro.Services;


import android.content.Context;
import android.util.Log;

import com.Softv.SoftvApp.SoftvCobro.sampledata.Constants;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Service;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.Softv.SoftvApp.SoftvCobro.Request.Request.Obs;
import static com.Softv.SoftvApp.SoftvCobro.Request.Request.clvP;
import static com.Softv.SoftvApp.SoftvCobro.Request.Request.clvProblemarepo;


public class Services {

    public static int opcion;
    public static int clvorden = 0;
    public static int clavequeja = 0;
    public static String cont;
    public static String precinto;
    public static JSONObject jsonObject = new JSONObject();
    JSONObject jsonObject20 = new JSONObject();
    public static long ClvTrabajoRequest;
    public static String ejecutarStatus;
    public static JSONArray jsonArrayap = new JSONArray();
   // public static JSONArray jsonTokenFirebase = new JSONArray();
    public static JSONObject jsonTokenFirebase = new JSONObject();
    public static int Band=0;

    /////////TOKEN///C////
    public Service getClientService(final Context context) {
        Util.preferences = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        //Modificacion del Header

                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", Util.getEncoPreference(Util.preferences))
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .connectTimeout(150, TimeUnit.MINUTES)
                .readTimeout(150, TimeUnit.MINUTES)
                .writeTimeout(150, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service RequestPost(final Context context, JSONObject jsonObject) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", Util.getTokenPreference(Util.preferences))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        })      .connectTimeout(30, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.MINUTES).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);

    }

    public String getToken(final Context context) {
        String token;
        Util.preferences = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        token = Util.getTokenPreference(Util.preferences);
        Log.d("token", token);
        return token;
    }

    public Service getServiciosSaldos(final Context context, String ContratoSaldo) {
        //POST Body JsonArray
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Contrato", ContratoSaldo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject));
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body).build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.NEW_URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getDetallesSaldos(final Context context, String contratoSaldo) {
        //POST Body JsonArray
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Contrato", contratoSaldo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject));
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body).build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.NEW_URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getListClientesSaldoService(final Context applicationContext, int i, String text) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if(i == 1){
            jsonObject.put("ContratoCom", text);
            jsonObject.put("Nombre", "");
            jsonObject.put("Telefono", "");
            jsonObject.put("Op", i);
        }else if(i == 2){
            jsonObject.put("ContratoCom", "");
            jsonObject.put("Nombre", text);
            jsonObject.put("Telefono", "");
            jsonObject.put("Op", i);
        }else if(i == 3){
            jsonObject.put("ContratoCom", "");
            jsonObject.put("Nombre", "");
            jsonObject.put("Telefono", text);
            jsonObject.put("Op", i);
        }else{
            jsonObject.put("ContratoCom", "");
            jsonObject.put("Nombre", "");
            jsonObject.put("Telefono", "");
            jsonObject.put("Op", 0);
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject));
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(applicationContext))
                        .addHeader("Content-Type", "application/json")
                        .post(body).build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.NEW_URL)
                .client(client).addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }
}
