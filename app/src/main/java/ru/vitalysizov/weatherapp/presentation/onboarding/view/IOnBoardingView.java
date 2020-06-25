package ru.vitalysizov.weatherapp.presentation.onboarding.view;

import android.os.Bundle;

import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import ru.vitalysizov.weatherapp.presentation.base.IBaseView;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface IOnBoardingView extends IBaseView {

    void navigateToMainScreen(Bundle args);

    void navigateToSelectCityScreen();

}
