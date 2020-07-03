package com.example.g_note.ui.brand_new;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.g_note.R;


public class BrandNewFragment extends Fragment implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private BrandNewViewModel brandNewViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        brandNewViewModel =
                ViewModelProviders.of(this).get(BrandNewViewModel.class);
        View root = inflater.inflate(R.layout.fragment_brand_new, container, false);
        final TextView textView = root.findViewById(R.id.text_brand_new);

        brandNewViewModel.getText().observe(this, new Observer<String>() {
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

        ImageButton qRCode=getActivity().findViewById(R.id.QRCode);
        ImageButton brand_new_more = getActivity().findViewById(R.id.brand_new_More);

        qRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setAction("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });

        brand_new_more.setOnClickListener(this);

    }

    //点击按钮后，加载弹出式菜单
    @Override
    public void onClick(View v) {
        //创建弹出式菜单对象（最低版本11）
        PopupMenu popup = new PopupMenu(getActivity(), v);//第二个参数是绑定的那个view
        //获取菜单填充器
        MenuInflater inflater = popup.getMenuInflater();
        //填充菜单
        inflater.inflate(R.menu.more_menu, popup.getMenu());
        //绑定菜单项的点击事件
        popup.setOnMenuItemClickListener(this);
        //显示(这一行代码不要忘记了)
        popup.show();

    }

    //弹出式菜单的单击事件处理
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.OrderByName:
                Toast.makeText(getActivity(), "按名称排序", Toast.LENGTH_SHORT).show();
                break;
            case R.id.OrderByTime:
                Toast.makeText(getActivity(), "按时间排序", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return false;
    }


}
