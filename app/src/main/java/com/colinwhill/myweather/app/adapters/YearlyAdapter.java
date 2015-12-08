package com.colinwhill.myweather.app.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.colinwhill.myweather.app.R;
import com.colinwhill.myweather.app.Weather.Historical;

/**
 * Created by colinhill on 12/8/15.
 */
public class YearlyAdapter extends RecyclerView.Adapter<YearlyAdapter.YearlyViewHolder> {

    private Context context;
    private Historical[] years;

    public YearlyAdapter(Context context, Historical[] years){
        this.context = context;
        this.years = years;
    }

    @Override
    public YearlyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.historical_list_item, parent, false);
        YearlyViewHolder viewHolder = new YearlyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(YearlyViewHolder holder, int position) {
            holder.bindYear(years[position]);
    }

    @Override
    public int getItemCount() {
        return years.length;
    }

    public class YearlyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public TextView yearLabel;
        public TextView summaryLabel;
        public TextView tempMaxLabel;
        public TextView tempMinLabel;

        public YearlyViewHolder(View itemView) {
            super(itemView);

            yearLabel = (TextView) itemView.findViewById(R.id.yearLabel);
            summaryLabel = (TextView) itemView.findViewById(R.id.summaryLabel);
            tempMaxLabel = (TextView) itemView.findViewById(R.id.tempMaxLabel);
            tempMinLabel = (TextView) itemView.findViewById(R.id.tempMinLabel);

            itemView.setOnClickListener(this);
        }

        public void bindYear(Historical year){
            yearLabel.setText(year.getYear());
            summaryLabel.setText(year.getSummary());
            tempMaxLabel.setText(year.getTempMax() + "");
            tempMinLabel.setText(year.getTempMin() + "");

        }

        @Override
        public void onClick(View view) {

        }
    }
}
