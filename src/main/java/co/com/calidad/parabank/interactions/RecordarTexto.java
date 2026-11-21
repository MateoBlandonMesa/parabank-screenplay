package co.com.calidad.parabank.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecordarTexto implements Interaction {

    private final Target objetivo;
    private final String llave;

    private RecordarTexto(Target objetivo, String llave) {
        this.objetivo = objetivo;
        this.llave = llave;
    }

    public static RecordarTexto de(Target objetivo, String llave) {
        return new RecordarTexto(objetivo, llave);
    }

    @Override
    @Step("{0} guarda el texto que muestra la pantalla")
    public <T extends Actor> void performAs(T actor) {
        WebDriverWait espera = new WebDriverWait(
                BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(30));
        String texto = espera.until(driver -> leer(actor));
        actor.remember(llave, texto);
    }

    private String leer(Actor actor) {
        try {
            WebElement elemento = objetivo.resolveFor(actor);
            if (!elemento.isDisplayed()) {
                return null;
            }
            String valor = elemento.getText();
            if (valor == null || valor.isBlank()) {
                return null;
            }
            return valor.trim();
        } catch (RuntimeException e) {
            return null;
        }
    }
}
