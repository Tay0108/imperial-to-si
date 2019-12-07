package com.example.imperial_to_si.ui.imperialtosi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImperialToSIViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ImperialToSIViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Convert Imperial to SI:");
    }

    public LiveData<String> getText() {
        return mText;
    }
}