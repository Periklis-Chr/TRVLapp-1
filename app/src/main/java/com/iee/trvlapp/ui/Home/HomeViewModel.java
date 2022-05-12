package com.iee.trvlapp.ui.Home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("\"Our services cover all aspects of your travel needs ensuring that your Mediterranean holiday will be one to remember.\\n\" +\n" +
                        "\"With TRVL , you are assured of quality and reliability.\\n\"");
    }

    public LiveData<String> getText() {
        return mText;
    }
}