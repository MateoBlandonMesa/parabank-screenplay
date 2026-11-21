package co.com.calidad.parabank.stepdefinitions;

import co.com.calidad.parabank.questions.ElResumenDeCuentas;
import co.com.calidad.parabank.questions.ElTexto;
import co.com.calidad.parabank.questions.LaCuentaNueva;
import co.com.calidad.parabank.tasks.AbrirCuentaDeAhorros;
import co.com.calidad.parabank.tasks.AbrirParaBank;
import co.com.calidad.parabank.tasks.ActualizarContacto;
import co.com.calidad.parabank.tasks.CerrarSesion;
import co.com.calidad.parabank.tasks.ConsultarResumen;
import co.com.calidad.parabank.tasks.IniciarSesion;
import co.com.calidad.parabank.tasks.RegistrarCliente;
import co.com.calidad.parabank.tasks.TransferirFondos;
import co.com.calidad.parabank.userinterfaces.PaginaInicio;
import co.com.calidad.parabank.userinterfaces.PaginaNuevaCuenta;
import co.com.calidad.parabank.userinterfaces.PaginaPerfil;
import co.com.calidad.parabank.userinterfaces.PaginaRegistro;
import co.com.calidad.parabank.userinterfaces.PaginaResumen;
import co.com.calidad.parabank.userinterfaces.PaginaTransferencia;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

public class ParaBankStepDefinitions {

    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Dado("que el cliente abre la página de ParaBank")
    public void queElClienteAbreLaPaginaDeParaBank() {
        OnStage.theActorCalled("Cliente").attemptsTo(AbrirParaBank.enElNavegador());
    }

    @Cuando("se registra con datos válidos y un usuario único")
    public void seRegistraConDatosValidos() {
        theActorInTheSpotlight().attemptsTo(RegistrarCliente.conDatosValidos());
    }

    @Entonces("ve el mensaje de cuenta creada correctamente")
    public void veElMensajeDeCuentaCreada() {
        theActorInTheSpotlight().should(
                seeThat("el mensaje de bienvenida",
                        ElTexto.de(PaginaRegistro.MENSAJE_EXITO),
                        containsString("Your account was created successfully"))
        );
    }

    @Cuando("intenta registrarse con contraseñas distintas")
    public void intentaRegistrarseConContrasenasDistintas() {
        theActorInTheSpotlight().attemptsTo(RegistrarCliente.conClavesDistintas());
    }

    @Entonces("ve el mensaje de que las contraseñas no coinciden")
    public void veElMensajeDeContrasenas() {
        theActorInTheSpotlight().should(
                seeThat("el error de confirmación",
                        ElTexto.de(PaginaRegistro.ERROR_CLAVE),
                        containsString("Passwords did not match"))
        );
    }

    @Cuando("intenta registrarse sin diligenciar el formulario")
    public void intentaRegistrarseSinDatos() {
        theActorInTheSpotlight().attemptsTo(RegistrarCliente.sinDatos());
    }

    @Cuando("intenta registrarse sin diligenciar {string}")
    public void intentaRegistrarseSinDiligenciar(String campo) {
        theActorInTheSpotlight().attemptsTo(RegistrarCliente.omitiendo(campo));
    }

    @Entonces("ve el aviso {string}")
    public void veElAviso(String mensaje) {
        theActorInTheSpotlight().should(
                seeThat("el aviso del dato faltante",
                        ElTexto.delPanelDerecho(),
                        containsString(mensaje))
        );
    }

    @Entonces("ve que el nombre es obligatorio")
    public void veQueElNombreEsObligatorio() {
        theActorInTheSpotlight().should(
                seeThat("el error de nombre",
                        ElTexto.de(PaginaRegistro.ERROR_NOMBRE),
                        containsString("First name is required"))
        );
    }

    @Cuando("intenta iniciar sesión sin escribir las credenciales")
    public void intentaIniciarSesionSinCredenciales() {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.sinCredenciales());
    }

    @Entonces("sigue en el formulario de ingreso")
    public void sigueEnElFormularioDeIngreso() {
        theActorInTheSpotlight().should(
                seeThat("el formulario de ingreso",
                        ElTexto.de(PaginaInicio.TITULO),
                        containsString("Customer Login"))
        );
    }

    @Y("cierra la sesión")
    public void cierraLaSesion() {
        theActorInTheSpotlight().attemptsTo(CerrarSesion.delCliente());
    }

    @Y("inicia sesión con el usuario que acaba de registrar")
    public void iniciaSesionConElUsuarioRegistrado() {
        theActorInTheSpotlight().attemptsTo(IniciarSesion.conElUsuarioRegistrado());
    }

    @Entonces("ve el resumen de sus cuentas")
    public void veElResumenDeSusCuentas() {
        theActorInTheSpotlight().should(
                seeThat("el título del resumen",
                        ElTexto.de(PaginaResumen.TITULO),
                        containsString("Accounts Overview"))
        );
    }

    @Y("abre una cuenta de ahorros")
    public void abreUnaCuentaDeAhorros() {
        theActorInTheSpotlight().attemptsTo(AbrirCuentaDeAhorros.enElBanco());
    }

    @Entonces("ve el número de la nueva cuenta de ahorros")
    public void veElNumeroDeLaNuevaCuenta() {
        theActorInTheSpotlight().should(
                seeThat("el aviso de cuenta abierta",
                        ElTexto.de(PaginaNuevaCuenta.TITULO),
                        containsString("Account Opened")),
                seeThat("el número recordado",
                        LaCuentaNueva.numeroRecordado(),
                        not(isEmptyOrNullString()))
        );
    }

    @Cuando("consulta el resumen de cuentas")
    public void consultaElResumenDeCuentas() {
        theActorInTheSpotlight().attemptsTo(ConsultarResumen.deLasCuentas());
    }

    @Entonces("la nueva cuenta aparece en el resumen")
    public void laNuevaCuentaApareceEnElResumen() {
        theActorInTheSpotlight().should(
                seeThat("la cuenta nueva está en la tabla",
                        ElResumenDeCuentas.contieneLaCuentaNueva(),
                        is(true))
        );
    }

    @Cuando("transfiere fondos entre sus cuentas")
    public void transfiereFondos() {
        theActorInTheSpotlight().attemptsTo(TransferirFondos.entreCuentas());
    }

    @Entonces("ve que la transferencia fue completada")
    public void veQueLaTransferenciaFueCompletada() {
        theActorInTheSpotlight().should(
                seeThat("el aviso de transferencia",
                        ElTexto.de(PaginaTransferencia.TITULO),
                        containsString("Transfer Complete"))
        );
    }

    @Cuando("actualiza su información de contacto")
    public void actualizaSuInformacion() {
        theActorInTheSpotlight().attemptsTo(ActualizarContacto.delCliente());
    }

    @Entonces("ve que el perfil fue actualizado")
    public void veQueElPerfilFueActualizado() {
        theActorInTheSpotlight().should(
                seeThat("el aviso de perfil",
                        ElTexto.de(PaginaPerfil.TITULO),
                        containsString("Profile Updated"))
        );
    }
}
