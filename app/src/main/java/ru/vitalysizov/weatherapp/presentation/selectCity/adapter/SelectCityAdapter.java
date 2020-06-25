package ru.vitalysizov.weatherapp.presentation.selectCity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.vitalysizov.weatherapp.R;

public class SelectCityAdapter extends RecyclerView.Adapter<SelectCityViewHolder> {

    public OnCityClickListener onCityClickListener;
    private List<String> cities;

    public SelectCityAdapter(List<String> items, OnCityClickListener onCityClickListener) {
        this.cities = items;
        this.onCityClickListener = onCityClickListener;
    }

    @NonNull
    @Override
    public SelectCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_city, parent, false);
        return new SelectCityViewHolder(view, onCityClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectCityViewHolder holder, int position) {
        holder.tvCityName.setText(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public interface OnCityClickListener {
        void onCityClick(String city);
    }
}
