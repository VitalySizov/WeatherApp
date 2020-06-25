package ru.vitalysizov.weatherapp.presentation.main.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import javax.inject.Inject;
import javax.inject.Provider;

import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.vitalysizov.weatherapp.R;
import ru.vitalysizov.weatherapp.WeatherAppApplication;
import ru.vitalysizov.weatherapp.domain.WeatherItem;
import ru.vitalysizov.weatherapp.presentation.base.BaseFragment;
import ru.vitalysizov.weatherapp.presentation.main.adapter.WeatherForecastAdapter;
import ru.vitalysizov.weatherapp.presentation.main.mvp.MainPresenter;

public class MainFragment extends BaseFragment implements IMainView {

    private static final int LOCATION_REQUEST_CODE = 100;

    private AppCompatImageView ivWeatherIcon;
    private AppCompatTextView tvCurrentTemp;
    private AppCompatTextView tvDescription;
    private AppCompatTextView tvNightTimeTemp;
    private RecyclerView rvWeatherForecast;
    private Toolbar toolbar;
    private ProgressBar pbLoading;
    private LinearLayoutCompat linearLayoutError;
    private AppCompatTextView tvError;

    FusedLocationProviderClient locationProviderClient;
    private WeatherForecastAdapter weatherForecastAdapter = new WeatherForecastAdapter();

    @InjectPresenter
    MainPresenter presenter;

    @Inject
    Provider<MainPresenter> presenterProvider;

    @ProvidePresenter
    MainPresenter mainPresenter() {
        MainPresenter presenter = presenterProvider.get();
        if (getArguments() != null) {
            presenter.args = MainFragmentArgs.fromBundle(getArguments());
        }
        return presenter;
    }

    @Override
    public void performInject() {
        WeatherAppApplication.getAppComponent().inject(this);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivWeatherIcon = view.findViewById(R.id.ivWeatherIcon);
        tvCurrentTemp = view.findViewById(R.id.tvCurrentTemp);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvNightTimeTemp = view.findViewById(R.id.tvNightTimeTemp);
        toolbar = view.findViewById(R.id.toolbar);
        pbLoading = view.findViewById(R.id.pb_loading);
        linearLayoutError = view.findViewById(R.id.linearLayoutError);
        tvError = view.findViewById(R.id.tvError);
        rvWeatherForecast = view.findViewById(R.id.rvWeatherForecast);
        rvWeatherForecast.setAdapter(weatherForecastAdapter);

        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.currentLocation: {
                    presenter.getViewState().getLocation();
                    return true;
                }
                case R.id.cityLocation: {
                    NavHostFragment.findNavController(MainFragment.this).navigate(R.id.action_mainFragment_to_selectCityFragment);
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                presenter.getViewState().getLocation();
            }
        }
    }

    @Override
    public void getLocation() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            presenter.getViewState().showAllUiElements(false);
            presenter.getViewState().showError(true, getResources().getString(R.string.error_location));
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            locationProviderClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    presenter.getWeatherForecastByLocation(location.getLatitude(), location.getLongitude());
                }
            });
        }
    }

    @Override
    public void showLoading(boolean show) {
        if (show) {
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showError(boolean show, String text) {
        if (show) {
            linearLayoutError.setVisibility(View.VISIBLE);
            tvError.setText(text);
        } else {
            linearLayoutError.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showAllUiElements(boolean show) {
        if (show) {
            ivWeatherIcon.setVisibility(View.VISIBLE);
            tvCurrentTemp.setVisibility(View.VISIBLE);
            tvDescription.setVisibility(View.VISIBLE);
            tvNightTimeTemp.setVisibility(View.VISIBLE);
            rvWeatherForecast.setVisibility(View.VISIBLE);
        } else {
            toolbar.setTitle("");
            ivWeatherIcon.setVisibility(View.INVISIBLE);
            tvCurrentTemp.setVisibility(View.INVISIBLE);
            tvDescription.setVisibility(View.INVISIBLE);
            tvNightTimeTemp.setVisibility(View.INVISIBLE);
            rvWeatherForecast.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setData(WeatherItem weatherItem) {
        toolbar.setTitle(weatherItem.getName());
        Glide.with(this).load(getResources().getString(R.string.icon_image_mask, weatherItem.getIcon())).into(ivWeatherIcon);
        tvCurrentTemp.setText(getResources().getString(R.string.celsius_mask, weatherItem.getCurrentTemp()));
        tvNightTimeTemp.setText(getResources().getString(R.string.celsius_mask, weatherItem.getMinTemp()));
        tvDescription.setText(weatherItem.getDescription());

        weatherForecastAdapter.setItems(weatherItem.getForecastItemsList());
        rvWeatherForecast.setLayoutManager(new LinearLayoutManager(requireContext()));
    }


}
