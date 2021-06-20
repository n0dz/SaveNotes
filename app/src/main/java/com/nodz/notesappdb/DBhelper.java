package com.nodz.notesappdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBhelper extends SQLiteOpenHelper {

    final static String DBNAME = "NotesDB.db";
    final static String TABLE_NAME = "NotesTB";
    final static String COL_1 = "id";
    final static String COL_2 = "notes";
    final static int DBVERSION = 1;


    public DBhelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, notes TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNotes(String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, note);

        long results = db.insert(TABLE_NAME,null,values);

        if(results <= 0)
            return false;
        return true;
    }

    public ArrayList<NotesModel> getNotes(){
        ArrayList<NotesModel> notes = new ArrayList<>();
        ArrayList<NotesModel> id = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+TABLE_NAME,null);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                NotesModel model = new NotesModel();
                model.setId(cursor.getInt(0)+"");
                model.setNotes(cursor.getString(1));

                notes.add(model);
            }
        }
        cursor.close();
        db.close();
        return notes;
    }

    public boolean updateNote(String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //int id = getId(note);
        //values.put(COL_1,id);
        values.put(COL_2,note);

        long results = db.update(TABLE_NAME,values,"notes=?",new String[]{note});

        if(results <= 0)
            return false;
        return true;
    }

<<<<<<< HEAD
    public boolean updateData(String note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("note_text", note);

        long results = db.update("notes",values,"note_text=?",new String[]{note});

        if(results == -1)
            return false;
        return true;
    }

=======
	public void deleteNotes(String notes){
        SQLiteDatabase db = this.getWritableDatabase();
>>>>>>> 08cbcbf839462fda218a2cbba711ddbad65b48a1

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "notes=?", new String[]{notes});
        db.close();
    }
}
