package com.example.imperial_to_si.ui.sitoimperial;

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

public class SIToImperialFragment extends Fragment {

    private SIToImperialViewModel SIToImperialViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SIToImperialViewModel =
                ViewModelProviders.of(this).get(SIToImperialViewModel.class);
        View root = inflater.inflate(R.layout.fragment_si_to_imperial, container, false);
        final TextView textView = root.findViewById(R.id.text_si_to_imperial);
        SIToImperialViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final EditText amount = root.findViewById(R.id.si_amount);
        final Spinner siUnit = root.findViewById(R.id.si_unit);
        final TextView siResult = root.findViewById(R.id.result_si_to_imperial);

        final Button button = root.findViewById(R.id.submit_si);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Double siAmount = Double.parseDouble(amount.getText().toString());
                String unitName = siUnit.getSelectedItem().toString();

                ImperialUnit imperialUnit = siToImperialUnit(siAmount, unitName);

                // TODO można by ten output sformatować tak żeby ładnie wyglądły liczby np. 1.10000023
                // String result = String.format("%.23f", siUnit.getValue()) + " " + siUnit.getName();

                // TODO trzeba zwiekszyc lekko wysokosc boxa do wyswietlania wyniku
                String result = siAmount + " " + unitName + " = " + imperialUnit.getValue() + " " + imperialUnit.getName();
                siResult.setText(result);
            }
        });

        return root;
    }

    private ImperialUnit siToImperialUnit(Double siAmount, String unitName) {

        Unit unit = new UnitService(getContext())
                .findAllUnits()
                .stream()
                .filter(u -> u.getName().equals(unitName))
                .findFirst()
                .get();

        return new ImperialUnit(unit.getUnitSI(), siAmount / unit.getFactor());
    }

    @AllArgsConstructor
    @Getter
    private static class ImperialUnit {
        private String name;
        private Double value;
    }
}