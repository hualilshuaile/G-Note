package com.example.g_note.ui.micro_lesson;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MicroLessonViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MicroLessonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("微课");
    }

    public LiveData<String> getText() {
        return mText;
    }
}