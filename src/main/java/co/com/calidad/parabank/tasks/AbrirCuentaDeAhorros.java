package co.com.calidad.parabank.tasks;

import co.com.calidad.parabank.interactions.EsperarVisible;
import co.com.calidad.parabank.interactions.HacerClic;
import co.com.calidad.parabank.interactions.RecordarTexto;
import co.com.calidad.parabank.interactions.SeleccionarLista;
import co.com.calidad.parabank.userinterfaces.MenuBanca;
import co.com.calidad.parabank.userinterfaces.PaginaNuevaCuenta;
import co.com.calidad.parabank.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class AbrirCuentaDeAhorros implements Task {

    public static AbrirCuentaDeAhorros enElBanco() {
        return new AbrirCuentaDeAhorros();
    }

    @Override
    @Step("{0} abre una cuenta de ahorros")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                HacerClic.en(MenuBanca.NUEVA_CUENTA),
                EsperarVisible.el(PaginaNuevaCuenta.TIPO),
                SeleccionarLista.elTexto("SAVINGS", PaginaNuevaCuenta.TIPO),
                SeleccionarLista.elIndice(0, PaginaNuevaCuenta.CUENTA_ORIGEN),
                HacerClic.en(PaginaNuevaCuenta.BOTON),
                EsperarVisible.el(PaginaNuevaCuenta.TITULO),
                RecordarTexto.de(PaginaNuevaCuenta.NUMERO, Constantes.LLAVE_CUENTA)
        );
    }
}
