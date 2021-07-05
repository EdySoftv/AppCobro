
package com.Softv.SoftvApp.SoftvApp.Request;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
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

import com.Softv.SoftvApp.SoftvApp.Activitys.AsignarAparato;
import com.Softv.SoftvApp.SoftvApp.Activitys.Login;
import com.Softv.SoftvApp.SoftvApp.Activitys.NAPTAP;
import com.Softv.SoftvApp.SoftvApp.Activitys.Orden;
import com.Softv.SoftvApp.SoftvApp.Activitys.CambioDom;

import com.Softv.SoftvApp.SoftvApp.Activitys.CambioAparato;
import com.Softv.SoftvApp.SoftvApp.Activitys.PDF;
import com.Softv.SoftvApp.SoftvApp.Activitys.ReporteAsignacion;
import com.Softv.SoftvApp.SoftvApp.Activitys.Reportes;
import com.Softv.SoftvApp.SoftvApp.Activitys.Saldo;
import com.Softv.SoftvApp.SoftvApp.Activitys.ServiciosSaldo;
import com.Softv.SoftvApp.SoftvApp.Adapters.ClientesAdapter;
import com.Softv.SoftvApp.SoftvApp.Adapters.EliminarMaterialAdapter;
import com.Softv.SoftvApp.SoftvApp.Adapters.GraficaAdapter;
import com.Softv.SoftvApp.SoftvApp.Adapters.NAPAdapter;
import com.Softv.SoftvApp.SoftvApp.Adapters.TAPAdapter;
import com.Softv.SoftvApp.SoftvApp.Adapters.TrabajosAdapter;
import com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarOrdenes;
import com.Softv.SoftvApp.SoftvApp.Activitys.ExtensionesAdi;
import com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarReportes;


import com.Softv.SoftvApp.SoftvApp.Activitys.Inicio;
import com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes;
import com.Softv.SoftvApp.SoftvApp.Fragments.MaterialesOrdenes;
import com.Softv.SoftvApp.SoftvApp.Fragments.MaterialesReportes;
import com.Softv.SoftvApp.SoftvApp.Listas.Array;
import com.Softv.SoftvApp.SoftvApp.Listas.DetallesList;
import com.Softv.SoftvApp.SoftvApp.Listas.Example;
import com.Softv.SoftvApp.SoftvApp.Listas.Example1;
import com.Softv.SoftvApp.SoftvApp.Listas.Example2;
import com.Softv.SoftvApp.SoftvApp.Listas.Example3;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONAPTAP;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONApaTipDis;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONApaTipo;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONAparatosDisponibles;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONArbolServicios;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONCAMDO;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONCLIAPA;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONDATOSCLIENTE;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONDESCARGADIRECTA;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONColonia;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONDescripcionArticulosBit;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONDetalleBitacora;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONGETNAP;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONLlenaExtenciones;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONMediosSer;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONNombreTecnico;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONPERMISOSDIRECTA;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONPreDescarga;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONPregunta;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONReporteCliente;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONReportes;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONResponseTecnico;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONServicioAsignado;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONServiciosAparatos;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONServiciosCAMDO;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONSolucion;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONStatusApa;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONTAP;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONTecSec;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONTecSecReport;
import com.Softv.SoftvApp.SoftvApp.Listas.JSONTipoAparatos;
import com.Softv.SoftvApp.SoftvApp.Listas.ListaClientesSaldos;
import com.Softv.SoftvApp.SoftvApp.Listas.QuejasList;
import com.Softv.SoftvApp.SoftvApp.Activitys.MainActivity;
import com.Softv.SoftvApp.SoftvApp.Activitys.MainReportes;
import com.Softv.SoftvApp.SoftvApp.Listas.ServiciosList;
import com.Softv.SoftvApp.SoftvApp.Modelos.CambioAparatoDeepModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.ChecaSiExtencionesModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.DatosClientesSaldoList;
import com.Softv.SoftvApp.SoftvApp.Modelos.DeepConsModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.DescripcionArticuloModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.DetalleBitacoraModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetBUSCADetOrdSerListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetCheca_si_tiene_CAMDOModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetColoniaResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetConRelCtePlacabyContrato;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetConTecnicoAgendaResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetConsultaClientesListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetDameDatosCAMDOResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetDameListadoOrdenesAgendadasResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetDameSerDelCliFacListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetDeepValidaQuejaCompaniaAdicModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetGetDescargaMaterialArticulosByIdClvOrdenListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListAparatosDisponiblesByIdArticuloResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListClienteAparatosResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListTipoAparatosByIdArticuloResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetListaNapTapResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMACWAMModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMUESTRATRABAJOSQUEJASListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraAparatosDisponiblesListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraArbolServiciosAparatosPorinstalarListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraMedioPorServicoContratadoListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraRelOrdenesTecnicosListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraServiciosRelTipoAparatoListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraTecnicosAlmacenListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetMuestraTipoAparatoListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetQuejasListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetSP_StatusAparatosListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetServiciosResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetSoftvWEb_DameEntrecalles;
import com.Softv.SoftvApp.SoftvApp.Modelos.Get_ClvTecnicoResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetchecaBitacoraTecnicoModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetdameSerDELCliresumenResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.GetuspBuscaContratoSeparado2ListResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.InfoClienteModelo;
import com.Softv.SoftvApp.SoftvApp.Modelos.ListadoQuejasAgendadas;
import com.Softv.SoftvApp.SoftvApp.Modelos.LlenaExtencionesModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.ModelDetallesList;
import com.Softv.SoftvApp.SoftvApp.Modelos.ModelServiciosList;
import com.Softv.SoftvApp.SoftvApp.Modelos.Muestra_TecnicosDescargaMaterialResult;
import com.Softv.SoftvApp.SoftvApp.Modelos.ObtieneNapModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.ObtieneTapModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.OrdSer;
import com.Softv.SoftvApp.SoftvApp.Modelos.RequierePregunta;
import com.Softv.SoftvApp.SoftvApp.Modelos.ValidaMACWAMMODEL;
import com.Softv.SoftvApp.SoftvApp.Modelos.ValidacionFirma;
import com.Softv.SoftvApp.SoftvApp.Modelos.dameTblPreDescargaMaterialResultModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.ProximaCitaModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.Queja;
import com.Softv.SoftvApp.SoftvApp.Modelos.TipoMaterialModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.UserModel;
import com.Softv.SoftvApp.SoftvApp.Modelos.mediosPregunta;
import com.Softv.SoftvApp.SoftvApp.R;
import com.Softv.SoftvApp.SoftvApp.Services.Services;
import com.Softv.SoftvApp.SoftvApp.Fragments.TrabajosReportes;
import com.Softv.SoftvApp.SoftvApp.Activitys.ServiciosAInstalar;
import com.Softv.SoftvApp.SoftvApp.sampledata.BarraCargar;
import com.Softv.SoftvApp.SoftvApp.sampledata.Constants;
import com.Softv.SoftvApp.SoftvApp.sampledata.Service;
import com.Softv.SoftvApp.SoftvApp.sampledata.SplashActivity;
import com.Softv.SoftvApp.SoftvApp.sampledata.Util;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.Softv.SoftvApp.SoftvApp.Activitys.CambioAparato.dialogCAPAT;
import static com.Softv.SoftvApp.SoftvApp.Activitys.Orden.statusBusquedaContrato;
import static com.Softv.SoftvApp.SoftvApp.Activitys.Orden.statusBusquedaOrden;
import static com.Softv.SoftvApp.SoftvApp.Activitys.Orden.statusBusquedaPresinto;
import static com.Softv.SoftvApp.SoftvApp.Activitys.Reportes.statusBusquedaContRepo;
import static com.Softv.SoftvApp.SoftvApp.Activitys.Reportes.statusBusquedaReporte;
import static com.Softv.SoftvApp.SoftvApp.Activitys.ServiciosAInstalar.dialogAsignacion;
import static com.Softv.SoftvApp.SoftvApp.Activitys.AsignarAparato.MACWAMText;
import static com.Softv.SoftvApp.SoftvApp.Activitys.AsignarAparato.constraintLayoutMACWAM;
import static com.Softv.SoftvApp.SoftvApp.Activitys.AsignarAparato.idArticuloasignado;
import static com.Softv.SoftvApp.SoftvApp.Activitys.AsignarAparato.jsonArrayMAC;

import static com.Softv.SoftvApp.SoftvApp.Adapters.QuejasAdapter.statusQueja;
import static com.Softv.SoftvApp.SoftvApp.Adapters.TrabajosAdapter.dialogTrabajos;
//import static com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarOrdenes.Status;
import static com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarOrdenes.dialogEjecutar;
import static com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarOrdenes.posTec;
import static com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarReportes.TecSecSeleccion;
import static com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarReportes.dialogReportes;
import static com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarReportes.fechaEjecujtar;
import static com.Softv.SoftvApp.SoftvApp.Fragments.EjecutarReportes.horaEjecutar;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes.dialogVisitaOrd;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes.ejecutada;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasOrdenes.visita;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasReportes.dialogVisitaRepo;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasReportes.reporteEjecutada;
import static com.Softv.SoftvApp.SoftvApp.Fragments.HorasReportes.repotteVisita;
import static com.Softv.SoftvApp.SoftvApp.Fragments.MaterialesOrdenes.clasificacionMat;
import static com.Softv.SoftvApp.SoftvApp.Fragments.MaterialesOrdenes.descripcionMat;
import static com.Softv.SoftvApp.SoftvApp.Fragments.MaterialesOrdenes.posDescMat;
import static com.Softv.SoftvApp.SoftvApp.Fragments.MaterialesReportes.clasificacionMatR;
import static com.Softv.SoftvApp.SoftvApp.Fragments.MaterialesReportes.descripcionMatR;
import static com.Softv.SoftvApp.SoftvApp.Fragments.MaterialesReportes.spinnerExtMatR;
import static com.Softv.SoftvApp.SoftvApp.Fragments.TrabajosOrdenes.adaptertrabajos;
import static com.Softv.SoftvApp.SoftvApp.Fragments.TrabajosOrdenes.trabajos;
import static com.Softv.SoftvApp.SoftvApp.Fragments.TrabajosReportes.Clv_Sol;
import static com.Softv.SoftvApp.SoftvApp.Fragments.TrabajosReportes.proble;
import static com.Softv.SoftvApp.SoftvApp.Listas.Array.Asigna;
import static com.Softv.SoftvApp.SoftvApp.Listas.Array.Asigna1;
import static com.Softv.SoftvApp.SoftvApp.Fragments.TrabajosReportes.solucion;
import static com.Softv.SoftvApp.SoftvApp.Listas.Array.contratoQ;
import static com.Softv.SoftvApp.SoftvApp.Listas.Array.contratosrc;
import static com.Softv.SoftvApp.SoftvApp.Listas.Array.nom_tecnicoSecundarioQ;
import static com.Softv.SoftvApp.SoftvApp.Listas.Array.ordensrc;
import static com.Softv.SoftvApp.SoftvApp.Listas.Array.precintosrc;
import static java.util.Arrays.asList;


