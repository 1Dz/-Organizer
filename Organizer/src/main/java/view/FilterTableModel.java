package view;

import model.Department;
import model.Quest;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 18.10.16.
 */
public class FilterTableModel extends AbstractTableModel {

    Set<Quest> set;
    String header;
    int rowCount;
    Object[] data;
    String name;
    public FilterTableModel(Set<Quest> set, String header) {
        this.set = set;
        this.header = header;
        this.data = exportedData();
        this.rowCount = data.length;
    }

    public void update(Set<Quest> set, String header)
    {
        this.set = set;
        this.header = header;
        this.data = exportedData();
        this.rowCount = data.length;
        fireTableDataChanged();
        fireTableStructureChanged();
    }

    private Object[] exportedData()
    {
        Object[] result = new Object[0];
        int count = 0;
        if(header.equals("Нет фильтра") || header.equals("Имя"))
        {
            result = new Object[set.size()];
            Iterator<Quest> it = set.iterator();
            while (it.hasNext())
            {
                result[count] = it.next().getName();
                count++;
            }
        }
        if(header.equals("Подразделение"))
        {
            result = new Object[Department.values().length];
            for(int i = 0; i < Department.values().length; i++)
            {
                result[i] = Department.values()[i].toRusString();
            }
        }
        if(header.equals("Дата"))
        {
            List<Object> tempList = new ArrayList<>();
            Iterator<Quest> it = set.iterator();
            while (it.hasNext())
            {
                Quest temp = it.next();
                if(!tempList.contains(new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(temp.getDate())))
                {
                    tempList.add(new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(temp.getDate()));
                }
            }
            result = tempList.toArray();
        }
        return result;
    }

    @Override
    public String getColumnName(int column) {
        return header;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex];
    }


}
