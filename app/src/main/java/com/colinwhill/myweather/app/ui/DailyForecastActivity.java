package com.colinwhill.myweather.app.ui;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import com.colinwhill.myweather.app.R;
import com.colinwhill.myweather.app.Weather.Daily;
import com.colinwhill.myweather.app.adapters.DayAdapter;

public class DailyForecastActivity extends ListActivity {

    private Daily[] days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        DayAdapter adapter = new DayAdapter(this, days);


    }



}



