package ru.vitalysizov.weatherapp.data.local.prefs;

import android.content.SharedPreferences;

public class WeatherPreferences {

    public static final String FILE_PREFS = "FilePrefs";
    private static final String FIRST_RUN_PREFS = "FirstRunPrefs";

    private SharedPreferences prefs;

    public WeatherPreferences(SharedPreferences preferences) {
        this.prefs = preferences;
    }

    public void setFirstStart(boolean firstStart) {
        prefs.edit().putBoolean(FIRST_RUN_PREFS, firstStart).apply();
    }

    public boolean getFirstStart() {
        return prefs.getBoolean(FIRST_RUN_PREFS, false);
    }

}
