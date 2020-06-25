package ru.vitalysizov.weatherapp.presentation.selectCity.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import ru.vitalysizov.weatherapp.R;

public class SelectCityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    SelectCityAdapter.OnCityClickListener onCityClickListener;
    AppCompatTextView tvCityName;

    public SelectCityViewHolder(@NonNull View itemView, SelectCityAdapter.OnCityClickListener cityClickListener) {
        super(itemView);
        this.onCityClickListener = cityClickListener;
        itemView.setOnClickListener(this);
        tvCityName = itemView.findViewById(R.id.tvCityName);
    }

    @Override
    public void onClick(View v) {
        onCityClickListener.onCityClick(tvCityName.getText().toString() );
    }
}
