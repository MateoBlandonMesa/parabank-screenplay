package co.com.calidad.parabank.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EsperarVisible implements Interaction {

    private final Target objetivo;

    private EsperarVisible(Target objetivo) {
        this.objetivo = objetivo;
    }

    public static EsperarVisible el(Target objetivo) {
        return new EsperarVisible(objetivo);
    }

    @Override
    @Step("{0} espera a que el elemento esté visible")
    public <T extends Actor> void performAs(T actor) {
        WebDriverWait espera = new WebDriverWait(
                BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(30));
        espera.until(driver -> visible(actor));
    }

    private boolean visible(Actor actor) {
        try {
            WebElement elemento = objetivo.resolveFor(actor);
            return elemento.isDisplayed();
        } catch (RuntimeException e) {
            return false;
        }
    }
}
