package com.nodz.notesappdb;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
<<<<<<< HEAD
import android.graphics.Color;
=======
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
>>>>>>> 08cbcbf839462fda218a2cbba711ddbad65b48a1
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.crowdfire.cfalertdialog.CFAlertDialog;

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

        NotesModel model = noteslist.get(position);

        Log.i("ID is",model.getId()+"THIS IS ID");
        holder.tvnotes.setText(model.getNotes());
<<<<<<< HEAD

        if(position%2==0) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffff99"));
            holder.tvnotes.setTextColor(ContextCompat.getColor(context, R.color.Thinkpurple));
        }
        else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffcc66"));
            holder.tvnotes.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        // First deleting the selected text and saving the updated text .
=======
        holder.tvnoteId.setText(model.getId());

>>>>>>> 08cbcbf839462fda218a2cbba711ddbad65b48a1
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                Intent in = new Intent(context,EditNoteActivity.class);
                in.putExtra("text",model.getNotes()+" ");
                //in.putExtra("type",1);
                noteslist.remove(position);
                helper.deleteNotes(model.getNotes());
=======
                Intent in = new Intent(context,AddNoteActivity.class);
                in.putExtra("id",model.getId());
                in.putExtra("type",2);
>>>>>>> 08cbcbf839462fda218a2cbba711ddbad65b48a1
                context.startActivity(in);
                notifyDataSetChanged();
            }
        });

        holder.showImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Alert using Builder
                CFAlertDialog.Builder builder = new CFAlertDialog.Builder(context)
                        // NOTIFICATION,ALERT or Bottomsheet
                        .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                        .setMessage(model.getNotes()).setTextColor(Color.parseColor("#FF6200EE"));
                // Show the alert

                builder.addButton("Edit", Color.parseColor("#FFFFFF"), Color.parseColor("#429ef4"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.CENTER, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(context,EditNoteActivity.class);
                        i.putExtra("text_note",model.getNotes());
                        i.putExtra("type",6);
                        context.startActivity(i);
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        // Deleting a note.
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
<<<<<<< HEAD
                CFAlertDialog.Builder builder = new CFAlertDialog.Builder(context)
                        // NOTIFICATION,ALERT or Bottomsheet
                        .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                        .setTitle("Are you sure ")
                        .setMessage("Your note will be deleted!");

                builder.addButton("Yes", Color.parseColor("#FFFFFF"), Color.parseColor("#429ef4"), CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            noteslist.remove(position);
                            helper.deleteNotes(model.getNotes());
                            notifyDataSetChanged();
                            dialog.dismiss();
                    }
                });

                builder.addButton("No", Color.parseColor("#FFFFFF"), Color.parseColor("#429ef4"), CFAlertDialog.CFAlertActionStyle.NEGATIVE, CFAlertDialog.CFAlertActionAlignment.JUSTIFIED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
=======
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
>>>>>>> 08cbcbf839462fda218a2cbba711ddbad65b48a1
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class customViewHolder extends RecyclerView.ViewHolder{
<<<<<<< HEAD
        TextView tvnotes;
        CardView cardView;
        ImageButton showImgBtn;
=======
        TextView tvnotes,tvnoteId;
>>>>>>> 08cbcbf839462fda218a2cbba711ddbad65b48a1

        public customViewHolder(View itemView) {
            super(itemView);
            tvnotes = itemView.findViewById(R.id.note);
<<<<<<< HEAD
            cardView = itemView.findViewById(R.id.cardView);
            showImgBtn = itemView.findViewById(R.id.showNote);
=======
            tvnoteId = itemView.findViewById(R.id.noteId);
>>>>>>> 08cbcbf839462fda218a2cbba711ddbad65b48a1
        }
    }/*
    public void alertDialog(){
        LayoutInflater factory = LayoutInflater.from(context);
        final View view = factory.inflate(R.layout.layout_alert_dialog, null);
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setView(view);
        dialog.show();

    }*/
}
