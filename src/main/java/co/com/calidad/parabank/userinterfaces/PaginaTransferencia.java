package co.com.calidad.parabank.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class PaginaTransferencia {

    public static final Target MONTO = Target.the("monto")
            .located(By.id("amount"));
    public static final Target ORIGEN = Target.the("cuenta origen de la transferencia")
            .located(By.id("fromAccountId"));
    public static final Target DESTINO = Target.the("cuenta destino de la transferencia")
            .located(By.id("toAccountId"));
    public static final Target BOTON = Target.the("botón transferir")
            .located(By.cssSelector("#transferForm input[value='Transfer']"));
    public static final Target TITULO = Target.the("transferencia completa")
            .located(By.xpath("//h1[contains(.,'Transfer Complete!')]"));

    private PaginaTransferencia() {
    }
}
