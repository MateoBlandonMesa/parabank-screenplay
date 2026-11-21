package co.com.calidad.parabank.tasks;

import co.com.calidad.parabank.interactions.EsperarVisible;
import co.com.calidad.parabank.interactions.HacerClic;
import co.com.calidad.parabank.userinterfaces.MenuBanca;
import co.com.calidad.parabank.userinterfaces.PaginaResumen;
import co.com.calidad.parabank.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ConsultarResumen implements Task {

    public static ConsultarResumen deLasCuentas() {
        return new ConsultarResumen();
    }

    @Override
    @Step("{0} consulta el resumen de cuentas")
    public <T extends Actor> void performAs(T actor) {
        String numero = actor.recall(Constantes.LLAVE_CUENTA);
        Target enlace = Target.the("cuenta " + numero).located(By.linkText(numero));
        actor.attemptsTo(
                HacerClic.en(MenuBanca.RESUMEN),
                EsperarVisible.el(PaginaResumen.TITULO),
                EsperarVisible.el(enlace)
        );
    }
}
