package EstructuraNodos;

public class ColaClientes {
    private NodoSimple frente; // El primero de la fila
    private NodoSimple fin;     // El último de la fila

    public ColaClientes() {
        frente = null;
        fin = null;
    }

    public boolean esVacia() {
        return frente == null;
    }

    // Método 'enqueue' (Encolar)
    public void encolar(Object cliente) {
        NodoSimple nuevo = new NodoSimple(cliente);
        if (esVacia()) {
            // Si es el primero, es el frente Y el fin
            frente = nuevo;
            fin = nuevo;
        } else {
            // Se forma detrás del último
            fin.siguiente = nuevo;
            fin = nuevo; // El nuevo es ahora el último
        }
        System.out.println("Cliente encolado.");
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
        if (esVacia()) {
            System.out.println("COLA VACÍA");
            return;
        }

        NodoSimple temp = frente;
        System.out.println("== COLA DE CLIENTES (Primero -> Último) ==");
        int i = 1;
        while (temp != null) {
            // Asumimos que guardamos un NodoCliente o un objeto con .toString()
            System.out.println(i + ". " + temp.dato.toString()); 
            temp = temp.siguiente;
            i++;
        }
    }
}
