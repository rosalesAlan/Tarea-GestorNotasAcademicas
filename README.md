# Sistema de Gestión de Notas

Un proyecto completo en Java que implementa los **5 pilares de la Programación Orientada a Objetos** para gestionar notas de estudiantes por materia.

## 📋 Descripción

Este sistema permite a:
- **Estudiantes**: Ver sus notas y promedios
- **Profesores**: Agregar notas a estudiantes en sus materias asignadas

## 🏗️ Pilares de POO Implementados

✅ **1. Clase Abstracta**
- Clase `Usuario` es abstracta y no puede instanciarse directamente
- Define el comportamiento común de todos los usuarios

✅ **2. Herencia**
- `Estudiante` y `Profesor` heredan de `Usuario`
- Reutilizan atributos y métodos de la clase base

✅ **3. Interfaces**
- `Gestionable`: Define operaciones de gestión (guardar, eliminar, actualizar)
- `Visualizable`: Define métodos para mostrar información
- Implementadas en `Materia`, `Nota` y `Usuario`

✅ **4. Encapsulamiento**
- Todos los atributos son **privados**
- Acceso controlado mediante **getters y setters públicos**
- Protección de datos sensibles

✅ **5. Abstracción**
- Métodos abstractos como `mostrarPermisos()` en `Usuario`
- Ocultamiento de complejidad del sistema
- Interfaz amigable en consola

## 📁 Estructura del Proyecto

```
src/
├── interfaces/
│   ├── Gestionable.java
│   └── Visualizable.java
├── modelos/
│   ├── Usuario.java (CLASE ABSTRACTA)
│   ├── Estudiante.java (HERENCIA)
│   ├── Profesor.java (HERENCIA)
│   ├── Materia.java (INTERFACES)
│   └── Nota.java (INTERFACES)
├── servicios/
│   ├── SistemaAutenticacion.java
│   └── GestorNotas.java
└── utilidades/
    └── Menu.java
```

## 🚀 Cómo Ejecutar

### En Windows:
```bash
ejecutar.bat
```

### En Linux/Mac:
```bash
chmod +x ejecutar.sh
./ejecutar.sh
```

### O manualmente:
```bash
# Compilar
javac -d . src/interfaces/*.java src/modelos/*.java src/servicios/*.java src/utilidades/*.java src/Main.java

# Ejecutar
java Main
```

## 👤 Usuarios de Prueba

### Estudiantes:
| Nombre | Contraseña |
|--------|-----------|
| Juan Pérez | 1234 |
| María González | 5678 |
| Carlos López | 9012 |

### Profesores:
| Nombre | Contraseña |
|--------|-----------|
| Ana Martínez | profesor1 |
| Roberto Silva | profesor2 |

## 📊 Funcionalidades

### Para Estudiantes:
- ✓ Ver todas sus notas
- ✓ Ver promedio general
- ✓ Ver promedio por materia
- ✓ Ver sus permisos

### Para Profesores:
- ✓ Agregar notas a estudiantes
- ✓ Ver sus materias asignadas
- ✓ Ver estudiantes por materia
- ✓ Ver sus permisos

## 📚 Materias Disponibles

1. **Matemáticas** (Prof. Ana Martínez)
2. **Física** (Prof. Ana Martínez)
3. **Programación** (Prof. Roberto Silva)
4. **Base de Datos** (Prof. Roberto Silva)

## 💾 Datos y Escala de Calificación

- **Rango de notas**: 0 - 5
- **Nota mínima aprobatoria**: 3.0
- **Escala de letras**:
  - A: 4.5 - 5.0
  - B+: 4.0 - 4.4
  - B: 3.5 - 3.9
  - C: 3.0 - 3.4
  - F: 0 - 2.9

## 🔒 Seguridad

- Autenticación requerida para acceder al sistema
- Contraseñas almacenadas (en la demostración, sin encriptación por simplicidad)
- Permisos diferenciados por rol

## 📝 Características Especiales

- 🎨 Interfaz en consola amigable con separadores visuales
- 📈 Cálculo automático de promedios
- 🔄 Sistema de sesiones con login/logout
- ✓ Validación de datos de entrada
- 📋 Visualización clara de información

## 🎓 Aplicaciones Educativas

Este proyecto es ideal para aprender:
- Programación Orientada a Objetos
- Diseño de sistemas
- Patrones de diseño básicos
- Manejo de colecciones en Java
- Interfaces de usuario en consola

## 📄 Autor

Proyecto educativo de Programación 3
