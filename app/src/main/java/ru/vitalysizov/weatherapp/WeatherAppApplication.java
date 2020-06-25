package ru.vitalysizov.weatherapp;

import android.app.Application;

import io.reactivex.plugins.RxJavaPlugins;
import ru.vitalysizov.weatherapp.di.AppComponent;
import ru.vitalysizov.weatherapp.di.AppModule;
import ru.vitalysizov.weatherapp.di.DaggerAppComponent;
import ru.vitalysizov.weatherapp.di.NetworkModule;

public class WeatherAppApplication extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.setErrorHandler(Throwable::printStackTrace);

        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }
}
