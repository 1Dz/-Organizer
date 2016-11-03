package controller;

import model.Department;
import model.Model;
import model.Quest;
import observable.Observer;
import view.MainWindow;

import java.util.Date;
import java.util.Iterator;
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
        switch (action)
        {
            case "addQuest":
                model.addQuest(view.getQuest());
                view.setQuests(model.getqSet());
                break;
            case "removeQuest":
                model.removeQuest(view.getQuest());
                view.setQuests(model.getqSet());
                break;
            case "editQuest":
                model.updateQuest(view.getQuest());
                view.setQuests(model.getqSet());
                break;
            case "updateQuest":
                model.updateQuest(view.getQuest());
                view.setQuests(model.getqSet());
                break;
        }
    }

    public Set<Quest> getQuests()
    {
        return model.getqSet();
    }
    public Quest getQuest(String name)
    {
        Iterator<Quest> it = model.getqSet(name).iterator();
        Quest result;
        while (it.hasNext())
        {
            result = it.next();
            if(result.getName().equals(name))
                return result;
        }
        return null;
    }
    public Set<Quest> getQuests(Department department)
    {
        return model.getqSet(department);
    }
    public Set<Quest> getqSet(String name)
    {
        return model.getqSet(name);
    }
    public Set<Quest> getQuests(Date date)
    {
        return model.getqSet(date);
    }
}
