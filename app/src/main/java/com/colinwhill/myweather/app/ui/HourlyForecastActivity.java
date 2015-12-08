package com.colinwhill.myweather.app.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.colinwhill.myweather.app.R;
import com.colinwhill.myweather.app.Weather.Hourly;
import com.colinwhill.myweather.app.adapters.HourlyAdapter;
import java.util.Arrays;

public class HourlyForecastActivity extends AppCompatActivity {

    private Hourly[] hours;

    // Inject the Hourly Vie with Butterknife

    @Bind(R.id.recyclerView) RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast);

        Intent intent = getIntent();

        //Transferring data from one activity to another
        Parcelable[] parcelables = intent.getParcelableArrayExtra(WeatherActivity.HOURLY_FORECAST);

        hours = Arrays.copyOf(parcelables, parcelables.length, Hourly[].class);

        ButterKnife.bind(this);

        HourlyAdapter adapter = new HourlyAdapter(hours);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

    }


}
