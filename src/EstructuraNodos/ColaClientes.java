package EstructuraNodos;

public class ColaClientes {
    private NodoSimple frente;
    private NodoSimple fin;

    public ColaClientes() {
        frente = null;
        fin = null;
    }

    public boolean esVacia() {
        return frente == null;
    }

    //enqueue
    public void encolar(Object cliente) {
        NodoSimple nuevo = new NodoSimple(cliente);
        if (esVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    // Método 'dequeue' (Desencolar)
    public Object desencolar() {
        if (esVacia()) {
            System.out.println("La cola está vacía.");
            return null;
        }
        
        Object cliente = frente.dato; // Guardamos el dato del primero
        frente = frente.siguiente; // El segundo se vuelve el primero
        
        // Si la cola queda vacía, 'fin' también debe ser null
        if (frente == null) {
            fin = null;
        }
        return cliente; // Devolvemos el dato del cliente atendido
    }

    // Método para mostrar
    public void mostrar() {
        System.out.println("=========================================");
        System.out.println("       COLA DE ATENCIÓN ACTUAL");
        System.out.println("=========================================");

        if (esVacia()) {
            System.out.println("No hay clientes en cola de atención.");
            System.out.println("=========================================");
            return;
        }

        NodoSimple temp = frente;
        System.out.println("(Primero en ser atendido -> Último)\n");
        int i = 1;
        while (temp != null) {
            Object dato = temp.dato;
            if (dato instanceof NodoCliente) {
                NodoCliente cliente = (NodoCliente) dato;
                System.out.println(i + ". " + cliente.nombre + " - Doc: " + cliente.documento);
            } else {
                System.out.println(i + ". " + temp.dato.toString());
            }
            temp = temp.siguiente;
            i++;
        }
        System.out.println("\nTotal en cola: " + (i - 1));
        System.out.println("=========================================");
    }

    // Método para contar personas en cola
    public void contarPersonasEnCola() {
        int contador = 0;
        NodoSimple temp = frente;
        while (temp != null) {
            contador++;
            temp = temp.siguiente;
        }
        System.out.println("\n=== PERSONAS EN COLA DE ATENCIÓN ===");
        System.out.println("Total de personas en espera: " + contador);
    }

    // Método para reportes
    public void mostrarPersonasEnCola() {
        int contador = 0;
        NodoSimple temp = frente;

        System.out.println("\n=== REPORTE: PERSONAS EN COLA DE ATENCIÓN ===");

        if (esVacia()) {
            System.out.println("No hay personas en cola de atención.");
            return;
        }

        while (temp != null) {
            contador++;
            Object dato = temp.dato;
            if (dato instanceof NodoCliente) {
                NodoCliente cliente = (NodoCliente) dato;
                System.out.println(contador + ". " + cliente.nombre + " - Doc: " + cliente.documento);
            } else {
                System.out.println(contador + ". " + dato.toString());
            }
            temp = temp.siguiente;
        }
        System.out.println("\nTotal de personas en espera: " + contador);
    }

    // Método para contar elementos sin mostrar
    public int contarElementos() {
        int contador = 0;
        NodoSimple temp = frente;
        while (temp != null) {
            contador++;
            temp = temp.siguiente;
        }
        return contador;
    }
}
