package com.example.duongthuhien.kltn.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.Kaiwa_frag;
import com.example.duongthuhien.kltn.Model.ThamKhao_frag;
import com.example.duongthuhien.kltn.R;

import java.util.ArrayList;

/**
 * Created by Duong Thu Hien on 6/27/2018.
 */

public class Kaiwa_adapter extends BaseAdapter{
    private static ArrayList<Kaiwa_frag> listKaiwaFrag;
    private LayoutInflater minflater;
    Context KaiwaFragment;

    public Kaiwa_adapter(Context KaiwaFragment, ArrayList<Kaiwa_frag> results){
        listKaiwaFrag = results;
        minflater = LayoutInflater.from(KaiwaFragment);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Kaiwa_adapter.ViewHolder holder;
        if(convertView == null){
            convertView = minflater.inflate(R.layout.item_kaiwa_frag, null);
            holder = new ViewHolder();
            holder.tv_Jword = (TextView) convertView.findViewById(R.id.tv_Jword);
            holder.tv_NameJ = (TextView) convertView.findViewById(R.id.tv_NameJ);
            holder.tv_NameK = (TextView) convertView.findViewById(R.id.tv_NameK);
            holder.tv_PhienAm = (TextView) convertView.findViewById(R.id.tv_PhienAm);
            holder.tv_Vword = (TextView) convertView.findViewById(R.id.tv_Vword);

            convertView.setTag(holder);
        } else {
            holder = (Kaiwa_adapter.ViewHolder) convertView.getTag();
        }

        holder.tv_Jword.setText(listKaiwaFrag.get(position).getStrJWord());
        holder.tv_PhienAm.setText(listKaiwaFrag.get(position).getStrPhienAm());
        holder.tv_Vword.setText(listKaiwaFrag.get(position).getStrVword());
        holder.tv_NameJ.setText(listKaiwaFrag.get(position).getStrNamJ());
        holder.tv_NameK.setText(listKaiwaFrag.get(position).getStrNameK());

        return convertView;
    }

    static class ViewHolder{
        TextView tv_NameK,tv_NameJ,tv_Vword,tv_Jword,tv_PhienAm;

    }

    @Override
    public int getCount() {
        return listKaiwaFrag.size();
    }

    @Override
    public Object getItem(int position) {
        return listKaiwaFrag.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
