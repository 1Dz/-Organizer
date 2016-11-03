package view;

import model.Department;
import model.Quest;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.ObjectInput;
import java.util.Date;
import java.util.Set;

/**
 * Created by user on 29.09.16.
 */
public class MainTableModel extends AbstractTableModel {

    int rowCount;
    int columnCount;
    Set<Quest> qSet;
    Object[][] obj;
    String[] headers = {"Имя", "Подразделение", "Дата", "Входящая", "Исходящая", "Вып/Не вып", "Описание"};
    Class[] columnClass = {String.class, Department.class, Date.class, File.class, File.class, ImageIcon.class, String.class};

    public MainTableModel(Set<Quest> qSet)
    {
        this.qSet = qSet;
        obj = exportedData();
        this.rowCount = obj.length;
        this.columnCount = headers.length;
    }
    private Object[][] exportedData()
    {
        Object[] qArr = qSet.toArray();
        Object[][] objects = new Object[qSet.size()][7];
        for(int i = 0; i < qSet.size(); i++)
        {
            objects[i][0] = ((Quest)qArr[i]).getName();
            objects[i][1] = ((Quest)qArr[i]).getDepartment().toRusString();
            objects[i][2] = ((Quest)qArr[i]).getDate();
            objects[i][3] = ((Quest)qArr[i]).getApplication().getName();
            objects[i][4] = ((Quest)qArr[i]).getSelfApplication().getName();
            if(((Quest)qArr[i]).isDone())
                objects[i][5] = new ImageIcon(getClass().getResource("/icons/plus.jpg"));
            else objects[i][5] = new ImageIcon(getClass().getResource("/icons/minus.jpg"));
            objects[i][6] = ((Quest)qArr[i]).getDescription();
        }
        return objects;
    }
    public void update(Set<Quest> qSet)
    {
        this.qSet = qSet;
        this.obj = exportedData();
        this.rowCount = obj.length;
        fireTableDataChanged();

    }
    public int getRowCount() {
        return this.rowCount;
    }

    public int getColumnCount() {
        return this.columnCount;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return obj[rowIndex][columnIndex];
    }


    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }



}
