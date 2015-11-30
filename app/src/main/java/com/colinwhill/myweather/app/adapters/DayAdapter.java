package com.colinwhill.myweather.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.colinwhill.myweather.app.R;
import com.colinwhill.myweather.app.Weather.Daily;

/**
 * Created by colinhill on 11/30/15.
 */
public class DayAdapter extends BaseAdapter {

    private Context context;
    private Daily[] dayArray;

    public DayAdapter (Context context, Daily[] dayArray){

        this.context = context;
        this.dayArray = dayArray;
    }
    @Override
    public int getCount() {
        return dayArray.length;
    }

    @Override
    public Object getItem(int position) {
        return dayArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;//e arent going to use this. TAG items for easy reference
    }

    // This is the method that is used for recycling the list cells to make the application scroll
    // as smooth as possible and be very efficient
    // Conver vert view is the recycling view that gets called
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            // View is Empty or New
            convertView = LayoutInflater.from(context).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            holder.tempLabel = (TextView) convertView.findViewById(R.id.tempLabel);
            holder.dayLabel = (TextView) convertView.findViewById(R.id.dayNameLabel);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Daily day = dayArray[position];

        holder.iconImageView.setImageResource(day.getIconId());
        holder.tempLabel.setText(day.getTempMax() + "" );
        holder.dayLabel.setText(day.getDOTW()); // get the day of the week

        return convertView;
    }

    private static class ViewHolder{
        ImageView iconImageView;
        TextView tempLabel;
        TextView dayLabel;

    }
}
