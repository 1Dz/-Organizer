package model;

import exception.StoreException;

import java.util.Date;
import java.util.Set;

/**
 * Created by user on 29.09.16.
 */
public interface DataStore {
    void addQuest(Quest quest) throws StoreException;
    void removeQuest(String quest);
    void updateQuest(Quest quest) throws StoreException;
    Set<Quest> getQuest(String name) throws StoreException;
    Set<Quest> getQuest(Date date) throws StoreException;
    Set<Quest> getQuest(Department whoes) throws StoreException;
    Set<Quest> getQuest(boolean done) throws StoreException;
    Set<Quest> getQuest() throws StoreException;
    Set<Quest> getQuest(Date start, Date end) throws StoreException;
}
