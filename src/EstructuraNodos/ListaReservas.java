package EstructuraNodos;

import java.time.LocalDate;
import java.util.Scanner;

public class ListaReservas {
    private NodoReserva inicio;


    public ListaReservas() {
        inicio = null;
    }

    // Metodo si esta vacia
    public boolean esVacio() {
        return inicio == null;
    }


    // Metodo para crear reserva
    public void crearReserva(NodoCliente cliente, NodoPaquete paquete, NodoServicioAdicional[] servicioAdicional,
                             int cantidadPersona, String estado, double montoTotal, double montoDescuento,
                             double montoRecargo, String codigoPromocio, LocalDate fechaReserva, String medioPago) {
        NodoReserva nuevo = new NodoReserva(cliente, paquete, servicioAdicional, cantidadPersona, estado, montoTotal,
                                            montoDescuento, montoRecargo, codigoPromocio, fechaReserva, medioPago);
        if (esVacio()) {
            inicio = nuevo;
        } else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        System.out.println("Reserva creada exitosamente con código: " + nuevo.codigo);
    }

    public double calcularMontoReserva(int codigoReserva) {
        NodoReserva reserva = buscarReservaPorCodigo(codigoReserva);
        if (reserva == null) return 0;
        return reserva.montoTotal;
    }

