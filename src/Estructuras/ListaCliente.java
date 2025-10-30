package Estructuras;

public class ListaCliente {
    private NodoCliente inicio;

    public ListaCliente() {
        inicio = null;
    }

    //Metodo si esta vacio
    public boolean esVacio(){
        return inicio == null;
    }

    //Metodo para agregar cliente
    public void agregarCliente(String documento, String tipoCliente, String telefono, String correo,
                               String genero, int edad, String nombre){
        //Verifica si ya existe
        if (buscarCliente(documento) != null){
            System.out.println("Error, el numero de documento ya esta registrado");
            return;
        }
        NodoCliente nuevo = new NodoCliente(documento, tipoCliente, telefono, correo, genero, edad, nombre);
        if (esVacio()){
            inicio = nuevo;
            nuevo.siguiente = null;
        }else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        System.out.println("Cliente agregado correctamente");
    }

    //Metodo para mostrar clientes
    public void mostrarClientes(){
        if (!esVacio()){
            NodoCliente t = inicio;
            System.out.println("-- LISTA DE CLIENTES --");

            int contador = 1;
            while (t != null){
                System.out.println("Cliente # "+contador);
                System.out.println("---------------");
                System.out.println("Documento: "+t.documento+"\nNombre: "+t.nombre+
                                    "\nEdad: "+t.edad+"\nGenero: "+t.genero+"\nCorreo: "+t.correo+
                                    "\nTelefono: "+t.telefono+"\nTipo de cliente: "+t.tipoCliente);
                t = t.siguiente;
            }
        }else {
            System.out.println("La lista esta vacia!");
        }
    }

    // Meotodo para buscar repeticiones
    public NodoCliente buscarCliente(String documento) {
        if (esVacio()) return null;

        NodoCliente aux = inicio;
        while (aux != null) {
            if (aux.documento.equalsIgnoreCase(documento)) {
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }


    //Metodo para eliminar cliente
    public void eliminarCliente(String documento){
        if (esVacio()){
            System.out.println("La lista esta vacia!");
            return;
        }

        NodoCliente actual = inicio;
        NodoCliente anterior = null;

        while (actual != null){
            if (actual.documento.equalsIgnoreCase(documento)){
                if (anterior == null){
                    inicio = actual.siguiente;
                }else {
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

    //Metodo para buscar y mostrar el cliente por numero de documento
    public void buscarMostrarCliente(String documento){
        if (esVacio()){
            System.out.println("La lista esta vacia!");
            return;
        }

        NodoCliente t = inicio;
        boolean encontrado = false;

        while (t != null){
            if (t.documento.equalsIgnoreCase(documento)){
                System.out.println("!Cliente encontrado!");
                System.out.println("---------------");
                System.out.println("Documento: "+t.documento+"\nNombre: "+t.nombre+
                        "\nEdad: "+t.edad+"\nGenero: "+t.genero+"\nCorreo: "+t.correo+
                        "\nTelefono: "+t.telefono+"\nTipo de cliente: "+t.tipoCliente);
                encontrado = true;
                break;
            }
            t = t.siguiente;
        }
        if (!encontrado){
            System.out.println("Usuario no encontrado");
        }
    }
}
