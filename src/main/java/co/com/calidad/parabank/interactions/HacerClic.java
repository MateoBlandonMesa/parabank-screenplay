package co.com.calidad.parabank.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

public class HacerClic implements Interaction {

    private final Target objetivo;

    private HacerClic(Target objetivo) {
        this.objetivo = objetivo;
    }

    public static HacerClic en(Target objetivo) {
        return new HacerClic(objetivo);
    }

    @Override
    @Step("{0} hace clic")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                EsperarVisible.el(objetivo),
                Click.on(objetivo),
                Esperar.unMomento()
        );
    }
}
