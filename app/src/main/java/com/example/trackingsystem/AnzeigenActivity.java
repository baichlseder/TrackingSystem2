package com.example.trackingsystem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AnzeigenActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<GPS> adapter;
    private ArrayList<GPS> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anzeigen);

        initList();
        getListDataFromDB();
    }

    private void getListDataFromDB() {
        final String GET_GPS = "SELECT * FROM GPS";

        MySQLiteHelper dbHelper = new MySQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor rows = db.rawQuery(GET_GPS, null);

        while (rows.moveToNext()) {
            double lon = rows.getDouble(1);
            double lat = rows.getDouble(2);
            String date = rows.getString(3);
            GPS gps = new GPS(lon, lat, date);
            list.add(gps);
        }

        rows.close();
        db.close();
        adapter.notifyDataSetChanged();
    }

    private void initList() {
        list = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView = findViewById(R.id.listViewGPS);
        listView.setAdapter(adapter);
    }

    public void onBtnZurueckClicked(View v) {
        this.finish();
    }
}
