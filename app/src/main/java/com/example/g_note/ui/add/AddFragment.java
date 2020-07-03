package com.example.g_note.ui.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.g_note.Paper.BlankPaper;
import com.example.g_note.Paper.CalligraphyPaper;
import com.example.g_note.Paper.EnglishPaper;
import com.example.g_note.Paper.GridPaper;
import com.example.g_note.Paper.LinePaper;
import com.example.g_note.Paper.WritingPaper;
import com.example.g_note.R;

public class AddFragment extends Fragment {

    private AddViewModel addViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel =
                ViewModelProviders.of(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add, container, false);
        final TextView textView = root.findViewById(R.id.text_add);
        addViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageButton blankPaper = getActivity().findViewById(R.id.BlankPaper);
        ImageButton linePaper = getActivity().findViewById(R.id.LinePaper);
        ImageButton gridPaper = getActivity().findViewById(R.id.GridPaper);
        ImageButton englishPaper = getActivity().findViewById(R.id.EnglishPaper);
        ImageButton writingPaper = getActivity().findViewById(R.id.WritingPaper);
        ImageButton calligraphyPaper = getActivity().findViewById(R.id.CalligraphyPaper);


        blankPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), BlankPaper.class);
                startActivity(intent);
            }
        });

        linePaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LinePaper.class);
                startActivity(intent);
            }
        });

        gridPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GridPaper.class);
                startActivity(intent);
            }
        });

        englishPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EnglishPaper.class);
                startActivity(intent);
            }
        });

        writingPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WritingPaper.class);
                startActivity(intent);
            }
        });

        calligraphyPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalligraphyPaper.class);
                startActivity(intent);
            }
        });
    }
}
