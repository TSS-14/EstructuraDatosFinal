package EstructuraNodos;

public class ListaServicioAdicional {
    private NodoServicioAdicional inicio;

    public ListaServicioAdicional() {
        inicio = null;
    }

    //Metodo para saber si esta vacio
    boolean esVacio(){
        return inicio == null;
    }

    //Metodo para agregar servicio
    public void agregarServicio(String codigo, String nombreServicio, double valorServicio){
        NodoServicioAdicional nuevo = new NodoServicioAdicional(codigo, nombreServicio, valorServicio);
        if (esVacio()){
            inicio = nuevo;
            nuevo.siguiente = null;
        }else {
            nuevo.siguiente = nuevo;
            inicio = nuevo;
        }
    }

    //Metodo para ver servicios
    public void mostrarServicio(){
        if (esVacio()){
            System.out.println("La lista esta vacia");
            return;
        }

        NodoServicioAdicional t = inicio;
        System.out.println("== SERVICIOS ADICIONALES ==");
        while (t != null){
            System.out.println("Codigo del servicio: "+t.codigoServico+"\nNombre del servicio: "+t.nombreServicio+
                                "\nValor del servicio: "+t.valorServicio);
            t = t.siguiente;
        }
    }
    //Metodo para eliminar paquete
    public boolean eliminarPaquete(String codigo){
        if (esVacio()){
            System.out.println("No hay paquetes registrados");
        }

        //Cabeza
        if (inicio.codigoServico.equalsIgnoreCase(codigo)){
            inicio = inicio.siguiente;
            System.out.println("Servicio eliminado correctamente");
            return true;
        }

        //Final
        NodoServicioAdicional anterior = inicio;
        NodoServicioAdicional actual = inicio.siguiente;

        while (actual != null){
            if (actual.codigoServico.equalsIgnoreCase(codigo)){
                anterior.siguiente = actual.siguiente;
                System.out.println("Servicio eliminado correctamente");
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        System.out.println("Codigo del servicio no encontrado"+codigo);
        return false;
    }

    //Metodo para actualizar valor del servicio
    public void actualizarValorServicio(String codigo, double valorServicio){
        NodoServicioAdicional nuevo = buscarServicio(codigo);
        if (nuevo != null){
            nuevo.valorServicio = valorServicio;
            System.out.println("Valor del servicio actualizado correctamente");
        }else {
            System.out.println("No se encontro el servicio");
        }
    }

    //Metodo para buscar servicio
    public NodoServicioAdicional buscarServicio(String codigo){
        NodoServicioAdicional actual = inicio;
        while (actual != null){
            if (actual.codigoServico.equalsIgnoreCase(codigo)){
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }
}
