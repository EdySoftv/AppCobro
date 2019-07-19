package com.example.pablo.prueba7.Services;


import android.content.Context;
import android.util.Log;

import com.example.pablo.prueba7.Activitys.CambioAparato;
import com.example.pablo.prueba7.Activitys.MainActivity;
import com.example.pablo.prueba7.Dibujo.Firma;
import com.example.pablo.prueba7.Fragments.EjecutarOrdenes;
import com.example.pablo.prueba7.Fragments.HorasReportes;
import com.example.pablo.prueba7.Fragments.HorasOrdenes;

import com.example.pablo.prueba7.Fragments.MaterialesOrdenes;
import com.example.pablo.prueba7.Fragments.MaterialesReportes;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Modelos.ChecaSiExtencionesModel;
import com.example.pablo.prueba7.Modelos.DeepConsModel;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.example.pablo.prueba7.Modelos.UserModel;
import com.example.pablo.prueba7.Activitys.ServiciosAInstalar;
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




import static com.example.pablo.prueba7.Adapters.QuejasAdapter.clvReport;
import static com.example.pablo.prueba7.Adapters.QuejasAdapter.contratoReport;
import static com.example.pablo.prueba7.Adapters.TrabajosAdapter.ClaveTrabajo;

import static com.example.pablo.prueba7.Fragments.EjecutarReportes.horas12;

import static com.example.pablo.prueba7.Fragments.EjecutarReportes.solution;
import static com.example.pablo.prueba7.Fragments.EjecutarReportes.year;
import static com.example.pablo.prueba7.Fragments.EjecutarOrdenes.añoE;
import static com.example.pablo.prueba7.Fragments.EjecutarOrdenes.diaE;
import static com.example.pablo.prueba7.Fragments.EjecutarOrdenes.mesE;
import static com.example.pablo.prueba7.Fragments.HorasReportes.TecSecSelecc1;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.EFDM;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.EIMD;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.IFDM;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.IIDM;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.clvTipoDescMat;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.extSer;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.idInventarioMD;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.totalDM;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.EFDMR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.EIMDR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.IFDMR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.IIDMR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.clvTipoDescMatR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.extSerR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.idInventarioMDR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.totalDMR;
import static com.example.pablo.prueba7.Fragments.TrabajosReportes.Clv_Sol;
import static com.example.pablo.prueba7.Request.Request.Obs;
import static com.example.pablo.prueba7.Request.Request.clvP;


public class Services {

    public static int opcion;
    public static int clvorden = 0;
    public static int clavequeja = 0;
    public static String cont;
    public static JSONObject jsonObject = new JSONObject();
    JSONObject jsonObject20 = new JSONObject();
    public static long ClvTrabajoRequest;
    public static String ejecutarStatus;
    public static JSONArray jsonArrayap = new JSONArray();
   // public static JSONArray jsonTokenFirebase = new JSONArray();
    public static JSONObject jsonTokenFirebase = new JSONObject();

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
        jsonObject.put("Clv_Orden",  Util.getClvOrden(Util.preferences));
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

