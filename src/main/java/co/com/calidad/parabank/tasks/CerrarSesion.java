package co.com.calidad.parabank.tasks;

import co.com.calidad.parabank.interactions.EsperarVisible;
import co.com.calidad.parabank.interactions.HacerClic;
import co.com.calidad.parabank.userinterfaces.MenuBanca;
import co.com.calidad.parabank.userinterfaces.PaginaInicio;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class CerrarSesion implements Task {

    public static CerrarSesion delCliente() {
        return new CerrarSesion();
    }

    @Override
    @Step("{0} cierra la sesión")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                HacerClic.en(MenuBanca.SALIR),
                EsperarVisible.el(PaginaInicio.USUARIO)
        );
    }
}
