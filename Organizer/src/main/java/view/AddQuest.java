package view;

import com.toedter.calendar.JDateChooser;
import model.Department;
import model.Quest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

/**
 * Created by user on 12.10.16.
 */
public class AddQuest extends JFrame {

    private JPanel panel;
    private JTextField nameField;
    private JTextField inField;
    private JTextField outField;
    private JTextArea descField;
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
    private File inFile;
    private File outFile;

    private JComboBox qBox;

    private JButton ok;
    private JButton cancel;

    private MainWindow called;

    public AddQuest(MainWindow called)
    {
        this.called = called;
        this.setTitle("Добавление записи");
        this.setBackground(new Color(60, 60, 60));
        this.setResizable(false);
        this.setSize(450, 350);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initPanel();
        initLabels();
        initFields();
        initComboBox();
        initDateChooser();
        initFileChoosers();
        initOkCancelButtons();
        this.setVisible(true);
    }

    private void initLabels()
    {
        this.nameLabel = new JLabel("Имя");
        this.nameLabel.setBounds(20, 20, 100, 30);
        this.depLabel = new JLabel("Подразделение");
        this.depLabel.setBounds(20, 45, 100, 30);
        this.dateLabel = new JLabel("Дата");
        this.dateLabel.setBounds(20, 70, 100, 30);
        this.inLabel = new JLabel("Входящая");
        this.inLabel.setBounds(20, 95, 100, 30);
        this.outLabel = new JLabel("Исходящая");
        this.outLabel.setBounds(20, 120, 100, 30);
        this.descLabel = new JLabel("Описание");
        this.descLabel.setBounds(20, 145, 100, 30);
        this.isDoneLabel = new JLabel("Вып./Не вып.");
        this.isDoneLabel.setBounds(20, 240, 100, 30);

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

        this.inField = new JTextField();
        inField.setBounds(120, 100, 200, 25);
        inField.setEditable(false);

        this.outField = new JTextField();
        outField.setEditable(false);
        outField.setBounds(120, 125, 200, 25);

        this.descField = new JTextArea(4, 26);
        JScrollPane descScroll = new JScrollPane(descField);
        descScroll.setBounds(120, 150, 300, 100);

        this.bg = new ButtonGroup();
        JRadioButton yes = new JRadioButton("Вып.");
        yes.setBounds(120, 235, 50, 50);
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDone = true;
            }
        });
        JRadioButton no = new JRadioButton("Не вып.");
        no.setBounds(180, 235, 90, 50);
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDone = false;
            }
        });
        bg.add(yes);
        bg.add(no);

        panel.add(nameField);
        panel.add(inField);
        panel.add(outField);
        panel.add(descScroll);
        panel.add(yes);
        panel.add(no);
    }

    private void initDateChooser()
    {
        this.dateChooser = new JDateChooser(new Date(), "dd.MM.yyyy");
        this.dateChooser.setBounds(120, 75, 244, 25);
        this.dateChooser.getDateEditor().getUiComponent().setFont(new Font("Verdana", Font.BOLD, 13));
        this.dateChooser.getDateEditor().getUiComponent().setFocusable(false);
        panel.add(dateChooser);
    }

    private void initComboBox()
    {
        this.qBox = new DepComboBox();
        this.qBox.setBounds(120, 50, 200, 25);
        panel.add(qBox);
    }

    private void initPanel()
    {
        this.panel = new JPanel(null);
        this.add(panel);
    }

    private void initFileChoosers()
    {
        JButton buttonIn = new JButton("Oткрыть");
        buttonIn.setBounds(320, 100, 100, 25);
        buttonIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int x = chooser.showOpenDialog(new JFrame());
                if(x == JFileChooser.APPROVE_OPTION)
                {
                    inFile = chooser.getSelectedFile();
                    inField.setText(inFile.getName());
                }
            }
        });
        JButton buttonOut = new JButton("Открыть");
        buttonOut.setBounds(320, 125, 100, 25);
        buttonOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int x = chooser.showOpenDialog(new JFrame());
                if(x == JFileChooser.APPROVE_OPTION)
                {
                    outFile = chooser.getSelectedFile();
                    outField.setText(outFile.getName());
                }
            }
        });
        panel.add(buttonIn);
        panel.add(buttonOut);
    }

    private void initOkCancelButtons()
    {
        ok = new JButton("OK");
        ok.setBounds(100, 270, 100, 40);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                Department dep = null;
                for(Department x : Department.values()) {
                    if(x.toRusString().equals(qBox.getSelectedItem().toString())) {
                        dep = x;
                        break;
                    }
                }
                Date date = dateChooser.getDate();
                String describtion = descField.getText();
                resultQuest = new Quest(name, dep, date, inFile, outFile, isDone, describtion);
                called.setQuest(resultQuest);
                called.notifyObservers("addQuest");
                called.getMainTable().update(called.getQuests());
                dispose();
            }
        });
        cancel = new JButton("Cancel");
        cancel.setBounds(220, 270, 100, 40);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(ok);
        panel.add(cancel);
    }
}
