package utilidades;

import java.util.Scanner;
import java.util.List;
import modelos.*;
import servicios.*;

/**
 * Clase que gestiona el menú interactivo de la consola
 */
public class Menu {
    
    private Scanner scanner;
    private SistemaAutenticacion autenticacion;
    private GestorNotas gestorNotas;
    
    /**
     * Constructor del menú
     */
    public Menu(SistemaAutenticacion autenticacion, GestorNotas gestorNotas) {
        this.scanner = new Scanner(System.in);
        this.autenticacion = autenticacion;
        this.gestorNotas = gestorNotas;
    }
    
    /**
     * Muestra el menú principal
     */
    public void mostrarMenuPrincipal() {
        while (true) {
            limpiarPantalla();
            System.out.println("═══════════════════════════════════════");
            System.out.println("   SISTEMA DE GESTIÓN DE NOTAS");
            System.out.println("═══════════════════════════════════════");
            
            if (autenticacion.hayUsuarioAutenticado()) {
                Usuario usuarioActual = autenticacion.getUsuarioActual();
                System.out.println("\n👤 Usuario: " + usuarioActual.getNombreCompleto());
                System.out.println("📋 Rol: " + usuarioActual.getTipoUsuario());
                System.out.println("\n───────────────────────────────────────");
                
                if (usuarioActual instanceof Estudiante) {
                    mostrarMenuEstudiante();
                } else if (usuarioActual instanceof Profesor) {
                    mostrarMenuProfesor();
                }
            } else {
                mostrarMenuLogin();
            }
        }
    }
    
    /**
     * Menú de login
     */
    private void mostrarMenuLogin() {
        System.out.println("\n1. Iniciar sesión");
        System.out.println("2. Salir");
        System.out.println("\n───────────────────────────────────────");
        System.out.print("Selecciona una opción: ");
        
        String opcion = scanner.nextLine();
        
        if (opcion.equals("1")) {
            realizarLogin();
        } else if (opcion.equals("2")) {
            System.out.println("\n👋 ¡Hasta luego!");
            System.exit(0);
        } else {
            System.out.println("\n❌ Opción inválida");
            pausa();
        }
    }
    
    /**
     * Realizar login
     */
    private void realizarLogin() {
        limpiarPantalla();
        System.out.println("═══════════════════════════════════════");
        System.out.println("         INICIAR SESIÓN");
        System.out.println("═══════════════════════════════════════");
        
        System.out.print("\nNombre y Apellido (ej: Juan Pérez): ");
        String nombreCompleto = scanner.nextLine();
        
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();
        
        if (autenticacion.autenticar(nombreCompleto, contraseña)) {
            System.out.println("\n✓ ¡Sesión iniciada correctamente!");
            pausa();
        } else {
            System.out.println("\n❌ Usuario o contraseña incorrectos");
            pausa();
        }
    }
    
    /**
     * Menú para estudiantes
     */
    private void mostrarMenuEstudiante() {
        System.out.println("\n1. Ver mis notas");
        System.out.println("2. Ver promedio general");
        System.out.println("3. Ver permisos");
        System.out.println("4. Cerrar sesión");
        System.out.println("\n───────────────────────────────────────");
        System.out.print("Selecciona una opción: ");
        
        String opcion = scanner.nextLine();
        
        switch (opcion) {
            case "1":
                verNotasEstudiante();
                break;
            case "2":
                verPromedioEstudiante();
                break;
            case "3":
                verPermisosEstudiante();
                break;
            case "4":
                autenticacion.cerrarSesion();
                System.out.println("\n✓ Sesión cerrada");
                pausa();
                break;
            default:
                System.out.println("\n❌ Opción inválida");
                pausa();
        }
    }
    
    /**
     * Menú para profesores
     */
    private void mostrarMenuProfesor() {
        System.out.println("\n1. Agregar nota a estudiante");
        System.out.println("2. Ver mis materias");
        System.out.println("3. Ver estudiantes de una materia");
        System.out.println("4. Ver permisos");
        System.out.println("5. Cerrar sesión");
        System.out.println("\n───────────────────────────────────────");
        System.out.print("Selecciona una opción: ");
        
        String opcion = scanner.nextLine();
        
        switch (opcion) {
            case "1":
                agregarNotaEstudiante();
                break;
            case "2":
                verMateriasProfesor();
                break;
            case "3":
                verEstudiantesMateria();
                break;
            case "4":
                verPermisosProfesor();
                break;
            case "5":
                autenticacion.cerrarSesion();
                System.out.println("\nSesión cerrada");
                pausa();
                break;
            default:
                System.out.println("\n Opción inválida");
                pausa();
        }
    }
    
