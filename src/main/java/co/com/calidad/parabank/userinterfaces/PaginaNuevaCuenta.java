package co.com.calidad.parabank.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class PaginaNuevaCuenta {

    public static final Target TIPO = Target.the("tipo de cuenta")
            .located(By.id("type"));
    public static final Target CUENTA_ORIGEN = Target.the("cuenta de origen")
            .located(By.id("fromAccountId"));
    public static final Target BOTON = Target.the("botón abrir cuenta")
            .located(By.cssSelector("#openAccountForm input[value='Open New Account']"));
    public static final Target NUMERO = Target.the("número de la cuenta nueva")
            .located(By.id("newAccountId"));
    public static final Target TITULO = Target.the("cuenta abierta")
            .located(By.xpath("//h1[contains(.,'Account Opened!')]"));

    private PaginaNuevaCuenta() {
    }
}
