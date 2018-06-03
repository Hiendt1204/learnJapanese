package com.example.duongthuhien.kltn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.duongthuhien.kltn.hiragana.HiraganaActivity;
import com.example.duongthuhien.kltn.katakana.KatakanaActivity;

import GTCB.GTCBActivity;
import GTCB2.GTCB2Activity;
import kanji.KanjiActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button Btn_Katakana;
    Button Btn_GTCB;
    Button Btn_GTCB2;
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
        Btn_GTCB=findViewById(R.id.Btn_GTCB);
        Btn_GTCB2=findViewById(R.id.Btn_GTCB2);
        Btn_Hiragana=findViewById(R.id.Btn_Hiragana);
        Btn_Kanji=findViewById(R.id.Btn_Kanji);
        Btn_Katakana=findViewById(R.id.Btn_Katakana);
        
        Btn_GTCB.setOnClickListener(this);
        Btn_Hiragana.setOnClickListener(this);
        Btn_Kanji.setOnClickListener(this);
        Btn_Katakana.setOnClickListener(this);
        Btn_GTCB2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Btn_GTCB:
                doClickBtn_GTCB();
                break;
            case R.id.Btn_Hiragana:
                doClickBtn_Hiragana();
                break;
            case R.id.Btn_Kanji:
                doClickBtn_Kanji();
                break;
            case R.id.Btn_Katakana:
                doClickBtn_Katakana();
                break;
            case R.id.Btn_GTCB2:
                doClickBtn_GTCB2();
                break;
        }

    }


    private void doClickBtn_Katakana() {
        Intent intent=new Intent(MainActivity.this,HiraganaActivity.class);
        intent.putExtra("btn_Katakana",1);
        startActivity(intent);
    }

    private void doClickBtn_Kanji() {
        Intent intent=new Intent(MainActivity.this, KanjiActivity.class);
        startActivity(intent);
    }

    private void doClickBtn_Hiragana() {
        Intent intent=new Intent(MainActivity.this,HiraganaActivity.class);
        intent.putExtra("btn_Hiragana",2);
        startActivity(intent);
    }

    private void doClickBtn_GTCB() {
        Intent intent=new Intent(MainActivity.this, GTCBActivity.class);
        startActivity(intent);
    }
    private void doClickBtn_GTCB2() {
        Intent intent=new Intent(MainActivity.this, GTCB2Activity.class);
        startActivity(intent);
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
