package modelos;

import interfaces.Visualizable;
import java.time.LocalDate;

//clase o entidad nota
public class Nota implements Visualizable {
    
    // Encapsulamiento: atributos privados
    private Estudiante estudiante;
    private String materia;
    private double valor;
    private String descripcion;
    private String profesor;
    private LocalDate fecha;
    
    //constructor de la nota

    public Nota(Estudiante estudiante, String materia, double valor, String descripcion, String profesor) {
        this.estudiante = estudiante;
        this.materia = materia;
        this.valor = valor;
        this.descripcion = descripcion;
        this.profesor = profesor;
        this.fecha = LocalDate.now();
    }
    
    // Encapsulamiento
    public Estudiante getEstudiante() {
        return estudiante;
    }
    
    public String getMateria() {
        return materia;
    }
    
    public double getValor() {
        return valor;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public String getProfesor() {
        return profesor;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    //Metodo que detemrina cuando una nota se aprueba, condicion tiene que ser mayor a 3

    public boolean esAprobatoria() {
        return valor > 3.0;
    }
    
    //convierte la nota en una letra segun el valor americano
    public String getCalificacionLetra() {
        if (valor >= 4.5) return "A";
        if (valor >= 4.0) return "B+";
        if (valor >= 3.5) return "B";
        if (valor >= 3.0) return "C";
        return "F";
    }
    
    // interfaz
    @Override
    public void mostrar() {
        System.out.println(obtenerDetalles());
    }
    
    @Override
    public String obtenerDetalles() {
        return String.format("Materia: %s | Nota: %.2f (%s) | %s | Profesor: %s | Fecha: %s",
                materia, valor, getCalificacionLetra(), descripcion, profesor, fecha);
    }
}
