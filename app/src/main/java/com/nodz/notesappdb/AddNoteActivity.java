package com.nodz.notesappdb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;


import com.nodz.notesappdb.databinding.ActivityAddNoteBinding;

import java.util.Objects;


public class AddNoteActivity extends AppCompatActivity {

    ActivityAddNoteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        DBhelper dBhelper = new DBhelper(this);

        binding.etAddnote.setText(getIntent().getStringExtra("note"));

        binding.saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = dBhelper.insertNotes(binding.etAddnote.getText().toString());
                if(isInserted) {
                    Toast.makeText(AddNoteActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                    NotesAdapter adapter = new NotesAdapter();
                    adapter.notifyDataSetChanged();
                    startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
                 }
                else
                    Toast.makeText(AddNoteActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
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