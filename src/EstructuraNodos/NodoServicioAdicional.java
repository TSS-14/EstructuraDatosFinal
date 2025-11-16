package EstructuraNodos;

public class NodoServicioAdicional {
    public static int contadorCodigo = 1;
    int codigoServico;
    String nombreServicio;
    double valorServicio;
    NodoServicioAdicional siguiente;

    public NodoServicioAdicional(String nombreServicio, double valorServicio) {
        this.codigoServico = contadorCodigo++;
        this.nombreServicio = nombreServicio;
        this.valorServicio = valorServicio;
    }

    public double getPrecio() {
        return this.valorServicio;
    }

    public String getNombre() {
        return this.nombreServicio;
    }


}
