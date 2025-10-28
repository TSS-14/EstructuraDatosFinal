package Estructuras;

public class NodoPaquete {
    String codigoPaquete;
    String destino;
    int duracionDias;
    String tipoPaquete;
    double precioPaquete;
    int plazasTotales;
    int plazaDiponible;
    NodoPaquete siguiente;

    public NodoPaquete(String codigoPaquete, String destino, int duracionDias,
                       String tipoPaquete, double precioPaquete, int plazasTotales, int plazaDiponible) {
        this.codigoPaquete = codigoPaquete;
        this.destino = destino;
        this.duracionDias = duracionDias;
        this.tipoPaquete = tipoPaquete;
        this.precioPaquete = precioPaquete;
        this.plazasTotales = plazasTotales;
        this.plazaDiponible = plazaDiponible;
    }
}
