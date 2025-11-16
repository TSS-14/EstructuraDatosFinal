package Menu;

import EstructuraNodos.ListaPagos;
import EstructuraNodos.ListaReservas;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestionPagos {
    public static void menuPagos(ListaPagos gestionPagos, ListaReservas gestionReservas, Scanner sc) {
        int opcionPago = 0;
        do {
            System.out.println("\n--GESTIÓN DE PAGOS--");
            System.out.println("1. Registrar pago");
            System.out.println("2. Mostrar pagos");
            System.out.println("3. Mostrar porcentaje por medio de pago");
            System.out.println("4. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcionPago = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número.");
                sc.nextLine();
                continue;
            }

            switch (opcionPago) {
                case 1: {
                    System.out.println("-- Registro de pago --");

                    int codigoReserva = 0;
                    System.out.println("Ingrese el código de la reserva: ");
                    try {
                        codigoReserva = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número.");
                        sc.nextLine();
                        continue;
                    }

                    double monto = gestionReservas.calcularMontoReserva(codigoReserva);
                    System.out.println("Monto de la reserva a pagar: " + monto);

                    String metodoPago = "";
                    int opcionMetodo;
                    do {
                        System.out.println("\nSeleccione el método de pago:");
                        System.out.println("1.Efectivo");
                        System.out.println("2.Tarjeta de credito");
                        System.out.println("3.Transferencia bancaria");
                        System.out.println("Opcion: ");
                        try {
                            opcionMetodo = sc.nextInt();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número.");
                            sc.nextLine();
                            opcionMetodo = 0;
                            continue;
                        }

                        switch (opcionMetodo) {
                            case 1 -> metodoPago = "Efectivo";
                            case 2 -> metodoPago = "Tarjeta de credito";
                            case 3 -> metodoPago = "Transferencia";
                            default -> System.out.println("Opción inválida. Intente nuevamente");
                        }
                    } while (opcionMetodo < 1 || opcionMetodo > 3);

                    if (metodoPago.equalsIgnoreCase("Efectivo")) {
                        System.out.println("Recibe: ");
                        double recibe = sc.nextDouble();
                        sc.nextLine();

                        double devuelve = recibe - monto;
                        System.out.println("Devuelve " + devuelve);
                        gestionReservas.confirmarReserva(codigoReserva);

                    } else if (metodoPago.equalsIgnoreCase("Tarjeta de credito")) {
                        System.out.println("Ingrese los últimos 4 dígitos de la tarjeta:");
                        String ultimosDigitos = sc.nextLine();
                        System.out.println("Pago realizado con tarjeta terminada en " + ultimosDigitos);
                        gestionReservas.confirmarReserva(codigoReserva);

                    } else if (metodoPago.equalsIgnoreCase("Transferencia")) {
                        System.out.println("Ingrese el número de referencia de la transferencia:");
                        String referencia = sc.nextLine();
                        System.out.println("Transferencia registrada con referencia " + referencia);
                        gestionReservas.confirmarReserva(codigoReserva);
                    }
                    gestionPagos.registrarPago(codigoReserva, monto, metodoPago, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    break;
                }
                case 2:
                    gestionPagos.mostrarPagos();
                    break;

                case 3:
                    gestionPagos.mostrarPorcentajePorMedio();
            }
        } while (opcionPago != 4);
    }
}