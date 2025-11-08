package EstructuraNodos;

// Este nodo es genérico. Puede guardar cualquier cosa.
// Lo usaremos para guardar Clientes en la cola y Servicios en la pila.
public class NodoSimple {
    
    // Usamos 'Object' para que pueda guardar cualquier tipo de dato
    Object dato; 
    NodoSimple siguiente;

    public NodoSimple(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
