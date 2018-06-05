package com.example.duongthuhien.kltn.Adapter;

import android.app.Activity;
import android.media.Image;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.duongthuhien.kltn.Model.BaiHocMCCB;
import com.example.duongthuhien.kltn.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong Thu Hien on 6/4/2018.
 */

public class MCCB_adapter extends ArrayAdapter {

    ImageView imv_iconMCCB;
    TextView tv_lvMCCB;
    ListView ll_MCCB;

    Activity context;
    int resource;
     List objects;

    public MCCB_adapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }
    public View getView(final int pos, View convertView, ViewGroup patent){
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);

        imv_iconMCCB=(ImageView) row.findViewById(R.id.imv_iconMCCB);
        tv_lvMCCB=row.findViewById(R.id.tv_lvMCCB);
        ll_MCCB=row.findViewById(R.id.ll_Maucaucoban);

        final BaiHocMCCB baiHocMCCB=(BaiHocMCCB)this.objects.get(pos);

        tv_lvMCCB.setText(baiHocMCCB.getTrName());
        int resourceId = this.context.getResources().getIdentifier(baiHocMCCB.getUrlImage(), "drawable", this.context.getPackageName());
        imv_iconMCCB.setImageResource(resourceId);




    return row;
    }

}
