package co.com.calidad.parabank.questions;

import co.com.calidad.parabank.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class LaCuentaNueva implements Question<String> {

    public static LaCuentaNueva numeroRecordado() {
        return new LaCuentaNueva();
    }

    @Override
    public String answeredBy(Actor actor) {
        String numero = actor.recall(Constantes.LLAVE_CUENTA);
        return numero == null ? "" : numero;
    }
}
