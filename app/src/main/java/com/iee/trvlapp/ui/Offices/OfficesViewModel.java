package com.iee.trvlapp.ui.Offices;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OfficesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OfficesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Offices fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}