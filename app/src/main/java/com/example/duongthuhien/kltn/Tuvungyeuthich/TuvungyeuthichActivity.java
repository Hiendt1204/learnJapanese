package com.example.duongthuhien.kltn.Tuvungyeuthich;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.duongthuhien.kltn.Adapter.TuVungYeuThich_Kanji_adapter;
import com.example.duongthuhien.kltn.Adapter.TuVungYeuThich_TuMoi_adapter;
import com.example.duongthuhien.kltn.Adapter.TuVungYeuThich_adapter;
import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.Model.TVYT1;
import com.example.duongthuhien.kltn.Model.ThamKhao_frag;
import com.example.duongthuhien.kltn.Model.Tumoi_Frag;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.ArrayList;

public class TuvungyeuthichActivity extends AppCompatActivity {
    ListView lv_TuVungYeuThich;
    int posF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuvungyeuthich);
        addControls();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        posF=intent.getIntExtra("posF",-1);

        ArrayList<Kanji1> kanji1List=new ArrayList<Kanji1>();
        ArrayList<Tumoi_Frag> MCCB1List=new ArrayList<Tumoi_Frag>();
        ArrayList<TVYT1> favoriteList=new ArrayList<TVYT1>();
        SQLiteDataController sqLiteDataController=new SQLiteDataController(TuvungyeuthichActivity.this);
        sqLiteDataController.open();
        SQLiteDatabase database=sqLiteDataController.getMyDatabase();

        if (posF==1){

            kanji1List= sqLiteDataController.getFavouriteK();
            TuVungYeuThich_Kanji_adapter kanji_adapter=new TuVungYeuThich_Kanji_adapter
                    (TuvungyeuthichActivity.this,R.layout.item_listview_kanji1,kanji1List);

            lv_TuVungYeuThich.setAdapter(kanji_adapter);
        }else if (posF==2){
            MCCB1List= sqLiteDataController.getFavouriteM();
            TuVungYeuThich_TuMoi_adapter kanji_adapter=new TuVungYeuThich_TuMoi_adapter
                    (TuvungyeuthichActivity.this,R.layout.item_tumoi_fagment,MCCB1List);

            lv_TuVungYeuThich.setAdapter(kanji_adapter);
        }else if (posF==0){
            favoriteList= sqLiteDataController.getFavourite();
            TuVungYeuThich_adapter kanji_adapter=new TuVungYeuThich_adapter
                    (TuvungyeuthichActivity.this,R.layout.item_tuvungyeuthich,favoriteList);

            lv_TuVungYeuThich.setAdapter(kanji_adapter);
        }



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addControls() {
        lv_TuVungYeuThich=findViewById(R.id.lv_TuVungYeuThich);
    }
}
