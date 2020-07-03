package com.example.g_note;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.g_note.Qqweixinlogin.QQLoginManager;
import com.example.g_note.Qqweixinlogin.ToastUtil;
import com.tencent.tauth.UiError;
import org.json.JSONObject;

import static com.example.g_note.Qqweixinlogin.QQLoginManager.App_id;


public class ThirdPartyLogin extends AppCompatActivity implements QQLoginManager.QQLoginListener {

    private ImageButton thirdPartyLoginBack,QQLogin,WechatLogin;
    private TextView qqHintTextView,WechatHint;
    private QQLoginManager qqLoginManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_party_login);
        getSupportActionBar().hide();
        qqLoginManager = new QQLoginManager(App_id, this);
        thirdPartyLoginBack=findViewById(R.id.ThirdPartyLoginBack);
        qqHintTextView=findViewById(R.id.main_hint);
        QQLogin=findViewById(R.id.QQLogin);
        WechatLogin=findViewById(R.id.WechatLogin);
        WechatHint=findViewById(R.id.WechatHint);

        QQLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqLoginManager.launchQQLogin();
                WechatLogin.setVisibility(View.INVISIBLE);
                WechatHint.setVisibility(View.INVISIBLE);
                qqHintTextView.setText("正在发起QQ授权登录，请稍等...");
            }
        });
        thirdPartyLoginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThirdPartyLogin.this.finish();
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        qqLoginManager.onActivityResultData(requestCode, resultCode, data);
    }

    @Override
    public void onQQLoginSuccess(JSONObject jsonObject) {
        qqHintTextView.setText("点击调用QQ登录");
        Intent intent = new Intent(this,FastBrowse.class);
        startActivity(intent);
        ToastUtil.showToast(this, "登录成功");
    }

    @Override
    public void onQQLoginCancel() {
        qqHintTextView.setText("点击调用QQ登录");
        ToastUtil.showToast(this, "登录取消");
    }

    @Override
    public void onQQLoginError(UiError uiError) {
        qqHintTextView.setText("点击调用QQ登录");
        ToastUtil.showToast(this, "登录出错！");
    }
}
