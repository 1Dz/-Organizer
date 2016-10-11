package view;

import controller.Controller;
import model.Quest;
import observable.Observable;
import observable.Observer;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * Created by user on 29.09.16.
 */
public class MainWindow extends JFrame implements Observable, ActionListener{

    private Controller controller;
    private Set<Quest> quests;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private TableModel mainTable;

    public MainWindow(Controller controller)
    {
        this.controller = controller;
        this.setTitle("Органайзер");
        this.setBackground(new Color(60, 60, 60));
        this.setResizable(false);
        this.setSize(700, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initTable();
        initButtons();
        this.setVisible(true);
    }

    public void initButtons()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(0, 100, 700, 100);

        JButton addQuest = new JButton("<html>Добавить <p>запись</html>");
        addQuest.addActionListener(this);
        addQuest.setActionCommand("addQuest");
        addQuest.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 13));
        addQuest.setBounds(20, 150, 90, 50);

        buttonPanel.add(addQuest);
        this.add(buttonPanel);
    }

    private void initTable()
    {
        tablePanel = new JPanel();
        tablePanel.setLayout(null);
        tablePanel.setBounds(0, 200, 700, 280);

        this.quests = controller.getQuests();
        this.mainTable = new MainTableModel(quests);
        JTable table = new JTable(mainTable);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 0, 660, 250);
        tablePanel.add(scrollPane);

        this.add(tablePanel);
    }

    public void setQuests(Set<Quest> quests) {
        this.quests = quests;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String action) {
        for(Observer x : observers)
            x.handleAction(action);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
