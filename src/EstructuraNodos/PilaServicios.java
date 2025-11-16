package EstructuraNodos;

public class PilaServicios {
    private NodoSimple cima; // Solo nos importa el de arriba

    public PilaServicios() {
        cima = null;
    }

    public boolean esVacia() {
        return cima == null;
    }

    //Push
    public void apilar(Object servicio) {
        NodoSimple nuevo = new NodoSimple(servicio);
        if (!esVacia()) {
            nuevo.siguiente = cima;
        }
        cima = nuevo;
    }

    //Pop
    public Object desapilar() {
        if (esVacia()) {
            return null;
        }
        Object servicio = cima.dato;
        cima = cima.siguiente;
        return servicio;
    }

    public void mostrar() {
        if (esVacia()) {
            System.out.println("\n══════════════════════════════════════");
            System.out.println("  HISTORIAL DE SERVICIOS VENDIDOS");
            System.out.println("══════════════════════════════════════");
            System.out.println("No hay servicios registrados aún.");
            return;
        }

        NodoSimple temp = cima;
        int contador = 1;
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  HISTORIAL DE SERVICIOS VENDIDOS");
        System.out.println("══════════════════════════════════════");
        System.out.println("(Más reciente primero)\n");

        while (temp != null) {
            System.out.println(contador + ". " + temp.dato.toString());
            temp = temp.siguiente;
            contador++;
        }
        System.out.println("\nTotal de servicios vendidos: " + (contador - 1));
        System.out.println("══════════════════════════════════════\n");
    }

    public int contarElementos() {
        int contador = 0;
        NodoSimple temp = cima;
        while (temp != null) {
            contador++;
            temp = temp.siguiente;
        }
        return contador;
    }
}
