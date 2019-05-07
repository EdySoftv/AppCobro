package com.example.pablo.prueba7.Services;


import android.content.Context;

import com.example.pablo.prueba7.Activitys.CambioAparato;
import com.example.pablo.prueba7.Dibujo.Firma;
import com.example.pablo.prueba7.Dibujo.Screenshot;
import com.example.pablo.prueba7.Fragments.EjecutarFragment;
import com.example.pablo.prueba7.Fragments.HorasFragment;
import com.example.pablo.prueba7.Fragments.InstalacionFragment;

import com.example.pablo.prueba7.Fragments.Materiales;
import com.example.pablo.prueba7.Fragments.MaterialesFragment;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.ChecaSiExtencionesModel;
import com.example.pablo.prueba7.Modelos.DeepConsModel;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.example.pablo.prueba7.Modelos.UserModel;
import com.example.pablo.prueba7.Activitys.asignacion;
import com.example.pablo.prueba7.sampledata.Constants;
import com.example.pablo.prueba7.sampledata.Service;
import com.example.pablo.prueba7.sampledata.Util;

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

import static com.example.pablo.prueba7.Activitys.Inicio.tipodeDescarga;
import static com.example.pablo.prueba7.Adapters.Arbol_Adapter.clv_unicaNet;
import static com.example.pablo.prueba7.Adapters.ordenes_adapter_result.clvor;
import static com.example.pablo.prueba7.Adapters.quejas_adapter_result.clvReport;
import static com.example.pablo.prueba7.Adapters.quejas_adapter_result.contratoReport;
import static com.example.pablo.prueba7.Adapters.trabajos_adapter_result.ClaveTrabajo;

import static com.example.pablo.prueba7.Adapters.trabajos_adapter_result.clvTra;
import static com.example.pablo.prueba7.Dibujo.Firma.ConvertirImgString;
import static com.example.pablo.prueba7.Fragments.Ejecutar1Fragment.horas12;

import static com.example.pablo.prueba7.Fragments.Ejecutar1Fragment.solution;
import static com.example.pablo.prueba7.Fragments.Ejecutar1Fragment.year;
import static com.example.pablo.prueba7.Fragments.EjecutarFragment.añoE;
import static com.example.pablo.prueba7.Fragments.EjecutarFragment.diaE;
import static com.example.pablo.prueba7.Fragments.EjecutarFragment.mesE;
import static com.example.pablo.prueba7.Fragments.HorasFragment.TecSecSelecc1;
import static com.example.pablo.prueba7.Fragments.Materiales.EFDM;
import static com.example.pablo.prueba7.Fragments.Materiales.EIMD;
import static com.example.pablo.prueba7.Fragments.Materiales.IFDM;
import static com.example.pablo.prueba7.Fragments.Materiales.IIDM;
import static com.example.pablo.prueba7.Fragments.Materiales.clvTipoDescMat;
import static com.example.pablo.prueba7.Fragments.Materiales.extSer;
import static com.example.pablo.prueba7.Fragments.Materiales.idInventarioMD;
import static com.example.pablo.prueba7.Fragments.Materiales.totalDM;
import static com.example.pablo.prueba7.Fragments.MaterialesFragment.EFDMR;
import static com.example.pablo.prueba7.Fragments.MaterialesFragment.EIMDR;
import static com.example.pablo.prueba7.Fragments.MaterialesFragment.IFDMR;
import static com.example.pablo.prueba7.Fragments.MaterialesFragment.IIDMR;
import static com.example.pablo.prueba7.Fragments.MaterialesFragment.clvTipoDescMatR;
import static com.example.pablo.prueba7.Fragments.MaterialesFragment.extSerR;
import static com.example.pablo.prueba7.Fragments.MaterialesFragment.idInventarioMDR;
import static com.example.pablo.prueba7.Fragments.MaterialesFragment.totalDMR;
import static com.example.pablo.prueba7.Fragments.TrabajosFragment.Clv_Sol;
import static com.example.pablo.prueba7.Request.Request.Obs;
import static com.example.pablo.prueba7.Request.Request.clvP;

