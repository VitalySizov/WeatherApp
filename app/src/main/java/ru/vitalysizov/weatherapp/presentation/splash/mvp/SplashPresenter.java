package ru.vitalysizov.weatherapp.presentation.splash.mvp;


import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import moxy.InjectViewState;
import ru.vitalysizov.weatherapp.WeatherAppApplication;
import ru.vitalysizov.weatherapp.data.local.prefs.WeatherPreferences;
import ru.vitalysizov.weatherapp.presentation.base.BasePresenter;
import ru.vitalysizov.weatherapp.presentation.splash.view.ISplashView;

@InjectViewState
public class SplashPresenter extends BasePresenter<ISplashView> {

    @Inject
    public SplashPresenter() {
    }

    @Inject
    WeatherAppApplication appApplication;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        WeatherPreferences preferences = new WeatherPreferences(appApplication.getSharedPreferences(WeatherPreferences.FILE_PREFS, Context.MODE_PRIVATE));

        launch(Completable.timer(1, TimeUnit.SECONDS)
                .subscribe(() -> {
                    if (preferences.getFirstStart()) {
                        getViewState().navigateToMainScreen();
                    } else {
                        getViewState().navigateToOnBoardingScreen();
                    }
                }, this::handleError));
    }
}
