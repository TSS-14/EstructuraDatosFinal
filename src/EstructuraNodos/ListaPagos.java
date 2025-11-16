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


    public void mostrarPorcentajePorMedio() {
        if (esVacio()){
            System.out.println("No hay pagos registrados");
            return;
        }

        int total = 0;
        int efectivo = 0, tarjetaCredito = 0, transferencia = 0;

        NodoPago actual = inicio;
        while (actual != null){
            String metodo = actual.metodoPago.trim().toLowerCase();
            if (metodo.contains("efectivo")){
                efectivo++;
            } else if (metodo.contains("tarjeta")) {
                tarjetaCredito++;
            } else if (metodo.contains("transferencia")) {
                transferencia++;
            }
            total++;
            actual = actual.siguiente;
        }
        System.out.println("\n=== PORCENTAJE POR MÉTODO DE PAGO ===");
        System.out.printf("Efectivo: %.2f%% (%d pagos)\n", (efectivo * 100.0) / total, efectivo);
        System.out.printf("Tarjeta de crédito: %.2f%% (%d pagos)\n", (tarjetaCredito * 100.0) / total, tarjetaCredito);
        System.out.printf("Transferencia: %.2f%% (%d pagos)\n", (transferencia * 100.0) / total, transferencia);
        System.out.println("Total de pagos: " + total);
    }

    public NodoPago getInicio() {
        return  this.inicio;
    }

}