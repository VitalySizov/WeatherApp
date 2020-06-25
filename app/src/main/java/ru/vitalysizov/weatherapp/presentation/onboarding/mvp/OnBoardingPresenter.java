package ru.vitalysizov.weatherapp.presentation.onboarding.mvp;

import android.content.Context;

import javax.inject.Inject;

import moxy.InjectViewState;
import ru.vitalysizov.weatherapp.WeatherAppApplication;
import ru.vitalysizov.weatherapp.data.local.prefs.WeatherPreferences;
import ru.vitalysizov.weatherapp.presentation.base.BasePresenter;
import ru.vitalysizov.weatherapp.presentation.main.view.MainFragmentArgs;
import ru.vitalysizov.weatherapp.presentation.onboarding.view.IOnBoardingView;

@InjectViewState
public class OnBoardingPresenter extends BasePresenter<IOnBoardingView> {

    @Inject
    public OnBoardingPresenter() {
    }

    @Inject
    WeatherAppApplication appApplication;

    WeatherPreferences preferences;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        preferences = new WeatherPreferences(appApplication.getSharedPreferences(WeatherPreferences.FILE_PREFS, Context.MODE_PRIVATE));
    }

    public void clickToSelectCity() {
        preferences.setFirstStart(true);
        getViewState().navigateToSelectCityScreen();
    }

    public void clickToLocationGranted() {
        preferences.setFirstStart(true);
        MainFragmentArgs args = new MainFragmentArgs.Builder().setCity("").build();
        getViewState().navigateToMainScreen(args.toBundle());
    }
}
