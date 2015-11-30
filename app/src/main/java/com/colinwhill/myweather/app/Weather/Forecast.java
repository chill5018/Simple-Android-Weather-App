package com.colinwhill.myweather.app.Weather;

import com.colinwhill.myweather.app.R;

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

    public static int getIconId(String icon){
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
}
