package com.example.imperial_to_si.ui.imperialtosi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.imperial_to_si.R;
import com.example.imperial_to_si.database.Unit;
import com.example.imperial_to_si.database.UnitService;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ImperialToSIFragment extends Fragment {

    private ImperialToSIViewModel imperialToSIViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        imperialToSIViewModel =
                ViewModelProviders.of(this).get(ImperialToSIViewModel.class);
        View root = inflater.inflate(R.layout.fragment_imperial_to_si, container, false);
        final TextView textView = root.findViewById(R.id.text_imperial_to_si);
        imperialToSIViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final EditText amount = root.findViewById(R.id.imperial_amount);
        final Spinner imperialUnit = root.findViewById(R.id.imperial_unit);
        final TextView imperialResult = root.findViewById(R.id.result_imperial_to_si);
        final Button button = root.findViewById(R.id.submit_imperial);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Double imperialAmount = Double.parseDouble(amount.getText().toString());
                String unitName = imperialUnit.getSelectedItem().toString();

                SIUnit siUnit = imperialToSIUnit(imperialAmount, unitName);

                // TODO można by ten output sformatować tak żeby ładnie wyglądły liczby np. 1.10000023
                // String result = String.format("%.23f", siUnit.getValue()) + " " + siUnit.getName();

                // TODO trzeba zwiekszyc lekko wysokosc boxa do wyswietlania wyniku
                String result = siUnit.getValue() + " " + siUnit.getName();
                imperialResult.setText(result);

            }
        });

        return root;
    }

    private SIUnit imperialToSIUnit(Double imperialAmount, String unitName) {

        Unit unit = new UnitService(getContext())
                .findAllUnits()
                .stream()
                .filter(u -> u.getName().equals(unitName))
                .findFirst()
                .get();

        return new SIUnit(unit.getUnitSI(), imperialAmount * unit.getFactor());
    }

    @AllArgsConstructor
    @Getter
    private static class SIUnit {
        private String name;
        private Double value;
    }
}