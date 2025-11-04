package EstructuraNodos;

public class NodoReserva {
    public static int contadorCodigo = 1;
    int codigo;
    NodoCliente cliente;
    NodoPaquete paquete;
    NodoServicioAdicional servicioAdicional;
    int cantidadPersonas;
    String estado;
    double montoTotal;
    NodoReserva siguiente;

    public NodoReserva(NodoCliente cliente, NodoPaquete paquete, NodoServicioAdicional servicioAdicional,
                       int cantidadPersonas, String estado, double montoTotal) {
        this.codigo = contadorCodigo++;
        this.cliente = cliente;
        this.paquete = paquete;
        this.servicioAdicional = servicioAdicional;
        this.cantidadPersonas = cantidadPersonas;
        this.estado = estado;
        this.montoTotal = montoTotal;
    }
}
