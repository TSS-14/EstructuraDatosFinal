package Menu;

import EstructuraNodos.ColaClientes;
import EstructuraNodos.PilaServicios;
import EstructuraNodos.ListaCliente;
import EstructuraNodos.NodoCliente;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionSimulacion {

    public static void menuSimulacion(
            ColaClientes colaAtencion, 
            ColaClientes colaEspera, 
            PilaServicios pilaServicios, 
            ListaCliente listaClientes, // Para buscar clientes
            Scanner sc) {

        int opcion = 0;
        do {
            System.out.println("\n-- SIMULACIÓN DE COLAS Y PILAS --");
            System.out.println("1. Agregar cliente a Cola de Atención");
            System.out.println("2. Atender cliente (Desencolar)");
            System.out.println("3. Mostrar Cola de Atención");
            System.out.println("---");
            System.out.println("4. Agregar cliente a Lista de Espera");
            System.out.println("5. Atender cliente (Lista de Espera)");
            System.out.println("6. Mostrar Lista de Espera");
            System.out.println("---");
            System.out.println("7. Apilar Servicio Vendido (Simulación)");
            System.out.println("8. Desapilar Último Servicio Vendido");
            System.out.println("9. Mostrar Pila de Servicios Vendidos");
            System.out.println("---");
            System.out.println("10. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1: // Agregar a Cola de Atención
                    System.out.println("Ingrese documento del cliente a encolar:");
                    String doc1 = sc.nextLine();
                    NodoCliente cliente1 = listaClientes.buscarCliente(doc1);
                    if (cliente1 != null) {
                        colaAtencion.encolar(cliente1.getNombre()); // Guardamos solo el nombre
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case 2: // Atender Cola
                    Object atendido1 = colaAtencion.desencolar();
                    if (atendido1 != null) {
                        System.out.println("Cliente atendido: " + atendido1.toString());
                    }
                    break;
                case 3: // Mostrar Cola Atención
                    colaAtencion.mostrar();
                    break;
                case 4: // Agregar a Lista de Espera
                    System.out.println("Ingrese documento del cliente a encolar en espera:");
                    String doc2 = sc.nextLine();
                    NodoCliente cliente2 = listaClientes.buscarCliente(doc2);
                    if (cliente2 != null) {
                        colaEspera.encolar(cliente2.getNombre()); // Guardamos solo el nombre
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case 5: // Atender Lista de Espera
                    Object atendido2 = colaEspera.desencolar();
                    if (atendido2 != null) {
                        System.out.println("Cliente de lista de espera atendido: " + atendido2.toString());
                    }
                    break;
                case 6: // Mostrar Lista de Espera
                    colaEspera.mostrar();
                    break;
                case 7: // Apilar Servicio
                    System.out.println("Simulación: Ingrese nombre del servicio vendido:");
                    String servicio = sc.nextLine();
                    pilaServicios.apilar(servicio);
                    break;
                case 8: // Desapilar Servicio
                    Object ultimoServicio = pilaServicios.desapilar();
                    if (ultimoServicio != null) {
                        System.out.println("Servicio desapilado: " + ultimoServicio.toString());
                    }
                    break;
                case 9: // Mostrar Pila
                    pilaServicios.mostrar();
                    break;
                case 10:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 10);
    }
}
