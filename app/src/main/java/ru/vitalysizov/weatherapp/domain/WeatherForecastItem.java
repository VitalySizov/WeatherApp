package ru.vitalysizov.weatherapp.domain;

public class WeatherForecastItem {

    private String mIcon;
    private int mDt;
    private int mDayTemp;
    private int mNightTemp;
    private String mDescription;

    public WeatherForecastItem(String mIcon, int mDt, int mDayTemp, int mNightTemp, String mDescription) {
        this.mIcon = mIcon;
        this.mDt = mDt;
        this.mDayTemp = mDayTemp;
        this.mNightTemp = mNightTemp;
        this.mDescription = mDescription;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String mIcon) {
        this.mIcon = mIcon;
    }

    public int getDt() {
        return mDt;
    }

    public void setDt(int mDt) {
        this.mDt = mDt;
    }

    public int getDayTemp() {
        return mDayTemp;
    }

    public void setDayTemp(int mDayTemp) {
        this.mDayTemp = mDayTemp;
    }

    public int getNightTemp() {
        return mNightTemp;
    }

    public void setNightTemp(int mNightTemp) {
        this.mNightTemp = mNightTemp;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
