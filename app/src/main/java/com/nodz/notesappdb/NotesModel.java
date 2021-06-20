package com.nodz.notesappdb;

public class NotesModel {

    String notes;
    String id;

    public NotesModel() {
    }

    public NotesModel(String notes, String id) {
        this.notes = notes;
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
