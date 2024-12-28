package com.example.rainstick.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("Seattle, WA, USA");
    }

    public LiveData<String> getText() {
        return mText;
    }
}