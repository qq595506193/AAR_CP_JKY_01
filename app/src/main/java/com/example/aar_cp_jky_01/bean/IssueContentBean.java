package com.example.aar_cp_jky_01.bean;

import java.io.Serializable;
import java.util.List;

public class IssueContentBean implements Serializable {
    /**
     * cont : 腹痛多由腹内组织或器官受到某种强烈刺激或损伤所致，也可由胸部疾病及全身性疾病所致。此外，腹痛又是一种主观感觉，腹痛的性质和强度，不仅受病变情况和刺激程度影响，而且受神经和心理等因素的影响。即患者对疼痛刺激的敏感性存在差异，相同病变的刺激在不同的患者或同一患者的不同时期引起的腹痛在性质、强度及持续时间上有所不同。
     * btns : [{"id":"1","cont":"是","type":"3"},{"id":"2","cont":"否","type":"3"}]
     * btn_align : 1
     * btn_type : 0
     * v_send_code : 1862710
     */

    private String cont;
    private int btn_align;
    private int btn_type;
    private String v_send_code;
    private List<BtnsBean> btns;
    private String daan_laiyuan;
    private String chongxin_shuru;// 重新输入
    private String system_message;
    private List<BtnsBean> sub_titles;
    private String btnduoxuan;
    private String time_valid;
    private String sender_Flag;
    private List<String> situations;

    public List<String> getSituations() {
        return situations;
    }

    public void setSituations(List<String> situations) {
        this.situations = situations;
    }

    public List<BtnsBean> getSub_titles() {
        return sub_titles;
    }

    public void setSub_titles(List<BtnsBean> sub_titles) {
        this.sub_titles = sub_titles;
    }

    public String getSender_Flag() {
        return sender_Flag;
    }

    public void setSender_Flag(String sender_Flag) {
        this.sender_Flag = sender_Flag;
    }

    public String getTime_valid() {
        return time_valid;
    }

    public void setTime_valid(String time_valid) {
        this.time_valid = time_valid;
    }

    public String getBtnduoxuan() {
        return btnduoxuan;
    }

    public void setBtnduoxuan(String btnduoxuan) {
        this.btnduoxuan = btnduoxuan;
    }

    public String getSystem_message() {
        return system_message;
    }

    public void setSystem_message(String system_message) {
        this.system_message = system_message;
    }

    public String getChongxin_shuru() {
        return chongxin_shuru;
    }

    public void setChongxin_shuru(String chongxin_shuru) {
        this.chongxin_shuru = chongxin_shuru;
    }

    public String getDaan_laiyuan() {
        return daan_laiyuan;
    }

    public void setDaan_laiyuan(String daan_laiyuan) {
        this.daan_laiyuan = daan_laiyuan;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public int getBtn_align() {
        return btn_align;
    }

    public void setBtn_align(int btn_align) {
        this.btn_align = btn_align;
    }

    public int getBtn_type() {
        return btn_type;
    }

    public void setBtn_type(int btn_type) {
        this.btn_type = btn_type;
    }

    public String getV_send_code() {
        return v_send_code;
    }

    public void setV_send_code(String v_send_code) {
        this.v_send_code = v_send_code;
    }

    public List<BtnsBean> getBtns() {
        return btns;
    }

    public void setBtns(List<BtnsBean> btns) {
        this.btns = btns;
    }

    public static class BtnsBean implements Serializable {
        /**
         * id : 1
         * cont : 是
         * type : 3
         */

        private String id;
        private String cont;
        private String type;
        private String option_type;
        private String Option_Color;
        private String Option_Confirm;
        private boolean isAsk;

        public boolean isAsk() {
            return isAsk;
        }

        public void setAsk(boolean ask) {
            isAsk = ask;
        }

        public String getOption_type() {
            return option_type;
        }

        public void setOption_type(String option_type) {
            this.option_type = option_type;
        }

        public String getOption_Color() {
            return Option_Color;
        }

        public void setOption_Color(String option_Color) {
            Option_Color = option_Color;
        }

        public String getOption_Confirm() {
            return Option_Confirm;
        }

        public void setOption_Confirm(String option_Confirm) {
            Option_Confirm = option_Confirm;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCont() {
            return cont;
        }

        public void setCont(String cont) {
            this.cont = cont;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
