package com.nodz.notesappdb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nodz.notesappdb.databinding.ActivityAddNoteBinding;

import java.util.ArrayList;
import java.util.Objects;


public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;
    ArrayList<NotesModel> list;
    DBhelper helper;
    NotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        helper = new DBhelper(this);
        list = helper.getNotes();

        adapter = new NotesAdapter(list,this);

        binding.saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = helper.insertNotes(binding.etAddnote.getText().toString());
                if (isInserted) {
                    Toast.makeText(AddNoteActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                    startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
                } else
                    Toast.makeText(AddNoteActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}