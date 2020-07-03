package com.example.g_note.ui.brand_new;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BrandNewViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BrandNewViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("最新");
    }

    public LiveData<String> getText() {
        return mText;
    }
}