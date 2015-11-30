package com.colinwhill.myweather.app.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.SimpleFormatter;

/**
 * Created by colinhill on 11/25/15.
 */
public class Daily {
    private long time;
    private String summary;
    private double tempMax;
    private double tempMin;
    private String icon;
    private String timezone;
    private long sunrise;
    private long sunset;

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

    public String getIcon() {
        return icon;
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

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public int getIconId(){
        return Forecast.getIconId(icon);
    }

    public String getDOTW(){

        SimpleDateFormat formatter= new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));

        Date dateTime = new Date(time * 1000); // In sec need millisec so * 1000

        return formatter.format(dateTime);
    }

    public String getSunriseFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimezone()));

        Date dateTime = new Date(getSunrise() * 1000);
        return formatter.format(dateTime);
    }

    public String getSunsetFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimezone()));

        Date dateTime = new Date(getSunset() * 1000);
        return formatter.format(dateTime);
    }
}
