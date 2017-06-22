package com.diucity.chandroid.ui.event;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class MainEvent {
    private String message;

    public MainEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}