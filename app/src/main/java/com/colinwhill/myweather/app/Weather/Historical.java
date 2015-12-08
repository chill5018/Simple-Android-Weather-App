package com.colinwhill.myweather.app.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by colinhill on 12/8/15.
 */
public class Historical implements Parcelable {
    private long time;
    private String summary;
    private double tempMax;
    private double tempMin;

    public Historical() { }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getYear(){
        SimpleDateFormat formatter = new SimpleDateFormat("y");

        Date dateTime = new Date(getTime() * 1000);
        return formatter.format(dateTime);
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTempMax() {
        return (int) Math.round((tempMax-32)/1.80);
    }

    public void setTempMax(double temperature) {
        this.tempMax = temperature;
    }

    public int getTempMin() {
        return (int) Math.round((tempMin-32)/1.80);
    }

    public void setTempMin(double temperature) {
        this.tempMin = temperature;
    }

    @Override
    public int describeContents(){return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeLong(time);
        dest.writeString(summary);
        dest.writeDouble(tempMax);
        dest.writeDouble(tempMin);
    }

    private Historical(Parcel in){
        time = in.readLong();
        summary = in.readString();
        tempMax = in.readDouble();
        tempMin = in.readDouble();

    }

    public static final Parcelable.Creator<Historical> CREATOR = new Parcelable.Creator<Historical>() {
        @Override
        public Historical createFromParcel(Parcel parcel) {
            return new Historical(parcel);
        }

        @Override
        public Historical[] newArray(int i) {
            return new Historical[i];
        }
    };

}
