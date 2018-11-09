package com.zuoyu.laundry.entity;

/**
 * 功能:
 * 创建人: 张野
 * 创建时间: 2017/6/8.
 * QQ: 154151222
 * EMAIL: zhangye98@foxmail.com
 */
public class HomeEntity {
    private Home data;

    public Home getData() {
        return data;
    }

    public void setData(Home data) {
        this.data = data;
    }


    public class Home {

        private String username;
        private String avatar;
        private float star;
        private float dayAmount;
        private float monthAmount;
        private String orderTakeCount;
        private String orderDeliveryCount;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public float getStar() {
            return star;
        }

        public void setStar(float star) {
            this.star = star;
        }

        public float getDayAmount() {
            return dayAmount;
        }

        public void setDayAmount(float dayAmount) {
            this.dayAmount = dayAmount;
        }

        public float getMonthAmount() {
            return monthAmount;
        }

        public void setMonthAmount(float monthAmount) {
            this.monthAmount = monthAmount;
        }

        public String getOrderTakeCount() {
            return orderTakeCount;
        }

        public void setOrderTakeCount(String orderTakeCount) {
            this.orderTakeCount = orderTakeCount;
        }

        public String getOrderDeliveryCount() {
            return orderDeliveryCount;
        }

        public void setOrderDeliveryCount(String orderDeliveryCount) {
            this.orderDeliveryCount = orderDeliveryCount;
        }

        @Override
        public String toString() {
            return "Home{" +
                    "username='" + username + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", star='" + star + '\'' +
                    ", dayAmount='" + dayAmount + '\'' +
                    ", monthAmount='" + monthAmount + '\'' +
                    ", orderTakeCount='" + orderTakeCount + '\'' +
                    ", orderDeliveryCount='" + orderDeliveryCount + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeEntity{" +
                "data=" + data +
                '}';
    }
}
