# language: es
@parabank
Característica: Operaciones de un cliente en ParaBank
  Como cliente del banco
  Quiero registrarme y operar mis cuentas
  Para administrar mi dinero en línea

  # Al registrarse, ParaBank abre una cuenta corriente con saldo.
  # Abrir la cuenta de ahorros transfiere 90 dólares desde esa cuenta.

  Antecedentes:
    Dado que el cliente abre la página de ParaBank

  @demo @feliz
  Escenario: Registro exitoso de un cliente nuevo
    Cuando se registra con datos válidos y un usuario único
    Entonces ve el mensaje de cuenta creada correctamente

  @excepcion
  Escenario: Registro rechazado cuando las contraseñas no coinciden
    Cuando intenta registrarse con contraseñas distintas
    Entonces ve el mensaje de que las contraseñas no coinciden

  @excepcion
  Escenario: Registro rechazado cuando faltan datos obligatorios
    Cuando intenta registrarse sin diligenciar el formulario
    Entonces ve que el nombre es obligatorio

  @excepcion
  Escenario: Inicio de sesión rechazado sin credenciales
    Cuando intenta iniciar sesión sin escribir las credenciales
    Entonces sigue en el formulario de ingreso

  @demo @alterno
  Escenario: El cliente registrado puede volver a iniciar sesión
    Cuando se registra con datos válidos y un usuario único
    Y cierra la sesión
    Y inicia sesión con el usuario que acaba de registrar
    Entonces ve el resumen de sus cuentas

  @demo @feliz @banca
  Escenario: Apertura de cuenta de ahorros, consulta, transferencia y actualización de datos
    Cuando se registra con datos válidos y un usuario único
    Y abre una cuenta de ahorros
    Entonces ve el número de la nueva cuenta de ahorros
    Cuando consulta el resumen de cuentas
    Entonces la nueva cuenta aparece en el resumen
    Cuando transfiere fondos entre sus cuentas
    Entonces ve que la transferencia fue completada
    Cuando actualiza su información de contacto
    Entonces ve que el perfil fue actualizado

  # Se ejecuta una vez por cada fila de Ejemplos. Los escenarios de arriba no cambian.
  @esquema @excepcion
  Esquema del escenario: El registro indica el dato obligatorio que falta
    Cuando intenta registrarse sin diligenciar "<campo>"
    Entonces ve el aviso "<mensaje>"

    Ejemplos:
      | campo    | mensaje                 |
      | nombre   | First name is required. |
      | apellido | Last name is required.  |
      | usuario  | Username is required.   |
