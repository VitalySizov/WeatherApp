package ru.vitalysizov.weatherapp.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import io.reactivex.plugins.RxJavaPlugins;
import ru.vitalysizov.weatherapp.R;
import ru.vitalysizov.weatherapp.WeatherAppApplication;
import ru.vitalysizov.weatherapp.presentation.base.BaseActivity;

public class AppActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}