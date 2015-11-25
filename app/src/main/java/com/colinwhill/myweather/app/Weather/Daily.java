package com.colinwhill.myweather.app.Weather;

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

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double temperature) {
        this.tempMax = temperature;
    }

    public double getTempMin() {
        return tempMin;
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
}
