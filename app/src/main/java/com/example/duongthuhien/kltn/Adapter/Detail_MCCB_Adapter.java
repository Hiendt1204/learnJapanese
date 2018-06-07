package com.example.duongthuhien.kltn.Adapter;

import android.app.Activity;
import android.content.Context;
import android.media.SoundPool;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.Detail_MCCB;
import com.example.duongthuhien.kltn.NewWord;
import com.example.duongthuhien.kltn.NewWordMCCB;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong Thu Hien on 6/4/2018.
 */

public class Detail_MCCB_Adapter extends ArrayAdapter {
    Button btn_PlayMCCB;
    TextView tv_JWord;
    TextView tv_VWord;
    TextView tv_PhienAm;

    List<NewWordMCCB> newWordMCCBS=new ArrayList<NewWordMCCB>();
    Activity context;
    int resource;
    List objects;

    public Detail_MCCB_Adapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource,null);

        tv_JWord=row.findViewById(R.id.tv_Jword);
        tv_VWord=row.findViewById(R.id.tv_Vword);
        tv_PhienAm=row.findViewById(R.id.tv_PhienAm);


        final NewWordMCCB detail_mccb =(NewWordMCCB) this.objects.get(pos);
        tv_JWord.setText(detail_mccb.getJNewWord());
        tv_PhienAm.setText(detail_mccb.getPhienAm());
        tv_VWord.setText(detail_mccb.getVNewWord());
        return row;
    }
}
