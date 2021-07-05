package com.nodz.notesappdb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nodz.notesappdb.databinding.ActivityAddNoteBinding;

import java.util.ArrayList;
import java.util.Objects;


public class AddNoteActivity extends AppCompatActivity {
    ActivityAddNoteBinding binding;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        NotesModel model = new NotesModel();
        final DBhelper dBhelper = new DBhelper(this);
        adapter = new NotesAdapter();

        binding.etAddnote.setText(getIntent().getStringExtra("notetext"));

        binding.saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = dBhelper.insertNotes(binding.etAddnote.getText().toString());
                if(isInserted) {
                    adapter.notifyDataSetChanged();
                    Toast.makeText(AddNoteActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddNoteActivity.this,MainActivity.class));
                 }
                else
                    Toast.makeText(AddNoteActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}