package ru.vitalysizov.weatherapp.domain;

import java.util.List;

public class WeatherItem {

    private String mName;
    private double mLat;
    private double mLon;
    private int mCurrentTemp;
    private int mMinTemp;
    private String mDescription;
    private String mIcon;
    private List<WeatherForecastItem> forecastItemsList = null;

    public WeatherItem() {
    }

    public WeatherItem(String mName, double mLat, double mLon, int mCurrentTemp, int mMinTemp, String mDescription, String mIcon) {
        this.mName = mName;
        this.mLat = mLat;
        this.mLon = mLon;
        this.mCurrentTemp = mCurrentTemp;
        this.mMinTemp = mMinTemp;
        this.mDescription = mDescription;
        this.mIcon = mIcon;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public double getLat() {
        return mLat;
    }

    public void setLat(long mLat) {
        this.mLat = mLat;
    }

    public double getLon() {
        return mLon;
    }

    public void setLon(long mLon) {
        this.mLon = mLon;
    }

    public int getCurrentTemp() {
        return mCurrentTemp;
    }

    public void setCurrentTemp(int mCurrentTemp) {
        this.mCurrentTemp = mCurrentTemp;
    }

    public int getMinTemp() {
        return mMinTemp;
    }

    public void setMinTemp(int mMinTemp) {
        this.mMinTemp = mMinTemp;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public List<WeatherForecastItem> getForecastItemsList() {
        return forecastItemsList;
    }

    public void setForecastItemsList(List<WeatherForecastItem> forecastItemsList) {
        this.forecastItemsList = forecastItemsList;
    }
}
