package com.example.g_note.Paper;
        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import com.example.g_note.R;
public class EnglishPaper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.english_paper);
        getSupportActionBar().hide(); //隐藏app标题栏
    }
}
