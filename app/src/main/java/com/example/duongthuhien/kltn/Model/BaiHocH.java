package com.example.duongthuhien.kltn.Model;

import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Duong Thu Hien on 5/24/2018.
 */

public class BaiHocH implements Serializable {
    private String strBaiHoc;

    public String getStrBaiHoc() {
        return strBaiHoc;
    }

    public void setStrBaiHoc(String strBaiHoc) {
        this.strBaiHoc = strBaiHoc;
    }
    public BaiHocH(String s) {
        strBaiHoc = s;
    }
/*
    public BaiHocH(String s) {
       //this.tv_BaiHoc = s;
    }

    public TextView getTv_BaiHoc() {
        return tv_BaiHoc;
    }

    public void setTv_BaiHoc(TextView tv_BaiHoc) {
        this.tv_BaiHoc = tv_BaiHoc;
    }

    public BaiHocH(TextView tv_BaiHoc) {
        this.tv_BaiHoc = tv_BaiHoc;
    }

    private TextView tv_BaiHoc;*/
}
