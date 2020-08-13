package com.example.aar_cp_jky_01.bean;

import java.io.Serializable;
import java.util.List;

public class MessageBean implements Serializable {

    private int messageType;

    private int send_type;

    private boolean isSystemMessage = false;

    private boolean isHistory = false;

    private boolean isOffLine = false;

    public boolean isHistory() {
        return isHistory;
    }

    public void setHistory(boolean history) {
        isHistory = history;
    }

    public boolean isOffLine() {
        return isOffLine;
    }

    public void setOffLine(boolean offLine) {
        isOffLine = offLine;
    }

    public boolean isSystemMessage() {
        return isSystemMessage;
    }

    public void setSystemMessage(boolean systemMessage) {
        isSystemMessage = systemMessage;
    }


    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getSend_type() {
        return send_type;
    }

    public void setSend_type(int send_type) {
        this.send_type = send_type;
    }

    /**
     * @return 是否为本人发送
     */
    private boolean isMeSend;

    private String cotent_text;

    private List<IssueContentBean.BtnsBean> btns;

    private List<CheckMoreBean> checkMoreBeans;

    public List<CheckMoreBean> getCheckMoreBeans() {
        return checkMoreBeans;
    }

    public void setCheckMoreBeans(List<CheckMoreBean> checkMoreBeans) {
        this.checkMoreBeans = checkMoreBeans;
    }

    public List<IssueContentBean.BtnsBean> getBtns() {
        return btns;
    }

    public void setBtns(List<IssueContentBean.BtnsBean> btns) {
        this.btns = btns;
    }

    public String getCotent_text() {
        return cotent_text;
    }

    public void setCotent_text(String cotent_text) {
        this.cotent_text = cotent_text;
    }

    public boolean isMeSend() {
        return isMeSend;
    }

    public void setMeSend(boolean meSend) {
        isMeSend = meSend;
    }
}
