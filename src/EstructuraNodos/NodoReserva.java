package EstructuraNodos;

public class NodoReserva {
    
  
    public static int contadorCodigo = 1;
    public double montoDescuento;
    public double costoServiciosAdicionales;    
    int codigo;
    NodoCliente cliente;
    NodoPaquete paquete;
    NodoServicioAdicional servicioAdicional;
    int cantidadPersonas;
    String estado;
    double montoTotal;  
    String documentoCliente;
    
    NodoReserva siguiente;

    public Object codigoPaquete;
    public String destino; 

    
    public NodoReserva(NodoCliente cliente, NodoPaquete paquete, NodoServicioAdicional servicioAdicional,
                       int cantidadPersonas, String estado, double montoTotal) {
        
        this.codigo = contadorCodigo++;
        
        this.cliente = cliente;
        this.paquete = paquete;
        this.servicioAdicional = servicioAdicional;
        
        this.cantidadPersonas = cantidadPersonas;
        this.estado = estado;
        this.montoTotal = montoTotal;
        
        if (cliente != null) {
            this.documentoCliente = cliente.documento; 
        } else {
            this.documentoCliente = null;
        }
        
        this.montoDescuento = 0.0;
        this.costoServiciosAdicionales = 0.0;
        
        this.siguiente = null;
    }
}