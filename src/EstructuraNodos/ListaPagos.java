package EstructuraNodos;

public class ListaPagos {
    private NodoPago inicio;

    public ListaPagos() {
        inicio = null;
    }

    public boolean esVacio() {
        return inicio == null;
    }

    public void registrarPago(int codigoReserva, double monto, String metodoPago, String fechaPago) {
        NodoPago nuevo = new NodoPago(codigoReserva, monto, metodoPago, fechaPago);
        if (esVacio()) {
            inicio = nuevo;
        } else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        System.out.println("Pago registrado exitosamente con código: " + nuevo.codigoPago);
    }

    public void mostrarPagos() {
        if (esVacio()) {
            System.out.println("No hay pagos registrados");
            return;
        }

        NodoPago actual = inicio;
        System.out.println("\n=== LISTA DE PAGOS ===");
        while (actual != null) {
            System.out.println("\nCódigo de pago: " + actual.codigoPago);
            System.out.println("Código de reserva: " + actual.codigoReserva);
            System.out.println("Monto: $" + actual.monto);
            System.out.println("Método de pago: " + actual.metodoPago);
            System.out.println("Estado: " + actual.estado);
            System.out.println("Fecha: " + actual.fechaPago);
            System.out.println("--------------------");
            actual = actual.siguiente;
        }
    }

    public NodoPago buscarPago(int codigoPago) {
        NodoPago actual = inicio;
        while (actual != null) {
            if (actual.codigoPago == codigoPago) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public void confirmarPago(int codigoPago) {
        NodoPago pago = buscarPago(codigoPago);
        if (pago != null) {
            pago.estado = "Confirmado";
            System.out.println("Pago confirmado exitosamente");
        } else {
            System.out.println("Pago no encontrado");
        }
    }

    public void cancelarPago(int codigoPago) {
        NodoPago pago = buscarPago(codigoPago);
        if (pago != null) {
            pago.estado = "Cancelado";
            System.out.println("Pago cancelado exitosamente");
        } else {
            System.out.println("Pago no encontrado");
        }
    }

    public void mostrarPagosPorReserva(int codigoReserva) {
        boolean encontrado = false;
        NodoPago actual = inicio;
        
        System.out.println("\n=== PAGOS DE LA RESERVA " + codigoReserva + " ===");
        while (actual != null) {
            if (actual.codigoReserva == codigoReserva) {
                System.out.println("\nCódigo de pago: " + actual.codigoPago);
                System.out.println("Monto: $" + actual.monto);
                System.out.println("Método de pago: " + actual.metodoPago);
                System.out.println("Estado: " + actual.estado);
                System.out.println("Fecha: " + actual.fechaPago);
                System.out.println("--------------------");
                encontrado = true;
            }
            actual = actual.siguiente;
        }
        
        if (!encontrado) {
            System.out.println("No se encontraron pagos para la reserva " + codigoReserva);
        }
    }
}