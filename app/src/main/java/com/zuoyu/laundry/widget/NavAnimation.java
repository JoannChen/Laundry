package com.zuoyu.laundry.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.zuoyu.laundry.R;
import com.zuoyu.laundry.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Function：选项卡切换动画
 *
 * Created by JoannChen on 2017/6/5 16:32
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class NavAnimation extends RelativeLayout implements View.OnClickListener {

    private Context context;

    private List<TextView> textList = new ArrayList<>();
    private String[] data;
    private int width, height;
    private int childWidth;
    private int index;
    private int currentPosition;
    private int endPosition;
    private boolean isLine = true;

    private int lineHeight = 10;

    // 效果一：选项卡选中底部显示线条
    private int checkedLineColor = R.color.red;

    // 效果二：选项卡选中选中后背景颜色改变
    private int checkedBackground = R.color.red;

    // 字体选中/未选中的颜色
    private int checkedTextColor = R.color.red;
    private int uncheckedTextColor = R.color.red;

    private int duration = 150;
    private int textSize = 22;
    private boolean isInit = false;
    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener;


    public NavAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    public void setData(String[] data) {
        this.data = data;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        if (!isInit) {
            isInit = true;
            initView();

        }
    }

    private void initView() {

        if (data != null) {

            childWidth = width / data.length;
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            addView(linearLayout);

            View viewBg = new View(getContext());
            viewBg.setId(R.id.v_line);
            viewBg.setBackgroundResource(checkedLineColor);

            if (isLine) {
                LayoutParams viewBgParams = new LayoutParams(childWidth, ViewUtil.getHeight(lineHeight));
                viewBgParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                addView(viewBg, viewBgParams);
            } else {
                LayoutParams viewBgParams = new LayoutParams(childWidth, LayoutParams.MATCH_PARENT);
                viewBg.setBackgroundResource(checkedBackground);
                addView(viewBg, viewBgParams);
            }

            LayoutParams textViewParams = new LayoutParams(childWidth, height);

            for (int i = 0; i < data.length; i++) {
                TextView textView = new TextView(getContext());
                textView.setTag(i);
                textView.setGravity(Gravity.CENTER);
                textView.setText(data[i]);
                textView.setTextColor(ContextCompat.getColor(context, uncheckedTextColor));
                ViewUtil.setTextSize(textView, textSize);
                textView.setOnClickListener(this);
                textList.add(textView);
                linearLayout.addView(textView, textViewParams);
            }

            textList.get(0).setTextColor(ContextCompat.getColor(context, checkedTextColor != 0 ? checkedTextColor : uncheckedTextColor));
        }
    }


    @Override
    public void onClick(View v) {

        if (index != (int) v.getTag()) {

            if (uncheckedTextColor != 0) {
                textList.get(index).setTextColor(ContextCompat.getColor(context, uncheckedTextColor));
            }

            final View bgView = findViewById(R.id.v_line);

            if (index < (int) v.getTag()) {
                endPosition += ((int) v.getTag() - index) * childWidth;
            } else {
                endPosition -= (index - (int) v.getTag()) * childWidth;
            }

            index = ((int) v.getTag());

            TranslateAnimation translateAnimation = new TranslateAnimation(currentPosition, endPosition, 0, 0);
            translateAnimation.setFillAfter(true);// True:图片停在动画结束位置
            translateAnimation.setDuration(duration);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    if (checkedTextColor != 0) {
                        textList.get(index).setTextColor(getResources().getColor(checkedTextColor));
                    }
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    currentPosition = childWidth * index;

                    if (onCheckedChangeListener != null) {
                        onCheckedChangeListener.onCheckedChanged(null, index);
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            bgView.startAnimation(translateAnimation);

        }

    }

    public void setTextColor(int selectTextColor, int noSelectTextColor) {
        this.checkedTextColor = selectTextColor;
        this.uncheckedTextColor = noSelectTextColor;
    }

    /**
     * 设置切换效果为横线并设置颜色
     */
    public void setCheckedLineColor(int checkedLineColor) {
        isLine = true;
        this.checkedLineColor = checkedLineColor;
    }

    /**
     * 切换样式为横线的高度
     *
     * @param height 横线
     */
    public void setLineHeight(int height) {
        lineHeight = height;
    }

    /**
     * 设置切换效果为背景并设置背景图片
     */
    public void setSelectBackground(int selectBg) {
        isLine = false;
        this.checkedBackground = selectBg;
    }

    /**
     * 设置动画时间
     *
     * @param duration 多少秒完成
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public void setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }
}
