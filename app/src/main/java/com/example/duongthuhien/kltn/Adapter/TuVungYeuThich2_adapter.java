package com.example.duongthuhien.kltn.Adapter;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.Tumoi_Frag;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.List;

/**
 * Created by 84973 on 6/22/2018.
 */

public class TuVungYeuThich2_adapter extends ArrayAdapter {

    Activity context;
    int resource;
    @NonNull List<Tumoi_Frag> objects;
    public TuVungYeuThich2_adapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        TextView tv_SoThuTu_M = (TextView) row.findViewById(R.id.tv_SoThuTu_M);
        TextView tv_JWord_M = (TextView) row.findViewById(R.id.tv_JWord_M);
        TextView tv_PhienAm_M = (TextView) row.findViewById(R.id.tv_PhienAm_M);
        TextView tv_VWord_M = (TextView) row.findViewById(R.id.tv_VWord_M);
        final ImageView btn_Favorite = (ImageView) row.findViewById(R.id.btn_Favorite);
        Button btn_PlayM1 = (Button) row.findViewById(R.id.btn_PlayM1);
        TextView tv_Kanji_M = (TextView) row.findViewById(R.id.tv_Kanji_M);
        TextView tv_cnMean_M = (TextView) row.findViewById(R.id.tv_cnMean_M);

        if (objects.get(pos).getFavorite()==1){
            //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_red_24dp);
            btn_Favorite.setColorFilter(context.getResources().getColor(R.color.colorAccent));
        } else {
            //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            btn_Favorite.setColorFilter(context.getResources().getColor(R.color.colorGTCB));
        }

        btn_Favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDataController sqLiteDataController=new SQLiteDataController(context);
                sqLiteDataController.open();
                SQLiteDatabase database=sqLiteDataController.getMyDatabase();

                if (objects.get(pos).getFavorite()==1){

                    sqLiteDataController.update0FavoriteKanji(objects.get(pos).getId());
                    objects.get(pos).setFavorite(0);
                    //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    btn_Favorite.setColorFilter(context.getResources().getColor(R.color.Black));
                }else if (objects.get(pos).getFavorite()==0){
                    Log.d("hiendt","getFavorite1  "+objects.get(pos).getFavorite());
                    sqLiteDataController.update1FavoriteKanji(objects.get(pos).getId());
                    objects.get(pos).setFavorite(1);
                    //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_red_24dp);
                    btn_Favorite.setColorFilter(context.getResources().getColor(R.color.colorAccent));
                }
                sqLiteDataController.getFavourite();
            }
        });

        tv_SoThuTu_M.setText("" + objects.get(pos).getSoThuTu_M());
        tv_JWord_M.setText(objects.get(pos).getStrJWord_M());
        tv_PhienAm_M.setText(objects.get(pos).getStrPhienAm_M());
        tv_VWord_M.setText(objects.get(pos).getStrVWord_M());
        tv_Kanji_M.setText(objects.get(pos).getStrKanji());
        tv_cnMean_M.setText(objects.get(pos).getStrCn_Mean());



        return row;
    }
}