package EstructuraNodos;

import java.time.LocalDate;

public class ListaPaquete {
    private NodoPaquete inicio;

    public ListaPaquete() {
        inicio = null;
    }

    public boolean esVacio(){
        return inicio == null;
    }


    public void agregarPaquete(String destino, int duracionDias,
                               String tipoPaquete, double precioPaquete, int plazasTotales, int plazaDiponible, LocalDate fechaViaje,
                               LocalDate finFechaViaje){
        NodoPaquete nuevo = new NodoPaquete(destino, duracionDias, tipoPaquete, precioPaquete, plazasTotales, plazaDiponible, fechaViaje, finFechaViaje);
        if (esVacio()){
            inicio = nuevo;
            nuevo.siguiente = null;
        }else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        System.out.println("Paquete agregado exitosamente");
    }

    public String obtenerDestino(String codigoPaqueteBuscado) {
        NodoPaquete actual = inicio;
        while (actual != null) {
            if (String.valueOf(actual.codigoPaquete).equalsIgnoreCase(codigoPaqueteBuscado)) {
                return actual.destino;
            }
            actual = actual.siguiente;
        }
        return "Destino Desconocido";
    }

    public void mostrarPaquetes(){
        if (esVacio()){
            System.out.println("No hay paquetes turisticos registrados aún.");
            return;
        }
        NodoPaquete actual = inicio;
        System.out.println("=== LISTA DE PAQUETES ===");
        while (actual != null){
            System.out.println("Código: " + actual.codigoPaquete +
                    "\nDestino: " + actual.destino +
                    "\nDuración: " + actual.duracionDias + " días" +
                    "\nFecha de inicio: " + actual.inicioFechaViaje+"  -- Fecha a finalizar: " + actual.finFechaViaje +
                    "\nTipo: " + actual.tipoPaquete +
                    "\nPrecio: $" + actual.precioPaquete +
                    "\nPlazas totales: " + actual.plazasTotales +
                    "\nPlazas disponibles: " + actual.plazaDiponible);
            System.out.println("---------------");
            actual = actual.siguiente;
        }
    }

    public NodoPaquete buscarPaquete(int codigo){
        NodoPaquete actual = inicio;
        while (actual != null){
            if (actual.codigoPaquete == codigo){
                System.out.println("-- Paquete de viajes --");
                System.out.println("Código: " + actual.codigoPaquete +
                        "\nDestino: " + actual.destino +
                        "\nDuración: " + actual.duracionDias + " días" +
                        "\nFecha de inicio: " + actual.inicioFechaViaje+"  -- Fecha a finalizar: " + actual.finFechaViaje +
                        "\nTipo: " + actual.tipoPaquete +
                        "\nPrecio: $" + actual.precioPaquete +
                        "\nPlazas totales: " + actual.plazasTotales +
                        "\nPlazas disponibles: " + actual.plazaDiponible);
                System.out.println("---------------");
                return actual;
            }
            actual = actual.siguiente;
        }
        System.out.println("Paquete no encontrado");
        return null;
    }

    public boolean eliminarPaquete(int codigo) {
        if (esVacio()) {
            System.out.println("No hay paquetes registrados");
            return false;
        }

        // Verificar si la cabeza es el paquete a eliminar
        if (inicio.codigoPaquete == codigo) {
            inicio = inicio.siguiente;
            System.out.println("Paquete eliminado correctamente");
            return true;
        }

        // Buscar en el cuerpo o cola
        NodoPaquete anterior = inicio;
        NodoPaquete actual = inicio.siguiente;

        while (actual != null) {
            if (actual.codigoPaquete == codigo) {
                anterior.siguiente = actual.siguiente;
                System.out.println("Paquete eliminado correctamente");
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }

        System.out.println("Código del paquete no encontrado: " + codigo);
        return false;
    }
}

