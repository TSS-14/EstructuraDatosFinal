package EstructuraNodos;

public class ListaReservas {
    private NodoReserva inicio;

    public ListaReservas(){
        inicio = null;
    }

    //Metodo si esta vacio
    public boolean esVacio(){
        return inicio == null;
    }

    //Metodo para crear reserva
    public void crearReserva(NodoCliente cliente, NodoPaquete paquete, NodoServicioAdicional servicioAdicional,
                             int cantidadPersona, String estado, double montoTotal){
        NodoReserva nuevo = new NodoReserva(cliente, paquete, servicioAdicional,cantidadPersona,estado,montoTotal);
        if (esVacio()){
            inicio = nuevo;
            nuevo.siguiente = null;
        }else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        System.out.println("Reserva creada exitosamente");
    }

    //Metodo para confirmar reserva
    public void confirmarReserva(int codigoReserva){
        NodoReserva aux = inicio;

        while (aux != null){
            if (aux.codigo == codigoReserva){
                NodoPaquete paquete = aux.paquete;

                if (paquete.plazaDiponible >= aux.cantidadPersonas){
                    paquete.plazaDiponible -= aux.cantidadPersonas;
                    aux.estado = "Confirmado";
                    System.out.println("Reserva confirmada");
                }else {
                    aux.estado = "Pendiente";
                    System.out.println("No hay plazas diponibles. La reserva queda pendiente.");
                }
                return;
            }
            aux = aux.siguiente;
        }
        System.out.println("No se encuentra ninguna reserva con ese codigo");
    }

    //Metodo para cancelar reserva
    public void cancelarReserva(int codigoReserva) {
        NodoReserva aux = inicio;
        while (aux != null) {
            if (aux.codigo == codigoReserva) {
                aux.estado = "Cancelado";
                System.out.println("Reserva cancelada");
                return;
            }
            aux = aux.siguiente;
        }
        System.out.println("No se encuentra ninguna reserva con ese código");
    }

    //Metodo para mostrar  todas las reservas
    public void mostrarReservas(){
        if (esVacio()){
            System.out.println("No hay reservas registradas");
            return;
        }

        NodoReserva aux = inicio;
        while (aux != null){
            System.out.println("== LISTA DE RESERVAS ==");

            //Datos del cliente
            System.out.println("\nCliente: "+aux.cliente.nombre+
                    "\nDocumento: "+aux.cliente.documento+
                    "\nCorreo: "+aux.cliente.correo+
                    "\nTelefono: "+aux.cliente.telefono);

            //Datos del paquete turistico
            System.out.println("\n----------");
            System.out.println("\nDestino: "+aux.paquete.destino+
                    "\nDuracion: "+aux.paquete.duracionDias+
                    "\nPrecio paquete: $"+aux.paquete.precioPaquete);

            //Datos propios de la reserva
            System.out.println("\n----------");
            String nombreServicio = aux.servicioAdicional != null ? aux.servicioAdicional.nombreServicio : "Sin servicio adicional";
            System.out.println("\nCantidad de persona: "+aux.cantidadPersonas+
                    "\nServicios adicionales: "+nombreServicio+
                    "\nEstado: "+aux.estado+
                    "\nMonto total $"+ aux.montoTotal);

            aux = aux.siguiente;
        }
    }
}
