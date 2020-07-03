package com.example.g_note.ui.account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.g_note.MainActivity;
import com.example.g_note.R;
import com.example.g_note.ResetPassword;
import com.mob.MobApplication;
import com.mob.MobSDK;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.wechat.friends.Wechat;
public class Shareqq extends AppCompatActivity {
    Button wxshare;
    Button qqshare;
    private static final String APP_ID = "101835475"; //获取的APPID
    private ShareUiListener mIUiListener;
    private Tencent mTencent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share);
        mTencent = Tencent.createInstance(APP_ID, Shareqq.this.getApplicationContext());
        MobSDK.init(this);
        wxshare=findViewById(R.id.wxshare);
        qqshare=findViewById(R.id.qqshare);
    wxshare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showShare(Wechat.NAME);
            }
        });
    qqshare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                qqShare();
            }
        });
    }
    private void showShare(String platform) {
        final OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("俊憨憨智能笔记");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("憨批俊的分享");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://cnhuadong.vip/trans/K9tC0xJK_Y8WtN2w1S0gzZVdTQUE=");
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("https://www.ecust.edu.cn/");
        //启动分享
        oks.show(MobSDK.getContext());
    }
    private void qqShare() {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);//分享的类型
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "憨批俊的智能笔记");//分享标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,"憨批俊的在线学习笔记。");//要分享的内容摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,"https://www.ecust.edu.cn/");//内容地址
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"https://c-ssl.duitang.com/uploads/item/201807/09/20180709002655_Ej3yU.thumb.700_0.jpeg");//分享的图片URL
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "G-note");//应用名称
        mTencent.shareToQQ(Shareqq.this, params, new ShareUiListener());
    }
    private class ShareUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            //分享成功
        }
        @Override
        public void onError(UiError uiError) {
            //分享失败
        }
        @Override
        public void onCancel() {
            //分享取消
        }
    }
    /**
     * 回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != mTencent) {
            mTencent.onActivityResult(requestCode, resultCode, data);
        }
    }
}
