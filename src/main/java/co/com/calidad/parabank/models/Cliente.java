package co.com.calidad.parabank.models;

public final class Cliente {

    private final String nombre;
    private final String apellido;
    private final String direccion;
    private final String ciudad;
    private final String estado;
    private final String codigoPostal;
    private final String telefono;
    private final String seguroSocial;
    private final String usuario;
    private final String clave;
    private final String confirmacion;

    private Cliente(String nombre, String apellido, String direccion, String ciudad, String estado,
                    String codigoPostal, String telefono, String seguroSocial, String usuario,
                    String clave, String confirmacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
        this.seguroSocial = seguroSocial;
        this.usuario = usuario;
        this.clave = clave;
        this.confirmacion = confirmacion;
    }

    public static Cliente nuevoUnico() {
        String sufijo = String.valueOf(System.currentTimeMillis());
        String clave = "Clave123";
        return new Cliente(
                "Ana", "Garcia", "Calle 10", "Medellin", "ANT", "050001",
                "3001234567", "123456789", "ana" + sufijo, clave, clave
        );
    }

    public Cliente conConfirmacionDistinta() {
        return new Cliente(
                nombre, apellido, direccion, ciudad, estado, codigoPostal,
                telefono, seguroSocial, usuario, clave, clave + "X"
        );
    }

    public String nombre() {
        return nombre;
    }

    public String apellido() {
        return apellido;
    }

    public String direccion() {
        return direccion;
    }

    public String ciudad() {
        return ciudad;
    }

    public String estado() {
        return estado;
    }

    public String codigoPostal() {
        return codigoPostal;
    }

    public String telefono() {
        return telefono;
    }

    public String seguroSocial() {
        return seguroSocial;
    }

    public String usuario() {
        return usuario;
    }

    public String clave() {
        return clave;
    }

    public String confirmacion() {
        return confirmacion;
    }
}
