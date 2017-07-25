package com.diucity.newsoulrecycler.entity;

import android.content.Context;

import com.diucity.newsoulrecycler.utils.PhoneUtils;

import java.util.List;

/**
 * By：CH  on 2017/7/13 0013
 * <p>
 * email：576748006@qq.com
 */
public class UpLoadBean {
    public UpLoadBean(Context context,List<ItemsBean> items) {
        this.timestamp = System.currentTimeMillis();
        this.recyclerid = PhoneUtils.getMacAddress(context);
        this.items = items;
    }

    /**
     * timestamp : 37459127
     * recyclerid : 1
     * items : [{"code":375720,"type":1,"integral":10},{"code":375720,"type":2,"integral":20}]
     */

    private long timestamp;
    private String recyclerid;
    private List<ItemsBean> items;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRecyclerid() {
        return recyclerid;
    }

    public void setRecyclerid(String recyclerid) {
        this.recyclerid = recyclerid;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        public ItemsBean(int code, int type, int integral) {
            this.code = code;
            this.type = type;
            this.integral = integral;
        }

        /**
         * code : 375720
         * type : 1
         * integral : 10
         */

        private int code;
        private int type;
        private int integral;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }
    }
}
