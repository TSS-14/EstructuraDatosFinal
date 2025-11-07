package EstructuraNodos;

import EstructuraNodos.ListaCliente;
import EstructuraNodos.ListaPaquete;

public class ListaReservas {
    private NodoReserva inicio;



    public ListaReservas() {
        inicio = null;
    }

    // Método para acceder al inicio de la lista (necesario para reportes externos)
    public NodoReserva getInicio() {
        return inicio;
    }

    // Método si está vacía
    public boolean esVacio() {
        return inicio == null;
    }

   

    // Metodo para crear reserva
    public void crearReserva(NodoCliente cliente, NodoPaquete paquete, NodoServicioAdicional servicioAdicional,
            int cantidadPersona, String estado, double montoTotal) {
        NodoReserva nuevo = new NodoReserva(cliente, paquete, servicioAdicional, cantidadPersona, estado, montoTotal);
        if (esVacio()) {
            inicio = nuevo;
        } else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        System.out.println("Reserva creada exitosamente con código: " + nuevo.codigo);
    }

    // Metodo para confirmar reserva
    public void confirmarReserva(int codigoReserva) {
        NodoReserva aux = inicio;

        while (aux != null) {
            if (aux.codigo == codigoReserva) {
                NodoPaquete paquete = aux.paquete;

                // CORRECCIÓN: Se asume que el campo es 'plazasDisponible'
                if (paquete.plazaDiponible >= aux.cantidadPersonas) {
                    paquete.plazaDiponible -= aux.cantidadPersonas;
                    aux.estado = "Confirmado";
                    System.out.println("Reserva confirmada");
                } else {
                    aux.estado = "Pendiente";
                    System.out.println("No hay plazas disponibles. La reserva queda pendiente.");
                }
                return;
            }
            aux = aux.siguiente;
        }
        System.out.println("No se encuentra ninguna reserva con ese código");
    }

    // Metodo para cancelar reserva
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

    // Metodo para mostrar todas las reservas
    public void mostrarReservas() {
        if (esVacio()) {
            System.out.println("No hay reservas registradas");
            return;
        }

        NodoReserva aux = inicio;
        while (aux != null) {
            System.out.println("\n== RESERVA CÓDIGO: " + aux.codigo + " ==");

            // Datos del cliente
            System.out.println("\nCliente: " + aux.cliente.nombre +
                    "\nDocumento: " + aux.cliente.documento +
                    "\nCorreo: " + aux.cliente.correo +
                    "\nTelefono: " + aux.cliente.telefono);

            // Datos del paquete turistico
            System.out.println("\n----------");
            System.out.println("Destino: " + aux.paquete.destino +
                    "\nDuracion: " + aux.paquete.duracionDias + " días" +
                    "\nPrecio paquete: $" + aux.paquete.precioPaquete);

            // Datos propios de la reserva
            System.out.println("\n----------");
            String nombreServicio = aux.servicioAdicional != null ? aux.servicioAdicional.nombreServicio : "Sin servicio adicional";
            System.out.println("Cantidad de personas: " + aux.cantidadPersonas +
                    "\nServicios adicionales: " + nombreServicio +
                    "\nEstado: " + aux.estado +
                    "\nMonto total: $" + aux.montoTotal);

            aux = aux.siguiente;
        }
    }

   

    // Metodo reporte: Estado de reservas
    public void reporteEstadoReservas() {
        if (esVacio()) {
            System.out.println("No hay reservas registradas para generar el reporte de estado.");
            return;
        }

        int totalReservas = 0;
        int confirmadas = 0;
        int pendientes = 0;
        int canceladas = 0;

        NodoReserva actual = inicio;
        while (actual != null) {
            totalReservas++;
            String estado = actual.estado.toLowerCase();

            if (estado.contains("confirmado")) {
                confirmadas++;
            } else if (estado.contains("pendiente")) {
                pendientes++;
            } else if (estado.contains("cancelado")) {
                canceladas++;
            }
            actual = actual.siguiente;
        }

        double pctConfirmadas = (totalReservas > 0) ? (double) confirmadas / totalReservas * 100 : 0;
        double pctPendientes = (totalReservas > 0) ? (double) pendientes / totalReservas * 100 : 0;
        double pctCanceladas = (totalReservas > 0) ? (double) canceladas / totalReservas * 100 : 0;

        System.out.println("\n=========================================");
        System.out.println("📊 REPORTE DE ESTADO Y CANTIDADES DE RESERVAS");
        System.out.println("=========================================");
        System.out.printf("CANTIDAD TOTAL DE RESERVAS REALIZADAS: %d%n", totalReservas);
        System.out.printf("Reservas CONFIRMADAS: %d (%.2f%%)%n", confirmadas, pctConfirmadas);
        System.out.printf("Reservas PENDIENTES: %d (%.2f%%)%n", pendientes, pctPendientes);
        System.out.printf("Reservas CANCELADAS: %d (%.2f%%)%n", canceladas, pctCanceladas);
        System.out.println("-----------------------------------------");
    }

