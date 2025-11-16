package EstructuraNodos;

public class ListaCodigoPromocion {
    private NodoCodigoPromocion inicio;

    //Metodo si esta vacio
    public boolean esVacio(){
        return inicio == null;
    }

    //Metodo para crear codigo
    public void crearCodigo(String codigo, String descripcion, double descuento){
        NodoCodigoPromocion nuevo = new NodoCodigoPromocion(codigo, descripcion, descuento);
        if (esVacio()){
            inicio = nuevo;
        }else {
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        System.out.println("!Codigo de promocion creado con exito!");
    }

    //Meotodo para buscar el codigo de promocion
    public NodoCodigoPromocion buscarCodigoPromocio(String codigo){
        NodoCodigoPromocion actual = inicio;
        while (actual != null){
            if (actual.codigo.equalsIgnoreCase(codigo)){
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    //Meotodo para mostrar todos los codigos de promocion
    public void mostrarCodigo(){
        if (esVacio()){
            System.out.println("No hay codigos de promocion registrados");
            return;
        }

        System.out.println("\n-- LISTA DE CODIGOS PROMOCIONALES --");
        NodoCodigoPromocion actual = inicio;
        while (actual != null){
            System.out.println("---------------");
            System.out.println("Codigo promocional: "+actual.codigo +
                               "\nDescripcion de codigo: "+actual.detalles +
                               "\nPorcentaje de descuento: "+actual.descuento);
            System.out.println("---------------");
            actual = actual.siguiente;
        }
    }

    //Metodo para eliminar codigo promocional
    public boolean eliminarCodigo(String codigo) {
        if (esVacio()) {
            System.out.println("No hay codigos para eliminar.");
            return false;
        }
        if (inicio.codigo.equalsIgnoreCase(codigo)) {
            inicio = inicio.siguiente;
            System.out.println("Codigo promocional eliminado con exito.");
            return true;
        }
        NodoCodigoPromocion actual = inicio;
        while (actual.siguiente != null) {
            if (actual.siguiente.codigo.equalsIgnoreCase(codigo)) {
                actual.siguiente = actual.siguiente.siguiente;
                System.out.println("Codigo promocional eliminado con exito.");
                return true;
            }
            actual = actual.siguiente;
        }
        System.out.println("Codigo promocional no encontrado.");
        return false;
    }

}
