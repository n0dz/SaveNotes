package com.nodz.notesappdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;

import android.view.MenuItem;
import android.view.View;



import com.nodz.notesappdb.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayList<NotesModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBhelper helper = new DBhelper(this);
        list = helper.getNotes();

        NotesAdapter adapter = new NotesAdapter(list,this);
        binding.noteRecyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        //GridLayoutManager manager = new GridLayoutManager(this,2);
        binding.noteRecyclerView.setLayoutManager(manager);

        binding.addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),AddNoteActivity.class);
                startActivity(in);
            }
        });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.addNoteMenu:
                startActivity(new Intent(getApplicationContext(),AddNoteActivity.class));
                break;

            case R.id.exitAppMenu:
                //finishAffinity();
                new AlertDialog.Builder(HomeActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure ?")
                        .setMessage("Your note will be deleted!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
        }
        return true;
    }
}
