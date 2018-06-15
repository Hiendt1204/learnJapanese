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
    private String addTag(char tagCode, String target) {
        String first = "";
        String last = "";
        switch (tagCode) {
            case 'H':
                first = "<h4>";
                last = "</h4>";
                break;
            case 'E':
                first = "<p><font color=\"purple\">";
                last = "</font></p>";
                break;
            case 'T':
                first = "<p><font color=\"cyan\">";
                last = "</font></p>";
                break;
            case 'R':
                first = "<p><font color=\"red\">";
                last = "</font></p>";
                break;
            default:
                first = "<p>";
                last = "</p>";
                break;
        }
        return first + target + last;
    }
    private void convertHtml(){
        String newStrList = "";
        String[] tmp = this.strListItem.split("\\$");

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].length() == 0) {
                continue;
            } else if (tmp[i].length() == 1) {
                char tag =tmp[i].charAt(0);
                switch (tag) {
                    case 'H':
                    case 'C':
                    case 'E':
                    case 'T':
                    case 'R':
                        tmp[i+1] = addTag(tag, tmp[i+1]);
                        newStrList += tmp[i+1];
                        i++;
                        break;
                }
            }
        }
        this.strListItem = newStrList;
    }
}
