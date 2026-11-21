package co.com.calidad.parabank.tasks;

import co.com.calidad.parabank.interactions.Escribir;
import co.com.calidad.parabank.interactions.EsperarValor;
import co.com.calidad.parabank.interactions.EsperarVisible;
import co.com.calidad.parabank.interactions.HacerClic;
import co.com.calidad.parabank.userinterfaces.MenuBanca;
import co.com.calidad.parabank.userinterfaces.PaginaPerfil;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ActualizarContacto implements Task {

    public static ActualizarContacto delCliente() {
        return new ActualizarContacto();
    }

    @Override
    @Step("{0} actualiza su información de contacto")
    public <T extends Actor> void performAs(T actor) {
        String telefono = "310-555-" + String.valueOf(System.currentTimeMillis()).substring(9);
        actor.attemptsTo(
                HacerClic.en(MenuBanca.PERFIL),
                EsperarValor.delCampo(PaginaPerfil.NOMBRE),
                Escribir.elTexto(telefono).en(PaginaPerfil.TELEFONO),
                HacerClic.en(PaginaPerfil.BOTON),
                EsperarVisible.el(PaginaPerfil.TITULO)
        );
    }
}