    /**
     * Ver notas del estudiante
     */
    private void verNotasEstudiante() {
        limpiarPantalla();
        System.out.println("═══════════════════════════════════════");
        System.out.println("         MIS NOTAS");
        System.out.println("═══════════════════════════════════════\n");
        
        Estudiante estudiante = (Estudiante) autenticacion.getUsuarioActual();
        List<Nota> notas = gestorNotas.obtenerNotasEstudiante(estudiante);
        
        if (notas.isEmpty()) {
            System.out.println("No tienes notas aún.");
        } else {
            System.out.println(" Todas tus notas:\n");
            for (Nota nota : notas) {
                System.out.println("  • " + nota.obtenerDetalles());
            }
        }
        
        pausa();
    }
    
    /**
     * Ver promedio del estudiante
     */
    private void verPromedioEstudiante() {
        limpiarPantalla();
        System.out.println("═══════════════════════════════════════");
        System.out.println("         MI PROMEDIO");
        System.out.println("═══════════════════════════════════════\n");
        
        Estudiante estudiante = (Estudiante) autenticacion.getUsuarioActual();
        double promedio = gestorNotas.calcularPromedioEstudiante(estudiante);
        
        System.out.println(" Promedio general: " + String.format("%.2f", promedio));
        
        // Mostrar promedios por materia
        List<Materia> materias = gestorNotas.obtenerTodasMaterias();
        System.out.println("\nPromedios por materia:\n");
        
        boolean tieneMaterias = false;
        for (Materia materia : materias) {
            if (materia.obtenerEstudiantePorId(estudiante.getIdEstudiante()) != null) {
                double promMateria = gestorNotas.obtenerPromedioPorMateria(estudiante, materia.getNombre());
                if (promMateria > 0) {
                    System.out.println("  • " + materia.getNombre() + ": " + String.format("%.2f", promMateria));
                    tieneMaterias = true;
                }
            }
        }
        
        if (!tieneMaterias) {
            System.out.println("  No tienes notas aún");
        }
        
        pausa();
    }
    
    /**
     * Agregar nota a un estudiante
     */
    private void agregarNotaEstudiante() {
        limpiarPantalla();
        System.out.println("═══════════════════════════════════════");
        System.out.println("      AGREGAR NOTA A ESTUDIANTE");
        System.out.println("═══════════════════════════════════════\n");
        
        Profesor profesor = (Profesor) autenticacion.getUsuarioActual();
        List<Materia> materias = gestorNotas.obtenerMateriasProfesor(profesor);
        
        if (materias.isEmpty()) {
            System.out.println(" No tienes materias asignadas");
            pausa();
            return;
        }
        
        // Seleccionar materia
        System.out.println("Mis materias:\n");
        for (int i = 0; i < materias.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + materias.get(i).getNombre());
        }
        
