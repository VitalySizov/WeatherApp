package ru.vitalysizov.weatherapp.presentation.splash.view;

import androidx.navigation.fragment.NavHostFragment;

import javax.inject.Inject;
import javax.inject.Provider;

import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.vitalysizov.weatherapp.R;
import ru.vitalysizov.weatherapp.WeatherAppApplication;
import ru.vitalysizov.weatherapp.presentation.base.BaseFragment;
import ru.vitalysizov.weatherapp.presentation.splash.mvp.SplashPresenter;

public class SplashFragment extends BaseFragment implements ISplashView {

    @InjectPresenter
    SplashPresenter presenter;

    @Inject
    Provider<SplashPresenter> presenterProvider;

    @ProvidePresenter
    SplashPresenter splashPresenter() {
        return presenterProvider.get();
    }

    @Override
    public void performInject() {
        WeatherAppApplication.getAppComponent().inject(this);
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void navigateToOnBoardingScreen() {
        NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_onBoardingFragment);
    }

    @Override
    public void navigateToMainScreen() {
        NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_mainFragment);
    }
}
