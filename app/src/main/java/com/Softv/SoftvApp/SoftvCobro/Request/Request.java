
package com.Softv.SoftvApp.SoftvCobro.Request;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Softv.SoftvApp.SoftvCobro.Activitys.Login;

import com.Softv.SoftvApp.SoftvCobro.Activitys.PDF;
import com.Softv.SoftvApp.SoftvCobro.Activitys.Saldo;
import com.Softv.SoftvApp.SoftvCobro.Activitys.ServiciosSaldo;


import com.Softv.SoftvApp.SoftvCobro.Adapters.ClientesAdapter;
import com.Softv.SoftvApp.SoftvCobro.Listas.Array;
import com.Softv.SoftvApp.SoftvCobro.Listas.HistorialDePagoList;
import com.Softv.SoftvApp.SoftvCobro.Listas.JSONPERMISOSDIRECTA;
import com.Softv.SoftvApp.SoftvCobro.Listas.JSONResponseTecnico;
import com.Softv.SoftvApp.SoftvCobro.Listas.JSONVendedores;
import com.Softv.SoftvApp.SoftvCobro.Listas.UltimoSerieYFolio;
import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelDetallesList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelHistorialDePagoList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.ModelServiciosList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.MuestraVendedoresResult;
import com.Softv.SoftvApp.SoftvCobro.Modelos.Muestra_TecnicosDescargaMaterialResult;
import com.Softv.SoftvApp.SoftvCobro.Modelos.UltimoSerieYFolioUnica;
import com.Softv.SoftvApp.SoftvCobro.Modelos.UserModel;

import com.Softv.SoftvApp.SoftvCobro.Listas.DetallesList;
import com.Softv.SoftvApp.SoftvCobro.Listas.ListaClientesSaldos;
import com.Softv.SoftvApp.SoftvCobro.Listas.ServiciosList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.DatosClientesSaldoList;
import com.Softv.SoftvApp.SoftvCobro.Modelos.Get_ClvCajeroResult;
import com.Softv.SoftvApp.SoftvCobro.R;
import com.Softv.SoftvApp.SoftvCobro.Services.Services;
import com.Softv.SoftvApp.SoftvCobro.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Constants;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Service;
import com.Softv.SoftvApp.SoftvCobro.sampledata.SplashActivity;
import com.Softv.SoftvApp.SoftvCobro.sampledata.Util;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static java.util.Arrays.asList;


public class Request extends AppCompatActivity {
    public static boolean NAP=false,TAP=false,NAPCAMDO=false,TAPCAMDO=false,Placa=false,Desc=false, SeGuarda = false;
    Services services = new Services();
    Array array = new Array();
    public static String reintentarComando, contraroMA, obsMA, obsAg, statusMA,PlacaMA, extencionesE, Obs,ObsR, msgComando = "",problemaReal;
    public static boolean isnet, firma,MACWAM,validaExisteFirmaBool;
    public static boolean PermCobro=true;
    public static boolean PermEii = false;
    public static boolean PermMarquilla = true, PermBarrio = true;
    public static String VENTA, RENTA;
    public static Long abc;
    public static int clvP, tecC, nExtenciones = 0,clvProblemarepo,ContratoReal,ClvUsuario,NoBitacora,ClvTipSerReportes, Cambio;
    public int reintentaB;
    public static String stringValidaTrabajos;
    public static ArrayAdapter adapterTecSec, adapterTecSecR, adapterNap,adapterTap,adapterColonia,adapterTAPNAPCAMDO;
    public static boolean pieza = false, rapagejecutar = false, extencionesMat = false,escable = false, ValidarPoste=false;
    public static int validFirma;
    public static String ciudadcmdo, localidadcmdo, coloniacmdo, callecmdo, numerocmdo, numeroicmdo, telefonocmdo, callencmdo, callescmdo, calleecmdo, calleocmdo, casacmdo,referenciascmd,entrecallescmd;
    public static String ejecutarStatus,reporteStatus,clasProblema;
    public static String reporteVisita1,reporteVisita2,reporteVisita3,reporteHora1,reporteHora2,reporteHora3, titul;
    public static String ContratoCompuestoSaldo,NombreSaldo,TelefonoSaldo,Calle_NumeroSaldo,ColoniaSaldo,ContratoSaldo, FechaCosultaSaldo, TotalSaldo, PlacaSaldo;
    public static  String GetTicketResult = Constants.URL_REPORTES, NombreGeneral;
    public static float Monto;
    public static Integer Session = 0, CLV_FACTURA = 0, ServicioVentas = 0, Vendedor = 0;
    private ProgressDialog dialogcarga;

