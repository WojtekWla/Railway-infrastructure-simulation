package GUI_Project.Interface;

import GUI_Project.Rail.Station;
import GUI_Project.TrainSet;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {
    private Station[][] stations;

    public TableModel(Station[][] stations) {
        this.stations = stations;

    }

    public Station[][] getStations() {
        return stations;
    }

    @Override
    public int getRowCount() {
        return stations.length;
    }

    @Override
    public int getColumnCount() {
        return stations[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return stations[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        stations[rowIndex][columnIndex] = (Station)aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
