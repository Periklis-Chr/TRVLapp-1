package com.iee.trvlapp.ui.Costumers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CostumersViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public CostumersViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Costumers fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
