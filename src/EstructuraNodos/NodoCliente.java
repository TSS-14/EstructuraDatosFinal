package EstructuraNodos;

public class NodoCliente {
    String documento;
    String nombre;
    int edad;
    String genero;
    String correo;
    String telefono;
    String tipoCliente;
    NodoCliente siguiente;

    public NodoCliente(String documento, String tipoCliente, String telefono, String correo,
                       String genero, int edad, String nombre) {
        this.documento = documento;
        this.tipoCliente = tipoCliente;
        this.telefono = telefono;
        this.correo = correo;
        this.genero = genero;
        this.edad = edad;
        this.nombre = nombre;
    }
}
