package com.example.duongthuhien.kltn.Model;

import android.util.Log;

/**
 * Created by 84973 on 6/13/2018.
 */

public class NguPhap_Frag {
    String strListTitle;
    String strListItem;

    public NguPhap_Frag() {
    }

    public NguPhap_Frag(String strListTitle, String strListItem) {
        this.strListTitle = strListTitle;
        this.strListItem = strListItem;
    }

    public String getStrListTitle() {
        return strListTitle;
    }

    public void setStrListTitle(String strListTitle) {
        this.strListTitle = strListTitle;
    }

    public String getStrListItem() {
        return strListItem;
    }

    public void setStrListItem(String strListItem) {
        this.strListItem = strListItem;
        convertHtml();


    }
    private void convertHtml(){
        String[] paragraphs = this.strListItem.split("\\$\\w\\$");
        for(String paragraph : paragraphs) {
            //remove tag
            //String tmp = paragraph.substring(3);
            //Log.d("hiendt","tmp : " + tmp);
        }
    }
}
