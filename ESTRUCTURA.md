# 📦 Estructura del Proyecto - Sistema de Gestión de Notas

## Árbol de Archivos Completo

```
Universidad/Programcion 3/Nueva carpeta (3)/
│
├── 📄 miprograma.js (archivo original del workspace)
│
├── 📂 src/
│   ├── 📂 interfaces/
│   │   ├── Gestionable.java        (INTERFAZ - Operaciones de gestión)
│   │   └── Visualizable.java       (INTERFAZ - Métodos de visualización)
│   │
│   ├── 📂 modelos/
│   │   ├── Usuario.java            (CLASE ABSTRACTA - Base de usuarios)
│   │   ├── Estudiante.java         (HERENCIA de Usuario)
│   │   ├── Profesor.java           (HERENCIA de Usuario)
│   │   ├── Materia.java            (Implementa Gestionable, Visualizable)
│   │   └── Nota.java               (Implementa Visualizable)
│   │
│   ├── 📂 servicios/
│   │   ├── SistemaAutenticacion.java  (Gestiona login de usuarios)
│   │   └── GestorNotas.java           (Gestiona notas y materias)
│   │
│   ├── 📂 utilidades/
│   │   └── Menu.java               (Interfaz interactiva en consola)
│   │
│   └── Main.java                   (PUNTO DE ENTRADA - Ejecutable)
│
├── 📄 ejecutar.bat                 (Script para ejecutar en Windows)
├── 📄 ejecutar.sh                  (Script para ejecutar en Linux/Mac)
├── 📄 README.md                    (Documentación principal)
├── 📄 PILARES_POO.md              (Explicación detallada de los 5 pilares)
├── 📄 INSTALACION.md               (Guía de instalación de requisitos)
└── 📄 ESTRUCTURA.md               (Este archivo)
```

---

## 📊 Diagrama de Relaciones entre Clases

### Jerarquía de Clases (Herencia):

```
                    ┌─────────────┐
                    │   Usuario   │ ◄── CLASE ABSTRACTA
                    │  (abstract) │
                    └──────┬──────┘
                           │
                ┌──────────┼──────────┐
                │ HERENCIA │          │ HERENCIA
                │          │          │
         ┌──────▼─────┐   ┌─▼────────────┐
         │ Estudiante │   │   Profesor   │
         └────────────┘   └──────────────┘
```

### Implementación de Interfaces:

```
┌──────────────┐        ┌──────────────┐
│ Gestionable  │        │ Visualizable │
├──────────────┤        ├──────────────┤
│+ guardar()   │        │+ mostrar()   │
│+ eliminar()  │        │+ detalles()  │
│+ actualizar()│        └──────────────┘
└──────┬───────┘              ▲  ▲
       │                      │  │
       │ (implementa)         │  │ (implementan)
       │                      │  │
       │                 ┌────┴──┴────┬─────────┐
   ┌───▼──────┐        ┌─┴───┐   ┌────┴──┐  ┌──┴──┐
   │  Materia │        │Usuario│ │Materia│  │Nota │
   └──────────┘        └──────┘  └───────┘  └─────┘
```

---

## 🔄 Flujo de Datos del Sistema

```
┌──────────────────────────────────────────────────────────┐
│                    MAIN.JAVA                             │
│         (Punto de entrada del programa)                  │
└──────────────┬───────────────────────────────────────────┘
               │
               ├─► SistemaAutenticacion
               │   ├─ Usuarios predefinidos (3 estudiantes + 2 profesores)
               │   └─ Gestión de login/logout
               │
               ├─► GestorNotas
               │   ├─ Inicializa materias
               │   ├─ Asigna materias a profesores
               │   ├─ Agrega estudiantes a materias
               │   └─ Gestiona notas
               │
               └─► Menu
                   ├─ Interfaz de consola
                   ├─ Menú estudiantes
                   │  ├─ Ver notas
                   │  ├─ Ver promedio
                   │  └─ Ver permisos
                   │
                   └─ Menú profesores
                      ├─ Agregar nota
                      ├─ Ver materias
                      ├─ Ver estudiantes
                      └─ Ver permisos
```

---

## 📋 Responsabilidades por Clase

### Interfaces:

| Interfaz | Responsabilidad |
|----------|-----------------|
| **Gestionable** | Define operaciones CRUD (guardar, eliminar, actualizar) |
| **Visualizable** | Define métodos para mostrar información en consola |

### Modelos (Clases de Datos):

| Clase | Responsabilidad |
|-------|-----------------|
| **Usuario** | Base abstracta para todos los usuarios |
| **Estudiante** | Usuario que puede ver sus notas |
| **Profesor** | Usuario que puede agregar notas |
| **Materia** | Representa una asignatura con estudiantes |
| **Nota** | Representa una calificación de un estudiante |

### Servicios (Lógica de Negocio):

| Servicio | Responsabilidad |
|----------|-----------------|
| **SistemaAutenticacion** | Valida credenciales y gestiona sesiones |
| **GestorNotas** | Gestiona operaciones con notas y materias |

### Utilidades:

| Utilidad | Responsabilidad |
|----------|-----------------|
| **Menu** | Presenta interfaz interactiva en consola |

---

## 🔐 Control de Acceso

### Atributos Privados (Encapsulamiento):

```
Usuario:
  - nombre (privado)
  - apellido (privado)
  - contraseña (privado)
  - tipoUsuario (privado)

Estudiante:
  - idEstudiante (privado)
  - notas (privado)

Profesor:
  - idProfesor (privado)
  - materiasAsignadas (privado)

Materia:
  - nombre (privado)
  - codigo (privado)
  - profesor (privado)
  - estudiantes (privado)

Nota:
  - estudiante (privado)
  - materia (privado)
  - valor (privado)
  - descripcion (privado)
  - profesor (privado)
  - fecha (privado)
```

