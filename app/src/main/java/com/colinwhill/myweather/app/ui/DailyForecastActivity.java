package com.colinwhill.myweather.app.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.colinwhill.myweather.app.R;
import com.colinwhill.myweather.app.Weather.Daily;
import com.colinwhill.myweather.app.adapters.DayAdapter;

import java.util.Arrays;

public class DailyForecastActivity extends ListActivity {

    private Daily[] days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        Intent intent = getIntent();

        //Transferring data from one activity to another
        Parcelable[] parcelables = intent.getParcelableArrayExtra(WeatherActivity.DAILY_FORECAST);

        days = Arrays.copyOf(parcelables, parcelables.length, Daily[].class);

        DayAdapter adapter = new DayAdapter(this, days);

        setListAdapter(adapter);

    }



}



