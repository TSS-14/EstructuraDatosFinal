package Menu;

import EstructuraNodos.ListaPaquete;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionPaqueteTuristico {
    public static void menuPaqueteTuristico(ListaPaquete gestionPaquete, Scanner sc){
        int opcionPaquete = 0;
        do {
            System.out.println("\n--GESTIÓN DE PAQUETES TURISTICOS");
            System.out.println("1.Registrar nuevo paquete");
            System.out.println("2.Buscar paquete por codigo");
            System.out.println("3.Mostrar todos los paquetes");
            System.out.println("4.Eliminar paquetes");
            System.out.println("5.Volver al menu principal");
            System.out.println("Seleccione una opcion");
            try {
                opcionPaquete = sc.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un numero.");
                sc.nextLine();
                continue;
            }

            switch (opcionPaquete){
                case 1:
                    System.out.println("-- Registro de paquetes --");
                    sc.nextLine();

                    System.out.println("Ingrese el codigo del paquete");
                    String codigoPaquete = sc.nextLine();

                    String destino = "";
                    boolean destinoValido = false;

                    while (!destinoValido){
                        System.out.println("Ingrese el destino del paquete:");
                        destino = sc.nextLine();

                        if (destino.matches("\\d+")){
                            System.out.println("Error: Solo se permite letras, Intente nuevamente.");
                        }else {
                            destinoValido = true;
                        }
                    }

                    int duracion = 0;
                    boolean diasValidos = false;
                    while (!diasValidos){
                        System.out.println("Ingrese el numero de dias:");
                        try {
                            duracion = sc.nextInt();
                        } catch (InputMismatchException e){
                            System.out.println("Error: debe ingresar solo numeros, Intente nuevamente.");
                            sc.nextLine();
                            continue;
                        }
                        sc.nextLine();

                        if (duracion <= 0){
                            System.out.println("Error: el numero de dias debe ser mayor a cero, Intente nuevamente");
                        }else {
                            diasValidos = true;
                        }
                    }

                    String tipoPaquete = "";
                    int opcionTipoPaquete = 0;
                    do {
                        System.out.println("\nSeleccione el tipo de paquete");
                        System.out.println("1.Nacional");
                        System.out.println("2.Internacional");
                        System.out.println("Opción:");
                        try {
                            opcionTipoPaquete = sc.nextInt();
                        } catch (InputMismatchException e){
                            System.out.println("Opción invalida. Intente nuevamente");
                            sc.nextLine();
                            opcionTipoPaquete = 0;
                            continue;
                        }
                        sc.nextLine();

                        switch (opcionTipoPaquete){
                            case 1 -> tipoPaquete = "Nacional";
                            case 2 -> tipoPaquete = "Internacional";
                            default -> System.out.println("Opcion invalida. Intente nuevamente");
                        }
                    }while (opcionTipoPaquete < 1 || opcionTipoPaquete > 2);

                    double precioPaquete = 0;
                    System.out.println("Ingrese el precio del paquete");
                    try {
                        precioPaquete = sc.nextDouble();
                    } catch (InputMismatchException e){
                        System.out.println("Error: debe ingresar un numero decimal.");
                        sc.nextLine();
                    }

                    int plazasTotales = 0;
                    System.out.println("Ingrese las plazas totales");
                    try {
                        plazasTotales = sc.nextInt();
                    } catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un numero.");
                        sc.nextLine();
                    }

                    int plazaDisponible = plazasTotales;


                    gestionPaquete.agregarPaquete(codigoPaquete, destino, duracion, tipoPaquete,
                                                    precioPaquete, plazasTotales, plazaDisponible);
                    break;

                case 2:
                    System.out.println("-- Busqueda de Paquete --");
                    sc.nextLine();

                    System.out.println("Ingrese el codigo del paquete");
                    String codigo = sc.nextLine();

                    gestionPaquete.buscarPaquete(codigo);
                    break;

                case 3:
                    System.out.println("-- Mostrar todos los paquetes --");
                    sc.nextLine();

                    gestionPaquete.mostrarPaquetes();
                    break;
                case 4:
                    System.out.println("-- Eliminar paquete por codigo --");
                    sc.nextLine();

                    System.out.println("Ingrese el codigo del paquete");
                    String eliminarCodigo = sc.nextLine();

                    gestionPaquete.eliminarPaquete(eliminarCodigo);
                    break;
            }
        }while (opcionPaquete != 5);
    }
}
