package com.example.duongthuhien.kltn.Model;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 84973 on 6/8/2018.
 */

public class Kanji1 implements Serializable {
    int id;
    int str_Sothutu;
    String str_JWord_K;
    String str_VWord_K;
    String str_On;
    String str_Kun;
    String str_MoTa;
    String str_MoTa_V;
    String soundK;
    String str_AmHan;
    String str_ronjomi;
    String str_rkunjomi;
    String str_ViDu;
    ArrayList<KanjiExample> mExampleList;




    public Kanji1() {
        mExampleList = new ArrayList<KanjiExample>();
    }

    public Kanji1(int id,int str_Sothutu, String str_JWord_K, String str_VWord_K, String str_On,
                  String str_Kun, String str_MoTa, String str_MoTa_V,String soundK,String str_AmHan,
                          String str_ronjomi, String str_rkunjomi,String str_ViDu) {
        this.id=id;
        this.str_Sothutu = str_Sothutu;
        this.str_JWord_K = str_JWord_K;
        this.str_VWord_K = str_VWord_K;
        this.str_On = str_On;
        this.str_Kun = str_Kun;
        this.str_MoTa = str_MoTa;
        this.str_MoTa_V = str_MoTa_V;
        this.soundK=soundK;
        this.str_AmHan=str_AmHan;
        this.str_ronjomi=str_ronjomi;
        this.str_rkunjomi=str_rkunjomi;
        mExampleList = new ArrayList<KanjiExample>();
        setStr_ViDu(str_ViDu);

    }

    public String getStr_ViDu() {
        return str_ViDu;
    }

    public void setStr_ViDu(String str_ViDu) {
        this.str_ViDu = str_ViDu;
        String[] rowStr = this.str_ViDu.split("※");
        for(String row : rowStr) {
            String[] cols = row.split("∴");
            if (cols != null && cols.length > 0) {
                KanjiExample example = new KanjiExample();
                example.setCot1(cols[0]);
                example.setCot2(cols[1]);
                example.setCot3(cols[2]);
                mExampleList.add(example);
            }
        }
    }

    public ArrayList<KanjiExample> getExampleList() {
        return mExampleList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStr_AmHan() {
        return str_AmHan;
    }

    public void setStr_AmHan(String str_AmHan) {
        this.str_AmHan = str_AmHan;
    }

    public String getStr_ronjomi() {
        return str_ronjomi;
    }

    public void setStr_ronjomi(String str_ronjomi) {
        this.str_ronjomi = str_ronjomi;
    }

    public String getStr_rkunjomi() {
        return str_rkunjomi;
    }

    public void setStr_rkunjomi(String str_rkunjomi) {
        this.str_rkunjomi = str_rkunjomi;
    }

    public String getSoundK() {
        return soundK;
    }

    public void setSoundK(String soundK) {
        this.soundK = soundK;
    }

    public int getStr_Sothutu() {
        return str_Sothutu;
    }

    public void setStr_Sothutu(int str_Sothutu) {
        this.str_Sothutu = str_Sothutu;
    }

    public String getStr_JWord_K() {
        return str_JWord_K;
    }

    public void setStr_JWord_K(String str_JWord_K) {
        this.str_JWord_K = str_JWord_K;
    }

    public String getStr_VWord_K() {
        return str_VWord_K;
    }

    public void setStr_VWord_K(String str_VWord_K) {
        this.str_VWord_K = str_VWord_K;
    }

    public String getStr_On() {
        return str_On;
    }

    public void setStr_On(String str_On) {
        this.str_On = str_On;
    }

    public String getStr_Kun() {
        return str_Kun;
    }

    public void setStr_Kun(String str_Kun) {
        this.str_Kun = str_Kun;
    }

    public String getStr_MoTa() {
        return str_MoTa;
    }

    public void setStr_MoTa(String str_MoTa) {
        this.str_MoTa = str_MoTa;
    }

    public String getStr_MoTa_V() {
        return str_MoTa_V;
    }

    public void setStr_MoTa_V(String str_MoTa_V) {
        this.str_MoTa_V = str_MoTa_V;
    }

}
