package co.com.calidad.parabank.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleccionarLista implements Interaction {

    private final Target lista;
    private final String textoVisible;
    private final int indice;

    private SeleccionarLista(Target lista, String textoVisible, int indice) {
        this.lista = lista;
        this.textoVisible = textoVisible;
        this.indice = indice;
    }

    public static SeleccionarLista elTexto(String textoVisible, Target lista) {
        return new SeleccionarLista(lista, textoVisible, -1);
    }

    public static SeleccionarLista elIndice(int indice, Target lista) {
        return new SeleccionarLista(lista, null, indice);
    }

    @Override
    @Step("{0} selecciona una opción de la lista")
    public <T extends Actor> void performAs(T actor) {
        int minimo = textoVisible == null ? indice + 1 : 1;
        WebDriverWait espera = new WebDriverWait(
                BrowseTheWeb.as(actor).getDriver(), Duration.ofSeconds(30));
        espera.until(driver -> cantidad(actor) >= minimo);

        WebElement elemento = lista.resolveFor(actor);
        Select selector = new Select(elemento);
        if (textoVisible != null) {
            selector.selectByVisibleText(textoVisible);
        } else {
            selector.selectByIndex(indice);
        }
        actor.attemptsTo(Esperar.unMomento());
    }

    private int cantidad(Actor actor) {
        try {
            return lista.resolveFor(actor).findElements(By.tagName("option")).size();
        } catch (RuntimeException e) {
            return 0;
        }
    }
}
