package com.example.g_note;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.g_note.ui.TimeCountUtil;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class PhoneMessageLogin extends AppCompatActivity implements View.OnClickListener {

    private ImageButton PhoneMessageLoginBack, PhoneMessageLogin;
    private Button SendVerifyCodeLogin;
    private EditText PhoneNumberLogin, yzma;
    private String phone_number, code_number;
    private boolean flag = true;
    private TimeCountUtil mTimeCountUtil;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_message_login);
        getSupportActionBar().hide();
        getId();
        //这里的倒计时的时间 是 ：用第二参数 / 第三个三参数 = 倒计时为60秒
        mTimeCountUtil = new TimeCountUtil(SendVerifyCodeLogin, 60000, 1000);

        EventHandler eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };

        SMSSDK.registerEventHandler(eventHandler);
    }

    protected void onDestroy() {
        EventHandler eventHandler = new EventHandler();
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }


    @SuppressLint("HandlerLeak")
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event=msg.arg1;
            int result=msg.arg2;
            Object data=msg.obj;
            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                if(result == SMSSDK.RESULT_COMPLETE) {
                    boolean smart = (Boolean)data;
                    if(smart) {
                        Toast.makeText(getApplicationContext(),"该手机号已经注册过，请重新输入",
                                Toast.LENGTH_LONG).show();
                        PhoneNumberLogin.requestFocus();
                        return;
                    }
                }
            }
            if(result==SMSSDK.RESULT_COMPLETE)
            {
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    Toast.makeText(getApplicationContext(), "验证码输入正确",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PhoneMessageLogin.this,FastBrowse.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);//登陆成功后点击返回时直接返回到桌面而不是退出登录
                    startActivity(intent);
                }
            }
            else
            {
                if(flag)
                {
                    SendVerifyCodeLogin.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"验证码获取失败请重新获取", Toast.LENGTH_LONG).show();
                    PhoneNumberLogin.requestFocus();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"验证码输入错误", Toast.LENGTH_LONG).show();
                }
            }
        }

    };
    private void getId()
    {
        PhoneMessageLogin = findViewById(R.id.PhoneMessageLogin);
        PhoneMessageLoginBack = findViewById(R.id.PhoneMessageLoginBack);
        SendVerifyCodeLogin = findViewById(R.id.SendVerifyCodeLogin);
        PhoneNumberLogin = findViewById(R.id.PhoneNumberLogin);

        yzma = findViewById(R.id.yzma);
        SendVerifyCodeLogin.setOnClickListener(this);
        PhoneMessageLogin.setOnClickListener( this);
        PhoneMessageLoginBack.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SendVerifyCodeLogin:
                mTimeCountUtil.start();
                if (judPhone())//去掉左右空格获取字符串
                {
                    SMSSDK.getVerificationCode("86", phone_number);
                    yzma.requestFocus();
                }
                break;
            case R.id.PhoneMessageLogin:
                if (judCord())
                    SMSSDK.submitVerificationCode("86", phone_number, code_number);
                flag = false;
                break;
            default:
                break;
        }
    }
    private boolean judPhone()
    {
        if(TextUtils.isEmpty(PhoneNumberLogin.getText().toString().trim()))
        {
            Toast.makeText(PhoneMessageLogin.this,"请输入您的电话号码",Toast.LENGTH_LONG).show();
            PhoneNumberLogin.requestFocus();
            return false;
        }
        else if(PhoneNumberLogin.getText().toString().trim().length()!=11)
        {
            Toast.makeText(PhoneMessageLogin.this,"您的电话号码位数不正确",Toast.LENGTH_LONG).show();
            PhoneNumberLogin.requestFocus();
            return false;
        }
        else
        {
            phone_number=PhoneNumberLogin.getText().toString().trim();
            String num="[1][358]\\d{9}";
            if(phone_number.matches(num))
                return true;
            else
            {
                Toast.makeText(PhoneMessageLogin.this,"请输入正确的手机号码",Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }
    private boolean judCord()
    {
        judPhone();
        if(TextUtils.isEmpty(yzma.getText().toString().trim()))
        {
            Toast.makeText(PhoneMessageLogin.this,"请输入您的验证码",Toast.LENGTH_LONG).show();
            yzma.requestFocus();
            return false;
        }
        else if(yzma.getText().toString().trim().length()!=4)
        {
            Toast.makeText(PhoneMessageLogin.this,"您的验证码位数不正确",Toast.LENGTH_LONG).show();
            yzma.requestFocus();

            return false;
        }
        else
        {
            code_number=yzma.getText().toString().trim();
            return true;
        }
    }

}




