package ru.vitalysizov.weatherapp.data.network.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vitalysizov.weatherapp.data.network.model.WeatherCurrent.WeatherByCoordinatesResponse;
import ru.vitalysizov.weatherapp.data.network.model.WeatherForecast.ForecastResponse;

public interface IWeatherApiService {

    @GET("data/2.5/weather")
    Single<WeatherByCoordinatesResponse> getWeatherByCoordinates(@Query("lat") double lat, @Query("lon") double lon);

    @GET("data/2.5/weather")
    Single<WeatherByCoordinatesResponse> getWeatherByCity(@Query("q") String cityName);

    @GET("data/2.5/onecall")
    Single<ForecastResponse> getWeatherForecast(@Query("lat") double lat, @Query("lon") double lon);
}
