package ru.gb.frags.user_interface;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.gb.frags.R;
import ru.gb.frags.data.Constants;
import ru.gb.frags.data.Note;

public class EditNoteActivity extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private Button saveNote;
    private int id = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        title = findViewById(R.id.edit_note_title);
        description = findViewById(R.id.edit_note_description);
        saveNote = findViewById(R.id.edit_note_button);

        Intent intent = getIntent();
        if (intent != null) {
            Note note = (Note) intent.getSerializableExtra(Constants.NOTE);
            id = note.getId();
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }

    }
}