    //Metodo por si existe un error en el login o inicio de sesion
    public void ErrorLogin(final Context context,ProgressDialog dialogLogin, View view) {
        EditText usurio, contraseña;
        Button entrar;
        usurio = view.findViewById(R.id.usuario);
        contraseña = view.findViewById(R.id.contrasenia);
        entrar = view.findViewById(R.id.btnLogin);
            //si el error pasa al momento de hacer el login se borran todos los datos del preference para que
            //el usuario tenga que volver a iniciar sesion
            Toast.makeText(context, "Usuario y/o Contraseña incorrecto", Toast.LENGTH_LONG).show();
            usurio.setEnabled(true);
            contraseña.setEnabled(true);
            entrar.setEnabled(true);
            dialogLogin.dismiss();

        try {
            Util.preferences.edit().clear().commit();
            SplashActivity.LoginShare = false;
        }catch (Exception e){}
    }
    public void ErrorInicioNoCoincide(final Context context,ProgressDialog dialogInicio, View view) {
        /*EditText usurio, contraseña;
        Button entrar;
        usurio = view.findViewById(R.id.usuario);
        contraseña = view.findViewById(R.id.contrasenia);
        entrar = view.findViewById(R.id.btnLogin);
        //si el error pasa al momento de hacer el login se borran todos los datos del preference para que
        //el usuario tenga que volver a iniciar sesion
        Toast.makeText(context, "Usuario y/o Contraseña incorrecto", Toast.LENGTH_LONG).show();
        usurio.setEnabled(true);
        contraseña.setEnabled(true);
        entrar.setEnabled(true);*/
        //dialogInicio.dismiss();

        try {
            Util.preferences.edit().clear().commit();
            SplashActivity.LoginShare = false;
            Intent intento = new Intent(context, Login.class);
            intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intento);
        }catch (Exception e){}
    }
    public void ErrorInicioDeSesion(final Context context, ProgressDialog dialogInicio,final Activity activity) {
            //en caso de que el erro sea al momento de abrir la aplicacion con el usuario logeado se manda un
            //mensaje de error
            dialogInicio.dismiss();
        new AlertDialog.Builder(activity,R.style.InvitationDialog)
                .setTitle("Error")
                .setMessage("Error al inciar aplicación")
                .setPositiveButton("Intentar otra vez",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intento = new Intent(context, Saldo.class);
                                intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                context.startActivity(intento);
                            }
                        })
                .setNegativeButton("Cerrar aplicación",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();

        }
        public void ErrorMensaje(final Context context,final String error){
            Toast.makeText(context, error, Toast.LENGTH_LONG).show();
        }


    //Token///
    public void getReviews(final Context context, final ProgressDialog dialogLogin, final View view, final boolean Login, final Activity activity) {
        //se inicializa la clase Service y le declaras en donde vas a guardar los datos y de que link
        //se van a obtener con los servicios 'Services'
        Services restApiAdapter = new Services();
        Service service = restApiAdapter.getClientService(context);
        Call<JsonObject> call = service.getDataUser();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            //
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //Peticion de datos sobre el Json "LogOnResult"
                //se verifica que el resultado del request sea 200=ok
                if (response.code() == 200) {
                    //inicia el preferences
                    Util.preferences = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                    Util.editor = Util.preferences.edit();
                    //se guarda el json de response en un json para sacar los datos
                    JsonObject userJson = response.body().getAsJsonObject("LogOnResult");
                    //Introduccion de datos del request en el Modelo para poder usarlos
                    UserModel user = new UserModel(
                            userJson.get("Usuario").getAsString(),
                            userJson.get("Token").getAsString(),
                            userJson.get("Codigo").getAsString(),
                            userJson.get("IdUsuario").getAsInt()
                    );

                    Util.editor.putInt("clvUsuario",user.getId_Usuario());
                    Util.editor.putString("token", user.getCodigo());
                    Util.editor.commit();
                    try{
                        //terminando el proceso de obtener token es hora de obtener la clvtecnico
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("Clv_Usuario",Util.getUsuarioPreference(Util.preferences));
                        getClv_tecnico(context,jsonObject,dialogLogin,view,Login,activity);
                    }catch (Exception e){}
                } else {
                    //en caso de ser diferente de 200 el codigo de respuesta, mandar al metodo de error
                    if(Login==true){
                        ErrorLogin(context,dialogLogin,view);
                    }else{
                        ErrorInicioNoCoincide(context,dialogLogin,view);
                    }

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                //en caso que no se pueda ejecturar el request mandar al metodo de error
                if(Login==true){
                    ErrorLogin(context,dialogLogin,view);
                }else{
                    ErrorInicioNoCoincide(context,dialogLogin,view);
                }

            }
        });
    }

    //Clave Tecnico//
    public void getClv_tecnico(final Context context, final JSONObject jsonObject, final ProgressDialog dialogLogin, final View view, final boolean Login, final Activity activity) {
        Call<JSONResponseTecnico> call = services.RequestPost(context, jsonObject).getDataTec();
        call.enqueue(new Callback<JSONResponseTecnico>() {
            @Override
            public void onResponse(Call<JSONResponseTecnico> call, Response<JSONResponseTecnico> response) {
                //Guardar Body del request en JSONResponseTecnico ya que lo regresa como una lista
                if (response.code() == 200) {
                    try {
                        Util.preferences = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                        Util.editor = Util.preferences.edit();
                        JSONResponseTecnico jsonResponse = response.body();
                        //Pide datos sobre el Json Get_ClvTecnicoResult haciendo referencia al JsonResponse donde se guardo
                        array.datatec = new ArrayList<List<Get_ClvCajeroResult>>(asList(jsonResponse.Get_ClvCajeroResult()));
                        //Se crea un Iterator con la lista para que se pueda recorrer con la informacion
                        Iterator<List<Get_ClvCajeroResult>> iteData = array.datatec.iterator();
                        while (iteData.hasNext()) {
                            List<Get_ClvCajeroResult> data = (List<Get_ClvCajeroResult>) iteData.next();
                            //se guardan los datos en el preference
                            Util.editor.putInt("clvTec", Integer.parseInt(data.get(0).clv_tecnico));
                            Util.editor.putString("nombre_Tecnico", data.get(0).getNombre_tec());
                            Util.editor.commit();

                            if(data.get(0).getNombre_tec().equals("error") && Integer.parseInt(data.get(0).clv_tecnico) == 101){
                                Toast.makeText(context, "Este usuario no puede usar esta aplicación", Toast.LENGTH_LONG).show();
                                float Error = 0/0;
                            }
                        }

                        if(Login==true){
                            Intent intento = new Intent(context, Saldo.class);
                            intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intento);
                            dialogLogin.dismiss();
                        }else{
                            try{
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("clv_tecnico", Util.getClvTec(Util.preferences));
                                //getProximaCita(context,jsonObject,view,dialogLogin,activity);
                                //getOrdenes(context,jsonObject,view,dialogLogin,activity);
                            }catch (Exception x){dialogLogin.dismiss();}
                        }
                    }catch (Exception e){
                        if(Login==true){
                            ErrorLogin(context,dialogLogin,view);
                            Toast.makeText(context, "Error al conseguir clave de caja", Toast.LENGTH_LONG).show();
                        }else{
                            ErrorInicioNoCoincide(context,dialogLogin,view);
                        }

                    }

                } else {
                    if(Login==true){
                        ErrorLogin(context,dialogLogin,view);
                        Toast.makeText(context, "Error al conseguir clave de caja", Toast.LENGTH_LONG).show();
                    }else{
                        ErrorInicioNoCoincide(context,dialogLogin,view);
                    }
                }
            }

            @Override
            public void onFailure(Call<JSONResponseTecnico> call, Throwable t) {
                if(Login==true){
                    ErrorLogin(context,dialogLogin,view);
                    Toast.makeText(context, "Error al conseguir clave de caja", Toast.LENGTH_LONG).show();
                }else{
                    ErrorInicioNoCoincide(context,dialogLogin,view);
                }
            }
        });
    }


    public void getPermisosDirecta(final Context context, final JSONObject jsonObject) {
        Call<JSONPERMISOSDIRECTA> call = services.RequestPost(context, jsonObject).getPermisosDirecta();
        call.enqueue(new Callback<JSONPERMISOSDIRECTA>() {
            @Override
            public void onResponse(Call<JSONPERMISOSDIRECTA> call, Response<JSONPERMISOSDIRECTA> response) {


                if (response.code() == 200) {
                    Util.preferences = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                    Util.editor = Util.preferences.edit();
                    JSONPERMISOSDIRECTA jsonResponse = response.body();
                    array.dataPermisosDirecta = new ArrayList<List<Muestra_TecnicosDescargaMaterialResult>>(asList(jsonResponse.Muestra_TecnicosDescargaMaterialResult()));
                    Iterator<List<Muestra_TecnicosDescargaMaterialResult>> itdata = array.dataPermisosDirecta.iterator();
                    while (itdata.hasNext()) {
                        List<Muestra_TecnicosDescargaMaterialResult> dat = itdata.next();
                        for (int i = 0; i < dat.size(); i++) {
                            if(dat.get(i).Existe==1){
                                Util.editor.putBoolean("PermisisDescarga",true);
                            }else{
                                Util.editor.putBoolean("PermisisDescarga",false);
                            }
                        }
                    }
                    Util.editor.commit();
                }else{
                    ErrorMensaje(context,"Error "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONPERMISOSDIRECTA> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
/*
    public void getListClientesSaldo(final Context applicationContext, final int i, String text) {
        Service service = null;
        try {
            service = services.getListClientesSaldoService(applicationContext, i, text);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ListaClientesSaldos> call = service.getDataListClSal();
        call.enqueue(new Callback<ListaClientesSaldos>() {
            @Override
            public void onResponse(Call<ListaClientesSaldos> call, Response<ListaClientesSaldos> response) {
                if (response.code() == 200){
                    ListaClientesSaldos jsonResponse = response.body();
                    Array.DataClientes = new ArrayList<List<DatosClientesSaldoList>>(asList(jsonResponse.DatosClientesSaldoListResult()));
                    Iterator<List<DatosClientesSaldoList>> itData = Array.DataClientes.iterator();
                    while (itData.hasNext()) {
                        List<DatosClientesSaldoList> dat = (List<DatosClientesSaldoList>) itData.next();
                        Array.ContratoCompuestoList.clear();
                        Array.NombreList.clear();
                        Array.TelefonoList.clear();
                        Array.CalleNumeroList.clear();
                        Array.ColoniaList.clear();
                        Array.ContratoList.clear();
                        Array.PlacaList.clear();
                        for (int i = 0; i < dat.size(); i++){
                            Array.ContratoCompuestoList.add(String.valueOf(dat.get(i).getContratoCompuesto()));
                            Array.NombreList.add(String.valueOf(dat.get(i).getNombre()));
                            Array.TelefonoList.add(String.valueOf(dat.get(i).getTelefono()));
                            Array.CalleNumeroList.add(String.valueOf(dat.get(i).getCalle()) + " #" + String.valueOf(dat.get(i).getNumero()));
                            Array.ColoniaList.add(String.valueOf(dat.get(i).getColonia()));
                            Array.ContratoList.add(String.valueOf(dat.get(i).getContrato()));
                            Array.PlacaList.add(String.valueOf(dat.get(i).getPlaca()));
                        }
                        if (Array.ContratoCompuestoList.size() == 0 && i==1){
                            ErrorMensaje(applicationContext,"Contrato no encontrada ");
                            //statusBusquedaOrden = false;
                        }else if (Array.NombreList.size() == 0 && i==2){
                            ErrorMensaje(applicationContext,"Nombre no encontrado ");
                            //statusBusquedaContrato = false;
                        }else if (Array.NombreList.size() == 0 && i==3){
                            ErrorMensaje(applicationContext,"Placa no encontrado ");
                            //statusBusquedaPresinto = false;
                        }

                    }
                    Saldo.adaptercl = new ClientesAdapter(applicationContext, Array.ContratoCompuestoList, Array.NombreList, Array.TelefonoList, Array.CalleNumeroList, Array.ColoniaList, Array.ContratoList, Array.PlacaList);
                    Saldo.clientList.setAdapter(Saldo.adaptercl);
                } else {
                    ErrorMensaje(applicationContext,"Error al conseguir lista de clientes "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ListaClientesSaldos> call, Throwable t) {
                ErrorMensaje(applicationContext,"Error "+t.getMessage());
            }
        });
    }*/
public void getListClientesSaldo(final Context applicationContext, final int i, String text) {
    Service service = null;
    try {
        service = services.getListClientesSaldoService(applicationContext, i, text);
    } catch (JSONException e) {
        Log.e("API_ERROR", "Error al crear JSON: " + e.getMessage());
        e.printStackTrace();
    }

    if (service == null) {
        Log.e("API_ERROR", "No se pudo crear el servicio");
        return;
    }

    Call<ListaClientesSaldos> call = service.getDataListClSal();
    call.enqueue(new Callback<ListaClientesSaldos>() {
        @Override
        public void onResponse(Call<ListaClientesSaldos> call, Response<ListaClientesSaldos> response) {
            Log.d("API_RESPONSE", "Código de respuesta: " + response.code());
            if (response.code() == 200){
                ListaClientesSaldos jsonResponse = response.body();
                Array.DataClientes = new ArrayList<List<DatosClientesSaldoList>>(asList(jsonResponse.DatosClientesSaldoListResult()));
                Iterator<List<DatosClientesSaldoList>> itData = Array.DataClientes.iterator();
                while (itData.hasNext()) {
                    List<DatosClientesSaldoList> dat = (List<DatosClientesSaldoList>) itData.next();
                    Array.ContratoCompuestoList.clear();
                    Array.NombreList.clear();
                    Array.TelefonoList.clear();
                    Array.CalleNumeroList.clear();
                    Array.ColoniaList.clear();
                    Array.ContratoList.clear();
                    Array.PlacaList.clear();
                    for (int i = 0; i < dat.size(); i++){
                        Array.ContratoCompuestoList.add(String.valueOf(dat.get(i).getContratoCompuesto()));
                        Array.NombreList.add(String.valueOf(dat.get(i).getNombre()));
                        Array.TelefonoList.add(String.valueOf(dat.get(i).getTelefono()));
                        Array.CalleNumeroList.add(String.valueOf(dat.get(i).getCalle()) + " #" + String.valueOf(dat.get(i).getNumero()));
                        Array.ColoniaList.add(String.valueOf(dat.get(i).getColonia()));
                        Array.ContratoList.add(String.valueOf(dat.get(i).getContrato()));
                        Array.PlacaList.add(String.valueOf(dat.get(i).getPlaca()));
                    }
                    if (Array.ContratoCompuestoList.size() == 0 && i==1){
                        ErrorMensaje(applicationContext,"Contrato no encontrado ");
                    }else if (Array.NombreList.size() == 0 && i==2){
                        ErrorMensaje(applicationContext,"Nombre no encontrado ");
                    }else if (Array.NombreList.size() == 0 && i==3){
                        ErrorMensaje(applicationContext,"Placa no encontrada ");
                    }
                }
                Saldo.adaptercl = new ClientesAdapter(applicationContext, Array.ContratoCompuestoList, Array.NombreList, Array.TelefonoList, Array.CalleNumeroList, Array.ColoniaList, Array.ContratoList, Array.PlacaList);
                Saldo.clientList.setAdapter(Saldo.adaptercl);
            } else {
                Log.e("API_ERROR", "Error en la respuesta. Código: " + response.code());
                Log.e("API_ERROR", "Mensaje: " + response.message());
                try {
                    Log.e("API_ERROR", "Cuerpo del error: " + response.errorBody().string());
                } catch (IOException e) {
                    Log.e("API_ERROR", "No se pudo leer el cuerpo del error");
                }
                ErrorMensaje(applicationContext,"Error al conseguir lista de clientes. Código: " + response.code());
            }
        }

        @Override
        public void onFailure(Call<ListaClientesSaldos> call, Throwable t) {
            Log.e("API_FAILURE", "La llamada falló: " + t.getMessage());
            ErrorMensaje(applicationContext,"Error de conexión: " + t.getMessage());
        }
    });
}

    public void getServiciosSaldos(final Context context, final String ContratoSaldo) {
        Service service = null;
        service = services.getServiciosSaldos(context, ContratoSaldo);
        Call<ServiciosList> call = service.getDataServiciosSaldos();
        call.enqueue(new Callback<ServiciosList>() {
            @Override
            public void onResponse(Call<ServiciosList> call, Response<ServiciosList> response) {
                if (response.code() == 200) {
                    ServiciosList jsonResponse = response.body();
                    array.DataServicios = new ArrayList<List<ModelServiciosList>>(asList(jsonResponse.GetClienteServiciosResult()));
                    Iterator<List<ModelServiciosList>> itData = array.DataServicios.iterator();
                    while (itData.hasNext()){
                        List<ModelServiciosList> dat = (List<ModelServiciosList>) itData.next();
                        Array.ServicioSaldo.clear();
                        Array.StatusSaldo.clear();
                        Array.TipServSaldo.clear();
                        for (int i = 0; i < dat.size(); i++){
                            Array.ServicioSaldo.add(String.valueOf(dat.get(i).getServicio()));
                            Array.StatusSaldo.add(String.valueOf(dat.get(i).getStatus()));
                            Array.TipServSaldo.add(String.valueOf(dat.get(i).getTipServ()));
                        }
                    }
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("Contrato", ContratoSaldo);
                        getDetallesSaldos(context, ContratoSaldo);
                    }catch (Exception x){
                        Toast toast1 = Toast.makeText(context, "Error al conseguir el saldo", Toast.LENGTH_SHORT);toast1.show();
                    }
                }else {
                    ErrorMensaje(context,"Error al conseguir lista de servicios "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ServiciosList> call, Throwable t) {

            }
        });
    }

    public void getDetallesSaldos(final Context context, final String ContratoSaldo) {
        Service service = null;
        service = services.getDetallesSaldos(context, ContratoSaldo);
        Call<DetallesList> call = service.getDataDetallesSaldosSession();
        call.enqueue(new Callback<DetallesList>() {
            @Override
            public void onResponse(Call<DetallesList> call, Response<DetallesList> response) {
                if (response.code() == 200) {
                    Monto = 0;
                    Session = 0;
                    CLV_FACTURA = 0 ;
                    DetallesList jsonResponse = response.body();
                    array.DataDetalles = new ArrayList<List<ModelDetallesList>>(asList(jsonResponse.GetClienteCobroClienteSessionResult()));
                    Iterator<List<ModelDetallesList>> itData = array.DataDetalles.iterator();
                    while (itData.hasNext()){
                        List<ModelDetallesList> dat = (List<ModelDetallesList>) itData.next();
                        Array.DescripcionSaldo.clear();
                        Array.FechaConsultaSaldo.clear();
                        Array.MontoSaldo.clear();
                        Array.OperacionSaldo.clear();

                        for (int i = 0; i < dat.size(); i++){
                            Array.DescripcionSaldo.add(String.valueOf(dat.get(i).getDescripcion()));
                            Array.FechaConsultaSaldo.add(String.valueOf(dat.get(i).getFechaConsulta()));
                            Array.MontoSaldo.add(dat.get(i).getMonto());
                            Array.OperacionSaldo.add(String.valueOf(dat.get(i).getOperacion()));
                            if(String.valueOf(dat.get(i).getOperacion()).equals("+"))
                                Monto = Monto + dat.get(i).getMonto();
                            else Monto = Monto - dat.get(i).getMonto();
                            FechaCosultaSaldo = String.valueOf(dat.get(i).getFechaConsulta());
                            Session = Integer.valueOf(dat.get(i).getSession());
                        }
                    }
                    DecimalFormat formato = new DecimalFormat(Constants.FORMATO);
                    TotalSaldo = formato.format(Monto);

                    Intent intent1 = new Intent(context, ServiciosSaldo.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
                }else {
                    ErrorMensaje(context,"Error al conseguir lista de servicios "+response.message());
                }
            }

            @Override
            public void onFailure(Call<DetallesList> call, Throwable t) {

            }

        });
    }

    public void GuardarPago(final Context context, final JSONObject jsonObject, final ProgressDialog dialogInicio){
        Call<JsonObject> call = services.RequestPost(context,jsonObject).GuardarPago();
        Log.i("PAGO",jsonObject.toString());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    //dialogInicio.dismiss();
                    try {
                        JSONObject jsonObject1 = new JSONObject(new Gson().toJson(response.body()));
                        if(jsonObject1.getInt("GuardaPagoMOVILResult")!=0){
                            CLV_FACTURA=jsonObject1.getInt("GuardaPagoMOVILResult");
                            Toast toast1 = Toast.makeText(context, "Pago registrado", Toast.LENGTH_SHORT);toast1.show();
                            try{
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("Clv_Factura", CLV_FACTURA);
                                GetTicketNom(context, jsonObject,dialogInicio);
                            }catch (Exception x){
                                Toast toast2 = Toast.makeText(context, "Error al recibir la factura", Toast.LENGTH_SHORT);
                                toast2.show();
                            }

                        }else{
                            Toast toast1 = Toast.makeText(context, "Pago NO registrado", Toast.LENGTH_SHORT);toast1.show();
                        }
                    } catch (JSONException e) {
                        dialogInicio.dismiss();
                        ErrorMensaje(context,"Error al recibir la respuesta "+response.message());
                    }

                }else{
                    dialogInicio.dismiss();
                    String errorMsg = "Error desconocido";

                    try {
                        if (response.errorBody() != null) {
                            errorMsg = response.errorBody().string();
                        }
                    } catch (Exception e) {
                        errorMsg = e.getMessage();
                    }
                    Log.e("ErrorAlGuardar",errorMsg);
                    ErrorMensaje(context,"Error al guardar el pago, verifique los datos del pago "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void GuardarAbono(final Context context, JSONObject jsonObject, final ProgressDialog dialogInicio, final float porAbonar) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).GuardarAbono();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject1 = new JSONObject(new Gson().toJson(response.body()));
                        if(jsonObject1.getInt("GuardaAbonoMOVILResult")!=0){
                            //Toast toast1 = Toast.makeText(context, "Session actualizada", Toast.LENGTH_SHORT);toast1.show();
                            try{
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("Clv_Session", Integer.valueOf(Session));
                                jsonObject.put("Contrato", Integer.valueOf(ContratoSaldo));
                                jsonObject.put("ClvUsuario", Util.getUsuarioPreference(Util.preferences));
                                jsonObject.put("Saldo", porAbonar);
                                jsonObject.put("Tipo", "C");
                                jsonObject.put("Serie_V", "");
                                jsonObject.put("Folio_V", 0);
                                jsonObject.put("Clv_Vendedor", 0);
                                jsonObject.put("Folio_VInt", 0);
                                GuardarPago(context, jsonObject,dialogInicio);
                                //dialogoGuardar(ctx);
                            }catch (Exception x){
                                Toast toast2 = Toast.makeText(context, "Error al registrar el pago", Toast.LENGTH_SHORT);
                                toast2.show();
                            }
                        }else{
                            Toast toast1 = Toast.makeText(context, "Session NO actualizada", Toast.LENGTH_SHORT);toast1.show();
                        }
                    } catch (JSONException e) {
                        ErrorMensaje(context,"Error al recibir la respuesta "+response.message());
                    }

                }else{
                    ErrorMensaje(context,"Error en la petición "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void GetTicketNom(final Context context, final JSONObject jsonObject, final ProgressDialog dialogInicio) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).GetTicketNombre();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                // ✅ LOG COMPLETO DE LA RESPUESTA
                Log.d("TICKET_DEBUG", "=== RESPUESTA DEL SERVIDOR ===");
                Log.d("TICKET_DEBUG", "Código HTTP: " + response.code());
                Log.d("TICKET_DEBUG", "Mensaje HTTP: " + response.message());
                Log.d("TICKET_DEBUG", "URL llamada: " + call.request().url());

                // ✅ LOG DEL BODY (éxito o error)
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("TICKET_DEBUG", "Body (éxito): " + response.body().toString());
                }

                // ✅ CAPTURA EL ERROR BODY (esto es clave cuando no es 200)
                if (!response.isSuccessful()) {
                    try {
                        String errorBody = response.errorBody() != null
                                ? response.errorBody().string()
                                : "errorBody vacío";
                        Log.e("TICKET_ERROR", "Error Body: " + errorBody);
                    } catch (IOException e) {
                        Log.e("TICKET_ERROR", "No se pudo leer errorBody: " + e.getMessage());
                    }
                }

                if (response.code() == 200) {
                    dialogInicio.dismiss();
                    GetTicketResult = Constants.URL_REPORTES;
                    try {
                        JSONObject jsonObject1 = new JSONObject(new Gson().toJson(response.body()));
                        Log.d("TICKET_DEBUG", "JSON parseado: " + jsonObject1.toString());

                        // Verifica si el campo existe antes de obtenerlo
                        if (!jsonObject1.has("GetTicketResult")) {
                            Log.e("TICKET_ERROR", "Campo 'GetTicketResult' NO existe en la respuesta");
                            Log.e("TICKET_ERROR", "Campos disponibles: " + jsonObject1.keys().toString());
                            ErrorMensaje(context, "Campo GetTicketResult no encontrado");
                            return;
                        }

                        String x = jsonObject1.getString("GetTicketResult");
                        Log.d("TICKET_DEBUG", "GetTicketResult valor: " + x);
                        GetTicketResult = GetTicketResult + x;

                        Intent intent1 = new Intent(context, PDF.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent1);

                    } catch (JSONException e) {
                        Log.e("TICKET_ERROR", "JSONException: " + e.getMessage(), e);
                        ErrorMensaje(context, "Error al parsear JSON: " + e.getMessage());
                    }

                } else {
                    Log.e("TICKET_ERROR", "Código inesperado: " + response.code());
                    ErrorMensaje(context, "Error al conseguir el ticket. Código: "
                            + response.code() + " | " + response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("TICKET_ERROR", "onFailure — tipo: " + t.getClass().getSimpleName());
                Log.e("TICKET_ERROR", "onFailure — mensaje: " + t.getMessage(), t);
                ErrorMensaje(context, "Error de conexión: " + t.getMessage());
            }
        });
    }

    public void getHistorialDePagos(final Context context, final String Contrato, final ProgressDialog dialog, final TextView fecha, final TextView total) {
        Service service = null;
        service = services.getHistorialDePagosHechos(context, Contrato);
        Call<HistorialDePagoList> call = service.getHistorialDePago();
        call.enqueue(new Callback<HistorialDePagoList>() {
            @Override
            public void onResponse(Call<HistorialDePagoList> call, Response<HistorialDePagoList> response) {
                if (response.code() == 200) {
                    Monto = 0;
                    HistorialDePagoList jsonResponse = response.body();
                    array.DataHistorial = new ArrayList<List<ModelHistorialDePagoList>>(asList(jsonResponse.GetHistorialDePagoResult()));
                    Iterator<List<ModelHistorialDePagoList>> itData = array.DataHistorial.iterator();
                    while (itData.hasNext()){
                        List<ModelHistorialDePagoList> dat = (List<ModelHistorialDePagoList>) itData.next();
                        Array.ContratoHistorial.clear();
                        Array.HoraConsultaHistorial.clear();
                        Array.MontoHistorial.clear();

                        for (int i = 0; i < dat.size(); i++){
                            Array.ContratoHistorial.add(String.valueOf(dat.get(i).getContrato()));
                            Array.HoraConsultaHistorial.add(String.valueOf(dat.get(i).getHoraConsulta()));
                            Array.MontoHistorial.add(dat.get(i).getMonto());
                            Monto = Monto + dat.get(i).getMonto();
                        }
                    }
                    DecimalFormat formato = new DecimalFormat(Constants.FORMATO);
                    total.setText(formato.format(Monto));
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = df.format(c.getTime());
                    fecha.setText(formattedDate);

                    String hoy = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

                    String ultima = "";
                    if (!Array.HoraConsultaHistorial.isEmpty()) {
                        ultima = Array.HoraConsultaHistorial.get(0);

                        if (ultima != null && ultima.length() >= 10) {
                            ultima = ultima.substring(0, 10);
                        }
                    }

                    fecha.setText("Hoy: " + hoy + "\nÚltimo pago: " + ultima);

                    dialog.dismiss();
                }else {
                    ErrorMensaje(context,"Error al conseguir lista de servicios "+response.message());
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<HistorialDePagoList> call, Throwable t) {

            }

        });
    }

    public void ValidarVendedor(final ProgressDialog dialogcarga, final Context applicationContext, final ConstraintLayout serieYFolio, final TextView textViewVendedor, final TextView textViewSerie, final TextView textViewFolioTv, final TextView textViewFolioInternet, final Spinner spinnerVendedor, final Spinner spinnerSerie, final Spinner spinnerFolioTv, final Spinner spinnerFolioInternet, final int Clv_Tecnico, final int Session, final Integer Contrato, final String Serie, final int Clave) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("Clv_Tecnico", Clv_Tecnico);
        }catch (Exception x){
        }
        Call<JsonObject> call = services.RequestPost(applicationContext, jsonObject).VendedorTecnico();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    int lugar=0;
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        int respuesta = jsonObject.getInt("ObtieneVendedorTecnicoResult");
                        Vendedor = respuesta;
                        if(respuesta > 0){
                            serieYFolio.setVisibility(View.VISIBLE);
                            ServicioVentas(dialogcarga, applicationContext, textViewVendedor, textViewSerie, textViewFolioTv, textViewFolioInternet, spinnerVendedor, spinnerSerie, spinnerFolioTv, spinnerFolioInternet, Clv_Tecnico, Session, Contrato, Serie, Clave);
                        }else{
                            serieYFolio.setVisibility(View.GONE);
                            dialogcarga.dismiss();
                        }
                        //dialogcarga.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ErrorMensaje(applicationContext,"Error al conseguir "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(applicationContext,"Error "+t.getMessage());
            }
        });
    }

    private void ServicioVentas(final ProgressDialog dialogcarga, final Context applicationContext, final TextView textViewVendedor, final TextView textViewSerie, final TextView textViewFolioTv, final TextView textViewFolioInternet, final Spinner spinnerVendedor, final Spinner spinnerSerie, final Spinner spinnerFolioTv, final Spinner spinnerFolioInternet, final int Clv_Tecnico, final int Session, final Integer Contrato, final String Serie, final int Clave) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("clv_session", Session);
        }catch (Exception x){
        }
        Call<JsonObject> call = services.RequestPost(applicationContext, jsonObject).ServicioVentas();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    int lugar=0;
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        int respuesta = jsonObject.getInt("DameServicioVentasAPPResult");

                        if(respuesta == 1){
                            //Vendedor
                            textViewVendedor.setVisibility(View.VISIBLE);
                            spinnerVendedor.setVisibility(View.VISIBLE);
                            //Serie
                            textViewSerie.setVisibility(View.VISIBLE);
                            spinnerSerie.setVisibility(View.VISIBLE);
                            //Folio TV / Recuperacion
                            textViewFolioTv.setText("Folio Tv: ");
                            textViewFolioTv.setVisibility(View.VISIBLE);
                            spinnerFolioTv.setVisibility(View.VISIBLE);
                            //Folio Internet
                            textViewFolioInternet.setVisibility(View.GONE);
                            spinnerFolioInternet.setVisibility(View.GONE);
                        }
                        else if(respuesta == 2){
                            //Vendedor
                            textViewVendedor.setVisibility(View.VISIBLE);
                            spinnerVendedor.setVisibility(View.VISIBLE);
                            //Serie
                            textViewSerie.setVisibility(View.VISIBLE);
                            spinnerSerie.setVisibility(View.VISIBLE);
                            //Folio TV / Recuperacion
                            textViewFolioTv.setText("Folio Tv: ");
                            textViewFolioTv.setVisibility(View.GONE);
                            spinnerFolioTv.setVisibility(View.GONE);
                            //Folio Internet
                            textViewFolioInternet.setVisibility(View.VISIBLE);
                            spinnerFolioInternet.setVisibility(View.VISIBLE);
                        }
                        else if(respuesta == 12){
                            //Vendedor
                            textViewVendedor.setVisibility(View.VISIBLE);
                            spinnerVendedor.setVisibility(View.VISIBLE);
                            //Serie
                            textViewSerie.setVisibility(View.VISIBLE);
                            spinnerSerie.setVisibility(View.VISIBLE);
                            //Folio TV / Recuperacion
                            textViewFolioTv.setText("Folio Tv: ");
                            textViewFolioTv.setVisibility(View.VISIBLE);
                            spinnerFolioTv.setVisibility(View.VISIBLE);
                            //Folio Internet
                            textViewFolioInternet.setVisibility(View.VISIBLE);
                            spinnerFolioInternet.setVisibility(View.VISIBLE);
                        }
                        else if(respuesta == 25){
                            //Vendedor
                            textViewVendedor.setVisibility(View.VISIBLE);
                            spinnerVendedor.setVisibility(View.VISIBLE);
                            //Serie
                            textViewSerie.setVisibility(View.VISIBLE);
                            spinnerSerie.setVisibility(View.VISIBLE);
                            //Folio TV / Recuperacion
                            textViewFolioTv.setText("Folio Recuperador: ");
                            textViewFolioTv.setVisibility(View.VISIBLE);
                            spinnerFolioTv.setVisibility(View.VISIBLE);
                            //Folio Internet
                            textViewFolioInternet.setVisibility(View.GONE);
                            spinnerFolioInternet.setVisibility(View.GONE);
                        }
                        else{
                            //Vendedor
                            textViewVendedor.setVisibility(View.VISIBLE);
                            spinnerVendedor.setVisibility(View.VISIBLE);
                            //Serie
                            textViewSerie.setVisibility(View.VISIBLE);
                            spinnerSerie.setVisibility(View.VISIBLE);
                            //Folio TV / Recuperacion
                            textViewFolioTv.setText("Folio Recuperador: ");
                            textViewFolioTv.setVisibility(View.VISIBLE);
                            spinnerFolioTv.setVisibility(View.VISIBLE);
                            //Folio Internet
                            textViewFolioInternet.setVisibility(View.VISIBLE);
                            spinnerFolioInternet.setVisibility(View.VISIBLE);
                        }

                        ServicioVentas = respuesta;

                        MuestraVendedores(applicationContext, spinnerVendedor, Clave, Contrato,spinnerSerie,spinnerFolioTv,spinnerFolioInternet);

                        dialogcarga.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    ErrorMensaje(applicationContext,"Error al conseguir "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(applicationContext,"Error "+t.getMessage());
            }
        });
    }

    private void MuestraVendedores(final Context applicationContext, final Spinner spinnerVendedor, int clave, Integer contrato, final Spinner spinnerSerie, final Spinner spinnerFolioTv, final Spinner spinnerFolioInternet) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("ClvUsuario", clave);
            jsonObject.put("CONTRATO", contrato);
        }catch (Exception x){
        }
        Call<JSONVendedores> call = services.RequestPost(applicationContext, jsonObject).Vendedores();
        call.enqueue(new Callback<JSONVendedores>() {
            @Override
            public void onResponse(Call<JSONVendedores> call, Response<JSONVendedores> response) {
                if (response.code() == 200) {
                    JSONVendedores jsonResponse = response.body();
                    Array.dataVendedores = new ArrayList<List<MuestraVendedoresResult>>(asList(jsonResponse.MuestraVendedoresResult()));
                    Iterator<List<MuestraVendedoresResult>> itData = Array.dataVendedores.iterator();
                    Array.vendedoresLista.clear();
                    Array.vendedoresLista.add("<Seleccionar>");
                    while (itData.hasNext()){
                        List<MuestraVendedoresResult> dat = (List<MuestraVendedoresResult>) itData.next();
                        for (int i = 0; i < dat.size(); i++) {
                            Array.vendedoresLista.add(dat.get(i).getNombre());
                        }
                    }
                    spinnerVendedor.setAdapter(new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, Array.vendedoresLista));
                    spinnerVendedor.setSelection(0);
                } else {
                    ErrorMensaje(applicationContext,"Error al conseguir "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONVendedores> call, Throwable throwable) {
                ErrorMensaje(applicationContext,"Error al conseguir "+throwable.getMessage());
            }
        });
    }

    public void UltimoSerieYFolio(final ProgressDialog dialogInicio,final  Context applicationContext,final  JSONObject jsonObject,final  Spinner spinnerSerie,final  Spinner spinnerFolioTv,final  Spinner spinnerFolioInternet) {
        Call<UltimoSerieYFolio> call = services.RequestPost(applicationContext, jsonObject).SerieYFolio();
        call.enqueue(new Callback<UltimoSerieYFolio>() {
            @Override
            public void onResponse(Call<UltimoSerieYFolio> call, Response<UltimoSerieYFolio> response) {
                if (response.code() == 200) {
                    UltimoSerieYFolio jsonResponse = response.body();
                    Array.dataUltimoSerieYFolio = new ArrayList<List<UltimoSerieYFolioUnica>>(asList(jsonResponse.UltimoSerieYFolioUnicaResult()));
                    Iterator<List<UltimoSerieYFolioUnica>> itData = Array.dataUltimoSerieYFolio.iterator();
                    Array.UltimoSerieYFolioLista.clear();
                    Array.UltimoSerieYFolioLista.add("<Seleccionar>");
                    while (itData.hasNext()){
                        List<UltimoSerieYFolioUnica> dat = (List<UltimoSerieYFolioUnica>) itData.next();
                        for (int i = 0; i < dat.size(); i++) {
                            Array.UltimoSerieYFolioLista.add(dat.get(i).getSerie());
                        }
                    }
                    spinnerSerie.setAdapter(new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, Array.UltimoSerieYFolioLista));
                    spinnerSerie.setSelection(0);
                    dialogInicio.dismiss();
                    //Folio_Disponible(dialogInicio, applicationContext);

                } else {
                    ErrorMensaje(applicationContext,"Error al conseguir "+response.message());
                }
            }

            @Override
            public void onFailure(Call<UltimoSerieYFolio> call, Throwable throwable) {
                ErrorMensaje(applicationContext,"Error al conseguir "+throwable.getMessage());
            }
        });
    }

    public void Folio_DisponibleRecu(final ProgressDialog dialogInicio, final  Context applicationContext, final  JSONObject jsonObject1, final  Spinner spinnerFolioTv, final  Spinner spinnerFolioInternet) {
        Call<JsonObject> call = services.RequestPost(applicationContext, jsonObject1).FolioDisponibleR();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        Array.dataFolioDisponibleRecu = jsonObject.getJSONArray("FolioDisponibleRecuResult");

                        Array.FolioDisponibleRecuLista.clear();
                        Array.FolioDisponibleRecuLista.add("<Seleccionar>");

                        for (int i = 0; i < Array.dataFolioDisponibleRecu.length(); i++){
                            Array.FolioDisponibleRecuLista.add(String.valueOf(Array.dataFolioDisponibleRecu.get(i)));
                        }

                        spinnerFolioTv.setAdapter(new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, Array.FolioDisponibleRecuLista));
                        spinnerFolioTv.setSelection(0);
                        Folio_DisponibleInt(dialogInicio, applicationContext, jsonObject1, spinnerFolioInternet);
                        //dialogInicio.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else{
                    ErrorMensaje(applicationContext,"Error al conseguir "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                ErrorMensaje(applicationContext,"Error al conseguir "+throwable.getMessage());
            }
        });
    }

    public void Folio_Disponible(final ProgressDialog dialogInicio, final  Context applicationContext, final  JSONObject jsonObject1, final  Spinner spinnerFolioTv, final  Spinner spinnerFolioInternet) {
        Call<JsonObject> call = services.RequestPost(applicationContext, jsonObject1).FolioDisponible();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        Array.dataFolioDisponible = jsonObject.getJSONArray("FolioDisponibleResult");

                        Array.FolioDisponibleLista.clear();
                        Array.FolioDisponibleLista.add("<Seleccionar>");

                        for (int i = 0; i < Array.dataFolioDisponible.length(); i++){
                            Array.FolioDisponibleLista.add(String.valueOf(Array.dataFolioDisponible.get(i)));
                        }

                        spinnerFolioTv.setAdapter(new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, Array.FolioDisponibleLista));
                        spinnerFolioTv.setSelection(0);
                        Folio_DisponibleInt(dialogInicio, applicationContext, jsonObject1, spinnerFolioInternet);
                        //dialogInicio.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else{
                    ErrorMensaje(applicationContext,"Error al conseguir "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                ErrorMensaje(applicationContext,"Error al conseguir "+throwable.getMessage());
            }
        });
    }

    public void Folio_DisponibleInt(final ProgressDialog dialogInicio, final  Context applicationContext, final  JSONObject jsonObject, final  Spinner spinnerFolioInternet) {
        Call<JsonObject> call = services.RequestPost(applicationContext, jsonObject).FolioDisponibleI();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        Array.dataFolioDisponibleInt = jsonObject.getJSONArray("FolioDisponibleIntResult");

                        Array.FolioDisponibleIntLista.clear();
                        Array.FolioDisponibleIntLista.add("<Seleccionar>");

                        for (int i = 0; i < Array.dataFolioDisponibleInt.length(); i++){
                            Array.FolioDisponibleIntLista.add(String.valueOf(Array.dataFolioDisponibleInt.get(i)));
                        }

                        spinnerFolioInternet.setAdapter(new ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_dropdown_item, Array.FolioDisponibleIntLista));
                        spinnerFolioInternet.setSelection(0);
                        //Folio_DisponibleInt(dialogInicio, applicationContext, jsonObject, spinnerFolioInternet);
                        dialogInicio.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else{
                    ErrorMensaje(applicationContext,"Error al conseguir "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable throwable) {
                ErrorMensaje(applicationContext,"Error al conseguir "+throwable.getMessage());
            }
        });
    }

}