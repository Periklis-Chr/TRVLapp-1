package com.iee.trvlapp.ui.Home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Our services cover all \n" +
                "aspects of your travel\n" +
                " needs ensuring that your\n" +
                " Mediterranean holiday will\n " +
                "be one to remember With TRVL \n" +
                " you are assured of quality\n" +
                " and reliability.\n");
    }

    public LiveData<String> getText() {
        return mText;
    }
}