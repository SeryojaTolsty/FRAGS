package ru.gb.frags.data;

import java.io.Serializable;

public class NewNote implements Serializable {
    private Integer newId;
    private String newTitle;
    private String newDescription;

    public NewNote(String newTitle, String newDescription) {
        this.newTitle = newTitle;
        this.newDescription = newDescription;
    }

    public NewNote(Integer newId, String newTitle, String newDescription) {
        this.newId = newId;
        this.newTitle = newTitle;
        this.newDescription = newDescription;
    }

    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
}
