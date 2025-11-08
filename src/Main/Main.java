package Main;

import EstructuraNodos.ColaClientes;
import EstructuraNodos.PilaServicios;
import Menu.GestionSimulacion;
import EstructuraNodos.ListaCliente;
import EstructuraNodos.ListaPaquete;
import EstructuraNodos.ListaReservas;
import EstructuraNodos.ListaServicioAdicional;
import Menu.GestionCliente;
import Menu.GestionPaqueteTuristico;
import Menu.GestionReportes;
import Menu.GestionReservas;
import Menu.GestionServicios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ListaPaquete listaPaquete = new ListaPaquete();
        ListaCliente listaCliente = new ListaCliente();
        ListaServicioAdicional listaServicioAdicional = new ListaServicioAdicional();
        ListaReservas listaReservas = new ListaReservas();
        GestionCliente gestionCliente = new GestionCliente();
        GestionPaqueteTuristico gestionPaqueteTuristico = new GestionPaqueteTuristico();
        GestionServicios gestionServicios = new GestionServicios();
        GestionReservas gestionReservas = new GestionReservas();
        GestionReportes gestionReportes = new GestionReportes();
        ColaClientes colaAtencion = new ColaClientes();
        ColaClientes colaEspera = new ColaClientes();
        PilaServicios pilaServicios = new PilaServicios();




        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("===========================");
            System.out.println("AGENCIA DE VIAJES ECORUTAS");
            System.out.println("===========================");
            System.out.println("1.Gestión de Clientes");
            System.out.println("2.Gestión de Paquetes Turisticos");
            System.out.println("3.Gestion de Servicios Adicionales");
            System.out.println("4.Gestión de Reservas");
            System.out.println("5.Gestión de pagos");
            System.out.println("6.Reportes y estadísticas");
            System.out.println("7.Simulación de Colas y Pilas");
            System.out.println("8.Salir");
            System.out.println("Ingrese su opción:");
            try {
                opcion = sc.nextInt();
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
                    GestionPaqueteTuristico.menuPaqueteTuristico(listaPaquete, sc);
                    break;
                case 3:
                    GestionServicios.menuServicio(listaServicioAdicional, sc);
                    break;
                case 4:
                    GestionReservas.menuReservas(listaReservas, listaCliente, listaPaquete, listaServicioAdicional, sc);
                    break;
                case 6:
                    GestionReportes.menuReportes(listaCliente, listaReservas, listaPaquete, sc);
                    break;
                case 7: 
                    GestionSimulacion.menuSimulacion(
                            colaAtencion, 
                            colaEspera, 
                            pilaServicios, 
                            listaCliente, 
                            sc);
                    break;

            }
        } while (opcion != 8);
    }
}
