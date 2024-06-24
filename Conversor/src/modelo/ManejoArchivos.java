package modelo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ManejoArchivos {

    private static final String FILE_NAME = "historial_conversiones.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void escribirRegistro(RegistroConversión registro) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String linea = formatoRegistro(registro);
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<RegistroConversión> leerHistorial() {
        List<RegistroConversión> historial = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                RegistroConversión registro = parsearRegistro(linea);
                if (registro != null) {
                    historial.add(registro);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            e.printStackTrace();
        }
        return historial;
    }

    private static String formatoRegistro(RegistroConversión registro) {
        return String.format("%s,%s,%.2f,%.2f,%s",
                registro.getMonedaOrigen(),
                registro.getMonedaDestino(),
                registro.getValorOrigen(),
                registro.getValorDestino(),
                registro.getFechaHora().format(formatter));
    }

    private static RegistroConversión parsearRegistro(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 5) {
            return null;
        }
        try {
            String monedaOrigen = partes[0];
            String monedaDestino = partes[1];
            double valorOrigen = Double.parseDouble(partes[2]);
            double valorDestino = Double.parseDouble(partes[3]);
            LocalDateTime fechaHora = LocalDateTime.parse(partes[4], formatter);
            return new RegistroConversión(monedaOrigen, monedaDestino, valorOrigen, valorDestino, fechaHora);
        } catch (NumberFormatException | DateTimeParseException e) {
            System.err.println("Error al parsear registro: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
