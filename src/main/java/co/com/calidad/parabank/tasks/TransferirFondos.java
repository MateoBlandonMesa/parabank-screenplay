package co.com.calidad.parabank.tasks;

import co.com.calidad.parabank.interactions.Escribir;
import co.com.calidad.parabank.interactions.EsperarVisible;
import co.com.calidad.parabank.interactions.HacerClic;
import co.com.calidad.parabank.interactions.SeleccionarLista;
import co.com.calidad.parabank.userinterfaces.MenuBanca;
import co.com.calidad.parabank.userinterfaces.PaginaTransferencia;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class TransferirFondos implements Task {

    public static TransferirFondos entreCuentas() {
        return new TransferirFondos();
    }

    @Override
    @Step("{0} transfiere fondos entre sus cuentas")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                HacerClic.en(MenuBanca.TRANSFERIR),
                Escribir.elTexto("10").en(PaginaTransferencia.MONTO),
                SeleccionarLista.elIndice(0, PaginaTransferencia.ORIGEN),
                SeleccionarLista.elIndice(1, PaginaTransferencia.DESTINO),
                HacerClic.en(PaginaTransferencia.BOTON),
                EsperarVisible.el(PaginaTransferencia.TITULO)
        );
    }
}
