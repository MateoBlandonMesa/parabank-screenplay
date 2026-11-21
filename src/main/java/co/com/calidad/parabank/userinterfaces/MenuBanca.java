package co.com.calidad.parabank.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class MenuBanca {

    public static final Target NUEVA_CUENTA = Target.the("abrir cuenta")
            .located(By.linkText("Open New Account"));
    public static final Target RESUMEN = Target.the("resumen de cuentas")
            .located(By.linkText("Accounts Overview"));
    public static final Target TRANSFERIR = Target.the("transferir")
            .located(By.linkText("Transfer Funds"));
    public static final Target PERFIL = Target.the("actualizar datos")
            .located(By.linkText("Update Contact Info"));
    public static final Target SALIR = Target.the("cerrar sesión")
            .located(By.linkText("Log Out"));

    private MenuBanca() {
    }
}
