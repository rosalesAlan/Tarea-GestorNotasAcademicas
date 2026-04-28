package servicios;

import modelos.Materia;
import modelos.Estudiante;
import modelos.Profesor;
import modelos.Nota;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio de gestión de notas y materias
 */
public class GestorNotas {
    
    private List<Materia> materias;
    
    /**
     * Constructor que inicializa el gestor de notas
     */
    public GestorNotas() {
        this.materias = new ArrayList<>();
    }
    
    // Metodo para inicializar materias y asignar profesores y estudiantes
    public void inicializarMaterias(SistemaAutenticacion sistema) {
        // Obtener profesores
        List<Profesor> profesores = sistema.obtenerTodosProfesores();
        
        if (profesores.size() >= 2) {
            Profesor prof1 = profesores.get(0);
            Profesor prof2 = profesores.get(1);
            
            // Asignar materias a profesores
            prof1.asignarMateria("Matemáticas");
            prof1.asignarMateria("Física");
            prof2.asignarMateria("Programación");
            prof2.asignarMateria("Base de Datos");
            
            // Crear materias
            Materia mat1 = new Materia("Matemáticas", "MAT001", prof1);
            Materia mat2 = new Materia("Física", "FIS001", prof1);
            Materia mat3 = new Materia("Programación", "PRG001", prof2);
            Materia mat4 = new Materia("Base de Datos", "BDD001", prof2);
            
            // Agregar estudiantes a las materias
            List<Estudiante> estudiantes = sistema.obtenerTodosEstudiantes();
            for (Estudiante est : estudiantes) {
                mat1.agregarEstudiante(est);
                mat2.agregarEstudiante(est);
                mat3.agregarEstudiante(est);
                mat4.agregarEstudiante(est);
            }
            
            // Guardar materias
            materias.add(mat1);
            materias.add(mat2);
            materias.add(mat3);
            materias.add(mat4);
        }
    }
    
    //metodo para obtener todas las materias
    public List<Materia> obtenerTodasMaterias() {
        return new ArrayList<>(materias);
    }
    
    //Metodo que obtiene una materia por su nombre
    public Materia obtenerMateriaPorNombre(String nombre) {
        for (Materia materia : materias) {
            if (materia.getNombre().equalsIgnoreCase(nombre)) {
                return materia;
            }
        }
        return null;
    }
    
    //Metodo que obtiene las materias de un profesor
    public List<Materia> obtenerMateriasProfesor(Profesor profesor) {
        List<Materia> materiasProf = new ArrayList<>();
        for (Materia materia : materias) {
            if (materia.getProfesor().equals(profesor)) {
                materiasProf.add(materia);
            }
        }
        return materiasProf;
    }
    
    //pemrite agregar una nota a un estudiante, validando que el profesor tenga asignada la materia y que la nota esté entre 0 y 5
    public boolean agregarNotaEstudiante(Estudiante estudiante, String nombreMateria, 
                                         double valor, String descripcion, Profesor profesor) {
        
        // Validar que el valor esté entre 0 y 10
        if (valor < 0 || valor > 10) {
            System.out.println("❌ Error: La nota debe estar entre 0 y 10");
            return false;
        }
        
        // Validar que el profesor tenga asignada la materia
        if (!profesor.tieneMateria(nombreMateria)) {
            System.out.println("❌ Error: Este profesor no tiene asignada la materia " + nombreMateria);
            return false;
        }
        
        // Crear y agregar la nota
        Nota nota = profesor.crearNota(estudiante, nombreMateria, valor, descripcion);
        if (nota != null) {
            estudiante.agregarNota(nota);
            System.out.println("✓ Nota agregada exitosamente");
            return true;
        }
        
        return false;
    }
    
    // Obtener notas de un estudiante
    public List<Nota> obtenerNotasEstudiante(Estudiante estudiante) {
        return estudiante.getNotas();
    }
    
    //para obtener la lista de notas de un estudiante por materia
    public List<Nota> obtenerNotasEstudiantePorMateria(Estudiante estudiante, String materia) {
        return estudiante.getNotasPorMateria(materia);
    }
    
    //calculamos el promedio de un estudiante
    public double calcularPromedioEstudiante(Estudiante estudiante) {
        return estudiante.calcularPromedio();
    }
    
    //metodo que obtiene el promedio de un estudiante segun su materia

    public double obtenerPromedioPorMateria(Estudiante estudiante, String materia) {
        List<Nota> notas = obtenerNotasEstudiantePorMateria(estudiante, materia);
        if (notas.isEmpty()) return 0;
        
        double suma = 0;
        for (Nota nota : notas) {
            suma += nota.getValor();
        }
        return suma / notas.size();
    }
}
