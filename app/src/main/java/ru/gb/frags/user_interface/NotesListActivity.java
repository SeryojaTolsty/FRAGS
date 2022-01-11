package ru.gb.frags.user_interface;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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

    public static final String NOTE_NEW = "NOTE_NEW";

//    private Repo repository = new InMemoryRepoImpl();
    private Repo repository = InMemoryRepoImpl.getInstance();
    private RecyclerView list;
    private NotesAdapter adapter;

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        fillRepo();

        adapter = new NotesAdapter();
        adapter.setNotes(repository.getAll());

        adapter.setOnNoteClickListener(this);

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter) ;
    }

    public void fillRepo() {
        repository.create(new Note("Title 1", "Description 1"));
        repository.create(new Note("Title 2", "Description 2"));
        repository.create(new Note("Title 3", "Description 3"));
        repository.create(new Note("Title 4", "Description 4"));
        repository.create(new Note("Title 5", "Description 5"));
        repository.create(new Note("Title 6", "Description 6"));
//        repository.create(new Note("Title 7", "Description 7"));
//        repository.create(new Note("Title 8", "Description 8"));
//        repository.create(new Note("Title 9", "Description 9"));
//        repository.create(new Note("Title 10", "Description 10"));
//        repository.create(new Note("Title 11", "Description 11"));
//        repository.create(new Note("Title 12", "Description 12"));
//        repository.create(new Note("Title 13", "Description 13"));
//        repository.create(new Note("Title 14", "Description 14"));
//        repository.create(new Note("Title 15", "Description 15"));
//        repository.create(new Note("Title 16", "Description 16"));
    }


    @Override
    public void onNoteClick(Note note) {
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra(Constants.NOTE, note);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
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
                Intent createIntent = new Intent(this, NewNoteActivity.class);
                createIntent.putExtra(Constants.NOTE_NEW, note);
                startActivity(createIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClickNote(Note note) {
        Intent editIntent = new Intent(this, EditNoteActivity.class);
        editIntent.putExtra(NOTE_NEW, note);
        startActivity(editIntent);
    }
}