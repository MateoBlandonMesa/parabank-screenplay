package co.com.calidad.parabank.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public class ElTexto implements Question<String> {

    private final Target zona;

    private ElTexto(Target zona) {
        this.zona = zona;
    }

    public static ElTexto de(Target zona) {
        return new ElTexto(zona);
    }

    public static ElTexto delPanelDerecho() {
        return de(co.com.calidad.parabank.userinterfaces.Paneles.DERECHO);
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            return Text.of(zona).answeredBy(actor);
        } catch (RuntimeException e) {
            return "";
        }
    }
}
