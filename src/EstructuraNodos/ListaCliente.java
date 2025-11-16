package EstructuraNodos;

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

    //Metodo para clientes con el mismo ID
    public boolean existeClienteRepetido(String documento){
        NodoCliente actual = inicio;
        while (actual != null){
            if (actual.documento.equalsIgnoreCase(documento)){
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
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
            System.out.println("No hay clientes registrados aún");
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
            System.out.println("No hay clientes registrados aún");
            return;
        }
        
        NodoCliente t = inicio;
        System.out.println("\n-- LISTA DE CLIENTES --");

        int contador = 1;
        while (t != null) {
            System.out.println("---------------");
            System.out.println("Cliente # " + contador++);
            System.out.println("---------------");
            System.out.println("Documento: " + t.documento + "\nNombre: " + t.nombre +
                    "\nEdad: " + t.edad + "\nGenero: " + t.genero + "\nCorreo: " + t.correo +
                    "\nTelefono: " + t.telefono + "\nTipo de cliente: " + t.tipoCliente);
            t = t.siguiente;
        }
    }

    //Metodo 12 de reportes
    public void clientesPorGenero() {
        int masculino = 0, femenino = 0, otro = 0;
        NodoCliente aux = inicio;
        while (aux != null) {
            if (aux.genero.equalsIgnoreCase("Masculino") || aux.genero.equalsIgnoreCase("M")) {
                masculino++;
            } else if (aux.genero.equalsIgnoreCase("Femenino") || aux.genero.equalsIgnoreCase("F")) {
                femenino++;
            } else {
                otro++;
            }
            aux = aux.siguiente;
        }

        int total = masculino + femenino + otro;
        System.out.println("\n=== CLIENTES POR GÉNERO ===");
        System.out.println("Total de clientes: " + total);
        if (total > 0) {
            System.out.println("Masculino: " + masculino + " (" + String.format("%.2f", masculino * 100.0 / total) + "%)");
            System.out.println("Femenino: " + femenino + " (" + String.format("%.2f", femenino * 100.0 / total) + "%)");
            if (otro > 0) {
                System.out.println("Otro: " + otro + " (" + String.format("%.2f", otro * 100.0 / total) + "%)");
            }
        }
    }

    //Metodo 13 de clientes
    public void estadisticasPorEdad() {
        int menores18 = 0, entre18y30 = 0, entre31y50 = 0, entre51y65 = 0, mayores65 = 0;
        NodoCliente aux = inicio;

        while (aux != null) {
            int edad = aux.edad;
            if (edad < 18) {
                menores18++;
            } else if (edad >= 18 && edad <= 30) {
                entre18y30++;
            } else if (edad >= 31 && edad <= 50) {
                entre31y50++;
            } else if (edad >= 51 && edad <= 65) {
                entre51y65++;
            } else {
                mayores65++;
            }
            aux = aux.siguiente;
        }

        int total = menores18 + entre18y30 + entre31y50 + entre51y65 + mayores65;
        System.out.println("\n=== CLIENTES POR RANGO DE EDAD ===");
        System.out.println("Total de clientes: " + total);
        if (total > 0) {
            System.out.println("Menores de 18: " + menores18 + " (" + String.format("%.2f", menores18 * 100.0 / total) + "%)");
            System.out.println("18-30 años: " + entre18y30 + " (" + String.format("%.2f", entre18y30 * 100.0 / total) + "%)");
            System.out.println("31-50 años: " + entre31y50 + " (" + String.format("%.2f", entre31y50 * 100.0 / total) + "%)");
            System.out.println("51-65 años: " + entre51y65 + " (" + String.format("%.2f", entre51y65 * 100.0 / total) + "%)");
            System.out.println("Mayores de 65: " + mayores65 + " (" + String.format("%.2f", mayores65 * 100.0 / total) + "%)");
        }
    }
}