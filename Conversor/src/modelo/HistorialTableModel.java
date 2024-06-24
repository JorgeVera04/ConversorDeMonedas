package modelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class HistorialTableModel extends AbstractTableModel {

    private final List<RegistroConversión> historial;
    private final String[] columnas = {"De", "A", "Cantidad", "Conversión", "Fecha y Hora"};

    public HistorialTableModel(List<RegistroConversión> historial) {
        this.historial = historial;
    }

    @Override
    public int getRowCount() {
        return historial.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RegistroConversión registro = historial.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return registro.getMonedaOrigen();
            case 1:
                return registro.getMonedaDestino();
            case 2:
                return registro.getValorOrigen();
            case 3:
                return registro.getValorDestino();
            case 4:
                return registro.getFechaHora();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
