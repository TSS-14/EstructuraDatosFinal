package EstructuraNodos;
import java.time.LocalDate;

public class NodoReserva {
    public static int contadorCodigo = 1;
    public double montoDescuento;
    public double montoRecargo;
    public double costoServiciosAdicionales;
    public String medioPago;
    int codigo;
    NodoCliente cliente;
    NodoPaquete paquete;
    NodoServicioAdicional[] servicioAdicional;
    int cantidadPersonas;
    String estado;
    double montoTotal;
    public LocalDate fechaReserva;
    NodoReserva siguiente;

    public NodoReserva(NodoCliente cliente, NodoPaquete paquete, NodoServicioAdicional[] servicioAdicional,
                       int cantidadPersonas, String estado, double montoTotal, double montoDescuento,
                       double montoRecargo, String codigoPromocio, LocalDate fechaReserva, String medioPago) {
        this.codigo = contadorCodigo++;
        this.cliente = cliente;
        this.paquete = paquete;
        this.servicioAdicional = servicioAdicional;
        this.cantidadPersonas = cantidadPersonas;
        this.estado = estado;
        this.montoTotal = montoTotal;
        this.montoDescuento = montoDescuento;
        this.montoRecargo = montoRecargo;
        this.fechaReserva = fechaReserva;
        this.costoServiciosAdicionales = 0;
        this.siguiente = null;
        this.medioPago = medioPago;
    }

    public NodoCliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public NodoPaquete getPaquete() {
        return this.paquete;
    }

    public NodoServicioAdicional[] getServiciosAdicionales() {
        return this.servicioAdicional;
    }

    public int getCantidadPersona() {
        return this.cantidadPersonas;
    }

    public int getContadorServicios() {
        return (servicioAdicional != null) ? servicioAdicional.length : 0;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
