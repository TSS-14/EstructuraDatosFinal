package Main;

import Estructuras.ListaCliente;
import Estructuras.ListaPaquete;

import java.util.Scanner;
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        ListaCliente GestionCliente = new ListaCliente();
        ListaPaquete GestionPaquetes = new ListaPaquete();
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("===========================");
            System.out.println("AGENCIA DE VIAJES ECORUTAS");
            System.out.println("===========================");
            System.out.println("1.Gestión de Clientes");
            System.out.println("2.Gestión de Paquetes Turisticos");
            System.out.println("3.Gestión de Reservas");
            System.out.println("4.Gestión de pagos");
            System.out.println("5.Reportes y estadísticas");
            System.out.println("6.Simulación de Colas y Pilas");
            System.out.println("7.Salir");
            System.out.println("Ingrese su opción:");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1: {
                    int opcionCliente = 0;
                    do {
                        System.out.println("\n--GESTIÓN DE CLIENTES--");
                        System.out.println("1.Registrar nuevo cliente");
                        System.out.println("2.Buscar cliente");
                        System.out.println("3.Mostrar clientes");
                        System.out.println("4.Eliminar cliente");
                        System.out.println("5.Volver al menú principal");
                        System.out.println("Seleccione una opción: ");
                        opcionCliente = sc.nextInt();

                        switch (opcionCliente) {
                            case 1:
                                System.out.println("-- Registro de cliente --");
                                sc.nextLine();

                                String documento = "";
                                boolean documentoValido = false;
                                while (!documentoValido){
                                    System.out.println("Ingrese el numero de documento:");
                                    documento = sc.nextLine();

                                    if (!documento.matches("\\d+")) {
                                        System.out.println("Error, Solo se permite numeros. Intente nuevamente");
                                    }
                                    else {
                                        documentoValido = true;
                                    }
                                }

                                String nombre = "";
                                boolean nombreValido = false;

                                while (!nombreValido){
                                    System.out.println("Ingrese su nombre:");
                                    nombre = sc.nextLine();

                                    if (nombre.matches("\\d+")){
                                        System.out.println("Error, Solo se permite letras. Intente nuevamente");
                                    }else {
                                        nombreValido = true;
                                    }
                                }

                                int edad = 0;
                                boolean edadValida = false;

                                while (!edadValida){
                                    System.out.println("Ingrese su edad:");
                                    if (sc.hasNextInt()){
                                        edad = sc.nextInt();
                                        sc.nextLine();
                                        if (edad < 0 || edad > 100){
                                            System.out.println("Error, edad invalida. Intente nuevamente.");
                                        }else {
                                            edadValida = true;
                                        }
                                    }else {
                                        System.out.println("Error, debe ingresar un numero.");
                                        sc.nextLine();
                                    }
                                }

                                String genero = "";
                                int opcionGenero;
                                do {
                                    System.out.println("\nSeleccione el genero");
                                    System.out.println("1.Masculino");
                                    System.out.println("2.Femenino");
                                    System.out.println("Opcion:");
                                    opcionGenero = sc.nextInt();
                                    sc.nextLine();

                                    switch (opcionGenero) {
                                        case 1 -> genero = "Masculino";
                                        case 2 -> genero = "Femenino";
                                        default -> System.out.println("Opcion invalida. Intente nuevamente");
                                    }
                                } while (opcionGenero < 1 || opcionGenero > 2);

                                System.out.println("Ingrese un correo electronico:");
                                String correo = sc.nextLine();

                                boolean valido = false;
                                String telefono = "";
                                while (!valido){
                                    System.out.println("Ingrese un numero de telefono");
                                    telefono = sc.nextLine();

                                    if (!telefono.matches("\\d+"))
                                        System.out.println("Error, Solo se permite numeros. Intente nuevamente");

                                    else if (telefono.length() != 10)
                                        System.out.println("Error, El numero debe de tener 10 digitos. Intente nuevamente");

                                    else
                                        valido = true;
                                }

                                String tipoCliente = "";
                                int opcionTipoCliente;
                                do {
                                    System.out.println("\nSeleccione tipo de cliente");
                                    System.out.println("1.Particular");
                                    System.out.println("2.Empresa");
                                    System.out.println("Opción:");
                                    opcionTipoCliente = sc.nextInt();

                                    switch (opcionTipoCliente) {
                                        case 1 -> tipoCliente = "Particular";
                                        case 2 -> tipoCliente = "Empresa";
                                        default -> System.out.println("Opcion invalida. Intente nuevamente");
                                    }
                                } while (opcionTipoCliente < 1 || opcionTipoCliente > 2);

                                GestionCliente.agregarCliente(documento, tipoCliente, telefono, correo, genero, edad, nombre);
                                break;
                            case 2:
                                System.out.println("-- Buscar Cliente --");
                                sc.nextLine();

                                System.out.println("Ingrese el documento del cliente:");
                                GestionCliente.buscarMostrarCliente(sc.nextLine());
                                break;
                            case 3:
                                System.out.println("-- Cliente Listados --");
                                GestionCliente.mostrarClientes();
                                break;
                        }
                    } while (opcionCliente != 5);
                break;
                }
                case 2:{
                    int opcionCliente = 0;
                    do {
                        System.out.println("\n--GESTIÓN DE PAQUETES TURISTICOS--");
                        System.out.println("1.Registrar nuevo paquete");
                        System.out.println("2.Buscar paquete por codigo");
                        System.out.println("3.Mostrar todos los paquetes");
                        System.out.println("4.Eliminar paquete");
                        System.out.println("5.Volver al menú principal");
                        System.out.println("Seleccione una opción");
                        opcionCliente = sc.nextInt();

                        switch (opcionCliente){

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
                                        System.out.println("Error, Solo se permite letras. Intente nuevamente");
                                    }else {
                                        destinoValido = true;
                                    }
                                }

                                int duracion = 0;
                                boolean diasValidos = false;
                                while (!diasValidos) {
                                    System.out.println("Ingrese el numero de dias:");

                                    if (sc.hasNextInt()){
                                        duracion = sc.nextInt();
                                        sc.nextLine();

                                        if (duracion <= 0){
                                            System.out.println("Error, el numero de dias debe ser mayor a cero. Intente nuevamente");
                                        }else {
                                            diasValidos = true;
                                        }
                                    }else {
                                        System.out.println("Error, debe ingresar solo numeros. Intente nuevamente");
                                        sc.nextLine();
                                    }
                                }

                                String tipoPaquete = "";
                                int opcionPaquete = 0;
                                do {
                                    System.out.println("\nSeleccione el tipo de paquete");
                                    System.out.println("1.Nacional");
                                    System.out.println("2.Internacional");
                                    System.out.println("Opción:");
                                    opcionPaquete = sc.nextInt();
                                    sc.nextLine();

                                    switch (opcionPaquete){
                                        case 1 -> tipoPaquete = "Nacional";
                                        case 2 -> tipoPaquete = "Internacional";
                                        default -> System.out.println("Opcion invalida. Intente nuevamente");
                                    }
                                }while (opcionPaquete < 1 || opcionPaquete > 2);

                                System.out.println("Ingrese el precio del paquete");
                                double precioPaquete = sc.nextDouble();

                                System.out.println("Ingrese las plazas totales");
                                int plazasTotales = sc.nextInt();

                                System.out.println("Ingrese las plazas disponibles");
                                int plazasDisponibles = sc.nextInt();

                                GestionPaquetes.agregarPaquete(codigoPaquete,destino,duracion,tipoPaquete,
                                                                precioPaquete,plazasTotales,plazasDisponibles);
                                break;

                            case 2:
                                System.out.println("-- Busqueda de Paquete");
                                sc.nextLine();

                                System.out.println("Ingrese el codigo del paquete");
                                String codigo = sc.nextLine();

                                GestionPaquetes.buscarPaquete(codigo);
                                break;
                            case 3:
                                System.out.println("-- Mostrar todos los paquetes --");
                                sc.nextLine();

                                GestionPaquetes.mostrarPaquetes();
                                break;
                            case 4:
                                System.out.println("-- Eliminar Paquete por Codigo --");
                                sc.nextLine();

                                System.out.println("Ingrese el codigo del paquete:");
                                String eliminarCodigo = sc.nextLine();

                                GestionPaquetes.eliminarPaquete(eliminarCodigo);
                                break;
                        }
                    }while (opcionCliente != 5);
                    break;
                }

            }
        }while (opcion != 7);
    }
}
