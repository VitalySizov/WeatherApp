package ru.vitalysizov.weatherapp.data.network.model.WeatherCurrent;

import com.google.gson.annotations.SerializedName;

public class SysResponse {

    @SerializedName("country")
    private String country;

    @SerializedName("sunrise")
    private int sunrise;

    @SerializedName("sunset")
    private int sunset;
}
