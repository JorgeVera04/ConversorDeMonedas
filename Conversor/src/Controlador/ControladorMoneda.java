package controlador;

import modelo.ConsultaMonedas;
import modelo.RegistroConversión;
import modelo.ManejoArchivos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ControladorMoneda {

    private ConsultaMonedas consultaMonedas;

    public ControladorMoneda() {
        consultaMonedas = new ConsultaMonedas();
    }

    public Map<String, Double> obtenerTasasDeCambio() {
        return consultaMonedas.buscaMoneda().getConversion_rates();
    }

    public double convertirMoneda(String monedaOrigen, String monedaDestino, double valor) {
        Map<String, Double> tasasDeCambio = obtenerTasasDeCambio();

        if (!tasasDeCambio.containsKey(monedaOrigen) || !tasasDeCambio.containsKey(monedaDestino)) {
            throw new IllegalArgumentException("Moneda seleccionada no encontrada en la API");
        }

        double tasaOrigen = tasasDeCambio.get(monedaOrigen);
        double tasaDestino = tasasDeCambio.get(monedaDestino);

        double valorConvertido = (valor / tasaOrigen) * tasaDestino;

        RegistroConversión registro = new RegistroConversión(monedaOrigen, monedaDestino, valor, valorConvertido, LocalDateTime.now());
        ManejoArchivos.escribirRegistro(registro);

        return valorConvertido;
    }

    public List<RegistroConversión> obtenerHistorialConversiones() {
        return ManejoArchivos.leerHistorial();
    }
}
