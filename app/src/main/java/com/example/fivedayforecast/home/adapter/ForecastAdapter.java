package com.example.fivedayforecast.home.adapter;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fivedayforecast.Constants;
import com.example.fivedayforecast.R;
import com.example.fivedayforecast.Utils;
import com.example.fivedayforecast.model.Day;
import com.example.fivedayforecast.model.Forecast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private final List<Day> data;

    public ForecastAdapter() {
        data = new ArrayList<>();
    }

    public void setData(@NonNull List<Day> days) {
        Objects.requireNonNull(days);
        data.clear();
        data.addAll(days);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item,
                parent, false);
        return new ForecastViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ForecastViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_dayandtime)
        TextView dayAndTime;
        @BindView(R.id.tv_condition)
        TextView condition;
        @BindView(R.id.tv_clouds)
        TextView clouds;
        @BindView(R.id.tv_temparature)
        TextView temparature;
        @BindView(R.id.tv_wind)
        TextView wind;
        @BindView(R.id.iv_weather_icon)
        ImageView weatherIcon;

        private final Resources resources;

        ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            resources = itemView.getContext().getResources();
        }

        void bind(Day day) {
            Picasso.get().load(Constants.ICON_BASE_URL+day.weather.get(0).icon+Constants.ICON_EXTENSION)
                    .into(weatherIcon);
            dayAndTime.setText(Utils.getDateForLocaleFromUtc(day.dateAndTime));
            condition.setText(day.weather.get(0).description.toUpperCase());
            clouds.setText(resources.getString(R.string.cloud_percentage, day.clouds.all));
            temparature.setText(Utils.getCelsiusFromKelvin(day.main.temp));
            wind.setText(resources.getString(R.string.wind_speed,
                    Utils.roundDoubleToTwoDecimalPoints(day.wind.speed)));
        }


    }
}
