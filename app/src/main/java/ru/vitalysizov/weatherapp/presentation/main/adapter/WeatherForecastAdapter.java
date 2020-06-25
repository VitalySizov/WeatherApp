package ru.vitalysizov.weatherapp.presentation.main.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ru.vitalysizov.weatherapp.R;
import ru.vitalysizov.weatherapp.domain.WeatherForecastItem;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastViewHolder> {

    private Calendar calendar = Calendar.getInstance(Locale.getDefault());
    private List<WeatherForecastItem> dailyItems = new ArrayList<>();

    public void setItems(List<WeatherForecastItem> items) {
        dailyItems.clear();
        dailyItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeatherForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_forecast, parent, false);
        return new WeatherForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherForecastViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        String icon = dailyItems.get(position).getIcon();
        Glide.with(context).load(context.getString(R.string.icon_image_mask, icon)).into(holder.ivWeather);

        holder.tvDate.setText(getDate(dailyItems.get(position).getDt()));
        holder.tvDayTemp.setText(context.getString(R.string.celsius_mask, dailyItems.get(position).getDayTemp()));
        holder.tvNightTemp.setText(context.getString(R.string.celsius_mask, dailyItems.get(position).getNightTemp()));
        holder.tvDescription.setText(dailyItems.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return dailyItems.size();
    }

    private String getDate(long time) {
        calendar.setTimeInMillis(time * 1000);
        return DateFormat.format("dd MMMM", calendar).toString();
    }
}
