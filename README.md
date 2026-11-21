# ParaBank con Screenplay

Automatización del taller de Calidad de Software para [ParaBank](https://parabank.parasoft.com/).

## Cómo correr la demo

Doble clic en `ejecutar-demo.bat`.

Chrome se abre solo, ejecuta los 6 escenarios y al final abre el reporte. OneDrive bloquea la carpeta `target`, así que el HTML queda en la carpeta temporal:

`%TEMP%\parabank-screenplay-serenity\index.html`

El `.bat` también copia ese reporte a `reporte-de-respaldo/index.html`. Si ParaBank falla durante la sustentación, abran esa copia y no vuelvan a lanzar el `.bat`.

Para correr solo el esquema del escenario:

```powershell
.\gradlew.bat test "-Dcucumber.filter.tags=@esquema" "-Ddemo.pausa.segundos=1"
```

Para correr solo el flujo del banco (el más largo, el que hay que mostrar):

En PowerShell el comando lleva `.\` al inicio:

```powershell
.\gradlew.bat test "-Dcucumber.filter.tags=@banca" "-Ddemo.pausa.segundos=1"
```

Para correr todo, sin la pausa de un segundo (más rápido, menos fácil de seguir con la vista):

```powershell
.\gradlew.bat test "-Ddemo.pausa.segundos=0" "-Dserenity.step.delay=0"
```

## Qué automatiza

1. Registro exitoso de un cliente nuevo.
2. Registro rechazado si las contraseñas no coinciden.
3. Registro rechazado si el formulario va vacío.
4. Login rechazado cuando no se escriben usuario ni clave. El sitio público, hoy, deja entrar con cualquier clave no vacía como si fuera el último cliente que se registró; por eso el camino excepcional del login es el envío vacío, que sí se queda en el formulario.
5. El cliente nuevo cierra sesión y vuelve a entrar.
6. Con el cliente recién registrado: abre una cuenta de ahorros, la ve en el resumen, transfiere 10 dólares y actualiza el teléfono.
7. Esquema del escenario, etiqueta `@esquema`: el mismo registro se repite tres veces, cada vez sin un dato distinto (nombre, apellido o usuario), y comprueba el aviso de ese campo.

Al registrarse, ParaBank deja una cuenta corriente con saldo. Abrir la de ahorros pasa 90 dólares desde esa cuenta.

## Mapa del patrón, para señalarlo en la sustentación

| Capa del profesor | Dónde está | Qué hace |
| --- | --- | --- |
| Feature | `src/test/resources/features/parabank/banca_en_linea.feature` | La historia, en Gherkin |
| Runner | `runners/RunnerParabank.java` | Arranca los escenarios y genera el reporte |
| Step Definition | `stepdefinitions/ParaBankStepDefinitions.java` | Conecta cada frase del feature con una tarea |
| Actor | el "Cliente" dentro del step definition | Quien ejecuta la historia |
| Task | `tasks/` | Acciones de negocio: registrarse, transferir, actualizar |
| Interaction | `interactions/` | El detalle: clic, escribir, elegir de una lista, esperar |
| Question | `questions/` | La verificación: qué texto quedó en pantalla |
| User interface | `userinterfaces/` | Dónde está cada campo y cada botón |
| Model | `models/Cliente.java` | Los datos del cliente |
