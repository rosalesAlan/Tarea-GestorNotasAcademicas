package modelos;

import interfaces.Visualizable;

//Clase usuario de quien heredarán otras clases
public abstract class Usuario implements Visualizable {
    
    // Encapsulamiento: atributos privados
    private String nombre;
    private String apellido;
    private String contraseña;
    private String tipoUsuario;
    
    /**
     * Constructor protegido para evitar instanciación directa
     */
    protected Usuario(String nombre, String apellido, String contraseña, String tipoUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
    }
    
    //metodo abstracto
    public abstract void mostrarPermisos();
    
    //  Encapsulamiento
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
    
    //verifiaccion de contraseña
    public boolean verificarContraseña(String contraseñaIngresada) {
        return this.contraseña.equals(contraseñaIngresada);
    }
    
    @Override
    public String obtenerDetalles() {
        return "Usuario: " + getNombreCompleto() + " (" + tipoUsuario + ")";
    }
    
    @Override
    public void mostrar() {
        System.out.println(obtenerDetalles());
    }
}
