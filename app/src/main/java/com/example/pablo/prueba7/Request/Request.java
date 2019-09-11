package com.example.pablo.prueba7.Request;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo.prueba7.Activitys.AsignarAparato;
import com.example.pablo.prueba7.Activitys.Orden;
import com.example.pablo.prueba7.Activitys.CambioDom;

import com.example.pablo.prueba7.Activitys.CambioAparato;
import com.example.pablo.prueba7.Activitys.ReporteAsignacion;
import com.example.pablo.prueba7.Activitys.Reportes;
import com.example.pablo.prueba7.Adapters.ArbolAdapter;
import com.example.pablo.prueba7.Adapters.EliminarMaterialAdapter;
import com.example.pablo.prueba7.Adapters.GraficaAdapter;
import com.example.pablo.prueba7.Adapters.OrdenesAdapter;
import com.example.pablo.prueba7.Adapters.TablaAdapter;
import com.example.pablo.prueba7.Adapters.TrabajosAdapter;
import com.example.pablo.prueba7.Fragments.EjecutarOrdenes;
import com.example.pablo.prueba7.Activitys.ExtensionesAdi;
import com.example.pablo.prueba7.Fragments.EjecutarReportes;
import com.example.pablo.prueba7.Fragments.HorasReportes;


import com.example.pablo.prueba7.Activitys.Inicio;
import com.example.pablo.prueba7.Fragments.HorasOrdenes;
import com.example.pablo.prueba7.Fragments.MaterialesOrdenes;
import com.example.pablo.prueba7.Fragments.MaterialesReportes;
import com.example.pablo.prueba7.Listas.Array;
import com.example.pablo.prueba7.Listas.Example;
import com.example.pablo.prueba7.Listas.Example1;
import com.example.pablo.prueba7.Listas.Example2;
import com.example.pablo.prueba7.Listas.Example3;
import com.example.pablo.prueba7.Listas.JSONApaTipDis;
import com.example.pablo.prueba7.Listas.JSONApaTipo;
import com.example.pablo.prueba7.Listas.JSONAparatosDisponibles;
import com.example.pablo.prueba7.Listas.JSONArbolServicios;
import com.example.pablo.prueba7.Listas.JSONCAMDO;
import com.example.pablo.prueba7.Listas.JSONCLIAPA;
import com.example.pablo.prueba7.Listas.JSONDescripcionArticulosBit;
import com.example.pablo.prueba7.Listas.JSONDetalleBitacora;
import com.example.pablo.prueba7.Listas.JSONGETNAP;
import com.example.pablo.prueba7.Listas.JSONLlenaExtenciones;
import com.example.pablo.prueba7.Listas.JSONMediosSer;
import com.example.pablo.prueba7.Listas.JSONNombreTecnico;
import com.example.pablo.prueba7.Listas.JSONPreDescarga;
import com.example.pablo.prueba7.Listas.JSONPregunta;
import com.example.pablo.prueba7.Listas.JSONReporteCliente;
import com.example.pablo.prueba7.Listas.JSONReportes;
import com.example.pablo.prueba7.Listas.JSONResponseTecnico;
import com.example.pablo.prueba7.Listas.JSONServicioAsignado;
import com.example.pablo.prueba7.Listas.JSONServiciosAparatos;
import com.example.pablo.prueba7.Listas.JSONSolucion;
import com.example.pablo.prueba7.Listas.JSONStatusApa;
import com.example.pablo.prueba7.Listas.JSONTAP;
import com.example.pablo.prueba7.Listas.JSONTecSec;
import com.example.pablo.prueba7.Listas.JSONTecSecReport;
import com.example.pablo.prueba7.Listas.JSONTipoAparatos;
import com.example.pablo.prueba7.Listas.QuejasList;
import com.example.pablo.prueba7.Activitys.Login;
import com.example.pablo.prueba7.Activitys.MainActivity;
import com.example.pablo.prueba7.Activitys.MainReportes;
import com.example.pablo.prueba7.Modelos.CambioAparatoDeepModel;
import com.example.pablo.prueba7.Modelos.ChecaSiExtencionesModel;
import com.example.pablo.prueba7.Modelos.DeepConsModel;
import com.example.pablo.prueba7.Modelos.DescripcionArticuloModel;
import com.example.pablo.prueba7.Modelos.DetalleBitacoraModel;
import com.example.pablo.prueba7.Modelos.GetBUSCADetOrdSerListResult;
import com.example.pablo.prueba7.Modelos.GetCheca_si_tiene_CAMDOModel;
import com.example.pablo.prueba7.Modelos.GetConTecnicoAgendaResult;
import com.example.pablo.prueba7.Modelos.GetDameDatosCAMDOResult;
import com.example.pablo.prueba7.Modelos.GetDameListadoOrdenesAgendadasResult;
import com.example.pablo.prueba7.Modelos.GetDameSerDelCliFacListResult;
import com.example.pablo.prueba7.Modelos.GetDeepValidaQuejaCompaniaAdicModel;
import com.example.pablo.prueba7.Modelos.GetListAparatosDisponiblesByIdArticuloResult;
import com.example.pablo.prueba7.Modelos.GetListClienteAparatosResult;
import com.example.pablo.prueba7.Modelos.GetListTipoAparatosByIdArticuloResult;
import com.example.pablo.prueba7.Modelos.GetMACWAMModel;
import com.example.pablo.prueba7.Modelos.GetMUESTRATRABAJOSQUEJASListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraAparatosDisponiblesListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraMedioPorServicoContratadoListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraRelOrdenesTecnicosListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraServiciosRelTipoAparatoListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraTecnicosAlmacenListResult;
import com.example.pablo.prueba7.Modelos.GetMuestraTipoAparatoListResult;
import com.example.pablo.prueba7.Modelos.GetQuejasListResult;
import com.example.pablo.prueba7.Modelos.GetSP_StatusAparatosListResult;
import com.example.pablo.prueba7.Modelos.Get_ClvTecnicoResult;
import com.example.pablo.prueba7.Modelos.GetdameSerDELCliresumenResult;
import com.example.pablo.prueba7.Modelos.GetuspBuscaContratoSeparado2ListResult;
import com.example.pablo.prueba7.Modelos.InfoClienteModelo;
import com.example.pablo.prueba7.Modelos.ListadoQuejasAgendadas;
import com.example.pablo.prueba7.Modelos.LlenaExtencionesModel;
import com.example.pablo.prueba7.Modelos.ObtieneNapModel;
import com.example.pablo.prueba7.Modelos.ObtieneTapModel;
import com.example.pablo.prueba7.Modelos.OrdSer;
import com.example.pablo.prueba7.Modelos.RequierePregunta;
import com.example.pablo.prueba7.Modelos.ValidaMACWAMMODEL;
import com.example.pablo.prueba7.Modelos.ValidacionFirma;
import com.example.pablo.prueba7.Modelos.dameTblPreDescargaMaterialResultModel;
import com.example.pablo.prueba7.Modelos.ProximaCitaModel;
import com.example.pablo.prueba7.Modelos.Queja;
import com.example.pablo.prueba7.Modelos.TipoMaterialModel;
import com.example.pablo.prueba7.Modelos.UserModel;
import com.example.pablo.prueba7.Modelos.mediosPregunta;
import com.example.pablo.prueba7.R;
import com.example.pablo.prueba7.Services.Services;
import com.example.pablo.prueba7.Fragments.TrabajosReportes;
import com.example.pablo.prueba7.Activitys.ServiciosAInstalar;
import com.example.pablo.prueba7.sampledata.BarraCargar;
import com.example.pablo.prueba7.sampledata.Service;
import com.example.pablo.prueba7.sampledata.SplashActivity;
import com.example.pablo.prueba7.sampledata.Util;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pablo.prueba7.Activitys.CambioAparato.dialogCAPAT;
import static com.example.pablo.prueba7.Activitys.Orden.statusBusquedaContrato;
import static com.example.pablo.prueba7.Activitys.Orden.statusBusquedaOrden;
import static com.example.pablo.prueba7.Activitys.Reportes.statusBusquedaContRepo;
import static com.example.pablo.prueba7.Activitys.Reportes.statusBusquedaReporte;
import static com.example.pablo.prueba7.Activitys.ServiciosAInstalar.dialogAsignacion;
import static com.example.pablo.prueba7.Activitys.AsignarAparato.MACWAMText;
import static com.example.pablo.prueba7.Activitys.AsignarAparato.constraintLayoutMACWAM;
import static com.example.pablo.prueba7.Activitys.AsignarAparato.idArticuloasignado;
import static com.example.pablo.prueba7.Activitys.AsignarAparato.jsonArrayMAC;

import static com.example.pablo.prueba7.Adapters.QuejasAdapter.clvReport;
import static com.example.pablo.prueba7.Adapters.QuejasAdapter.statusQueja;
import static com.example.pablo.prueba7.Adapters.TrabajosAdapter.dialogTrabajos;
import static com.example.pablo.prueba7.Fragments.EjecutarOrdenes.TecSec;
import static com.example.pablo.prueba7.Fragments.EjecutarOrdenes.dialogEjecutar;
import static com.example.pablo.prueba7.Fragments.EjecutarOrdenes.ejecutar;
import static com.example.pablo.prueba7.Fragments.EjecutarOrdenes.posTec;
import static com.example.pablo.prueba7.Fragments.EjecutarReportes.TecSecSeleccion;
import static com.example.pablo.prueba7.Fragments.EjecutarReportes.TecSecu;
import static com.example.pablo.prueba7.Fragments.EjecutarReportes.dialogReportes;
import static com.example.pablo.prueba7.Fragments.EjecutarReportes.fechaEjecujtar;
import static com.example.pablo.prueba7.Fragments.EjecutarReportes.horaEjecutar;
import static com.example.pablo.prueba7.Fragments.EjecutarReportes.tecSecPosRepo;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.dialogVisitaOrd;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.ejecutada;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.observacionesTecnico;
import static com.example.pablo.prueba7.Fragments.HorasOrdenes.visita;
import static com.example.pablo.prueba7.Fragments.HorasReportes.TecSecSelecc1;
import static com.example.pablo.prueba7.Fragments.HorasReportes.dialogVisitaRepo;
import static com.example.pablo.prueba7.Fragments.HorasReportes.reporteEjecutada;
import static com.example.pablo.prueba7.Fragments.HorasReportes.repotteVisita;
import static com.example.pablo.prueba7.Fragments.HorasReportes.tecPosRepo;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.clasificacionMat;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.descripcionMat;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.posClasMat;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.posDescMat;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.posExtMat;
import static com.example.pablo.prueba7.Fragments.MaterialesOrdenes.spinnerExtMat;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.clasificacionMatR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.descripcionMatR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.posClasMatR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.posExtMatR;
import static com.example.pablo.prueba7.Fragments.MaterialesReportes.spinnerExtMatR;
import static com.example.pablo.prueba7.Fragments.TrabajosOrdenes.adaptertrabajos;
import static com.example.pablo.prueba7.Fragments.TrabajosOrdenes.trabajos;
import static com.example.pablo.prueba7.Fragments.TrabajosReportes.Clv_Sol;
import static com.example.pablo.prueba7.Fragments.TrabajosReportes.posSolucionRepo;
import static com.example.pablo.prueba7.Fragments.TrabajosReportes.proble;
import static com.example.pablo.prueba7.Listas.Array.Asigna;
import static com.example.pablo.prueba7.Listas.Array.Asigna1;
import static com.example.pablo.prueba7.Fragments.TrabajosReportes.solucion;
import static com.example.pablo.prueba7.Listas.Array.contratoQ;
import static com.example.pablo.prueba7.Listas.Array.contratosrc;
import static com.example.pablo.prueba7.Listas.Array.ordensrc;
import static com.example.pablo.prueba7.Services.Services.ClvTrabajoRequest;
import static com.example.pablo.prueba7.Services.Services.clavequeja;
import static com.example.pablo.prueba7.Services.Services.opcion;
import static java.util.Arrays.asList;

