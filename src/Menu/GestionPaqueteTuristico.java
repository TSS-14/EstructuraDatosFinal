// Java
package Menu;
import EstructuraNodos.ListaCodigoPromocion;
import EstructuraNodos.ListaPaquete;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionPaqueteTuristico {
    public static void menuPaqueteTuristico(ListaPaquete gestionPaquete, ListaCodigoPromocion gestionPromocion,
                                            Scanner sc){
        int opcionPaquete = 0;
        do {
            System.out.println("\n--GESTIÓN DE PAQUETES TURISTICOS Y PROMOCIONES--");
            System.out.println("1. Registrar nuevo paquete");
            System.out.println("2. Buscar paquete por codigo");
            System.out.println("3. Mostrar todos los paquetes");
            System.out.println("4. Eliminar paquete");
            System.out.println("5. Registrar codigo promocional");
            System.out.println("6. Mostrar todos los codigos promocionales");
            System.out.println("7. Eliminar codigo promocional");
            System.out.println("8. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");
            try {
                opcionPaquete = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número.");
                sc.nextLine();
                continue;
            }

            switch (opcionPaquete){
                case 1:
                    System.out.println("-- Registro de paquetes --");

                    String destino = "";
                    boolean destinoValido = false;

                    while (!destinoValido){
                        System.out.println("Ingrese el destino del paquete:");
                        destino = sc.nextLine();

                        if (destino.matches("\\d+")){
                            System.out.println("Error: Solo se permite letras. Intente nuevamente.");
                        }else {
                            destinoValido = true;
                        }
                    }

                    int duracion = 0;
                    boolean diasValidos = false;
                    while (!diasValidos){
                        System.out.println("Ingrese el número de días:");
                        try {
                            duracion = sc.nextInt();
                        } catch (InputMismatchException e){
                            System.out.println("Error: debe ingresar solo numeros, Intente nuevamente.");
                            sc.nextLine();
                            continue;
                        }
                        sc.nextLine();

                        if (duracion <= 0){
                            System.out.println("Error: el número de días debe ser mayor a cero. Intente nuevamente");
                        }else {
                            diasValidos = true;
                        }
                    }

                    String fechaInicioStr = "";
                    LocalDate fechaInicioViaje = null;
                    LocalDate finFechaViaje = null;
                    boolean fechaValida = false;
                    do {
                        System.out.println("Ingrese la fecha de inicio del viaje (AAAA-MM-DD):");
                        fechaInicioStr = sc.nextLine();
                        try {
                            fechaInicioViaje = LocalDate.parse(fechaInicioStr);
                            finFechaViaje = fechaInicioViaje.plusDays(duracion);
                            System.out.println("La fecha de fin del viaje será: " + finFechaViaje);
                            fechaValida = true;
                        } catch (Exception e) {
                            System.out.println("Error: formato de fecha inválido. Use AAAA-MM-DD.");
                        }
                    } while (!fechaValida);

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
                            default -> System.out.println("Opción invalida. Intente nuevamente");
                        }
                    }while (opcionTipoPaquete < 1 || opcionTipoPaquete > 2);

                    double precioPaquete = 0;
                    System.out.println("Ingrese el precio del paquete");
                    try {
                        precioPaquete = sc.nextDouble();
                    } catch (InputMismatchException e){
                        System.out.println("Error: debe ingresar un número decimal.");
                        sc.nextLine();
                    }
                    sc.nextLine();

                    int plazasTotales = 0;
                    System.out.println("Ingrese las plazas totales");
                    try {
                        plazasTotales = sc.nextInt();
                    } catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un numero.");
                        sc.nextLine();
                    }
                    sc.nextLine();

                    int plazaDisponible = plazasTotales;


                    gestionPaquete.agregarPaquete(destino, duracion, tipoPaquete,
                            precioPaquete, plazasTotales, plazaDisponible, fechaInicioViaje, finFechaViaje);
                    break;

                case 2:
                    System.out.println("-- Busqueda de Paquete --");

                    System.out.println("Ingrese el codigo del paquete:");
                    int codigo = 0;
                    try {
                        codigo = sc.nextInt();
                    } catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número.");
                        sc.nextLine();
                        break;
                    }
                    sc.nextLine();

                    gestionPaquete.buscarPaquete(codigo);
                    break;

                case 3:
                    System.out.println("-- Mostrar todos los paquetes --");
                    gestionPaquete.mostrarPaquetes();
                    break;
                case 4:
                    System.out.println("-- Eliminar paquete por codigo --");

                    System.out.println("Ingrese el codigo del paquete:");
                    int eliminarCodigo = 0;
                    try {
                        eliminarCodigo = sc.nextInt();
                    } catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número.");
                        sc.nextLine();
                        break;
                    }
                    sc.nextLine();

                    gestionPaquete.eliminarPaquete(eliminarCodigo);
                    break;

                case 5:
                    System.out.println("-- Agregar codigo promocional --");

                    System.out.println("Ingrese el codigo promocional: ");
                    String promocionCodigo = sc.nextLine();

                    System.out.println("Ingrese la descripcion del codigo: ");
                    String descripcion = sc.nextLine();

                    System.out.println("Ingrese el procentaje de descuento: ");
                    double descuento = 0;
                    try {
                        descuento = sc.nextDouble();
                    } catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un numero");
                        sc.nextLine();
                        break;
                    }
                    sc.nextLine();
                    gestionPromocion.crearCodigo(promocionCodigo, descripcion, descuento);
                    break;
                case 6:
                    gestionPromocion.mostrarCodigo();
                    break;
                case 7:
                    System.out.println("Ingrese el codigo a eliminar: ");
                    String codigoPromocion = sc.nextLine();

                    gestionPromocion.eliminarCodigo(codigoPromocion);
                    break;
            }
        }while (opcionPaquete != 8);
    }
}


