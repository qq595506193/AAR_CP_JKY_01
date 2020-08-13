package com.example.aar_cp_jky_01.bean;

import java.util.List;

public class DiseaseMessageBean {


    /**
     * cont : 有没有出现以下症状？请选择或者直接输入
     * v_send_code : 1899127
     * situations : ["都没有","发热","咳嗽","胸闷","气急","呼吸困难","腹部压痛","下腹坠胀","腹部反跳痛","腹部肿块","呕吐","腹泻","黄疸","恶心","黑便","阴道出血","便血","腹胀","腰痛","肝肿大","血尿","盆腔包块","阴道不规则出血","呕血","腹肌紧张"]
     */

    private String cont;
    private String v_send_code;
    private List<String> situations;

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getV_send_code() {
        return v_send_code;
    }

    public void setV_send_code(String v_send_code) {
        this.v_send_code = v_send_code;
    }

    public List<String> getSituations() {
        return situations;
    }

    public void setSituations(List<String> situations) {
        this.situations = situations;
    }
}
