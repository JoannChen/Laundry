package com.zuoyu.laundry;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zuoyu.laundry.base.BaseActivity;
import com.zuoyu.laundry.utils.PermissionUtil;
import com.zuoyu.laundry.utils.ToastUtil;
import com.zuoyu.laundry.widget.AlphaTitleScrollView;

public class MainActivity extends BaseActivity {


    private long mLastClickTime = 0;// 上次点击返回按钮的时间
    private RelativeLayout titleRLayout;


    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initTitle() {

    }


    @Override
    public void initView() {

        setContentView(R.layout.activity_main);

        // 可滑动的标题栏，带有渐变效果
        // 记得在返回事件里将透明度设置为不透明 titleRLayout.getBackground().setAlpha(255);否则会影响其他界面的显示，具体原因不明
        AlphaTitleScrollView scroll = (AlphaTitleScrollView) findViewById(R.id.scrollView);
        titleRLayout = (RelativeLayout) findViewById(R.id.rl_title);
        ImageView head = (ImageView) findViewById(R.id.iv_head);
        scroll.setTitleAndHead(titleRLayout, head);
        titleRLayout.getBackground().setAlpha(0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_text:
                loadingProgress.show();
                break;
        }
    }

    @Override
    public void initAfterLayout(Bundle savedInstanceState) {

    }


    /**
     * 当权限请求已完成时收到的回调
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtil.requestPermissionsResult(this, requestCode, permissions, grantResults, mPermissionGrant);
    }


    /**
     * 权限授予后返回的操作
     */
    private PermissionUtil.PermissionGrant mPermissionGrant = new PermissionUtil.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            if (requestCode == PermissionUtil.CODE_CAMERA) {
                // 相应的操作
            }
        }
    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                if ((System.currentTimeMillis() - mLastClickTime) > 2000) {
                    ToastUtil.show("再按一次退出程序");
                    mLastClickTime = System.currentTimeMillis();
                } else {
                    finish();
                    return false;
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
