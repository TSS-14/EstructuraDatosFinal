package Estructuras;

public class ListaPaquete {
    private NodoPaquete inicio;

    public ListaPaquete() {
        inicio = null;
    }

    //Metodo si esta vacio
    public boolean esVacio(){
        return inicio == null;
    }

    //Metodo para agregar un paquete
    public void agregarPaquete(String codigoPaquete, String destino, int duracionDias,
                               String tipoPaquete, double precioPaquete, int plazasTotales, int plazaDiponible){

        NodoPaquete nuevo = new NodoPaquete(codigoPaquete, destino, duracionDias, tipoPaquete, precioPaquete, plazasTotales, plazaDiponible);
        if (esVacio()){
            inicio = nuevo;
            nuevo.siguiente = null;
        }else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        System.out.println("Paquete agregado exitosamente");
    }

    //Meotodo para mostrar paquetes
    public void mostrarPaquetes(){
        if (esVacio()){
            System.out.println("La lista esta vacia!");
            return;
        }

        NodoPaquete t = inicio;
        System.out.println("=== LISTA DE PAQUETES ===");
        while (t != null){
            System.out.println("Código: " + t.codigoPaquete +
                    "\nDestino: " + t.destino +
                    "\nDuración: " + t.duracionDias + " días" +
                    "\nTipo: " + t.tipoPaquete +
                    "\nPrecio: $" + t.precioPaquete +
                    "\nPlazas totales: " + t.plazasTotales +
                    "\nPlazas disponibles: " + t.plazaDiponible);
            t = t.siguiente;
        }
    }

    //Metodo para buscar paquete
    public NodoPaquete buscarPaquete(String codigo){
        NodoPaquete actual = inicio;
        while (actual != null){
            if (actual.codigoPaquete.equalsIgnoreCase(codigo)){
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    //Metodo para eliminar paquete
    public boolean eliminarPaquete(String codigo){
        if (esVacio()){
            System.out.println("No hay paquetes registrados");
            return false;
        }

        //Cabeza
        if (inicio.codigoPaquete.equalsIgnoreCase(codigo)){
            inicio = inicio.siguiente;
            System.out.println("Paquete eliminado correctamente");
            return true;
        }

        //Final
        NodoPaquete anterior = inicio;
        NodoPaquete actual = inicio.siguiente;

        while (actual != null){
            if (actual.codigoPaquete.equalsIgnoreCase(codigo)){
                anterior.siguiente = actual.siguiente;
                System.out.println("Paquete eliminado correctamente");
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }

        System.out.println("Codigo del paquete no encontrado"+codigo);
        return false;
    }

    //Metodo para actualiza plazas
    public void actualizarPlazas(String codigo, int nuevasPlazas){
        NodoPaquete nuevo = buscarPaquete(codigo);
        if (nuevo != null){
            nuevo.plazaDiponible = nuevasPlazas;
            System.out.println("Plazas actualizadas correctamente");
        }else {
            System.out.println("No se encontro el paquete");
        }
    }

}
