package com.colinwhill.myweather.app.Weather;

import com.colinwhill.myweather.app.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by colinhill on 11/23/15.
 */
public class Current {
    private String icon;
    private long time;
    private double temperature;
    private double humidity;
    private double precipChance;
    private String summary;
    private String timeZone;
    private long sunrise;
    private long sunset;


    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getIcon() {
        return icon;
    }

    public int getIconId(){
        // All the previous code with the icon stings went to forecast
        // Now we have a cleaner and more global use of the iconID for the Daily and Hourly classes

        return Forecast.getIconId(icon);

    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getTime() {
        return time;
    }

    public String getFormattedTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));

        Date dateTime = new Date(getTime() * 1000);
        return formatter.format(dateTime);
    }

    public void setTime(long time) {
        this.time = time;
    }



    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public String getSunriseFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));

        Date dateTime = new Date(getSunrise() * 1000);
        return formatter.format(dateTime);
    }

    public String getSunsetFormatted() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        formatter.setTimeZone(TimeZone.getTimeZone(getTimeZone()));

        Date dateTime = new Date(getSunset() * 1000);
        return formatter.format(dateTime);
    }

    public int getTemperature() {

        return (int) Math.round((temperature-32)/1.80);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return (int) Math.round(humidity*100);
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getPrecipChance() {
        return (int) Math.round(precipChance*100);
    }

    public void setPrecipChance(double precipChance) {
        this.precipChance = precipChance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


}
