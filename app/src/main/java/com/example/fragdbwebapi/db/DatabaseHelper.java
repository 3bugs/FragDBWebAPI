package com.example.fragdbwebapi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Promlert on 7/23/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Contacts";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_PHONE = "phone";
    public static final String COL_PICTURE = "picture";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_NAME + " TEXT, "
            + COL_PHONE + " TEXT, "
            + COL_PICTURE + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("DatabaseHelper", SQL_CREATE_TABLE);
        db.execSQL(SQL_CREATE_TABLE);

        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "พร้อมเลิศ หล่อวิจิตร");
        cv.put(COL_PHONE, "085-058-1776");
        cv.put(COL_PICTURE, "promlert.png");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "Android Studio");
        cv.put(COL_PHONE, "081-234-5678");
        cv.put(COL_PICTURE, "android.png");
        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
