package com.diucity.newsoulrecycler.biz.event;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class MainEvent {
    private int type;
    private String message;
    public MainEvent(int type ,String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}