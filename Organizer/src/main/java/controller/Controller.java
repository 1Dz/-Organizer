package controller;

import model.Model;
import model.Quest;
import observable.Observer;
import view.MainWindow;

import java.util.Set;

/**
 * Created by user on 11.10.16.
 */
public class Controller implements Observer{

    private MainWindow view;
    private Model model;

    public Controller()
    {
        this.model = new Model(this);
        this.view = new MainWindow(this);
    }
    @Override
    public void handleAction(String action) {

    }

    public Set<Quest> getQuests()
    {
        return model.getqSet();
    }

}
