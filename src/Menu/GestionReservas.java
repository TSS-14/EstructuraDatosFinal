package Menu;

import EstructuraNodos.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionReservas {
    public static void  menuReservas(ListaReservas gestionReserva, ListaCliente gestionCliente,
                                     ListaPaquete gestionPaquete, ListaServicioAdicional gestionServicioAdicional, Scanner sc){
        int opcionReserva = 0;
        do {
            System.out.println("\n--GESTION DE RESERVAS--");
            System.out.println("1.Crear nueva reserva");
            System.out.println("2.Confirmar reserva");
            System.out.println("3.Cancelar reserva");
            System.out.println("4.Mostrar todas las reservas");
            System.out.println("5.Volver al menu principal");
            try {
                opcionReserva = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
            } catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un numero.");
                sc.nextLine();
                continue;
            }

            switch (opcionReserva){
                case 1:
                    System.out.println("-- Registro de reserva --");
                    //sc.nextLine(); // Ya se limpió el buffer arriba

                    //Buscamos cliente
                    System.out.println("Ingrese el documento del cliente: ");
                    String documento = sc.nextLine();
                    NodoCliente cliente = gestionCliente.buscarCliente(documento);

                    if (cliente == null){
                        System.out.println("No se encontro cliente. Intente nuevamente.");
                        break;
                    }

                    //Buscamos codigo de paquete
                    System.out.println("Ingrese el código del paquete:");
                    String codigoPaquete = sc.nextLine();
                    NodoPaquete paquete = gestionPaquete.buscarPaquete(codigoPaquete);

                    if (paquete == null){
                        System.out.println("No se encontro el paquete. Intente nuevamente.");
                        break;
                    }

                    NodoServicioAdicional servicio = null;
                    int eleccionServicio = 0;
                    do {
                        System.out.println("Desea agregar un servicio adicional?");
                        System.out.println("1.SI");
                        System.out.println("2.NO");
                        System.out.println("Opción");
                        try {
                            eleccionServicio = sc.nextInt();
                            sc.nextLine(); // Limpiar buffer
                        } catch (InputMismatchException e){
                            System.out.println("Error: Debe ingresar un numero.");
                            sc.nextLine();
                            continue;
                        }
                        if (eleccionServicio == 1){
                            //Buscamos codigo de servicioAdicional
                            System.out.println("Ingrese el codigo del servicio adicional:");
                            String codigoServicio = sc.nextLine();
                            servicio = gestionServicioAdicional.buscarServicio(codigoServicio);

                            if (servicio == null){
                                System.out.println("No se encontro el servicio adicional. Intente nuevamente.");
                                break;
                            }
                        }else {
                            System.out.println("");
                        }
                    }while (eleccionServicio != 1 && eleccionServicio != 2);


                    //Ingresa cantidad de personas
                    int cantidadPersona = 0;
                    System.out.println("Ingrese la cantidad de personas");
                    try {
                        cantidadPersona = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e){
                        System.out.println("Error: Debe ser un numero.");
                        sc.nextLine();
                        continue;
                    }

                    //calcular monto total
                    double precioPaquete = paquete.getPrecio();
                    double precioServicio = (servicio != null) ? servicio.getPrecio() : 0;
                    double montoTotal = precioPaquete * cantidadPersona + precioServicio;
                    System.out.println("Monto total de la reserva: $"+montoTotal);

                    gestionReserva.crearReserva(cliente,paquete,servicio,cantidadPersona,"Pendiente", montoTotal);
                    break;

                case 2: {
                    System.out.println("-- Confirmar reserva --");
                    //sc.nextLine(); // Ya se limpió el buffer arriba

                    int codigo = 0;
                    System.out.println("Ingrese el codigo de la reserva");
                    try {
                        codigo = sc.nextInt();
                        sc.nextLine(); // Limpiar buffer
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe de ser un numero.");
                        sc.nextLine();
                        continue;
                    }

                    gestionReserva.confirmarReserva(codigo);
                    break;
                }

                case 3: {
                    System.out.println("-- Cancelar reserva --");
                    //sc.nextLine(); // Ya se limpió el buffer arriba

                    int codigo = 0;
                    System.out.println("Ingrese el codigo de la reservar");
                    try {
                        codigo = sc.nextInt();
                        sc.nextLine(); // Limpiar buffer
                    } catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un numero");
                        sc.nextLine();
                        continue;
                    }

                    gestionReserva.cancelarReserva(codigo);
                    break;
                }

                case 4:
                    System.out.println("-- Mostrar todas las reservas --");
                    gestionReserva.mostrarReservas();
                    break;

            }
        }while (opcionReserva != 5);
    }

}