        System.out.print("\nSelecciona una materia: ");
        int opcionMateria;
        try {
            opcionMateria = Integer.parseInt(scanner.nextLine()) - 1;
            if (opcionMateria < 0 || opcionMateria >= materias.size()) {
                System.out.println("Opción inválida");
                pausa();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida");
            pausa();
            return;
        }
        
        Materia materiaSeleccionada = materias.get(opcionMateria);
        List<Estudiante> estudiantes = materiaSeleccionada.getEstudiantes();
        
        if (estudiantes.isEmpty()) {
            System.out.println("\nEsta materia no tiene estudiantes");
            pausa();
            return;
        }
        
        // Seleccionar estudiante
        System.out.println("\nEstudiantes en " + materiaSeleccionada.getNombre() + ":\n");
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + estudiantes.get(i).getNombreCompleto() + 
                             " (" + estudiantes.get(i).getIdEstudiante() + ")");
        }
        
        System.out.print("\nSelecciona un estudiante: ");
        int opcionEstudiante;
        try {
            opcionEstudiante = Integer.parseInt(scanner.nextLine()) - 1;
            if (opcionEstudiante < 0 || opcionEstudiante >= estudiantes.size()) {
                System.out.println(" Opción inválida");
                pausa();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida");
            pausa();
            return;
        }
        
        Estudiante estudianteSeleccionado = estudiantes.get(opcionEstudiante);
        
        // Ingresar nota
        System.out.print("\nValor de la nota (0-10): ");
        double valor;
        try {
            valor = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida");
            pausa();
            return;
        }
        
        System.out.print("Descripción (ej: Examen, Taller, etc.): ");
        String descripcion = scanner.nextLine();
        
        // Agregar nota
        if (gestorNotas.agregarNotaEstudiante(estudianteSeleccionado, materiaSeleccionada.getNombre(), 
                                              valor, descripcion, profesor)) {
            System.out.println("✓ Nota agregada exitosamente para " + estudianteSeleccionado.getNombreCompleto());
        }
        
        pausa();
    }
    
    /**
     * Ver materias del profesor
     */
    private void verMateriasProfesor() {
        limpiarPantalla();
        System.out.println("═══════════════════════════════════════");
        System.out.println("         MIS MATERIAS");
        System.out.println("═══════════════════════════════════════\n");
        
        Profesor profesor = (Profesor) autenticacion.getUsuarioActual();
        List<Materia> materias = gestorNotas.obtenerMateriasProfesor(profesor);
        
        if (materias.isEmpty()) {
            System.out.println("No tienes materias asignadas.");
        } else {
            System.out.println(" Tus materias:\n");
            for (Materia materia : materias) {
                System.out.println("  • " + materia.obtenerDetalles());
            }
        }
        
        pausa();
    }
    
    /**
     * Ver estudiantes de una materia
     */
    private void verEstudiantesMateria() {
        limpiarPantalla();
        System.out.println("═══════════════════════════════════════");
        System.out.println("      ESTUDIANTES POR MATERIA");
        System.out.println("═══════════════════════════════════════\n");
        
        Profesor profesor = (Profesor) autenticacion.getUsuarioActual();
        List<Materia> materias = gestorNotas.obtenerMateriasProfesor(profesor);
        
        if (materias.isEmpty()) {
            System.out.println(" No tienes materias asignadas");
            pausa();
            return;
        }
        
        System.out.println("Mis materias:\n");
        for (int i = 0; i < materias.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + materias.get(i).getNombre());
        }
        
        System.out.print("\nSelecciona una materia: ");
        int opcion;
        try {
            opcion = Integer.parseInt(scanner.nextLine()) - 1;
            if (opcion < 0 || opcion >= materias.size()) {
                System.out.println(" Opción inválida");
                pausa();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println(" Entrada inválida");
            pausa();
            return;
        }
        
        Materia materiaSel = materias.get(opcion);
        List<Estudiante> estudiantes = materiaSel.getEstudiantes();
        
        System.out.println("\n Estudiantes en " + materiaSel.getNombre() + ":\n");
        
        if (estudiantes.isEmpty()) {
            System.out.println("  No hay estudiantes en esta materia");
        } else {
            for (Estudiante est : estudiantes) {
                System.out.println("  • " + est.getNombreCompleto() + " (" + est.getIdEstudiante() + ")");
            }
        }
        
        pausa();
    }
    
    /**
     * Ver permisos del estudiante
     */
    private void verPermisosEstudiante() {
        limpiarPantalla();
        System.out.println("═══════════════════════════════════════");
        System.out.println("         MIS PERMISOS");
        System.out.println("═══════════════════════════════════════\n");
        
        Estudiante estudiante = (Estudiante) autenticacion.getUsuarioActual();
        estudiante.mostrarPermisos();
        
        pausa();
    }
    
    /**
     * Ver permisos del profesor
     */
    private void verPermisosProfesor() {
        limpiarPantalla();
        System.out.println("═══════════════════════════════════════");
        System.out.println("         MIS PERMISOS");
        System.out.println("═══════════════════════════════════════\n");
        
        Profesor profesor = (Profesor) autenticacion.getUsuarioActual();
        profesor.mostrarPermisos();
        
        pausa();
    }
    
    /**
     * Limpia la pantalla de la consola
     */
    private void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * Pausa la ejecución
     */
    private void pausa() {
        System.out.print("\nPresiona Enter para continuar...");
        scanner.nextLine();
    }
}
