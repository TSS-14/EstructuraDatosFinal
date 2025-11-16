package Main;

import EstructuraNodos.*;
import Menu.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaPaquete listaPaquete = new ListaPaquete();
        ListaCliente listaCliente = new ListaCliente();
        ListaServicioAdicional listaServicioAdicional = new ListaServicioAdicional();
        ListaReservas listaReservas = new ListaReservas();
        ListaPagos listaPagos = new ListaPagos();
        ListaCodigoPromocion listaCodigoPromocion = new ListaCodigoPromocion();
        ColaClientes colaAtencion = new ColaClientes();
        ColaClientes colaEspera = new ColaClientes();
        PilaServicios pilaServicios = new PilaServicios();

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n===========================");
            System.out.println("AGENCIA DE VIAJES ECORUTAS");
            System.out.println("===========================");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Paquetes Turisticos");
            System.out.println("3. Gestion de Servicios Adicionales");
            System.out.println("4. Gestión de Reservas");
            System.out.println("5. Gestión de pagos");
            System.out.println("6. Reportes y estadísticas");
            System.out.println("7. Visualización de Colas y Pilas");
            System.out.println("8. Salir");
            System.out.print("Ingrese su opción: ");
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    GestionCliente.menuCliente(listaCliente, sc);
                    break;
                case 2:
                    GestionPaqueteTuristico.menuPaqueteTuristico(listaPaquete, listaCodigoPromocion, sc);
                    break;
                case 3:
                    GestionServicios.menuServicio(listaServicioAdicional, sc);
                    break;
                case 4:
                    GestionReservas.menuReservas(listaReservas, listaCliente, listaPaquete, listaServicioAdicional, listaCodigoPromocion, colaAtencion, pilaServicios, sc);
                    break;
                case 5:
                    GestionPagos.menuPagos(listaPagos, listaReservas, sc);
                    break;
                case 6:
                    GestionReportes.menuReportes(listaCliente, listaReservas, listaPaquete, listaPagos, colaAtencion, sc);
                    break;
                case 7:
                    GestionSimulacion.menuSimulacion(colaAtencion, colaEspera, pilaServicios, sc);
                    break;
                case 8:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 8);

        sc.close();
    }
}
