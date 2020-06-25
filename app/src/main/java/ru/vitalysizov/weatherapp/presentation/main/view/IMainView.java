package ru.vitalysizov.weatherapp.presentation.main.view;

import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;
import ru.vitalysizov.weatherapp.domain.WeatherItem;
import ru.vitalysizov.weatherapp.presentation.base.IBaseView;

public interface IMainView extends IBaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void getLocation();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showLoading(boolean show);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showError(boolean show, String text);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setData(WeatherItem weatherItem);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showAllUiElements(boolean show);

}
