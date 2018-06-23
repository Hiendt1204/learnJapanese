package com.example.duongthuhien.kltn.Model;

/**
 * Created by 84973 on 6/24/2018.
 */

public class TVYT1 {
    int id;
    int SoThuTu_TK;
    String strJWord_TK;
    String strPhienAm_TK;
    String strVWord_TK;
    int favorite;

    public TVYT1() {

    }

    public TVYT1(int id, int soThuTu_TK, String strJWord_TK, String strPhienAm_TK, String strVWord_TK,int favorite ) {
        this.id = id;
        SoThuTu_TK = soThuTu_TK;
        this.strJWord_TK = strJWord_TK;
        this.strPhienAm_TK = strPhienAm_TK;
        this.strVWord_TK = strVWord_TK;
        this.favorite=favorite;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoThuTu_TK() {
        return SoThuTu_TK;
    }

    public void setSoThuTu_TK(int soThuTu_TK) {
        SoThuTu_TK = soThuTu_TK;
    }

    public String getStrJWord_TK() {
        return strJWord_TK;
    }

    public void setStrJWord_TK(String strJWord_TK) {
        this.strJWord_TK = strJWord_TK;
    }

    public String getStrPhienAm_TK() {
        return strPhienAm_TK;
    }

    public void setStrPhienAm_TK(String strPhienAm_TK) {
        this.strPhienAm_TK = strPhienAm_TK;
    }

    public String getStrVWord_TK() {
        return strVWord_TK;
    }

    public void setStrVWord_TK(String strVWord_TK) {
        this.strVWord_TK = strVWord_TK;
    }
}
