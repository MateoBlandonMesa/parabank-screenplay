package co.com.calidad.parabank.tasks;

import co.com.calidad.parabank.interactions.EnviarLogin;
import co.com.calidad.parabank.interactions.Escribir;
import co.com.calidad.parabank.interactions.EsperarVisible;
import co.com.calidad.parabank.interactions.HacerClic;
import co.com.calidad.parabank.userinterfaces.MenuBanca;
import co.com.calidad.parabank.userinterfaces.PaginaInicio;
import co.com.calidad.parabank.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class IniciarSesion implements Task {

    private enum Modo { VACIA, REGISTRADO }

    private final Modo modo;

    private IniciarSesion(Modo modo) {
        this.modo = modo;
    }

    public static IniciarSesion sinCredenciales() {
        return new IniciarSesion(Modo.VACIA);
    }

    public static IniciarSesion conElUsuarioRegistrado() {
        return new IniciarSesion(Modo.REGISTRADO);
    }

    @Override
    @Step("{0} inicia sesión")
    public <T extends Actor> void performAs(T actor) {
        if (modo == Modo.VACIA) {
            actor.attemptsTo(
                    EnviarLogin.con("", ""),
                    EsperarVisible.el(PaginaInicio.USUARIO)
            );
            return;
        }

        String usuario = actor.recall(Constantes.LLAVE_USUARIO);
        String clave = actor.recall(Constantes.LLAVE_CLAVE);
        actor.attemptsTo(
                Escribir.elTexto(usuario).en(PaginaInicio.USUARIO),
                Escribir.elTexto(clave).en(PaginaInicio.CLAVE),
                HacerClic.en(PaginaInicio.BOTON),
                EsperarVisible.el(MenuBanca.SALIR)
        );
    }
}
