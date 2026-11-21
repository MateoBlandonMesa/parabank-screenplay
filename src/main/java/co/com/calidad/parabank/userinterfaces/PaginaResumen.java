package co.com.calidad.parabank.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class PaginaResumen {

    public static final Target TITULO = Target.the("título del resumen")
            .located(By.xpath("//h1[contains(.,'Accounts Overview')]"));
    public static final Target TABLA = Target.the("tabla de cuentas")
            .located(By.id("accountTable"));

    private PaginaResumen() {
    }
}
