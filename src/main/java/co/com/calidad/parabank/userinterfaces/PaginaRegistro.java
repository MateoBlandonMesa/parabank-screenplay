package co.com.calidad.parabank.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public final class PaginaRegistro {

    public static final Target NOMBRE = campo("nombre", "customer.firstName");
    public static final Target APELLIDO = campo("apellido", "customer.lastName");
    public static final Target DIRECCION = campo("dirección", "customer.address.street");
    public static final Target CIUDAD = campo("ciudad", "customer.address.city");
    public static final Target ESTADO = campo("estado", "customer.address.state");
    public static final Target CODIGO_POSTAL = campo("código postal", "customer.address.zipCode");
    public static final Target TELEFONO = campo("teléfono", "customer.phoneNumber");
    public static final Target SEGURO_SOCIAL = campo("seguro social", "customer.ssn");
    public static final Target USUARIO = campo("usuario", "customer.username");
    public static final Target CLAVE = campo("clave", "customer.password");
    public static final Target CONFIRMACION = campo("confirmación", "repeatedPassword");
    public static final Target BOTON = Target.the("botón registrar")
            .located(By.cssSelector("#customerForm input[value='Register']"));
    public static final Target MENSAJE_EXITO = Target.the("cuenta creada")
            .located(By.xpath("//p[contains(.,'Your account was created successfully')]"));
    public static final Target ERROR_CLAVE = Target.the("error de contraseña")
            .located(By.id("repeatedPassword.errors"));
    public static final Target ERROR_NOMBRE = Target.the("error de nombre")
            .located(By.id("customer.firstName.errors"));

    public static Target errorDe(String campo) {
        String id = switch (campo) {
            case "nombre" -> "customer.firstName.errors";
            case "apellido" -> "customer.lastName.errors";
            case "direccion" -> "customer.address.street.errors";
            case "ciudad" -> "customer.address.city.errors";
            case "estado" -> "customer.address.state.errors";
            case "codigo postal" -> "customer.address.zipCode.errors";
            case "seguro social" -> "customer.ssn.errors";
            case "usuario" -> "customer.username.errors";
            case "clave" -> "customer.password.errors";
            case "confirmacion" -> "repeatedPassword.errors";
            default -> throw new IllegalArgumentException("Campo no contemplado en el registro: " + campo);
        };
        return Target.the("error de " + campo).located(By.id(id));
    }

    private PaginaRegistro() {
    }

    private static Target campo(String nombre, String id) {
        return Target.the(nombre).located(By.id(id));
    }
}
