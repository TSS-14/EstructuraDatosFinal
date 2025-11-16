package Menu;

import EstructuraNodos.ListaServicioAdicional;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionServicios {
    public static void menuServicio(ListaServicioAdicional gestionServicioAdicional, Scanner sc){
        int opcionServicio = 0;
        do {
            System.out.println("\n--GESTIÓN DE SERVICIOS ADICIONALES--");
            System.out.println("1. Agregar servicio adicional");
            System.out.println("2. Mostrar servicios adicionales");
            System.out.println("3. Actualizar valor de servicio adicional");
            System.out.println("4. Eliminar servicio adicional");
            System.out.println("5. Regresar al menu principal");
            System.out.print("Seleccione una opcion: ");
            try {
                opcionServicio = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un numero.");
                sc.nextLine();
                continue;
            }

            switch (opcionServicio) {
                case 1:{

                    System.out.println("-- Registro de servicio --");
                    sc.nextLine();

                    String nombreServicio = "";
                    int opcionNombreServicio = 0;
                    do {
                        System.out.println("\nSeleccione el tipo de servicio");
                        System.out.println("1.Seguro de viaje (PERSONA)");
                        System.out.println("2.Guia privado (DIA)");
                        System.out.println("3.Transporte privado (TRASLADO)");
                        System.out.println("4.Entrada a atracciones (PERSONA)");
                        System.out.println("5.Comidas incluidas (DIASxPERSONAS)");
                        System.out.println("Ingrese su opcion");

                        try {
                            opcionNombreServicio = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ser un numero.");
                            sc.nextLine();
                            opcionNombreServicio = 0;
                            continue;
                        }

                        switch (opcionNombreServicio) {
                            case 1 -> nombreServicio = "Seguro de viaje (PERSONA)";
                            case 2 -> nombreServicio = "Guia privado (DIA)";
                            case 3 -> nombreServicio = "Transporte privado (DIA)";
                            case 4 -> nombreServicio = "Entrada a atracciones (PERSONA)";
                            case 5 -> nombreServicio = "Comidas incluidas (DIASxPERSONAS)";
                            default -> System.out.println("Error: opcion invalida. Intente nuevamente");
                        }
                    } while (opcionNombreServicio < 1 || opcionNombreServicio > 5);


                    double valorServicio = 0;
                    while (true) {
                        System.out.println("Ingrese el valor del servicio");
                        try {
                            valorServicio = sc.nextDouble();
                            sc.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Error: debe de ingresar un numero.");
                            sc.nextLine();
                        }
                    }

                    gestionServicioAdicional.agregarServicio(nombreServicio, valorServicio);
                    break;
                }
                case 2: {
                    System.out.println("-- Mostrar servicio --");
                    sc.nextLine();

                    gestionServicioAdicional.mostrarServicio();
                    break;
                }
                case 3: {
                    System.out.println("-- Actualizar valor de servicio --");
                    sc.nextLine();

                    System.out.println("Ingrese el codigo del servicio");
                    int codigo;
                    try {
                        codigo = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: El código debe ser un número.");
                        sc.nextLine();
                        break;
                    }

                    double valor = 0;
                    System.out.println("Ingrese el nuevo valor del servicio");
                    try {
                        valor = sc.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: debe de ingresar un número.");
                        sc.nextLine();
                        break;
                    }
                    gestionServicioAdicional.actualizarValorServicio(codigo, valor);
                    break;
                }
                case 4: {
                    System.out.println("-- Eliminar servicio adicional --");
                    sc.nextLine();

                    System.out.println("Ingrese el codigo del servicio");
                    int codigoEliminar;
                    try {
                        codigoEliminar = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: El código debe ser un número.");
                        sc.nextLine();
                        break;
                    }

                    gestionServicioAdicional.eliminarPaquete(codigoEliminar);
                    break;
                }

            }
        }while (opcionServicio != 5);
    }
}