### Métodos Públicos (Acceso Controlado):

```
Usuario:
  + getNombre(): String
  + getApellido(): String
  + getNombreCompleto(): String
  + verificarContraseña(String): boolean
  + mostrarPermisos(): void (ABSTRACTO)

Estudiante:
  + getIdEstudiante(): String
  + getNotas(): List<Nota>
  + agregarNota(Nota): void
  + getNotasPorMateria(String): List<Nota>
  + calcularPromedio(): double

Profesor:
  + getIdProfesor(): String
  + getMateriasAsignadas(): List<String>
  + asignarMateria(String): void
  + tieneMateria(String): boolean
  + crearNota(...): Nota

Materia:
  + getNombre(): String
  + getCodigo(): String
  + getProfesor(): Profesor
  + getEstudiantes(): List<Estudiante>
  + agregarEstudiante(Estudiante): void
  + obtenerEstudiantePorId(String): Estudiante

Nota:
  + getMateria(): String
  + getValor(): double
  + getDescripcion(): String
  + esAprobatoria(): boolean
  + getCalificacionLetra(): String
```

---

## 💾 Flujo de Datos - Ejemplo Completo

### Caso 1: Estudiante Ve Sus Notas

```
1. Usuario abre el programa
   └─► Main.java crea servicios

2. Usuario selecciona "Iniciar sesión"
   └─► Menu.realizarLogin()

3. Usuario ingresa "Juan Pérez" y "1234"
   └─► SistemaAutenticacion.autenticar()
       └─► Busca usuario con ese nombre y contraseña
       └─► Retorna true y establece usuarioActual

4. Menú reconoce que es estudiante
   └─► Menu.mostrarMenuEstudiante()

5. Estudiante selecciona "Ver mis notas"
   └─► Menu.verNotasEstudiante()
       └─► Obtiene estudiante actual: Estudiante est = (Estudiante) getUsuarioActual()
       └─► Llama GestorNotas.obtenerNotasEstudiante(est)
           └─► Retorna: est.getNotas()
               └─► Retorna List<Nota> del estudiante

6. Muestra todas las notas en formato legible
```

### Caso 2: Profesor Agrega Nota

```
1. Usuario abre el programa
   └─► Main.java crea servicios

2. Usuario es profesor Ana Martínez
   └─► Menú reconoce que es profesor
   └─► Menu.mostrarMenuProfesor()

3. Profesor selecciona "Agregar nota a estudiante"
   └─► Menu.agregarNotaEstudiante()

4. Sistema muestra materias del profesor
   └─► GestorNotas.obtenerMateriasProfesor(profesor)

5. Profesor selecciona "Matemáticas"
   └─► Obtiene estudiantes de esa materia
   └─► Materia.getEstudiantes()

6. Profesor selecciona estudiante "Juan Pérez"
   └─► Ingresa nota: 4.5, descripción: "Examen"

7. Sistema valida y agrega nota
   └─► GestorNotas.agregarNotaEstudiante(juan, "Matemáticas", 4.5, "Examen", ana)
       └─► Profesor.crearNota(juan, "Matemáticas", 4.5, "Examen")
           └─► Crea: new Nota(juan, "Matemáticas", 4.5, "Examen", "Ana Martínez")
       └─► Estudiante.agregarNota(nota)
           └─► juan.notas.add(nota)

8. Confirmación: "Nota agregada exitosamente"
```

---

## 📈 Estadísticas del Proyecto

- **Total de archivos**: 15
- **Total de clases**: 10 (1 abstracta)
- **Total de interfaces**: 2
- **Líneas de código aproximadas**: 900+
- **Métodos públicos**: 50+
- **Métodos privados/protegidos**: 30+
- **Pilares de POO implementados**: 5/5 ✓

---

## 🎯 Funcionalidades Implementadas

- ✓ Sistema de autenticación con login
- ✓ Usuarios (Estudiantes y Profesores)
- ✓ Materias con estudiantes asignados
- ✓ Sistema de notas por materia
- ✓ Cálculo de promedios
- ✓ Escala de calificaciones (F, C, B, B+, A)
- ✓ Control de permisos por rol
- ✓ Interfaz de menú interactiva
- ✓ Validación de datos
- ✓ Sistema de sesiones (login/logout)

---

## 🚀 Cómo Comenzar

1. Asegúrate de tener Java JDK instalado (ver INSTALACION.md)
2. Abre terminal en la carpeta del proyecto
3. Windows: ejecuta `ejecutar.bat`
4. Linux/Mac: ejecuta `./ejecutar.sh`
5. ¡Disfruta el sistema!

---

## 📚 Archivos de Documentación

| Archivo | Contenido |
|---------|----------|
| **README.md** | Descripción general y guía de uso |
| **PILARES_POO.md** | Explicación detallada de los 5 pilares implementados |
| **INSTALACION.md** | Cómo instalar y configurar Java |
| **ESTRUCTURA.md** | Este archivo - estructura del proyecto |

---

## ✨ Características Especiales

- 🎨 Interfaz amigable con separadores visuales
- 🔒 Encapsulamiento completo de datos
- 🔄 Polimorfismo mediante herencia e interfaces
- 📊 Cálculos automáticos de promedios
- ✅ Validación de entrada de datos
- 👥 Sistema de roles y permisos
- 🔐 Autenticación de usuarios
