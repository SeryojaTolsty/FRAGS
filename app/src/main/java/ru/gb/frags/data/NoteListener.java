package ru.gb.frags.data;

import java.util.List;

public interface NoteListener {
   List<Note> onNoteChanges(Note note);
}
