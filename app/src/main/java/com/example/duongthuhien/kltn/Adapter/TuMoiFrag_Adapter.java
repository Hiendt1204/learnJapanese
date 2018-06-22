package com.example.duongthuhien.kltn.Adapter;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Minna.TuMoiFragment;
import com.example.duongthuhien.kltn.Model.Tumoi_Frag;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;
import com.example.duongthuhien.kltn.Tuvungyeuthich.TuvungyeuthichActivity;

import java.util.ArrayList;

/**
 * Created by 84973 on 6/12/2018.
 */

public class TuMoiFrag_Adapter extends BaseAdapter  {
    private static ArrayList<Tumoi_Frag> listTuMoiFrag;
    int mpos;
    Context context;
    ArrayList<Tumoi_Frag> results;


    private LayoutInflater mInflater;
    public TuMoiFrag_Adapter(Context context, ArrayList<Tumoi_Frag> results){
        this.context=context;
        this.results=results;

        listTuMoiFrag = results;
        mInflater = LayoutInflater.from(context);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        mpos=position;
        final ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_tumoi_fagment, null);
            holder = new ViewHolder();
            holder.tv_SoThuTu_M = (TextView) convertView.findViewById(R.id.tv_SoThuTu_M);
            holder.tv_JWord_M = (TextView) convertView.findViewById(R.id.tv_JWord_M);
            holder.tv_PhienAm_M = (TextView) convertView.findViewById(R.id.tv_PhienAm_M);
            holder.tv_VWord_M = (TextView) convertView.findViewById(R.id.tv_VWord_M);
            holder.btn_Favorite = (ImageView) convertView.findViewById(R.id.btn_Favorite);
            holder.btn_PlayM1 = (Button) convertView.findViewById(R.id.btn_PlayM1);
            holder.tv_Kanji_M = (TextView) convertView.findViewById(R.id.tv_Kanji_M);
            holder.tv_cnMean_M = (TextView) convertView.findViewById(R.id.tv_cnMean_M);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_SoThuTu_M.setText("" + listTuMoiFrag.get(position).getSoThuTu_M());
        holder.tv_JWord_M.setText(listTuMoiFrag.get(position).getStrJWord_M());
        holder.tv_PhienAm_M.setText(listTuMoiFrag.get(position).getStrPhienAm_M());
        holder.tv_VWord_M.setText(listTuMoiFrag.get(position).getStrVWord_M());
        holder.tv_Kanji_M.setText(listTuMoiFrag.get(position).getStrKanji());
        holder.tv_cnMean_M.setText(listTuMoiFrag.get(position).getStrCn_Mean());
        holder.btn_Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDataController sqLiteDataController=new SQLiteDataController(context);
                sqLiteDataController.open();
                SQLiteDatabase database=sqLiteDataController.getMyDatabase();

                if (listTuMoiFrag.get(position).getFavorite()==1){
                    Log.d("hiendt","getFavorite  "+listTuMoiFrag.get(position).getFavorite());
                    sqLiteDataController.update0FavoriteKotoba(listTuMoiFrag.get(position).getId());
                    listTuMoiFrag.get(position).setFavorite(0);
                    //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    holder.btn_Favorite.setColorFilter(context.getResources().getColor(R.color.Black));
                }else if (listTuMoiFrag.get(position).getFavorite()==0){
                    sqLiteDataController.update1FavoriteKotoba(listTuMoiFrag.get(position).getId());
                    listTuMoiFrag.get(position).setFavorite(1);
                    //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_red_24dp);
                    holder.btn_Favorite.setColorFilter(context.getResources().getColor(R.color.colorAccent));
                }
                sqLiteDataController.getFavourite();
            }

        });

        return convertView;
    }



     class ViewHolder{
        TextView tv_SoThuTu_M, tv_JWord_M,tv_PhienAm_M,tv_VWord_M,tv_Kanji_M,tv_cnMean_M;
        Button btn_PlayM1;
        ImageView btn_Favorite;
    }


}
