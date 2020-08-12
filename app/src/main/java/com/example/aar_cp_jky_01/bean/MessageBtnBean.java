package com.example.aar_cp_jky_01.bean;

/**
 * Created by x_wind on 17/3/15.
 */
public class MessageBtnBean {
    private String id;
    private String btn_text;
    private String color;
    private boolean isAsk;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBtn_text(String btn_text) {
        this.btn_text = btn_text;
    }

    public String getBtn_text() {
        return btn_text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setIsAsk(boolean isAsk) {
        this.isAsk = isAsk;
    }

    public boolean isAsk() {
        return isAsk;
    }


}
