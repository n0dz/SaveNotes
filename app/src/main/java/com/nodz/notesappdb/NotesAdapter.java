package com.nodz.notesappdb;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

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

        Dialog dialog = new Dialog(context);

        //Editing Note
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.edit_dialog);
                dialog.show();

                EditText newnote = dialog.findViewById(R.id.editNoteText);
                newnote.setText(holder.tvnotes.getText());
                Button btn = dialog.findViewById(R.id.updateNote);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        noteslist.remove(holder.getAdapterPosition());
                        helper.deleteNotes(model.getNotes());

                        model.setNotes(" "+newnote.getText().toString());
                        helper.insertNotes(" "+newnote.getText().toString());

                        noteslist.add(model);
                        notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });

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
