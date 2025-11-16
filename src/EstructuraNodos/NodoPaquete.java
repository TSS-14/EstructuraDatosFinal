package EstructuraNodos;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.Locale;

public class NodoPaquete {
    public static int contadorCodigo = 1;
    int codigoPaquete;
    String destino;
    int duracionDias;
    String tipoPaquete;
    double precioPaquete;
    int plazasTotales;
    int plazaDiponible;
    public LocalDate inicioFechaViaje;
    public LocalDate finFechaViaje;
    NodoPaquete siguiente;

    public NodoPaquete(String destino, int duracionDias,
                       String tipoPaquete, double precioPaquete, int plazasTotales, int plazaDiponible, LocalDate fechaViaje,
                       LocalDate finFechaViaje) {
        this.codigoPaquete = contadorCodigo++;
        this.destino = destino;
        this.duracionDias = duracionDias;
        this.tipoPaquete = tipoPaquete;
        this.precioPaquete = precioPaquete;
        this.plazasTotales = plazasTotales;
        this.plazaDiponible = plazaDiponible;
        this.inicioFechaViaje = fechaViaje;
        this.finFechaViaje = finFechaViaje;
    }

    public double getPrecio() {
        return this.precioPaquete;
    }

    public double getDuracionDias() {
        return this.duracionDias;
    }

    public LocalDate getInicioFechaViaje() {
        return inicioFechaViaje;
    }

    public int getPlazaDiponible() {
        return plazaDiponible;
    }

    public void setPlazaDiponible(int plazaDiponible) {
        this.plazaDiponible = plazaDiponible;
    }
}
