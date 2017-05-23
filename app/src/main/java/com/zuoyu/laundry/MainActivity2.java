package com.zuoyu.laundry;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.zuoyu.laundry.base.BaseActivity;
import com.zuoyu.laundry.utils.LogUtil;
import com.zuoyu.laundry.utils.PermissionUtil;

public class MainActivity2 extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback{


    @Override
    public int setLayout() {
        return R.layout.activity_main2;
    }

    @Override
    public void initTitle() {

    }


    @Override
    public void initView() {
        TextView text = (TextView) findViewById(R.id.tv_text);
        text.setOnClickListener(this);

        // 请求相应的权限
        PermissionUtil.requestPermission(this, PermissionUtil.CODE_CAMERA, mPermissionGrant);

        LogUtil.d("hah");
        LogUtil.e("hah");
        LogUtil.i("hah");
        LogUtil.v("hah");
        LogUtil.w("hah");
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


}
