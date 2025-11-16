package EstructuraNodos;

public class NodoCodigoPromocion {
    String codigo;
    String detalles;
    double descuento;
    NodoCodigoPromocion siguiente;

    public NodoCodigoPromocion(String codigo, String detalles, double descuento) {
        this.codigo = codigo;
        this.detalles = detalles;
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }
}
