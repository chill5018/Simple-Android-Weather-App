package com.colinwhill.myweather.app.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by colinhill on 11/25/15.
 */
public class Hourly implements Parcelable{
    private long time;
    private String summary;
    private double temperature;
    private String icon;
    private String timezone;

    public Hourly() {

    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    // #10 in the Hourly Adapter change return type from Double to Int and round
    public int getTemperature() {
        return (int) Math.round((temperature-32)/1.80);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    // #11 in the Hourly Adapter get icon ID from the utility we created in Forecast
    public int getIconId(){
        return Forecast.getIconId(icon);
    }

    // #9 in the Hourly Adapter
    public String getHour(){
        // They use "h a"
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        Date dateTime = new Date(getTime() * 1000);
        return formatter.format(dateTime);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(time);
        dest.writeDouble(temperature);
        dest.writeString(icon);
        dest.writeString(summary);
        dest.writeString(timezone);

    }

    private Hourly(Parcel in){
        time = in.readLong();
        temperature = in.readDouble();
        icon = in.readString();
        summary = in.readString();
        timezone = in.readString();

    }

    public static final Creator<Hourly> CREATOR = new Creator<Hourly>() {
        @Override
        public Hourly createFromParcel(Parcel source) {
            return new Hourly(source);
        }

        @Override
        public Hourly[] newArray(int size) {
            return new Hourly[size];
        }
    };
}