public class Request extends AppCompatActivity {
    public static boolean NAP=false,TAP=false,NAPCAMDO=false,TAPCAMDO=false,Placa=false,Desc=false, SeGuarda = false;
    Services services = new Services();
    Array array = new Array();
    public static String reintentarComando, contraroMA, obsMA, statusMA,PlacaMA, extencionesE, Obs,ObsR, msgComando = "",problemaReal;
    public static boolean isnet, firma,MACWAM,validaExisteFirmaBool;
    public static boolean PermPlaca = false, PermVamra = false, PermBolivia = false, PermVallarta = false, PermCobro=false, PermCableCentro=false;
    public static String VENTA, RENTA;
    public static Long abc;
    public static int clvP, tecC, nExtenciones = 0,clvProblemarepo,ContratoReal,ClvUsuario,NoBitacora,ClvTipSerReportes, Cambio;
    public int reintentaB;
    public static String stringValidaTrabajos;
    public static ArrayAdapter adapterTecSec, adapterTecSecR, adapterNap,adapterTap,adapterColonia,adapterTAPNAPCAMDO;
    public static boolean pieza = false, rapagejecutar = false, extencionesMat = false,escable = false, validanodo=false;
   public static int validFirma;
    public static String ciudadcmdo, localidadcmdo, coloniacmdo, callecmdo, numerocmdo, numeroicmdo, telefonocmdo, callencmdo, callescmdo, calleecmdo, calleocmdo, casacmdo,referenciascmd,entrecallescmd;
    public static String ejecutarStatus,reporteStatus,clasProblema;
    public static String reporteVisita1,reporteVisita2,reporteVisita3,reporteHora1,reporteHora2,reporteHora3, titul;
    public static String ContratoCompuestoSaldo,NombreSaldo,TelefonoSaldo,Calle_NumeroSaldo,ColoniaSaldo,ContratoSaldo, FechaCosultaSaldo, TotalSaldo;
    public static  String GetTicketResult = Constants.URL_REPORTES, NombreGeneral;
    public static float Monto;
    public static Integer Session = 0, CLV_FACTURA = 0 ;

