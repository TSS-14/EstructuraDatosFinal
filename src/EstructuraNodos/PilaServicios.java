package EstructuraNodos;

public class PilaServicios {
    private NodoSimple cima; // Solo nos importa el de arriba

    public PilaServicios() {
        cima = null;
    }

    public boolean esVacia() {
        return cima == null;
    }

    // Método 'push' (Apilar)
    public void apilar(Object servicio) {
        NodoSimple nuevo = new NodoSimple(servicio);
        if (!esVacia()) {
            nuevo.siguiente = cima;
        }
        cima = nuevo;
        System.out.println("Servicio apilado.");
    }

    // Método 'pop' (Desapilar)
    public Object desapilar() {
        if (esVacia()) {
            System.out.println("La pila de servicios está vacía.");
            return null;
        }
        Object servicio = cima.dato; // Guardamos el dato
        cima = cima.siguiente;     // Movemos la cima
        return servicio;           // Devolvemos el dato
    }

    // Método para mostrar
    public void mostrar() {
        if (esVacia()) {
            System.out.println("PILA DE SERVICIOS VACÍA");
            return;
        }

        NodoSimple temp = cima;
        System.out.println("== PILA DE SERVICIOS VENDIDOS (Más reciente primero) ==");
        while (temp != null) {
            // Asumimos que guardamos un String o un objeto con .toString()
            System.out.println("-> " + temp.dato.toString()); 
            temp = temp.siguiente;
        }
    }
}
