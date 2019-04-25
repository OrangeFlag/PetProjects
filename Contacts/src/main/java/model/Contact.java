package model;

import java.util.Date;
import java.util.Map;

public abstract class Contact {
    private String number;
    private Date timeCreated;
    protected Date timeLastEdit;

    public Contact(String number) {
        this.number = number;
        this.timeCreated = new java.util.Date();
        this.timeLastEdit = new java.util.Date();
    }

    public abstract String getName();

    public abstract void setName(String name);

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
        this.timeLastEdit = new java.util.Date();
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public Date getTimeLastEdit() {
        return timeLastEdit;
    }

    public abstract Map<String, Object> getExtraFields();

    public abstract void setExtraFields(Map<String, Object> extraFields);
}
