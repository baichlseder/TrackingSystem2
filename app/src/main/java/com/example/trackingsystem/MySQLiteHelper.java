package com.example.trackingsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "GPS.db";
    private final static int DB_VERSION = 1;

    public MySQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_GPS_TABLE =
                "CREATE TABLE GPS" +
                        "(" +
                        "GpsID INTEGER PRIMARY KEY, " +
                        "Longitude REAL NOT NULL, " +
                        "Latitide REAL NOT NULL, " +
                        "Date TEXT NOT NULL" +
                        ")";

        db.execSQL(CREATE_GPS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String DROP_GPS = "DROP TABLE GPS";
        db.execSQL(DROP_GPS);
        onCreate(db);
    }
}
