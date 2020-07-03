package com.example.g_note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ResetPassword extends AppCompatActivity {

    private ImageButton resetPasswordBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        getSupportActionBar().hide();

        resetPasswordBack=findViewById(R.id.ResetPasswordBack);

        resetPasswordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassword.this.finish();
            }
        });

    }
}
