package com.example.duongthuhien.kltn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duongthuhien.kltn.ChuCai.ChuCaiActivity;
import com.example.duongthuhien.kltn.MCCB.MCCBActivity;
import com.example.duongthuhien.kltn.Minna.MinnaActivity;
import com.example.duongthuhien.kltn.hiragana.HiraganaActivity;

import com.example.duongthuhien.kltn.Tuvungyeuthich.TuvungyeuthichActivity;
import com.example.duongthuhien.kltn.kanji.KanjiActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button Btn_Katakana;
    Button Btn_Minna;
    Button Btn_Tuvungyeuthich;
    Button Btn_MCCB;
    Button Btn_Hiragana;
    Button Btn_Kanji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvents();

    }

    private void addEvents() {
    }

    private void addControl() {
        Btn_Minna=findViewById(R.id.Btn_Minna);
        Btn_Tuvungyeuthich=findViewById(R.id.Btn_Tuvungyeuthich);
        Btn_Hiragana=findViewById(R.id.btn_Hiragana);
        Btn_Kanji=findViewById(R.id.Btn_Kanji);
        Btn_Katakana=findViewById(R.id.Btn_Katakana);
        Btn_MCCB=findViewById(R.id.Btn_MCCB);
        
        Btn_Minna.setOnClickListener(this);
        Btn_Hiragana.setOnClickListener(this);
        Btn_Kanji.setOnClickListener(this);
        Btn_Katakana.setOnClickListener(this);
        Btn_Tuvungyeuthich.setOnClickListener(this);
        Btn_MCCB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Btn_MCCB:
                doClickBtn_MCCB();
                break;
            case R.id.btn_Hiragana:
                doClickBtn_Hiragana();
                break;
            case R.id.Btn_Kanji:
                doClickBtn_Kanji();
                break;
            case R.id.Btn_Katakana:
                doClickBtn_Katakana();
                break;
            case R.id.Btn_Tuvungyeuthich:
                doClickBtn_Tuvungyeuthich();
                break;
            case R.id.Btn_Minna:
                doClickBtn_Minna();
                break;
        }

    }


    private void doClickBtn_Hiragana() {
        Intent intent=new Intent(MainActivity.this,ChuCaiActivity.class);
        intent.putExtra("A",2);
        startActivity(intent);
    }

    private void doClickBtn_Katakana() {
        Intent intent=new Intent(MainActivity.this,HiraganaActivity.class);
        intent.putExtra("A",1);
        startActivity(intent);
    }

    private void doClickBtn_Kanji() {
        Intent intent=new Intent(MainActivity.this, KanjiActivity.class);
       // intent.putExtra("trangthaibtn",3);
        startActivity(intent);
    }

    private void doClickBtn_MCCB() {
        Intent intent=new Intent(MainActivity.this, MCCBActivity.class);
        startActivity(intent);
    }
    private void doClickBtn_Minna() {
        Intent intent=new Intent(MainActivity.this, MinnaActivity.class);
        startActivity(intent);
    }
    private void doClickBtn_Tuvungyeuthich() {
        Intent intent=new Intent(MainActivity.this, TuvungyeuthichActivity.class);
        intent.putExtra("posF",0);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
