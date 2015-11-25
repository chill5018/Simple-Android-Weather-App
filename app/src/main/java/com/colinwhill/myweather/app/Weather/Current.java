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

        int iconId = R.drawable.clear_day;

        if (icon.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        }
        else if (icon.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if (icon.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (icon.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (icon.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (icon.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (icon.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (icon.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (icon.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (icon.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }
        return iconId;
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

    public int getTemperature() {
        return (int) Math.round(temperature);
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
