package ru.gb.frags.user_interface;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.gb.frags.R;
import ru.gb.frags.data.Constants;
import ru.gb.frags.data.InMemoryRepoImpl;
import ru.gb.frags.data.Note;
import ru.gb.frags.data.Repo;

public class NewNoteActivity extends AppCompatActivity  {

    private Repo repository = InMemoryRepoImpl.getInstance();
    private EditText title;
    private EditText description;
    private Button saveNote;
    private int id;

//    private NotesAdapter adapter;

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);


        title = findViewById(R.id.new_note_title);
        description = findViewById(R.id.new_note_description);
        saveNote = findViewById(R.id.new_note_update);

        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.hasExtra(Constants.NOTE)) {
            note = (Note) intent.getSerializableExtra(Constants.NOTE_NEW);
        }
        else {
            note = new Note("", "");
        }


        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = NewNoteActivity.this.title.getText().toString();
                note.setTitle(title);
                String description = NewNoteActivity.this.description.getText().toString();
                note.setDescription(description);
                if (note.getId() == null) {
                    repository.create(note);
                } else {
                    repository.update(note);
                }
//                adapter.setNotes(repository.onNoteChanges());
                finish();
            }
        });
    }


}