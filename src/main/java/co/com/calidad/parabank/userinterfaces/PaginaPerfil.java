package co.com.calidad.parabank.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class PaginaPerfil {

    public static final Target NOMBRE = Target.the("nombre del perfil")
            .located(By.id("customer.firstName"));
    public static final Target TELEFONO = Target.the("teléfono del perfil")
            .located(By.id("customer.phoneNumber"));
    public static final Target BOTON = Target.the("botón actualizar perfil")
            .located(By.cssSelector("input[value='Update Profile']"));
    public static final Target TITULO = Target.the("perfil actualizado")
            .located(By.xpath("//h1[contains(.,'Profile Updated')]"));

    private PaginaPerfil() {
    }
}
