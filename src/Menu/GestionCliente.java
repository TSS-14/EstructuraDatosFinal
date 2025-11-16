package Menu;

import EstructuraNodos.ListaCliente;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionCliente {
    public static void menuCliente(ListaCliente gestionCliente, Scanner sc){
        int opcionCliente = 0;
        do {
            System.out.println("\n--GESTIÓN DE CLIENTES--");
            System.out.println("1. Registrar nuevo cliente");
            System.out.println("2. Buscar cliente");
            System.out.println("3. Mostrar clientes");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcionCliente = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Error: Debe ingresar un número.");
                sc.nextLine();
                continue;
            }

            switch (opcionCliente) {
                case 1:
                    System.out.println("-- Registro de cliente --");

                    String documento = "";
                    boolean documentoValido = false;
                    while (!documentoValido){
                        System.out.println("Ingrese el numero de documento:");
                        documento = sc.nextLine();

                        if (!documento.matches("\\d+")) {
                            System.out.println("Error, Solo se permite numeros. Intente nuevamente");
                            continue;
                        }

                        if (gestionCliente.existeClienteRepetido(documento)){
                            System.out.println("Error. Ya existe una persona registrada con ese ID");
                            continue;
                        }
                        documentoValido = true;
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
                            if (edad < 10 || edad > 95){
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
                    int opcionGenero = 0;
                    do {
                        System.out.println("\nSeleccione el genero");
                        System.out.println("1.Masculino");
                        System.out.println("2.Femenino");
                        System.out.println("Opcion:");
                        try {
                            opcionGenero = sc.nextInt();
                        } catch (InputMismatchException e){
                            System.out.println("Error: Debe ser un numero.");
                            sc.nextLine();
                            continue;
                        }

                        switch (opcionGenero) {
                            case 1 -> genero = "Masculino";
                            case 2 -> genero = "Femenino";
                            default -> System.out.println("Opcion invalida. Intente nuevamente");
                        }
                    } while (opcionGenero < 1 || opcionGenero > 2);

                    sc.nextLine();
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
                    int opcionTipoCliente = 0;
                    do {
                        System.out.println("\nSeleccione tipo de cliente");
                        System.out.println("1.Particular");
                        System.out.println("2.Empresa");
                        System.out.println("Opción:");
                        try {
                            opcionTipoCliente = sc.nextInt();
                        } catch (InputMismatchException e){
                            System.out.println("Error: Debe ser un numero.");
                            sc.nextLine();
                            continue;
                        }

                        switch (opcionTipoCliente) {
                            case 1 -> tipoCliente = "Particular";
                            case 2 -> tipoCliente = "Empresa";
                            default -> System.out.println("Opcion invalida. Intente nuevamente");
                        }
                    } while (opcionTipoCliente < 1 || opcionTipoCliente > 2);

                    gestionCliente.agregarCliente(documento, tipoCliente, telefono, correo, genero, edad, nombre);
                    break;
                case 2:
                    System.out.println("-- Buscar Cliente --");

                    System.out.println("Ingrese el documento del cliente:");
                    gestionCliente.buscarMostrarCliente(sc.nextLine());
                    break;
                case 3:
                    System.out.println("-- Cliente Listados --");
                    gestionCliente.mostrarClientes();
                    break;
                case 4:
                    System.out.println("-- Eliminar Cliente --");

                    System.out.println("Ingrese el documento del cliente:");
                    gestionCliente.eliminarCliente(sc.nextLine());
                    break;
            }
        } while (opcionCliente != 5);

    }
}
