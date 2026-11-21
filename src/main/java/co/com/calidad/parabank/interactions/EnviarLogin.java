package co.com.calidad.parabank.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Envía el formulario con los valores exactos.
 * Chrome a veces sustituye el usuario por una contraseña guardada si solo se hace clic.
 */
public class EnviarLogin implements Interaction {

    private final String usuario;
    private final String clave;

    private EnviarLogin(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public static EnviarLogin con(String usuario, String clave) {
        return new EnviarLogin(usuario, clave);
    }

    @Override
    @Step("{0} envía el formulario de ingreso")
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript(
                "var form = document.createElement('form');"
                        + "form.method = 'POST';"
                        + "form.action = 'login.htm';"
                        + "form.autocomplete = 'off';"
                        + "function campo(nombre, valor) {"
                        + "  var input = document.createElement('input');"
                        + "  input.type = 'hidden';"
                        + "  input.name = nombre;"
                        + "  input.value = valor;"
                        + "  form.appendChild(input);"
                        + "}"
                        + "campo('username', arguments[0]);"
                        + "campo('password', arguments[1]);"
                        + "document.body.appendChild(form);"
                        + "form.submit();",
                usuario, clave
        );
        actor.attemptsTo(Esperar.unMomento());
    }
}
