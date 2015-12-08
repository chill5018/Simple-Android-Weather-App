package com.colinwhill.myweather.app.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.colinwhill.myweather.app.R;
import com.colinwhill.myweather.app.Weather.Historical;
import com.colinwhill.myweather.app.adapters.YearlyAdapter;

import java.util.Arrays;

public class HistoricalWeatherActivity extends AppCompatActivity {

    Historical[] years;

    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_weather);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(WeatherActivity.HISTORICAL_FORECAST);

        years = Arrays.copyOf(parcelables, parcelables.length, Historical[].class);

        YearlyAdapter adapter = new YearlyAdapter(this, years);
        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);


    }


}
