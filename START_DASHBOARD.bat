@echo off
echo ==============================================================
echo [ SISTEMA NEON STARTUP ]
echo Compilando e Iniciando Dashboard... (Por favor espera)
echo ==============================================================

call mvn clean compile exec:java "-Dexec.mainClass=app.MenuPrincipalGUI"

echo.
echo Presiona cualquier tecla para salir...
pause >nul
