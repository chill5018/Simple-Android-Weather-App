package com.colinwhill.myweather.app.Weather;

/**
 * Created by colinhill on 11/25/15.
 */
public class Forecast {

    private Current current;
    private Hourly[] hourlyForcast;
    private Daily[] dailyForcasts;

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Hourly[] getHourlyForcast() {
        return hourlyForcast;
    }

    public void setHourlyForcast(Hourly[] hourlyForcast) {
        this.hourlyForcast = hourlyForcast;
    }

    public Daily[] getDailyForcasts() {
        return dailyForcasts;
    }

    public void setDailyForcasts(Daily[] dailyForcasts) {
        this.dailyForcasts = dailyForcasts;
    }
}
