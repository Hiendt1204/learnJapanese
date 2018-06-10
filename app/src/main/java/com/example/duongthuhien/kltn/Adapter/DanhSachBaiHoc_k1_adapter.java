package com.example.duongthuhien.kltn.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.BaiHocH;
import com.example.duongthuhien.kltn.R;

import java.util.List;

/**
 * Created by 84973 on 6/9/2018.
 */

public class DanhSachBaiHoc_k1_adapter extends ArrayAdapter{
    // man hình sử dụng giao diện này
    Activity context;
    //layout cho từng dòng muốn hiển thị
    int resource;
    // danh sách nguồn dữ liệu muốn hiển thị lên giao diện
    List objects;
    int trangthaibtn;
    public DanhSachBaiHoc_k1_adapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //LayoutInflater dùng để buil 1 lớp bình thường thành code java mà android có thể sử dụng được
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);

        TextView tv_tenBaiHocK1=row.findViewById(R.id.tv_tenBaiHocK1);

        final BaiHocH baiHocH= (BaiHocH) this.objects.get(position);

        tv_tenBaiHocK1.setText(baiHocH.getStrBaiHoc());

        return row;
    }
}
