package com.example.duongthuhien.kltn.Tuvungyeuthich;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.Model.ThamKhao_frag;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;
import com.example.duongthuhien.kltn.kanji.KanjiActivity;

import java.util.ArrayList;

public class TuvungyeuthichActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuvungyeuthich);

       ArrayList<ThamKhao_frag> kanji1List=new ArrayList<ThamKhao_frag>();
        SQLiteDataController sqLiteDataController=new SQLiteDataController(TuvungyeuthichActivity.this);
        sqLiteDataController.open();
        SQLiteDatabase database=sqLiteDataController.getMyDatabase();

        kanji1List= sqLiteDataController.getFavourite();
        Log.d("hiendt","favorite " +kanji1List.size());
    }
}
