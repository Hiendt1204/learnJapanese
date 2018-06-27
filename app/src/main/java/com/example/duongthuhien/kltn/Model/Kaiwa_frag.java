package com.example.duongthuhien.kltn.Model;

/**
 * Created by Duong Thu Hien on 6/27/2018.
 */

public class Kaiwa_frag {
    int id;
    String strNameK;
    String strNamJ;
    String strJWord;
    String strPhienAm;
    String strVword;

    public Kaiwa_frag() {
    }

    public Kaiwa_frag(int id, String strNameK, String strNamJ, String strJWord, String strPhienAm, String strVword) {
        this.id = id;
        this.strNameK = strNameK;
        this.strNamJ = strNamJ;
        this.strJWord = "";
        this.strPhienAm = "";
        this.strVword = strVword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStrNameK() {
        return strNameK;
    }

    public void setStrNameK(String strNameK) {
        this.strNameK = strNameK;
    }

    public String getStrNamJ() {
        return strNamJ;
    }

    public void setStrNamJ(String strNamJ) {
        this.strNamJ = strNamJ;
    }

    public String getStrJWord() {

        return strJWord;
    }

    public void setStrJWord(String strJWord) {
        if (strJWord!=null){
            this.strJWord = strJWord;
        }

    }

    public String getStrPhienAm() {
        return strPhienAm;
    }

    public void setStrPhienAm(String strPhienAm) {
        if (strPhienAm!=null){
            this.strPhienAm = strPhienAm;
        }

    }

    public String getStrVword() {
        return strVword;
    }

    public void setStrVword(String strVword) {
        this.strVword = strVword;
    }
}
