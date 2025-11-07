package EstructuraNodos;

import EstructuraNodos.ListaReservas; // Necesario para el reporte de monto por edad

public class ListaCliente {
    private NodoCliente inicio;

    public ListaCliente() {
        inicio = null;
    }

    // Metodo si esta vacio
    public boolean esVacio() {
        return inicio == null;
    }


    // Metodo para agregar cliente
    public void agregarCliente(String documento, String tipoCliente, String telefono, String correo,
            String genero, int edad, String nombre) {
        // Verifica si ya existe
        if (buscarCliente(documento) != null) {
            System.out.println("Error, el numero de documento ya esta registrado");
            return;
        }
        NodoCliente nuevo = new NodoCliente(documento, tipoCliente, telefono, correo, genero, edad, nombre);
        if (esVacio()) {
            inicio = nuevo;
            nuevo.siguiente = null;
        } else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        System.out.println("Cliente agregado correctamente");
    }

    // Meotodo para buscar repeticiones (devuelve el nodo)
    public NodoCliente buscarCliente(String documento) {
        if (esVacio())
            return null;

        NodoCliente aux = inicio;
        while (aux != null) {
            if (aux.documento.equalsIgnoreCase(documento)) {
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    // Metodo para eliminar cliente
    public void eliminarCliente(String documento) {
        if (esVacio()) {
            System.out.println("La lista esta vacia!");
            return;
        }

        NodoCliente actual = inicio;
        NodoCliente anterior = null;

        while (actual != null) {
            if (actual.documento.equalsIgnoreCase(documento)) {
                if (anterior == null) {
                    inicio = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                System.out.println("Cliente eliminado correctamente");
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        System.out.println("Cliente no encontrado");
    }

    // Metodo para buscar y mostrar el cliente por numero de documento
    public void buscarMostrarCliente(String documento) {
        NodoCliente t = buscarCliente(documento);
        if (t != null) {
            System.out.println("!Cliente encontrado!");
            System.out.println("---------------");
            System.out.println("Documento: " + t.documento + "\nNombre: " + t.nombre +
                    "\nEdad: " + t.edad + "\nGenero: " + t.genero + "\nCorreo: " + t.correo +
                    "\nTelefono: " + t.telefono + "\nTipo de cliente: " + t.tipoCliente);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }
    
    // Metodo para mostrar todos los clientes
    public void mostrarClientes() {
        if (esVacio()) {
            System.out.println("La lista esta vacia!");
            return;
        }
        
        NodoCliente t = inicio;
        System.out.println("\n-- LISTA DE CLIENTES --");

        int contador = 1;
        while (t != null) {
            System.out.println("Cliente # " + contador++);
            System.out.println("---------------");
            System.out.println("Documento: " + t.documento + "\nNombre: " + t.nombre +
                    "\nEdad: " + t.edad + "\nGenero: " + t.genero + "\nCorreo: " + t.correo +
                    "\nTelefono: " + t.telefono + "\nTipo de cliente: " + t.tipoCliente);
            t = t.siguiente;
        }
    }

    
    // Metodo auxiliar para obtener la edad, usado por el reporte de monto recaudado
    public int obtenerEdadPorDocumento(String documento) {
        NodoCliente actual = inicio;
        while (actual != null) {
            if (actual.documento.equals(documento)) {
                return actual.edad;
            }
            actual = actual.siguiente;
        }
        return -1; // Cliente no encontrado
    }

    // Metodo auxiliar para mostrar datos, usado por el reporte Mayor/Menor Gasto en ListaReservas
    public void mostrarDatosClientePorDocumento(String documentoBuscado) {
        NodoCliente actual = inicio;
        
        while (actual != null) {
            if (actual.documento.equals(documentoBuscado)) { 
                System.out.println("    Nombre: " + actual.nombre);
                System.out.println("    Documento: " + actual.documento);
                System.out.println("    Edad: " + actual.edad);
                System.out.println("    Teléfono: " + actual.telefono);
                System.out.println("    Tipo: " + actual.tipoCliente);
                return; 
            }
            actual = actual.siguiente;
        }
        
        System.out.println("    ERROR: Cliente no encontrado con documento: " + documentoBuscado);
    }


    // Metodo reporte : Clientes atendidos por genero
    public void contarClientesPorGenero() {
        if (esVacio()) {
            System.out.println("No hay clientes registrados para generar el reporte.");
            return;
        }

        int masculino = 0;
        int femenino = 0;
        int otros = 0;

        NodoCliente actual = inicio;
        while (actual != null) {

            String genero = actual.genero.toLowerCase();

            if (genero.equals("masculino")) {
                masculino++;
            } else if (genero.equals("femenino")) {
                femenino++;
            } else {
                otros++;
            }

            actual = actual.siguiente;
        }

        System.out.println("\n=========================================");
        System.out.println("📊 REPORTE DE CLIENTES ATENDIDOS POR GÉNERO");
        System.out.println("=========================================");
        System.out.println("Total Clientes Masculinos: " + masculino);
        System.out.println("Total Clientes Femeninos: " + femenino);

        if (otros > 0) {
            System.out.println("Clientes con género no especificado/otro: " + otros);
        }
        System.out.println("-----------------------------------------");
        System.out.println("Total de clientes en el sistema: " + (masculino + femenino + otros));
        System.out.println("-----------------------------------------");
    }

    // Metodo reporte : Clientes por rango de edad
    public void contarClientesPorRangoEdad() {
        if (esVacio()) {
            System.out.println("No hay clientes registrados para generar el reporte.");
            return;
        }

        int rangoMenores = 0; // < 18
        int rangoAdultoJoven = 0; // 18-40
        int rangoAdulto = 0; // 41-65
        int rangoMayor = 0; // > 65

        NodoCliente actual = inicio;
        while (actual != null) {
            int edad = actual.edad;

            if (edad < 18) {
                rangoMenores++;
            } else if (edad >= 18 && edad <= 40) {
                rangoAdultoJoven++;
            } else if (edad >= 41 && edad <= 65) {
                rangoAdulto++;
            } else { // edad > 65
                rangoMayor++;
            }
            actual = actual.siguiente;
        }

        System.out.println("\n=========================================");
        System.out.println("👴 REPORTE DE CLIENTES POR RANGO DE EDAD");
        System.out.println("=========================================");
        System.out.println("Clientes (<18 años): " + rangoMenores);
        System.out.println("Clientes (18-40 años): " + rangoAdultoJoven);
        System.out.println("Clientes (41-65 años): " + rangoAdulto);
        System.out.println("Clientes (>65 años): " + rangoMayor);
        System.out.println("-----------------------------------------");
    }

    // Metodo reporte: Monto recaudado por edad
    public void reporteMontoRecaudadoPorEdad(ListaReservas listaReservas) {
        if (inicio == null || listaReservas.esVacio()) {
            System.out.println(
                    "No hay suficientes datos (clientes o reservas) para generar el reporte de facturación por edad.");
            return;
        }

        double[] montosPorRango = new double[4];
        double totalRecaudadoGeneral = 0.0;

        NodoReserva reservaActual = listaReservas.getInicio();
        while (reservaActual != null) {
            if (reservaActual.estado.equalsIgnoreCase("Confirmado")) {

                int edad = this.obtenerEdadPorDocumento(reservaActual.documentoCliente); 
                double monto = reservaActual.montoTotal;

                if (edad != -1) { 
                    totalRecaudadoGeneral += monto;

                    if (edad < 18) {
                        montosPorRango[0] += monto;
                    } else if (edad >= 18 && edad <= 40) {
                        montosPorRango[1] += monto;
                    } else if (edad >= 41 && edad <= 65) {
                        montosPorRango[2] += monto;
                    } else { // edad > 65
                        montosPorRango[3] += monto;
                    }
                }
            }
            reservaActual = reservaActual.siguiente;
        }

        System.out.println("\n=========================================");
        System.out.println(" REPORTE: MONTO RECAUDADO POR RANGO DE EDAD");
        System.out.println("=========================================");
        System.out.printf("Total Recaudado General (Confirmado): $%,.2f%n", totalRecaudadoGeneral);
        System.out.println("-----------------------------------------");
        System.out.printf("Menores (<18): $%,.2f%n", montosPorRango[0]);
        System.out.printf("Jóvenes (18-40): $%,.2f%n", montosPorRango[1]);
        System.out.printf("Adultos (41-65): $%,.2f%n", montosPorRango[2]);
        System.out.printf("Mayores (>65): $%,.2f%n", montosPorRango[3]);
        System.out.println("-----------------------------------------");
    }
}