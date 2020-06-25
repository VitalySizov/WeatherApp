package ru.vitalysizov.weatherapp.presentation.selectCity.mvp;

import javax.inject.Inject;

import moxy.InjectViewState;
import ru.vitalysizov.weatherapp.presentation.base.BasePresenter;
import ru.vitalysizov.weatherapp.presentation.selectCity.view.ISelectCityView;

@InjectViewState
public class SelectCityPresenter extends BasePresenter<ISelectCityView> {

    @Inject
    public SelectCityPresenter() {
    }

}
