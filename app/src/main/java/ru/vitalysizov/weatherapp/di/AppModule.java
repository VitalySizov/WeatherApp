package ru.vitalysizov.weatherapp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.vitalysizov.weatherapp.WeatherAppApplication;

@Module
public class AppModule {

    private final WeatherAppApplication mApp;

    public AppModule(WeatherAppApplication mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    WeatherAppApplication provideApp() {
        return mApp;
    }
}
