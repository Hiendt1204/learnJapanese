package com.example.duongthuhien.kltn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.duongthuhien.kltn.MCCB.MCCBActivity;
import com.example.duongthuhien.kltn.TNSC.TNSCActivity;
import com.example.duongthuhien.kltn.hiragana.HiraganaActivity;

import com.example.duongthuhien.kltn.TNTC.TNTCActivity;
import com.example.duongthuhien.kltn.kanji.KanjiActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button Btn_Katakana;
    Button Btn_TNSC;
    Button Btn_TNTC;
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
        Btn_TNSC=findViewById(R.id.Btn_TNSC);
        Btn_TNTC=findViewById(R.id.Btn_TNTC);
        Btn_Hiragana=findViewById(R.id.Btn_Hiragana);
        Btn_Kanji=findViewById(R.id.Btn_Kanji);
        Btn_Katakana=findViewById(R.id.Btn_Katakana);
        Btn_MCCB=findViewById(R.id.Btn_MCCB);
        
        Btn_TNSC.setOnClickListener(this);
        Btn_Hiragana.setOnClickListener(this);
        Btn_Kanji.setOnClickListener(this);
        Btn_Katakana.setOnClickListener(this);
        Btn_TNTC.setOnClickListener(this);
        Btn_MCCB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Btn_TNSC:
                doClickBtn_TNSC();
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
            case R.id.Btn_TNTC:
                doClickBtn_TNTC();
                break;
            case R.id.Btn_MCCB:
                doClickBtn_MCCB();
                break;
        }

    }


    private void doClickBtn_Hiragana() {
        Intent intent=new Intent(MainActivity.this,HiraganaActivity.class);
        intent.putExtra("btn_Hiragana",2);
        startActivity(intent);
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

    private void doClickBtn_MCCB() {
        Intent intent=new Intent(MainActivity.this, MCCBActivity.class);
        startActivity(intent);
    }
    private void doClickBtn_TNSC() {
        Intent intent=new Intent(MainActivity.this, TNSCActivity.class);
        startActivity(intent);
    }
    private void doClickBtn_TNTC() {
        Intent intent=new Intent(MainActivity.this, TNTCActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
