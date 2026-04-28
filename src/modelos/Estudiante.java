package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Estudiante que hereda de Usuario
 */
public class Estudiante extends Usuario {
    
    // Encapsulamiento: atributos privados
    private String idEstudiante;
    private List<Nota> notas;
    
    // Constructor del estudiante
    public Estudiante(String nombre, String apellido, String contraseña, String idEstudiante) {
        super(nombre, apellido, contraseña, "ESTUDIANTE");
        this.idEstudiante = idEstudiante;
        this.notas = new ArrayList<>();
    }
    

    @Override
    public void mostrarPermisos() {
        System.out.println("Permisos del Estudiante " + getNombreCompleto() + ":");
        System.out.println("  ✓ Ver mis notas");
        System.out.println("  ✗ Agregar notas");
        System.out.println("  ✗ Gestionar materias");
    }
    
    // Encapsulamiento
    public String getIdEstudiante() {
        return idEstudiante;
    }
    
    public List<Nota> getNotas() {
        return new ArrayList<>(notas);
    }
    
    // Metodo para agregar la nota al estudiante

    public void agregarNota(Nota nota) {
        notas.add(nota);
    }
    
    
     // Metodo para obtiene las notas de una materia específica
     
    public List<Nota> getNotasPorMateria(String materia) {
        List<Nota> notasMateria = new ArrayList<>();
        for (Nota nota : notas) {
            if (nota.getMateria().equals(materia)) {
                notasMateria.add(nota);
            }
        }
        return notasMateria;
    }
    
    //promeido de las notas
    public double calcularPromedio() {
        if (notas.isEmpty()) return 0;
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getValor();
        }
        return suma / notas.size();
    }
    
    @Override
    public String obtenerDetalles() {
        return super.obtenerDetalles() + " [ID: " + idEstudiante + "]";
    }
}
