package Menu;

import EstructuraNodos.ColaClientes;
import EstructuraNodos.PilaServicios;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionSimulacion {

    public static void menuSimulacion(
            ColaClientes colaAtencion, 
            ColaClientes colaEspera, 
            PilaServicios pilaServicios, 
            Scanner sc) {

        int opcion = 0;
        do {
            System.out.println("\n --VISUALIZACIÓN DE COLAS Y PILAS-- ");
            System.out.println("  (Se actualizan automáticamente)");
            System.out.println();
            System.out.println("1. Ver Cola de Atención Actual");
            System.out.println("2. Ver Lista de Espera");
            System.out.println("3. Ver Historial de Servicios Vendidos");
            System.out.println("4. Ver Estadísticas Generales");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("\nSeleccione una opción: ");

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
                    colaAtencion.mostrar();
                    break;

                case 2:
                    colaEspera.mostrar();
                    break;

                case 3:
                    pilaServicios.mostrar();
                    break;

                case 4:
                    mostrarEstadisticas(colaAtencion, colaEspera, pilaServicios);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);
    }

    /**
     * Muestra estadísticas generales de las colas y pilas
     */
    private static void mostrarEstadisticas(ColaClientes colaAtencion,
                                           ColaClientes colaEspera,
                                           PilaServicios pilaServicios) {

        System.out.println("\n ---ESTADÍSTICAS DE COLAS Y PILAS---");

        // Contar elementos en cada estructura
        int enAtencion = contarElementos(colaAtencion);
        int enEspera = contarElementos(colaEspera);
        int serviciosVendidos = contarElementos(pilaServicios);

        System.out.println("\n COLAS:");
        System.out.println("  • Clientes en atención: " + enAtencion);
        System.out.println("  • Clientes en lista de espera: " + enEspera);
        System.out.println("  • Total en colas: " + (enAtencion + enEspera));

        System.out.println("\n SERVICIOS:");
        System.out.println("  • Servicios vendidos registrados: " + serviciosVendidos);

    }


    private static int contarElementos(Object estructura) {
        int contador = 0;
        try {
            if (estructura instanceof ColaClientes) {
                ColaClientes cola = (ColaClientes) estructura;
                if (!cola.esVacia()) {
                    // Recorrer para contar (sin mostrar)
                    contador = cola.contarElementos();
                }
            } else if (estructura instanceof PilaServicios) {
                PilaServicios pila = (PilaServicios) estructura;
                if (!pila.esVacia()) {
                    contador = pila.contarElementos();
                }
            }
        } catch (Exception e) {
            contador = 0;
        }
        return contador;
    }
}
