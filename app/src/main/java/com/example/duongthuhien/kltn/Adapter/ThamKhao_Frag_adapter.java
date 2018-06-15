package com.example.duongthuhien.kltn.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.ThamKhao_frag;
import com.example.duongthuhien.kltn.R;

import java.util.ArrayList;

/**
 * Created by 84973 on 6/15/2018.
 */

public class ThamKhao_Frag_adapter extends BaseAdapter {
    private static ArrayList<ThamKhao_frag> listThamKhaoFrag;
    private LayoutInflater minflater;

    public ThamKhao_Frag_adapter(Context ThamKhaoFragment,ArrayList<ThamKhao_frag> results){
        listThamKhaoFrag = results;
        minflater = LayoutInflater.from(ThamKhaoFragment);
    }
    @Override
    public int getCount() {
        return listThamKhaoFrag.size();
    }

    @Override
    public Object getItem(int position) {
        return listThamKhaoFrag.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = minflater.inflate(R.layout.item_thamkhao_fragment, null);
            holder = new ViewHolder();
            holder.tv_SoThuTu_TK = (TextView) convertView.findViewById(R.id.tv_SoThuTu_TK);
            holder.tv_JWord_TK = (TextView) convertView.findViewById(R.id.tv_Jword_TK);
            holder.tv_PhienAm_TK = (TextView) convertView.findViewById(R.id.tv_PhienAm_TK);
            holder.tv_VWord_TK = (TextView) convertView.findViewById(R.id.tv_Vword_TK);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_SoThuTu_TK.setText("" + (position+1));
        holder.tv_JWord_TK.setText(listThamKhaoFrag.get(position).getStrJWord_TK());
        holder.tv_PhienAm_TK.setText(listThamKhaoFrag.get(position).getStrPhienAm_TK());
        holder.tv_VWord_TK.setText(listThamKhaoFrag.get(position).getStrVWord_TK());

        return convertView;
    }

    static class ViewHolder{
        TextView tv_SoThuTu_TK, tv_JWord_TK,tv_PhienAm_TK,tv_VWord_TK;
    }
}
