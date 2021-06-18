package com.nodz.notesappdb;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.customViewHolder> {

    Context context;
    ArrayList<NotesModel> noteslist;
    DBhelper helper;

    public NotesAdapter() {
    }

    public NotesAdapter(ArrayList<NotesModel> noteslist, Context context) {
        this.context = context;
        this.noteslist = noteslist;
    }

    @NonNull
    @Override
    public NotesAdapter.customViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sample_item,parent,false);
        return new customViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.customViewHolder holder, int position) {
        helper = new DBhelper(context);
        NotesModel model = noteslist.get(position);
        holder.tvnotes.setText(model.getNotes());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,AddNoteActivity.class);
                in.putExtra("notetext",model.getNotes());
                context.startActivity(in);
            }
        });

        // Deleting a note.
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure ?")
                        .setMessage("Your note will be deleted!")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        noteslist.remove(position);
                                        helper.deleteNotes(model.getNotes());
                                        notifyDataSetChanged();
                                    }
                                }
                        ).setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class customViewHolder extends RecyclerView.ViewHolder{
        TextView tvnotes;

        public customViewHolder(View itemView) {
            super(itemView);
            tvnotes = itemView.findViewById(R.id.note);
        }
    }

}
