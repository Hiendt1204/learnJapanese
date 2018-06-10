package com.example.duongthuhien.kltn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.List;

public class GhepTuActivity extends AppCompatActivity {
    int lession_Id;
    List<Kanji1> kanji1List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghep_tu);

        Intent intent = getIntent();
        lession_Id = intent.getIntExtra("LESSION_ID", -1);

        SQLiteDataController sqLiteDataController=new SQLiteDataController(GhepTuActivity.this);
        sqLiteDataController.open();
        kanji1List=sqLiteDataController.getbylessionID(lession_Id);
    }
}
