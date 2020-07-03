package com.example.g_note;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g_note.R;
import com.example.g_note.SqlLogin.User;
import com.example.g_note.SqlLogin.UserService;

public class Register extends AppCompatActivity {

    EditText PhoneNumberInput;
    EditText PasswordSet;
    ImageButton Register ,RegisterBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        PhoneNumberInput = findViewById(R.id.PhoneNumberInput);
        PasswordSet= findViewById(R.id.PasswordSet);
        Register = findViewById(R.id.Register);
        RegisterBack = findViewById(R.id.RegisterBack);
        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = PhoneNumberInput.getText().toString().trim();
                String pass = PasswordSet.getText().toString().trim();
                Log.i("TAG", name + "_" + pass);
                UserService uService = new UserService(Register.this);
                User user = new User();
                user.setUsername(name);
                user.setPassword(pass);
                uService.register(user);
                if (name.equals("")) {
                    Toast.makeText(Register.this, "注册账户不能为空", Toast.LENGTH_LONG).show();
                } else if (pass.equals("")) {
                    Toast.makeText(Register.this, "注册密码不能为空", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Register.this, "注册成功", Toast.LENGTH_LONG).show();

                }
            }

        });
        RegisterBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}