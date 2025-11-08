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
            System.out.println("1. Registrar nuevo pago");
            System.out.println("2. Ver todos los pagos");
            System.out.println("3. Buscar pagos por reserva");
            System.out.println("4. Confirmar pago");
            System.out.println("5. Cancelar pago");
            System.out.println("6. Volver al menú principal");
            System.out.println("Seleccione una opción:");
            
            try {
                opcionPago = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número.");
                sc.nextLine();
                continue;
            }

            switch (opcionPago) {
                case 1: {
                    System.out.println("-- Registro de pago --");
                    
                    int codigoReserva = 0;
                    System.out.println("Ingrese el código de la reserva:");
                    try {
                        codigoReserva = sc.nextInt();
                        sc.nextLine(); // Limpiar buffer
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número.");
                        sc.nextLine();
                        continue;
                    }

                    double monto = 0;
                    System.out.println("Ingrese el monto del pago:");
                    try {
                        monto = sc.nextDouble();
                        sc.nextLine(); // Limpiar buffer
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número.");
                        sc.nextLine();
                        continue;
                    }

                    String metodoPago = "";
                    int opcionMetodo;
                    do {
                        System.out.println("\nSeleccione el método de pago:");
                        System.out.println("1. Efectivo");
                        System.out.println("2. Tarjeta de crédito");
                        System.out.println("3. Transferencia bancaria");
                        System.out.println("4. PSE");
                        try {
                            opcionMetodo = sc.nextInt();
                            sc.nextLine(); // Limpiar buffer
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un número.");
                            sc.nextLine();
                            opcionMetodo = 0;
                            continue;
                        }

                        switch (opcionMetodo) {
                            case 1 -> metodoPago = "Efectivo";
                            case 2 -> metodoPago = "Tarjeta de crédito";
                            case 3 -> metodoPago = "Transferencia bancaria";
                            case 4 -> metodoPago = "PSE";
                            default -> System.out.println("Opción inválida. Intente nuevamente");
                        }
                    } while (opcionMetodo < 1 || opcionMetodo > 4);

                    // Obtener fecha y hora actual
                    LocalDateTime ahora = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String fechaPago = ahora.format(formato);

                    gestionPagos.registrarPago(codigoReserva, monto, metodoPago, fechaPago);
                    break;
                }
                case 2: {
                    System.out.println("-- Ver todos los pagos --");
                    gestionPagos.mostrarPagos();
                    break;
                }
                case 3: {
                    System.out.println("-- Buscar pagos por reserva --");
                    System.out.println("Ingrese el código de la reserva:");
                    try {
                        int codigoReserva = sc.nextInt();
                        sc.nextLine(); // Limpiar buffer
                        gestionPagos.mostrarPagosPorReserva(codigoReserva);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número.");
                        sc.nextLine();
                    }
                    break;
                }
                case 4: {
                    System.out.println("-- Confirmar pago --");
                    System.out.println("Ingrese el código del pago:");
                    try {
                        int codigoPago = sc.nextInt();
                        sc.nextLine(); // Limpiar buffer
                        gestionPagos.confirmarPago(codigoPago);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número.");
                        sc.nextLine();
                    }
                    break;
                }
                case 5: {
                    System.out.println("-- Cancelar pago --");
                    System.out.println("Ingrese el código del pago:");
                    try {
                        int codigoPago = sc.nextInt();
                        sc.nextLine(); // Limpiar buffer
                        gestionPagos.cancelarPago(codigoPago);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número.");
                        sc.nextLine();
                    }
                    break;
                }
            }
        } while (opcionPago != 6);
    }
}