    // Metodo reporte: Calcular monto total facturado
    public void calcularMontoTotalFacturado() {
        if (esVacio()) {
            System.out.println("No hay reservas para calcular la facturación.");
            return;
        }

        double montoTotalGeneral = 0.0;
        NodoReserva actual = inicio;
        while (actual != null) {
            // Solo se factura lo Confirmado
            if (actual.estado.equalsIgnoreCase("Confirmado")) {
                montoTotalGeneral += actual.montoTotal; // Se usa el campo existente montoTotal
            }
            actual = actual.siguiente;
        }

        System.out.println("\n=========================================");
        System.out.println("💰 REPORTE DE MONTO TOTAL FACTURADO");
        System.out.println("=========================================");
        System.out.printf("MONTO TOTAL GENERAL FACTURADO (CONFIRMADO): $%,.2f%n", montoTotalGeneral);
        System.out.println("-----------------------------------------");
    }

   
    // Metodo reporte: Monto por paquete
    public void reporteMontoPorPaquete(ListaPaquete listaPaquetes) {
        if (esVacio()) {
            System.out.println("No hay reservas para calcular el recaudo por paquete.");
            return;
        }

        double totalGeneral = 0.0;
        NodoReserva actual = inicio;

        while (actual != null) {
            if (actual.estado.equalsIgnoreCase("Confirmada")) {
                totalGeneral += actual.montoTotal;
            }
            actual = actual.siguiente;
        }

        System.out.println("\n=========================================");
        System.out.println("💵 REPORTE: MONTO POR PAQUETE Y DESTINO");
        System.out.println("=========================================");
        System.out.printf("MONTO TOTAL FACTURADO (CONFIRMADO): $%,.2f%n", totalGeneral);
        System.out.println("-----------------------------------------");

        actual = inicio;
        while (actual != null) {
            if (actual.estado.equalsIgnoreCase("Confirmada")) {
                String codigoPaquete = actual.paquete.codigoPaquete; 
                String destino = listaPaquetes.obtenerDestino(codigoPaquete); 
                
                double montoActual = actual.montoTotal; 
                double porcentaje = (totalGeneral > 0) ? (montoActual / totalGeneral) * 100 : 0.0;

                System.out.printf("  Reserva %d (Paquete %s - %s):%n",
                        actual.codigo, codigoPaquete, destino);
                System.out.printf("    Monto: $%,.2f | Contribuye: %.2f%%%n",
                        montoActual, porcentaje);
            }
            actual = actual.siguiente;
        }
        System.out.println("-----------------------------------------");
    }

    // Metodo reporte: Monto por servicio (Requiere campo 'costoServiciosAdicionales' en NodoReserva)
    public void reporteMontoPorServicio() {
        if (esVacio()) {
            System.out.println("No hay reservas con servicios adicionales.");
            return;
        }

        double totalServiciosGeneral = 0.0;
        NodoReserva actual = inicio;
        
        while (actual != null) {
            if (actual.estado.equalsIgnoreCase("Confirmada")) {
                totalServiciosGeneral += actual.costoServiciosAdicionales;
            }
            actual = actual.siguiente;
        }

        System.out.println("\n=========================================");
        System.out.println("💰 REPORTE: MONTO POR SERVICIO ADICIONAL");
        System.out.println("=========================================");
        System.out.printf("MONTO TOTAL RECAUDADO POR SERVICIOS ADICIONALES (CONFIRMADO): $%,.2f%n", totalServiciosGeneral);
        System.out.println("-----------------------------------------");
    }

