package modelos;

import interfaces.Gestionable;
import interfaces.Visualizable;
import java.util.ArrayList;
import java.util.List;

// Clase Materia que implementa interfaces

public class Materia implements Gestionable, Visualizable {
    
    // Encapsulamiento: atributos privados
    private String nombre;
    private String codigo;
    private Profesor profesor;
    private List<Estudiante> estudiantes;
    
    //constructor
    public Materia(String nombre, String codigo, Profesor profesor) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.profesor = profesor;
        this.estudiantes = new ArrayList<>();
    }
    
    // Encapsulamiento
    public String getNombre() {
        return nombre;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public Profesor getProfesor() {
        return profesor;
    }
    
    public List<Estudiante> getEstudiantes() {
        return new ArrayList<>(estudiantes);
    }
    
    //Metodo para agregar un estudiante a la materia

    public void agregarEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }
    
    //metodo para eliminar un estudiante de la materia

    public void eliminarEstudiante(Estudiante estudiante) {
        estudiantes.remove(estudiante);
    }
    

    public Estudiante obtenerEstudiantePorId(String idEstudiante) {
        for (Estudiante est : estudiantes) {
            if (est.getIdEstudiante().equals(idEstudiante)) {
                return est;
            }
        }
        return null;
    }
    
    // interfaces
    @Override
    public void guardar() {
        System.out.println("Materia " + nombre + " guardada.");
    }
    
    @Override
    public void eliminar() {
        System.out.println("Materia " + nombre + " eliminada.");
    }
    
    @Override
    public void actualizar() {
        System.out.println("Materia " + nombre + " actualizada.");
    }
    
    @Override
    public void mostrar() {
        System.out.println(obtenerDetalles());
    }
    
    @Override
    public String obtenerDetalles() {
        return "Materia: " + nombre + " (" + codigo + ") - Profesor: " + 
               profesor.getNombreCompleto() + " | Estudiantes: " + estudiantes.size();
    }
}
