package com.colinwhill.myweather.app.ui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.colinwhill.myweather.app.R;
import com.colinwhill.myweather.app.Weather.Daily;
import com.colinwhill.myweather.app.adapters.DayAdapter;

import java.util.Arrays;

public class DailyForecastActivity extends Activity {

    // Refactor from ListActivity to Activity
    @Bind(android.R.id.list) ListView listView;
    @Bind(android.R.id.empty) TextView emptyTextView;

    private Daily[] days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        //Transferring data from one activity to another
        Parcelable[] parcelables = intent.getParcelableArrayExtra(WeatherActivity.DAILY_FORECAST);

        days = Arrays.copyOf(parcelables, parcelables.length, Daily[].class);

        DayAdapter adapter = new DayAdapter(this, days);

//        setListAdapter(adapter);

        listView.setAdapter(adapter);
        listView.setEmptyView(emptyTextView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Create A Toast When the User Clicks on A Day

                String DOTW = days[position].getDOTW();
                String conditions = days[position].getSummary();
                String maxTemp = days[position].getTempMax()+"";
                String minTemp = days[position].getTempMin()+"";
                String sunrise = days[position].getSunriseFormatted();
                String sunset = days[position].getSunsetFormatted();

                String message = String.format("On %s Sunrise will be at: %s and will set at: %s. The High will be %s," +
                                " and Low will be %s. Throughout the day it will be %s",
                        DOTW, sunrise, sunset, maxTemp, minTemp, conditions);

                Toast.makeText(DailyForecastActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }



}