    // Metodo reporte: Monto total descuentos (Requiere campo 'montoDescuento' en NodoReserva)
    public void calcularMontoTotalDescuentos() {
        if (esVacio()) {
            System.out.println("No hay reservas registradas.");
            return;
        }

        double totalDescuentos = 0.0;
        NodoReserva actual = inicio;
        while (actual != null) {
            totalDescuentos += actual.montoDescuento;
            actual = actual.siguiente;
        }

        System.out.println("\n=========================================");
        System.out.println("🎁 REPORTE DE DESCUENTOS APLICADOS");
        System.out.println("=========================================");
        System.out.printf("MONTO TOTAL DE DESCUENTOS APLICADOS: $%,.2f%n", totalDescuentos);
        System.out.println("-----------------------------------------");
    }

    // Metodo reporte: Porcentaje de servicios vendidos
    public void reportePorcentajeServiciosVendidos() {
        if (esVacio()) {
            System.out.println("No hay reservas para calcular el porcentaje.");
            return;
        }

        int totalReservasConfirmadas = 0;
        int reservasConServicios = 0;

        NodoReserva actual = inicio;
        while (actual != null) {
            if (actual.estado.equalsIgnoreCase("Confirmada")) {
                totalReservasConfirmadas++;

                if (actual.servicioAdicional != null) {
                    reservasConServicios++;
                }
            }
            actual = actual.siguiente;
        }

        double porcentaje = (totalReservasConfirmadas > 0)
                ? (double) reservasConServicios / totalReservasConfirmadas * 100
                : 0.0;

        System.out.println("\n=========================================");
        System.out.println("📈 REPORTE: PORCENTAJE DE SERVICIOS VENDIDOS");
        System.out.println("=========================================");
        System.out.printf("Total de Reservas Confirmadas: %d%n", totalReservasConfirmadas);
        System.out.printf("Reservas con Servicios Adicionales: %d%n", reservasConServicios);
        System.out.println("-----------------------------------------");
        System.out.printf("PORCENTAJE DE RESERVAS CON SERVICIOS: %.2f%%%n", porcentaje);
        System.out.println("-----------------------------------------");
    }
    
    // Metodo reporte: Cliente que más y menos gastó (Movido de ListaCliente)
    public void reporteClienteMayorMenorGasto(ListaCliente listaClientes) {
        if (esVacio()) {
            System.out.println("No hay reservas para determinar el mayor/menor gasto.");
            return;
        }

        String docMayorGasto = "";
        double mayorGasto = -1.0;
        String docMenorGasto = "";
        double menorGasto = Double.MAX_VALUE;

        NodoReserva actual = inicio;
        while (actual != null) {
            if (actual.estado.equalsIgnoreCase("Confirmado")) {
                double gastoActual = actual.montoTotal;

                if (actual.cliente != null) {

                    // Mayor gasto
                    if (gastoActual > mayorGasto) {
                        mayorGasto = gastoActual;
                        docMayorGasto = actual.cliente.documento;
                    }
                    // Menor gasto
                    if (gastoActual < menorGasto) {
                        menorGasto = gastoActual;
                        docMenorGasto = actual.cliente.documento;
                    }
                }
            }
            actual = actual.siguiente;
        }

        System.out.println("\n=========================================");
        System.out.println("⭐ REPORTE: CLIENTE CON MAYOR Y MENOR GASTO");
        System.out.println("=========================================");

        if (mayorGasto != -1.0) {
            System.out.println("🥇 CLIENTE CON MAYOR GASTO:");
            listaClientes.mostrarDatosClientePorDocumento(docMayorGasto);
            System.out.printf("  Monto total: $%,.2f%n", mayorGasto);
            System.out.println("-----------------------------------------");

            System.out.println("📉 CLIENTE CON MENOR GASTO:");
            listaClientes.mostrarDatosClientePorDocumento(docMenorGasto);
            System.out.printf("  Monto total: $%,.2f%n", menorGasto);
            System.out.println("-----------------------------------------");
        } else {
            System.out.println("No hay reservas confirmadas para analizar el gasto.");
        }
    }
}