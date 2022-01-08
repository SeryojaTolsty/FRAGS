package ru.gb.frags.user_interface;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.gb.frags.R;
import ru.gb.frags.data.Constants;
import ru.gb.frags.data.Note;

public class NewNoteActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private Button saveNote;
    private int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        title = findViewById(R.id.new_note_title);
        description = findViewById(R.id.new_note_description);
        saveNote = findViewById(R.id.new_note_update);

        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.hasExtra(Constants.NOTE_NEW)) {
            Note note = (Note) intent.getSerializableExtra(Constants.NOTE_NEW);
//            id = note.getId();
//            title.setText(note.getTitle());
//            description.setText(note.getDescription());

        }
    }
}