package com.example.g_note.ui.micro_lesson;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.g_note.R;

public class MicroLessonFragment extends Fragment {

    private MicroLessonViewModel microLessonViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        microLessonViewModel =
                ViewModelProviders.of(this).get(MicroLessonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_micro_lesson, container, false);
        final TextView textView = root.findViewById(R.id.text_micro_lesson);
        microLessonViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
