package com.colinwhill.myweather.app.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.*;
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

public class WeatherActivity extends Activity {

    public static final String TAG = WeatherActivity.class.getSimpleName();

    private Forecast forecast;

    @Bind(R.id.timeLabel) TextView timeLabel;
    @Bind(R.id.temperatureLabel) TextView temperatureLabel;
    @Bind(R.id.humidityValue) TextView humidityValue;
    @Bind(R.id.precipValue) TextView precipValue;
    @Bind(R.id.summaryLabel) TextView summaryLabel;
    @Bind(R.id.iconImageView) ImageView iconImageView;
    @Bind(R.id.refreshImageView) ImageView refreshImageView;
    @Bind(R.id.progressBar) ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        progressBar.setVisibility(View.INVISIBLE);

        final double lat = 55.6761;
        final double lon = 12.5683;

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

            // Set the request URL
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
        temperatureLabel.setText(forecast.getCurrent().getTemperature()+"");
        timeLabel.setText(forecast.getCurrent().getFormattedTime()+" it will be");
        humidityValue.setText(forecast.getCurrent().getHumidity()+"%");
        precipValue.setText(forecast.getCurrent().getPrecipChance()+"%");
        summaryLabel.setText(forecast.getCurrent().getSummary());

        Drawable drawable = getResources().getDrawable(forecast.getCurrent().getIconId());
        iconImageView.setImageDrawable(drawable);

    }

    private Forecast parseForecast(String jsonData) throws JSONException {
        Forecast forecast = new Forecast();

        forecast.setCurrent(getCurrentDetails(jsonData));
        forecast.setHourlyForcast(getHourlyForecast(jsonData));
        forecast.setDailyForcasts(getDailyForecast(jsonData));



        return forecast;
    }

    private Daily[] getDailyForecast(String jsonData) throws JSONException{
        JSONObject forecast = new JSONObject(jsonData);
        String timezone= forecast.getString("timezone");
        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data =  hourly.getJSONArray("data");

        Daily[] dayArray = new Daily[data.length()];

        for (int i = 0; i < data.length(); i++){
            JSONObject jsonHour = data.getJSONObject(i);

            Daily daily = new Daily();

            daily.setTime(jsonHour.getLong("time"));
            daily.setTempMax(jsonHour.getDouble("temperatureMax"));
            daily.setTempMin(jsonHour.getDouble("temperatureMin"));
            daily.setSunrise(jsonHour.getLong("sunriseTime"));
            daily.setSunset(jsonHour.getLong("sunsetTime"));
            daily.setIcon(jsonHour.getString("icon"));
            daily.setSummary(jsonHour.getString("summary"));
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

        Current current = new Current();
        current.setHumidity(currently.getDouble("humidity"));
        current.setTime(currently.getLong("time"));
        current.setTemperature(currently.getDouble("temperature"));
        current.setPrecipChance(currently.getDouble("precipProbability"));
        current.setIcon(currently.getString("icon"));
        current.setSummary(currently.getString("summary"));
        current.setTimeZone(timeZone);

        Log.d(TAG, current.getFormattedTime());

        return current;

    }

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

}