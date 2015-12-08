package com.colinwhill.myweather.app.ui;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.*;
//import com.colinwhill.myweather.app.R;
import com.colinwhill.myweather.app.R;
import com.colinwhill.myweather.app.Weather.Current;
import com.colinwhill.myweather.app.Weather.Daily;
import com.colinwhill.myweather.app.Weather.Forecast;
import com.colinwhill.myweather.app.Weather.Hourly;
import com.squareup.okhttp.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class WeatherActivity extends AppCompatActivity {

    public static final String TAG = WeatherActivity.class.getSimpleName();
    public static final String DAILY_FORECAST = "DAILY_FORECAST";
    public static final String HOURLY_FORECAST = "HOURLY_FORECAST";

    private Forecast forecast;
    private SwipeRefreshLayout swipeLayout;

// Bind the Elements to the View using Butter Knife
    @Bind(R.id.timeLabel) TextView timeLabel;
    @Bind(R.id.temperatureLabel) TextView temperatureLabel;
    @Bind(R.id.humidityValue) TextView humidityValue;
    @Bind(R.id.precipValue) TextView precipValue;
    @Bind(R.id.summaryLabel) TextView summaryLabel;
    @Bind(R.id.iconImageView) ImageView iconImageView;
    @Bind(R.id.refreshImageView) ImageView refreshImageView;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.sunriseLabel) TextView sunriseLabel;
    @Bind(R.id.sunsetLabel) TextView sunsetLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        // Bind the elements to the view (Create the View)
        ButterKnife.bind(this);

        progressBar.setVisibility(View.INVISIBLE);

        final double lat = 55.6761;
        final double lon = 12.5683;

        // I need a method call that produces a GPS Coordinate For the GetForecast Method

        refreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForecast(lat, lon);
            }
        });

        getForecast(lat, lon);

    Log.d(TAG, "Main UI code is Running");


    }

    // This method is now ready to take the lat and long of a declared location and present that information to the user
    private void getForecast(double lat, double lon) {
        String apiKey = "4f8b93aa111efce8f04cf7b64fbe1464";
        String forecastURL = "https://api.forecast.io/forecast/" + apiKey + "/" +lat+ "," + lon;

        // Check for network connection
        if (isNetworkAvailable()) {
            toggleRefresh();

            // Set the Client for the Request
            OkHttpClient client = new OkHttpClient();

            // Set the request to the apiURL
            Request request = new Request.Builder()
                    .url(forecastURL)
                    .build();

            // Call the request
            Call call = client.newCall(request);
            call.enqueue(new Callback() {

                @Override
                public void onFailure(Request request, IOException e) {
                    toggleRefresh();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });

                    try {
                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            //Current weather information parsing JSON Data
                            forecast = parseForecast(jsonData);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //Updating the Display
                                    updateDisplay();
                                }
                            });

                        } else {
                            // Code for when Connection is Not Successful
                            alertUserAboutError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception Caught: ", e);
                    }
                    catch (JSONException jsE){
                        Log.e(TAG, "Exception Caught: ", jsE);

                    }

                }
            });
        }
        else {
            Toast.makeText(this, R.string.network_unavailable_message, Toast.LENGTH_LONG);
        }
    }

    private void toggleRefresh() {
        if (progressBar.getVisibility() == View.INVISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
            refreshImageView.setVisibility(View.INVISIBLE);
        } else{
            progressBar.setVisibility(View.INVISIBLE);
            refreshImageView.setVisibility(View.VISIBLE);
        }
    }

    private void updateDisplay() {
        // This updates the view with the newly assigned values
        temperatureLabel.setText(forecast.getCurrent().getTemperature()+"");
        timeLabel.setText(forecast.getCurrent().getFormattedTime()+"");
        humidityValue.setText(forecast.getCurrent().getHumidity()+"%");
        precipValue.setText(forecast.getCurrent().getPrecipChance()+"%");
        summaryLabel.setText(forecast.getCurrent().getSummary());
        sunriseLabel.setText(forecast.getCurrent().getSunriseFormatted()+"");
        sunsetLabel.setText(forecast.getCurrent().getSunsetFormatted()+"");

        Drawable drawable = getResources().getDrawable(forecast.getCurrent().getIconId());
        iconImageView.setImageDrawable(drawable);

    }

    private Forecast parseForecast(String jsonData) throws JSONException {
        //A Complete Forecast includes, current weather, hourly, and 7 day outlook
        Forecast forecast = new Forecast();

        forecast.setCurrent(getCurrentDetails(jsonData));
        forecast.setHourlyForcast(getHourlyForecast(jsonData));
        forecast.setDailyForcasts(getDailyForecast(jsonData));

        return forecast;
    }

    private Daily[] getDailyForecast(String jsonData) throws JSONException{
        JSONObject forecast = new JSONObject(jsonData);
        String timezone= forecast.getString("timezone");
        JSONObject day = forecast.getJSONObject("daily");
        JSONArray data =  day.getJSONArray("data");

        Daily[] dayArray = new Daily[data.length()];

        for (int i = 0; i < data.length(); i++){
            JSONObject jsonDay = data.getJSONObject(i);

            Daily daily = new Daily();

            daily.setTime(jsonDay.getLong("time"));
            daily.setTempMax(jsonDay.getDouble("temperatureMax"));
            daily.setTempMin(jsonDay.getDouble("temperatureMin"));
            daily.setSunrise(jsonDay.getLong("sunriseTime"));
            daily.setSunset(jsonDay.getLong("sunsetTime"));
            daily.setIcon(jsonDay.getString("icon"));
            daily.setSummary(jsonDay.getString("summary"));
            daily.setTimezone(timezone);

            dayArray[i] = daily;
        }
        return dayArray;
    }

    private Hourly[] getHourlyForecast(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone= forecast.getString("timezone");
        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data =  hourly.getJSONArray("data");

        Hourly[] hourArray = new Hourly[data.length()];

        for (int i = 0; i < data.length(); i++){
            JSONObject jsonHour = data.getJSONObject(i);

            Hourly hour = new Hourly();

            hour.setTime(jsonHour.getLong("time"));
            hour.setTemperature(jsonHour.getDouble("temperature"));
            hour.setIcon(jsonHour.getString("icon"));
            hour.setSummary(jsonHour.getString("summary"));
            hour.setTimezone(timezone);

            hourArray[i] = hour;
        }
        return hourArray;
    }

    private Current getCurrentDetails(String jsonData) throws JSONException{

        JSONObject forecast = new JSONObject(jsonData);

        String timeZone = forecast.getString("timezone");
        Log.i(TAG, "From JSON: "+timeZone);

        JSONObject currently = forecast.getJSONObject("currently");
        Daily[] d = getDailyForecast(jsonData);

        Current current = new Current();
        current.setHumidity(currently.getDouble("humidity"));
        current.setTime(currently.getLong("time"));
        current.setTemperature(currently.getDouble("temperature"));
        current.setPrecipChance(currently.getDouble("precipProbability"));
        current.setIcon(currently.getString("icon"));
        current.setSummary(currently.getString("summary"));
        current.setSunrise(d[0].getSunrise());
        current.setSunset(d[0].getSunset());
        current.setTimeZone(timeZone);

        Log.d(TAG, current.getFormattedTime());

        return current;

    }

    // Sufficient Test for Network Availability
    private boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (netInfo != null && netInfo.isConnected())
            isAvailable = true;

        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error dialog");
    }


    // Using Butter Knife and the On-Click Attribut to link the Daily Button to the View
    @OnClick (R.id.dailyButton)
    public void startDailyActivity(View view) {
        // This initiates the Daily view when the button is clicked
        Intent intent = new Intent(this, DailyForecastActivity.class);
        intent.putExtra(DAILY_FORECAST, forecast.getDailyForcasts());
        startActivity(intent);

    }

    @OnClick (R.id.hourlyButton)
    public void startHourlyActivity(View view){
        Intent intent = new Intent(this, HourlyForecastActivity.class);
        intent.putExtra(HOURLY_FORECAST, forecast.getHourlyForcast());
        startActivity(intent);
    }

}