import static com.example.pablo.prueba7.Activitys.asignacion.jsonArray;
import static com.example.pablo.prueba7.Activitys.asignacion.jsonObject2;
import static com.example.pablo.prueba7.Activitys.asignado.idArticuloasignado;


public class Services {
    public static int claveTecnico;
    public static int opcion;
    public static int clvorden = 0;
    public static int clavequeja = 0;
    public static String cont;
    public static JSONObject jsonObject = new JSONObject();
    JSONObject jsonObject20 = new JSONObject();
    public static JSONArray jsonArrayap = new JSONArray();

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
                .connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Servicio Tecnico///
    public Service getTecService(final Context context) throws JSONException {
        //POST Body Json
        Util.preferences = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv_Usuario", Util.getUsuarioPreference(Util.preferences));
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        try {
            final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

                @Override
                public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                    //Modificacion del Header
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", getToken(context))
                            .addHeader("Content-Type", "application/json")
                            .post(body)
                            .build();
                    return chain.proceed(newRequest);
                }
            }).connectTimeout(15, TimeUnit.MINUTES)
                    .readTimeout(15, TimeUnit.MINUTES)
                    .writeTimeout(15, TimeUnit.MINUTES)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.NEW_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(Service.class);
        } catch (Exception e) {

        }
        return null;
    }

    //Proximo Servicio/////
    public Service getProxService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clv_tecnico", Util.getClvTec(Util.preferences));
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    //Orden de servicio//
    public Service getOrdSerService(final Context context) throws JSONException {
        //POST Body Json
        Util.preferences = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clv_tecnico", Util.getClvTec(Util.preferences));
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
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

    //Lista de ordenes///
    public Service getListOrdService(final Context context) throws JSONException {
//POST Body JsonArray
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject.put("clv_tecnico", Util.getClvTec(Util.preferences));
        jsonObject.put("op", opcion);
        jsonObject.put("clv_orden", clvorden);
        jsonObject.put("contratoCom", cont);
        jsonObject2.put("ObjLista", jsonObject);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject2));
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

    //Lista de Reportes////
    public Service getListQuejasService(final Context context) throws JSONException {
        //POST Body JsonArray
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject.put("clv_tecnico", Util.getClvTec(Util.preferences));
        jsonObject.put("op", opcion);
        jsonObject.put("clv_queja", clavequeja);
        jsonObject.put("contratoCom", cont);
        jsonObject2.put("ObjLista", jsonObject);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject2));
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

    //Servicios Service//
    public Service getServiciosService(final Context context) throws JSONException {
        //POST Body Json
        Util.preferences = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Contrato", DeepConsModel.Contrato);
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

    //Informacion pantalla de ordenes//
    public Service getDeepConsService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv_Orden", clvor);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    //Informacion del cliente//
    public Service getInfoClienteService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CONTRATO", DeepConsModel.Contrato);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    //Lista de Trabajos//
    public Service getTrabajoService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv_Orden", clvor);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Tecnico Secundario//
    public Service getTecSecService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ClvOrdSer", clvor);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    // Aparatos Clientes//
    public Service getCliApaService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Contrato", DeepConsModel.Contrato);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Status Aparato//
    public Service getStatusApa(final Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("Authorization", getToken(context))
                                .build();


                        return chain.proceed(newRequest);
                    }
                }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    ///Tipo de Aparato//
    public Service getApaTipoService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ContratoNet", CambioAparato.contrato);
        jsonObject.put("Id_Articulo", CambioAparato.idArticulo);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Tipo de Aparato Disponible//
    public Service getApaTipDisService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
        jsonObject.put("Id_Articulo", CambioAparato.idArticulo2);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Cambio de Domicilio//
    public Service getCAMODOService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clv_orden", clvor);
        jsonObject.put("Clave", ClaveTrabajo);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Arbol Servicios//
    public Service getArbolSerService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clv_orden", clvor);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Medios Servicios//
    public Service getMediosSerService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ClvUnicaNet", clv_unicaNet);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Tipo Aparatos//
    public Service getTipoAparatosService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id", 0);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("obj", jsonObject);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("obj", jsonObject);
        jsonObject3.put("Lst", jsonArray);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject3));
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Aparatos Disponibles//
    public Service getAparatosDisponiblesService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clv_orden", clvor);
        jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
        jsonObject.put("idArticulo", idArticuloasignado);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Servicios Disponibles//
    public Service getServiciosAparatosService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id", idArticuloasignado);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("obj", jsonObject);
        JSONArray list = new JSONArray();
        list.put(jsonObject2);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("obj", jsonObject);
        jsonObject3.put("Lst", jsonArray);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject3));
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Tipo Aparatos//
    public Service getAceptarAsigService(final Context context) throws JSONException {
        Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = Array.dataArbSer.iterator();
        List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData.next();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id", 0);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("obj", jsonObject);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("obj", jsonObject);
        jsonObject3.put("Lst", asignacion.jsonArray2);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject3));
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Servicios Disponibles//
    public Service getExtencionAdiService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clave", ClaveTrabajo);
        jsonObject.put("Clv_Orden", clvor);
        jsonObject.put("Contrato", DeepConsModel.Contrato);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, String.valueOf(jsonObject));
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    ///Reportes///
    public Service getMediosReportes(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ContratoCom", contratoReport);
        jsonObject.put("IdUsuario", 1);
        jsonObject.put("TipoSer", 1);
        jsonObject.put("Op", 0);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //solucion///
    public Service getSolocionService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("TipoSer", 1);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //observacion y problema ///
    public Service getReporteCService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv_Queja", clvReport);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //tecnicoNombre///
    public Service getNombreService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv", clvReport);
        jsonObject.put("Op", 1);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Servicios Asignados//
    public Service getAsignadosService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Contrato", com.example.pablo.prueba7.Request.Request.abc);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //hora inicio, hora fin//
    public Service getTecSecRService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Contrato", com.example.pablo.prueba7.Request.Request.abc);
        MediaType JSON = MediaType.parse("application/json; charse=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getValidaOrdSerService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CLV_ORDEN", clvor);
        jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
        jsonObject.put("OP2", 0);
        jsonObject.put("OPCION", "M");
        jsonObject.put("STATUS", HorasFragment.statusHora);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getChecaCAMDOService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ClvOrden", clvor);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getADDRELORDUSUService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.put("ClvOrden", clvor);
        jsonObject.put("ClvUsuario", UserModel.Id_Usuario);
        jsonObject.put("Status", HorasFragment.statusHora);
        jsonObject1.put("objNueRelOrdenUsuario", jsonObject);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject1.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getDeppMODORDSERService(final Context context) throws JSONException {
        //POST Body Json
        String ab = "";
        JSONObject jsonObject = new JSONObject();
        if ((mesE+1) < 10) {
            ab = "0" + (mesE+1);
        } else {
            ab = String.valueOf(mesE+1);
        }
        String s = diaE + "-" + ab + "-" + +añoE;
        jsonObject.put("ClvFactura", DeepConsModel.Clv_FACTURA);
        jsonObject.put("ClvOrden", DeepConsModel.Clv_Orden);
        jsonObject.put("ClvTecnico", Util.getClvTec(Util.preferences));
        jsonObject.put("ClvTipSer", DeepConsModel.Clv_TipSer);
        jsonObject.put("Contrato", DeepConsModel.Contrato);
        jsonObject.put("FecEje", s);
        jsonObject.put("FecSol", DeepConsModel.Fec_Sol);
        jsonObject.put("Impresa", 1);
        jsonObject.put("ListadeArticulos", "");
        jsonObject.put("Obs", DeepConsModel.Obs);
        jsonObject.put("Status", HorasFragment.statusHora);
        jsonObject.put("TecnicoCuadrilla", InstalacionFragment.TecSecSelecc);
        jsonObject.put("Visita1", "");
        jsonObject.put("Visita2", "");

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getGuardaHoraService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv_orden", clvor);
        jsonObject.put("horaFin", EjecutarFragment.horaHoy);
        jsonObject.put("horaInicio", "08:00");
        jsonObject.put("opcion", 1);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getGuardaOrdSerAparatosService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ClvOrden", clvor);
        jsonObject.put("Op", "M");
        jsonObject.put("Status", HorasFragment.statusHora);
        jsonObject.put("Op2", 0);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service getAddLlenaBitacoraService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.put("DescripcionMov", "Se generó la");
        jsonObject.put("ClvOrden", clvor);
        jsonObject1.put("objSP_LLena_Bitacora_Ordenes", jsonObject);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject1.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    //Reportes///
    public Service getGuardaHoraReporte(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv_orden", clvReport);
        jsonObject.put("horaFin", horas12);
        jsonObject.put("horaInicio", "08:00");
        jsonObject.put("opcion", 2);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getGuardaInfoReportes(final Context context) throws JSONException {
        //POST Body Json
        JSONObject objQuejas = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        objQuejas.put("Clv_Queja", clvReport.toString());
        objQuejas.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
        objQuejas.put("FechaProceso", "");
        objQuejas.put("Fecha_Ejecucion", year + " " + horas12);
        objQuejas.put("HP", "");
        objQuejas.put("HV1", "");
        objQuejas.put("HV2", "");
        objQuejas.put("HV2", "");
        objQuejas.put("IdUsuario", 1);
        objQuejas.put("Observaciones", Obs);
        objQuejas.put("Solucion", solution);
        objQuejas.put("Status", HorasFragment.statusHora);
        objQuejas.put("TecnicoCuadrilla", TecSecSelecc1);
        objQuejas.put("Visita", false);
        objQuejas.put("Visita1", HorasFragment.reportesselectDate1.getText());
        objQuejas.put("Visita2", HorasFragment.reportesselectDate2.getText());
        objQuejas.put("Visita3", HorasFragment.reportesselectDate3.getText());
        objQuejas.put("clvPrioridadQueja", clvP);
        objQuejas.put("clvProblema", Clv_Sol);
        jsonObject.put("objQuejas", objQuejas);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getValidaInfoReportes(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ClvQueja", clvReport);
        jsonObject.put("IdUsuario", 1);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getGuardaCoordenadasService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.put("Contrato", DeepConsModel.Contrato);
        jsonObject.put("Latitud", InstalacionFragment.cordLat.getText());
        jsonObject.put("Longitud", InstalacionFragment.cordLong.getText());
        jsonObject1.put("ObjCoorCli", jsonObject);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject1.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getConsultaIpService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.put("contrato", DeepConsModel.Contrato);
        jsonObject1.put("ObjNodo", jsonObject);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject1.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service getReintentarComandoService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("contrato", DeepConsModel.Contrato);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

    public Service getCAPATService(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject.put("ClvAparato", CambioAparato.clvAparatoCAPAT);
        jsonObject.put("ClvOrden", clvor);
        jsonObject.put("ContratoNet", CambioAparato.contrato);
        jsonObject.put("Status", CambioAparato.statusAparato);
        jsonObject.put("Trabajo", "CAPAT");
        jsonObject1.put("ObjCambioAparato", jsonObject);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject1.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service getDeepCAPATService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Clv_Orden", clvor);
            jsonObject.put("Clave", ClaveTrabajo);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service recibiapar(final Context context) throws JSONException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //Log.i(jsonArrayap.get(0));
        try {
            jsonObject20.put("objSP_InsertaTbl_NoEntregados", jsonArrayap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RequestBody body = RequestBody.create(JSON, jsonObject20.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                //Modificacion del Header
                okhttp3.Request newRequest = chain.request().newBuilder()
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

    public Service getMuestraBitService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ClvTecnico", Util.getClvTec(Util.preferences));
            jsonObject.put("IdAlmacen", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service getDetalleBitService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ClvTecnico", Util.getClvTec(Util.preferences));
            jsonObject.put("ClvTipo", clvTipoDescMat);
            jsonObject.put("IdAlmacen", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }//

    public Service getChecaExtService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CLVORDEN", clvor);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service getLlenaExtService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CLV_ORDEN", clvor);
            jsonObject.put("NUMEXT", ChecaSiExtencionesModel.NUMEXT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service getTipoMatService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        try {
            jsonObject.put("catUnidadClave", 0);
            jsonObject.put("Tipo", "");
            jsonObject.put("Articulo", "");
            jsonObject.put("IdArticulo", Materiales.idArticuloDM);
            jsonObject1.put("Softv_ObtenTipoMaterialEntity", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject1.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }//

    public Service getValidaPreService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("clvOrden", clvor);
            jsonObject.put("noArticulo", idInventarioMD);
            jsonObject.put("NoExt", extSer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }//

    public Service addPreDescargaService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        int escable;
        if (com.example.pablo.prueba7.Request.Request.pieza == true) {
            escable = 0;
        } else {
            escable = 1;
        }
        try {
            jsonObject.put("contrato", DeepConsModel.Contrato);
            jsonObject.put("clvOrden", clvor);
            jsonObject.put("noArticulo", idInventarioMD);
            jsonObject.put("cantidadUtilizada", totalDM);
            jsonObject.put("clvTecnico", Util.getClvTec(Util.preferences));
            jsonObject.put("idAlmacenEmpresa", 0);
            jsonObject.put("metrajeInicio", IIDM);
            jsonObject.put("metrajeFin", IFDM);
            jsonObject.put("esCable", escable);
            jsonObject.put("tipoDescarga", tipodeDescarga);
            jsonObject.put("metrajeInicioExterior", EIMD);
            jsonObject.put("metrajeFinExterior", EFDM);
            jsonObject.put("NoExt", extSer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }//

    public Service getPreDescargaService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clvOrden", clvor);
            jsonObject.put("NoExt", extSer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }//

    //////////////////////
    public Service getDetalleBitRService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("ClvTecnico", Util.getClvTec(Util.preferences));
            jsonObject.put("ClvTipo", clvTipoDescMatR);
            jsonObject.put("IdAlmacen", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service getTipoMatRService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();

        try {
            jsonObject.put("catUnidadClave", 0);
            jsonObject.put("Tipo", "");
            jsonObject.put("Articulo", "");
            jsonObject.put("IdArticulo", MaterialesFragment.idArticuloDMR);
            jsonObject1.put("Softv_ObtenTipoMaterialEntity", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject1.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service getValidaPreRService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("clvOrden", clvor);
            jsonObject.put("noArticulo", idInventarioMDR);
            jsonObject.put("NoExt", extSerR);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service addPreDescargaRService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        int escable;
        if (com.example.pablo.prueba7.Request.Request.pieza == true) {
            escable = 0;
        } else {
            escable = 1;
        }
        try {
            jsonObject.put("contrato", DeepConsModel.Contrato);
            jsonObject.put("clvOrden", clvor);
            jsonObject.put("noArticulo", idInventarioMDR);
            jsonObject.put("cantidadUtilizada", totalDMR);
            jsonObject.put("clvTecnico", Util.getClvTec(Util.preferences));
            jsonObject.put("idAlmacenEmpresa", 0);
            jsonObject.put("metrajeInicio", IIDMR);
            jsonObject.put("metrajeFin", IFDMR);
            jsonObject.put("esCable", escable);
            jsonObject.put("tipoDescarga", tipodeDescarga);
            jsonObject.put("metrajeInicioExterior", EIMDR);
            jsonObject.put("metrajeFinExterior", EFDMR);
            jsonObject.put("NoExt", extSerR);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }

    public Service getPreDescargaRService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("clvOrden", clvor);
            jsonObject.put("NoExt", extSer);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }
    ////////////////////
    public Service getDeppMODORDSERServiceVisita(final Context context,JSONObject jsonObject) {


        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();
                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }
    ////////////////////////
    public Service addFirmaService(final Context context) {
        //POST Body Json

        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject.put("Clv_orden", clvor);
            jsonObject.put("FirmaCliente",Firma.imagenAEnviar);
            jsonObject1.put("ObjLista",jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String boundary = jsonObject1.toString().replace("\\","");
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject1.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
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
        return token;
    }

    public Service getValidaFirma(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clv_orden", clvor);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        }).connectTimeout(15, TimeUnit.MINUTES)
                .readTimeout(15, TimeUnit.MINUTES)
                .writeTimeout(15, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);
    }
    ////////////////MACWAM///////////
    public Service RequestPost(final Context context, JSONObject jsonObject) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        final RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                //Modificacion del Header
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", getToken(context))
                        .addHeader("Content-Type", "application/json")
                        .post(body)
                        .build();


                return chain.proceed(newRequest);
            }
        })      .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NEW_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(Service.class);

    }
}












