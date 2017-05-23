package com.zuoyu.laundry.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * <pre>
 * Function：图片加载框架
 *
 * Created by JoannChen on 2017/4/20 14:18
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class ImageUtil {

    /**
     * 加载图片
     *
     * @param context   上下文对象
     * @param imageView 组件
     * @param url       图片地址
     */
    public static void load(Context context, ImageView imageView, String url) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(imageView);
    }


    /**
     * 加载图片
     *
     * @param context   上下文对象
     * @param imageView 组件
     * @param url       图片地址
     * @param res       默认图片
     */
    public static void load(Context context, ImageView imageView, String url, int res) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .placeholder(res)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载图片，实现圆角
     *
     * @param context   上下文对象
     * @param imageView 组件
     * @param url       图片地址
     */
    public static void loadRound(final Context context, final ImageView imageView, String url) {
        if (context == null) {
            return;
        }
        Glide.with(context)
                .load(url)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        //要实现圆角，只需要加上这句
                        RoundedBitmapDrawable round = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        round.setCircular(true);
                        round.setCornerRadius(100L);
                        imageView.setImageDrawable(round);
                    }
                });
    }


}
