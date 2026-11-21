package co.com.calidad.parabank.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class Paneles {

    public static final Target DERECHO = Target.the("panel derecho")
            .located(By.id("rightPanel"));

    private Paneles() {
    }
}
