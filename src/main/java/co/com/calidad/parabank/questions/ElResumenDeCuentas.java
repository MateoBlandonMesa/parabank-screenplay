package co.com.calidad.parabank.questions;

import co.com.calidad.parabank.userinterfaces.PaginaResumen;
import co.com.calidad.parabank.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class ElResumenDeCuentas implements Question<Boolean> {

    public static ElResumenDeCuentas contieneLaCuentaNueva() {
        return new ElResumenDeCuentas();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String numero = actor.recall(Constantes.LLAVE_CUENTA);
        if (numero == null || numero.isBlank()) {
            return false;
        }
        try {
            return Text.of(PaginaResumen.TABLA).answeredBy(actor).contains(numero);
        } catch (RuntimeException e) {
            return false;
        }
    }
}
