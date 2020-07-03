package com.example.g_note.ui.smart_pen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.g_note.FastBrowse;
import com.example.g_note.Paper.EnglishPaper;
import com.example.g_note.R;


public class SmartPenFragment extends Fragment {
    private SmartPenViewModel smartPenViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        smartPenViewModel = ViewModelProviders.of(this).get(SmartPenViewModel.class);
        View root = inflater.inflate(R.layout.fragment_smart_pen, container, false);
        final TextView textView = root.findViewById(R.id.text_smart_pen);
        smartPenViewModel.getText().observe(this, new Observer<String>() {
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
        Button PairStart =getActivity().findViewById(R.id.PairStart);
        PairStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Ble.class);
                startActivity(intent);
            }
    });
}
}
