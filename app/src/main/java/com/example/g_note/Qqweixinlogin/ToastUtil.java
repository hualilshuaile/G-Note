package com.example.g_note.Qqweixinlogin;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ToastUtil extends AppCompatActivity {
    private static Toast toast;

    public static void initToast(Context context) {
        toast = Toast.makeText(context,
                "",
                Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

}