    //Lista de TrabajosOrdenes//
    public Service getTrabajoService(final Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Clv_Orden",  Util.getClvOrden(Util.preferences));
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
        jsonObject.put("ClvOrdSer",  Util.getClvOrden(Util.preferences));
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
        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
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
        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
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
    public Service getAceptarAsigService(final Context context) throws JSONException {
        Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData = Array.dataArbSer.iterator();
        List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData.next();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Id", 0);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("obj", jsonObject);
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("obj", jsonObject);
        jsonObject3.put("Lst", ServiciosAInstalar.jsonArray2);
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
        jsonObject.put("Clv_Orden",  Util.getClvOrden(Util.preferences));
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
        jsonObject.put("Clv_Queja", Util.getClvQueja(Util.preferences));
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
        jsonObject.put("Clv", Util.getClvQueja(Util.preferences));
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

        String ejecutar;
        if(EjecutarOrdenes.ejecutarStatus.equals(null)){
            ejecutar=ejecutarStatus;
        }else{
            ejecutar = EjecutarOrdenes.ejecutarStatus;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
        jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
        jsonObject.put("OP2", 0);
        jsonObject.put("OPCION", "M");
        jsonObject.put("STATUS", ejecutar);
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
        jsonObject.put("ClvOrden",  Util.getClvOrden(Util.preferences));
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
        jsonObject.put("ClvOrden",  Util.getClvOrden(Util.preferences));
        jsonObject.put("ClvUsuario", UserModel.Id_Usuario);
        jsonObject.put("Status", HorasReportes.statusHora);
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

        if ((mesE+1) < 10) {
            ab = "0" + (mesE+1);
        } else {
            ab = String.valueOf(mesE+1);
        }
        String s = diaE + "-" + ab + "-" + +añoE;


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
        jsonObject.put("Clv_orden",  Util.getClvOrden(Util.preferences));
        jsonObject.put("horaFin", EjecutarOrdenes.horaFin);
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
        jsonObject.put("ClvOrden",  Util.getClvOrden(Util.preferences));
        jsonObject.put("Op", "M");
        jsonObject.put("Status", HorasReportes.statusHora);
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
        jsonObject.put("ClvOrden",  Util.getClvOrden(Util.preferences));
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
        jsonObject.put("Clv_orden", Util.getClvQueja(Util.preferences));
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
        objQuejas.put("Clv_Queja", String.valueOf(Util.getClvQueja(Util.preferences)));
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
        objQuejas.put("Status", HorasReportes.statusHora);
        objQuejas.put("TecnicoCuadrilla", TecSecSelecc1);
        objQuejas.put("Visita", false);
        objQuejas.put("Visita1", "");
        objQuejas.put("Visita2", "");
        objQuejas.put("Visita3", "");
        objQuejas.put("clvPrioridadQueja", clvP);
        objQuejas.put("clvProblema",ClvTrabajoRequest );
        objQuejas.put("clvProblemaS", Clv_Sol);
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
        jsonObject.put("ClvQueja", Util.getClvQueja(Util.preferences));
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
        jsonObject.put("Latitud", HorasOrdenes.cordLat.getText());
        jsonObject.put("Longitud", HorasOrdenes.cordLong.getText());
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



    public Service getDeepCAPATService(final Context context) {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("Clv_Orden",  Util.getClvOrden(Util.preferences));
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

    public Service enviocClvTecnicoToken(final Context context) throws JSONException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");


        final RequestBody body = RequestBody.create(JSON, jsonTokenFirebase.toString());
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
            jsonObject.put("CLVORDEN",  Util.getClvOrden(Util.preferences));
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
            jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
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
            jsonObject.put("IdArticulo", MaterialesOrdenes.idArticuloDM);
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
            jsonObject.put("clvOrden",  Util.getClvOrden(Util.preferences));
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
            jsonObject.put("clvOrden",  Util.getClvOrden(Util.preferences));
            jsonObject.put("noArticulo", idInventarioMD);
            jsonObject.put("cantidadUtilizada", totalDM);
            jsonObject.put("clvTecnico", Util.getClvTec(Util.preferences));
            jsonObject.put("idAlmacenEmpresa", 0);
            jsonObject.put("metrajeInicio", IIDM);
            jsonObject.put("metrajeFin", IFDM);
            jsonObject.put("esCable", escable);
            jsonObject.put("tipoDescarga", Util.getTipoDescarga(Util.preferences));
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
            jsonObject.put("clvOrden",  Util.getClvOrden(Util.preferences));
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
            jsonObject.put("IdArticulo", MaterialesReportes.idArticuloDMR);
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
            jsonObject.put("clvOrden",  Util.getClvQueja(Util.preferences));
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
            jsonObject.put("clvOrden",  Util.getClvQueja(Util.preferences));
            jsonObject.put("noArticulo", idInventarioMDR);
            jsonObject.put("cantidadUtilizada", totalDMR);
            jsonObject.put("clvTecnico", Util.getClvTec(Util.preferences));
            jsonObject.put("idAlmacenEmpresa", 0);
            jsonObject.put("metrajeInicio", IIDMR);
            jsonObject.put("metrajeFin", IFDMR);
            jsonObject.put("esCable", escable);
            jsonObject.put("tipoDescarga", Util.getTipoDescarga(Util.preferences));
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
            jsonObject.put("clvOrden",  Util.getClvQueja(Util.preferences));
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
    /*public Service addFirmaService(final Context context) {
        //POST Body Json


        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonObject1 = new JSONObject();
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
    }*/
    public String getToken(final Context context) {
        String token;
        Util.preferences = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        token = Util.getTokenPreference(Util.preferences);
        Log.d("token", token);
        return token;
    }

    public Service getValidaFirma(final Context context) throws JSONException {
        //POST Body Json
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clv_orden",  Util.getClvOrden(Util.preferences));
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
                        .addHeader("Authorization", Util.getTokenPreference(Util.preferences))
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












