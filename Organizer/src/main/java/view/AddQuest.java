package view;

import com.toedter.calendar.JDateChooser;
import model.Quest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by user on 12.10.16.
 */
public class AddQuest extends JFrame {

    private JPanel panel;
    private JTextField nameField;
    private JTextField departmentField;
    private JTextField dateField;
    private JTextField inField;
    private JTextField outField;
    private JTextField descField;
    private ButtonGroup bg;

    private JDateChooser dateChooser;

    private JLabel nameLabel;
    private JLabel depLabel;
    private JLabel dateLabel;
    private JLabel inLabel;
    private JLabel outLabel;
    private JLabel descLabel;
    private JLabel isDoneLabel;

    private Quest resultQuest;
    private boolean isDone;

    public AddQuest()
    {
        this.setTitle("Добавление записи");
        this.setBackground(new Color(60, 60, 60));
        this.setResizable(false);
        this.setSize(550, 250);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initPanel();
        initLabels();
        initFields();
        initDateChooser();
        this.setVisible(true);
    }

    private void initLabels()
    {
        this.nameLabel = new JLabel("Имя");
        this.nameLabel.setBounds(20, 20, 100, 30);
        this.depLabel = new JLabel("Подразделение");
        this.depLabel.setBounds(20, 40, 100, 30);
        this.dateLabel = new JLabel("Дата");
        this.dateLabel.setBounds(20, 60, 100, 30);
        this.inLabel = new JLabel("Входящая");
        this.inLabel.setBounds(20, 80, 100, 30);
        this.outLabel = new JLabel("Исходящая");
        this.outLabel.setBounds(20, 100, 100, 30);
        this.descLabel = new JLabel("Описание");
        this.descLabel.setBounds(20, 120, 100, 30);
        this.isDoneLabel = new JLabel("Вып./Не вып.");
        this.isDoneLabel.setBounds(20, 140, 100, 30);

        panel.add(nameLabel);
        panel.add(depLabel);
        panel.add(dateLabel);
        panel.add(inLabel);
        panel.add(outLabel);
        panel.add(descLabel);
        panel.add(isDoneLabel);

    }

    private void initFields()
    {
        this.nameField = new JTextField();
        nameField.setBounds(120, 25, 200, 25);

        this.departmentField = new JTextField();
        departmentField.setBounds(120, 45, 200, 25);
        departmentField.setEditable(false);

        this.dateField = new JTextField();
        dateField.setBounds(120, 65, 200, 25);
        dateField.setEditable(false);

        this.inField = new JTextField();
        inField.setBounds(120, 85, 200, 25);
        inField.setEditable(false);

        this.outField = new JTextField();
        outField.setEditable(false);
        outField.setBounds(120, 105, 200, 25);

        this.descField = new JTextField();
        descField.setBounds(120, 125, 200, 25);

        this.bg = new ButtonGroup();
        JRadioButton yes = new JRadioButton("Вып.");
        yes.setBounds(120, 130, 50, 50);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDone = true;
            }
        });
        JRadioButton no = new JRadioButton("Не вып.");
        no.setBounds(180, 130, 90, 50);
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDone = false;
            }
        });
        bg.add(yes);
        bg.add(no);

        panel.add(nameField);
        panel.add(departmentField);
        panel.add(dateField);
        panel.add(inField);
        panel.add(outField);
        panel.add(descField);
        panel.add(yes);
        panel.add(no);
    }

    private void initDateChooser()
    {
        this.dateChooser = new JDateChooser(new Date());
        this.dateChooser.setBounds(340, 65, 100, 27);
        dateField.setText(new SimpleDateFormat("dd.MM.yyyy г.", Locale.ENGLISH).format(dateChooser.getDate()));
        panel.add(dateChooser);
    }

    private void initPanel()
    {
        this.panel = new JPanel(null);
        this.add(panel);
    }
}
