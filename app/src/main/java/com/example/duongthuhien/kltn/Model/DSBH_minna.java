package com.example.duongthuhien.kltn.Model;

import java.io.Serializable;

/**
 * Created by Duong Thu Hien on 6/8/2018.
 */

public class DSBH_minna implements Serializable {
    private String str_TenBaihoc;

    public DSBH_minna() {
    }

    public String getStr_TenBaihoc() {
        return str_TenBaihoc;
    }

    public void setStr_TenBaihoc(String str_TenBaihoc) {
        this.str_TenBaihoc = str_TenBaihoc;
    }
}
