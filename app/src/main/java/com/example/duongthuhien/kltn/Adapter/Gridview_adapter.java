package com.example.duongthuhien.kltn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.Word;

import java.util.List;

/**
 * Created by Duong Thu Hien on 5/28/2018.
 */

public class Gridview_adapter extends BaseAdapter {
    private List<Word> mWordList;
    private LayoutInflater mlayoutInflater;
    private int mTypeCustom;

    public Gridview_adapter(Context context, List<Word> WordList) {
        mlayoutInflater = LayoutInflater.from(context);
        mWordList = WordList;

    }

    @Override
    public int getCount() {
        return mWordList == null ? 0 : mWordList.size();
    }

    @Override
    public Object getItem(int pos) {
        return mWordList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return mWordList.get(pos).getId();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        String JWord=mWordList.get(pos).getJword();
        String VWord=mWordList.get(pos).getVword();
        TextView tv_Jword;
        TextView tv_Vword;
        if (convertView==null){
            convertView=mlayoutInflater.inflate(R.layout.item_gridview,parent,false);
            tv_Jword=convertView.findViewById(R.id.tv_Jword);
            tv_Vword=convertView.findViewById(R.id.tv_Vword);
        }else{
            tv_Jword=convertView.findViewById(R.id.tv_Jword);
            tv_Vword=convertView.findViewById(R.id.tv_Vword);
        }
        tv_Jword.setText(JWord);
        tv_Vword.setText(VWord);
        return convertView;
    }
}
