package com.example.imperial_to_si.ui.sitoimperial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.imperial_to_si.R;

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
                // Code here executes on main thread after user presses button

                System.out.println("Ilosc jednostki: " + amount.getText() + siUnit.getSelectedItem().toString());

                siResult.setText(amount.getText() + " " + siUnit.getSelectedItem().toString());

            }
        });

        return root;
    }
}