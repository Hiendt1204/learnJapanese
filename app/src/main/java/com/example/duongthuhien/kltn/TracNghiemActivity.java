package com.example.duongthuhien.kltn;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBar;
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
import com.example.duongthuhien.kltn.hiragana.KetQuaActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TracNghiemActivity extends AppCompatActivity implements View.OnClickListener {
    int lession_Id;
    List<Kanji1> kanji1List;
    TextView tvWordSelected;
    TextView mtvScore;
    int btnPos;

    int[] mIdUsed = {-1, -1, -1, -1};
    ArrayList<Integer> midAnswerSelected =new ArrayList<>();
    Button[] btnAnswer = new Button[4];

    ArrayList<AnswerList> answerLists=new ArrayList<>();
    private Handler mHandler = new Handler();

    int mposListview = -1;
    int mAnswerCount = 0;
    int mCorrectId = -1;
    //số điểm cộng vào tổng điểm khi chọn đúng đáp án
    private static final int SCORE_MATCH = 1;
    //số điểm trừ khi chọn đáp án sai
    private static final int SCORE_WRONG = 1;
    //time delay để chuyển câu
    private static final long DELAY_TIME = 1000;

    // arraylist để lưu những từ đã chọn
    private ArrayList<String> mListWordSelected = new ArrayList<>();

    //biến lưu tổng điểm
    private int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trac_nghiem);

        addControls();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        lession_Id = intent.getIntExtra("LESSION_ID", -1);

        SQLiteDataController sqLiteDataController=new SQLiteDataController(TracNghiemActivity.this);
        sqLiteDataController.open();
        kanji1List=sqLiteDataController.getbylessionID(lession_Id);
        Log.d("hiendt1","" + kanji1List.size());

        fillData(lession_Id);
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
    private void fillData(int lession_Id) {
        if (mAnswerCount >= kanji1List.size()) {
            Intent intent = new Intent(TracNghiemActivity.this, KetQuaActivity.class);
            intent.putExtra("answerLists",answerLists );
            startActivity(intent);
            return;
        }
        // ham random Random rd=new Random();
        // x=rd.nextInt((Max-Min+1)+Min);

        Random rd = new Random();
        rd.setSeed(System.currentTimeMillis());
        int wordPos = -1;
        int wordId = -1;
        do {
            wordPos = rd.nextInt(kanji1List.size());
            Log.d("hiendt1","" + wordPos);
            wordId = kanji1List.get(wordPos).getId();

        } while (isIdAnswerSelected(wordId));


        // midAnswerSelected[mAnswerCount] = wordId;
        midAnswerSelected.add(wordId);
        Log.d("hiendt1","count" + mAnswerCount);
        mAnswerCount++;
        tvWordSelected.setText(kanji1List.get(wordPos).getStr_JWord_K());
        tvWordSelected.setTag(kanji1List.get(wordPos).getId());
        mCorrectId = wordPos;

        Random rd1 = new Random();
        btnPos = rd1.nextInt((3 - 0 + 1) + 0);
        btnAnswer[btnPos].setTag(kanji1List.get(wordPos).getId());
        btnAnswer[btnPos].setText(kanji1List.get(wordPos).getStr_VWord_K());
        mIdUsed[0] = kanji1List.get(wordPos).getId();
        int pos = 1;
        for (int i = 0; i <= 3; i++) {
            int wordPos1 = 0;
            if (i != btnPos) {

                wordPos1 = rd.nextInt(9);
                if (!isUsed(wordPos1)) {
                    btnAnswer[i].setText(kanji1List.get(wordPos1).getStr_VWord_K());
                    btnAnswer[i].setTag(kanji1List.get(wordPos1).getId());
                    mIdUsed[pos] = kanji1List.get(wordPos1).getId();

                    pos++;
                } else {
                    i--;
                }

            }
        }


    }


    private void addControls() {

        mtvScore=findViewById(R.id.mtvScore);
        tvWordSelected=findViewById(R.id.tvTudechon);
        btnAnswer[0]=findViewById(R.id.btnAnswer1);
        btnAnswer[1]=findViewById(R.id.btnAnswer2);
        btnAnswer[2]=findViewById(R.id.btnAnswer3);
        btnAnswer[3]=findViewById(R.id.btnAnswer4);

        btnAnswer[0].setOnClickListener(this);
        btnAnswer[1].setOnClickListener(this);
        btnAnswer[2].setOnClickListener(this);
        btnAnswer[3].setOnClickListener(this);

    }

    private boolean isIdAnswerSelected(int id) {
        for (int i = 0; i < midAnswerSelected.size(); i++) {
            if (midAnswerSelected.get(i)== id)
                return true;
        }

        return false;
    }

    private boolean isUsed(int wordPos1) {
        for (int i = 0; i <= 3; i++) {
            if (mIdUsed[i] == wordPos1)
                return true;
        }

        return false;
    }

    @Override
    public void onClick(final View view) {
        AnswerList answerList =new AnswerList();

        int id = (int) view.getTag();

        if (checkAnswer(id)) {
            mScore = mScore + SCORE_MATCH;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundColor(Color.parseColor("#edb1ab"));
                }
            }, 500);
            view.setBackgroundColor(Color.parseColor("#EE2C2C"));


        } else if (!checkAnswer(id)) {
            answerList.setWrongAnswer(kanji1List.get(id).getStr_VWord_K());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundColor(Color.parseColor("#edb1ab"));
                   // btnAnswer[btnPos].setBackgroundColor(Color.parseColor("#edb1ab"));
                    Log.d("Hiendt","pos  " );
                }
            }, 500);
            view.setBackgroundColor(Color.parseColor("#87CEFA"));
            //btnAnswer[btnPos].setBackgroundColor(Color.parseColor("#EE2C2C"));
        }


        mtvScore.setText(String.valueOf(mScore));

        answerList.setQuestion(kanji1List.get(mCorrectId).getStr_JWord_K());
        answerList.setCorrectAnswer(kanji1List.get(mCorrectId).getStr_VWord_K());

        answerLists.add(answerList);


        fillData(lession_Id);

    }
    private void hidePopup(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000);
    }
    private boolean checkAnswer(int id) {
        if (id == (Integer) tvWordSelected.getTag())

            return true;
        else
            return false;
    }
}
