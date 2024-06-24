package modelo;

import java.time.LocalDateTime;

public class RegistroConversión {

    private String monedaOrigen;
    private String monedaDestino;
    private double valorOrigen;
    private double valorDestino;
    private LocalDateTime fechaHora;

    public RegistroConversión(String monedaOrigen, String monedaDestino, double valorOrigen, double valorDestino, LocalDateTime fechaHora) {
        this.monedaOrigen = monedaOrigen;
        this.monedaDestino = monedaDestino;
        this.valorOrigen = valorOrigen;
        this.valorDestino = valorDestino;
        this.fechaHora = fechaHora;
    }

    // Getters y Setters
    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public double getValorOrigen() {
        return valorOrigen;
    }

    public double getValorDestino() {
        return valorDestino;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return "De " + valorOrigen + " " + monedaOrigen + " a " + valorDestino + " " + monedaDestino + " el " + fechaHora;
    }
}
