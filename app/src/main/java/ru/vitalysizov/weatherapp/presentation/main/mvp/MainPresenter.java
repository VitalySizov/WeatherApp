package ru.vitalysizov.weatherapp.presentation.main.mvp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import ru.vitalysizov.weatherapp.R;
import ru.vitalysizov.weatherapp.WeatherAppApplication;
import ru.vitalysizov.weatherapp.data.network.api.IWeatherApiService;
import ru.vitalysizov.weatherapp.data.network.model.WeatherCurrent.WeatherByCoordinatesResponse;
import ru.vitalysizov.weatherapp.data.network.model.WeatherForecast.DailyItem;
import ru.vitalysizov.weatherapp.data.network.model.WeatherForecast.ForecastResponse;
import ru.vitalysizov.weatherapp.domain.WeatherForecastItem;
import ru.vitalysizov.weatherapp.domain.WeatherItem;
import ru.vitalysizov.weatherapp.presentation.base.BasePresenter;
import ru.vitalysizov.weatherapp.presentation.main.view.IMainView;
import ru.vitalysizov.weatherapp.presentation.main.view.MainFragmentArgs;

@InjectViewState
public class MainPresenter extends BasePresenter<IMainView> {

    @Inject
    IWeatherApiService apiService;

    @Inject
    WeatherAppApplication appApplication;

    @Inject
    MainPresenter() {
    }

    private WeatherItem loadWeatherItem = new WeatherItem();
    public MainFragmentArgs args;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        String city = args.getCity();

        if (city != null && !city.isEmpty()) {
            getWeatherForecastByCity(city);
        } else {
            getViewState().getLocation();
        }
    }

    public void getWeatherForecastByLocation(double lat, double lon) {
        getViewState().showLoading(true);
        launch(Single.zip(apiService.getWeatherByCoordinates(lat, lon).subscribeOn(Schedulers.io()),
                apiService.getWeatherForecast(lat, lon).subscribeOn(Schedulers.io()), (weatherByCoordinatesResponse, forecastResponse) -> {
                    WeatherItem item = getCurrentWeatherItem(weatherByCoordinatesResponse);
                    item.setForecastItemsList(getDailyForecastList(forecastResponse));
                    return item;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherItem -> {
                    getViewState().showLoading(false);
                    getViewState().showError(false, "");
                    getViewState().showAllUiElements(true);
                    getViewState().setData(weatherItem);
                }, (error -> {
                    handleError(error);
                    getViewState().showLoading(false);
                    getViewState().showAllUiElements(false);
                    getViewState().showError(true, appApplication.getString(R.string.error_receiving_data));
                })));
    }

    public void getWeatherForecastByCity(String city) {
        getViewState().showLoading(true);
        launch(apiService.getWeatherByCity(city).subscribeOn(Schedulers.io())
                .flatMap((Function<WeatherByCoordinatesResponse, SingleSource<WeatherItem>>) item -> {
                    loadWeatherItem = getCurrentWeatherItem(item);
                    return Single.just(loadWeatherItem);
                }).flatMap((Function<WeatherItem, SingleSource<ForecastResponse>>)
                        weatherItem -> apiService.getWeatherForecast(weatherItem.getLat(), weatherItem.getLon()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(forecastResponse -> {
                    loadWeatherItem.setForecastItemsList(getDailyForecastList(forecastResponse));
                    getViewState().showLoading(false);
                    getViewState().showError(false, "");
                    getViewState().showAllUiElements(true);
                    getViewState().setData(loadWeatherItem);
                }, (error -> {
                    getViewState().showLoading(false);
                    getViewState().showAllUiElements(false);
                    getViewState().showError(true, appApplication.getString(R.string.error_receiving_data));
                    handleError(error);
                })));
    }

    private WeatherItem getCurrentWeatherItem(WeatherByCoordinatesResponse response) {
        return new WeatherItem(
                response.getName(),
                response.getCoordinateResponse().getLat(),
                response.getCoordinateResponse().getLon(),
                (int) response.getMainResponse().getTemp(),
                (int) response.getMainResponse().getTempMin(),
                response.getWeatherResponseList().get(0).getDescription(),
                response.getWeatherResponseList().get(0).getIcon());
    }

    private List<WeatherForecastItem> getDailyForecastList(ForecastResponse response) {
        List<WeatherForecastItem> weatherForecastItems = new ArrayList<>();
        for (DailyItem element : response.getDaily()) {
            weatherForecastItems.add(new WeatherForecastItem(
                    element.getWeather().get(0).getIcon(),
                    element.getDt(),
                    (int) element.getTemp().getDay(),
                    (int) element.getTemp().getNight(),
                    element.getWeather().get(0).getDescription()));
        }
        return weatherForecastItems;
    }


}
