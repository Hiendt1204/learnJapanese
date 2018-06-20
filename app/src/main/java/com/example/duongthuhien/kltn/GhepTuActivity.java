package com.example.duongthuhien.kltn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GhepTuActivity extends AppCompatActivity implements View.OnClickListener {
    int lession_Id;
    List<Kanji1> kanji1List;
    Button[] Answer = new Button[12];
    int wordPos=-1;
    int btnpos=-1;
    int btnpos1=-1;

    ArrayList<Integer> midAnswerSelected =new ArrayList<>();
    ArrayList<Integer> mBtnSelected =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghep_tu);

        Intent intent = getIntent();
        lession_Id = intent.getIntExtra("LESSION_ID", -1);

        SQLiteDataController sqLiteDataController=new SQLiteDataController(GhepTuActivity.this);
        sqLiteDataController.open();
        kanji1List=sqLiteDataController.getbylessionID(lession_Id);

        addControls();
        fillData();
    }

    private void fillData() {
        Random rd = new Random();
        rd.setSeed(System.currentTimeMillis());
        for (int i=0;i<=5;i++){

            do {
                wordPos = rd.nextInt(kanji1List.size());
            } while (isIdAnswerSelected(wordPos));

            Log.d("btnpos","Wordpos " + wordPos);
            do {
                btnpos = rd.nextInt(12);
            }while (isBtnSelected(btnpos));

            Answer[btnpos].setText(kanji1List.get(wordPos).getStr_VWord_K());

            mBtnSelected.add(btnpos);
            Log.d("btnpos","btnpos " + btnpos);
            Log.d("btnpos","Vword " + kanji1List.get(wordPos).getStr_VWord_K());



            do {
                btnpos1 = rd.nextInt(12);
            }while (isBtnSelected(btnpos1));
            Answer[btnpos1].setText(kanji1List.get(wordPos).getStr_JWord_K());

            mBtnSelected.add(btnpos1);
            Log.d("btnpos1","btnpos1 " + btnpos1);
            Log.d("btnpos1","Jword " + kanji1List.get(wordPos).getStr_JWord_K());

        }
        midAnswerSelected.add(wordPos);


    }
    private boolean isIdAnswerSelected(int id) {
        for (int i = 0; i < midAnswerSelected.size(); i++) {
            if (wordPos==midAnswerSelected.get(i))
                return true;
        }

        return false;
    }
    private boolean isBtnSelected(int id) {
        for (int i = 0; i < mBtnSelected.size(); i++) {
            if (id==mBtnSelected.get(i))
                return true;
        }

        return false;
    }

    private void addControls() {

        Answer[0]=findViewById(R.id.btn_1);
        Answer[1]=findViewById(R.id.btn_2);
        Answer[2]=findViewById(R.id.btn_3);
        Answer[3]=findViewById(R.id.btn_4);
        Answer[4]=findViewById(R.id.btn_5);
        Answer[5]=findViewById(R.id.btn_6);
        Answer[6]=findViewById(R.id.btn_7);
        Answer[7]=findViewById(R.id.btn_8);
        Answer[8]=findViewById(R.id.btn_9);
        Answer[9]=findViewById(R.id.btn_10);
        Answer[10]=findViewById(R.id.btn_11);
        Answer[11]=findViewById(R.id.btn_12);


        Answer[0].setOnClickListener(this);
        Answer[1].setOnClickListener(this);
        Answer[2].setOnClickListener(this);
        Answer[3].setOnClickListener(this);
        Answer[4].setOnClickListener(this);
        Answer[5].setOnClickListener(this);
        Answer[6].setOnClickListener(this);
        Answer[7].setOnClickListener(this);
        Answer[8].setOnClickListener(this);
        Answer[9].setOnClickListener(this);
        Answer[10].setOnClickListener(this);
        Answer[11].setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

    }
}