    // Metodo para confirmar reserva
    public void confirmarReserva(int codigoReserva) {
        NodoReserva aux = inicio;

        while (aux != null) {
            if (aux.codigo == codigoReserva) {
                aux.estado = "Confirmado";
                System.out.println("Reserva confirmada");
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
            System.out.println("Estado: " + aux.estado);

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

            // Servicios adicionales contratados
            String nombreServicio = "Sin servicio adicional";
            if (aux.servicioAdicional != null && aux.servicioAdicional.length > 0) {
                nombreServicio = "";
                for (int i = 0; i < aux.servicioAdicional.length; i++) {
                    if (aux.servicioAdicional[i] != null) {
                        nombreServicio += aux.servicioAdicional[i].nombreServicio;
                        if (i < aux.servicioAdicional.length - 1) {
                            nombreServicio += ", ";
                        }
                    }
                }
                if (nombreServicio.isEmpty()) {
                    nombreServicio = "Sin servicio adicional";
                }
            }
            System.out.println("\n----------");
            System.out.println("Servicios adicionales contratados: \n" + nombreServicio);

            System.out.println("\n----------");
            System.out.println("Monto total: $"+aux.montoTotal);



            aux = aux.siguiente;
        }
    }

    //Metodo para contar reservas hechas y reservas confirmadas
    public void contadorReservas(){
        int totalReservas = 0;
        int reservasConfirmadas = 0;
        NodoReserva aux = inicio;
        while (aux != null){
            totalReservas++;
            if (aux.estado.equalsIgnoreCase("Confirmado")){
                reservasConfirmadas++;
            }
            aux = aux.siguiente;
        }
        System.out.println("Total de reservas: "+totalReservas);
        System.out.println("Total de reservas confirmadas: "+ reservasConfirmadas);
    }

    //Metodo para el monto total facturado de reservas confirmadas
    public void montoTotalConfirmado(){
        double montoTotal = 0;
        NodoReserva aux = inicio;
        while (aux != null){
            if (aux.estado.equalsIgnoreCase("Confirmado")){
                montoTotal += aux.montoTotal;
            }
            aux = aux.siguiente;
        }
        System.out.println("Monto total facturado (reservas confirmadas):"+montoTotal);
    }

    //Metodo para calcular lo recaudado por paquete
    public void montoTotalPaquete(){
        if (esVacio()){
            System.out.println("No hay reservas registradas");
            return;
        }
        NodoReserva aux = inicio;
        double totalPaquetes = 0;
        while (aux != null){
            if (aux.estado.equalsIgnoreCase("Confirmado")){
                totalPaquetes += aux.paquete.precioPaquete;
            }
            aux = aux.siguiente;
        }
        System.out.println("Monto total neto de paquetes confirmados :"+totalPaquetes);
    }

    public int contarReservasPorClienteEnAnio(NodoCliente cliente, int year) {
        int contador = 0;
        NodoReserva actual = inicio;
        while (actual != null) {
            if (actual.getCliente().equals(cliente) &&
                    actual.getFechaReserva().getYear() == year) {
                contador++;
            }
            actual = actual.siguiente;
        }
        return contador;
    }

    public void montoPorMedioDePago(ListaPagos listaPagos) {
        System.out.println("\n=== MONTO RECAUDADO POR MEDIO DE PAGO ===");

        if (listaPagos.getInicio() == null) {
            System.out.println("No hay pagos registrados.");
            return;
        }

        double efectivo = 0, tarjeta = 0, transferencia = 0;
        NodoPago aux = listaPagos.getInicio();

        while (aux != null) {
            String medio = aux.getMedioPago();
            double monto = aux.getMontoTotal();

            if (medio != null) {
                if (medio.equalsIgnoreCase("Efectivo")) {
                    efectivo += monto;
                } else if (medio.equalsIgnoreCase("Tarjeta de credito") || medio.equalsIgnoreCase("Tarjeta")) {
                    tarjeta += monto;
                } else if (medio.equalsIgnoreCase("Transferencia bancaria") || medio.equalsIgnoreCase("Transferencia")) {
                    transferencia += monto;
                }
            }
            aux = aux.siguiente;
        }

        double total = efectivo + tarjeta + transferencia;

        System.out.println("\nEfectivo: $" + String.format("%.2f", efectivo));
        if (total > 0) {
            System.out.println("  → Porcentaje: " + String.format("%.2f", (efectivo / total * 100)) + "%");
        }

        System.out.println("\nTarjeta de crédito: $" + String.format("%.2f", tarjeta));
        if (total > 0) {
            System.out.println("  → Porcentaje: " + String.format("%.2f", (tarjeta / total * 100)) + "%");
        }

        System.out.println("\nTransferencia bancaria: $" + String.format("%.2f", transferencia));
        if (total > 0) {
            System.out.println("  → Porcentaje: " + String.format("%.2f", (transferencia / total * 100)) + "%");
        }

        System.out.println("\n─────────────────────────────────");
        System.out.println("TOTAL RECAUDADO: $" + String.format("%.2f", total));
    }

    public NodoReserva buscarReservaPorCodigo(int codigoReserva) {
        NodoReserva aux = inicio;
        while (aux != null) {
            if (aux.codigo == codigoReserva) {
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    // Variable estática para almacenar el descuento total calculado
    public static double ultimoMontoDescuento = 0;

    public static double calcularMontoReserva(NodoPaquete paquete, NodoServicioAdicional[] serviciosAdicionales,
                                              int contadorServicios, int cantidadPersona, int opcionCodigo,
                                              String codigoPromocional, ListaCodigoPromocion gestionCodigoPromocion,
                                              ListaReservas gestionReserva, NodoCliente cliente) {
        double precioPaquete = paquete.getPrecio();
        double precioServicios = 0;
        for (int i = 0; i < contadorServicios; i++) {
            NodoServicioAdicional servicio = serviciosAdicionales[i];
            String nombreServicio = servicio.getNombre();
            double precio = servicio.getPrecio();
            switch (nombreServicio) {
                case "Seguro de viaje (PERSONA)":
                    precioServicios += precio * cantidadPersona;
                    break;
                case "Guia privado (DIA)":
                case "Transporte privado (DIA)":
                    precioServicios += precio * paquete.getDuracionDias();
                    break;
                case "Entrada a atracciones (PERSONA)":
                    precioServicios += precio * cantidadPersona;
                    break;
                case "Comidas incluidas (DIASxPERSONAS)":
                    precioServicios += precio * paquete.getDuracionDias() * cantidadPersona;
                    break;
                default:
                    System.out.println("Error: opción de servicio adicional inválida.");
                    break;
            }
        }
        double montoTotal = precioPaquete * cantidadPersona + precioServicios;

        System.out.println("Monto base de la reserva: $" + montoTotal);

        double montoDescuentoAcumulado = 0; // Monto real descontado en pesos

        // Descuento por código promocional
        if (opcionCodigo == 1 && codigoPromocional != null && !codigoPromocional.isEmpty()) {
            NodoCodigoPromocion promo = gestionCodigoPromocion.buscarCodigoPromocio(codigoPromocional);
            if (promo != null) {
                double descuento = promo.getDescuento();
                double montoDescuento = montoTotal * descuento / 100;
                montoTotal -= montoDescuento;
                montoDescuentoAcumulado += montoDescuento;
                System.out.println("Descuento por código (" + descuento + "%): -$" + montoDescuento);
                System.out.println("Monto tras descuento por código: $" + montoTotal);
            } else {
                System.out.println("Código promocional no válido.");
            }
        }

        // Descuento por cliente frecuente
        int reservasAnio = gestionReserva.contarReservasPorClienteEnAnio(cliente, LocalDate.now().getYear());
        if (reservasAnio > 2) {
            double descuento = 12;
            double montoDescuento = montoTotal * descuento / 100;
            montoTotal -= montoDescuento;
            montoDescuentoAcumulado += montoDescuento;
            System.out.println("Descuento por cliente frecuente (12%): -$" + montoDescuento);
            System.out.println("Monto tras descuento por cliente frecuente: $" + montoTotal);
        }

        // Descuento por contratación anticipada
        long diasAnticipacion = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), paquete.getInicioFechaViaje());
        if (diasAnticipacion > 60) {
            double descuento = 8;
            double montoDescuento = montoTotal * descuento / 100;
            montoTotal -= montoDescuento;
            montoDescuentoAcumulado += montoDescuento;
            System.out.println("Descuento por contratación anticipada (8%): -$" + montoDescuento);
            System.out.println("Monto tras descuento por contratación anticipada: $" + montoTotal);
        }

        // Descuento por grupo grande
        if (cantidadPersona > 10) {
            double descuento = 10;
            double montoDescuento = montoTotal * descuento / 100;
            montoTotal -= montoDescuento;
            montoDescuentoAcumulado += montoDescuento;
            System.out.println("Descuento por grupo grande (10%): -$" + montoDescuento);
            System.out.println("Monto tras descuento por grupo grande: $" + montoTotal);
        }

        System.out.println("Monto final de la reserva: $" + montoTotal);

        // Guardar el monto de descuento para usarlo en la creación de la reserva
        ultimoMontoDescuento = montoDescuentoAcumulado;

        return montoTotal;
    }


    // Metodo 5 reportes
    public void montoTotalDescuentos() {
        System.out.println("\n=== MONTO TOTAL DE DESCUENTOS APLICADOS ===");

        if (esVacio()) {
            System.out.println("No hay reservas registradas.");
            return;
        }

        double totalDescuentos = 0;
        int cantidadReservasConDescuento = 0;
        double montoSinDescuentos = 0;
        double montoConDescuentos = 0;

        NodoReserva aux = inicio;
        while (aux != null) {
            if (aux.montoDescuento > 0) {
                cantidadReservasConDescuento++;
            }
            totalDescuentos += aux.montoDescuento;
            montoConDescuentos += aux.montoTotal;
            montoSinDescuentos += (aux.montoTotal + aux.montoDescuento);
            aux = aux.siguiente;
        }

        System.out.println("\nReservas con descuento: " + cantidadReservasConDescuento);
        System.out.println("Monto sin descuentos: $" + String.format("%.2f", montoSinDescuentos));
        System.out.println("Monto con descuentos: $" + String.format("%.2f", montoConDescuentos));
        System.out.println("\n─────────────────────────────────");
        System.out.println("TOTAL DESCUENTOS APLICADOS: $" + String.format("%.2f", totalDescuentos));

        if (montoSinDescuentos > 0) {
            double porcentajeDescuento = (totalDescuentos / montoSinDescuentos) * 100;
            System.out.println("Porcentaje de descuento promedio: " + String.format("%.2f", porcentajeDescuento) + "%");
        }
    }

    // Metodo 6 reportes
    public void clienteMayorMenorGasto() {
        if (esVacio()) {
            System.out.println("No hay reservas registradas");
            return;
        }

        // Recorrer reservas confirmadas y sumar por cliente
        NodoReserva aux = inicio;
        NodoCliente clienteMayor = null;
        NodoCliente clienteMenor = null;
        double mayorGasto = 0;
        double menorGasto = Double.MAX_VALUE;

        // Usamos un enfoque simple: recorremos todas las reservas
        while (aux != null) {
            if (aux.estado.equalsIgnoreCase("Confirmado")) {
                NodoCliente clienteActual = aux.cliente;
                double gastoTotal = calcularGastoPorCliente(clienteActual);

                if (gastoTotal > mayorGasto) {
                    mayorGasto = gastoTotal;
                    clienteMayor = clienteActual;
                }
                if (gastoTotal < menorGasto) {
                    menorGasto = gastoTotal;
                    clienteMenor = clienteActual;
                }
            }
            aux = aux.siguiente;
        }

        if (clienteMayor != null) {
            System.out.println("Cliente que más gastó: " + clienteMayor.getNombre() + " - $" + mayorGasto);
        }
        if (clienteMenor != null && menorGasto != Double.MAX_VALUE) {
            System.out.println("Cliente que menos gastó: " + clienteMenor.getNombre() + " - $" + menorGasto);
        }
    }

    private double calcularGastoPorCliente(NodoCliente cliente) {
        double total = 0;
        NodoReserva aux = inicio;
        while (aux != null) {
            if (aux.estado.equalsIgnoreCase("Confirmado") && aux.cliente.equals(cliente)) {
                total += aux.montoTotal;
            }
            aux = aux.siguiente;
        }
        return total;
    }

    //Metodo 7 reportes
    public void porcentajeEstadoReservas() {
        int total = 0, confirmadas = 0, pendientes = 0, canceladas = 0;
        NodoReserva aux = inicio;
        while (aux != null) {
            total++;
            if (aux.estado.equalsIgnoreCase("Confirmado")) {
                confirmadas++;
            } else if (aux.estado.equalsIgnoreCase("Pendiente")) {
                pendientes++;
            } else if (aux.estado.equalsIgnoreCase("Cancelado")) {
                canceladas++;
            }
            aux = aux.siguiente;
        }

        if (total > 0) {
            System.out.println("Total de reservas: " + total);
            System.out.println("Confirmadas: " + confirmadas + " (" + (confirmadas * 100.0 / total) + "%)");
            System.out.println("Pendientes: " + pendientes + " (" + (pendientes * 100.0 / total) + "%)");
            System.out.println("Canceladas: " + canceladas + " (" + (canceladas * 100.0 / total) + "%)");
        } else {
            System.out.println("No hay reservas registradas");
        }
    }

    //Metodo 8 reportes
    public void ocupacionPorPaquete() {
        if (esVacio()) {
            System.out.println("No hay reservas registradas");
            return;
        }

        NodoReserva aux = inicio;
        System.out.println("\n=== OCUPACIÓN POR PAQUETE ===");
        while (aux != null) {
            if (aux.estado.equalsIgnoreCase("Confirmado")) {
                NodoPaquete paquete = aux.paquete;
                int ocupadas = paquete.plazasTotales - paquete.plazaDiponible;
                double porcentaje = (ocupadas * 100.0) / paquete.plazasTotales;
                System.out.println("Destino: " + paquete.destino + " | Ocupadas: " + ocupadas + "/" +
                        paquete.plazasTotales + " (" + String.format("%.2f", porcentaje) + "%)");
            }
            aux = aux.siguiente;
        }
    }

    //Metodo 9 reportes
    public void porcentajeReservasPorTipo() {
        int totalNacional = 0, totalInternacional = 0;
        NodoReserva aux = inicio;
        while (aux != null) {
            if (aux.estado.equalsIgnoreCase("Confirmado")) {
                if (aux.paquete.tipoPaquete.equalsIgnoreCase("Nacional")) {
                    totalNacional++;
                } else if (aux.paquete.tipoPaquete.equalsIgnoreCase("Internacional")) {
                    totalInternacional++;
                }
            }
            aux = aux.siguiente;
        }

        int total = totalNacional + totalInternacional;
        if (total > 0) {
            System.out.println("Total de reservas confirmadas: " + total);
            System.out.println("Nacional: " + totalNacional + " (" + (totalNacional * 100.0 / total) + "%)");
            System.out.println("Internacional: " + totalInternacional + " (" + (totalInternacional * 100.0 / total) + "%)");
        } else {
            System.out.println("No hay reservas confirmadas");
        }
    }

    //Metodo 10 reportes
    public void promedioPlazasVendidasPorPaquete() {
        if (esVacio()) {
            System.out.println("No hay reservas registradas");
            return;
        }

        int totalPlazasVendidas = 0;
        int totalReservas = 0;
        NodoReserva aux = inicio;
        while (aux != null) {
            if (aux.estado.equalsIgnoreCase("Confirmado")) {
                totalPlazasVendidas += aux.cantidadPersonas;
                totalReservas++;
            }
            aux = aux.siguiente;
        }

        if (totalReservas > 0) {
            double promedio = (double) totalPlazasVendidas / totalReservas;
            System.out.println("Promedio de plazas vendidas por paquete: " + String.format("%.2f", promedio));
        } else {
            System.out.println("No hay reservas confirmadas");
        }
    }

    // Metodo 11 reportes
    public void porcentajeServiciosVendidos() {
        int totalReservas = 0;
        int reservasConServicios = 0;
        NodoReserva aux = inicio;

        while (aux != null) {
            if (aux.estado.equalsIgnoreCase("Confirmado")) {
                totalReservas++;
                if (aux.servicioAdicional != null && aux.servicioAdicional.length > 0) {
                    boolean tieneServicio = false;
                    for (NodoServicioAdicional servicio : aux.servicioAdicional) {
                        if (servicio != null) {
                            tieneServicio = true;
                            break;
                        }
                    }
                    if (tieneServicio) {
                        reservasConServicios++;
                    }
                }
            }
            aux = aux.siguiente;
        }

        if (totalReservas > 0) {
            double porcentaje = (reservasConServicios * 100.0) / totalReservas;
            System.out.println("Reservas con servicios adicionales: " + reservasConServicios + "/" + totalReservas +
                    " (" + String.format("%.2f", porcentaje) + "%)");
        } else {
            System.out.println("No hay reservas confirmadas");
        }
    }
}