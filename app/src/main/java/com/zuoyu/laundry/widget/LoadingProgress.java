package com.zuoyu.laundry.widget;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.zuoyu.laundry.R;

/**
 * <pre>
 * Function：自定义加载进度框
 *
 * Created by JoannChen on 2017/3/10 15:48
 * QQ:411083907
 * E-mail:q8622268@163.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class LoadingProgress extends ProgressDialog {


    // 加载文字（传值）
    private String loadingContent;


    // 加载动画
    private RotateAnimation mAnimation;

    /**
     * 自定义无忧停车动画加载进度的对话框
     *
     * @param context 上下文对象
     */
    public LoadingProgress(Context context) {
        super(context, R.style.Loading_Dialog_Style);
        this.loadingContent = context.getString(R.string.loading_in);
        setCanceledOnTouchOutside(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initView();
    }

    private void initView() {

        setContentView(R.layout.layout_toast);

        // 加载提示文字
        TextView loadingText = (TextView) findViewById(R.id.tv_text);
        loadingText.setText(loadingContent);

        // 加载动画展示
        ImageView loadingImg = (ImageView) findViewById(R.id.iv_icon);
        loadingImg.setFocusable(false);

        // float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue
        mAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        /* 常用方法 */
        mAnimation.setDuration(1500);//设置动画持续时间
        mAnimation.setRepeatCount(-1);//设置重复次数
        mAnimation.setFillAfter(true);//动画执行完后是否停留在执行完的状态

        loadingImg.setAnimation(mAnimation);


        // 布局写法
//      mAnimation =(RotateAnimation) AnimationUtils.loadAnimation(context,R.anim.anim_rotating);


    }


    @Override
    public void show() {
        super.show();
        initView();
        mAnimation.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        mAnimation.cancel();
    }
}
