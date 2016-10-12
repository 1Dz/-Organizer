package model;

import controller.Controller;
import exception.StoreException;

import javax.swing.*;
import java.util.Set;

/**
 * Created by user on 29.09.16.
 */
public class Model {

    private Quest quest;
    private Set<Quest> qSet;
    private Controller controller;
    private DataStore store;
    public Model(Controller controller)
    {
        this.controller = controller;
        this.store = new CarpetStore();
        try {
            this.qSet = store.getQuest();
        } catch (StoreException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
        }
    }
    public void addQuest(Quest quest)
    {
        store.addQuest(quest);
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public Set<Quest> getqSet() {
        return qSet;
    }

    public void setqSet(Set<Quest> qSet) {
        this.qSet = qSet;
    }
}
