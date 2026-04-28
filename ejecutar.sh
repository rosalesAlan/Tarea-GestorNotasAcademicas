#!/bin/bash

echo "======================================"
echo "  Compilando Sistema de Gestion de Notas"
echo "======================================"

# Compilar todos los archivos Java
javac -d . src/interfaces/*.java src/modelos/*.java src/servicios/*.java src/utilidades/*.java src/Main.java

if [ $? -eq 0 ]; then
    echo ""
    echo "======================================"
    echo "  Ejecutando Sistema..."
    echo "======================================"
    echo ""
    java Main
else
    echo ""
    echo "❌ Error en la compilacion"
fi
