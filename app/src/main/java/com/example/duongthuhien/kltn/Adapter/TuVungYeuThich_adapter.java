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

import com.example.duongthuhien.kltn.Model.TVYT1;
import com.example.duongthuhien.kltn.Model.ThamKhao_frag;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.List;

/**
 * Created by 84973 on 6/22/2018.
 */

public class TuVungYeuThich_adapter extends ArrayAdapter {
    Activity context;
    int resource;
    @NonNull List<TVYT1> objects;

    public TuVungYeuThich_adapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects=objects;
        this.resource=resource;
    }
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        TextView tv_SoThuTu_TVYT = (TextView) row.findViewById(R.id.tv_SoThuTu_TVYT);
        TextView tv_JWord_TVYT = (TextView) row.findViewById(R.id.tv_Jword_TVYT);
        TextView tv_PhienAm_TVYT = (TextView) row.findViewById(R.id.tv_PhienAm_TVYT);
        TextView tv_VWord_TVYT = (TextView) row.findViewById(R.id.tv_Vword_TVYT);
        final ImageView btn_Favorite_TVYT=(ImageView) row.findViewById(R.id.btn_Favorite_TVYT);

        tv_SoThuTu_TVYT.setText(String.valueOf(pos +1));
        tv_JWord_TVYT.setText(objects.get(pos).getStrJWord_TK());
        tv_PhienAm_TVYT.setText(objects.get(pos).getStrPhienAm_TK());
        tv_VWord_TVYT.setText(objects.get(pos).getStrVWord_TK());

        if (objects.get(pos).getFavorite()==1){
            Log.d("hiendt","TVYT_adapter"+ objects.get(pos).getFavorite());
            //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_red_24dp);
            btn_Favorite_TVYT.setColorFilter(context.getResources().getColor(R.color.colorAccent));
        } else {
            //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            btn_Favorite_TVYT.setColorFilter(context.getResources().getColor(R.color.Black));
        }

        btn_Favorite_TVYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDataController sqLiteDataController=new SQLiteDataController(context);
                sqLiteDataController.open();
                SQLiteDatabase database=sqLiteDataController.getMyDatabase();

                if (objects.get(pos).getFavorite()==1){
                    sqLiteDataController.update0FavoriteKanji(objects.get(pos).getId());
                    objects.get(pos).setFavorite(0);
                    //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    btn_Favorite_TVYT.setColorFilter(context.getResources().getColor(R.color.Black));
                }else if (objects.get(pos).getFavorite()==0){
                    sqLiteDataController.update1FavoriteKanji(objects.get(pos).getId());
                    objects.get(pos).setFavorite(1);
                    //btn_FavoriteK1.setImageResource(R.drawable.ic_favorite_border_red_24dp);
                    btn_Favorite_TVYT.setColorFilter(context.getResources().getColor(R.color.colorAccent));
                }
                sqLiteDataController.getFavourite();

            }
        });

        return row;
    }
}
