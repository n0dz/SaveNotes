package com.nodz.notesappdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBhelper extends SQLiteOpenHelper {

    final static String DBNAME = "NotesDB.db";
    final static String TABLE_NAME = "notes";
    final static int DBVERSION = 1;

    public DBhelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table "+TABLE_NAME+"(id INTEGER primary key autoincrement, note_text TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists notes");
        onCreate(db);
    }

    public boolean insertNotes(String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("note_text", note);

        long results = db.insert("notes",null,values);

        if(results <= 0)
            return false;
        return true;
    }

    public ArrayList<NotesModel> getNotes(){
        ArrayList<NotesModel> notes = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from notes",null);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                NotesModel model = new NotesModel();
                model.setNotes(cursor.getString(1));
                notes.add(model);
            }
        }
        cursor.close();
        db.close();
        return notes;
    }

    public void deleteNotes(String notes){
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "note_text=?", new String[]{notes});
        db.close();

    }

    public void updateNotes() {

    }


}
