package com.example.duongthuhien.kltn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Minna.TuMoiFragment;
import com.example.duongthuhien.kltn.Model.Tumoi_Frag;
import com.example.duongthuhien.kltn.R;

import java.util.ArrayList;

/**
 * Created by 84973 on 6/12/2018.
 */

public class TuMoiFrag_Adapter extends BaseAdapter {
    private static ArrayList<Tumoi_Frag> listTuMoiFrag;

    private LayoutInflater mInflater;
    public TuMoiFrag_Adapter(Context TuMoiFragment, ArrayList<Tumoi_Frag> results){
        listTuMoiFrag = results;
        mInflater = LayoutInflater.from(TuMoiFragment);
    }


    @Override
    public int getCount() {
        return listTuMoiFrag.size();
    }

    @Override
    public Object getItem(int position) {
        return listTuMoiFrag.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_tumoi_fagment, null);
            holder = new ViewHolder();
            holder.tv_SoThuTu_M = (TextView) convertView.findViewById(R.id.tv_SoThuTu_M);
            holder.tv_JWord_M = (TextView) convertView.findViewById(R.id.tv_JWord_M);
            holder.tv_PhienAm_M = (TextView) convertView.findViewById(R.id.tv_PhienAm_M);
            holder.tv_VWord_M = (TextView) convertView.findViewById(R.id.tv_VWord_M);
            holder.btn_Favorite = (Button) convertView.findViewById(R.id.btn_Favorite);
            holder.btn_PlayM1 = (Button) convertView.findViewById(R.id.btn_PlayM1);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_SoThuTu_M.setText(listTuMoiFrag.get(position).getSoThuTu_M());
        holder.tv_JWord_M.setText(listTuMoiFrag.get(position).getStrJWord_M());
        holder.tv_PhienAm_M.setText(listTuMoiFrag.get(position).getStrPhienAm_M());
        holder.tv_VWord_M.setText(listTuMoiFrag.get(position).getStrVWord_M());

        return convertView;
    }

    static class ViewHolder{
        TextView tv_SoThuTu_M, tv_JWord_M,tv_PhienAm_M,tv_VWord_M;
        Button btn_PlayM1,btn_Favorite;
    }


}
