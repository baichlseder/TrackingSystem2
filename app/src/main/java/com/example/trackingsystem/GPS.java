package com.example.trackingsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;

public class GPS {
    double latitude;
    double longitude;
    String myDate;

    public GPS() { }

    public GPS(double latitude, double longitude, String myDate) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.myDate = myDate;
    }

    public GPS(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.myDate = getSysdate();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMyDate() {
        return myDate;
    }

    public void setMyDate(String myDate) {
        this.myDate = myDate;
    }

    @Override
    public String toString() {
        return "latitude=" + String.format("%.4f",latitude) +
                ", longitude=" + String.format("%.4f",longitude)+
                ", myDate=" + myDate +
                '}';
    }

    private static String getSysdate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.mm.yyyy  HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
