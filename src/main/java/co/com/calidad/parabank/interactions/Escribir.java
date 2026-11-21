package co.com.calidad.parabank.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

public class Escribir implements Interaction {

    private final String texto;
    private final Target campo;

    private Escribir(String texto, Target campo) {
        this.texto = texto;
        this.campo = campo;
    }

    public static Destino elTexto(String texto) {
        return campo -> new Escribir(texto, campo);
    }

    public interface Destino {
        Escribir en(Target campo);
    }

    @Override
    @Step("{0} escribe en el campo")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                EsperarVisible.el(campo),
                Enter.theValue(texto).into(campo),
                Esperar.unMomento()
        );
    }
}
