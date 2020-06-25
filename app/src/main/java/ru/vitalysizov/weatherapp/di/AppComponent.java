package ru.vitalysizov.weatherapp.di;


import javax.inject.Singleton;

import dagger.Component;
import ru.vitalysizov.weatherapp.presentation.main.view.MainFragment;
import ru.vitalysizov.weatherapp.presentation.onboarding.view.OnBoardingFragment;
import ru.vitalysizov.weatherapp.presentation.selectCity.view.SelectCityFragment;
import ru.vitalysizov.weatherapp.presentation.splash.view.SplashFragment;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainFragment fragment);

    void inject(OnBoardingFragment fragment);

    void inject(SplashFragment fragment);

    void inject(SelectCityFragment fragment);
}
