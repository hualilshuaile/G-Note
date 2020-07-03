package com.example.g_note.ui.smart_pen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SmartPenViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SmartPenViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("智能笔");
    }

    public LiveData<String> getText() {
        return mText;
    }
}