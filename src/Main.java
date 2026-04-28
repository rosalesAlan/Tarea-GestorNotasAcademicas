import servicios.SistemaAutenticacion;
import servicios.GestorNotas;
import utilidades.Menu;


public class Main {
    
    public static void main(String[] args) {
        // Inicializar servicios
        SistemaAutenticacion autenticacion = new SistemaAutenticacion();
        GestorNotas gestorNotas = new GestorNotas();
        
        // Inicializar datos de ejemplo
        gestorNotas.inicializarMaterias(autenticacion);
        
        // Crear y mostrar menú
        Menu menu = new Menu(autenticacion, gestorNotas);
        menu.mostrarMenuPrincipal();
    }
}
