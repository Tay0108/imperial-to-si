package com.example.imperial_to_si.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.imperial_to_si.R;
import com.example.imperial_to_si.MusicService;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private Button buttonStart;
    private Button buttonStop;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        //getting buttons from xml
        buttonStart = (Button) root.findViewById(R.id.play_music);
        buttonStop = (Button) root.findViewById(R.id.stop_music);

        System.out.println("buttonStart: " + buttonStart);
        System.out.println("buttonStop: " + buttonStop);

        //attaching onclicklistener to buttons
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("music starts");
                getActivity().startService(new Intent(HomeFragment.this.getActivity(), MusicService.class));
                buttonStart.setEnabled(false);
                buttonStop.setEnabled(true);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("music stops");
                getActivity().stopService(new Intent(HomeFragment.this.getActivity(), MusicService.class));
                buttonStart.setEnabled(true);
                buttonStop.setEnabled(false);
            }
        });

        return root;
    }
}