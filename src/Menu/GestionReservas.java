package Menu;

import EstructuraNodos.*;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionReservas {
    public static void  menuReservas(ListaReservas gestionReserva, ListaCliente gestionCliente,
                                     ListaPaquete gestionPaquete, ListaServicioAdicional gestionServicioAdicional,
                                     ListaCodigoPromocion gestionCodigoPromocion,
                                     ColaClientes colaAtencion, PilaServicios pilaServicios,
                                     Scanner sc){
        int opcionReserva = 0;
        do {
            System.out.println("\n--GESTION DE RESERVAS--");
            System.out.println("1. Crear nueva reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Mostrar todas las reservas");
            System.out.println("4. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcionReserva = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un numero.");
                sc.nextLine();
                continue;
            }

            switch (opcionReserva) {
                case 1:{
                    System.out.println("-- Registro de reserva --");

                    System.out.println("Ingrese el documento del cliente: ");
                    String documento = sc.nextLine();
                    NodoCliente cliente = gestionCliente.buscarCliente(documento);

                    if (cliente == null) {
                        System.out.println("No se encontro cliente. Intente nuevamente.");
                        break;
                    }

                    // Agregar automáticamente a la cola de atención
                    colaAtencion.encolar(cliente);

                    System.out.println("Ingrese el código del paquete:");
                    int codigoPaquete = sc.nextInt();
                    NodoPaquete paquete = gestionPaquete.buscarPaquete(codigoPaquete);

                    if (paquete == null) {
                        System.out.println("No se encontro el paquete. Intente nuevamente.");
                        break;
                    }

                    int opcionCodigo = 0;
                    String codigoPromocional = "";
                    System.out.println("¿Desea ingresar un codigo promocional?");
                    System.out.println("1.SI");
                    System.out.println("2.NO");
                    System.out.println("Opcion: ");
                    try {
                        opcionCodigo = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un número.");
                        sc.nextLine();
                    }
                    if (opcionCodigo == 1) {
                        System.out.println("Ingrese el código promocional:");
                        codigoPromocional = sc.nextLine();
                    } else if (opcionCodigo != 2){
                        System.out.println("Opcion invalida. Por favor intente nuevamente");
                    }

                    NodoServicioAdicional[] serviciosAdicionales = new NodoServicioAdicional[5]; // Máximo 5 servicios
                    int contadorServicios = 0;
                    int eleccionServicio = 0;
                    do {
                        System.out.println("¿Desea agregar un servicio adicional?");
                        System.out.println("1.SI");
                        System.out.println("2.NO");
                        System.out.println("Opción");
                        try {
                            eleccionServicio = sc.nextInt();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debe ingresar un numero.");
                            sc.nextLine();
                            continue;
                        }
                        if (eleccionServicio == 1) {
                            if (contadorServicios >= serviciosAdicionales.length) {
                                System.out.println("No puede agregar más servicios adicionales.");
                                break;
                            }
                            System.out.println("Ingrese el codigo del servicio adicional:");
                            int codigoServicio = sc.nextInt();
                            NodoServicioAdicional servicio = gestionServicioAdicional.buscarServicio(codigoServicio);

                            if (servicio == null) {
                                System.out.println("No se encontro el servicio adicional. Intente nuevamente.");
                            } else {
                                serviciosAdicionales[contadorServicios++] = servicio;
                                // Registrar automáticamente el servicio en la pila
                                pilaServicios.apilar(servicio.getNombre());
                                System.out.println("Servicio adicional agregado:");
                                System.out.println("\nNombre del servicio: "+servicio.getNombre() +
                                        "\nValor del servicio: "+servicio.getPrecio());
                            }
                        }
                    } while (eleccionServicio != 2);

                    int cantidadPersona = 0;
                    System.out.println("Ingrese la cantidad de personas");
                    try {
                        cantidadPersona = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ser un numero.");
                        sc.nextLine();
                        continue;
                    }

                    double montoTotal = ListaReservas.calcularMontoReserva(
                            paquete,
                            serviciosAdicionales,
                            contadorServicios,
                            cantidadPersona,
                            opcionCodigo,
                            codigoPromocional,
                            gestionCodigoPromocion,
                            gestionReserva,
                            cliente
                    );

                    // Descontar cupos disponibles del paquete
                    int cuposActuales = paquete.getPlazaDiponible();
                    paquete.setPlazaDiponible(cuposActuales - cantidadPersona);

                    // Crear la reserva con el monto de descuento calculado
                    gestionReserva.crearReserva(cliente, paquete, serviciosAdicionales,
                            cantidadPersona, "Pendiente", montoTotal,
                            ListaReservas.ultimoMontoDescuento, 0.0,
                            codigoPromocional, LocalDate.now(), "");

                    // Desencolar cliente después de ser atendido
                    colaAtencion.desencolar();
                    break;
                }

                case 2: {
                    System.out.println("-- Cancelar reserva --");

                    int codigo = 0;
                    System.out.println("Ingrese el codigo de la reservar");
                    try {
                        codigo = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e){
                        System.out.println("Error: Debe ingresar un numero");
                        sc.nextLine();
                        continue;
                    }

                    gestionReserva.cancelarReserva(codigo);
                    break;
                }

                case 3:
                    System.out.println("-- Mostrar todas las reservas --");
                    gestionReserva.mostrarReservas();
                    break;

            }
        }while (opcionReserva != 4);
    }
}
