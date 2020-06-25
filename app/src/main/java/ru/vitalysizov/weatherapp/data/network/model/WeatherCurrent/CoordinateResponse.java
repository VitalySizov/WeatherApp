package ru.vitalysizov.weatherapp.data.network.model.WeatherCurrent;

import com.google.gson.annotations.SerializedName;

public class CoordinateResponse {

    @SerializedName("lon")
    private double lon;

    @SerializedName("lat")
    private double lat;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}
