package model;

import java.io.File;
import java.util.Date;

/**
 * Created by user on 29.09.16.
 */
public class Quest {

    private String name;
    private Department department;
    private Date date;
    private File application;
    private File selfApplication;
    private boolean done;
    private String description;

    public Quest(String name, Department department, Date date, boolean done, String description)
    {
        this.name = name;
        this.department = department;
        this.date = date;
        this.done = done;
        this.description = description;
    }

    public Quest(String name, Department department, Date date, File application, File selfApplication, boolean done, String description) {
        this.name = name;
        this.department = department;
        this.date = date;
        this.application = application;
        this.selfApplication = selfApplication;
        this.done = done;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getIntDone()
    {
        if(done)
            return 1;
        else return 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public File getApplication() {
        return application;
    }

    public void setApplication(File application) {
        this.application = application;
    }

    public File getSelfApplication() {
        return selfApplication;
    }

    public void setSelfApplication(File selfApplication) {
        this.selfApplication = selfApplication;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
