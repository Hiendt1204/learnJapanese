package com.example.duongthuhien.kltn.Model;

import android.media.Image;
import android.media.SoundPool;

/**
 * Created by Duong Thu Hien on 6/4/2018.
 */

public class NewWordMCCB {
    int id;
    int catagory_id;
    String sound_NewWord;
    String JNewWord;
    String VNewWord;
    String PhienAm;

    public NewWordMCCB() {
    }

    public NewWordMCCB(int id, int catagory_id, String sound_NewWord, String JNewWord, String VNewWord, String PhienAm) {
        this.id = id;
        this.catagory_id = catagory_id;
        this.sound_NewWord = sound_NewWord;
        this.JNewWord = JNewWord;
        this.VNewWord = VNewWord;
        this.PhienAm = PhienAm;
    }

    public String getPhienAm() {
        return PhienAm;
    }

    public void setPhienAm(String phienAm) {
        PhienAm = phienAm;
    }

    public String getSound_NewWord() {
        return sound_NewWord;
    }

    public void setSound_NewWord(String sound_NewWord) {
        this.sound_NewWord = sound_NewWord;
    }

    public int getCatagory_id() {
        return catagory_id;
    }

    public void setCatagory_id(int catagory_id) {
        this.catagory_id = catagory_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJNewWord() {
        return JNewWord;
    }

    public void setJNewWord(String JNewWord) {
        this.JNewWord = JNewWord;
    }

    public String getVNewWord() {
        return VNewWord;
    }

    public void setVNewWord(String VNewWord) {
        this.VNewWord = VNewWord;
    }
}
