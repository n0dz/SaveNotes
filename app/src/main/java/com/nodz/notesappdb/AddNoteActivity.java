package com.nodz.notesappdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nodz.notesappdb.databinding.ActivityAddNoteBinding;


public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;
    int noteId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        final DBhelper dBhelper = new DBhelper(this);

        binding.etAddnote.setText(getIntent().getStringExtra("notetext"));

        binding.saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = dBhelper.insertNotes(binding.etAddnote.getText().toString());
                if(isInserted) {
                    Toast.makeText(AddNoteActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                    NotesAdapter adapter = new NotesAdapter();
                    adapter.notifyDataSetChanged();
                    startActivity(new Intent(AddNoteActivity.this,HomeActivity.class));
                }
                else
                    Toast.makeText(AddNoteActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}