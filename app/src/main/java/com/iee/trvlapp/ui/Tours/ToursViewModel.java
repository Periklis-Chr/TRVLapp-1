package com.iee.trvlapp.ui.Tours;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToursViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ToursViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Tours fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}