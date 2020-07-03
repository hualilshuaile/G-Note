package com.example.g_note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.g_note.SqlLogin.UserService;
import com.mob.MobSDK;

public class MainActivity extends AppCompatActivity {


    private CheckBox checkbox;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private EditText PhoneNumber,Password;
    private String phoneNumberValue,passwordValue;

    private ImageButton Login;
    private Button login_by_phone, password_forget, Register, third_party_login;
    private long mExitTime;
    private static final String TAG = "MainActivity";
    private boolean state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //点击控件输入时软键盘不会将布局顶起
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate:");
        getSupportActionBar().hide(); //隐藏app标题栏
        checkbox=findViewById(R.id.checkbox);
        Login = findViewById(R.id.Login);
        login_by_phone = findViewById(R.id.LoginByPhone);
        password_forget = findViewById(R.id.PasswordForget);
        Register = findViewById(R.id.Register);
        third_party_login = findViewById(R.id.ThirdPartyLogin);
        PhoneNumber=findViewById(R.id.PhoneNumber );
        Password=findViewById(R.id.Password);
        output();
        Login.setOnClickListener(new OnClickListener() {
                                     public void onClick(View v) {
                                         phoneNumberValue = PhoneNumber.getText().toString();
                                         passwordValue = Password.getText().toString();
                Log.i("TAG",phoneNumberValue+"_"+passwordValue);
                UserService uService=new UserService(MainActivity.this);
                boolean flag=uService.login(phoneNumberValue, passwordValue);
                if(flag){
                    input();
                    Log.i("TAG","登录成功");
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,FastBrowse.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//登陆成功后点击返回是直接返回到桌面而不是退出登录
                    startActivity(intent);
                }
                else if ( TextUtils.isEmpty(PhoneNumber.getText().toString().trim()) ){
                    Toast.makeText(MainActivity.this, "输入账户不能为空", Toast.LENGTH_LONG).show();
                }
                else if (  TextUtils.isEmpty(Password.getText().toString().trim()) ){
                    Toast.makeText(MainActivity.this, "输入密码不能为空", Toast.LENGTH_LONG).show();
                }
                 else  {
                    Log.i("TAG","登录失败");
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        });
        login_by_phone.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhoneMessageLogin.class);
                startActivity(intent);
            }
        });
        password_forget.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResetPassword.class);
                startActivity(intent);
            }
        });

        Register.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        third_party_login.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThirdPartyLogin.class);
                startActivity(intent);
            }
        });
    }
    private void output(){
        SharedPreferences shared = getSharedPreferences("mypsd", MODE_PRIVATE);
        String name1 = shared.getString("name", "");
        String psd1 = shared.getString("psd", "");
        boolean ischecked1 = shared.getBoolean("isChecked", false);
        PhoneNumber.setText(name1);
        Password.setText(psd1);
        checkbox.setChecked(ischecked1);
    }
    private void input() {
        SharedPreferences.Editor edit = getSharedPreferences("mypsd", MODE_PRIVATE).edit();
        if (checkbox.isChecked()) {
            edit.putString("name", PhoneNumber.getText().toString());
            edit.putString("psd",Password.getText().toString());
            edit.putBoolean("isChecked", true);
        }
        else
         {
             edit.clear();              //若选择全部清除就保留这行代码，注释以下三行
            edit.putString("name", PhoneNumber.getText().toString());//只存用户名
            edit.putString("psd","");
            edit.putBoolean("isChecked", false);
        }
        edit.commit();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
