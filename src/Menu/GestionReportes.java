package Menu;

import EstructuraNodos.ListaCliente;
import EstructuraNodos.ListaReservas;
import EstructuraNodos.ListaPaquete; 

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionReportes {

    public static void menuReportes(ListaCliente gestionCliente, ListaReservas gestionReserva, ListaPaquete gestionPaquete, Scanner sc){
        int opcionReporte = 0;
        
        do {
            System.out.println("\n--- GESTIÓN DE REPORTES Y ESTADÍSTICAS ---");
            System.out.println("-----------------------------------------");
            System.out.println("📊 REPORTES DE RESERVAS Y FACTURACIÓN");
            System.out.println("1.  Reservas por Estado (Cantidad y Porcentaje)");
            System.out.println("2.  Monto Total Facturado (Confirmado)");
            System.out.println("3.  Monto Recaudado por Paquete y Destino");
            System.out.println("4.  Monto Recaudado por Servicios Adicionales");
            System.out.println("5.  Monto Total de Descuentos Aplicados");
            System.out.println("6.  Porcentaje de Reservas con Servicios Vendidos");
            System.out.println("7.  Cliente con Mayor y Menor Gasto");
            
            System.out.println("\n👤 REPORTES DE CLIENTES Y DEMOGRAFÍA");
            System.out.println("8.  Clientes por Género");
            System.out.println("9.  Clientes por Rango de Edad (Cantidad)");
            System.out.println("10. Monto Recaudado por Rango de Edad");
            
            System.out.println("\n--- PENDIENTES ---");
            System.out.println("11. Reporte de Medios de Pago (Requiere implementación de Pagos)");
            System.out.println("12. Volver al menú principal");
            System.out.print("\nSeleccione una opción: ");
            
            try {
                opcionReporte = sc.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número.");
                sc.nextLine(); // Limpiar buffer
                continue;
            }
            sc.nextLine(); // Limpiar buffer

            System.out.println("-----------------------------------------");

            switch (opcionReporte) {
                // REPORTES DE RESERVAS Y FACTURACIÓN
                case 1:
                    // R1: Cantidad de reservas por estado y porcentaje.
                    gestionReserva.reporteEstadoReservas(); 
                    break;
                case 2:
                    // R3: Monto total facturado.
                    gestionReserva.calcularMontoTotalFacturado();
                    break;
                case 3:
                    // R5: Monto recaudado por paquete (por destino).
                    gestionReserva.reporteMontoPorPaquete(gestionPaquete);
                    break;
                case 4:
                    // R6: Monto recaudado por cada servicio adicional.
                    gestionReserva.reporteMontoPorServicio();
                    break;
                case 5:
                    // R7: Monto total de descuentos aplicados.
                    gestionReserva.calcularMontoTotalDescuentos();
                    break;
                case 6:
                    // R15: Porcentaje de servicios vendidos respecto al total de reservas.
                    gestionReserva.reportePorcentajeServiciosVendidos();
                    break;
                case 7:
                    // R8: Cliente que más gastó y cliente que menos gastó.
                    gestionReserva.reporteClienteMayorMenorGasto(gestionCliente);
                    break;
                
                // REPORTES DE CLIENTES Y DEMOGRAFÍA
                case 8:
                    // R2: Cantidad de clientes por género.
                    gestionCliente.contarClientesPorGenero();
                    break;
                case 9:
                    // R13a: Estadísticas por rango de edad (Cantidad de clientes).
                    gestionCliente.contarClientesPorRangoEdad();
                    break;
                case 10:
                    // R13b: Estadísticas por rango de edad (Monto recaudado).
                    gestionCliente.reporteMontoRecaudadoPorEdad(gestionReserva);
                    break;
                    
                // PENDIENTES / SALIR
                case 11:
                    // R4: Monto recaudado por cada medio de pago.
                    System.out.println("\nEl Reporte de Medios de Pago está pendiente de implementación.");
                    break;
                case 12:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcionReporte != 12);
    }
}