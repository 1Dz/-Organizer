package view;

import controller.Controller;
import model.Department;
import model.Quest;
import observable.Observable;
import observable.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

/**
 * Created by user on 29.09.16.
 */
public class MainWindow extends JFrame implements Observable, ActionListener{

    private Controller controller;
    private Set<Quest> quests;
    private Quest quest;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private MainTableModel mainTable;
    private List<Observer> observers = new ArrayList<>();
    private JLabel filterLabel;
    private JLabel imageFilterLabel;
    private ImageIcon filterIcon;
    private FilterTableModel filterTableModel;

    public MainWindow(Controller controller)
    {
        this.controller = controller;
        addObserver(controller);
        this.setTitle("Органайзер");
        this.setBackground(new Color(60, 60, 60));
        this.setResizable(false);
        this.setSize(700, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initTable();
        initButtons();
        initFilter();
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
        addQuest.setBounds(20, 145, 90, 50);

        JButton removeButton = new JButton("<html> Удалить <p> запись </html>");
        removeButton.addActionListener(this);
        removeButton.setActionCommand("removeQuest");
        removeButton.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 13));
        removeButton.setBounds(120, 145, 90, 50);

        JButton editButton = new JButton("<html> Редактировать <p> запись </html>");
        editButton.addActionListener(this);
        editButton.setActionCommand("editQuest");
        editButton.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 13));
        editButton.setBounds(220, 145, 125, 50);

        buttonPanel.add(removeButton);
        buttonPanel.add(addQuest);
        buttonPanel.add(editButton);
        this.add(buttonPanel);
    }

    private void initTable()
    {
        tablePanel = new JPanel();
        tablePanel.setLayout(null);
        tablePanel.setBounds(0, 200, 700, 280);
        this.quests = controller.getQuests();
        this.mainTable = new MainTableModel(quests);
        final JTable table = new MyTable(mainTable);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                quest = controller.getQuest((String) mainTable.getValueAt(table.getSelectedRow(), 0));
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 0, 660, 250);
        tablePanel.add(scrollPane);

        this.add(tablePanel);
    }

    private void initFilter()
    {
        this.filterIcon = new ImageIcon("./src/main/resources/icons/filter.jpg");
        this.imageFilterLabel = new JLabel(filterIcon);
        this.imageFilterLabel.setBounds(20, 15, 20, 20);
        this.filterLabel = new JLabel("Фильтр:");
        this.filterLabel.setBounds(40, 10, 100, 30);
        final FilterTableModel filterTableModel = new FilterTableModel(quests, "Нет фильтра");
        JTable filterTable = new JTable(filterTableModel);
        JScrollPane scrollPane = new JScrollPane(filterTable);
        scrollPane.setBounds(180, 15, 120, 125);
        ButtonGroup bg = new ButtonGroup();
        JRadioButton nameBut = new JRadioButton("Имя");
        nameBut.setBounds(40, 35, 50, 30);
        nameBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableModel.update(quests, "Имя");
            }
        });
        JRadioButton depBut = new JRadioButton("Подразделение");
        depBut.setBounds(40, 60, 120, 30);
        depBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableModel.update(quests, "Подразделение");
            }
        });
        JRadioButton dateBut = new JRadioButton("Дата");
        dateBut.setBounds(40, 85, 50, 30);
        dateBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableModel.update(quests, "Дата");
            }
        });
        JRadioButton noFilter = new JRadioButton("Нет");
        noFilter.setBounds(40, 110, 50, 30);
        noFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableModel.update(quests, "Нет фильтра");
            }
        });
        bg.add(nameBut);
        bg.add(depBut);
        bg.add(dateBut);
        bg.add(noFilter);
        buttonPanel.add(imageFilterLabel);
        buttonPanel.add(scrollPane);
        buttonPanel.add(filterLabel);
        buttonPanel.add(nameBut);
        buttonPanel.add(depBut);
        buttonPanel.add(dateBut);
        buttonPanel.add(noFilter);
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
        switch (e.getActionCommand())
        {
            case "addQuest":
                AddQuest q = new AddQuest(this);
                break;
            case "removeQuest":
                notifyObservers("removeQuest");
                try {
                    quest = quests.iterator().next();
                }
                catch (NoSuchElementException e1)
                {
                    quest = null;
                }
                mainTable.update(quests);
                break;
            case "editQuest":
                new EditQuest(this, quest);
                break;
        }
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public MainTableModel getMainTable() {
        return mainTable;
    }

    public Set<Quest> getQuests() {
        return quests;
    }
}
