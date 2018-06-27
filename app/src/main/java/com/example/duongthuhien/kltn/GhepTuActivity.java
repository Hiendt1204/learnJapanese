package com.example.duongthuhien.kltn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.AnswerList;
import com.example.duongthuhien.kltn.Model.Kanji1;
import com.example.duongthuhien.kltn.SQLiteData.SQLiteDataController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GhepTuActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_mScore;
    int mScore=0;
    int lession_Id;
    List<Kanji1> kanji1List;
    Button[] Answer = new Button[12];
    int wordPos = -1;
    int btnpos = -1;
    int btnpos1 = -1;
    private int mCurrentIndex = -1;
    private int mCurrentButton=-1;
    private int mOpenedBtn = 0;

    private Handler mHandler = new Handler();
    ArrayList<Integer> midAnswerSelected = new ArrayList<>();
    ArrayList<Integer> mBtnSelected = new ArrayList<>();
    private boolean blockClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghep_tu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        lession_Id = intent.getIntExtra("LESSION_ID", -1);

        SQLiteDataController sqLiteDataController = new SQLiteDataController(GhepTuActivity.this);
        sqLiteDataController.open();
        kanji1List = sqLiteDataController.getbylessionID(lession_Id);

        addControls();
        fillData();
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
    private void fillData() {
        Random rd = new Random();
        rd.setSeed(System.currentTimeMillis());
        for (int i = 0; i <= 5; i++) {

            do {
                wordPos = rd.nextInt(kanji1List.size());
            } while (isIdAnswerSelected(wordPos));
            midAnswerSelected.add(wordPos);
            do {
                btnpos = rd.nextInt(12);
            } while (isBtnSelected(btnpos));

            Answer[btnpos].setText(kanji1List.get(wordPos).getStr_VWord_K());
            Answer[btnpos].setTag(kanji1List.get(wordPos).getId());
            Answer[btnpos].setBackgroundColor(getResources().getColor(R.color.Ghi));
            mBtnSelected.add(btnpos);

            do {
                btnpos1 = rd.nextInt(12);
            } while (isBtnSelected(btnpos1));
            Answer[btnpos1].setText(kanji1List.get(wordPos).getStr_JWord_K());
            Answer[btnpos1].setTag(kanji1List.get(wordPos).getId());
            Answer[btnpos1].setBackgroundColor(getResources().getColor(R.color.Ghi));
            mBtnSelected.add(btnpos1);
        }

    }

    private boolean isIdAnswerSelected(int id) {
        for (int i = 0; i < midAnswerSelected.size(); i++) {
            if (wordPos == midAnswerSelected.get(i))
                return true;
        }

        return false;
    }

    private boolean isBtnSelected(int id) {
        for (int i = 0; i < mBtnSelected.size(); i++) {
            if (id == mBtnSelected.get(i))
                return true;
        }

        return false;
    }

    private void addControls() {

        tv_mScore=findViewById(R.id.tv_mScore);
        Answer[0] = findViewById(R.id.btn_1);
        Answer[1] = findViewById(R.id.btn_2);
        Answer[2] = findViewById(R.id.btn_3);
        Answer[3] = findViewById(R.id.btn_4);
        Answer[4] = findViewById(R.id.btn_5);
        Answer[5] = findViewById(R.id.btn_6);
        Answer[6] = findViewById(R.id.btn_7);
        Answer[7] = findViewById(R.id.btn_8);
        Answer[8] = findViewById(R.id.btn_9);
        Answer[9] = findViewById(R.id.btn_10);
        Answer[10] = findViewById(R.id.btn_11);
        Answer[11] = findViewById(R.id.btn_12);


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
        switch (v.getId()) {
            case R.id.btn_1:
                processClickBtn(v, 0);
                break;
            case R.id.btn_2:
                processClickBtn(v, 1);
                break;
            case R.id.btn_3:
                processClickBtn(v, 2);
                break;
            case R.id.btn_4:
                processClickBtn(v, 3);
                break;
            case R.id.btn_5:
                processClickBtn(v, 4);
                break;
            case R.id.btn_6:
                processClickBtn(v, 5);
                break;
            case R.id.btn_7:
                processClickBtn(v, 6);
                break;
            case R.id.btn_8:
                processClickBtn(v, 7);
                break;
            case R.id.btn_9:
                processClickBtn(v, 8);
                break;
            case R.id.btn_10:
                processClickBtn(v, 9);
                break;
            case R.id.btn_11:
                processClickBtn(v, 10);
                break;
            case R.id.btn_12:
                processClickBtn(v, 11);
                break;
        }
    }

    private void processClickBtn(View v, final int index) {
        if (index == mCurrentButton && (int)v.getTag() == mCurrentIndex) {
            return;
        }
        if (blockClick) {
            return;
        }

        if (Answer[index].getText().length() == 0) {
            return;
        }

        if (mCurrentIndex==-1){
            int id = (int) Answer[index].getTag();
            mCurrentButton=index;
           mCurrentIndex= id;
           Answer[index].setBackgroundColor(Color.rgb(166, 252, 251));
        }else {
            Answer[index].setBackgroundColor(getResources().getColor(R.color.Ghi2));
            if (mCurrentIndex==(int)Answer[index].getTag()){
                Answer[index].setText("");
                Answer[mCurrentButton].setText("");
                mOpenedBtn=mOpenedBtn+2;
                mScore=mScore+10;

                Answer[index].setBackgroundColor(getResources().getColor(R.color.Ghi));
                Answer[(mCurrentButton)].setBackgroundColor(getResources().getColor(R.color.Ghi));
                if (mOpenedBtn>=12){
                    Intent intent = new Intent(GhepTuActivity.this, KetQuaGhepTuActivity.class);
                    intent.putExtra("mScore",mScore );
                    startActivityForResult(intent,1);
                    return;
                }
                Log.d("hiendt","mOpenBtn    "+mOpenedBtn);
            }
            else {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Answer[index].setBackgroundColor(getResources().getColor(R.color.Ghi));
                            Answer[(mCurrentButton)].setBackgroundColor(getResources().getColor(R.color.Ghi));
                            blockClick = false;
                        }
                    }, 500);
                Answer[index].setBackgroundColor(getResources().getColor(R.color.colorACCB));
                Answer[(mCurrentButton)].setBackgroundColor(getResources().getColor(R.color.colorACCB));
                mScore=mScore-1;
                blockClick = true;

            }
            mCurrentIndex=-1;
            tv_mScore.setText(""+mScore);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // requestCode la de phan biet la ket qua nay duoc tra ve tu thang AC nao
        //resultCode la ma ket qua tra ve
        //data la du lieu duoc tra ve
        if (resultCode==RESULT_OK){
            if (data.getIntExtra("HocLai",-1)==1){
                mOpenedBtn= 0;
                mCurrentIndex = -1;
                mCurrentButton = -1;
                midAnswerSelected.clear();
                mBtnSelected.clear();
                mScore=0;
                tv_mScore.setText(String.valueOf(mScore));
                fillData();
            }else if (data.getIntExtra("HocLai",-1)==2){
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}


