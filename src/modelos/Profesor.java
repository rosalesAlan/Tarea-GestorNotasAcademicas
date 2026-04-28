package modelos;

import java.util.ArrayList;
import java.util.List;

// clase profesor 
public class Profesor extends Usuario {
    
    // Encapsulamiento: atributos privados
    private String idProfesor;
    private List<String> materiasAsignadas;
    
    //constructor 
    public Profesor(String nombre, String apellido, String contraseña, String idProfesor) {
        super(nombre, apellido, contraseña, "PROFESOR");
        this.idProfesor = idProfesor;
        this.materiasAsignadas = new ArrayList<>();
    }
    
   
    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos del Profesor " + getNombreCompleto() + ":");
        System.out.println("  ✗ Ver mis notas");
        System.out.println("  ✓ Agregar notas a estudiantes");
        System.out.println("  ✓ Gestionar mis materias");
    }
    
    //  Encapsulamiento
    public String getIdProfesor() {
        return idProfesor;
    }
    
    public List<String> getMateriasAsignadas() {
        return new ArrayList<>(materiasAsignadas);
    }
    
    //metodo que me permite asignar una materia a un profesor
    public void asignarMateria(String materia) {
        if (!materiasAsignadas.contains(materia)) {
            materiasAsignadas.add(materia);
        }
    }
    
    //metrood que me pemrite saber que materia tiene un profesor
    public boolean tieneMateria(String materia) {
        return materiasAsignadas.contains(materia);
    }
    
    // metodo que permite a un profesor crear una nota para un estudiante 
    public Nota crearNota(Estudiante estudiante, String materia, double valor, String descripcion) {
        if (tieneMateria(materia)) {
            return new Nota(estudiante, materia, valor, descripcion, this.getNombreCompleto());
        }
        return null;
    }
    
    @Override
    public String obtenerDetalles() {
        return super.obtenerDetalles() + " [ID: " + idProfesor + "]";
    }
}
