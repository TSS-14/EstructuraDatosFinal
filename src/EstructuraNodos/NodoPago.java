package EstructuraNodos;

public class NodoPago {
    int codigoPago;
    int codigoReserva;
    double monto;
    String metodoPago;
    String estado;
    String fechaPago;
    NodoPago siguiente;
    
    public static int contadorCodigo = 1;

    public NodoPago(int codigoReserva, double monto, String metodoPago, String fechaPago) {
        this.codigoPago = contadorCodigo++;
        this.codigoReserva = codigoReserva;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.estado = "Pendiente";
        this.siguiente = null;
    }

    public String getMedioPago() {
        return this.metodoPago;
    }

    public double getMontoTotal() {
        return this.monto;
    }
}