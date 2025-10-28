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

        NodoPaquete actual = inicio;
        System.out.println("=== LISTA DE PAQUETES ===");
        while (actual != null){
            System.out.println("Código: " + actual.codigoPaquete +
                    ", Destino: " + actual.destino +
                    ", Duración: " + actual.duracionDias + " días" +
                    ", Tipo: " + actual.tipoPaquete +
                    ", Precio: $" + actual.precioPaquete +
                    ", Plazas totales: " + actual.plazasTotales +
                    ", Plazas disponibles: " + actual.plazaDiponible);
            actual = actual.siguiente;
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
}
