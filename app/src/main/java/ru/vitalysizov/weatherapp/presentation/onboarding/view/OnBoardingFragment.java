package ru.vitalysizov.weatherapp.presentation.onboarding.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.navigation.fragment.NavHostFragment;

import javax.inject.Inject;
import javax.inject.Provider;

import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.vitalysizov.weatherapp.R;
import ru.vitalysizov.weatherapp.WeatherAppApplication;
import ru.vitalysizov.weatherapp.presentation.base.BaseFragment;
import ru.vitalysizov.weatherapp.presentation.onboarding.mvp.OnBoardingPresenter;
import ru.vitalysizov.weatherapp.presentation.selectCity.view.SelectCityFragmentArgs;

public class OnBoardingFragment extends BaseFragment implements IOnBoardingView {

    private static final int LOCATION_REQUEST_CODE = 99;

    @InjectPresenter
    OnBoardingPresenter presenter;

    @Inject
    Provider<OnBoardingPresenter> presenterProvider;

    @ProvidePresenter
    OnBoardingPresenter onBoardingPresenter() {
        return presenterProvider.get();
    }

    @Override
    public void performInject() {
        WeatherAppApplication.getAppComponent().inject(this);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_onboarding;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppCompatButton mButtonRequest = view.findViewById(R.id.btnCurrentLocation);
        AppCompatButton mButtonChoiceManualLocation = view.findViewById(R.id.btnChoiceManualLocation);

        mButtonRequest.setOnClickListener(v -> requestLocationPermission());
        mButtonChoiceManualLocation.setOnClickListener(v -> presenter.clickToSelectCity());
    }

    @Override
    public void navigateToMainScreen(Bundle args) {
        NavHostFragment.findNavController(this).navigate(R.id.action_onBoardingFragment_to_mainFragment, args);
    }

    @Override
    public void navigateToSelectCityScreen() {
        SelectCityFragmentArgs args = new SelectCityFragmentArgs.Builder().setIsSelectFromMainScreen(false).build();
        NavHostFragment.findNavController(this).navigate(R.id.action_onBoardingFragment_to_selectCityFragment, args.toBundle());
    }

    public void requestLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                presenter.clickToLocationGranted();
            }
        }
    }
}
