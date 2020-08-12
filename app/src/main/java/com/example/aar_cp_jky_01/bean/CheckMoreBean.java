package com.example.aar_cp_jky_01.bean;

import android.widget.CheckBox;

import java.io.Serializable;

/**
 * Created by x_wind on 18/4/17.
 */
public class CheckMoreBean implements Serializable {
    public static final String singlebtn = "1", doublebtn = "2";
    boolean isCheck = false;
    String message;
    String color;
    String singleDouble = doublebtn;
    private int isSelect;

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setSingleDouble(String singleDouble) {
        this.singleDouble = singleDouble;
    }

    public String getSingleDouble() {
        return singleDouble;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public static CheckMoreBean addInfo(String message, String singleDouble, String color) {
        CheckMoreBean info = new CheckMoreBean(message);
        info.setMessage(message);
        info.setSingleDouble(singleDouble);
        info.setColor(color);
        return info;
    }

    public CheckMoreBean(String message) {
        this.message = message;
    }


    private SetCheckboxListener setCheckboxListener;

    public interface SetCheckboxListener {
        void checkBoxListener(CheckBox ck_index);
    }

    public void setSetCheckboxListener(SetCheckboxListener setCheckboxListener) {
        this.setCheckboxListener = setCheckboxListener;
    }
}
