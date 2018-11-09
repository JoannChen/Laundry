package com.zuoyu.laundry.entity;

/**
 * 功能:Token的实体类
 * 创建人: 张野
 * 创建时间: 2017/6/8.
 * QQ: 154151222
 * EMAIL: zhangye98@foxmail.com
 */
public class AuthEntity {

    private Auth data;

    public class Auth {
        private String token;
        private String expired_at;
        private String refresh_expired_at;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExpired_at() {
            return expired_at;
        }

        public void setExpired_at(String expired_at) {
            this.expired_at = expired_at;
        }

        public String getRefresh_expired_at() {
            return refresh_expired_at;
        }

        public void setRefresh_expired_at(String refresh_expired_at) {
            this.refresh_expired_at = refresh_expired_at;
        }

        @Override
        public String toString() {
            return "Auth{" +
                    "token='" + token + '\'' +
                    ", expired_at='" + expired_at + '\'' +
                    ", refresh_expired_at='" + refresh_expired_at + '\'' +
                    '}';
        }
    }

    public Auth getData() {
        return data;
    }

    public void setData(Auth data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AuthEntity{" +
                "data=" + data +
                '}';
    }
}