    JsonObject jsonConsultaIp;
    String a = "Seleccione técnico secundario";
    String f = "Seleccione tipo de solución";
    public static String datos[], datosTap[],datosNap[];
    BarraCargar barraCargar = new BarraCargar();
    public static boolean requierePregunta=false;
    public List<GetMuestraServiciosRelTipoAparatoListResult> ServDig;

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
        dialogInicio.dismiss();

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
                            Toast.makeText(context, "Error al conseguir clave técnico", Toast.LENGTH_LONG).show();
                        }else{
                            ErrorInicioNoCoincide(context,dialogLogin,view);
                        }

                    }

                } else {
                    if(Login==true){
                        ErrorLogin(context,dialogLogin,view);
                        Toast.makeText(context, "Error al conseguir clave técnico", Toast.LENGTH_LONG).show();
                    }else{
                        ErrorInicioNoCoincide(context,dialogLogin,view);
                    }
                }
            }

            @Override
            public void onFailure(Call<JSONResponseTecnico> call, Throwable t) {
                if(Login==true){
                    ErrorLogin(context,dialogLogin,view);
                    Toast.makeText(context, "Error al conseguir clave técnico", Toast.LENGTH_LONG).show();
                }else{
                    ErrorInicioNoCoincide(context,dialogLogin,view);
                }
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
                    //Verifica los permisos
                    Permisos(context, jsonObject);

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

                    try{
                        JSONObject jsonObjectDirecta = new JSONObject();
                        JSONObject jsonObjectDirecta1 = new JSONObject();
                        jsonObjectDirecta.put("Op",3);
                        jsonObjectDirecta.put("idcompania",3);
                        jsonObjectDirecta.put("ClvTecnicoMandar",Util.getClvTec(Util.preferences));
                        jsonObjectDirecta1.put("obj",jsonObjectDirecta);
                        getPermisosDirecta(context,jsonObjectDirecta1);
                    }catch (Exception e){}



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
                        Array.fechaQ.clear();
                        for (int i = 0; i < dat.size(); i++) {
                            Array.Queja.add(String.valueOf(dat.get(i).getClvQueja()));
                            Array.contratoQ.add(String.valueOf(dat.get(i).getContrato()));
                            Array.nombreQ.add(String.valueOf(dat.get(i).getNombre()));
                            Array.statusQ.add(String.valueOf(dat.get(i).getStatus()));
                            if(PermCableCentro == true)
                                Array.Direccion.add(String.valueOf(dat.get(i).getCalle() + ", " + dat.get(i).getColonia()));
                            else
                                Array.Direccion.add(String.valueOf(dat.get(i).getCalle() + ", " + dat.get(i).getNUMERO() + ", " + dat.get(i).getColonia()));


                            String[] caracteres = dat.get(i).getFecha().split(" ");
                            Array.fechaQ.add(caracteres[0]);

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
                    RENTA = null;
                    VENTA = null;
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
                        Array.direccionsrc.clear();
                        Array.fechasrc.clear();
                        Array.precintosrc.clear();
                        for (int i = 0; i < dat.size(); i++) {
                            Array.ordensrc.add(String.valueOf(dat.get(i).getClvOrden()));
                            Array.contratosrc.add(String.valueOf(dat.get(i).getContrato()));
                            Array.nombresrc.add(String.valueOf(dat.get(i).getNombre()));
                            Array.statusrc.add(String.valueOf(dat.get(i).getStatus()));
                            if(PermCableCentro == true)
                                Array.direccionsrc.add(String.valueOf(dat.get(i).getCalle() + ", " + dat.get(i).getColonia()));
                            else
                                Array.direccionsrc.add(String.valueOf(dat.get(i).getCalle() + ", " + dat.get(i).getNumero() + ", " + dat.get(i).getColonia()));
                            Array.napsrc.add(dat.get(i).getNap());
                            Array.tapsrc.add(dat.get(i).getTab());
                            Array.trabajosrc.add(dat.get(i).getDescripcion());
                            String[] caracteres = dat.get(i).getFecha().split(" ");
                            Array.fechasrc.add(caracteres[0]);
                            Array.precintosrc.add(String.valueOf(dat.get(i).getPrecinto()));
                        }
                    }
                    if (ordensrc.size() == 0 && statusBusquedaOrden == true){
                        ErrorMensaje(context,"Orden no encontrada ");
                        statusBusquedaOrden = false;
                    }else if (contratosrc.size() == 0 && statusBusquedaContrato == true){
                        ErrorMensaje(context,"Contrato no encontrado ");
                        statusBusquedaContrato = false;
                    }else if (precintosrc.size() == 0 && statusBusquedaPresinto == true){
                        ErrorMensaje(context,"Presinto no encontrado ");
                        statusBusquedaPresinto = false;
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
                    try {
                        PlacaMA=userJson.get("Placa").getAsString();
                    } catch (Exception e) {
                        PlacaMA="---";
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

                    //MainActivity.Nombre.setText(InfoClienteModelo.NOMBRE);
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
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("Contrato", DeepConsModel.Contrato);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<Example2> call = services.RequestPost(context, jsonObject).getDataServicios();
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
                        int abc=Util.getClvTec(Util.preferences);
                        Array.nom_tecnicoSecundario.clear();
                        Array.clv_tecnicoSecundario.clear();
                        Array.nom_tecnicoSecundario.add(a);
                        Array.clv_tecnicoSecundario.add(-1);
                        for (int i = 0; i < dat.size(); i++) {
                            if(abc!= dat.get(i).getCLV_TECNICO()){
                                Array.nom_tecnicoSecundario.add(dat.get(i).getNOMBRE());
                                Array.clv_tecnicoSecundario.add(dat.get(i).getCLV_TECNICO());

                            }
                        }
                        adapterTecSec = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, Array.nom_tecnicoSecundario);
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
                            if(CambioAparatoDeepModel.AparatoCliente==0){

                            }else{
                                CambioAparato.aparato.setSelection(CambioAparato.obtenerPosicionAC(CambioAparatoDeepModel.AparatoCliente));
                                try{
                                    JSONObject jsonObject = new JSONObject();
                                    JSONObject jsonObject1 = new JSONObject();
                                    jsonObject.put("Letra", dat.get(CambioAparato.obtenerPosicionAC(CambioAparatoDeepModel.AparatoCliente)).Letra);
                                    jsonObject1.put("ObjRelMacwan", jsonObject);
                                    ValidaMACWAM(getApplicationContext(), jsonObject1);
                                }catch (Exception e){}

                            }
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
                            if(CambioAparatoDeepModel.StatusEntrega.equals("")){

                            }else {
                                CambioAparato.estado.setSelection(CambioAparato.obtenerPosicionSA(CambioAparatoDeepModel.StatusEntrega));
                                getApaTipo(context);
                            }

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
                        if(CambioAparatoDeepModel.TipoAparatoAsignar==0){

                        }else{
                            CambioAparato.idArticulo = dat1.get(CambioAparato.obtenerPosicionTA(CambioAparatoDeepModel.TipoAparatoAsignar)).getIdArticulo();
                            CambioAparato.contratoNetCam = dat1.get(CambioAparato.obtenerPosicionTA(CambioAparatoDeepModel.TipoAparatoAsignar)).getControNet();
                        }

                    } catch (Exception e) {
                        //ErrorMensaje(context,"Error al conseguir tipo de aparato " + e.toString());
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
                            if(CambioAparatoDeepModel.TipoAparatoAsignar==0){

                            }else{
                                CambioAparato.tipoAparato.setSelection(CambioAparato.obtenerPosicionTA(CambioAparatoDeepModel.TipoAparatoAsignar));
                                CambioAparato.idArticulo = dat1.get(CambioAparato.obtenerPosicionTA(CambioAparatoDeepModel.TipoAparatoAsignar)).getIdArticulo();
                                CambioAparato.contratoNetCam = dat1.get(CambioAparato.obtenerPosicionTA(CambioAparatoDeepModel.TipoAparatoAsignar)).getControNet();
                                getApaTipDis(context);
                            }



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
                        if(CambioAparatoDeepModel.AparatoAsignar==0){

                        }else{
                            CambioAparato.idArticulo2 = dat1.get(CambioAparato.obtenerPosicionA(CambioAparatoDeepModel.AparatoAsignar)).getIdArticulo();
                        }


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
                            if(CambioAparatoDeepModel.AparatoAsignar==0){

                            }else{
                                CambioAparato.aparatoAsignar.setSelection(CambioAparato.obtenerPosicionA(CambioAparatoDeepModel.AparatoAsignar));
                            }

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
                            referenciascmd = dat.get(0).Referencia;
                            // Entre calles
                            String aux="";
                            if(callencmdo.length() != 0) aux = aux + "N: " + callencmdo +  "\n";
                            if(callescmdo.length() != 0) aux = aux + "S: " + callescmdo +  "\n";
                            if(calleecmdo.length() != 0) aux = aux + "E: " + calleecmdo +  "\n";
                            if(calleocmdo.length() != 0) aux = aux + "O: " + calleocmdo +  "\n";

                            //entrecallescmd = "N:"+callencmdo + "    S:" + callescmdo + "\nE:" + calleecmdo + "  O:" + calleocmdo;
                            entrecallescmd = aux;

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
                        entrecallescmd="--";
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
    public void getTipoAparatos(final Context context, final JSONObject jsonObject, final Spinner spinnerapa) {
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
                            array.tipoAparatoLetra.add(dat.get(i).getLetra());
                        }
                    }
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, array.tipoAparato);
                    spinnerapa.setAdapter(adapter1);
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
                    AsignarAparato.selectedStrings.clear();
                    JSONServiciosAparatos jsonResponse = response.body();
                    final Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
                    final List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();

                    array.dataserviciosAparatos = new ArrayList<List<GetMuestraServiciosRelTipoAparatoListResult>>(asList(jsonResponse.GetMuestraServiciosRelTipoAparatoListResult()));
                    Iterator<List<GetMuestraServiciosRelTipoAparatoListResult>> itData = array.dataserviciosAparatos.iterator();
                    ServDig=null;
                    while (itData.hasNext()) {
                        List<GetMuestraServiciosRelTipoAparatoListResult> dat = (List<GetMuestraServiciosRelTipoAparatoListResult>) itData.next();
                        ServDig = dat;
                        /*if(Util.getTipodeCliente(Util.preferences)==3){
                            cuadrilla.setVisibility(View.VISIBLE);

                        }else{
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
                        }*/

                        if(TrabajosAdapter.ISDIG==true){
                            if(letra.equals("T")||letra.equals("D")){
                                for (int i = 0; i < dat.size(); i++) {
                                    String titu = titul;
                                    if(dat.get(i).getNombre().contains(titu) || titu.contains(dat.get(i).getNombre())){
                                        array.serviciosAparatos.add(dat.get(i).getNombre());
                                        AsignarAparato.selectedStrings.add(dat.get(i).clv_UnicaNet);
                                    }
                                    //if(dat.get(i).clv_UnicaNet == ArbolAdapter.clv_unicaNet){}
                                    //AsignarAparato.selectedServ.add(dat.get(i).nombre);
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
                        for (int i = 0; i < dat.size(); i++) {
                            AsignarAparato.selectedServ.add(dat.get(i).nombre);
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
        Call<JsonObject> call;
        if(PermCableCentro == true && SeGuarda == true){
            call = services.RequestPost(context, jsonObject).getDataAceptarAsig2();
        }else{
            call = services.RequestPost(context, jsonObject).getDataAceptarAsig();
        }
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
    public void getSolucuion(final Context context, final JSONObject jsonObjet) {
        Array.clv_Soluc = new ArrayList<Integer>();
        Array.clv_Soluc.add(0, -1);

        Call<JSONSolucion> call = services.RequestPost(context, jsonObjet).getSolut();
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
                        //solucion.setSelection(posSolucionRepo);
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
                            ClvTipSerReportes = dat.get(i).getClvTipSer();
                            try{
                                PlacaMA=dat.get(i).getPlaca();
                            }catch (Exception e){
                                PlacaMA="---";
                            }

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
                            try{
                                JSONObject clv = new JSONObject();
                                clv.put("TipSer",ClvTipSerReportes);
                                getSolucuion(context,clv);
                            }catch (Exception e){}

                            //ClvTrabajoRequest=dat.get(i).clvProblema();
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
                            try{
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
                            }catch(Exception e){

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
                            //MainReportes.Nombre1.setText(dat.get(i).getNombre() + "  " + dat.get(i).getApellidoPaterno() + "  " + dat.get(i).getApellidoMaterno());
                            //MainReportes.Direccion1.setText(dat.get(i).getCALLE() + "  " + dat.get(i).getNUMERO() + "  " + dat.get(i).getCOLONIA());
                            MainReportes.contrato1.setText(dat.get(i).getCONTRATO());
                            MainReportes.ciudad1.setText(dat.get(i).getCIUDAD());
                            MainReportes.placa1.setText(PlacaMA);
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
        nom_tecnicoSecundarioQ = new ArrayList<String>();
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
                        nom_tecnicoSecundarioQ.clear();
                        Array.Clv_TecSecR.clear();
                        nom_tecnicoSecundarioQ.add(a);
                        Array.Clv_TecSecR.add(-1);
                        for (int i = 0; i < dat.size(); i++) {
                            if(Util.getClvTec(Util.preferences)!= dat.get(i).getClvTecnico()){
                                nom_tecnicoSecundarioQ .add(dat.get(i).getNombre());
                                Array.Clv_TecSecR.add(j, dat.get(i).getClvTecnico());
                                j = j + 1;
                            }
                        }
                        adapterTecSecR = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, nom_tecnicoSecundarioQ);
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
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 1");
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
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 2");
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
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 3");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 4");
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
                            jsonObject2.put("ClvUsuario", Util.getClvUsuario(Util.preferences));
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
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 5");
                    if(visita == 1){
                        dialogVisitaOrd.dismiss();
                    }else if (ejecutada == 1){
                        dialogEjecutar.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 6");
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
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 7");
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
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 8");
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
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 9");
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
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 10");
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
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 11");
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
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 12");
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
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 13");
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
            if (caracteres[0].equals("CAMDO") || caracteres[0].equals("ISNET") || caracteres[0].equals("ISDIG") || caracteres[0].equals("ISTVA")|| caracteres[0].equals("CAPAG")) {
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
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 14");
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
                                objQuejas.put("IdUsuario", Util.getClvUsuario(Util.preferences));
                                objQuejas.put("Observaciones", Obs);
                                objQuejas.put("Solucion", proble.getText());
                                objQuejas.put("Status", "E");
                                objQuejas.put("TecnicoCuadrilla", TecSecSeleccion);
                                objQuejas.put("Visita", false);
                                objQuejas.put("Visita1", "");
                                objQuejas.put("Visita2", "");
                                objQuejas.put("Visita3", "");
                                objQuejas.put("clvPrioridadQueja", clvP);
                                objQuejas.put("clvProblema", Clv_Sol);
                                objQuejas.put("clvProblema2", clvProblemarepo);
                                objQuejas.put("COMENTARIO", "MOVIL");
                                jsonObject1.put("objQuejas", objQuejas);
                                getGuardaCampos(context,jsonObject1);
                            }catch (Exception e){}
                        }

                    }
                } else{

                    dialogReportes.dismiss();
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 15");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                dialogReportes.dismiss();
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 16");
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
    jsonObject.put("IdUsuario", Util.getClvUsuario(Util.preferences));
    getValidaReporte(context,jsonObject,fecha,hora);
}catch (Exception e){}

                    }
                }else{
                    dialogReportes.dismiss();
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 17");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                dialogReportes.dismiss();
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 18");
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
                        ErrorMensaje(context,"Se ha producido un error, verifique su conexión e intente nuevamente Uni2");
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
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 19");
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
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 20");
                    dialogEjecutar.dismiss();
                    EjecutarOrdenes.eject.setEnabled(true);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente 21");
            }
        });
    }



    /*public void ConsultaIp(final Context context) {
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
                            dialogEjecutar.dismiss();
                            reiniciar.setEnabled(true);
                            Status.setText(Request.msgComando);
                        } else {
                            if (msgComando.length() > 3) {
                                Status.setText(msgComando);
                                dialogEjecutar.dismiss();

                                    Login.esperar(3);
                                    Intent intent = new Intent(context, Firma.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.startActivity(intent);

                            } else {
                                Login.esperar(5);
                                retry(call);
                            }
                        }
                    }
                }else{
                    //dialogEjecutar.dismiss();
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
                    Status.setText("Espera");
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
    }

    public void getValidaNodo(final Context context, final JSONObject jsonObject) {
        Call<JSONValidaNodo> call = services.RequestPost(context, jsonObject).getValidaNodo();
        call.enqueue(new Callback<JSONValidaNodo>() {
            @Override
            public void onResponse(Call<JSONValidaNodo> call, Response<JSONValidaNodo> response1) {
                if (response1.code() == 200) {
                    JSONValidaNodo jsonResponse = response1.body();
                    array.dataValidaNodo = new ArrayList<List<GetSoftvWebValidaNodo>>(asList(jsonResponse.GetSoftvWebValidaNodo()));
                    Iterator<List<GetSoftvWebValidaNodo>> itData = array.dataValidaNodo.iterator();
                    while (itData.hasNext()) {
                        List<GetSoftvWebValidaNodo> dat = itData.next();
                        for (int a=0; a<dat.size();a++){
                            if(dat.get(a).Clv_TipSer==2 && (dat.get(a).idMedio==2 || dat.get(a).idMedio==4)){
                                validanodo=true;
                            }
                        }
                    }

                    if(validanodo==true){
                        reiniciar.setVisibility(View.VISIBLE);
                        msgEjecutarOrd.setVisibility(View.VISIBLE);
                        EjecutarOrdenes.Esttuscomando.setVisibility(View.VISIBLE);
                        Status.setVisibility(View.VISIBLE);
                        ConsultaIp(context);
                    }else{
                        Toast.makeText(context, "Orden guardado correctamente", Toast.LENGTH_LONG);
                        dialogEjecutar.dismiss();
                        if(firma==true){
                            Intent intent = new Intent(context, Firma.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }else{
                            getListOrd(context);
                        }

                    }

                }else{
                    ErrorMensaje(context,"Error al validar nodo "+response1.message());
                }
            }

            @Override
            public void onFailure(Call<JSONValidaNodo> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
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
                        try{
                            MaterialesOrdenes.extSer=0;
                        }catch(Exception e){}
                        try{
                            MaterialesReportes.extSerR=0;
                        }catch (Exception x){}
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
                    }
                    try{
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array.detalleBit);
                        descripcionMatR.setAdapter(arrayAdapter);
                        descripcionMatR.setSelection(MaterialesReportes.posDescMatR);
                    }catch (Exception e){

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
                        //clasificacionMat.setSelection(posClasMat);
                    }catch (Exception e){
                        clasificacionMatR.setEnabled(true);
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array.descripcionArtBit);
                        clasificacionMatR.setAdapter(arrayAdapter);
                        //clasificacionMatR.setSelection(posClasMatR);
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
                    try {
                        //MaterialesOrdenes.extMat.setVisibility(View.VISIBLE);
                    }catch (Exception e){}
                    try {
                        //MaterialesReportes.extMatR.setVisibility(View.VISIBLE);
                    }catch (Exception e){}
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
                        //spinnerExtMat.setSelection(posExtMat);
                    } catch (Exception e) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, array.descripcionExt);
                        spinnerExtMatR.setAdapter(arrayAdapter);
                        //spinnerExtMatR.setSelection(posExtMatR);
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response1.message());
                    MaterialesReportes.extMatR.setVisibility(View.GONE);
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
                    pieza = false;
                    JsonObject userJson = response.body().getAsJsonObject("GetSoftv_ObtenTipoMaterialResult");
                    TipoMaterialModel user = new TipoMaterialModel(
                            userJson.get("Tipo").getAsString()
                    );
                    if (user.Tipo.equals("Piezas") || user.Tipo.equals("UND")) {
                        pieza = true;
                    } else {
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
                    if (user.Tipo.equals("Piezas") || user.Tipo.equals("UND")) {
                        MaterialesReportes.piezasMatR.setVisibility(View.VISIBLE);
                        MaterialesReportes.metrosMatR.setVisibility(View.INVISIBLE);
                        pieza = true;
                    } else {
                        MaterialesReportes.metrosMatR.setVisibility(View.VISIBLE);
                        MaterialesReportes.piezasMatR.setVisibility(View.INVISIBLE);
                        pieza = false;

                        if( MaterialesReportes.esFibraR==1){
                            MaterialesReportes.mIIR.setVisibility(View.GONE);
                            MaterialesReportes.mFIR.setVisibility(View.GONE);
                            MaterialesReportes.tvInteriorR.setVisibility(View.INVISIBLE);
                        }else{
                            MaterialesReportes.mIIR.setVisibility(View.VISIBLE);
                            MaterialesReportes.mFIR.setVisibility(View.VISIBLE);
                            MaterialesReportes.tvInteriorR.setVisibility(View.VISIBLE);
                        }
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

    ///////////////////////////////////

    public void getDescargaDirecta(final Activity activity, final Context context, final JSONObject jsonObject) {
        Call<JSONDESCARGADIRECTA> call = services.RequestPost(context, jsonObject).getDescargaDirecta();
        call.enqueue(new Callback<JSONDESCARGADIRECTA>() {
            @Override
            public void onResponse(Call<JSONDESCARGADIRECTA> call, Response<JSONDESCARGADIRECTA> response) {

                EliminarMaterialAdapter adapter;


                try{
                    array.listaTabla.clear();
                }catch (Exception e){}



                if (response.code() == 200) {
                    JSONDESCARGADIRECTA jsonResponse = response.body();
                    array.dataDescargaDirecta = new ArrayList<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>>(asList(jsonResponse.GetGetDescargaMaterialArticulosByIdClvOrdenListResult()));
                    Iterator<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>> itdata = array.dataDescargaDirecta.iterator();
                    while (itdata.hasNext()) {
                        List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult> dat = itdata.next();
                        for (int i = 0; i < dat.size(); i++) {
                            array.listaTabla.add(new ArrayList<String>());
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getDescripcion()));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getCANTIDADUTILIZADA()));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getNoExt()));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getNOARTICULO()));
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
            public void onFailure(Call<JSONDESCARGADIRECTA> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void addDescarga(final Activity activity, final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).addDescarga();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    //Toast.makeText(context, "Se agrego la descarga de material con el número de bitacora: " +NoBitacora, Toast.LENGTH_SHORT).show();
                    /*if(MainActivity.DescargaAgregar==true){
                        Toast.makeText(context, "Se agrego la descarga de material con el número de bitacora: " +NoBitacora, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Se agrego correctamente", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("ClvOrdSer",Util.getClvOrden(Util.preferences) );
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                            jsonObject.put("NoExt", 0);

                            getDescargaDirecta(activity,context,jsonObject);
                        }catch (Exception e){}
                    }*/

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
    public void bitacoraDirecta(final Activity activity, final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).bitacoraDirecta();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JsonObject userJson = response.body().getAsJsonObject("GetchecaBitacoraTecnicoResult");
                    //Introduccion de datos del request en el Modelo para poder usarlos

                        GetchecaBitacoraTecnicoModel user = new GetchecaBitacoraTecnicoModel(
                                userJson.get("idBitacora").getAsInt()
                        );
                        NoBitacora = user.idBitacora;
                    }catch (Exception e){
                        NoBitacora = 0;
                    }
                    String accion = "";
                    if (pieza == true) {
                        escable = false;
                    } else {
                        escable = true;
                    }




                        if (NoBitacora == 0) {
                        accion = "Agregar";
                        try {
                            JSONObject jsonObject = new JSONObject();
                            JSONObject jsonObject1 = new JSONObject();
                            JSONObject jsonObject2 = new JSONObject();
                            JSONArray jsonArray = new JSONArray();
                            jsonObject.put("IdTecnico", Util.getClvTec(Util.preferences));
                            jsonObject.put("ClvOrden", Util.getClvOrden(Util.preferences));
                            jsonObject.put("IdAlmacen", 0);
                            jsonObject.put("Accion", accion);
                            jsonObject.put("IdBitacora", NoBitacora);
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));

                            jsonObject1.put("NoArticulo", MaterialesOrdenes.idInventarioMD);
                            jsonObject1.put("Cantidad", MaterialesOrdenes.totalDM);
                            jsonObject1.put("EsCable", escable);
                            jsonObject1.put("MetrajeInicio", MaterialesOrdenes.IIDM);
                            jsonObject1.put("MetrajeFin", MaterialesOrdenes.IFDM);
                            jsonObject1.put("MetrajeInicioExt", MaterialesOrdenes.EIMD);
                            jsonObject1.put("MetrajeFinExt", MaterialesOrdenes.EFDM);
                            try {
                                jsonObject1.put("NumExt", MaterialesOrdenes.extSer);
                            }catch (Exception e){
                                jsonObject1.put("NumExt", 0);
                            }
                            jsonArray.put(0, jsonObject1);

                            jsonObject2.put("ObjDescargaMat", jsonObject);
                            jsonObject2.put("Articulos", jsonArray);

                            addDescarga(activity, context, jsonObject2);
                        } catch (Exception e) {
                        }
                    } else {

                        accion = "Modificar";
                       /* GetGetDescargaMaterialArticulosByIdClvOrdenListResult nuevo = new GetGetDescargaMaterialArticulosByIdClvOrdenListResult(
                                ,,
                                ,,,,,
                                ,,,
                                ,,0
                        );*/
                       GetGetDescargaMaterialArticulosByIdClvOrdenListResult nuevo = new GetGetDescargaMaterialArticulosByIdClvOrdenListResult();
                       nuevo.setCANTIDADUTILIZADA(MaterialesOrdenes.totalDM);
                       nuevo.setClvOrdSer(Util.getClvOrden(Util.preferences));
                       nuevo.setDescripcion(MaterialesOrdenes.descripcionMaterial);
                       nuevo.setESCABLE(escable);
                       nuevo.setMETRAJEFIN(MaterialesOrdenes.IFDM);
                       nuevo.setMETRAJEFINEXTERIOR(MaterialesOrdenes.EFDM);
                       nuevo.setMETRAJEINICIO(MaterialesOrdenes.IIDM);
                       nuevo.setMETRAJEINICIOEXTERIOR(MaterialesOrdenes.EIMD);
                       nuevo.setNOARTICULO(MaterialesOrdenes.idInventarioMD);
                       nuevo.setNoExt(MaterialesOrdenes.extSer);
                       try{
                           nuevo.setTecnico(Util.getNombreTecnicoPreference(Util.preferences));
                       }catch (Exception e){
                           nuevo.setTecnico("");
                       }
                       nuevo.setTipoDescarga(Util.getTipoDescarga(Util.preferences));
                       nuevo.setIdDescarga(0);

                       array.dataDescargaDirecta.get(0).add(nuevo);

                            Iterator<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>> itdata = array.dataDescargaDirecta.iterator();
                                List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult> dat = itdata.next();

                            Log.d("asd",array.dataDescargaDirecta.toString());
                            Log.d("asd",nuevo.toString());
                            //array.dataDescargaDirecta.add((List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>) nuevo);

                        array.listaTabla.add(new ArrayList<String>());
                        array.listaTabla.get(array.listaTabla.size()-1).add(String.valueOf(MaterialesOrdenes.descripcionMaterial));
                        array.listaTabla.get(array.listaTabla.size()-1).add(String.valueOf(MaterialesOrdenes.totalDM));
                        array.listaTabla.get(array.listaTabla.size()-1).add(String.valueOf(MaterialesOrdenes.extSer));
                        array.listaTabla.get(array.listaTabla.size()-1).add(String.valueOf(MaterialesOrdenes.idInventarioMD));

                        EliminarMaterialAdapter adapter;
                        MaterialesOrdenes.elimarMaterialesList.setVisibility(View.VISIBLE);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1);
                        MaterialesOrdenes.elimarMaterialesList.setLayoutManager(layoutManager);
                        adapter = new EliminarMaterialAdapter(array.listaTabla, context, activity);
                        MaterialesOrdenes.elimarMaterialesList.setAdapter(adapter);
                    }




                } else {
                    ErrorMensaje(context,"Error al conseguir datos de bitacora "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void DamebitacoraDirecta(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).bitacoraDirecta();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JsonObject userJson = response.body().getAsJsonObject("GetchecaBitacoraTecnicoResult");
                        //Introduccion de datos del request en el Modelo para poder usarlos

                        GetchecaBitacoraTecnicoModel user = new GetchecaBitacoraTecnicoModel(
                                userJson.get("idBitacora").getAsInt()
                        );
                        NoBitacora = user.idBitacora;
                    }catch (Exception e){
                        NoBitacora = 0;
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir datos de bitacora "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    //////////////////////////////////////////////////////////

    public void getDescargaDirectaR(final Activity activity, final Context context, final JSONObject jsonObject) {
        Call<JSONDESCARGADIRECTA> call = services.RequestPost(context, jsonObject).getDescargaDirecta();
        call.enqueue(new Callback<JSONDESCARGADIRECTA>() {
            @Override
            public void onResponse(Call<JSONDESCARGADIRECTA> call, Response<JSONDESCARGADIRECTA> response) {

                EliminarMaterialAdapter adapter;


                try{
                    array.listaTabla.clear();
                }catch (Exception e){}



                if (response.code() == 200) {
                    JSONDESCARGADIRECTA jsonResponse = response.body();
                    array.dataDescargaDirecta = new ArrayList<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>>(asList(jsonResponse.GetGetDescargaMaterialArticulosByIdClvOrdenListResult()));
                    Iterator<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>> itdata = array.dataDescargaDirecta.iterator();
                    while (itdata.hasNext()) {
                        List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult> dat = itdata.next();
                        for (int i = 0; i < dat.size(); i++) {
                            array.listaTabla.add(new ArrayList<String>());
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getDescripcion()));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getCANTIDADUTILIZADA()));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getNoExt()));
                            array.listaTabla.get(i).add(String.valueOf(dat.get(i).getNOARTICULO()));
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

                    }catch (Exception e){
                        Toast.makeText(context,"Error al cargar Tabla" ,Toast.LENGTH_SHORT ).show();
                    }
                }else{
                    ErrorMensaje(context,"Error al conseguir datos del materiales "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONDESCARGADIRECTA> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void addDescargaR(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).addDescarga();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    Toast.makeText(context, "Se agrego la descarga de material con el número de bitacora: " +NoBitacora, Toast.LENGTH_SHORT).show();
                   /* if(MainReportes.DescargaAgregarR==true){
                        Toast.makeText(context, "Se agrego la descarga de material con el número de bitacora: " +NoBitacora, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Se agrego correctamente", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("ClvOrdSer",Util.getClvOrden(Util.preferences) );
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                            jsonObject.put("NoExt", 0);

                            getDescargaDirectaR(activity,context,jsonObject);
                        }catch (Exception e){}*/
                    }else {
                    ErrorMensaje(context,"Error al agregar datos del materiales "+response.message());
                }

                }


            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void bitacoraDirectaR(final Activity activity, final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).bitacoraDirecta();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JsonObject userJson = response.body().getAsJsonObject("GetchecaBitacoraTecnicoResult");
                        //Introduccion de datos del request en el Modelo para poder usarlos

                        GetchecaBitacoraTecnicoModel user = new GetchecaBitacoraTecnicoModel(
                                userJson.get("idBitacora").getAsInt()
                        );
                        NoBitacora = user.idBitacora;
                    }catch (Exception e){
                        NoBitacora = 0;
                    }
                    String accion = "";
                    if (pieza == true) {
                        escable = false;
                    } else {
                        escable = true;
                    }




                    if (NoBitacora == 0) {
                        accion = "Agregar";
                        try {
                            JSONObject jsonObject = new JSONObject();
                            JSONObject jsonObject1 = new JSONObject();
                            JSONObject jsonObject2 = new JSONObject();
                            JSONArray jsonArray = new JSONArray();
                            jsonObject.put("IdTecnico", Util.getClvTec(Util.preferences));
                            jsonObject.put("ClvOrden", Util.getClvOrden(Util.preferences));
                            jsonObject.put("IdAlmacen", 0);
                            jsonObject.put("Accion", accion);
                            jsonObject.put("IdBitacora", NoBitacora);
                            jsonObject.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));

                            jsonObject1.put("NoArticulo", MaterialesReportes.idInventarioMDR);
                            jsonObject1.put("Cantidad", MaterialesReportes.totalDMR);
                            jsonObject1.put("EsCable", escable);
                            jsonObject1.put("MetrajeInicio", MaterialesReportes.IIDMR);
                            jsonObject1.put("MetrajeFin", MaterialesReportes.IFDMR);
                            jsonObject1.put("MetrajeInicioExt", MaterialesReportes.EIMDR);
                            jsonObject1.put("MetrajeFinExt", MaterialesReportes.EFDMR);
                            try {
                                jsonObject1.put("NumExt", MaterialesReportes.extSerR);
                            }catch (Exception e){
                                jsonObject1.put("NumExt", 0);
                            }
                            jsonArray.put(0, jsonObject1);

                            jsonObject2.put("ObjDescargaMat", jsonObject);
                            jsonObject2.put("Articulos", jsonArray);

                            addDescargaR(context, jsonObject2);
                        } catch (Exception e) {
                        }
                    } else {

                        accion = "Modificar";
                       /* GetGetDescargaMaterialArticulosByIdClvOrdenListResult nuevo = new GetGetDescargaMaterialArticulosByIdClvOrdenListResult(
                                ,,
                                ,,,,,
                                ,,,
                                ,,0
                        );*/
                        GetGetDescargaMaterialArticulosByIdClvOrdenListResult nuevo = new GetGetDescargaMaterialArticulosByIdClvOrdenListResult();
                        nuevo.setCANTIDADUTILIZADA(MaterialesReportes.totalDMR);
                        nuevo.setClvOrdSer(Util.getClvOrden(Util.preferences));
                        nuevo.setDescripcion(MaterialesReportes.descripcionMaterialR);
                        nuevo.setESCABLE(escable);
                        nuevo.setMETRAJEFIN(MaterialesReportes.IFDMR);
                        nuevo.setMETRAJEFINEXTERIOR(MaterialesReportes.EFDMR);
                        nuevo.setMETRAJEINICIO(MaterialesReportes.IIDMR);
                        nuevo.setMETRAJEINICIOEXTERIOR(MaterialesReportes.EIMDR);
                        nuevo.setNOARTICULO(MaterialesReportes.idInventarioMDR);
                        nuevo.setNoExt(MaterialesReportes.extSerR);
                        try{
                            nuevo.setTecnico(Util.getNombreTecnicoPreference(Util.preferences));
                        }catch (Exception e){
                            nuevo.setTecnico("");
                        }
                        nuevo.setTipoDescarga(Util.getTipoDescarga(Util.preferences));
                        nuevo.setIdDescarga(0);

                        array.dataDescargaDirecta.get(0).add(nuevo);

                        Iterator<List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>> itdata = array.dataDescargaDirecta.iterator();
                        List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult> dat = itdata.next();

                        Log.d("asd",array.dataDescargaDirecta.toString());
                        Log.d("asd",nuevo.toString());
                        //array.dataDescargaDirecta.add((List<GetGetDescargaMaterialArticulosByIdClvOrdenListResult>) nuevo);

                        array.listaTabla.add(new ArrayList<String>());
                        array.listaTabla.get(array.listaTabla.size()-1).add(String.valueOf(MaterialesReportes.descripcionMaterialR));
                        array.listaTabla.get(array.listaTabla.size()-1).add(String.valueOf(MaterialesReportes.totalDMR));
                        array.listaTabla.get(array.listaTabla.size()-1).add(String.valueOf(MaterialesReportes.extSerR));
                        array.listaTabla.get(array.listaTabla.size()-1).add(String.valueOf(MaterialesReportes.idInventarioMDR));

                        EliminarMaterialAdapter adapter;
                        MaterialesReportes.elimarMaterialesListR.setVisibility(View.VISIBLE);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 1);
                        MaterialesReportes.elimarMaterialesListR.setLayoutManager(layoutManager);
                        adapter = new EliminarMaterialAdapter(array.listaTabla, context, activity);
                        MaterialesReportes.elimarMaterialesListR.setAdapter(adapter);
                    }




                } else {
                    ErrorMensaje(context,"Error al conseguir datos de bitacora "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void DamebitacoraDirectaR(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).bitacoraDirecta();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JsonObject userJson = response.body().getAsJsonObject("GetchecaBitacoraTecnicoResult");
                        //Introduccion de datos del request en el Modelo para poder usarlos

                        GetchecaBitacoraTecnicoModel user = new GetchecaBitacoraTecnicoModel(
                                userJson.get("idBitacora").getAsInt()
                        );
                        NoBitacora = user.idBitacora;
                    }catch (Exception e){
                        NoBitacora = 0;
                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir datos de bitacora "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
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
                        try{
                        constraintLayoutMACWAM.setVisibility(View.GONE);
                        }catch (Exception e){}
                        try{
                            CambioAparato.textView35.setVisibility(View.GONE);
                           CambioAparato.MACWAMTextCambioAparato.setVisibility(View.GONE);
                        }catch (Exception e){}
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

    public void AsignaMACWAMCA(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).asignaMACWAM();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {

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

                                    if (Util.getPermisisDescarga(Util.preferences) == true) {
                                        try{
                                            if (Array.dataDescargaDirecta.get(0).size() != 0) {
                                                try {
                                                    JSONObject jsonDescarga = new JSONObject();
                                                    JSONObject jsonDescarga1 = new JSONObject();
                                                    JSONArray jsonArray = new JSONArray();
                                                    jsonDescarga.put("IdTecnico", Util.getClvTec(Util.preferences));
                                                    jsonDescarga.put("ClvOrden", Util.getClvQueja(Util.preferences));
                                                    jsonDescarga.put("IdAlmacen", 0);
                                                    jsonDescarga.put("Accion", "Agregar");
                                                    jsonDescarga.put("IdBitacora", 0);
                                                    jsonDescarga.put("TipoDescarga", Util.getTipoDescarga(Util.preferences));
                                                    jsonDescarga.put("Usuario", Util.getClvTec(Util.preferences));
                                                    jsonDescarga.put("Fecha", fechaEjecujtar);
                                                    for (int i = 0; i < Array.dataDescargaDirecta.get(0).size(); i++) {
                                                        JSONObject articulos = new JSONObject();
                                                        articulos.put("NoArticulo", Array.dataDescargaDirecta.get(0).get(i).NOARTICULO);
                                                        articulos.put("Cantidad", Array.dataDescargaDirecta.get(0).get(i).CANTIDADUTILIZADA);
                                                        articulos.put("EsCable", Array.dataDescargaDirecta.get(0).get(i).ESCABLE);
                                                        articulos.put("MetrajeInicio", Array.dataDescargaDirecta.get(0).get(i).METRAJEINICIO);
                                                        articulos.put("MetrajeFin", Array.dataDescargaDirecta.get(0).get(i).METRAJEFIN);
                                                        articulos.put("MetrajeInicioExt", Array.dataDescargaDirecta.get(0).get(i).METRAJEINICIOEXTERIOR);
                                                        articulos.put("MetrajeFinExt", Array.dataDescargaDirecta.get(0).get(i).METRAJEFINEXTERIOR);
                                                        try {
                                                            articulos.put("NumExt", Array.dataDescargaDirecta.get(0).get(i).NoExt);
                                                        } catch (Exception e) {
                                                            articulos.put("NumExt", 0);
                                                        }
                                                        jsonArray.put(i, articulos);
                                                    }


                                                    jsonDescarga1.put("ObjDescargaMat", jsonDescarga);
                                                    jsonDescarga1.put("Articulos", jsonArray);
                                                    jsonDescarga1.put("Autorizacion", 3);

                                                    addDescargaR(context, jsonDescarga1);
                                                } catch (Exception e) {
                                                }
                                            }
                                        }catch (Exception oas){}
                                    }
                                    reporteStatus = "E";
                                    try {
                                        EjecutarReportes.tecSecPosRepo=0;
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
                    ErrorMensaje(context,"Se ha producido un error, verifique su conexión e intente nuevamente Un1");
                    EjecutarOrdenes.dialogEjecutar.dismiss();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }

        });
    }

    public void getArbSerValidar(final Context context,final Spinner spinnerTap,final Spinner spinnerNap, final TextView nap,final TextView tap,
                                 final TextView txtPlaca, final EditText EtxtPlaca) {
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
                      TAP=false;
                      NAP=false;
                      Placa=false;
                      JSONArbolServicios jsonResponse = response.body();
                      array.dataArbSer = new ArrayList<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>>(asList(jsonResponse.GetMuestraArbolServiciosAparatosPorinstalarListResult()));
                      Iterator<List<GetMuestraArbolServiciosAparatosPorinstalarListResult>> itData4 = array.dataArbSer.iterator();
                      while (itData4.hasNext()) {
                          List<GetMuestraArbolServiciosAparatosPorinstalarListResult> dat4 = (List<GetMuestraArbolServiciosAparatosPorinstalarListResult>) itData4.next();
                          for (int i = 0; i < dat4.size(); i++) {
                              array.nombreArbol.add(dat4.get(i).getNombre());
                              if(dat4.get(i).IdMedio==1){
                                  TAP=true;
                                  Placa=true;
                              }
                              if(dat4.get(i).IdMedio==2){
                                  NAP=true;
                                  Placa=true;
                              }

                          }
                      }
                      if(Desc == true){
                          Placa=true;
                      }
                  }catch (Exception e){
                      TAP=false;
                      NAP=false;
                  }
                    if(PermPlaca) {
                        if(Placa==true){
                            txtPlaca.setVisibility(View.VISIBLE);
                            EtxtPlaca.setVisibility(View.VISIBLE);

                            EtxtPlaca.setText(PlacaMA);

                        }else{
                            txtPlaca.setVisibility(View.GONE);
                            EtxtPlaca.setVisibility(View.GONE);
                        }
                    }else{

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
                        tap.setVisibility(View.GONE);
                        spinnerTap.setVisibility(View.GONE);
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
                        nap.setVisibility(View.GONE);
                        spinnerNap.setVisibility(View.GONE);
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
                    ErrorMensaje(context,"Error al conseguir lista de NAP "+response.message());
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
                    ErrorMensaje(context,"Error al conseguir lista de TAP "+response.message());
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
                    ErrorMensaje(context,"Error al conseguir NAP por contrato "+response.message());
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
                    ErrorMensaje(context,"Error al guardar NAP "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void guardaTap(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).guardaTap();
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
                    ErrorMensaje(context,"Error al guarda TAP "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }





//    public String getValidaTrabajos(final Context context, final JSONObject jsonObject) {
//        stringValidaTrabajos="";
//        Call<JsonObject> call = services.RequestPost(context, jsonObject).getVALIOrdSer();
//        call.enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                if (response.code() == 200) {
//                    System.out.println("Entra");
//                    stringValidaTrabajos = String.valueOf(response.body().getAsJsonPrimitive("GetSP_ValidaGuardaOrdSerAparatosResult"));
//                }else{
//                    ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
//
//                }
//            }
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                ErrorMensaje(context,"Se ha producido un error, verifique su conexion e intente nuevamente");
//            }
//        });
//        return stringValidaTrabajos;
//
//    }

   /* public void imagen(final Context context, final JSONObject jsonObject, final Activity activity,final ProgressDialog progressDialog) {


        Call<JsonObject> call = services.RequestImagen(context, jsonObject).imagen();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    progressDialog.dismiss();
                    activity.finish();
                } else {
                    ErrorMensaje(context,"Error al mandar fotografias");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error al mandar fotografias");
            }
        });
    }*/
   /* public void GuardaCoordenadasR(final Context context) {

        Service service = null;
        try {
            service = services.getGuardaCoordenadasRService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Call<JsonObject> call = service.getGuardaCoordenadas();
        call.enqueue(new Callback<JsonObject>() {


            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response1) {
                reintentaB = 0;

                if (response1.code() == 200) {
                    dialogVisitaRepo.dismiss();
                    Toast.makeText(context, "Reporte guardado correctamente", Toast.LENGTH_LONG).show();
                    getListQuejas(context);

                } else {
                    ErrorMensaje(context,"Se ha producido un error al guardar coordenadas");
                    dialogVisitaRepo.dismiss();

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Se ha producido un error al guardar coordenadas");
            }
        });
    }*/

    public void getColonia(final Context context) {
        Call<JSONColonia> call = services.getColonia(context).getColonia();
        call.enqueue(new Callback<JSONColonia>() {
            @Override
            public void onResponse(Call<JSONColonia> call, Response<JSONColonia> response) {
                if (response.code() == 200) {
                    JSONColonia jsonResponse = response.body();

                    Array.dataColonia = new ArrayList<>(asList(jsonResponse.getColoniaResult()));
                    Iterator<List<GetColoniaResult>> itdata = Array.dataColonia.iterator();
                    Array.datosSpinnerColonia.clear();
                    Array.datosSpinnerColonia.add("Seleccione colonia");
                    while (itdata.hasNext()) {
                        List<GetColoniaResult> dat = itdata.next();

                        for (int i = 0; i < dat.size(); i++) {
                            Array.datosSpinnerColonia.add(dat.get(i).getNombre());
                        }
                        adapterColonia = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, Array.datosSpinnerColonia);
                        NAPTAP.spinnerColonia.setAdapter(adapterColonia);


                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir lista de colonias "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONColonia> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getNAPTAP(final Context context, final JSONObject jsonObject,final RecyclerView recyclerView, final String taponap, final Activity activity) {
        Call<JSONAPTAP> call = services.RequestPost(context,jsonObject).getNAPTAP();
        call.enqueue(new Callback<JSONAPTAP>() {
            @Override
            public void onResponse(Call<JSONAPTAP> call, Response<JSONAPTAP> response) {
                if (response.code() == 200) {
                    NAPAdapter adapterNap;
                    TAPAdapter adapterTap;
                    JSONAPTAP jsonResponse = response.body();
                    Array.tapFiltrado.clear();
                    Array.tapFiltradoPoste.clear();
                    Array.idTapCoordenadas.clear();
                    Array.tapLatitud.clear();
                    Array.tapLongitud.clear();
                    Array.tapnap = new ArrayList<>(asList(jsonResponse.GetListaNapTapResult()));
                    Iterator<List<GetListaNapTapResult>> itdata = Array.tapnap.iterator();
                    while (itdata.hasNext()) {
                        List<GetListaNapTapResult> dat = itdata.next();

                        for (int i = 0; i < dat.size(); i++) {
                            Array.tapFiltrado.add(dat.get(i).getNapTap());
                            Array.tapFiltradoPoste.add(dat.get(i).getPoste());
                            Array.idTapCoordenadas.add(dat.get(i).getIdNapTap());
                            Array.tapLatitud.add(dat.get(i).getLatitud());
                            Array.tapLongitud.add(dat.get(i).getLongitud());
                        }

                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,1);
                        recyclerView.setLayoutManager(layoutManager);
                        if(taponap.equals("NAP")){
                            adapterNap = new NAPAdapter(context,activity,"NAP");
                            recyclerView.setAdapter(adapterNap);
                        }
                        if(taponap.equals("CTO")){
                            adapterNap = new NAPAdapter(context,activity,"CTO");
                            recyclerView.setAdapter(adapterNap);
                        }
                        if(taponap.equals("TAP")){
                            adapterTap = new TAPAdapter(context,activity,"TAP");
                            recyclerView.setAdapter(adapterTap);
                        }


                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir lista filtrada "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONAPTAP> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void setTAPCoo(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).getTapCoo();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {

                    Toast.makeText(context,"Se han guardado correctamente las coordenadas",Toast.LENGTH_SHORT).show();
                    Intent intento = new Intent(context, Saldo.class);
                    intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intento);

                } else {
                    ErrorMensaje(context,"Error al guardar coordenadas "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void setNAPCoo(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).getNapCoo();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {

                    Toast.makeText(context,"Se han guardado correctamente las coordenadas",Toast.LENGTH_SHORT).show();
                    Intent intento = new Intent(context, Saldo.class);
                    intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intento);

                } else {
                    ErrorMensaje(context,"Error al guardar coordenadas "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void getServiciosCAMDO(final Context context, final JSONObject jsonObject,final Spinner spinnerTap,final Spinner spinnerNap, final TextView napCAMDO,final TextView tapCAMDO) {
        Call<JSONServiciosCAMDO> call = services.RequestPost(context, jsonObject).getServiciosCAMDO();
        call.enqueue(new Callback<JSONServiciosCAMDO>() {
            @Override
            public void onResponse(Call<JSONServiciosCAMDO> call, Response<JSONServiciosCAMDO> response) {
                if (response.code() == 200) {

                    TAPCAMDO=false;
                    NAPCAMDO=false;

                    JSONServiciosCAMDO jsonResponse = response.body();
                    Array.serviciosCAMDO = new ArrayList<>(asList(jsonResponse.getServiciosResult()));
                    Iterator<List<GetServiciosResult>> itdata = Array.serviciosCAMDO.iterator();
                    ArrayList<String>datos=new ArrayList<>();
                    while (itdata.hasNext()) {
                        List<GetServiciosResult> dat = itdata.next();

                        for (int i = 0; i < dat.size(); i++) {
                           if(dat.get(i).getStatus().equals("I")||dat.get(i).getStatus().equals("C")){
                               if(dat.get(i).getIdMedio()==1){
                                   TAPCAMDO=true;
                               }
                               if(dat.get(i).getIdMedio()==2){
                                   NAPCAMDO=true;
                               }
                           }
                        }

                        if(TAPCAMDO==true){
                           tapCAMDO.setVisibility(View.VISIBLE);
                            spinnerTap.setVisibility(View.VISIBLE);
//                            try{
//                                JSONObject jsonObject = new JSONObject();
//                                jsonObject.put("contrato",ContratoReal);
//                                getTap(context,jsonObject,spinnerTap);
//                            }catch (Exception e){}
                            getDeepCAMDOTAPNAP(context,"TAP",spinnerTap);



                        }else{
                            tapCAMDO.setVisibility(View.INVISIBLE);
                            spinnerTap.setVisibility(View.INVISIBLE);
                        }




                        if(NAPCAMDO==true){
                           napCAMDO.setVisibility(View.VISIBLE);
                            spinnerNap.setVisibility(View.VISIBLE);
//
//                            try{
//                                JSONObject jsonObject = new JSONObject();
//                                jsonObject.put("contrato",ContratoReal);
//                                getNapContrato(context,jsonObject,spinnerNap);
//                            }catch (Exception e){}

                            getDeepCAMDOTAPNAP(context,"NAP",spinnerNap);
                        }else{
                            napCAMDO.setVisibility(View.INVISIBLE);
                            spinnerNap.setVisibility(View.INVISIBLE);
                        }

                    }
                } else {
                    ErrorMensaje(context,"Error "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONServiciosCAMDO> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void getDeepCAMDOTAPNAP(final Context context, final String napotap,final Spinner spinner) {
        Service service = null;
        try {
            service = services.getCAMODOService(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<JSONCAMDO> call = service.getDataCAMDO();
        call.enqueue(new Callback<JSONCAMDO>() {
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
                            coloniacmdo = dat.get(0).colonia;
                        }
                        if(napotap.equals("NAP")){
                            try{
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("op",0);
                                jsonObject.put("nombre_Colonia",coloniacmdo);
                                getNAPTAPCAMDO(context,jsonObject,spinner);
                            }catch (Exception e){}
                        }
                        if(napotap.equals("TAP")){
                            try{
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("op",1);
                                jsonObject.put("nombre_Colonia",coloniacmdo);
                                getNAPTAPCAMDO(context,jsonObject,spinner);
                            }catch (Exception e){}
                        }

                    } catch (Exception e) {
                        coloniacmdo = "";
                        ErrorMensaje(context,"Error");
                    }
                } else {
                    ErrorMensaje(context,"Error "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONCAMDO> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void getNAPTAPCAMDO(final Context context, final JSONObject jsonObject,final Spinner spinner) {
        Call<JSONAPTAP> call = services.RequestPost(context,jsonObject).getNAPTAP();
        call.enqueue(new Callback<JSONAPTAP>() {
            @Override
            public void onResponse(Call<JSONAPTAP> call, Response<JSONAPTAP> response) {
                if (response.code() == 200) {
                    JSONAPTAP jsonResponse = response.body();
                    Array.tapFiltrado.clear();
                    Array.tapFiltrado.add("<Seleccionar>");
                    Array.tapnap = new ArrayList<>(asList(jsonResponse.GetListaNapTapResult()));
                    Iterator<List<GetListaNapTapResult>> itdata = Array.tapnap.iterator();
                    while (itdata.hasNext()) {
                        List<GetListaNapTapResult> dat = itdata.next();

                        for (int i = 0; i < dat.size(); i++) {
                            Array.tapFiltrado.add(dat.get(i).getNapTap());
                        }
                        adapterTAPNAPCAMDO = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, Array.tapFiltrado);
                        spinner.setAdapter(adapterTAPNAPCAMDO);


                    }
                } else {
                    ErrorMensaje(context,"Error al conseguir lista filtrada "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONAPTAP> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getDatosclienteNum(final Context context, final JSONObject jsonObject) {
        Call<JSONDATOSCLIENTE> call = services.RequestPost(context, jsonObject).getDatosclienteNum();
        call.enqueue(new Callback<JSONDATOSCLIENTE>() {
            @Override
            public void onResponse(Call<JSONDATOSCLIENTE> call, Response<JSONDATOSCLIENTE> response) {
                if (response.code() == 200) {
                    JSONDATOSCLIENTE jsonResponse = response.body();

                    Array.dataClientes = new ArrayList<>(asList(jsonResponse.GetConsultaClientesListResult()));
                    Iterator<List<GetConsultaClientesListResult>> itdata = Array.dataClientes.iterator();
                    ArrayList<String>datos=new ArrayList<>();
                    while (itdata.hasNext()) {
                        List<GetConsultaClientesListResult> dat = itdata.next();

                            if(dat.get(0).getCELULAR()!=null && dat.get(0).getTELEFONO()!=null){
                                MainActivity.TelyCel.setText("Celular: "+ String.valueOf(dat.get(0).getCELULAR()) +
                                        "\n"+ "Telefono: "+String.valueOf(dat.get(0).getTELEFONO()));
                            }
                           else if(dat.get(0).getCELULAR()!=null && dat.get(0).getTELEFONO()==null){
                                MainActivity.TelyCel.setText("Celular: "+ String.valueOf(dat.get(0).getCELULAR()));
                            }
                           else if(dat.get(0).getCELULAR()==null && dat.get(0).getTELEFONO()!=null){
                            MainActivity.TelyCel.setText("Telefono: "+String.valueOf(dat.get(0).getTELEFONO()));
                            }
                            else if(dat.get(0).getCELULAR()!=null && dat.get(0).getTELEFONO()!=null){
                                MainActivity.TelyCel.setText("Celular: Sin celular, Telefono: Sin telefono ");
                            }
                            try {
                                if(PermCableCentro == true)
                                    MainActivity.Direccion.setText(dat.get(0).getCol());
                                else
                                    if (!dat.get(0).getNumInt().equals(null)) {
                                        MainActivity.Direccion.setText(dat.get(0).getCalle() + ", Num Ext:" + dat.get(0).getNUMERO() + ", Num Int:" + dat.get(0).getNumInt() + ", " + dat.get(0).getCol());
                                    } else {
                                        MainActivity.Direccion.setText(dat.get(0).getCalle() + ", Num Ext:" + dat.get(0).getNUMERO() + " " + dat.get(0).getCol());
                                    }
                            }catch (Exception e){
                                MainActivity.Direccion.setText(dat.get(0).getCalle() + ", Num Ext:" + dat.get(0).getNUMERO() + " " + dat.get(0).getCol());
                            }




                    }


                } else {
                    ErrorMensaje(context,"Error al conseguir "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONDATOSCLIENTE> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getDatosclienteNumR(final Context context, final JSONObject jsonObject) {
        Call<JSONDATOSCLIENTE> call = services.RequestPost(context, jsonObject).getDatosclienteNum();
        call.enqueue(new Callback<JSONDATOSCLIENTE>() {
            @Override
            public void onResponse(Call<JSONDATOSCLIENTE> call, Response<JSONDATOSCLIENTE> response) {
                if (response.code() == 200) {
                    JSONDATOSCLIENTE jsonResponse = response.body();

                    Array.dataClientes = new ArrayList<>(asList(jsonResponse.GetConsultaClientesListResult()));
                    Iterator<List<GetConsultaClientesListResult>> itdata = Array.dataClientes.iterator();
                    ArrayList<String>datos=new ArrayList<>();
                    while (itdata.hasNext()) {
                        List<GetConsultaClientesListResult> dat = itdata.next();

                        if(dat.get(0).getCELULAR()!=null && dat.get(0).getTELEFONO()!=null){
                            MainReportes.TelyCelR.setText("Celular: "+ String.valueOf(dat.get(0).getCELULAR()) +
                                    "\n"+ "Telefono: "+String.valueOf(dat.get(0).getTELEFONO()));
                        }
                        else if(dat.get(0).getCELULAR()!=null && dat.get(0).getTELEFONO()==null){
                            MainReportes.TelyCelR.setText("Celular: "+ String.valueOf(dat.get(0).getCELULAR()));
                        }
                        else if(dat.get(0).getCELULAR()==null && dat.get(0).getTELEFONO()!=null){
                            MainReportes.TelyCelR.setText("Telefono: "+String.valueOf(dat.get(0).getTELEFONO()));
                        }
                        else if(dat.get(0).getCELULAR()!=null && dat.get(0).getTELEFONO()!=null){
                            MainReportes.TelyCelR.setText("Celular: Sin celular, Telefono: Sin telefono ");
                        }

                        try {
                            if(PermCableCentro==true)
                                MainReportes.Direccion1.setText(dat.get(0).getCol());
                            else
                                if (!dat.get(0).getNumInt().equals(null)) {
                                    MainReportes.Direccion1.setText(dat.get(0).getCalle() + ", Num Ext:" + dat.get(0).getNUMERO() + ", Num Int:" + dat.get(0).getNumInt() + ", " + dat.get(0).getCol());
                                } else {
                                    MainReportes.Direccion1.setText(dat.get(0).getCalle() + ", Num Ext:" + dat.get(0).getNUMERO() + " " + dat.get(0).getCol());
                                }
                        }catch (Exception e){
                            MainReportes.Direccion1.setText(dat.get(0).getCalle() + ", Num Ext:" + dat.get(0).getNUMERO() + " " + dat.get(0).getCol());
                        }


                    }


                } else {
                    ErrorMensaje(context,"Error al conseguir "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JSONDATOSCLIENTE> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }


    public void getCalles(final Context context, final JSONObject jsonObject, final TextView Entrecalles, final TextView referenciastxt, final TextView referencias) {
        Call<JsonObject> call = services.RequestPost(context, jsonObject).getCalles();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject userJson = response.body().getAsJsonObject("GetSoftvWEb_DameEntrecallesResult");
                    String jsonToString = String.valueOf(userJson);
                    GetSoftvWEb_DameEntrecalles calles = new GetSoftvWEb_DameEntrecalles();
                    Gson gson = new Gson();
                    calles = gson.fromJson(jsonToString,GetSoftvWEb_DameEntrecalles.class);
                    if(calles.getCasa().equals("N")){
                        if(calles.getSur().equals("")){
                            if(calles.getEste().equals("")){
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText("");
                                }else{
                                    Entrecalles.setText(calles.getOeste());
                                }
                            }else{
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText(calles.getEste());
                                }else{
                                    Entrecalles.setText(calles.getEste()+", "+calles.getOeste());
                                }
                            }
                        }else{
                            if(calles.getEste().equals("")){
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText(calles.getSur());
                                }else{
                                    Entrecalles.setText(calles.getSur()+", "+calles.getOeste());
                                }
                            }else{
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText(calles.getSur()+", "+calles.getEste());
                                }else{
                                    Entrecalles.setText(calles.getSur()+", "+calles.getEste()+", "+calles.getOeste());
                                }
                            }
                        }
                    }else if(calles.getCasa().equals("S")){
                        if(calles.getNorte().equals("")){
                            if(calles.getEste().equals("")){
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText("");
                                }else{
                                    Entrecalles.setText(calles.getOeste());
                                }
                            }else{
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText(calles.getEste());
                                }else{
                                    Entrecalles.setText(calles.getEste()+", "+calles.getOeste());
                                }
                            }
                        }else{
                            if(calles.getEste().equals("")){
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText(calles.getNorte());
                                }else{
                                    Entrecalles.setText(calles.getNorte()+", "+calles.getOeste());
                                }
                            }else{
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText(calles.getNorte()+", "+calles.getEste());
                                }else{
                                    Entrecalles.setText(calles.getNorte()+", "+calles.getEste()+", "+calles.getOeste());
                                }
                            }
                        }
                    }else if(calles.getCasa().equals("E")){
                        if(calles.getNorte().equals("")){
                            if(calles.getSur().equals("")){
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText("");
                                }else{
                                    Entrecalles.setText(calles.getOeste());
                                }
                            }else{
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText(calles.getSur());
                                }else{
                                    Entrecalles.setText(calles.getSur()+", "+calles.getOeste());
                                }
                            }
                        }else{
                            if(calles.getSur().equals("")){
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText(calles.getNorte());
                                }else{
                                    Entrecalles.setText(calles.getNorte()+", "+calles.getOeste());
                                }
                            }else{
                                if(calles.getOeste().equals("")){
                                    Entrecalles.setText(calles.getNorte()+", "+calles.getSur());
                                }else{
                                    Entrecalles.setText(calles.getNorte()+", "+calles.getSur()+", "+calles.getOeste());
                                }
                            }
                        }
                    }else if(calles.getCasa().equals("O")){
                        if(calles.getNorte().equals("")){
                            if(calles.getSur().equals("")){
                                if(calles.getEste().equals("")){
                                    Entrecalles.setText("");
                                }else{
                                    Entrecalles.setText(calles.getEste());
                                }
                            }else{
                                if(calles.getEste().equals("")){
                                    Entrecalles.setText(calles.getSur());
                                }else{
                                    Entrecalles.setText(calles.getSur()+", "+calles.getEste());
                                }
                            }
                        }else{
                            if(calles.getSur().equals("")){
                                if(calles.getEste().equals("")){
                                    Entrecalles.setText(calles.getNorte());
                                }else{
                                    Entrecalles.setText(calles.getNorte()+", "+calles.getEste());
                                }
                            }else{
                                if(calles.getEste().equals("")){
                                    Entrecalles.setText(calles.getNorte()+", "+calles.getSur());
                                }else{
                                    Entrecalles.setText(calles.getNorte()+", "+calles.getSur()+", "+calles.getEste());
                                }
                            }
                        }
                    }

                    if(calles.getReferencia().equals("")){
                        referenciastxt.setVisibility(View.GONE);
                        referencias.setVisibility(View.GONE);
                    }else{
                        referenciastxt.setVisibility(View.VISIBLE);
                        referencias.setVisibility(View.VISIBLE);
                        referencias.setText(calles.getReferencia());
                    }

                }else{
                    ErrorMensaje(context,"Error al conseguir datos del calles "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }



    public void addPlaca(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).addPlaca();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    ErrorMensaje(context,"Placa guardada "+response.message());
                } else {
                    ErrorMensaje(context,"Error al guardar Placa "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void getPlaca(final Context context, final JSONObject jsonObject, final EditText EtxtPlaca) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).getPlaca();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    JsonObject userJson = response.body().getAsJsonObject("GetConRelCtePlacabyContratoResult");
                    String jsonToString = String.valueOf(userJson);
                    GetConRelCtePlacabyContrato getConRelCtePlacabyContrato;
                    Gson gson = new Gson();
                    getConRelCtePlacabyContrato = gson.fromJson(jsonToString,GetConRelCtePlacabyContrato.class);
                    EtxtPlaca.setText(getConRelCtePlacabyContrato.Placa);

                }else{
                    ErrorMensaje(context,"Error al conseguir datos placa "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void Permisos(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).getPermisos();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        JSONObject Result = new JSONObject(jsonObject.getString("ConsultaPermisosResult"));
                        /*PermBolivia = true;
                        PermVamra = true;
                        PermPlaca = false;*/
                        if(Integer.parseInt(Result.getString("Bolivia"))==1){
                            PermBolivia = true;
                        }
                        if(Integer.parseInt(Result.getString("Vamra"))==1){
                            PermVamra = true;
                        }
                        if(Integer.parseInt(Result.getString("Placa"))==1){
                            PermPlaca = true;
                        }
                        if(Integer.parseInt(Result.getString("Vallarta"))==1){
                            PermVallarta = false;
                        }
                        if(Integer.parseInt(Result.getString("Cobro"))==1){
                            PermCobro = true;
                            Toast toast1 = Toast.makeText(context, "Activado el cobro", Toast.LENGTH_SHORT);toast1.show();
                        }
                        if(Integer.parseInt(Result.getString("CableCentro"))==1){
                            PermCableCentro = true;
                        }
                    } catch (JSONException e) {
                        PermBolivia = false;
                        PermVamra = false;
                        PermPlaca = true;
                        PermVallarta = false;
                        PermCobro = false;
                        PermCableCentro = false;
                    }

                }else{
                    ErrorMensaje(context,"Error al conseguir los permisos "+response.message());
                    PermBolivia = true;
                    PermPlaca = true;
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void DameCodigo(final Context context, final JSONObject jsonObject, final EditText codreg) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).CodReg();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        codreg.setText(jsonObject.getString("GetCodigoRegistroResult"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    ErrorMensaje(context,"Error al conseguir el código registro "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void ValidaCodigo(final Context context, final JSONObject jsonObject, final EditText codreg) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).VCodReg();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        if(Integer.parseInt(jsonObject.getString("ValidarCodigoRegistroResult"))==1){
                            AsignarAparato.valido = 1;
                        }else{
                            AsignarAparato.valido = 0;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    ErrorMensaje(context,"Error al conseguir el código registro "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
    public void SetCodigoRegistro(final Context context, final JSONObject jsonObject, final EditText codreg) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).SCodReg();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        codreg.setText(jsonObject.getString("GetCodigoRegistroResult"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ErrorMensaje(context,"Guardado código de registro");
                }else{
                    ErrorMensaje(context,"Error al conseguir el código registro "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

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
                        for (int i = 0; i < dat.size(); i++){
                            Array.ContratoCompuestoList.add(String.valueOf(dat.get(i).getContratoCompuesto()));
                            Array.NombreList.add(String.valueOf(dat.get(i).getNombre()));
                            Array.TelefonoList.add(String.valueOf(dat.get(i).getTelefono()));
                            Array.CalleNumeroList.add(String.valueOf(dat.get(i).getCalle()) + " #" + String.valueOf(dat.get(i).getNumero()));
                            Array.ColoniaList.add(String.valueOf(dat.get(i).getColonia()));
                            Array.ContratoList.add(String.valueOf(dat.get(i).getContrato()));
                        }
                        if (Array.ContratoCompuestoList.size() == 0 && i==1){
                            ErrorMensaje(applicationContext,"Contrato no encontrada ");
                            statusBusquedaOrden = false;
                        }else if (Array.NombreList.size() == 0 && i==2){
                            ErrorMensaje(applicationContext,"Nombre no encontrado ");
                            statusBusquedaContrato = false;
                        }else if (Array.NombreList.size() == 0 && i==3){
                            ErrorMensaje(applicationContext,"Placa no encontrado ");
                            statusBusquedaPresinto = false;
                        }

                    }
                    Saldo.adaptercl = new ClientesAdapter(applicationContext, Array.ContratoCompuestoList, Array.NombreList, Array.TelefonoList, Array.CalleNumeroList, Array.ColoniaList, Array.ContratoList);
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
                    DecimalFormat formato = new DecimalFormat("$0.00");
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

    public void GuardarPago(final Context context, final JSONObject jsonObject){
        Call<JsonObject> call = services.RequestPost(context,jsonObject).GuardarPago();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject1 = new JSONObject(new Gson().toJson(response.body()));
                        if(jsonObject1.getInt("GuardaPagoMOVILResult")!=0){
                            CLV_FACTURA=jsonObject1.getInt("GuardaPagoMOVILResult");
                            try{
                                JSONObject jsonObject = new JSONObject();
                                jsonObject.put("Clv_Factura", CLV_FACTURA);
                                GetTicketNom(context, jsonObject);
                            }catch (Exception x){
                                Toast toast2 = Toast.makeText(context, "Error al registrar el pago", Toast.LENGTH_SHORT);
                                toast2.show();
                            }

                        }else{
                            Toast toast1 = Toast.makeText(context, "Pago NO registrado", Toast.LENGTH_SHORT);toast1.show();
                        }
                    } catch (JSONException e) {
                        ErrorMensaje(context,"Error al recibir la respuesta "+response.message());
                    }

                }else{
                    ErrorMensaje(context,"Error al conseguir el código registro "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }



    public void GetTicketNom(final Context context, final JSONObject jsonObject) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).GetTicketNombre();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {
                    GetTicketResult = Constants.URL_REPORTES;
                    try {
                        JSONObject jsonObject1 = new JSONObject(new Gson().toJson(response.body()));
                        String x = jsonObject1.getString("GetTicketResult");
                        GetTicketResult = GetTicketResult + x;

                        Intent intent1 = new Intent(context, PDF.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent1);

                    } catch (JSONException e) {
                        ErrorMensaje(context,"Error al recibir la respuesta GetTicketNom"+response.message());
                    }

                }else{
                    ErrorMensaje(context,"Error al conseguir el ticket "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }


    public void VentaRenta(final Context context, final int clvOrden) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("Contrato", clvOrden);
        }catch (Exception x){
        }
        Call<JsonObject> call = services.RequestPost(context,jsonObject).GetVENTARENTA();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {

                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        JSONObject Result = new JSONObject(jsonObject.getString("VentaRentaResult"));
                        if(RENTA == null && VENTA==null){
                            RENTA = Result.getString("Renta");
                            VENTA = Result.getString("Venta");
                        }
                    } catch (JSONException e) {
                        ErrorMensaje(context,"Error al conseguir la cantidad de aparatos en venta y renta "+response.message());
                    }

                }else{
                    ErrorMensaje(context,"Error al conseguir la cantidad de aparatos en venta y renta "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }

    public void DatosCambio(final TextView anterior, final TextView nuevo, final JSONObject jsonObject, final Context context) {
        Call<JsonObject> call = services.RequestPost(context,jsonObject).GetDireccionesCAMDO();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.code() == 200) {

                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        JSONObject Result = new JSONObject(jsonObject.getString("DireccionesCAMDOResult"));
                        anterior.setText(Result.getString("Antigua"));
                        nuevo.setText(Result.getString("Nueva"));
                    } catch (JSONException e) {
                        anterior.setText("Direccion anterior");
                        nuevo.setText("Direccion nueva");
                        ErrorMensaje(context,"Error al mostrar direcciones "+response.message());
                    }
                    Cambio=0;
                }else{
                    ErrorMensaje(context,"Error al conseguir direcciones "+response.message());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ErrorMensaje(context,"Error "+t.getMessage());
            }
        });
    }
}