package co.com.calidad.parabank.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

/**
 * Pausa entre acciones. La rúbrica pide un delay o un sleep para poder
 * observar la prueba durante la socialización.
 */
public class Esperar implements Interaction {

    @Override
    @Step("{0} espera un momento para que se observe la pantalla")
    public <T extends Actor> void performAs(T actor) {
        long segundos = Long.parseLong(System.getProperty("demo.pausa.segundos", "1"));
        if (segundos <= 0) {
            return;
        }
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static Esperar unMomento() {
        return new Esperar();
    }
}
