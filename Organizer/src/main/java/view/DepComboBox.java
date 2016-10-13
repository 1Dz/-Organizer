package view;

import model.Department;
import model.Quest;

import javax.swing.*;
import java.util.Set;

/**
 * Created by user on 13.10.16.
 */
public class DepComboBox extends JComboBox {
    Department[] deps = {Department.FPKI, Department.PTKI, Department.ADMINISTRATION, Department.HOZ, Department.OGM, Department.PSH, Department.TRANSPORT};
    public DepComboBox()
    {
        for(Department x : deps)
            this.addItem(x.toString());
    }
}
