@echo off
chcp 65001 > nul
cd /d "%~dp0"
echo Ejecutando la demo de ParaBank. Chrome se va a abrir solo.
echo.
call gradlew.bat clean test --no-daemon
set REPORTE=%TEMP%\parabank-screenplay-serenity\index.html
echo.
echo Reporte: %REPORTE%
if exist "%REPORTE%" (
    start "" "%REPORTE%"
    robocopy "%TEMP%\parabank-screenplay-serenity" "%~dp0reporte-de-respaldo" /E /NFL /NDL /NJH /NJS /nc /ns /np > nul
    echo Copia de respaldo: %~dp0reporte-de-respaldo\index.html
) else (
    echo Esta corrida no genero el reporte. Abre la copia anterior:
    echo %~dp0reporte-de-respaldo\index.html
    if exist "%~dp0reporte-de-respaldo\index.html" start "" "%~dp0reporte-de-respaldo\index.html"
)
pause
