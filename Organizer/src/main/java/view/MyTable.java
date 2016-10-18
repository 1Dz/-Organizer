package view;

import exception.OrgException;
import model.Department;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * I don't know how it works, but it works
 */
public class MyTable extends JTable {

    public MyTable(TableModel dm) {
        super(dm);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(dm);
        sorter.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        sorter.setComparator(1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        sorter.setComparator(2, new Comparator<Date>() {

            @Override
            public int compare(Date o1, Date o2) {
                if(o1.before(o2))
                    return 1;
                return 0;
            }

            public int compare(String o1, String o2) {
                SimpleDateFormat df= new SimpleDateFormat();
                try {
                    if(df.parse(o1).before(df.parse(o2)))
                        return 1;
                    else
                        return 0;
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
                }
                return 0;
            }
        });
        sorter.setComparator(3, null);
        sorter.setComparator(4, null);
        sorter.setComparator(5, null);
        sorter.setComparator(6, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        setRowSorter(sorter);
    }

}
