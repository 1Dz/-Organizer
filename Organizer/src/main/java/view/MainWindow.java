package view;

import controller.Controller;
import exception.OrgException;
import model.Department;
import model.Quest;
import observable.Observable;
import observable.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        buttonPanel.setBounds(0, 150, 700, 100);

        JButton addQuest = new JButton("<html>Добавить <p>запись</html>");
        addQuest.addActionListener(this);
        addQuest.setActionCommand("addQuest");
        addQuest.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 13));
        addQuest.setBounds(20, 0, 90, 50);

        JButton removeButton = new JButton("<html> Удалить <p> запись </html>");
        removeButton.addActionListener(this);
        removeButton.setActionCommand("removeQuest");
        removeButton.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 13));
        removeButton.setBounds(120, 0, 90, 50);

        JButton editButton = new JButton("<html> Редактировать <p> запись </html>");
        editButton.addActionListener(this);
        editButton.setActionCommand("editQuest");
        editButton.setFont(new Font("Comic Sans", Font.CENTER_BASELINE, 13));
        editButton.setBounds(220, 0, 125, 50);

        JButton instructions = new JButton("Инструкции");
        instructions.addActionListener(this);
        instructions.setActionCommand("Инструкции");
        instructions.setBounds(350, 0, 100, 20);

        JButton letters = new JButton("Письма");
        letters.addActionListener(this);
        letters.setActionCommand("Письма");
        letters.setBounds(350, 20, 100, 20);

        buttonPanel.add(instructions);
        buttonPanel.add(letters);
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
                JTable x = (JTable) e.getComponent();
                if(x.getSelectedColumn() == 5 && e.getClickCount() == 2)
                {
                    if(quest.isDone())
                    {
                        quest.setDone(false);
                        controller.handleAction("updateQuest");
                        mainTable.update(quests);
                    }
                    else {
                        quest.setDone(true);
                        controller.handleAction("updateQuest");
                        mainTable.update(quests);
                    }
                }
                if(e.getClickCount() == 2 && x.getSelectedColumn() == 3 || e.getClickCount() == 2 && x.getSelectedColumn() == 4)
                {
                    int i = JOptionPane.showConfirmDialog(new JFrame(), "Открыть заявку "
                            + x.getValueAt(x.getSelectedRow(), x.getSelectedColumn()) + "?");
                    if(i == 0)
                    {
                        Desktop desktop = null;
                        if(desktop.isDesktopSupported())
                        {
                            desktop = Desktop.getDesktop();
                            try {
                                if (x.getSelectedColumn() == 3)
                                    desktop.open(quest.getApplication());
                                if (x.getSelectedColumn() == 4)
                                    desktop.open(quest.getSelfApplication());
                            } catch (IOException e1) {
                                try {
                                    throw new OrgException("Desktop is no supported." + e1.getMessage());
                                } catch (OrgException e2) {
                                    JOptionPane.showMessageDialog(new JFrame(), e2.getMessage());
                                }
                            }
                        }
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 0, 660, 250);
        tablePanel.add(scrollPane);

        this.add(tablePanel);
    }

    private void initFilter()
    {
        JPanel filterPanel = new JPanel(null);
        filterPanel.setBounds(0, 0, 150, 150);

        this.add(filterPanel);
        this.filterIcon = new ImageIcon(getClass().getResource("/icons/filter.jpg"));
        this.imageFilterLabel = new JLabel(filterIcon);
        this.imageFilterLabel.setBounds(20, 15, 20, 20);
        this.filterLabel = new JLabel("Фильтр:");
        this.filterLabel.setBounds(40, 10, 100, 30);

        final FilterTableModel filterTableModel = new FilterTableModel(quests, "Нет фильтра");

        JTable filterTable = new JTable(filterTableModel);
        filterTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTable x = (JTable) e.getComponent();
                String s = (String) x.getValueAt(x.getSelectedRow(), 0);
                switch (x.getColumnName(0))
                {
                    case "Имя":
                        quests = controller.getqSet(s);
                        mainTable.update(quests);
                        break;
                    case "Подразделение":
                        quests = controller.getQuests(Department.convert(s));
                        mainTable.update(quests);
                        break;
                    case "Дата":
                        try {
                            quests = controller.getQuests(new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(s));
                            mainTable.update(quests);
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    default:
                        quests = controller.getQuests();
                        mainTable.update(quests);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(filterTable);
        scrollPane.setBounds(180, 15, 120, 125);

        ButtonGroup bg = new ButtonGroup();

        JRadioButton nameBut = new JRadioButton("Имя");
        nameBut.setBounds(40, 35, 50, 30);
        nameBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quests = controller.getQuests();
                mainTable.update(quests);
                filterTableModel.update(quests, "Имя");
            }
        });

        JRadioButton depBut = new JRadioButton("Подразделение");
        depBut.setBounds(40, 60, 120, 30);
        depBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quests = controller.getQuests();
                filterTableModel.update(quests, "Подразделение");
            }
        });

        JRadioButton dateBut = new JRadioButton("Дата");
        dateBut.setBounds(40, 85, 50, 30);
        dateBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quests = controller.getQuests();
                filterTableModel.update(quests, "Дата");
            }
        });

        JRadioButton noFilter = new JRadioButton("Нет");
        noFilter.setBounds(40, 110, 50, 30);
        noFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quests = controller.getQuests();
                mainTable.update(quests);
                filterTableModel.update(quests, "Нет фильтра");
            }
        });

        bg.add(nameBut);
        bg.add(depBut);
        bg.add(dateBut);
        bg.add(noFilter);

        filterPanel.add(imageFilterLabel);
        filterPanel.add(scrollPane);
        filterPanel.add(filterLabel);
        filterPanel.add(nameBut);
        filterPanel.add(depBut);
        filterPanel.add(dateBut);
        filterPanel.add(noFilter);
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
