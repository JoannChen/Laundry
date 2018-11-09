package com.zuoyu.laundry.model;

import com.zuoyu.laundry.entity.AuthEntity;
import com.zuoyu.laundry.utils.DateUtil;
import com.zuoyu.laundry.utils.LogUtil;
import com.zuoyu.laundry.utils.SharedUtil;

/**
 * 功能:存储token 和删除token操作
 * 创建人: 张野
 * 创建时间: 2017/6/8.
 * QQ: 154151222
 * EMAIL: zhangye98@foxmail.com
 */
public class TokenAuthModel {

    private final String TOKEN = "token";
    private final String EXPIRED_AT = "expired_at";
    private final String REFRESH_EXPIRED_AT = "refresh_expired_at";
    private String tokenStr;
    private long currentExpired;

    private static TokenAuthModel tokenAuthModel;

    public static TokenAuthModel getInstance(){
        if(tokenAuthModel == null){
            tokenAuthModel = new TokenAuthModel();
        }
        return tokenAuthModel;
    }


    /**
     * 更新本地token
     */
    public void Update(AuthEntity authEntity) {
        try {
            tokenStr = authEntity.getData().getToken();
            SharedUtil.setString(TOKEN, tokenStr);
            SharedUtil.setString(EXPIRED_AT, authEntity.getData().getExpired_at());
            SharedUtil.setString(REFRESH_EXPIRED_AT, authEntity.getData().getRefresh_expired_at());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    /**
     * 清除本地token
     */
    public void clear() {
        SharedUtil.setString(TOKEN, "");
        SharedUtil.setString(EXPIRED_AT, "");
        SharedUtil.setString(REFRESH_EXPIRED_AT, "");
    }


    public String getToken(){
        if(tokenStr == null){
            tokenStr =  SharedUtil.getString(TOKEN);
        }
        LogUtil.i(TOKEN+":"+tokenStr);
        return tokenStr;
    }

    public void autoRegresh(OnRefreshTokenListener listener){
        if(currentExpired == 0){
            String current = SharedUtil.getString(EXPIRED_AT);
            currentExpired = DateUtil.dateParserMillis(current);
            LogUtil.i("Token过期时间："+current);
        }

        if(System.currentTimeMillis() >= currentExpired){
            /**
             * 更新token
             */
            listener.onRefresh();
        }else{

            listener.onRefreshFinish();
        }
    }

    public interface OnRefreshTokenListener {
        void onRefresh();
        void onRefreshFinish();
    }
}
