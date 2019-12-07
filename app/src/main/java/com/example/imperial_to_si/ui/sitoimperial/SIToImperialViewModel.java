package com.example.imperial_to_si.ui.sitoimperial;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SIToImperialViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SIToImperialViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Convert SI to imperial:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}