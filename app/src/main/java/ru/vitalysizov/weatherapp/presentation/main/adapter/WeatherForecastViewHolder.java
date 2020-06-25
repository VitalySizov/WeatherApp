package ru.vitalysizov.weatherapp.presentation.main.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import ru.vitalysizov.weatherapp.R;

public class WeatherForecastViewHolder extends RecyclerView.ViewHolder {

    AppCompatImageView ivWeather;
    AppCompatTextView tvDate;
    AppCompatTextView tvDescription;
    AppCompatTextView tvDayTemp;
    AppCompatTextView tvNightTemp;

    public WeatherForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        ivWeather = itemView.findViewById(R.id.ivWeather);
        tvDate = itemView.findViewById(R.id.tvDate);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        tvDayTemp = itemView.findViewById(R.id.tvDayTemp);
        tvNightTemp = itemView.findViewById(R.id.tvNightTemp);
    }
}
