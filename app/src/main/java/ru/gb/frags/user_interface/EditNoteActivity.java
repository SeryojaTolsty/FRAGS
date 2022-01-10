package ru.gb.frags.user_interface;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.gb.frags.R;
import ru.gb.frags.data.Constants;
import ru.gb.frags.data.InMemoryRepoImpl;
import ru.gb.frags.data.Note;
import ru.gb.frags.data.Repo;

public class EditNoteActivity extends AppCompatActivity {

    private Repo repository = InMemoryRepoImpl.getInstance();
    private EditText title;
    private EditText description;
    private Button saveNote;
    private int id = 1;

    private Note note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        title = findViewById(R.id.edit_note_title);
        description = findViewById(R.id.edit_note_description);
        saveNote = findViewById(R.id.edit_note_button);


        Intent intent = getIntent();
        if (intent != null) {
            note = (Note) intent.getSerializableExtra(Constants.NOTE_NEW);
            id = note.getId();
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }
        else {
            note = new Note("", "");
        }

        title.setText(note.getTitle());
        description.setText(note.getDescription());

        saveNote.setOnClickListener(view -> {
            String title = EditNoteActivity.this.title.getText().toString();
            this.note.setTitle(title);
            String description = EditNoteActivity.this.description.getText().toString();
            this.note.setDescription(description);
            if (this.note.getId() == null) {
                repository.create(this.note);
            } else {
                repository.update(this.note);
            }
            finish();
        });


    }
}
