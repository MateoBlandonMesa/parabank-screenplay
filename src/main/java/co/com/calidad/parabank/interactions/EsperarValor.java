package co.com.calidad.parabank.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EsperarValor implements Interaction {

    private final Target campo;

    private EsperarValor(Target campo) {
        this.campo = campo;
    }

    public static EsperarValor delCampo(Target campo) {
        return new EsperarValor(campo);
    }

    @Override
    @Step("{0} espera a que el formulario termine de cargar los datos")
    public <T extends Actor> void performAs(T actor) {
        WebDriverWait espera = new WebDriverWait(
                BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(30));
        espera.until(driver -> tieneTexto(actor));
    }

    private boolean tieneTexto(Actor actor) {
        try {
            String valor = campo.resolveFor(actor).getAttribute("value");
            return valor != null && !valor.isBlank();
        } catch (RuntimeException e) {
            return false;
        }
    }
}
