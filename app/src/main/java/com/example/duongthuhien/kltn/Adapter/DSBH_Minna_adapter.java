package com.example.duongthuhien.kltn.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.DSBH_minna;
import com.example.duongthuhien.kltn.R;

import java.util.List;

/**
 * Created by Duong Thu Hien on 6/8/2018.
 */

public class DSBH_Minna_adapter extends ArrayAdapter {
    Activity context;
    int resource;
    List objects;

    public DSBH_Minna_adapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public View getView(final  int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);

        final DSBH_minna dsbh_minna=(DSBH_minna) this.objects.get(pos);

        //TextView tv_TenBaiHocM=row.findViewById(R.id.tv_TenbaihocM);
        //tv_TenBaiHocM.setText(dsbh_minna.getStr_TenBaihoc());

        return row;
    }
}
