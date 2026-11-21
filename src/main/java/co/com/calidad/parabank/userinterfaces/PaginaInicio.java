package co.com.calidad.parabank.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class PaginaInicio {

    public static final Target USUARIO = Target.the("usuario")
            .located(By.cssSelector("#loginPanel input[name='username']"));
    public static final Target CLAVE = Target.the("clave")
            .located(By.cssSelector("#loginPanel input[name='password']"));
    public static final Target BOTON = Target.the("botón ingresar")
            .located(By.cssSelector("#loginPanel input[value='Log In']"));
    public static final Target REGISTRO = Target.the("enlace de registro")
            .located(By.linkText("Register"));
    public static final Target TITULO = Target.the("título de ingreso")
            .located(By.xpath("//h2[contains(.,'Customer Login')]"));

    private PaginaInicio() {
    }
}
