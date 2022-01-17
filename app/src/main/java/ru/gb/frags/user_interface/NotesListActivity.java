package ru.gb.frags.user_interface;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.frags.R;
import ru.gb.frags.data.Constants;
import ru.gb.frags.data.InMemoryRepoImpl;
import ru.gb.frags.data.Note;
import ru.gb.frags.data.Repo;
import ru.gb.frags.recycler.NotesAdapter;

public class NotesListActivity extends AppCompatActivity implements NotesAdapter.OnNoteClickListener {

    private static final int NOTE_EDIT_ACTIVITY = 555;
    private Repo repository = InMemoryRepoImpl.getInstance();
    private RecyclerView list;
    private NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NotesAdapter();
        adapter.setOnNoteClickListener(this);
        list.setAdapter(adapter) ;
        adapter.setNotes(repository.getAll());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_create:
                Intent createIntent = new Intent(this, EditNoteActivity.class);
                startActivityForResult(createIntent, NOTE_EDIT_ACTIVITY);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClickNote(Note note) {
        Intent editIntent = new Intent(this, EditNoteActivity.class);
        editIntent.putExtra(Constants.NOTE, note);
        startActivityForResult(editIntent, NOTE_EDIT_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == NOTE_EDIT_ACTIVITY)
        {
            if(resultCode == RESULT_OK && data != null)
            {
                Note note = (Note) data.getSerializableExtra(Constants.NOTE);
                if(note != null)
                {
                    if(note.getId() == -1)
                    {
                        repository.create(note);
                    }
                    else {
                        repository.update(note);
                    }
                    adapter.setNotes(repository.getAll());
                }
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}