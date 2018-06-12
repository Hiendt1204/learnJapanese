package com.example.duongthuhien.kltn.Model;

/**
 * Created by 84973 on 6/11/2018.
 */

public class Tumoi_Frag {
    int SoThuTu_M;
    String strJWord_M;
    String strPhienAm_M;
    String strVWord_M;
    String strPlayM1;
    int Favorite;

    public Tumoi_Frag() {
    }

    public Tumoi_Frag(int soThuTu_M, String strJWord_M,
                      String strPhienAm_M, String strVWord_M, String strPlayM1, int favorite) {
        SoThuTu_M = soThuTu_M;
        this.strJWord_M = strJWord_M;
        this.strPhienAm_M = strPhienAm_M;
        this.strVWord_M = strVWord_M;
        this.strPlayM1 = strPlayM1;
        Favorite = favorite;
    }

    public int getSoThuTu_M() {
        return SoThuTu_M;
    }

    public void setSoThuTu_M(int soThuTu_M) {
        SoThuTu_M = soThuTu_M;
    }

    public String getStrJWord_M() {
        return strJWord_M;
    }

    public void setStrJWord_M(String strJWord_M) {
        this.strJWord_M = strJWord_M;
    }

    public String getStrPhienAm_M() {
        return strPhienAm_M;
    }

    public void setStrPhienAm_M(String strPhienAm_M) {
        this.strPhienAm_M = strPhienAm_M;
    }

    public String getStrVWord_M() {
        return strVWord_M;
    }

    public void setStrVWord_M(String strVWord_M) {
        this.strVWord_M = strVWord_M;
    }

    public String getStrPlayM1() {
        return strPlayM1;
    }

    public void setStrPlayM1(String strPlayM1) {
        this.strPlayM1 = strPlayM1;
    }

    public int getFavorite() {
        return Favorite;
    }

    public void setFavorite(int favorite) {
        Favorite = favorite;
    }
}
