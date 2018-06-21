package com.example.duongthuhien.kltn.Tuvungyeuthich;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.duongthuhien.kltn.Adapter.TuVungYeuThich1_adapter;
import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.ArrayList;

public class TuvungyeuthichActivity extends AppCompatActivity {
    ListView lv_TuVungYeuThich;
    TuVungYeuThich1_adapter tuVungYeuThich_adapter;
    int posF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuvungyeuthich);
        addControls();

        Intent intent=getIntent();
        posF=intent.getIntExtra("posF",-1);

        if (posF==1){
            ArrayList<Kanji1> kanji1List=new ArrayList<Kanji1>();
            SQLiteDataController sqLiteDataController=new SQLiteDataController(TuvungyeuthichActivity.this);
            sqLiteDataController.open();
            SQLiteDatabase database=sqLiteDataController.getMyDatabase();

            kanji1List= sqLiteDataController.getFavouriteK();
            Log.d("hiendt","favorite " +kanji1List.size());
            TuVungYeuThich1_adapter kanji_adapter=new TuVungYeuThich1_adapter
                    (TuvungyeuthichActivity.this,R.layout.item_listview_kanji1,kanji1List);

            lv_TuVungYeuThich.setAdapter(kanji_adapter);
        }



    }

    private void addControls() {
        lv_TuVungYeuThich=findViewById(R.id.lv_TuVungYeuThich);
    }
}
