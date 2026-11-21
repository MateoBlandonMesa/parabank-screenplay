package co.com.calidad.parabank.tasks;

import co.com.calidad.parabank.interactions.Esperar;
import co.com.calidad.parabank.interactions.EsperarVisible;
import co.com.calidad.parabank.userinterfaces.PaginaInicio;
import co.com.calidad.parabank.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirParaBank implements Task {

    public static AbrirParaBank enElNavegador() {
        return new AbrirParaBank();
    }

    @Override
    @Step("{0} abre ParaBank")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(Constantes.URL_INICIO));
        BrowseTheWeb.as(actor).getDriver().manage().deleteAllCookies();
        actor.attemptsTo(
                Open.url(Constantes.URL_INICIO),
                EsperarVisible.el(PaginaInicio.USUARIO),
                Esperar.unMomento()
        );
    }
}
