package com.colinwhill.myweather.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.colinwhill.myweather.app.R;
import com.colinwhill.myweather.app.Weather.Daily;
import com.colinwhill.myweather.app.Weather.Hourly;
import org.w3c.dom.Text;

/**
 * Created by colinhill on 12/7/15.
 */

// 1. First Create the Class
public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.HourViewHolder> { // 3. Add the View Holder to Adapter

    // 13. Create Member variable to hold the Array
    private Hourly[] hours;
    private Context context;

    // 12. Need a Constructor
    public HourlyAdapter(Context context, Hourly[] hours ){
        this.context = context;
        this.hours = hours;

    }

    // 4. Auto Implement methods
    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //15. Inflate the Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item, parent, false);

        HourViewHolder viewHolder = new HourViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        // 16. The Bridge Between the Adapter and the bind method we created in viewholder class
        holder.bindHour(hours[position]);

    }

    @Override
    public int getItemCount() {
        // 14. Return length of the hours Array
        return hours.length;
    }
// 2. Create the View Holder for Recycler View
    public class HourViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

    // 5. Set the Layout

    public TextView timeLabel;
    public TextView summaryLabel;
    public TextView temperatureLabel;
    public ImageView iconImageView;

    // 6. View Constructor
     public HourViewHolder(View itemView) {
         super(itemView);

         timeLabel = (TextView) itemView.findViewById(R.id.timeLabel);
         summaryLabel = (TextView) itemView.findViewById(R.id.summaryLabel);
         temperatureLabel = (TextView) itemView.findViewById(R.id.temperatureLabel);
         iconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);

         // Set the Onclick listener for th recycler view
         itemView.setOnClickListener(this);
     }

    // 7. Map the data to the view --> Similar to the getView Method

    public void bindHour(Hourly hour){
        // 8. Go to Hourly Class to Massage the Data
        // 9. The Time should be represented only as an Hour
        // 10. Round the Temp to the nearest Int
        // 11. Convert Icon String to an int for the correct drawable

        timeLabel.setText(hour.getHour());
        summaryLabel.setText(hour.getSummary());
        temperatureLabel.setText(hour.getTemperature() + "");
        iconImageView.setImageResource(hour.getIconId());

    }

    @Override
    public void onClick(View view) {
        String time = timeLabel.getText().toString();
        String temp = temperatureLabel.getText().toString();
        String summary = summaryLabel.getText().toString();

        String message = String.format("At %s the temperature will be %s, and %s", time, temp, summary);

        Toast.makeText(context, message,Toast.LENGTH_LONG).show();
    }
}
}
