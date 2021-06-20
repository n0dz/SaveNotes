package com.nodz.notesappdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;


import com.nodz.notesappdb.databinding.ActivityEditNoteBinding;

import java.util.ArrayList;
import java.util.Objects;

public class EditNoteActivity extends AppCompatActivity {

    ActivityEditNoteBinding binding;
    ArrayList<NotesModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        final DBhelper dBhelper = new DBhelper(this);
        list = dBhelper.getNotes();

        NotesAdapter adapter = new NotesAdapter();
        NotesModel model = new NotesModel();

        if(getIntent().getIntExtra("type",0) == 6)
            binding.etEditnote.setText(getIntent().getStringExtra("text_note"));
        else
            binding.etEditnote.setText(getIntent().getStringExtra("text"));

        binding.editNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isInserted = dBhelper.updateData(binding.etEditnote.getText().toString());
                if(isInserted) {
                    Toast.makeText(EditNoteActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                    dBhelper.insertNotes(binding.etEditnote.getText().toString());
                    adapter.notifyDataSetChanged();
                    startActivity(new Intent(EditNoteActivity.this, MainActivity.class));
                }
                else
                    Toast.makeText(EditNoteActivity.this, "Failed", Toast.LENGTH_SHORT).show();
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