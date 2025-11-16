package Menu;

import EstructuraNodos.ListaCliente;
import EstructuraNodos.ListaReservas;
import EstructuraNodos.ListaPaquete;
import EstructuraNodos.ListaPagos;
import EstructuraNodos.ColaClientes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionReportes {

    public static void menuReportes(ListaCliente gestionCliente, ListaReservas gestionReserva, ListaPaquete gestionPaquete, ListaPagos gestionPagos, ColaClientes colaAtencion, Scanner sc){
        int opcionReporte = 0;
        
        do {
            System.out.println("\n--REPORTES Y ESTADÍSTICAS--");
            System.out.println("---------------------------------------------");
            System.out.println("=== RESERVAS Y FACTURACIÓN ===");
            System.out.println("1. Total de reservas realizadas y confirmadas");
            System.out.println("2. Monto total facturado (paquetes + servicios)");
            System.out.println("3. Monto recaudado por cada medio de pago");
            System.out.println("4. Monto recaudado por paquete (por destino)");
            System.out.println("5. Monto total de descuentos aplicados");
            System.out.println("6. Cliente que más gastó y que menos gastó");
            System.out.println("7. Porcentaje de reservas confirmadas / pendientes / canceladas");
            System.out.println("8. Ocupación por paquete");
            System.out.println("9. Porcentaje de reservas por tipo (nacional/Internacional)");
            System.out.println("10. Promedio de plazas vendidas por paquete");
            System.out.println("11. Porcentaje de servicios vendidos");
            System.out.println("---------------------------------------------");
            System.out.println("=== CLIENTES Y DEMOGRAFÍA ===");
            System.out.println("12. Clientes atendidos por género");
            System.out.println("13. Estadísticas por rango de edad");
            System.out.println("14. Personas en cola de atención");
            System.out.println("15. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcionReporte = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número.");
                sc.nextLine();
                continue;
            }

            switch (opcionReporte) {
                case 1:
                    gestionReserva.contadorReservas();
                    break;
                case 2:
                    gestionReserva.montoTotalConfirmado();
                    break;
                case 3:
                    gestionReserva.montoPorMedioDePago(gestionPagos);
                    break;
                case 4:
                    gestionReserva.montoTotalPaquete();
                    break;
                case 5:
                    gestionReserva.montoTotalDescuentos();
                    break;
                case 6:
                    gestionReserva.clienteMayorMenorGasto();
                    break;
                case 7:
                    gestionReserva.porcentajeEstadoReservas();
                    break;
                case 8:
                    gestionReserva.ocupacionPorPaquete();
                    break;
                case 9:
                    gestionReserva.porcentajeReservasPorTipo();
                    break;
                case 10:
                    gestionReserva.promedioPlazasVendidasPorPaquete();
                    break;
                case 11:
                    gestionReserva.porcentajeServiciosVendidos();
                    break;
                case 12:
                    gestionCliente.clientesPorGenero();
                    break;
                case 13:
                    gestionCliente.estadisticasPorEdad();
                    break;
                case 14:
                    colaAtencion.contarPersonasEnCola();
                    break;
                case 15:
                    System.out.println("Volviendo al menú principal.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcionReporte != 15);
    }
}