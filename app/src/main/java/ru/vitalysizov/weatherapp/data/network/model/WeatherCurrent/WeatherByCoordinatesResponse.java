package ru.vitalysizov.weatherapp.data.network.model.WeatherCurrent;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherByCoordinatesResponse {

    @SerializedName("coord")
    private CoordinateResponse coordinateResponse;

    @SerializedName("weather")
    private List<WeatherItemResponse> weatherResponseList;

    @SerializedName("base")
    private String base;

    @SerializedName("main")
    private MainResponse mainResponse;

    @SerializedName("wind")
    private WindResponse windResponse;

    @SerializedName("clouds")
    private CloudsResponse cloudsResponse;

    @SerializedName("dt")
    private int dt;

    @SerializedName("sys")
    private SysResponse sysResponse;

    @SerializedName("timezone")
    private int timezone;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private int cod;

    public CoordinateResponse getCoordinateResponse() {
        return coordinateResponse;
    }

    public void setCoordinateResponse(CoordinateResponse coordinateResponse) {
        this.coordinateResponse = coordinateResponse;
    }

    public List<WeatherItemResponse> getWeatherResponseList() {
        return weatherResponseList;
    }

    public void setWeatherResponseList(List<WeatherItemResponse> weatherResponseList) {
        this.weatherResponseList = weatherResponseList;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainResponse getMainResponse() {
        return mainResponse;
    }

    public void setMainResponse(MainResponse mainResponse) {
        this.mainResponse = mainResponse;
    }

    public WindResponse getWindResponse() {
        return windResponse;
    }

    public void setWindResponse(WindResponse windResponse) {
        this.windResponse = windResponse;
    }

    public CloudsResponse getCloudsResponse() {
        return cloudsResponse;
    }

    public void setCloudsResponse(CloudsResponse cloudsResponse) {
        this.cloudsResponse = cloudsResponse;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public SysResponse getSysResponse() {
        return sysResponse;
    }

    public void setSysResponse(SysResponse sysResponse) {
        this.sysResponse = sysResponse;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
}