public class Request extends AppCompatActivity {
    public static boolean NAP=false,TAP=false;
    Services services = new Services();
    Array array = new Array();
    public static String reintentarComando, contraroMA, obsMA, statusMA, extencionesE, Obs,ObsR, msgComando = "",problemaReal;
    public static boolean isnet, firma,MACWAM,validaExisteFirmaBool;
    public static Long abc;
    public static int clvP, tecC, nExtenciones = 0,clvProblemarepo,ContratoReal;
    public int reintentaB;
    public static String stringValidaTrabajos;
    public static ArrayAdapter adapterTecSec, adapterTecSecR, adapterNap,adapterTap;
    public static boolean pieza = false, rapagejecutar = false, extencionesMat = false;
   public static int validFirma;
    public static String ciudadcmdo, localidadcmdo, coloniacmdo, callecmdo, numerocmdo, numeroicmdo, telefonocmdo, callencmdo, callescmdo, calleecmdo, calleocmdo, casacmdo;
    public static String ejecutarStatus,reporteStatus,clasProblema;
    public static String reporteVisita1,reporteVisita2,reporteVisita3,reporteHora1,reporteHora2,reporteHora3;
    JsonObject jsonConsultaIp;
    String a = "Seleccione técnico secundario";
    String f = "Seleccione tipo de solución";
    public static String datos[], datosTap[],datosNap[];
    BarraCargar barraCargar = new BarraCargar();
    public static boolean requierePregunta=false;

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
                                Intent intento = new Intent(context, Inicio.class);
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
    public void getReviews(final Context context, final ProgressDialog dialogLogin, final View view) {
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
                    Util.editor.putString("token", user.getCodigo());
                    Util.editor.commit();
                    try{
                        //terminando el proceso de obtener token es hora de obtener la clvtecnico
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("Clv_Usuario",Util.getUsuarioPreference(Util.preferences));
                        getClv_tecnico(context,jsonObject,dialogLogin,view);
                    }catch (Exception e){}
                } else {
                    //en caso de ser diferente de 200 el codigo de respuesta, mandar al metodo de error
                    ErrorLogin(context,dialogLogin,view);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                //en caso que no se pueda ejecturar el request mandar al metodo de error
                ErrorLogin(context,dialogLogin,view);
            }
        });
    }

    //Clave Tecnico//
    public void getClv_tecnico(final Context context, final JSONObject jsonObject, final ProgressDialog dialogLogin, final View view) {
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
                        array.datatec = new ArrayList<List<Get_ClvTecnicoResult>>(asList(jsonResponse.Get_ClvTecnicoResult()));
                        //Se crea un Iterator con la lista para que se pueda recorrer con la informacion
                        Iterator<List<Get_ClvTecnicoResult>> iteData = array.datatec.iterator();
                        while (iteData.hasNext()) {
                            List<Get_ClvTecnicoResult> data = (List<Get_ClvTecnicoResult>) iteData.next();
                            //se guardan los datos en el preference
                            Util.editor.putInt("clvTec", Integer.parseInt(data.get(0).clv_tecnico));
                            Util.editor.putString("nombre_Tecnico", data.get(0).getNombre_tec());
                            Util.editor.commit();
                        }
                        Intent intento = new Intent(context, Inicio.class);
                        intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intento);
                        dialogLogin.dismiss();
                    }catch (Exception e){
                        ErrorLogin(context,dialogLogin,view);
                        Toast.makeText(context, "Error al conseguir clave técnico", Toast.LENGTH_LONG).show();
                    }

                } else {
                    ErrorLogin(context,dialogLogin,view);
                    Toast.makeText(context, "Error al conseguir clave técnico", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JSONResponseTecnico> call, Throwable t) {
                ErrorLogin(context,dialogLogin,view);
                Toast.makeText(context, "Error al conseguir clave técnico", Toast.LENGTH_LONG).show();
            }
        });
    }

    //Proxima Cita//
    public void getProximaCita(final Context context, final JSONObject jsonObject, final View view, final ProgressDialog dialogInicio, final Activity activity) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getDataProx();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    TextView tipoTrabajo, contratoTrabajo, horaTrabajo, calleDireccion, numeroDireccion, coloniaDireccion;
                    JsonObject userJson = response.body().getAsJsonObject("GetDameSiguienteCitaResult");
                    String jsonToString = String.valueOf(userJson);
                    ProximaCitaModel proximaCitaModel = new ProximaCitaModel();
                    Gson gson = new Gson();
                    proximaCitaModel = gson.fromJson(jsonToString,ProximaCitaModel.class);

                    tipoTrabajo = view.findViewById(R.id.tipoDeTrabajo);
                    contratoTrabajo = view.findViewById(R.id.contrato);
                    horaTrabajo =  view.findViewById(R.id.hora);
                    calleDireccion = view.findViewById(R.id.calle);
                    numeroDireccion = view.findViewById(R.id.numero);
                    coloniaDireccion =  view.findViewById(R.id.colonia);
                    tipoTrabajo.setText(proximaCitaModel.Tipo);
                    contratoTrabajo.setText(proximaCitaModel.Contrato);
                    horaTrabajo.setText(proximaCitaModel.Hora);
                    calleDireccion.setText(proximaCitaModel.Calle);
                    numeroDireccion.setText(proximaCitaModel.NUMERO);
                    coloniaDireccion.setText(proximaCitaModel.Colonia);


                    

                } else {
                    ErrorInicioDeSesion(context,dialogInicio,activity);
                    ErrorMensaje(context,"Error al conseguir datos de inicio");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorInicioDeSesion(context,dialogInicio,activity);
                ErrorMensaje(context,"Error al conseguir datos de inicio");

            }
        });
    }

    //ORDENES//
    public void getOrdenes(final Context context, final JSONObject jsonObject,final View view, final ProgressDialog dialogInicio,final Activity activity) {
        Call<Example> call = services.RequestPost(context, jsonObject).getDataOrdenes();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.code() == 200) {
                    Example jsonResponse = response.body();
                    PieChart pieChart;
                    GraficaAdapter adapter;
                    ArrayList<Integer> color = new ArrayList<>();
                    ArrayList<String> nombre = new ArrayList<>();
                    RecyclerView lista;

                    lista = view.findViewById(R.id.recyclerGrafica);
                    pieChart = view.findViewById(R.id.graficaPastel);
                    int OE=0,OP=0,OV=0,OEP=0,OO=0,RE=0,RP=0,RV=0,REP=0,RO=0;
                    array.dataord = new ArrayList<List<OrdSer>>(asList(jsonResponse.getDameOrdenesQuejasTotalesResult.getOrdSer()));
                    Iterator<List<OrdSer>> itData = array.dataord.iterator();
                    array.dataque = new ArrayList<List<Queja>>(asList(jsonResponse.getDameOrdenesQuejasTotalesResult.getQueja()));
                    Iterator<List<Queja>> itData1 = array.dataque.iterator();
                        while (itData.hasNext()) {
                            List<OrdSer> dat = (List<OrdSer>) itData.next();
                            for (int i = 0; i < dat.size(); i++) {
                                if (dat.get(i).getStatus().equals("Ejecutada")) {
                                    try {
                                        OE = dat.get(i).getTotal();
                                    } catch (Exception e) {
                                        OE = 0;
                                    }
                                }
                                if (dat.get(i).getStatus().equals("Pendiente")) {
                                    try {
                                        OP = dat.get(i).getTotal();
                                    } catch (Exception e) {
                                        OP = 0;
                                    }
                                }
                                if (dat.get(i).getStatus().equals("Visita")) {
                                    try {
                                        OV = dat.get(i).getTotal();
                                    } catch (Exception e) {
                                        OV = 0;
                                    }

                                }
                                if (dat.get(i).getStatus().equals("En Proceso")) {
                                    try {
                                        OEP = dat.get(i).getTotal();
                                    } catch (Exception e) {
                                        OEP = 0;
                                    }
                                }
                                if (dat.get(i).getStatus().equals("otro")) {
                                    try {
                                        OO = dat.get(i).getTotal();
                                    } catch (Exception e) {
                                        OO = 0;
                                    }
                                }
                            }
                        }

                    while (itData1.hasNext()) {
                        List<Queja> dat1 = (List<Queja>) itData1.next();
                        for (int i = 0; i < dat1.size(); i++) {
                            if (dat1.get(i).getStatus().equals("Ejecutada")) {
                                try {
                                    RE = dat1.get(i).getTotal();
                                } catch (Exception e) {
                                    RE = 0;
                                }
                            }
                            if (dat1.get(i).getStatus().equals("Pendiente")) {
                                try {
                                    RP = dat1.get(i).getTotal();
                                } catch (Exception e) {
                                    RP = 0;
                                }
                            }
                            if (dat1.get(i).getStatus().equals("Visita")) {
                                try {
                                    RV = dat1.get(i).getTotal();
                                } catch (Exception e) {
                                    RV = 0;
                                }

                            }
                            if (dat1.get(i).getStatus().equals("En Proceso")) {
                                try {
                                    REP = dat1.get(i).getTotal();
                                } catch (Exception e) {
                                    REP = 0;
                                }
                            }
                            if (dat1.get(i).getStatus().equals("otro")) {
                                try {
                                    RO = dat1.get(i).getTotal();
                                } catch (Exception e) {
                                    RO = 0;
                                }
                            }
                        }
                    }

                    pieChart.setUsePercentValues(true);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.setExtraOffsets(5, 10, 5, 5);
                    pieChart.setDragDecelerationFrictionCoef(1f);
                    pieChart.setDrawHoleEnabled(false);
                    pieChart.setHoleColor(android.R.color.white);
                    pieChart.setTransparentCircleRadius(1f);

                    //Datos de la grafica
                    ArrayList<PieEntry> yValues = new ArrayList<>();
                    if (OE == 0 && OP == 0 && OV == 0 && RP == 0&& OEP == 0 && OO == 0 && RE == 0 &&
                            RV == 0 && REP == 0 && RO == 0) {

                        yValues.add(new PieEntry(100f, "Completado"));
                        int color1=Color.rgb(100,221,23);//verde
                        color.add(color1);
                        nombre.add("Orden Ejecutada");

                        adapter = new GraficaAdapter(context,nombre,color);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);
                        lista.setLayoutManager(layoutManager);
                        lista.setAdapter(adapter);

                        PieDataSet dataSet = new PieDataSet(yValues, "");
                        dataSet.setSliceSpace(7f);
                        dataSet.setSelectionShift(10f);
                        dataSet.setColors(color);
                        dataSet.setHighlightEnabled(true);
                        PieData data = new PieData((dataSet));
                        data.setValueTextSize(15f);
                        data.setValueTextColor(Color.BLACK);
                        pieChart.setData(data);

                    } else {

                        if (OE != 0) {
                            yValues.add(new PieEntry(OE, "Orden Ejecutada"));
                            int color1=Color.rgb(119,189,79);//#77bd4f
                            color.add(color1);
                            nombre.add("Orden Ejecutada");
                        }
                        if (OP != 0) {
                            yValues.add(new PieEntry(OP, "Orden Pendiente"));
                            int color2=Color.rgb(40,187,130);//#28bb82
                            color.add(color2);
                            nombre.add("Orden Pendiente");
                        }
                        if (OV != 0) {
                            yValues.add(new PieEntry(OV, "Orden En Visita"));
                            int color3=Color.rgb(189,184,22);//#bab816
                            color.add(color3);
                            nombre.add("Orden En Visita");
                        }
                        if (OEP != 0) {
                            yValues.add(new PieEntry(OEP, "Orden En Proceso"));
                            int color4=Color.rgb(255,0,0);//rojo
                            color.add(color4);
                            nombre.add("Orden En Proceso");
                        }
                        if (OO != 0) {
                            yValues.add(new PieEntry(OO, "Otros"));
                            int color5=Color.rgb(255,0,0);//rojo
                            color.add(color5);
                            nombre.add("Otros");
                        }
                        if (RE != 0) {
                            yValues.add(new PieEntry(RE, "Reportes Ejecutadas"));
                            int color6=Color.rgb(6,167,189);//#06a7bd
                            color.add(color6);
                            nombre.add("Reportes Ejecutada");
                        }
                        if (RP != 0) {
                            yValues.add(new PieEntry(RP, "Reportes Pendiente"));
                            int color7=Color.rgb(0,187,169);//#00b3a9
                            color.add(color7);
                            nombre.add("Reportes Pendiente");
                        }
                        if (RV != 0) {
                            yValues.add(new PieEntry(RV, "Reportes En Visita"));
                            int color9=Color.rgb(255,166,0);//#ffa600
                            color.add(color9);
                            nombre.add("Reportes En Visita");
                        }
                        if (REP != 0) {
                            yValues.add(new PieEntry(REP, "Reportes En Proceso"));
                            int color8=Color.rgb(255,128,0);
                            color.add(color8);nombre.add("Reportes En Proceso");
                        }
                        if (RO != 0) {
                            yValues.add(new PieEntry(RO, "Otros"));
                            int color10=Color.rgb(255,0,0);
                            color.add(color10);
                            nombre.add("Reportes");
                        }
                    }


                    adapter = new GraficaAdapter(context,nombre,color);
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);
                    lista.setLayoutManager(layoutManager);
                    lista.setAdapter(adapter);



                    pieChart.getLegend().setEnabled(false);
                    PieDataSet dataSet = new PieDataSet(yValues, "");
                    dataSet.setSliceSpace(7f);
                    dataSet.setSelectionShift(10f);
                    dataSet.setColors(color);
                    dataSet.setHighlightEnabled(true);
                    PieData data = new PieData((dataSet));
                    data.setValueTextSize(15f);
                    data.setValueTextColor(Color.BLACK);
                    pieChart.animateXY(2000, 2000);
                    pieChart.setData(data);
                    dialogInicio.dismiss();





                } else {
                    ErrorInicioDeSesion(context,dialogInicio,activity);
                    ErrorMensaje(context,"Error al conseguir datos, intente otra vez "+response.message());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                ErrorInicioDeSesion(context,dialogInicio,activity);
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //Lista de ordenes///
    public void getListQuejas(final Context context) {
        Service service = null;
        try {
            service = services.getListQuejasService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<QuejasList> call = service.getQuejasAgendadas();
        call.enqueue(new Callback<QuejasList>() {
            @Override
            public void onResponse(Call<QuejasList> call, Response<QuejasList> response) {
                if (response.code() == 200) {
                    QuejasList jsonResponse = response.body();
                    array.dataquejas = new ArrayList<List<ListadoQuejasAgendadas>>(asList(jsonResponse.GetDameListadoQuejasAgendadasResult()));
                    Iterator<List<ListadoQuejasAgendadas>> itData = array.dataquejas.iterator();
                    while (itData.hasNext()) {
                        List<ListadoQuejasAgendadas> dat = (List<ListadoQuejasAgendadas>) itData.next();
                        Array.Queja.clear();
                        Array.nombreQ.clear();
                        Array.statusQ.clear();
                        Array.contratoQ.clear();
                        Array.Direccion.clear();
                        for (int i = 0; i < dat.size(); i++) {
                            Array.Queja.add(String.valueOf(dat.get(i).getClvQueja()));
                            Array.contratoQ.add(String.valueOf(dat.get(i).getContrato()));
                            Array.nombreQ.add(String.valueOf(dat.get(i).getNombre()));
                            Array.statusQ.add(String.valueOf(dat.get(i).getStatus()));
                            Array.Direccion.add(String.valueOf(dat.get(i).getCalle() + ", " + dat.get(i).getNUMERO() + ", " + dat.get(i).getColonia()));

                        }
                    }

                    if (Array.Queja.size() == 0 && statusBusquedaReporte == true){
                        ErrorMensaje(context,"Reporte no encontrado ");
                        statusBusquedaReporte = false;
                    }else if (contratoQ.size() == 0 && statusBusquedaContRepo == true){
                        ErrorMensaje(context,"Contrato no encontrado ");
                        statusBusquedaContRepo = false;
                    }

                    Intent intent1 = new Intent(context, Reportes.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
                } else {
                    ErrorMensaje(context,"Error al conseguir lista quejas "+response.message());
                }
            }

            @Override
            public void onFailure(Call<QuejasList> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getListOrd(final Context context) {
        Service service = null;
        try {
            service = services.getListOrdService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<Example1> call = service.getDataListOrd();
        call.enqueue(new Callback<Example1>() {
            @Override
            public void onResponse(Call<Example1> call, Response<Example1> response) {
                Log.d("asd","asd");
                if (response.code() == 200) {
                    Example1 jsonResponse = response.body();
                    array.dataagenda = new ArrayList<List<GetDameListadoOrdenesAgendadasResult>>(asList(jsonResponse.getGetDameListadoOrdenesAgendadasResult()));
                    Iterator<List<GetDameListadoOrdenesAgendadasResult>> itData = array.dataagenda.iterator();
                    while (itData.hasNext()) {
                        List<GetDameListadoOrdenesAgendadasResult> dat = (List<GetDameListadoOrdenesAgendadasResult>) itData.next();
                        Array.ordensrc.clear();
                        Array.nombresrc.clear();
                        Array.statusrc.clear();
                        Array.contratosrc.clear();
                        Array.napsrc.clear();
                        Array.tapsrc.clear();
                        Array.trabajosrc.clear();
                        for (int i = 0; i < dat.size(); i++) {
                            Array.ordensrc.add(String.valueOf(dat.get(i).getClvOrden()));
                            Array.contratosrc.add(String.valueOf(dat.get(i).getContrato()));
                            Array.nombresrc.add(String.valueOf(dat.get(i).getNombre()));
                            Array.statusrc.add(String.valueOf(dat.get(i).getStatus()));
                            Array.direccionsrc.add(String.valueOf(dat.get(i).getCalle() + ", " + dat.get(i).getNumero() + ", " + dat.get(i).getColonia()));
                            Array.napsrc.add(dat.get(i).getNap());
                            Array.tapsrc.add(dat.get(i).getTab());
                            Array.trabajosrc.add(dat.get(i).getDescripcion());
                        }
                    }
                    if (ordensrc.size() == 0 && statusBusquedaOrden == true){
                        ErrorMensaje(context,"Orden no encontrada ");
                        statusBusquedaOrden = false;
                    }else if (contratosrc.size() == 0 && statusBusquedaContrato == true){
                        ErrorMensaje(context,"Contrato no encontrado ");
                        statusBusquedaContrato = false;
                    }
                    Intent intent1 = new Intent(context, Orden.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
                } else {
                    ErrorMensaje(context,"Error al conseguir lista ordenes "+response.message());
                }
            }

            @Override
            public void onFailure(Call<Example1> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //Consuta pantalla ordenes//
    public void getDeepCons(final Context context) {
        Service service = null;
        try {
            service = services.getDeepConsService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.getDataDeepCons();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject userJson = response.body().getAsJsonObject("GetDeepConsultaOrdSerResult");
                    try {
                        TryDeepConsulta(userJson);
                    } catch (Exception e) {
                        TryDeepConsulta1(userJson);
                    }
                    getTrabajos(context);

                    ContratoReal= DeepConsModel.getContrato();

                    try {
                        contraroMA = (String.valueOf(DeepConsModel.getContatoCom()));
                    } catch (Exception e) {
                        ErrorMensaje(context,"Error al conseguir datos de la orden");
                    }
                    try {
                        obsMA = (String.valueOf(DeepConsModel.Obs));
                    } catch (Exception e) {
                        ErrorMensaje(context,"Error al conseguir datos de la orden");
                    }
                    try {
                        if (DeepConsModel.STATUS.equals("E")) {
                            statusMA = ("Ejecutada");

                        } else if (DeepConsModel.STATUS.equals("P")) {
                            statusMA = ("Pendiente");

                        } else if (DeepConsModel.STATUS.equals("V")) {
                            statusMA = ("En Visita");
                        }

                    } catch (Exception e) {
                        ErrorMensaje(context,"Error al conseguir datos de la orden");
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir datos de la orden "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //Informacion del Cliente//
    public void getInfoCliente(final Context context) {
        Service service = null;
        try {
            service = services.getInfoClienteService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.getDataInfoCliente();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JsonObject userJson = response.body().getAsJsonObject("GetDeepBUSCLIPORCONTRATO_OrdSerResult");
                        InfoClienteModelo user = new InfoClienteModelo(
                                userJson.get("CALLE").getAsString(),
                                userJson.get("CIUDAD").getAsString(),
                                userJson.get("COLONIA").getAsString(),
                                userJson.get("Compania").getAsString(),
                                userJson.get("NOMBRE").getAsString(),
                                userJson.get("NUMERO").getAsString()
                        );
                    } catch (Exception e) {
                    }
                    MainActivity.Direccion.setText(InfoClienteModelo.CALLE + " " + InfoClienteModelo.NUMERO + " " + InfoClienteModelo.COLONIA);
                    MainActivity.Nombre.setText(InfoClienteModelo.NOMBRE);
                } else {
                    ErrorMensaje(context,"Error al conseguir información del cliente "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //ServiciosdelCliente//
    public void getServicios(final Context context) {
        Service service = null;
        try {
            service = services.getServiciosService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<Example2> call = service.getDataServicios();
        call.enqueue(new Callback<Example2>() {
            @Override
            public void onResponse(Call<Example2> call, Response<Example2> response) {
                if (response.code() == 200) {
                    String a = "";
                    Example2 jsonResponse = response.body();
                    array.dataclientes = new ArrayList<List<GetdameSerDELCliresumenResult>>(asList(jsonResponse.getdameSerDELCliresumenResult()));
                    Iterator<List<GetdameSerDELCliresumenResult>> itData = array.dataclientes.iterator();
                    while (itData.hasNext()) {
                        List<GetdameSerDELCliresumenResult> dat = (List<GetdameSerDELCliresumenResult>) itData.next();
                        for (int i = 0; i < dat.size(); i++) {
                            String[] caracteres = dat.get(i).getResumen().split(" ");
                            if(caracteres[0].equals("Servicios")){
                                a+="\n";
                                a+=dat.get(i).getResumen() + "\n";
                            }else{
                                try{
                                    if(caracteres[5].equals("*")){
                                        a += caracteres[5]+" "+caracteres[6]+" "+caracteres[7]+" "+caracteres[8] + "\n";
                                        for(int b=9; b<caracteres.length;b++){
                                            a+= " "+caracteres[b];
                                        }
                                        a+="\n";
                                    }else{
                                        a += dat.get(i).getResumen() + "\n";
                                    }
                                }catch (Exception e){
                                    a += dat.get(i).getResumen() + "\n";
                                }
                            }
                        }
                        MainActivity.InfoServicios.setText(a);

                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir servicios del cliente "+response.message());
                }
            }

            @Override
            public void onFailure(Call<Example2> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //informacion trabajos//
    public void getTrabajos(final Context context) {
        Service service = null;
        try {
            service = services.getTrabajoService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<Example3> call = service.getDataTrabajos();
        call.enqueue(new Callback<Example3>() {
            @Override
            public void onResponse(Call<Example3> call, Response<Example3> response) {
                Log.d("asd","asd");
                if (response.code() == 200) {
                    Array.trabajox.clear();
                    Array.observacionesx.clear();
                    Array.accionx.clear();
                    Array.clavex.clear();
                    Array.clv_trabajox.clear();
                    Array.recibix.clear();
                    isnet = false;
                    Example3 jsonResponse = response.body();
                    array.dataTrabajos = new ArrayList<List<GetBUSCADetOrdSerListResult>>(asList(jsonResponse.getGetBUSCADetOrdSerListResult()));
                    Iterator<List<GetBUSCADetOrdSerListResult>> itData = array.dataTrabajos.iterator();
                    Array.trabajox.clear();
                    Array.accionx.clear();
                    Array.observacionesx.clear();
                    while (itData.hasNext()) {
                        List<GetBUSCADetOrdSerListResult> dat = (List<GetBUSCADetOrdSerListResult>) itData.next();
                        for (int i = 0; i < dat.size(); i++) {
                            dat.get(i).setSeRealiza(false);
                            Array.trabajox.add(String.valueOf(dat.get(i).getDescripcion()));
                            Array.accionx.add(String.valueOf(dat.get(i).getAccion()));
                            Array.clavex.add(dat.get(i).getClave());
                            Array.clv_trabajox.add(dat.get(i).getClvTrabajo());
                            Array.recibix.add(dat.get(i).getSeRealiza());
                            Array.observacionesx.add(dat.get(i).getObs());
                            if (dat.get(i).getClvTrabajo() == 1270) {
                                isnet = true;
                            }
                            if (dat.get(i).getClvTrabajo() == 1203) {
                                rapagejecutar = true;
                            }
                        }
                        Intent intento1 = new Intent(context, MainActivity.class);
                        intento1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intento1);
                        try {
                            trabajos.setAdapter(adaptertrabajos);
                        } catch (Exception e) {

                        }
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir trabajos "+response.message());
                }
            }

            @Override
            public void onFailure(Call<Example3> call, Throwable t) {
                ErrorMensaje(context,"Error al conseguir trabajos "+t.getMessage());
            }
        });
    }

    //TecnicoSecundario////
    public void getTecSec(final Context context,final Spinner spiner) {
        Array.clv_tecnicoSecundario = new ArrayList<Integer>();
        Array.clv_tecnicoSecundario.add(0, -1);

        Service service = null;
        try {
            service = services.getTecSecService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONTecSec> call = service.getDataTecSec();
        call.enqueue(new Callback<JSONTecSec>() {
            @Override
            public void onResponse(Call<JSONTecSec> call, Response<JSONTecSec> response) {
                if (response.code() == 200) {
                    JSONTecSec jsonResponse = response.body();
                    Array.dataTecSec = new ArrayList<>(asList(jsonResponse.GetMuestraRelOrdenesTecnicosListResult()));
                    Iterator<List<GetMuestraRelOrdenesTecnicosListResult>> itdata = Array.dataTecSec.iterator();
                    while (itdata.hasNext()) {
                        List<GetMuestraRelOrdenesTecnicosListResult> dat = itdata.next();
                        datos = new String[dat.size() + 1];
                        int j = 1;
                        datos[0] = a;
                        for (int i = 0; i < dat.size(); i++) {
                            datos[j] = dat.get(i).getNOMBRE();
                            Array.clv_tecnicoSecundario.add(j, dat.get(i).getCLV_TECNICO());

                            j = j + 1;
                        }
                        adapterTecSec = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                        spiner.setAdapter(adapterTecSec);
                        spiner.setSelection(posTec);

                        HorasOrdenes.Obs.setText(String.valueOf(DeepConsModel.Obs));
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir lista de técnicos secundarios "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONTecSec> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getExtencionesAdicionales(final Context context) {
        Service service = null;
        try {
            service = services.getExtencionAdiService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.getDataExtencionAdi();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                if (response1.code() == 200) {
                    String string = String.valueOf(response1.body().getAsJsonPrimitive("GetCONCONEXResult"));
                    extencionesE = string;
                    Intent intento = new Intent(context, ExtensionesAdi.class);
                    intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intento);
                    dialogTrabajos.dismiss();
                } else {
                    ErrorMensaje(context,"Error al conseguir extensiones adicionales "+response1.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //ClientesAparato//
    public void getCliApa(final Context context) {
        Service service = null;
        try {
            service = services.getCliApaService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONCLIAPA> call = service.getDataCliApa();
        call.enqueue(new Callback<JSONCLIAPA>() {
            @Override
            public void onResponse(Call<JSONCLIAPA> call, Response<JSONCLIAPA> response) {
                if (response.code() == 200) {
                    JSONCLIAPA jsonResponse = response.body();
                    array.dataCliApa = new ArrayList<List<GetListClienteAparatosResult>>(asList(jsonResponse.GetListClienteAparatosResult()));
                    Iterator<List<GetListClienteAparatosResult>> itdata = array.dataCliApa.iterator();
                    while (itdata.hasNext()) {
                        List<GetListClienteAparatosResult> dat = itdata.next();
                        String datos[] = new String[dat.size() + 1];
                        datos[0] = "Seleccione aparato";
                        int j = 1;
                        for (int i = 0; i < dat.size(); i++) {
                            datos[j] = dat.get(i).getMac();
                            j = j + 1;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                        CambioAparato.aparato.setAdapter(adapter);
                        try {
                            CambioAparato.aparato.setSelection(CambioAparato.obtenerPosicionAC(CambioAparatoDeepModel.AparatoCliente));
                            try{
                                JSONObject jsonObject = new JSONObject();
                                JSONObject jsonObject1 = new JSONObject();
                                jsonObject.put("Letra", dat.get(CambioAparato.obtenerPosicionAC(CambioAparatoDeepModel.AparatoCliente)).Letra);
                                jsonObject1.put("ObjRelMacwan", jsonObject);
                                ValidaMACWAM(getApplicationContext(), jsonObject1);
                            }catch (Exception e){}
                            getStatusApa(context);
                        } catch (Exception e) {
                            dialogTrabajos.dismiss();
                        }
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir aparatos del cliente "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONCLIAPA> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //Status Aparato////
    public void getStatusApa(final Context context) {
        Service service = services.getStatusApa(context);
        Call<JSONStatusApa> call = service.getDataStatusApa();
        call.enqueue(new Callback<JSONStatusApa>() {
            @Override
            public void onResponse(Call<JSONStatusApa> call, Response<JSONStatusApa> response) {
                if (response.code() == 200) {
                    JSONStatusApa jsonResponse = response.body();
                    array.dataStaApa = new ArrayList<List<GetSP_StatusAparatosListResult>>(asList(jsonResponse.GetSP_StatusAparatosListResult()));
                    Iterator<List<GetSP_StatusAparatosListResult>> itdata = array.dataStaApa.iterator();
                    while (itdata.hasNext()) {
                        List<GetSP_StatusAparatosListResult> dat = itdata.next();
                        String datos[] = new String[dat.size() + 1];
                        datos[0] = "Seleccione estado";
                        int j = 1;
                        for (int i = 0; i < dat.size(); i++) {
                            datos[j] = dat.get(i).getConcepto();
                            j = j + 1;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                        CambioAparato.estado.setAdapter(adapter);
                        try {
                            CambioAparato.estado.setSelection(CambioAparato.obtenerPosicionSA(CambioAparatoDeepModel.StatusEntrega));
                            getApaTipo(context);
                        } catch (Exception e) {

                        }
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir estatus del aparato "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONStatusApa> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //TipoAparato////
    public void getApaTipo(final Context context) {
        Service service = null;
        try {
            service = services.getApaTipoService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONApaTipo> call = service.getDataApaTipo();
        call.enqueue(new Callback<JSONApaTipo>() {
            @Override
            public void onResponse(Call<JSONApaTipo> call, Response<JSONApaTipo> response) {
                if (response.code() == 200) {
                    try {
                        Iterator<List<GetListClienteAparatosResult>> itdata1 = Array.dataCliApa.iterator();
                        List<GetListClienteAparatosResult> dat1 = itdata1.next();
                        CambioAparato.idArticulo = dat1.get(CambioAparato.obtenerPosicionTA(CambioAparatoDeepModel.TipoAparatoAsignar)).getIdArticulo();
                        CambioAparato.contrato = dat1.get(CambioAparato.obtenerPosicionTA(CambioAparatoDeepModel.TipoAparatoAsignar)).getControNet();

                    } catch (Exception e) {

                    }
                    JSONApaTipo jsonResponse = response.body();
                    array.dataApaTipo = new ArrayList<List<GetListTipoAparatosByIdArticuloResult>>(asList(jsonResponse.GetListTipoAparatosByIdArticuloResult()));
                    Iterator<List<GetListTipoAparatosByIdArticuloResult>> itdata = array.dataApaTipo.iterator();
                    while (itdata.hasNext()) {
                        List<GetListTipoAparatosByIdArticuloResult> dat = itdata.next();
                        String datos[] = new String[dat.size() + 1];
                        datos[0] = "Seleccione tipo de aparato";
                        int j = 1;
                        for (int i = 0; i < dat.size(); i++) {
                            datos[j] = dat.get(i).getNombre();j = j + 1;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                        CambioAparato.tipoAparato.setAdapter(adapter);
                        try {
                            Iterator<List<GetListClienteAparatosResult>> itdata1 = Array.dataCliApa.iterator();
                            List<GetListClienteAparatosResult> dat1 = itdata1.next();
                            CambioAparato.tipoAparato.setSelection(CambioAparato.obtenerPosicionTA(CambioAparatoDeepModel.TipoAparatoAsignar));
                            CambioAparato.idArticulo = dat1.get(CambioAparato.obtenerPosicionTA(CambioAparatoDeepModel.TipoAparatoAsignar)).getIdArticulo();
                            CambioAparato.contrato = dat1.get(CambioAparato.obtenerPosicionTA(CambioAparatoDeepModel.TipoAparatoAsignar)).getControNet();

                            getApaTipDis(context);
                        } catch (Exception e) {

                        }
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir tipo de aparato "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONApaTipo> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //AparatoDisponible////
    public void getApaTipDis(final Context context) {

        Service service = null;
        try {
            service = services.getApaTipDisService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONApaTipDis> call = service.getDataApaTipDis();
        call.enqueue(new Callback<JSONApaTipDis>() {
            @Override
            public void onResponse(Call<JSONApaTipDis> call, Response<JSONApaTipDis> response) {
                if (response.code() == 200) {
                    try {
                        Iterator<List<GetListTipoAparatosByIdArticuloResult>> itdata1 = Array.dataApaTipo.iterator();
                        List<GetListTipoAparatosByIdArticuloResult> dat1 = itdata1.next();
                        CambioAparato.idArticulo2 = dat1.get(CambioAparato.obtenerPosicionA(CambioAparatoDeepModel.AparatoAsignar)).getIdArticulo();
                    } catch (Exception e) {
                    }
                    JSONApaTipDis jsonResponse = response.body();
                    array.dataApaTipDis = new ArrayList<List<GetListAparatosDisponiblesByIdArticuloResult>>(asList(jsonResponse.GetListAparatosDisponiblesByIdArticuloResult()));
                    Iterator<List<GetListAparatosDisponiblesByIdArticuloResult>> itdata = array.dataApaTipDis.iterator();
                    while (itdata.hasNext()) {
                        List<GetListAparatosDisponiblesByIdArticuloResult> dat = itdata.next();
                        String datos[] = new String[dat.size() + 1];
                        datos[0] = "Seleccione aparato disponible";
                        int j = 1;
                        for (int i = 0; i < dat.size(); i++) {
                            datos[j] = dat.get(i).getDescripcion();
                            j = j + 1;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                        CambioAparato.aparatoAsignar.setAdapter(adapter);
                        try {
                            CambioAparato.aparatoAsignar.setSelection(CambioAparato.obtenerPosicionA(CambioAparatoDeepModel.AparatoAsignar));
                            dialogTrabajos.dismiss();
                        } catch (Exception e) {
                        }
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir aparatos disponibles "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONApaTipDis> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getDeepCAPAT(final Context context) {
        Services restApiAdapter = new Services();
        Service service = restApiAdapter.getDeepCAPATService(context);
        Call<JsonObject> call = service.getDeepCAPAT();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //Peticion de datos sobre el Json "LogOnResult"
                if (response.code() == 200) {
                    CambioAparatoDeepModel.StatusEntrega = "";
                    CambioAparatoDeepModel.AparatoAsignar=0;
                    CambioAparatoDeepModel.AparatoCliente=0;
                    CambioAparatoDeepModel.TipoAparatoAsignar=0;
                    JsonObject userJson = response.body().getAsJsonObject("GetCambioAparatoDeepResult");
                    try {
                        CambioAparatoDeepModel user = new CambioAparatoDeepModel(
                                userJson.get("AparatoAsignar").getAsInt(),
                                userJson.get("AparatoCliente").getAsInt(),
                                userJson.get("TipoAparatoAsignar").getAsInt(),
                                userJson.get("StatusEntrega").getAsString()
                        );
                        getCliApa(context);

                    } catch (Exception e) {
                        getCliApa(context);
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir datos de cambio de aparato "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getCAMDO(final Context context) {
        Service service = null;
        try {
            service = services.getCAMODOService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONCAMDO> call = service.getDataCAMDO();
        call.enqueue(new Callback<JSONCAMDO>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<JSONCAMDO> call, Response<JSONCAMDO> response) {
                if (response.code() == 200) {
                    try {
                        JSONCAMDO jsonResponse = response.body();
                        array.dataCAMDO = new ArrayList<List<GetDameDatosCAMDOResult>>(asList(jsonResponse.getDameDatosCAMDOResult()));
                        Iterator<List<GetDameDatosCAMDOResult>> itdata = array.dataCAMDO.iterator();
                        while (itdata.hasNext()) {
                            List<GetDameDatosCAMDOResult> dat = itdata.next();
                            String datos[] = new String[dat.size()];
                            ciudadcmdo = dat.get(0).Ciudad;
                            localidadcmdo = dat.get(0).localidad;
                            coloniacmdo = dat.get(0).colonia;
                            callecmdo = dat.get(0).calle;
                            numerocmdo = String.valueOf(dat.get(0).NUMERO);
                            numeroicmdo = dat.get(0).Num_int;
                            telefonocmdo = dat.get(0).TELEFONO;
                            callencmdo = dat.get(0).calleNorte;
                            callescmdo = dat.get(0).calleSur;
                            calleecmdo = dat.get(0).calleEste;
                            calleocmdo = dat.get(0).calleOeste;
                            casacmdo = dat.get(0).Casa;
                            Intent intento = new Intent(context, CambioDom.class);
                            intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intento);
                            dialogTrabajos.dismiss();
                        }
                    } catch (Exception e) {
                        ciudadcmdo = "";
                        localidadcmdo = "";
                        coloniacmdo = "";
                        callecmdo = "";
                        numerocmdo = "";
                        numeroicmdo = "";
                        telefonocmdo = "";
                        callencmdo = "";
                        callescmdo = "";
                        calleecmdo = "";
                        calleocmdo = "";
                        casacmdo = "";
                        Intent intento = new Intent(context, CambioDom.class);
                        intento.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intento);
                        dialogTrabajos.dismiss();
                        ErrorMensaje(context,"Error al conseguir datos de cambio de domicilio");
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir datos de cambio de domicilio "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONCAMDO> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //Arbol Servicios//
    public void getArbSer(final Context context) {
        Service service = null;
        try {
            service = services.getArbolSerService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONArbolServicios> call = service.getDataArbSer();
        call.enqueue(new Callback<JSONArbolServicios>() {
            @Override
            public void onResponse(Call<JSONArbolServicios> call, Response<JSONArbolServicios> response) {
                if (response.code() == 200) {

                    array.nombreArbol.clear();
                    JSONArbolServicios jsonResponse = response.body();
                    array.dataArbSer = new ArrayList<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>>(asList(jsonResponse.GetMuestraArbolServiciosAparatosPorinstalarListResult()));
                    Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
                    while (itData4.hasNext()) {
                        List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();
                        for (int i = 0; i < dat4.size(); i++) {
                            array.nombreArbol.add(dat4.get(i).getNombre());
                            if(dat4.get(i).IdMedio==1){
                                TAP=true;
                            }
                            if(dat4.get(i).IdMedio==2){
                                NAP=true;
                            }
                        }
                    }
                    Intent intento25 = new Intent(context, ServiciosAInstalar.class);
                    intento25.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intento25);
                    dialogTrabajos.dismiss();
                    //Request pregunta
                } else {
                    ErrorMensaje(context,"Error al conseguir datos de la instalacion "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONArbolServicios> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    //Arbol Servicios//

    //Medios Servicios//
    public void getMedSer(final Context context, JSONObject jsonObject, final Spinner spinnerMedio,final int posicionArbol) {
        Call<JSONMediosSer> call = services.RequestPost(context, jsonObject).getDataMedSer();
        call.enqueue(new Callback<JSONMediosSer>() {
            @Override
            public void onResponse(Call<JSONMediosSer> call, Response<JSONMediosSer> response) {
                if (response.code() == 200) {
                    array.medio.clear();
                    JSONMediosSer jsonResponse = response.body();
                    array.dataMedSer = new ArrayList<List<GetMuestraMedioPorServicoContratadoListResult>>(asList(jsonResponse.GetMuestraMedioPorServicoContratadoListResult()));
                    Iterator<List<GetMuestraMedioPorServicoContratadoListResult>> itData = array.dataMedSer.iterator();
                    while (itData.hasNext()) {
                        List<GetMuestraMedioPorServicoContratadoListResult> dat = (List<GetMuestraMedioPorServicoContratadoListResult>) itData.next();
                        array.medio.add("Seleccionar medio");
                        for (int i = 0; i < dat.size(); i++) {
                            array.medio.add(dat.get(i).getDescripcion());
                        }

                    }
                    //Llenamos spinnerMedio
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, Array.medio);
                    spinnerMedio.setAdapter(adapter1);

                    //Verificamos que tenga medio para llenar el spinner
                    //Arbol
                    Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData1 = Array.dataArbSer.iterator();
                    final List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = itData1.next();
                    if(dat4.get(posicionArbol).IdMedio!=0){
                        //selecciono la posicion del spinner
                        spinnerMedio.setSelection(ReporteAsignacion.obtenerPosicionSpinnerMedio(dat4.get(posicionArbol).IdMedio));
                    }


                } else {
                    ErrorMensaje(context,"Error al conseguir medios "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONMediosSer> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //Tipo de Aparatos//
    public void getTipoAparatos(final Context context, final JSONObject jsonObject, final Spinner spinner) {
        Call<JSONTipoAparatos> call = services.RequestPost(context, jsonObject).getDataTipoAparatos();
        call.enqueue(new Callback<JSONTipoAparatos>() {
            @Override
            public void onResponse(Call<JSONTipoAparatos> call, Response<JSONTipoAparatos> response) {
                if (response.code() == 200) {
                    array.tipoAparato.clear();
                    array.tipoAparatoLetra.clear();
                    JSONTipoAparatos jsonResponse = response.body();
                    array.dataTipoAparatos = new ArrayList<List<GetMuestraTipoAparatoListResult>>(asList(jsonResponse.GetMuestraTipoAparatoListResult()));
                    Iterator<List<GetMuestraTipoAparatoListResult>> itData = array.dataTipoAparatos.iterator();
                    array.tipoAparato.add("Seleccione tipo de aparato");
                    while (itData.hasNext()) {
                        List<GetMuestraTipoAparatoListResult> dat = itData.next();
                        for (int i = 0; i < dat.size(); i++) {
                            array.tipoAparato.add(dat.get(i).getNombre());
                            array.tipoAparatoLetra.add(dat.get(i).letra);
                        }
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, array.tipoAparato);
                    spinner.setAdapter(adapter1);
                } else {
                    ErrorMensaje(context,"Error al conseguir tipos de aparatos "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONTipoAparatos> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //Aparatos Disponibles///
    public void getAparatosDisponibles(final Context context,final JSONObject jsonObject, final Spinner spinner) {
        Call<JSONAparatosDisponibles> call = services.RequestPost(context, jsonObject).getDataAparatosDisponibles();
        call.enqueue(new Callback<JSONAparatosDisponibles>() {
            @Override
            public void onResponse(Call<JSONAparatosDisponibles> call, Response<JSONAparatosDisponibles> response) {
                if (response.code() == 200) {
                    array.aparatoDisponibles.clear();
                    array.aparatoAsignacion.clear();
                    JSONAparatosDisponibles jsonResponse = response.body();
                    array.dataAparatosDisponibles = new ArrayList<List<GetMuestraAparatosDisponiblesListResult>>(asList(jsonResponse.GetMuestraAparatosDisponiblesListResult()));
                    Iterator<List<GetMuestraAparatosDisponiblesListResult>> itData = array.dataAparatosDisponibles.iterator();
                    array.aparatoAsignacion.add("Seleccione aparato");
                    while (itData.hasNext()) {
                        List<GetMuestraAparatosDisponiblesListResult> dat = (List<GetMuestraAparatosDisponiblesListResult>) itData.next();
                        for (int i = 0; i < dat.size(); i++) {
                            array.aparatoDisponibles.add(dat.get(i).getDescripcion());
                            array.aparatoAsignacion.add(dat.get(i).getDescripcion());

                        }
                    }

                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, array.aparatoAsignacion);
                    spinner.setAdapter(adapter1);
                } else {
                    ErrorMensaje(context,"Error al conseguir aparatos disponibles "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONAparatosDisponibles> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //Servicios Aparatos//
    public void getServiciosAparatos(final Context context, final JSONObject jsonObject, final ListView lista, final String letra) {
        Call<JSONServiciosAparatos> call = services.RequestPost(context, jsonObject).getDataServiciosAparatos();
        call.enqueue(new Callback<JSONServiciosAparatos>() {
            @Override
            public void onResponse(Call<JSONServiciosAparatos> call, Response<JSONServiciosAparatos> response) {
                if (response.code() == 200) {
                    array.serviciosAparatos.clear();
                    JSONServiciosAparatos jsonResponse = response.body();
                    final Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
                    final List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();

                    array.dataserviciosAparatos = new ArrayList<List<GetMuestraServiciosRelTipoAparatoListResult>>(asList(jsonResponse.GetMuestraServiciosRelTipoAparatoListResult()));
                    Iterator<List<GetMuestraServiciosRelTipoAparatoListResult>> itData = array.dataserviciosAparatos.iterator();
                    while (itData.hasNext()) {
                        List<GetMuestraServiciosRelTipoAparatoListResult> dat = (List<GetMuestraServiciosRelTipoAparatoListResult>) itData.next();
                       if(TrabajosAdapter.ISDIG==true){
                            if(letra.equals("T")||letra.equals("D")){
                                for (int i = 0; i < dat.size(); i++) {
                                    array.serviciosAparatos.add(dat.get(i).getNombre());
                                    if(dat.get(i).clv_UnicaNet==ArbolAdapter.clv_unicaNet){
                                        AsignarAparato.selectedStrings.add(dat.get(i).clv_UnicaNet);
                                    }
                                }
                            }else{
                                for (int i = 0; i < dat.size(); i++) {
                                    array.serviciosAparatos.add(dat.get(i).getNombre());
                                    AsignarAparato.selectedStrings.add(dat.get(i).clv_UnicaNet);
                                }
                            }

                       }else{
                           for (int i = 0; i < dat.size(); i++) {
                               array.serviciosAparatos.add(dat.get(i).getNombre());
                               AsignarAparato.selectedStrings.add(dat.get(i).clv_UnicaNet);
                           }
                       }
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_checked, array.serviciosAparatos);
                    lista.setAdapter(arrayAdapter);
                    //lista.setItemChecked(0,true);
                    //Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
                    //List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();
                    //lista.setItemChecked(AsignarAparato.obtenerPosicionLista(dat4.get(ArbolAdapter.posicionArbol).Clv_UnicaNet,agregar),true);




                } else {
                    ErrorMensaje(context,"Error al conseguir servicios de aparatos "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONServiciosAparatos> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getAceptatAsignacino(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getDataAceptarAsig();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    if(MACWAM==true){
                        JSONObject jsonObject = new JSONObject();
                        try{
                            jsonObject.put("RelMacwanList",jsonArrayMAC);
                        }catch (Exception e){}
                        AsignaMACWAM(context,jsonObject);
                    }else{
                    Toast.makeText(context, "Aparatos agregados", Toast.LENGTH_LONG).show();
                    try{
                        ReporteAsignacion.dialogReporteAsignacion.dismiss();
                    }catch (Exception e){}
                    finish();
                    }
                } else {
                    ErrorMensaje(context,"Error al aceptar asignación "+response.message());
                    ReporteAsignacion.dialogReporteAsignacion.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
                ReporteAsignacion.dialogReporteAsignacion.dismiss();
            }
        });
    }

    //INFO CLIENTE Reportes///
//TIPO DE SOLUCION///
    public void getSolucuion(final Context context) {
        Array.clv_Soluc = new ArrayList<Integer>();
        Array.clv_Soluc.add(0, -1);

        Service service = null;
        try {
            service = services.getSolocionService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONSolucion> call = service.getSolut();
        call.enqueue(new Callback<JSONSolucion>() {
            @Override
            public void onResponse(Call<JSONSolucion> call, Response<JSONSolucion> response) {
                if (response.code() == 200) {
                    JSONSolucion jsonResponse = response.body();
                    array.dataSOL = new ArrayList<List<GetMUESTRATRABAJOSQUEJASListResult>>((asList(jsonResponse.getGetMUESTRATRABAJOSQUEJASListResult())));
                    Iterator<List<GetMUESTRATRABAJOSQUEJASListResult>> itdata = array.dataSOL.iterator();
                    while (itdata.hasNext()) {
                        List<GetMUESTRATRABAJOSQUEJASListResult> dat = itdata.next();
                        datos = new String[dat.size() + 1];
                        int j = 1;
                        datos[0] = f;
                        for (int i = 0; i < dat.size(); i++) {
                            datos[j] = dat.get(i).getDESCRIPCION();
                            Array.clv_Soluc.add(j, dat.get(i).getCLVTRABAJO());
                            j = j + 1;
                        }
                        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, datos);
                        solucion.setAdapter(adapter);
                        solucion.setSelection(posSolucionRepo);
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir soluciones "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONSolucion> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());

            }
        });
    }

    //Reporte del Cliente//
    public void getReportesC(final Context context) {
        Service service = null;
        try {
            service = services.getReporteCService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONReporteCliente> call = service.getRPC();
        call.enqueue(new Callback<JSONReporteCliente>() {
            @Override
            public void onResponse(Call<JSONReporteCliente> call, Response<JSONReporteCliente> response) {
                if (response.code() == 200) {
                    JSONReporteCliente jsonResponse = response.body();
                    array.dataReport = new ArrayList<List<GetQuejasListResult>>(asList(jsonResponse.getGetQuejasListResult()));
                    Iterator<List<GetQuejasListResult>> itData = array.dataReport.iterator();
                    while (itData.hasNext()) {
                        List<GetQuejasListResult> dat = (List<GetQuejasListResult>) itData.next();
                        for (int i = 0; i < dat.size(); ++i) {
                            Asigna.add(dat.get(i).getPrioridad());
                            Asigna1.add(dat.get(i).getClasificacionProblema());
                            Obs = dat.get(i).observaciones;
                            ObsR = dat.get(i).observaciones;
                            clvP = dat.get(i).clvPrioridadQueja;
                            tecC = dat.get(i).tecnicoCuadrilla;
                            clvProblemarepo =  dat.get(i).clvProblema;
                            problemaReal = dat.get(i).solucion;
                            clasProblema = dat.get(i).getClasificacionProblema();
                            try {
                                TrabajosReportes.prioridad.setText(String.valueOf(dat.get(i).getPrioridad()));
                                TrabajosReportes.clasific.setText(String.valueOf(dat.get(i).getClasificacionProblema()));
                                TrabajosReportes.desc.setText(String.valueOf(dat.get(i).getObservaciones()));
                                TrabajosReportes.problm.setText(String.valueOf(dat.get(i).getProblema()));

                                if(statusQueja.equals("E")){
                                    TrabajosReportes.proble.setText(String.valueOf(problemaReal));
                                    TrabajosReportes.proble.setEnabled(false);
                                    TrabajosReportes.solucion.setEnabled(false);

                                }

                            }catch (Exception e){}
                            if(dat.get(i).visita1!=null){
                                String palabra = dat.get(i).visita1;
                                String[] caracteres = palabra.split(" ");
                                Log.d("caracteres0",caracteres[0]);
                                Log.d("caracteres1",caracteres[1]);
                                String hora=caracteres[1].substring(0,5);
                                reporteVisita1=caracteres[0];
                                reporteHora1=hora;
                            }
                            if(dat.get(i).visita2!=null){
                                String palabra = dat.get(i).visita2;
                                String[] caracteres = palabra.split(" ");
                                Log.d("caracteres0",caracteres[0]);
                                Log.d("caracteres1",caracteres[1]);
                                String hora=caracteres[1].substring(0,5);
                                reporteVisita2=caracteres[0];
                                reporteHora2=hora;
                            }
                            if(dat.get(i).visita3!=null){
                                String palabra = dat.get(i).visita3;
                                String[] caracteres = palabra.split(" ");
                                Log.d("caracteres0",caracteres[0]);
                                Log.d("caracteres1",caracteres[1]);
                                String hora=caracteres[1].substring(0,5);
                                reporteVisita3=caracteres[0];
                                reporteHora3=hora;
                            }
                            String a="";

                            ClvTrabajoRequest=dat.get(i).getClvTrabajo();
                        }
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del reporte "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONReporteCliente> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //Nombre Tecnico//////
    public void getnombretec(final Context context) {
        Service service = null;
        try {
            service = services.getNombreService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONNombreTecnico> call = service.getNom();
        call.enqueue(new Callback<JSONNombreTecnico>() {
            @Override
            public void onResponse(Call<JSONNombreTecnico> call, Response<JSONNombreTecnico> response) {
                if (response.code() == 200) {
                    JSONNombreTecnico jsonResponse = response.body();
                    array.dataNom = new ArrayList<List<GetConTecnicoAgendaResult>>(Collections.singleton(asList(jsonResponse.getGetConTecnicoAgendaResult())));
                    Iterator<List<GetConTecnicoAgendaResult>> itData = array.dataNom.iterator();
                    while (itData.hasNext()) {
                        List<GetConTecnicoAgendaResult> dat = (List<GetConTecnicoAgendaResult>) itData.next();
                        for (int i = 0; i < dat.size(); ++i) {
                            MainReportes.NombreTec1.setText(String.valueOf(dat.get(i).getTecnico()));
                        }
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del tecnico "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONNombreTecnico> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //servicios asiggnados //
    public void getServiciosAsignados(final Context context) {
        Service service = null;
        try {
            service = services.getAsignadosService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONServicioAsignado> call = service.getServ();
        call.enqueue(new Callback<JSONServicioAsignado>() {
            @Override
            public void onResponse(Call<JSONServicioAsignado> call, Response<JSONServicioAsignado> response) {
                if (response.code() == 200) {
                    JSONServicioAsignado jsonResponse = response.body();
                    String a = "";
                    array.dataServ = new ArrayList<List<GetDameSerDelCliFacListResult>>((asList(jsonResponse.getGetDameSerDelCliFacListResult())));
                    Iterator<List<GetDameSerDelCliFacListResult>> itData = array.dataServ.iterator();
                    while (itData.hasNext()) {
                        List<GetDameSerDelCliFacListResult> dat = (List<GetDameSerDelCliFacListResult>) itData.next();
                        for (int i = 0; i < dat.size(); ++i) {
                            if(dat.get(i).getServicio().equals("-------------------------------------------------------------")){

                            }else{
                                String[] caracteres = dat.get(i).getServicio().split(" ");
                                if(caracteres[0].equals("Servicios")){
                                    a+="\n";
                                    a+=dat.get(i).getServicio() + "\n";
                                }else{
                                    try{
                                        if(caracteres[5].equals("*")){
                                            a += caracteres[5]+" "+caracteres[6]+" "+caracteres[7]+" "+caracteres[8] + "\n";
                                            for(int b=9; b<caracteres.length;b++){
                                                a+= " "+caracteres[b];
                                            }
                                            a+="\n";
                                        }else{
                                            a += dat.get(i).getServicio() + "\n";
                                        }
                                    }catch (Exception e){
                                        a += dat.get(i).getServicio() + "\n";
                                    }
                                }
                            }

                            MainReportes.infoA.setText(a);
                        }
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir servicios asignados "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONServicioAsignado> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getReportes(final Context context) {
        Service service = null;
        try {
            service = services.getMediosReportes(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONReportes> call = service.getReport();
        call.enqueue(new Callback<JSONReportes>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<JSONReportes> call, Response<JSONReportes> response) {
                if (response.code() == 200) {
                    JSONReportes jsonResponse = response.body();
                    array.dataRep = new ArrayList<List<GetuspBuscaContratoSeparado2ListResult>>(asList(jsonResponse.getGetuspBuscaContratoSeparado2ListResult()));
                    Iterator<List<GetuspBuscaContratoSeparado2ListResult>> itData = array.dataRep.iterator();
                    while (itData.hasNext()) {
                        List<GetuspBuscaContratoSeparado2ListResult> dat = (List<GetuspBuscaContratoSeparado2ListResult>) itData.next();
                        for (int i = 0; i < dat.size(); ++i) {
                            MainReportes.Nombre1.setText(dat.get(i).getNombre() + "  " + dat.get(i).getApellidoPaterno() + "  " + dat.get(i).getApellidoMaterno());
                            MainReportes.Direccion1.setText(dat.get(i).getCALLE() + "  " + dat.get(i).getNUMERO() + "  " + dat.get(i).getCOLONIA());
                            MainReportes.contrato1.setText(dat.get(i).getCONTRATO());
                            MainReportes.ciudad1.setText(dat.get(i).getCIUDAD());
                            abc = dat.get(i).contratoBueno;
                            getServiciosAsignados(context);
                            abc = dat.get(i).contratoBueno;
                            getServiciosAsignados(context);
                        }
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del cliente "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONReportes> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getTecSecR(final Context context, final Spinner TecSec) {
        Array.Clv_TecSecR = new ArrayList<Integer>();
        Array.Clv_TecSecR.add(0, -1);
        Service service = null;
        try {
            service = services.getTecSecRService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONTecSecReport> call = service.getTec();
        call.enqueue(new Callback<JSONTecSecReport>() {
            @Override
            public void onResponse(Call<JSONTecSecReport> call, Response<JSONTecSecReport> response) {
                if (response.code() == 200) {
                    JSONTecSecReport jsonResponse = response.body();
                    array.dataTECSEC = new ArrayList<List<GetMuestraTecnicosAlmacenListResult>>((asList(jsonResponse.getGetMuestraTecnicosAlmacenListResult())));
                    Iterator<List<GetMuestraTecnicosAlmacenListResult>> itdata = array.dataTECSEC.iterator();
                    while (itdata.hasNext()) {
                        List<GetMuestraTecnicosAlmacenListResult> dat = itdata.next();
                        datos = new String[dat.size() + 1];
                        int j = 1;
                        datos[0] = a;
                        for (int i = 0; i < dat.size(); i++) {
                            datos[j] = dat.get(i).getNombre();
                            Array.Clv_TecSecR.add(j, dat.get(i).getClvTecnico());
                            j = j + 1;
                        }
                        adapterTecSecR = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, datos);
                        TecSec.setAdapter(adapterTecSecR);

                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del tecnico secundario "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONTecSecReport> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getValidaOrdSer(final Context context, final JSONObject jsonObjet, final JSONObject jsonObjet1) {

        Call<JsonObject> call = services.RequestPost(context, jsonObjet1).getVALIOrdSer();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {

                        System.out.println("Entra");
                        String string1 = String.valueOf(response.body().getAsJsonPrimitive("GetSP_ValidaGuardaOrdSerAparatosResult"));



                    if (String.valueOf(response.body().getAsJsonPrimitive("GetSP_ValidaGuardaOrdSerAparatosResult")).length() == 2||visita==1) {
                        getChecaCAMDO(context, jsonObjet);
                    }
                  /*  if (visita==1){
                        getChecaCAMDO(context, jsonObjet);
                    }
                    else {

                        dialogEjecutar.dismiss();
                        ErrorMensaje(context,"Error"+ string1);
                        EjecutarOrdenes.eject.setEnabled(true);
                    }*/
                }else{
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                    if(visita == 1){
                        dialogVisitaOrd.dismiss();
                    }else if (ejecutada == 1){
                        dialogEjecutar.dismiss();
                    }

                    EjecutarOrdenes.eject.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                dialogEjecutar.dismiss();
                EjecutarOrdenes.eject.setEnabled(true);
            }
        });
    }

    public void getValidaTrabajos(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getVALIOrdSer();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    System.out.println("Entra");
                     stringValidaTrabajos = String.valueOf(response.body().getAsJsonPrimitive("GetSP_ValidaGuardaOrdSerAparatosResult"));

                }else{
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
            }
        });
    }

    public void getChecaCAMDO(final Context context, final JSONObject jsonObject1) {
        Service service = null;
        try {
            service = services.getChecaCAMDOService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.getChecaCAMDO();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                if (response1.code() == 200) {
                    JsonObject jsonObject = response1.body().getAsJsonObject("GetCheca_si_tiene_camdoResult");
                    GetCheca_si_tiene_CAMDOModel checa = new GetCheca_si_tiene_CAMDOModel(
                            jsonObject.get("Error").getAsString()
                    );
                    if (checa.Error.equals("0")) {
                        try{
                            JSONObject jsonObject2 = new JSONObject();
                            JSONObject jsonObject3 = new JSONObject();
                            jsonObject2.put("ClvOrden",  Util.getClvOrden(Util.preferences));
                            jsonObject2.put("ClvUsuario", UserModel.Id_Usuario);
                            jsonObject2.put("Status", ejecutarStatus);
                            jsonObject3.put("objNueRelOrdenUsuario", jsonObject2);
                            getAddRelOrdUsu(context, jsonObject1,jsonObject3);
                        }catch (Exception e){}

                    } else {
                        dialogEjecutar.dismiss();
                        EjecutarOrdenes.eject.setEnabled(true);
                        Toast.makeText(context, "Error: " + checa.Error, Toast.LENGTH_LONG).show();
                    }
                }else{
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                    if(visita == 1){
                        dialogVisitaOrd.dismiss();
                    }else if (ejecutada == 1){
                        dialogEjecutar.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
            }
        });
    }

    public void getAddRelOrdUsu(final Context context, final JSONObject jsonObject, final JSONObject jsonObject1) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject1).getADDRELORDUSU();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        if (ejecutarStatus.equals("E")) {
                            getDeepMODORDSER(context, jsonObject);
                        }
                        if (ejecutarStatus.equals("V")) {
                            getDeepMODORDSERV(context, jsonObject);
                        }
                    }catch (Exception e){
                        /*if (ejecutarStatus.equals("E")) {
                            getDeepMODORDSER(context, jsonObject);
                        }
                        if (ejecutarStatus.equals("V")) {
                            getDeepMODORDSERV(context, jsonObject);
                        }*/
                    }
                } else {
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                    if(visita == 1){
                        dialogVisitaOrd.dismiss();
                    }else if (ejecutada == 1){
                        dialogEjecutar.dismiss();
                    }
                    EjecutarOrdenes.eject.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                dialogEjecutar.dismiss();
                EjecutarOrdenes.eject.setEnabled(true);
            }
        });
    }

    public void getDeepMODORDSER(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getMODORDSER();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    getGuardaHora(context);
                } else {
                    ///ErrorMensaje(context,"Error, aparatos no enviados");
                    dialogEjecutar.dismiss();
                    EjecutarOrdenes.eject.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                dialogEjecutar.dismiss();
                EjecutarOrdenes.eject.setEnabled(true);
            }
        });
    }

    public void getDeepMODORDSERV(final Context context, final JSONObject json) {
        Service service = null;
        service = services.getDeppMODORDSERServiceVisita(context, json);
        Call<JsonObject> call = service.getMODORDSER();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                if (response1.code() == 200) {
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("ClvOrden",  Util.getClvOrden(Util.preferences));
                        jsonObject.put("Op", "M");
                        jsonObject.put("Status", ejecutarStatus);
                        jsonObject.put("Op2", 0);
                        getGuardaOrdSerAparatos(context,jsonObject);
                    }catch (Exception e){}


                } else {
                    ErrorMensaje(context,"Error, aparatos no enviados");
                    if(visita == 1){
                        dialogVisitaOrd.dismiss();
                    }else if (ejecutada == 1){
                        dialogEjecutar.dismiss();
                    }
                    EjecutarOrdenes.eject.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                dialogEjecutar.dismiss();
                EjecutarOrdenes.eject.setEnabled(true);
            }
        });
    }

    public void getGuardaHora(final Context context) {
        Service service = null;
        try {
            service = services.getGuardaHoraService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.getGuardaHora();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                if (response1.code() == 200) {
                    try{
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("ClvOrden",  Util.getClvOrden(Util.preferences));
                        jsonObject.put("Op", "M");
                        jsonObject.put("Status", ejecutarStatus);
                        jsonObject.put("Op2", 0);
                        getGuardaOrdSerAparatos(context,jsonObject);
                    }catch (Exception e){}
                } else {
                    //ErrorMensaje(context,"Error al guardar");
                    dialogEjecutar.dismiss();
                    EjecutarOrdenes.eject.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                dialogEjecutar.dismiss();
                EjecutarOrdenes.eject.setEnabled(true);
            }
        });
    }

    public void getGuardaOrdSerAparatos(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getGUARDAOrdSerAparatos();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {

                    addLlenaBitacora(context);
                } else {
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                    if(visita == 1){
                        dialogVisitaOrd.dismiss();
                    }else if (ejecutada == 1){
                        dialogEjecutar.dismiss();
                    }
                    EjecutarOrdenes.eject.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                dialogEjecutar.dismiss();
                EjecutarOrdenes.eject.setEnabled(true);
            }
        });
    }

    public void addLlenaBitacora(final Context context) {
        Service service = null;
        try {
            service = services.getAddLlenaBitacoraService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.getLLENABITACORA_ORD();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                int IS = 0;
                if (response1.code() == 200) {
                    if (String.valueOf(response1.body().getAsJsonPrimitive("AddSP_LLena_Bitacora_OrdenesResult")).equals("-1")) {
                        Iterator<List<GetBUSCADetOrdSerListResult>> itData = Array.dataTrabajos.iterator();
                        List<GetBUSCADetOrdSerListResult> dat = itData.next();
try{
    if (ejecutarStatus.equals("E")) {

        for (int a = 0; a < dat.size(); a++) {

            String palabra = dat.get(a).getDescripcion();
            String[] caracteres = palabra.split(" ");
            Log.d("caracteres0",caracteres[0]);
            Log.d("caracteres1",caracteres[1]);
            if (caracteres[0].equals("ISNET") || caracteres[0].equals("ISDIG") || caracteres[0].equals("ISTVA")|| caracteres[0].equals("CAPAG")) {
                IS = 1;
            }
        }
        if (IS == 1) {

            GuardaCoordenadas(context);
        } else {
            Toast.makeText(context, "Se ha guardado correctamente", Toast.LENGTH_LONG).show();
            getListOrd(context);
            dialogVisitaOrd.dismiss();
        }
    }
    if (ejecutarStatus.equals("V")) {
        //dialogEjecutar.dismiss();
        Toast.makeText(context, "Visita guardada correctamente", Toast.LENGTH_LONG).show();
        getListOrd(context);
        dialogVisitaOrd.dismiss();
    }
}catch (Exception e){
/*    if (ejecutarStatus.equals("E")) {

        for (int a = 0; a < dat.size(); a++) {

            String palabra = dat.get(a).getDescripcion();
            String[] caracteres = palabra.split(" ");
            Log.d("caracteres0",caracteres[0]);
            Log.d("caracteres1",caracteres[1]);
            if (caracteres[0].equals("ISNET") || caracteres[0].equals("ISDIG") || caracteres[0].equals("ISTVA")) {
                IS = 1;
            }
        }
        if (IS == 1) {

            GuardaCoordenadas(context);
        } else {
            Toast.makeText(context, "Se ha guardado correctamente", Toast.LENGTH_LONG).show();
            dialogEjecutar.dismiss();
            getListOrd(context);
        }
    }
    if (ejecutarStatus.equals("V")) {
        //dialogEjecutar.dismiss();
        Toast.makeText(context, "Se ha guardado correctamente", Toast.LENGTH_LONG).show();
        getListOrd(context);
    }*/
}
                    }
                } else {
                    ErrorMensaje(context,"Error al guardar");
                    dialogEjecutar.dismiss();
                    EjecutarOrdenes.eject.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                dialogEjecutar.dismiss();
                EjecutarOrdenes.eject.setEnabled(true);
            }
        });
    }

    //Ejecutar Reporte//
    public void getValidaReporte(final Context context, final JSONObject jsonObject,final String fecha,final String hora ) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getValidaRep();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                if (response1.code() == 200) {
                    JsonObject jsonObject = response1.body().getAsJsonObject("GetDeepValidaQuejaCompaniaAdicResult");
                    GetDeepValidaQuejaCompaniaAdicModel checa = new GetDeepValidaQuejaCompaniaAdicModel(
                            jsonObject.get("BaseIdUser").getAsInt()
                    );
                    if (checa.getBaseIdUser() == 0) {
                        JSONObject objQuejas = new JSONObject();
                        JSONObject jsonObject1 = new JSONObject();
                        if(reporteStatus.equals("E")){
                            try{
                                objQuejas.put("Clv_Queja", Util.getClvQueja(Util.preferences));
                                objQuejas.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                                objQuejas.put("FechaProceso", "");
                                objQuejas.put("Fecha_Ejecucion", fechaEjecujtar + " " + horaEjecutar);
                                objQuejas.put("HP", "");
                                objQuejas.put("HV1", "");
                                objQuejas.put("HV2", "");
                                objQuejas.put("HV3", "");
                                objQuejas.put("IdUsuario", 1);
                                objQuejas.put("Observaciones", Obs);
                                objQuejas.put("Solucion", proble.getText());
                                objQuejas.put("Status", "E");
                                objQuejas.put("TecnicoCuadrilla", TecSecSeleccion);
                                objQuejas.put("Visita", false);
                                objQuejas.put("Visita1", "");
                                objQuejas.put("Visita2", "");
                                objQuejas.put("Visita3", "");
                                objQuejas.put("clvPrioridadQueja", clvP);
                                objQuejas.put("clvProblema",ClvTrabajoRequest );
                                objQuejas.put("clvProblema2", Clv_Sol);
                                jsonObject1.put("objQuejas", objQuejas);
                                getGuardaCampos(context,jsonObject1);
                            }catch (Exception e){}
                        }

                    }
                } else{

                    dialogReportes.dismiss();
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                dialogReportes.dismiss();
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
            }
        });
    }

    //horas//
    public void getGuardaHoraReporte(final Context context, final JSONObject jsonObject,final String fecha,final String hora ) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getHiHf();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                if (response1.code() == 200) {
                    String string1 =String.valueOf(response1.body().getAsJsonPrimitive("GetGuardaHoraOrdenResult"));
                    if (string1.equals("0")) {
try{
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("ClvQueja", Util.getClvQueja(Util.preferences));
    jsonObject.put("IdUsuario", 1);
    getValidaReporte(context,jsonObject,fecha,hora);
}catch (Exception e){}

                    }
                }else{
                    dialogReportes.dismiss();
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                dialogReportes.dismiss();
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
            }
        });
    }

    //guardar campos//
    public void getGuardaCampos(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getLLenaReporte();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                if (response1.code() == 200) {
             /*       if (String.valueOf(response1.body().getAsJsonPrimitive("UpdateQuejasResult")).equals(-1)) {
                        EjecutarReportes.dialogReportes.dismiss();
                        clavequeja = 0;
                        opcion = 1;
                        Util.preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                        Util.editor = Util.preferences.edit();
                        Util.editor.putString("TipoDescarga", "Q");
                        Util.editor.commit();
                        Toast.makeText(context, "Reporte guardado correctamente", Toast.LENGTH_LONG).show();
                        getListQuejas(context);

                    }*/
                    if(repotteVisita==1){
                        dialogVisitaRepo.dismiss();
                    }else if (reporteEjecutada ==1) {
                        dialogReportes.dismiss();
                    }

                    Toast.makeText(context, "Reporte guardado correctamente", Toast.LENGTH_LONG).show();
                    getListQuejas(context);


                }else
                    {
                        ErrorMensaje(context,"Se ha producido un error, verifique su conexión e intente nuevamente");
                        getListQuejas(context);

                        if(repotteVisita==1){
                            dialogVisitaRepo.dismiss();
                        }else if (reporteEjecutada ==1) {
                            dialogReportes.dismiss();
                        }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                dialogReportes.dismiss();
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
            }
        });
    }

    public void GuardaCoordenadas(final Context context) {

        Service service = null;
        try {
            service = services.getGuardaCoordenadasService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<JsonObject> call = service.getGuardaCoordenadas();
        call.enqueue(new Callback<JsonObject>() {


            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                reintentaB = 0;

                if (response1.code() == 200) {
                    dialogEjecutar.dismiss();
                    Toast.makeText(context, "Orden guardado correctamente", Toast.LENGTH_LONG);
                    getListOrd(context);
                } else {
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
                    dialogEjecutar.dismiss();
                    EjecutarOrdenes.eject.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
            }
        });
    }

  /*  public void ConsultaIp(final Context context) {
        Service service = null;
        try {
            service = services.getConsultaIpService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.getConsultaIp();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                if (response1.code() == 200) {
                    jsonConsultaIp = new JsonObject();
                    jsonConsultaIp = response1.body().getAsJsonObject("GetConsultaIpPorContratoResult");
                    ConsultaIpModel user = new ConsultaIpModel(
                            jsonConsultaIp.get("AplicaReintentar").getAsBoolean(),
                            jsonConsultaIp.get("Msg").getAsString()
                    );
                    reintentarComando = String.valueOf(user.AplicaReintentar);
                    msgComando = user.Msg;
                    for (int a = 0; a < 1; a++) {
                        if (reintentarComando.equals("true")) {
                            reiniciar.setEnabled(true);
                            msgEjecutarOrd.setText(Request.msgComando);
                        } else {
                            if (msgComando.length() > 3) {

                                msgEjecutarOrd.setText(msgComando);
                                dialogEjecutar.dismiss();
                                ((Activity) context).finish();
                            } else {
                                Login.esperar(3);
                                retry(call);
                            }
                        }
                    }
                }else{
                    dialogEjecutar.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
            }

            public void retry(Call<JsonObject> call) {
                call.clone().enqueue(this);
            }
        });
    }

    public void ReintentarComando(final Context context) {
        reintentaB = 0;
        Service service = null;
        try {
            service = services.getReintentarComandoService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.getReintentaComando();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                if (response1.code() == 200) {
                    ConsultaIp(context);
                    msgEjecutarOrd.setText("");
                    reiniciar.setEnabled(true);
                  //  dialogEjecutar.dismiss();
                }else{
                    dialogEjecutar.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }*/

    public void SetCambioAparato(final Context context, final JSONObject jsonObject, final Activity activity) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getCAPAT();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Toast.makeText(context, "Se ha guardado el aparato correctamente", Toast.LENGTH_SHORT).show();


                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
                        jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                        jsonObject.put("OP2", 0);
                        jsonObject.put("OPCION", "M");
                        jsonObject.put("STATUS", "E");
                        getValidaTrabajos(getApplicationContext(),jsonObject);
                        //dialogCAPAT.dismiss();
                        //activity.finish();
                    }catch (Exception e){}
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("CLV_ORDEN",  Util.getClvOrden(Util.preferences));
                        jsonObject.put("Clv_Tecnico", Util.getClvTec(Util.preferences));
                        jsonObject.put("OP2", 0);
                        jsonObject.put("OPCION", "M");
                        jsonObject.put("STATUS", "E");
                        getValidaTrabajos(getApplicationContext(),jsonObject);
                        //dialogCAPAT.dismiss();
                        //activity.finish();
                    }catch (Exception r){}
                } else {
                    ErrorMensaje(context,"Error al agregar el aparato "+response.message());
                    try {
                        dialogCAPAT.dismiss();
                    }catch (Exception e){}
                    try {
                        dialogTrabajos.dismiss();
                    }catch (Exception r){}
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
                try {
                    dialogCAPAT.dismiss();
                }catch (Exception e){}
                try {
                    dialogTrabajos.dismiss();
                }catch (Exception r){}
            }
        });
    }

    public void send_aparat(final Context context) {
        adaptertrabajos.norec();
        Service service = null;
        try {
            service = services.recibiapar(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.noent();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                if (response1.code() == 200) {
                    //Toast.makeText(getApplicationContext(), "Envío de aparatos con éxito", Toast.LENGTH_SHORT);
                }else{
                    ErrorMensaje(context,"Error al mandar aparatos recibidos "+response1.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }

        });
    }

    public void envioTokenTecnicoRequest(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).envtokenfire();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
              if(response.code()!=200){
                  ErrorMensaje(context,"Error"+response.message());
              }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error"+t.getMessage());
            }

        });
    }


    public void getChecaExt(final Context context) {
        Service service = null;
        service = services.getChecaExtService(context);
        Call<JsonObject> call = service.getChecaExt();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject userJson = response.body().getAsJsonObject("GetUspChecaSiTieneExtensionesResult");
                    ChecaSiExtencionesModel user = new ChecaSiExtencionesModel(
                            userJson.get("BND").getAsInt(),
                            userJson.get("NUMEXT").getAsInt()
                    );
                    if (user.BND == 1) {
                        MuestraBit(context);
                        LlenaExt(context);
                        nExtenciones = user.BND;

                    } else {
                        MuestraBit(context);
                        extencionesMat = false;
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del extenciones "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void MuestraBit(final Context context) {
        Service service = null;
        service = services.getMuestraBitService(context);
        Call<JSONDetalleBitacora> call = service.getMuestraBit();
        call.enqueue(new Callback<JSONDetalleBitacora>() {
            @Override
            public void onResponse(Call<JSONDetalleBitacora> call, Response<JSONDetalleBitacora> response1) {
                if (response1.code() == 200) {
                    array.detalleBit.clear();
                    array.detalleBit.add(0, "---Seleccionar---");
                    int j = 1;
                    JSONDetalleBitacora jsonResponse = response1.body();
                    array.dataDetBit = new ArrayList<List<DetalleBitacoraModel>>(asList(jsonResponse.detalleBitacoraModel()));
                    Iterator<List<DetalleBitacoraModel>> itData = array.dataDetBit.iterator();
                    while (itData.hasNext()) {
                        List<DetalleBitacoraModel> dat = itData.next();

                        for (int i = 0; i < dat.size(); i++) {
                            array.detalleBit.add(j, dat.get(i).Descripcion);
                            j = j + 1;
                        }

                    }
                    try {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array.detalleBit);
                        descripcionMat.setAdapter(arrayAdapter);
                        descripcionMat.setSelection(posDescMat);
                    }catch (Exception e){
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array.detalleBit);
                        descripcionMatR.setAdapter(arrayAdapter);
                        descripcionMatR.setSelection(MaterialesReportes.posDescMatR);
                    }

                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response1.message());
                }
            }

            @Override
            public void onFailure(Call<JSONDetalleBitacora> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }

        });
    }

    public void DetalleBit(final Context context) {
        Service service = null;
        service = services.getDetalleBitService(context);
        Call<JSONDescripcionArticulosBit> call = service.getDetalleBit();
        call.enqueue(new Callback<JSONDescripcionArticulosBit>() {
            @Override
            public void onResponse(Call<JSONDescripcionArticulosBit> call, Response<JSONDescripcionArticulosBit> response1) {
                if (response1.code() == 200) {
                    array.descripcionArtBit.clear();
                    array.descripcionArtBit.add(0, "---Seleccionar---");
                    int j = 1;
                    JSONDescripcionArticulosBit jsonResponse = response1.body();
                    array.dataDetArtBit = new ArrayList<List<DescripcionArticuloModel>>(asList(jsonResponse.descripcionArticuloModel()));
                    Iterator<List<DescripcionArticuloModel>> itData = array.dataDetArtBit.iterator();
                    while (itData.hasNext()) {
                        List<DescripcionArticuloModel> dat = itData.next();

                        for (int i = 0; i < dat.size(); i++) {
                            array.descripcionArtBit.add(j, dat.get(i).Nombre);
                            j = j + 1;
                        }

                    }
                    try {
                        clasificacionMat.setEnabled(true);
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array.descripcionArtBit);
                        MaterialesOrdenes.clasificacionMat.setAdapter(arrayAdapter);
                        clasificacionMat.setSelection(posClasMat);
                    }catch (Exception e){
                        clasificacionMatR.setEnabled(true);
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array.descripcionArtBit);
                        clasificacionMatR.setAdapter(arrayAdapter);
                        clasificacionMatR.setSelection(posClasMatR);
                    }

                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response1.message());
                }
            }

            @Override
            public void onFailure(Call<JSONDescripcionArticulosBit> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }

        });
    }

    public void LlenaExt(final Context context) {
        Service service = null;
        service = services.getLlenaExtService(context);
        Call<JSONLlenaExtenciones> call = service.getLlenaExt();
        call.enqueue(new Callback<JSONLlenaExtenciones>() {
            @Override
            public void onResponse(Call<JSONLlenaExtenciones> call, Response<JSONLlenaExtenciones> response1) {
                if (response1.code() == 200) {
                    MaterialesOrdenes.extMat.setVisibility(View.VISIBLE);
                    array.descripcionExt.clear();
                    array.descripcionExt.add(0, "---Seleccionar---");
                    int j = 1;
                    JSONLlenaExtenciones jsonResponse = response1.body();
                    array.dataLlenaExt = new ArrayList<List<LlenaExtencionesModel>>(asList(jsonResponse.llenaExtencionesModel()));
                    Iterator<List<LlenaExtencionesModel>> itData = array.dataLlenaExt.iterator();
                    while (itData.hasNext()) {
                        List<LlenaExtencionesModel> dat = itData.next();
                        for (int i = 0; i < dat.size(); i++) {
                            array.descripcionExt.add(j, dat.get(i).DESCRIPCION);
                            j = j + 1;
                        }
                    }
                    extencionesMat = true;
                    try {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array.descripcionExt);
                        MaterialesOrdenes.spinnerExtMat.setAdapter(arrayAdapter);
                        spinnerExtMat.setSelection(posExtMat);
                    } catch (Exception e) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array.descripcionExt);
                        spinnerExtMatR.setAdapter(arrayAdapter);
                        spinnerExtMatR.setSelection(posExtMatR);
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response1.message());
                }
            }

            @Override
            public void onFailure(Call<JSONLlenaExtenciones> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }

        });
    }

    public void getTipoMat(final Context context) {
        Service service = null;
        service = services.getTipoMatService(context);
        Call<JsonObject> call = service.getTipoMat();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject userJson = response.body().getAsJsonObject("GetSoftv_ObtenTipoMaterialResult");
                    TipoMaterialModel user = new TipoMaterialModel(
                            userJson.get("Tipo").getAsString()
                    );
                    if (user.Tipo.equals("Piezas")) {
                        MaterialesOrdenes.piezasMat.setVisibility(View.VISIBLE);
                        MaterialesOrdenes.metrosMat.setVisibility(View.INVISIBLE);
                        pieza = true;
                    } else {
                        MaterialesOrdenes.metrosMat.setVisibility(View.VISIBLE);
                        MaterialesOrdenes.piezasMat.setVisibility(View.INVISIBLE);
                        pieza = false;
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getValidaPreDes(final Activity activity, final Context context) {
        Service service = null;
        service = services.getValidaPreService(context);
        Call<JsonObject> call = service.getValidaPre();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    String dato;
                    dato = String.valueOf(response.body().getAsJsonPrimitive("ValidaExisteTblPreDescargaMaterialResult"));
                    if (dato.equals("0")) {
                        addPreDes(activity, context);
                    }
                    if (dato.equals("1")) {
                        Toast.makeText(context, "Ya existe ese tipo de material", Toast.LENGTH_SHORT).show();
                        getPredescarga(activity, context);
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void addPreDes(final Activity activity, final Context context) {
        Service service = null;
        service = services.addPreDescargaService(context);
        Call<JsonObject> call = service.addPreDescarga();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Toast.makeText(context, "Se agrego correctamente", Toast.LENGTH_SHORT).show();
                    getPredescarga(activity, context);
                } else {
                    ErrorMensaje(context,"Error al agregar datos del materiales "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getPredescarga(final Activity activity, final Context context) {
        Service service = null;
        service = services.getPreDescargaService(context);
        Call<JSONPreDescarga> call = service.getPreDescarga();
        call.enqueue(new Callback<JSONPreDescarga>() {
            @Override
            public void onResponse(Call<JSONPreDescarga> call, Response<JSONPreDescarga> response) {

                EliminarMaterialAdapter adapter;


                try{
                    array.listaTabla.clear();
                }catch (Exception e){}



                if (response.code() == 200) {
                    JSONPreDescarga jsonResponse = response.body();
                    array.dataPreDescarga = new ArrayList<List<dameTblPreDescargaMaterialResultModel>>(asList(jsonResponse.getdameTblPreDescargaMaterialResultModel()));
                    Iterator<List<dameTblPreDescargaMaterialResultModel>> itdata = array.dataPreDescarga.iterator();
                    while (itdata.hasNext()) {
                        List<dameTblPreDescargaMaterialResultModel> dat = itdata.next();
                        for (int i = 0; i < dat.size(); i++) {
                            array.listaTabla.add(new ArrayList<String>());
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getNombre()));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).cantidadUtilizada));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getNoExt()));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).noArticulo));
                        }
                    }
                    try {
                        if(array.listaTabla.size()!=0) {
                            MaterialesOrdenes.elimarMaterialesList.setVisibility(View.VISIBLE);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1);
                            MaterialesOrdenes.elimarMaterialesList.setLayoutManager(layoutManager);
                            adapter = new EliminarMaterialAdapter(array.listaTabla, context, activity);
                            MaterialesOrdenes.elimarMaterialesList.setAdapter(adapter);
                        }else{
                            MaterialesOrdenes.elimarMaterialesList.setVisibility(View.GONE);
                        }

                    }catch (Exception e){
                       Toast.makeText(context,"Error al cargar Tabla" ,Toast.LENGTH_SHORT ).show();
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONPreDescarga> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //////////////////
    public void DetalleBitR(final Context context) {


        Service service = null;
        service = services.getDetalleBitRService(context);

        Call<JSONDescripcionArticulosBit> call = service.getDetalleBit();
        call.enqueue(new Callback<JSONDescripcionArticulosBit>() {

            @Override
            public void onResponse(Call<JSONDescripcionArticulosBit> call, Response<JSONDescripcionArticulosBit> response1) {
                if (response1.code() == 200) {
                    array.descripcionArtBit.clear();
                    array.descripcionArtBit.add(0, "---Seleccionar---");
                    int j = 1;
                    JSONDescripcionArticulosBit jsonResponse = response1.body();
                    array.dataDetArtBit = new ArrayList<List<DescripcionArticuloModel>>(asList(jsonResponse.descripcionArticuloModel()));
                    Iterator<List<DescripcionArticuloModel>> itData = array.dataDetArtBit.iterator();
                    while (itData.hasNext()) {
                        List<DescripcionArticuloModel> dat = itData.next();

                        for (int i = 0; i < dat.size(); i++) {
                            array.descripcionArtBit.add(j, dat.get(i).Nombre);
                            j = j + 1;
                        }

                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array.descripcionArtBit);
                    clasificacionMatR.setAdapter(arrayAdapter);
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response1.message());
                }
            }

            @Override
            public void onFailure(Call<JSONDescripcionArticulosBit> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }

        });
    }

    public void getTipoMatR(final Context context) {
        Service service = null;
        service = services.getTipoMatRService(context);
        Call<JsonObject> call = service.getTipoMat();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject userJson = response.body().getAsJsonObject("GetSoftv_ObtenTipoMaterialResult");
                    TipoMaterialModel user = new TipoMaterialModel(
                            userJson.get("Tipo").getAsString()
                    );
                    if (user.Tipo.equals("Piezas")) {
                        MaterialesReportes.piezasMatR.setVisibility(View.VISIBLE);
                        MaterialesReportes.metrosMatR.setVisibility(View.INVISIBLE);
                        pieza = true;
                    } else {
                        MaterialesReportes.metrosMatR.setVisibility(View.VISIBLE);
                        MaterialesReportes.piezasMatR.setVisibility(View.INVISIBLE);
                        pieza = false;
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getValidaPreDesR(final Activity activity, final Context context) {
        Service service = null;
        service = services.getValidaPreRService(context);
        Call<JsonObject> call = service.getValidaPre();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    String dato;
                    dato = String.valueOf(response.body().getAsJsonPrimitive("ValidaExisteTblPreDescargaMaterialResult"));
                    if (dato.equals("0")) {
                        addPreDesR(activity, context);
                    }
                    if (dato.equals("1")) {
                        Toast.makeText(context, "Ya existe ese tipo de material", Toast.LENGTH_SHORT).show();
                        getPredescargaR(activity, context);
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void addPreDesR(final Activity activity, final Context context) {
        Service service = null;
        service = services.addPreDescargaRService(context);
        Call<JsonObject> call = service.addPreDescarga();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Toast.makeText(context, "Se agrego correctamente", Toast.LENGTH_SHORT).show();
                    getPredescargaR(activity, context);
                } else {
                    ErrorMensaje(context,"Error al agregar datos del materiales "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getPredescargaR(final Activity activity, final Context context) {
        Service service = null;
        service = services.getPreDescargaRService(context);
        Call<JSONPreDescarga> call = service.getPreDescarga();
        call.enqueue(new Callback<JSONPreDescarga>() {
            @Override
            public void onResponse(Call<JSONPreDescarga> call, Response<JSONPreDescarga> response) {

                EliminarMaterialAdapter adapter;
                try{
                    array.listaTabla.clear();
                }catch (Exception e){}


                if (response.code() == 200) {
                    JSONPreDescarga jsonResponse = response.body();
                    array.dataPreDescarga = new ArrayList<List<dameTblPreDescargaMaterialResultModel>>(asList(jsonResponse.getdameTblPreDescargaMaterialResultModel()));
                    Iterator<List<dameTblPreDescargaMaterialResultModel>> itdata = array.dataPreDescarga.iterator();
                    while (itdata.hasNext()) {
                        List<dameTblPreDescargaMaterialResultModel> dat = itdata.next();
                        for (int i = 0; i < dat.size(); i++) {
                            array.listaTabla.add(new ArrayList<String>());
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getNombre()));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).cantidadUtilizada));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getNoExt()));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getNoArticulo()));
                        }
                    }

                    try {
                        if(array.listaTabla.size()!=0) {
                            MaterialesReportes.elimarMaterialesListR.setVisibility(View.VISIBLE);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1);
                            MaterialesReportes.elimarMaterialesListR.setLayoutManager(layoutManager);
                            adapter = new EliminarMaterialAdapter(array.listaTabla, context, activity);
                            MaterialesReportes.elimarMaterialesListR.setAdapter(adapter);
                        }else{
                            MaterialesReportes.elimarMaterialesListR.setVisibility(View.GONE);
                        }
                    }catch (Exception e){}

                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONPreDescarga> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    //////////
    public void addFirma(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).addFirma();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("asd","asd");
                if (response.code() == 200) {
                    Toast.makeText(context, "Se agrego correctamente", Toast.LENGTH_SHORT).show();
                    validaExisteFirmaBool = true;
                } else {
                    ErrorMensaje(context,"Error al agregar firma "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void eliminarPreDescarga(final Context context,final JSONObject jsonObject,final Activity activity) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).eliminaPreDescarga();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    if(Util.getTipoDescarga(Util.preferences).equals("O")){
                        getPredescarga(activity,context );
                    }else if(Util.getTipoDescarga(Util.preferences).equals("Q")){
                        getPredescargaR(activity,context );
                    }

                } else {
                    Toast.makeText(context, "Error al eliminar material "+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(context, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void TryDeepConsulta1(JsonObject userJson) {
        try {
            DeepConsModel user1 = new DeepConsModel(
                    userJson.get("Clv_FACTURA").getAsInt(),
                    userJson.get("Contrato").getAsInt(),
                    userJson.get("ContratoCom").getAsString(),
                    userJson.get("STATUS").getAsString(),
                    userJson.get("Obs").getAsString(),
                    userJson.get("Clv_Orden").getAsInt(),
                    userJson.get("Clv_TipSer").getAsInt(),
                    userJson.get("Fec_Sol").getAsString(),
                    userJson.get("Visita1").getAsJsonNull().toString()
            );
        } catch (Exception f) {
            DeepConsModel user1 = new DeepConsModel(
                    userJson.get("Clv_FACTURA").getAsInt(),
                    userJson.get("Contrato").getAsInt(),
                    userJson.get("ContratoCom").getAsString(),
                    userJson.get("STATUS").getAsString(),
                    userJson.get("Obs").getAsJsonNull().toString(),
                    userJson.get("Clv_Orden").getAsInt(),
                    userJson.get("Clv_TipSer").getAsInt(),
                    userJson.get("Fec_Sol").getAsString(),
                    userJson.get("Visita1").getAsJsonNull().toString()
            );
        }
    }

    public void TryDeepConsulta(JsonObject userJson) {
        try {
            DeepConsModel user = new DeepConsModel(
                    userJson.get("Clv_FACTURA").getAsInt(),
                    userJson.get("Contrato").getAsInt(),
                    userJson.get("ContratoCom").getAsString(),
                    userJson.get("STATUS").getAsString(),
                    userJson.get("Obs").getAsString(),
                    userJson.get("Clv_Orden").getAsInt(),
                    userJson.get("Clv_TipSer").getAsInt(),
                    userJson.get("Fec_Sol").getAsString(),
                    userJson.get("Visita1").getAsString()
            );
        } catch (Exception e) {
            DeepConsModel user = new DeepConsModel(
                    userJson.get("Clv_FACTURA").getAsInt(),
                    userJson.get("Contrato").getAsInt(),
                    userJson.get("ContratoCom").getAsString(),
                    userJson.get("STATUS").getAsString(),
                    userJson.get("Obs").getAsJsonNull().toString(),
                    userJson.get("Clv_Orden").getAsInt(),
                    userJson.get("Clv_TipSer").getAsInt(),
                    userJson.get("Fec_Sol").getAsString(),
                    userJson.get("Visita1").getAsString()
            );
        }
    }

    //Validar uso de firma//
    public void getValidaFirma(final Context context) {
        Service service = null;
        try {
            service = services.getValidaFirma(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JsonObject> call = service.validFirma();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject userJson = response.body().getAsJsonObject();
                    ValidacionFirma user = new ValidacionFirma(
                            userJson.get("TrabajosFirmaResult").getAsString());
                    validFirma = Integer.parseInt(user.getTrabajosFirmaResult());
                }
                        if (validFirma == 1) {
                            firma=true;
                        }else if (validFirma == 0){
                            firma=false;
                        }

                    }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error  "+t.getMessage());
            }
        });
    }
    //////////MACWAM/////////////////
    public void ValidaMACWAM(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).validaMACWAM();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject userJson = response.body().getAsJsonObject("GetValidaRequiereMacWanResult");
                    //Introduccion de datos del request en el Modelo para poder usarlos
                    ValidaMACWAMMODEL user = new ValidaMACWAMMODEL(
                            userJson.get("Resultado").getAsBoolean()
                    );
                    if(user.isResultado()==true){
                        MACWAM = true;
                        try{
                            constraintLayoutMACWAM.setVisibility(View.VISIBLE);
                            AsignarAparato.MACWAMText.setHeight(AsignarAparato.spinnerAparato.getHeight());
                            try{
                                CambioAparato.MACWAMTextCambioAparato.setHeight(CambioAparato.aparato.getHeight());
                                CambioAparato.textView35.setVisibility(View.VISIBLE);
                                CambioAparato.MACWAMTextCambioAparato.setVisibility(View.VISIBLE);
                            }catch (Exception e){}
                            try{
                                JSONObject jsonObject1 = new JSONObject();
                                JSONObject jsonObject2 = new JSONObject();
                                jsonObject1.put("Clv_Aparato",idArticuloasignado);
                                jsonObject2.put("ObjRelMacwan",jsonObject1);
                                GetMACWAM(context,jsonObject2);
                            }catch (Exception e){}
                        }catch (Exception e){
                            CambioAparato.textView35.setVisibility(View.VISIBLE);
                            CambioAparato.MACWAMTextCambioAparato.setVisibility(View.VISIBLE);
                        }
                    }else {
                        MACWAM=false;
                        constraintLayoutMACWAM.setVisibility(View.GONE);
                    }
                } else {
                    ErrorMensaje(context,"Error al validar MACWAN "+response.message());

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void GetMACWAM(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getMACWAM();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject userJson = response.body().getAsJsonObject("ConMacWanbyClv_AparatoResult");
                    //Introduccion de datos del request en el Modelo para poder usarlos
                    try{
                        GetMACWAMModel user = new GetMACWAMModel(
                                userJson.get("MacWan").getAsString()
                        );
                        try {
                            MACWAMText.setText(user.getMACWAM());
                        }catch (Exception e){}
                        try{
                            CambioAparato.MACWAMTextCambioAparato.setText(user.getMACWAM());
                        }catch (Exception q){}
                    }catch (Exception e){
                        GetMACWAMModel usernull = new GetMACWAMModel(
                                userJson.get("MacWan").getAsJsonNull().toString()
                        );

                    }

                } else {
                    ErrorMensaje(context,"Error al conseguir MACWAN "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void AsignaMACWAM(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).asignaMACWAM();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Toast.makeText(context, "Aparatos agregados", Toast.LENGTH_LONG).show();
                    try{
                        dialogAsignacion.dismiss();
                        finish();}catch (Exception e){}
                } else {
                    ErrorMensaje(context,"Error al asignar MACWAN "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void PreguntaMedios(final Context context, final JSONObject jsonObject) {
        Call<JSONPregunta> call = services.RequestPost(context, jsonObject).getPregunta();
        call.enqueue(new Callback<JSONPregunta>() {
            @Override
            public void onResponse(Call<JSONPregunta> call, Response<JSONPregunta> response) {
                Log.d("asd", "asd");
                requierePregunta = false;
                if (response.code() == 200) {
                    JSONPregunta jsonResponse = response.body();
                    try {
                        array.dataPregunta.clear();
                    } catch (Exception e) {
                    }
                    array.dataPregunta = new ArrayList<List<RequierePregunta>>(asList(jsonResponse.getDamePreguntaMedioPorOrdenResult.getRequierePregunta()));
                    Iterator<List<RequierePregunta>> itData = array.dataPregunta.iterator();
                    while (itData.hasNext()) {
                        List<RequierePregunta> dat = (List<RequierePregunta>) itData.next();
                        for (int i = 0; i < dat.size(); i++) {
                            requierePregunta = dat.get(i).RequierePregunta;
                        }
                    }
                    if (requierePregunta == true) {
                        try {
                            array.dataMediosPregunta.clear();
                            array.medioPregunta.clear();
                        } catch (Exception e) {
                        }


                        array.dataMediosPregunta = new ArrayList<List<mediosPregunta>>(asList(response.body().getDamePreguntaMedioPorOrdenResult.getmediosPregunta()));
                        Iterator<List<mediosPregunta>> itData1 = array.dataMediosPregunta.iterator();
                        array.medioPregunta.add("Seleccionar medio");
                        while (itData1.hasNext()) {
                            List<mediosPregunta> dat1 = (List<mediosPregunta>) itData1.next();
                            for (int i = 0; i < dat1.size(); i++) {
                                array.medioPregunta.add(dat1.get(i).getDescripcion());
                            }
                        }
                    } else {
                    }
                    getArbSer(context);
                }else{
                    ErrorMensaje(context,"Error al conseguir datos de instalacion "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONPregunta> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
                }
        });
    }
    public void validaExisteFirma(final Context context, final JSONObject jsonObject,final Activity activity) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).validaExisteFirma();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {

                    String dato;
                    dato = String.valueOf(response.body().getAsJsonPrimitive("ValidaExisteTblFirmaClienteResult"));
                    if (dato.equals("1")) {
                        validaExisteFirmaBool = true;

                    }
                    if (dato.equals("0")) {
                        validaExisteFirmaBool = false;
                        //Toast.makeText(context, "Es obligatoria la firma", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    ErrorMensaje(context,"Error al validar firma "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }

        });
    }
    public void validaExisteFirmaReporte(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).validaExisteFirma();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {

                    String dato;
                    dato = String.valueOf(response.body().getAsJsonPrimitive("ValidaExisteTblFirmaClienteResult"));
                    if (dato.equals("1")) {

                        if (reporteEjecutada == 1) {
                            //ejecutar
                            if (TrabajosReportes.solucion.getSelectedItem().toString().trim().equals("Seleccione tipo de solución")) {
                                Toast.makeText(context, "Seleccione un tipo de solución", Toast.LENGTH_SHORT).show();
                            } else {
                                if (proble.getText().toString().isEmpty()) {

                                    Toast.makeText(context, "Campo Problema real vacío", Toast.LENGTH_LONG).show();


                                } else {
                                    //EjecutarReportes.dialogReportes.show();
                                    reporteStatus = "E";
                                    try {
                                        JSONObject jsonObject = new JSONObject();
                                        jsonObject.put("Clv_orden", Util.getClvQueja(Util.preferences));
                                        jsonObject.put("horaFin", horaEjecutar);
                                        jsonObject.put("horaInicio", "08:00");
                                        jsonObject.put("opcion", 2);
                                        getGuardaHoraReporte(context, jsonObject, EjecutarReportes.fechaHoy, EjecutarReportes.horaHoy);
                                    } catch (Exception e) { }


                                }

                            }
                        }


                    }
                    else if (dato.equals("0")) {
                        validaExisteFirmaBool=false;
                        try{
                            EjecutarOrdenes.dialogEjecutar.dismiss();
                        }catch (Exception e) {
                            dialogReportes.dismiss();
                        }
                        Toast.makeText(context, "Es obligatoria la firma", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexión e intente nuevamente");
                    EjecutarOrdenes.dialogEjecutar.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }

        });
    }

    public void getArbSerValidar(final Context context,final Spinner spinnerTap,final Spinner spinnerNap, final TextView nap,final TextView tap) {
        Service service = null;
        try {
            service = services.getArbolSerService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONArbolServicios> call = service.getDataArbSer();
        call.enqueue(new Callback<JSONArbolServicios>() {
            @Override
            public void onResponse(Call<JSONArbolServicios> call, Response<JSONArbolServicios> response) {
                if (response.code() == 200) {

                  try{
                      array.nombreArbol.clear();
                      JSONArbolServicios jsonResponse = response.body();
                      array.dataArbSer = new ArrayList<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>>(asList(jsonResponse.GetMuestraArbolServiciosAparatosPorinstalarListResult()));
                      Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
                      while (itData4.hasNext()) {
                          List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();
                          for (int i = 0; i < dat4.size(); i++) {
                              array.nombreArbol.add(dat4.get(i).getNombre());
                              if(dat4.get(i).IdMedio==1){
                                  TAP=true;
                              }
                              if(dat4.get(i).IdMedio==2){
                                  NAP=true;
                              }
                          }
                      }

                      if(TAP==true){
                          tap.setVisibility(View.VISIBLE);
                          spinnerTap.setVisibility(View.VISIBLE);
                          try{
                              JSONObject jsonObject = new JSONObject();
                              jsonObject.put("contrato",ContratoReal);
                              getTap(context,jsonObject,spinnerTap);
                          }catch (Exception e){}


                      }else{
                          tap.setVisibility(View.INVISIBLE);
                          spinnerTap.setVisibility(View.INVISIBLE);
                      }




                      if(NAP==true){
                          nap.setVisibility(View.VISIBLE);
                          spinnerNap.setVisibility(View.VISIBLE);

                          try{
                              JSONObject jsonObject = new JSONObject();
                              jsonObject.put("contrato",ContratoReal);
                              getNapContrato(context,jsonObject,spinnerNap);
                          }catch (Exception e){}


                      }else{
                          nap.setVisibility(View.INVISIBLE);
                          spinnerNap.setVisibility(View.INVISIBLE);
                      }
                  }catch (Exception e){
                      TAP=false;
                      NAP=false;
                  }
                  } else {

                        ErrorMensaje(context,"Error al conseguir datos de la instalacion "+response.message());
                    }

            }

            @Override
            public void onFailure(Call<JSONArbolServicios> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getNap(final Context context, final JSONObject jsonObject, final Spinner spiner,final int Nap) {
        Call<JSONGETNAP> call = services.RequestPost(context, jsonObject).getNAP();
        call.enqueue(new Callback<JSONGETNAP>() {
            @Override
            public void onResponse(Call<JSONGETNAP> call, Response<JSONGETNAP> response) {
                if (response.code() == 200) {

                    JSONGETNAP jsonResponse = response.body();
                    Array.dataNap = new ArrayList<>(asList(jsonResponse.obtieneNapModel()));
                    Iterator<List<ObtieneNapModel>> itdata = Array.dataNap.iterator();
                    List<ObtieneNapModel> dat = itdata.next();
                    ArrayList<String>datos=new ArrayList<>();
                        for (int i = 0; i < dat.size(); i++) {
                            datos.add(dat.get(i).getClavetecnica());
                        }
                        adapterNap = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                        spiner.setAdapter(adapterNap);

                        if(Nap==0){

                        }else{
                            int position=0;
                            for(int i=0; i<dat.size(); i++){
                                if(dat.get(i).IdTap==(Nap)){
                                    position = i;
                                }
                            }
                            spiner.setSelection(position);

                        }

                } else {
                    ErrorMensaje(context,"Error al conseguir lista de técnicos secundarios "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONGETNAP> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getTap(final Context context, final JSONObject jsonObject, final Spinner spiner) {
        Call<JSONTAP> call = services.RequestPost(context, jsonObject).getTap();
        call.enqueue(new Callback<JSONTAP>() {
            @Override
            public void onResponse(Call<JSONTAP> call, Response<JSONTAP> response) {
                if (response.code() == 200) {
                    JSONTAP jsonResponse = response.body();

                    Array.dataTap = new ArrayList<>(asList(jsonResponse.obtieneTapModel()));
                    Iterator<List<ObtieneTapModel>> itdata = Array.dataTap.iterator();
                    ArrayList<String>datos=new ArrayList<>();
                    while (itdata.hasNext()) {
                        List<ObtieneTapModel> dat = itdata.next();

                        for (int i = 0; i < dat.size(); i++) {
                            datos.add(dat.get(i).getClavetecnica());
                        }
                        adapterTap = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, datos);
                        spiner.setAdapter(adapterTap);


                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir lista de técnicos secundarios "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONTAP> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getNapContrato(final Context context, final JSONObject jsonObject, final Spinner spiner) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getNapCpntrato();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    String dato;
                    dato = String.valueOf(response.body().getAsJsonPrimitive("GetObtieneNapContratoResult"));
                    if (dato.equals("0")) {
                        try{
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("contrato",ContratoReal);
                            getNap(context,jsonObject,spiner,Integer.parseInt(dato));
                        }catch (Exception e){}
                    }else{
                        try{
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("contrato",ContratoReal);
                            getNap(context,jsonObject,spiner,Integer.parseInt(dato));
                        }catch (Exception e){}
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir lista de técnicos secundarios "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void guardaNap(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).guardaNAP();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    String dato="0";
                    if (dato.equals("0")) {
                        Log.d("guardo","si");
                    }else{
                        Log.d("guardo","no");
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir lista de técnicos secundarios "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void guardaTap(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).guardaNAP();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    String dato="0";
                    if (dato.equals("0")) {
                        Log.d("guardo","si");
                    }else{
                        Log.d("guardo","no");
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir lista de técnicos secundarios "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }


}