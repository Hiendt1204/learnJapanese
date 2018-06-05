package com.example.duongthuhien.kltn.Model;

import java.io.Serializable;

/**
 * Created by Duong Thu Hien on 6/5/2018.
 */

public class Detail_MCCB implements Serializable {
    int id;
    int JWord;
    int VWord;
    int PhienAm;

    public Detail_MCCB() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJWord() {
        return JWord;
    }

    public void setJWord(int JWord) {
        this.JWord = JWord;
    }

    public int getVWord() {
        return VWord;
    }

    public void setVWord(int VWord) {
        this.VWord = VWord;
    }

    public int getPhienAm() {
        return PhienAm;
    }

    public void setPhienAm(int phienAm) {
        PhienAm = phienAm;
    }
}
