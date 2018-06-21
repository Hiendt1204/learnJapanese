package com.example.duongthuhien.kltn.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;
import com.example.duongthuhien.kltn.kanji.Kanji2Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 84973 on 6/8/2018.
 */

public class Kanji1_adapter extends ArrayAdapter{
    Button btn_Play;
    ImageView btn_FavoriteK1;
    Activity context;
    int resource;
    @NonNull List<Kanji1> objects;

    ArrayList<String> mlistSound=new ArrayList<String>();

    private int lessionPosition;
    public Kanji1_adapter(@NonNull Activity context, int resource, @NonNull List objects, int lessionPosition) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
        this.lessionPosition = lessionPosition;


    }
    public View getView(final int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        btn_Play=row.findViewById(R.id.btn_PlayK1);
        TextView tv_Sothutu=row.findViewById(R.id.tv_SoThuTu);
        TextView tv_JWord_K=row.findViewById(R.id.tv_JWord_K);
        TextView tv_VWord_K=row.findViewById(R.id.tv_VWord_K);
        TextView tv_On=row.findViewById(R.id.tv_On);
        TextView tv_Kun=row.findViewById(R.id.tv_Kun);
        ImageView tv_MoTa=row.findViewById(R.id.img_MoTa);
        TextView tv_MoTa_V=row.findViewById(R.id.tv_MoTa_V);
        ListView lv_Kanji1=row.findViewById(R.id.lv_Kanji1);
        btn_FavoriteK1=row.findViewById(R.id.btn_FavoriteK1);


        btn_FavoriteK1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDataController sqLiteDataController=new SQLiteDataController(context);
                 sqLiteDataController.open();
                SQLiteDatabase database=sqLiteDataController.getMyDatabase();

                if (objects.get(pos).getFavorite()==1){
                    Log.d("hiendt","getFavorite  "+objects.get(pos).getFavorite());
                    sqLiteDataController.update0FavoriteKanji(pos+1);
                    btn_FavoriteK1.setColorFilter(R.color.colorAccent,android.graphics.PorterDuff.Mode.MULTIPLY);
                }else if (objects.get(pos).getFavorite()==0){
                    Log.d("hiendt","getFavorite1  "+objects.get(pos).getFavorite());
                    sqLiteDataController.update1FavoriteKanji(pos+1);
                    btn_FavoriteK1.setColorFilter(R.color.colorGTCB,android.graphics.PorterDuff.Mode.MULTIPLY);;
                }
            }
        });

        btn_Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resourceId = context.getResources()
                        .getIdentifier(objects.get(pos).getSoundK(),
                                "raw", context.getPackageName());
            }
        });

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Kanji2Activity.class);
                intent.putExtra("PosK1",pos);
                intent.putExtra("ID",objects.get(pos).getStr_Sothutu());
                intent.putExtra("LessionPosition", Kanji1_adapter.this.lessionPosition);
                context.startActivity(intent);

            }
        });


        final Kanji1 kanji1=(Kanji1) this.objects.get(pos);


        tv_JWord_K.setText(Html.fromHtml(kanji1.getStr_JWord_K()));
        tv_Sothutu.setText(String.valueOf(pos +1));
        tv_VWord_K.setText(kanji1.getStr_VWord_K());
        tv_On.setText(kanji1.getStr_On());
        tv_Kun.setText(kanji1.getStr_Kun());
        int resourceId = this.context.getResources().getIdentifier(kanji1.getStr_MoTa(),
                "drawable", this.context.getPackageName());
        Log.d("hiendt","kun " + kanji1.getStr_Kun());
        tv_MoTa.setImageResource(resourceId);
        tv_MoTa_V.setText(Html.fromHtml(kanji1.getStr_MoTa_V()));



        return row;
    }


}
