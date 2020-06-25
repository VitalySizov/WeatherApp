package ru.vitalysizov.weatherapp.presentation.selectCity.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Provider;

import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.vitalysizov.weatherapp.R;
import ru.vitalysizov.weatherapp.WeatherAppApplication;
import ru.vitalysizov.weatherapp.presentation.base.BaseFragment;
import ru.vitalysizov.weatherapp.presentation.main.view.MainFragmentArgs;
import ru.vitalysizov.weatherapp.presentation.selectCity.adapter.SelectCityAdapter;
import ru.vitalysizov.weatherapp.presentation.selectCity.mvp.SelectCityPresenter;

public class SelectCityFragment extends BaseFragment implements ISelectCityView, SelectCityAdapter.OnCityClickListener {

    private RecyclerView rvSelectCity;

    @InjectPresenter
    SelectCityPresenter presenter;

    @Inject
    Provider<SelectCityPresenter> presenterProvider;

    @ProvidePresenter
    SelectCityPresenter onBoardingPresenter() {
        return presenterProvider.get();
    }

    @Override
    public int layoutId() {
        return R.layout.fragment_select_city;
    }

    @Override
    public void performInject() {
        WeatherAppApplication.getAppComponent().inject(this);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvSelectCity = view.findViewById(R.id.rvSelectCity);
        initRecyclerView();
    }

    private void initRecyclerView() {
        SelectCityAdapter adapter = new SelectCityAdapter(Arrays.asList(getResources().getStringArray(R.array.manual_cities_list)), this);
        rvSelectCity.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvSelectCity.setAdapter(adapter);
    }

    @Override
    public void onCityClick(String city) {
        boolean isSelectFromMainScreen = true;

        if (getArguments() != null) {
            SelectCityFragmentArgs selectCityFragmentArgs = SelectCityFragmentArgs.fromBundle(getArguments());
            isSelectFromMainScreen = selectCityFragmentArgs.getIsSelectFromMainScreen();
        }

        MainFragmentArgs args = new MainFragmentArgs.Builder().setCity(city).build();

        if (isSelectFromMainScreen) {
            NavHostFragment.findNavController(this).navigate(R.id.action_selectCityFragment_to_mainFragment, args.toBundle());
        } else {
            NavHostFragment.findNavController(this).navigate(R.id.action_selectCityFragment_to_mainFragment_replace, args.toBundle());
        }
    }
}
