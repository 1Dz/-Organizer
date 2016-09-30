package view;

import javax.swing.table.AbstractTableModel;

/**
 * Created by user on 29.09.16.
 */
public class MainTableModel extends AbstractTableModel {

    int rowCount;
    int columnCount;

    public MainTableModel()
    {

    }
    public int getRowCount() {
        return 0;
    }

    public int getColumnCount() {
        return 0;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
