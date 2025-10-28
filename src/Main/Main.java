package Main;

import Estructuras.ListaCliente;

import java.util.Scanner;
import java.util.function.BinaryOperator;

public class Main {
    public static void main(String[] args) {
        ListaCliente GestionCliente = new ListaCliente();
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
                case 1:
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

                        switch (opcionCliente){
                            case 1:
                                System.out.println("-- Registro de cliente --");
                                sc.nextLine();

                                System.out.println("Ingrese el numero de documento:");
                                String documento = sc.nextLine();

                                System.out.println("Ingrese el nombre: ");
                                String nombre = sc.nextLine();

                                System.out.println("Ingrese la edad: ");
                                int edad = sc.nextInt();
                                sc.nextLine();

                                String genero = "";
                                int opcionGenero;
                                do {
                                    System.out.println("\nSeleccione el genero");
                                    System.out.println("1.Masculino");
                                    System.out.println("2.Femenino");
                                    System.out.println("Opcion:");
                                    opcionGenero = sc.nextInt();
                                    sc.nextLine();

                                    switch (opcionGenero){
                                        case 1 -> genero = "Masculino";
                                        case 2 -> genero = "Femenino";
                                        default -> System.out.println("Opcion invalida. Intente nuevamente");
                                    }
                                }while (opcionGenero < 1 || opcionGenero > 2);

                                System.out.println("Ingrese un correo electronico:");
                                String correo = sc.nextLine();

                                System.out.println("Ingrese un numero de telefono");
                                String telefono = sc.nextLine();


                                Boolean valido = false;

                                if (telefono.length() != 10){
                                    System.out.println("El numero debe tener 10 digitos. Intente nuevamente");
                                } else if (!telefono.matches("\\d+")) {
                                    System.out.println("Solo se permite numeros. Intente nuevamente");
                                }else {
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

                                    switch (opcionTipoCliente){
                                        case 1 -> tipoCliente = "Particular";
                                        case 2 -> tipoCliente = "Empresa";
                                        default -> System.out.println("Opcion invalida. Intente nuevamente");
                                    }
                                }while (opcionTipoCliente < 1 || opcionTipoCliente > 2);

                                GestionCliente.agregarCliente(documento,tipoCliente,telefono,correo,genero,edad,nombre);
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
                    }while (opcionCliente != 5);
            }
        }while (opcion != 7);
    }
}
