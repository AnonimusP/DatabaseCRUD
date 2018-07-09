package com.example.komputer.androidaplikacja2bazydanych;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Komputer on 30.12.2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bazaakwizytorow";
    private static final String TABLE_NAME= "akwizytorzy";
    private static final String KEY_ID = "_id";
    private static final String FIRSTNAME = "FirstName";
    private static final String LASTNAME = "LastName";
    private static final String CITY = "City";
    private static final String HOUSE_NUMBER = "Housenumber";
    private static final String STREET = "Street";
    private static final String PHONE = "Phone";

    public DBHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase dba){
        String CREATE_CITIZEN_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FIRSTNAME + " TEXT,"
                + LASTNAME + " TEXT,"
                + CITY + " TEXT,"
                + HOUSE_NUMBER + " INT,"
                + STREET + " TEXT,"
                + PHONE + " TEXT"
                +")";
        dba.execSQL(CREATE_CITIZEN_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase dba, int oldVersion, int newVersion) {
        dba.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(dba);
    }
    void AddWorker(String Firstname, String Lastname, String City, String Housenumber, String Street, String Phone){
        SQLiteDatabase dba = this.getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(FIRSTNAME,Firstname);
        wartosci.put(LASTNAME,Lastname);
        wartosci.put(CITY,City);
        wartosci.put(HOUSE_NUMBER,Housenumber);
        wartosci.put(STREET,Street);
        wartosci.put(PHONE,Phone);

        dba.insert(TABLE_NAME,null,wartosci);
    }
    public Cursor ShowWorkers() {
        SQLiteDatabase dba = this.getWritableDatabase();
        Cursor cursor = dba.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }

    public Cursor ShowWorker(String id) {
        SQLiteDatabase dba = this.getWritableDatabase();
        Cursor cursor = dba.rawQuery("SELECT * FROM " +TABLE_NAME+ " WHERE _id=" + id,null);
        return cursor;
    }
    void UpdateWorker(String id, String Firstname, String Lastname, String City, String Housenumber, String Street, String Phone){
        SQLiteDatabase dba = this.getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(KEY_ID,id);
        wartosci.put(FIRSTNAME,Firstname);
        wartosci.put(LASTNAME,Lastname);
        wartosci.put(CITY,City);
        wartosci.put(HOUSE_NUMBER,Housenumber);
        wartosci.put(STREET,Street);
        wartosci.put(PHONE,Phone);

        dba.update(TABLE_NAME,wartosci,"_id = ?",new String[] {id});
    }
    void DeleteWorker (String id) {
        SQLiteDatabase dba = this.getWritableDatabase();
        dba.delete(TABLE_NAME,"_id = ?",new String[] {id});
    }
}
