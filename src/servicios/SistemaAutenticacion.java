package servicios;

import modelos.Usuario;
import modelos.Estudiante;
import modelos.Profesor;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de autenticación del sistema
 * Maneja login y registro de usuarios
 */
public class SistemaAutenticacion {
    
    private List<Usuario> usuarios;
    private Usuario usuarioActual;
    
    /**
     * Constructor que inicializa el sistema y crea usuarios de ejemplo
     */
    public SistemaAutenticacion() {
        this.usuarios = new ArrayList<>();
        this.usuarioActual = null;
        
        // Usuarios de prueba
        crearEstudianteDefault();
        crearProfesorDefault();
    }
    
    /**
     * Crea estudiantes de ejemplo
     */
    private void crearEstudianteDefault() {
        Estudiante est1 = new Estudiante("Juan", "Perez", "1234", "E001");
        Estudiante est2 = new Estudiante("Maria", "Gonzalez", "5678", "E002");
        Estudiante est3 = new Estudiante("Carlos", "Lopez", "9012", "E003");
        
        usuarios.add(est1);
        usuarios.add(est2);
        usuarios.add(est3);
    }
    
    /**
     * Crea profesores de ejemplo
     */
    private void crearProfesorDefault() {
        Profesor prof1 = new Profesor("Ana", "Martinez", "profesor1", "P001");
        Profesor prof2 = new Profesor("Roberto", "Silva", "profesor2", "P002");
        
        usuarios.add(prof1);
        usuarios.add(prof2);
    }
    
    /**
     * Intenta autenticar un usuario
     */
    public boolean autenticar(String nombreCompleto, String contraseña) {
        String[] partes = nombreCompleto.trim().split(" ");
        System.out.println("🔍 Intentando autenticar: " + nombreCompleto);
        if (partes.length < 2) {
            return false;
        }
        
        String nombre = partes[0];
        String apellido = partes[1];
        
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre) && 
                usuario.getApellido().equalsIgnoreCase(apellido)) {
                
                System.out.println("🔍 Verificando contraseña para " + usuario.getNombreCompleto());
                
                if (usuario.verificarContraseña(contraseña)) {
                    this.usuarioActual = usuario;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Cierra la sesión actual
     */
    public void cerrarSesion() {
        this.usuarioActual = null;
    }
    
    /**
     * Obtiene el usuario actual autenticado
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    /**
     * Verifica si hay un usuario autenticado
     */
    public boolean hayUsuarioAutenticado() {
        return usuarioActual != null;
    }
    
    /**
     * Obtiene todos los estudiantes
     */
    public List<Estudiante> obtenerTodosEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Estudiante) {
                estudiantes.add((Estudiante) usuario);
            }
        }
        return estudiantes;
    }
    
    /**
     * Obtiene todos los profesores
     */
    public List<Profesor> obtenerTodosProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Profesor) {
                profesores.add((Profesor) usuario);
            }
        }
        return profesores;
    }
    
    /**
     * Obtiene un estudiante por ID
     */
    public Estudiante obtenerEstudiantePorId(String id) {
        for (Estudiante est : obtenerTodosEstudiantes()) {
            if (est.getIdEstudiante().equals(id)) {
                return est;
            }
        }
        return null;
    }
}
