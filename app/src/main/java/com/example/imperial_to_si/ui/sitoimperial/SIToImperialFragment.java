package com.example.imperial_to_si.ui.sitoimperial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        return root;
    }
}