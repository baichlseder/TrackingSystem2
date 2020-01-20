package com.example.trackingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private static LocationManager locMan = null;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
        Button btnShow = findViewById(R.id.btnAnzeigen);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnzeigenActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        locMan.removeUpdates(this);
    }
    @Override
    public void onLocationChanged(Location location) {
    if(location==null) return;
    displayLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    private void displayLocation(Location location){
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        GPS gps = new GPS(latitude,longitude);

        EditText edLat = findViewById(R.id.editTextLat);
        EditText edLong = findViewById(R.id.editTextLon);
        EditText edDate = findViewById(R.id.editTextDate);


        edLat.setText(String.format("%.4f",latitude));
        edLong.setText(String.format("%.4f",longitude));
        edDate.setText(gps.getMyDate());

        final String insert_GPS = "INSERT INTO GPS (GpsID,Longitude,Latitide,Date) VALUES(?,?,?,?)";
        final String GET_COUNTID = "SELECT COUNT(*) FROM GPS";
        MySQLiteHelper dbHelper = new MySQLiteHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor row = db.rawQuery(GET_COUNTID,null);
        int id=0;
        if(row.moveToNext()){
            id=row.getInt(0)+1;
        }
        db.rawQuery(insert_GPS,new String[]{String.valueOf(id), String.valueOf(longitude), String.valueOf(latitude),gps.getMyDate()});



    }



}
