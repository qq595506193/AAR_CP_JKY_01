package com.example.aar_cp_jky_01.bean;

import java.io.Serializable;

public class MetaDataBean implements Serializable {

    /**
     * vSendCode : 1875563
     * flags : 0
     */

    private String vSendCode;
    private int flags;

    public String getVSendCode() {
        return vSendCode;
    }

    public void setVSendCode(String vSendCode) {
        this.vSendCode = vSendCode;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }
}
