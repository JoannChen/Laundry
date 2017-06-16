package com.zuoyu.laundry.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zuoyu.laundry.R;
import com.zuoyu.laundry.application.Constant;
import com.zuoyu.laundry.application.UrlManage;
import com.zuoyu.laundry.model.TokenAuthModel;
import com.zuoyu.laundry.utils.http.HMAC;
import com.zuoyu.laundry.utils.http.HttpResult;
import com.zuoyu.laundry.widget.LoadingProgress;

import org.bouncycastle.util.encoders.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.RequestBody;

/**
 * <pre>
 * Function：封装网络请求工具类
 *
 * Created by JoannChen on 2017/3/7 11:46
 * QQ:411083907
 * E-mail:Q8622268@foxmail.com
 * Copyright Notice：版权所有@ChenYongZuo
 * </pre>
 */
public class HttpUtil {
    private Context context;
    private boolean isCache = false;

    // 加载进度条
    private LoadingProgress loadingProgress;

    /**
     * 外层结构是对象
     */
    public static final int OBJECT_TYPE = 0x1;

    /**
     * 外层结构是集合
     */
    public static final int ARRAY_TYPE = 0x2;

    /**
     * 构造方法
     *
     * @param context 请求标记（用于销毁请求）
     */
    public HttpUtil(Context context) {
        this.context = context;
        loadingProgress = new LoadingProgress(context);
    }

    /**
     * POST 加密请求
     *
     * @param params 请求参数，需要一个有序的集合，所以用TreeMap
     * @param url    请求URL
     * @param result 结果回调
     */
    public <T> void post(final Map<String, String> params, final String url, final HttpResult<T> result) {

        if (!ToolUtil.isNetConnection()) {
            result.onFailed(0, context.getString(R.string.network_anomaly));
            ToastUtil.showNetWorkError();
            return;
        }

        if (!loadingProgress.isShowing()) {
            loadingProgress.show();
        }

        OkHttpUtils.post()
                .headers(getHeader())
                .params(params)
                .url(url)
                .tag(context)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

                if (loadingProgress.isShowing()) {
                    loadingProgress.dismiss();
                }

                LogUtil.e("URL:" + url + " 状态码：" + id + " 错误信息：" + e.getMessage());

                if (e.getMessage().equals("request failed , reponse's code is : 401")) {
                    put(null, UrlManage.REFRESH_URL, new HttpResult<String>() {
                        @Override
                        public void onSuccess(String mResult) {
                            LogUtil.i("刷新Token");
                            post(params, url, result);
                        }

                        @Override
                        public void onFailed(int errCord, String errMsg) {

                        }
                    });
                }

                result.onFailed(id, e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {

                if (loadingProgress.isShowing()) {
                    loadingProgress.dismiss();
                }

                LogUtil.e("【当前版本】V" + ToolUtil.getVersionName());
                LogUtil.e("【Post请求JSON】" + response);

                result.onSuccess((T) parseJson(response, result.getObj(), result.getType()));


            }
        });

    }


    /**
     * GET 加密请求
     *
     * @param params 请求参数
     * @param url    请求URL
     * @param result 结果回调
     */
    public <T> void get(final Map<String, String> params, final String url, final HttpResult<T> result) {

        if (!ToolUtil.isNetConnection()) {
            return;
        }

        if (!loadingProgress.isShowing()) {
            loadingProgress.show();
        }

        OkHttpUtils.get()
                .headers(getHeader())
                .params(params)
                .url(url)
                .tag(context)
                .build().execute(new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

                if (loadingProgress.isShowing()) {
                    loadingProgress.dismiss();
                }

                result.onFailed(id, e.getMessage());
                LogUtil.i("URL:" + url + " 状态码：" + id + " 错误信息：" + e.getMessage());

                if (e.getMessage().equals("request failed , reponse's code is : 401")) {
                    put(null, UrlManage.REFRESH_URL, new HttpResult<String>() {
                        @Override
                        public void onSuccess(String mResult) {
                            LogUtil.i("刷新Token");
                            get(params, url, result);
                        }

                        @Override
                        public void onFailed(int errCord, String errMsg) {

                        }
                    });
                }

            }

            @Override
            public void onResponse(String response, int id) {

                if (loadingProgress.isShowing()) {
                    loadingProgress.dismiss();
                }

                LogUtil.i("Get请求JSON：" + response);

                result.onSuccess((T) parseJson(response, result.getObj(), result.getType()));

            }
        });
    }


    public <T> void put(final Map<String, String> params, final String url, final HttpResult<T> result) {
        OkHttpUtils
                .put()
                .headers(getHeader())
                .requestBody(buildParams(params))
                .url(url)
                .tag(context)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                result.onFailed(id, e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                result.onSuccess((T) parseJson(response, result.getObj(), result.getType()));
            }
        });
    }


    /**
     * 生成头信息
     *
     * @return 生成头信息
     */
    private Map<String, String> getHeader() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + TokenAuthModel.getInstance().getToken()); // 加密串
        return header;
    }


    /**
     * 解析Json
     *
     * @param json  json串
     * @param clazz 当前类
     * @param type  json数据格式：OBJECT 对象；LIST 集合
     * @param <T>   泛型
     * @return 当前实体类
     */
    private <T> T parseJson(String json, Class<?> clazz, int type) {
        if (json != null && !json.equals("")) {

            // 是否存储JSON
            try {
                if (isCache) {
                    //存储JSON
                }

                switch (type) {
                    case OBJECT_TYPE:
                        return (T) JSON.parseObject(json, clazz);
                    case ARRAY_TYPE:
                        return (T) JSON.parseArray(json, clazz);
                    default:
                        return (T) json;

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 销毁请求
     */
    public void cancel() {
        OkHttpUtils.getInstance().cancelTag(context);
    }


    /**
     * 参数转换JSON格式
     *
     * @param params 参数
     * @return map集合
     */
    private Map<String, String> getJsonParams(Map<String, String> params) {

        Map<String, String> jsonParams = new HashMap<>();

        if (params != null) {
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry set : params.entrySet()) {
                try {
                    jsonObject.put(set.getKey().toString(), set.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            jsonParams.put("params", jsonObject.toString());
        }

        return jsonParams;
    }


    /**
     * 拼接参数的方法
     *
     * @param params 请求参数
     * @return 参数拼接
     */
    private String buildParams(Map<String, String> params) {
        if(params != null){
            StringBuilder urlParams = new StringBuilder("?");
            int index = 0;
            for (Map.Entry entry : params.entrySet()) {

                if (index != 0) {
                    urlParams.append("&");
                }
                urlParams.append(entry.getKey()).append("=").append(entry.getValue());
                index++;
            }
            LogUtil.i("参数：" + urlParams.toString());
            return urlParams.toString();
        }

        return "none";
    }
}
