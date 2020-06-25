package ru.vitalysizov.weatherapp.presentation.splash.view;

import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import ru.vitalysizov.weatherapp.presentation.base.IBaseView;

public interface ISplashView extends IBaseView {

    @StateStrategyType(SkipStrategy.class)
    void navigateToOnBoardingScreen();

    @StateStrategyType(SkipStrategy.class)
    void navigateToMainScreen();

}
