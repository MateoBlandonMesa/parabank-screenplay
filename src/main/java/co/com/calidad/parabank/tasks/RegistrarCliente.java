package co.com.calidad.parabank.tasks;

import co.com.calidad.parabank.interactions.Escribir;
import co.com.calidad.parabank.interactions.EsperarVisible;
import co.com.calidad.parabank.interactions.HacerClic;
import co.com.calidad.parabank.models.Cliente;
import co.com.calidad.parabank.userinterfaces.PaginaInicio;
import co.com.calidad.parabank.userinterfaces.PaginaRegistro;
import co.com.calidad.parabank.utils.Constantes;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;

public class RegistrarCliente implements Task {

    private enum Modo { VALIDO, CLAVES_DISTINTAS, VACIO, OMITIR }

    private final Modo modo;
    private final String campoOmitido;

    private RegistrarCliente(Modo modo, String campoOmitido) {
        this.modo = modo;
        this.campoOmitido = campoOmitido;
    }

    public static RegistrarCliente conDatosValidos() {
        return new RegistrarCliente(Modo.VALIDO, null);
    }

    public static RegistrarCliente conClavesDistintas() {
        return new RegistrarCliente(Modo.CLAVES_DISTINTAS, null);
    }

    public static RegistrarCliente sinDatos() {
        return new RegistrarCliente(Modo.VACIO, null);
    }

    public static RegistrarCliente omitiendo(String campo) {
        return new RegistrarCliente(Modo.OMITIR, campo);
    }

    @Override
    @Step("{0} diligencia el registro")
    public <T extends Actor> void performAs(T actor) {
        if (modo == Modo.VACIO) {
            actor.attemptsTo(
                    HacerClic.en(PaginaInicio.REGISTRO),
                    EsperarVisible.el(PaginaRegistro.BOTON),
                    HacerClic.en(PaginaRegistro.BOTON),
                    EsperarVisible.el(PaginaRegistro.ERROR_NOMBRE)
            );
            return;
        }

        Cliente cliente = Cliente.nuevoUnico();
        if (modo == Modo.CLAVES_DISTINTAS) {
            cliente = cliente.conConfirmacionDistinta();
        }

        actor.remember(Constantes.LLAVE_USUARIO, cliente.usuario());
        actor.remember(Constantes.LLAVE_CLAVE, cliente.clave());
        actor.attemptsTo(
                HacerClic.en(PaginaInicio.REGISTRO),
                EsperarVisible.el(PaginaRegistro.NOMBRE)
        );
        escribir(actor, "nombre", cliente.nombre(), PaginaRegistro.NOMBRE);
        escribir(actor, "apellido", cliente.apellido(), PaginaRegistro.APELLIDO);
        escribir(actor, "direccion", cliente.direccion(), PaginaRegistro.DIRECCION);
        escribir(actor, "ciudad", cliente.ciudad(), PaginaRegistro.CIUDAD);
        escribir(actor, "estado", cliente.estado(), PaginaRegistro.ESTADO);
        escribir(actor, "codigo postal", cliente.codigoPostal(), PaginaRegistro.CODIGO_POSTAL);
        escribir(actor, "telefono", cliente.telefono(), PaginaRegistro.TELEFONO);
        escribir(actor, "seguro social", cliente.seguroSocial(), PaginaRegistro.SEGURO_SOCIAL);
        escribir(actor, "usuario", cliente.usuario(), PaginaRegistro.USUARIO);
        escribir(actor, "clave", cliente.clave(), PaginaRegistro.CLAVE);
        escribir(actor, "confirmacion", cliente.confirmacion(), PaginaRegistro.CONFIRMACION);
        actor.attemptsTo(HacerClic.en(PaginaRegistro.BOTON));

        if (modo == Modo.VALIDO) {
            actor.attemptsTo(EsperarVisible.el(PaginaRegistro.MENSAJE_EXITO));
        } else if (modo == Modo.OMITIR) {
            actor.attemptsTo(EsperarVisible.el(PaginaRegistro.errorDe(campoOmitido)));
        } else {
            actor.attemptsTo(EsperarVisible.el(PaginaRegistro.ERROR_CLAVE));
        }
    }

    private <T extends Actor> void escribir(T actor, String campo, String valor, Target destino) {
        if (modo == Modo.OMITIR && campo.equals(campoOmitido)) {
            return;
        }
        actor.attemptsTo(Escribir.elTexto(valor).en(destino));
    }
}
