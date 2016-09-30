package model;

import exception.StoreException;
import util.DbHelper;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by user on 29.09.16.
 */
public class CarpetStore implements DataStore {
    private Connection connection = DbHelper.getInstance().getConnection();
    public void addQuest(Quest quest) {
        try {
            String request = "INSERT INTO quests (name, who, date, application, selfApplication, done, description) VALUES('" +
                    quest.getName() + "', '" + quest.getDepartment() + "' , '" + new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(quest.getDate()) +
                    "', '" + quest.getApplication().toPath().getFileName().toString() + "', '" + quest.getSelfApplication().toPath().getFileName().toString() +
                    "' , " + quest.getIntDone() + " , '" + quest.getDescription() +"'";
            Statement st = connection.createStatement();
            st.execute(request);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeQuest(String questName) {
        try{
            String request = "DELETE FROM quests WHERE name='" + questName + "'";
            Statement st = connection.createStatement();
            st.execute(request);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuest(Quest quest) {
        removeQuest(quest.getName());
        addQuest(quest);
    }

    public Set<Quest> getQuest(String name) throws StoreException {
        Set<Quest> resultSet = new TreeSet<>(new Comparator<Quest>() {
            @Override
            public int compare(Quest o1, Quest o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        String request = "SELECT * FROM quests WHERE name='" + name + "'";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next())
            {
                String qName = rs.getString("name");
                Department dep = Department.valueOf(rs.getString("who"));
                Date date = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(rs.getString("date"));
                int done = rs.getInt("done");
                boolean isDone;
                String description = rs.getString("description");
                if(done == 1)
                    isDone = true;
                else isDone = false;
                File application = new File(rs.getString("application"));
                Quest quest;
                if(application == null) {
                    quest = new Quest(qName, dep, date, isDone, description);
                }
                else
                {
                    File selfApplication = new File(rs.getString("selfApplication"));
                    quest = new Quest(qName, dep, date, application, selfApplication, isDone, description);
                }
                resultSet.add(quest);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new StoreException("Ошибка базы данных", e);
        } catch (ParseException e) {
            throw new StoreException("Не может конвертировать дату");
        }
        return resultSet;
    }

    public Set<Quest> getQuest(Date date) throws StoreException {
        Set<Quest> resultSet = new TreeSet<>(new Comparator<Quest>() {
            @Override
            public int compare(Quest o1, Quest o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        String request = "SELECT * FROM quests WHERE date='" + date + "'";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next())
            {
                String qName = rs.getString("name");
                Department dep = Department.valueOf(rs.getString("who"));
                Date qDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(rs.getString("date"));
                int done = rs.getInt("done");
                boolean isDone;
                String description = rs.getString("description");
                if(done == 1)
                    isDone = true;
                else isDone = false;
                File application = new File(rs.getString("application"));
                Quest quest;
                if(application == null) {
                    quest = new Quest(qName, dep, qDate, isDone, description);
                }
                else
                {
                    File selfApplication = new File(rs.getString("selfApplication"));
                    quest = new Quest(qName, dep, qDate, application, selfApplication, isDone, description);
                }
                resultSet.add(quest);
            }

        } catch (SQLException e) {
            throw new StoreException("Ошибка базы данных", e);
        } catch (ParseException e) {
            throw new StoreException("Не может конвертировать дату");
        }
        return resultSet;
    }

    public Set<Quest> getQuest(Department whoes) throws StoreException {
        Set<Quest> resultSet = new TreeSet<>(new Comparator<Quest>() {
            @Override
            public int compare(Quest o1, Quest o2) {
                return o1.getDepartment().compareTo(o2.getDepartment());
            }
        });
        String request = "SELECT * FROM quests WHERE who='" + whoes.toEngString() + "'";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next())
            {
                String qName = rs.getString("name");
                Department dep = Department.valueOf(rs.getString("who"));
                Date qDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(rs.getString("date"));
                int done = rs.getInt("done");
                boolean isDone;
                String description = rs.getString("description");
                if(done == 1)
                    isDone = true;
                else isDone = false;
                File application = new File(rs.getString("application"));
                Quest quest;
                if(application == null) {
                    quest = new Quest(qName, dep, qDate, isDone, description);
                }
                else
                {
                    File selfApplication = new File(rs.getString("selfApplication"));
                    quest = new Quest(qName, dep, qDate, application, selfApplication, isDone, description);
                }
                resultSet.add(quest);
            }
        } catch (SQLException e) {
            throw new StoreException("Ошибка базы данных", e);
        } catch (ParseException e) {
            throw new StoreException("Не может конвертировать дату");
        }
        return resultSet;
    }

    public Set<Quest> getQuest(boolean done) throws StoreException {
        Set<Quest> resultSet = new TreeSet<>(new Comparator<Quest>() {
            @Override
            public int compare(Quest o1, Quest o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        int intDone;
        if (done)
            intDone = 1;
        else intDone = 0;
        String request = "SELECT * FROM quests WHERE done= " + intDone;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {
                String qName = rs.getString("name");
                Department dep = Department.valueOf(rs.getString("who"));
                Date qDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(rs.getString("date"));
                String description = rs.getString("description");
                File application = new File(rs.getString("application"));
                Quest quest;
                if (application == null) {
                    quest = new Quest(qName, dep, qDate, done, description);
                } else {
                    File selfApplication = new File(rs.getString("selfApplication"));
                    quest = new Quest(qName, dep, qDate, application, selfApplication, done, description);
                }
                resultSet.add(quest);
            }
        } catch (SQLException e) {
            throw new StoreException("Ошибка базы данных", e);
        } catch (ParseException e) {
            throw new StoreException("Не может конвертировать дату");
        }
        return resultSet;
    }
        public Set<Quest> getQuest() throws StoreException {
            Set<Quest> resultSet = new TreeSet<>(new Comparator<Quest>() {
                @Override
                public int compare(Quest o1, Quest o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
            String request = "SELECT * FROM quests";
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(request);
                while (rs.next())
                {
                    String qName = rs.getString("name");
                    Department dep = Department.valueOf(rs.getString("who"));
                    Date qDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(rs.getString("date"));
                    int done = rs.getInt("done");
                    boolean isDone;
                    String description = rs.getString("description");
                    if(done == 1)
                        isDone = true;
                    else isDone = false;
                    File application = new File(rs.getString("application"));
                    Quest quest;
                    if(application == null) {
                        quest = new Quest(qName, dep, qDate, isDone, description);
                    }
                    else
                    {
                        File selfApplication = new File(rs.getString("selfApplication"));
                        quest = new Quest(qName, dep, qDate, application, selfApplication, isDone, description);
                    }
                    resultSet.add(quest);
                }

            } catch (SQLException e) {
                throw new StoreException("Ошибка базы данных", e);
            } catch (ParseException e) {
                throw new StoreException("Не может конвертировать дату");
            }
            return resultSet;
    }

    @Override
    public Set<Quest> getQuest(Date start, Date end) throws StoreException {
        Set<Quest> resultSet = new TreeSet<>(new Comparator<Quest>() {
            @Override
            public int compare(Quest o1, Quest o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        String request = "SELECT * FROM quests";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next())
            {
                Date qDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(rs.getString("date"));
                if(qDate.after(start) && qDate.before(end)) {
                    String qName = rs.getString("name");
                    Department dep = Department.valueOf(rs.getString("who"));
                    int done = rs.getInt("done");
                    boolean isDone;
                    String description = rs.getString("description");
                    if (done == 1)
                        isDone = true;
                    else isDone = false;
                    File application = new File(rs.getString("application"));
                    Quest quest;
                    if (application == null) {
                        quest = new Quest(qName, dep, qDate, isDone, description);
                    } else {
                        File selfApplication = new File(rs.getString("selfApplication"));
                        quest = new Quest(qName, dep, qDate, application, selfApplication, isDone, description);
                    }
                    resultSet.add(quest);
                }
            }

        } catch (SQLException e) {
            throw new StoreException("Ошибка базы данных", e);
        } catch (ParseException e) {
            throw new StoreException("Не может конвертировать дату");
        }
        return resultSet;
    }
}
