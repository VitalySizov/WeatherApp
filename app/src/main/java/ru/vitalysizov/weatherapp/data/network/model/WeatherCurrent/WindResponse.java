package ru.vitalysizov.weatherapp.data.network.model.WeatherCurrent;

import com.google.gson.annotations.SerializedName;

public class WindResponse {

    @SerializedName("speed")
    private double speed;

    @SerializedName("deg")
    private double deg;

}
