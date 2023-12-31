
package com.example.qurandiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "StudentLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "student_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "student_name";
    private static final String COLUMN_AGE = "student_age";
    private static final String COLUMN_CLASS = "student_class";
    private static final String COLUMN_SABAQ_PARA = "sabaq_para";

    private static final String COLUMN_SABQI = "sabqi";

    private static final String COLUMN_MANZIL = "manzil";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " Text, " +
                COLUMN_CLASS + " TEXT, " +
                COLUMN_SABAQ_PARA + " Text,"+
                COLUMN_SABQI + " TEXT, " +
                COLUMN_MANZIL + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    <string> void addStudent(String name, string age, String studentClass, string sabaqPara, String sabqi, String manzil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, (String) age);
        cv.put(COLUMN_CLASS, studentClass);
        cv.put(COLUMN_SABAQ_PARA, (String) sabaqPara);
        cv.put(COLUMN_SABQI, sabqi);
        cv.put(COLUMN_MANZIL, manzil);




        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Student Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public <string> void updateData(String row_id, String name, string age, String studentClass, string sabaqPara, string sabqi, string manzil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, (String) age);
        cv.put(COLUMN_CLASS, studentClass);
        cv.put(COLUMN_SABAQ_PARA, (String) sabaqPara);
        cv.put(COLUMN_SABQI, (String) sabqi);
        cv.put(COLUMN_MANZIL, (String) manzil);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Student Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(int row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(row_id)});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Student Deleted Successfully.", Toast.LENGTH_SHORT).show();
        }
    }


    void deleteAllData() {


        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}

