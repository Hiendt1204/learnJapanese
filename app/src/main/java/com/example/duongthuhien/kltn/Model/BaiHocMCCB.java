package com.example.duongthuhien.kltn.Model;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by Duong Thu Hien on 6/4/2018.
 */

public class BaiHocMCCB implements Serializable {
    private String urlImage;
    private String trName;

    public BaiHocMCCB() {
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getTrName() {
        return trName;
    }

    public void setTrName(String trName) {
        this.trName = trName;
    }


}

