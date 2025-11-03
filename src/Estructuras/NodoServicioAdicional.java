package Estructuras;

public class NodoServicioAdicional {
    String codigoServico;
    String nombreServicio;
    double valorServicio;
    NodoServicioAdicional siguiente;

    public NodoServicioAdicional(String codigoServicio, String nombreServicio, double valorServicio) {
        this.codigoServico = codigoServicio;
        this.nombreServicio = nombreServicio;
        this.valorServicio = valorServicio;
    }
}
