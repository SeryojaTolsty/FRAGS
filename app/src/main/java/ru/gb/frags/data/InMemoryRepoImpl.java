package ru.gb.frags.data;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepoImpl implements Repo {

    private static InMemoryRepoImpl repo;

    public static Repo getInstance() {
        if (repo == null) {
            repo = new InMemoryRepoImpl();
        }
        return repo;
    }

    private InMemoryRepoImpl() {
        fillRepo();
    }

    private ArrayList<Note> notes = new ArrayList<>();
    private int counter = 0;

    @Override
    public int create(Note note) {
        int id = counter++;
        note.setId(id);
        notes.add(note);
        return id;
    }

    @Override
    public Note read(int id) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == id) {
                return notes.get(i);
            }
        }
        return null;
    }

    @Override
    public void update(Note note) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(note.getId())) {
                notes.set(i, note);
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == id) {
                notes.remove(i);
                break;
            }
        }
    }


    public void fillRepo() {
        create(new Note("Title 1", "Description 1"));
        create(new Note("Title 2", "Description 2"));
        create(new Note("Title 3", "Description 3"));
        create(new Note("Title 4", "Description 4"));
        create(new Note("Title 5", "Description 5"));
        create(new Note("Title 6", "Description 6"));
    }


    @Override
    public List<Note> getAll() {
        return notes;
    }

//    @Override
//    public List<Note> onNoteChanges() {
//        for (int i = 0; i < notes.size(); i++) {
//            if (notes.get(i).getId() == notes.size()) {
//                notes.toArray();
//            }
//        }
//        return notes;
//    }
}
