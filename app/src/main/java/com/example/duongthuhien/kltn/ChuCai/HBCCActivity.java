package com.example.duongthuhien.kltn.ChuCai;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.AnswerList;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.Model.Word;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Duong Thu Hien on 5/14/2018.
 */

public class HBCCActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mtvScore;
    TextView tvWordSelected;
    int trangthaibtn;
    int btnPos;

    private Handler mHandler = new Handler();
    ArrayList<AnswerList> answerLists=new ArrayList<>();
    ArrayList<Word> mlistWord = new ArrayList<Word>();
    int[] mIdUsed = {-1, -1, -1, -1};
    int[] midAnswerSelected = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    Button[] btnAnswer = new Button[4];

    int mposListview = -1;
    int mAnswerCount = 0;
    int mCorrectId = -1;
    //số điểm cộng vào tổng điểm khi chọn đúng đáp án
    private static final int SCORE_MATCH = 1;
    //time delay để chuyển câu
    private static final long DELAY_TIME = 1000;

    // arraylist để lưu những từ đã chọn
    private ArrayList<String> mListWordSelected = new ArrayList<>();

    //biến lưu tổng điểm
    private int mScore = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hocbangchucai);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        mposListview = intent.getIntExtra("positionListview", -1);
        trangthaibtn=intent.getIntExtra("A",-1);

        getWordList(trangthaibtn);
        addControl();
        fillData(mposListview);


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // requestCode la de phan biet la ket qua nay duoc tra ve tu thang AC nao
        //resultCode la ma ket qua tra ve
        //data la du lieu duoc tra ve
        if (resultCode==RESULT_OK){
            if (data.getIntExtra("HocLai",-1)==1){
                mAnswerCount=0;
                mListWordSelected.clear();
                mScore=0;
                mtvScore.setText(String.valueOf(mScore));
                fillData(mposListview);
            }else if (data.getIntExtra("HocLai",-1)==2){
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void fillData(int posListview) {
        if (mAnswerCount >= 9) {
            Intent intent = new Intent(HBCCActivity.this, KetQuaHActivity.class);
            intent.putExtra("answerLists", answerLists);
            intent.putExtra("trangthaibtn",trangthaibtn);
            startActivityForResult(intent,0);
            return;
        }

        Random rd = new Random();
        rd.setSeed(System.currentTimeMillis());
        int wordPos = -1;
        int wordId = -1;
        do {
            wordPos = rd.nextInt(10) + posListview*10;
            Log.d("HBCCActivity", "mlistWord" +mlistWord );
            wordId = mlistWord.get(wordPos).getId();

        } while (isIdAnswerSelected(wordId));
        Log.d("HBCCActivity", "wordPos " + wordPos + " posListView "  + posListview);
        midAnswerSelected[mAnswerCount] = wordId;
        mAnswerCount++;
        tvWordSelected.setText(mlistWord.get(wordPos).getJword());
        tvWordSelected.setTag(mlistWord.get(wordPos).getId());
        mCorrectId = wordPos;

        Random rd1 = new Random();
         btnPos = rd1.nextInt((3 - 0 + 1) + 0);
        btnAnswer[btnPos].setTag(mlistWord.get(wordPos).getId());
        btnAnswer[btnPos].setText(mlistWord.get(wordPos).getVword());
        mIdUsed[0] = mlistWord.get(wordPos).getId();
        int pos = 1;
        for (int i = 0; i <= 3; i++) {
            int wordPos1 = 0;
            if (i != btnPos) {

                wordPos1 = rd.nextInt(9);
                if (!isUsed(wordPos1)) {
                    btnAnswer[i].setText(mlistWord.get(wordPos1).getVword());
                    btnAnswer[i].setTag(mlistWord.get(wordPos1).getId());
                    mIdUsed[pos] = mlistWord.get(wordPos1).getId();

                    pos++;
                } else {
                    i--;
                }

            }
        }


    }

    private boolean isIdAnswerSelected(int id) {
        for (int i = 0; i <= 9; i++) {
            if (midAnswerSelected[i] == id)
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

    private void addControl() {
        mtvScore = findViewById(R.id.mtvScore);
        tvWordSelected = findViewById(R.id.tvTudechon);
        btnAnswer[0] = findViewById(R.id.btnAnswer1);
        btnAnswer[1] = findViewById(R.id.btnAnswer2);
        btnAnswer[2] = findViewById(R.id.btnAnswer3);
        btnAnswer[3] = findViewById(R.id.btnAnswer4);

        btnAnswer[0].setOnClickListener(this);
        btnAnswer[1].setOnClickListener(this);
        btnAnswer[2].setOnClickListener(this);
        btnAnswer[3].setOnClickListener(this);

    }
    public ArrayList getWordList(int trangthaibtn) {

        if (trangthaibtn==2){
            String[] Tiengviet = getResources().getStringArray(R.array.roomaji);
            String[] hiragana = getResources().getStringArray(R.array.hiragana);
            for (int i = 0; i < Tiengviet.length; i++) {
                Word word = new Word();
                word.setId(i);
                word.setJword(hiragana[i]);
                word.setVword(Tiengviet[i]);
                mlistWord.add(word);
            }
        }
        else if (trangthaibtn==1){
            String[] Tiengviet = getResources().getStringArray(R.array.roomaji_k);
            String[] hiragana = getResources().getStringArray(R.array.katakana);
            for (int i = 0; i < Tiengviet.length; i++) {
                Word word = new Word();
                word.setId(i);
                word.setJword(hiragana[i]);
                word.setVword(Tiengviet[i]);
                mlistWord.add(word);
            }

        }
        return mlistWord;
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
                    fillData(mposListview);
                }
            }, 500);
            view.setBackgroundColor(Color.parseColor("#EE2C2C"));


        } else if (!checkAnswer(id)) {
            answerList.setWrongAnswer(mlistWord.get(id).getJword());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundColor(Color.parseColor("#edb1ab"));
                     btnAnswer[btnPos].setBackgroundColor(Color.parseColor("#edb1ab"));
                    fillData(mposListview);
                }
            }, 500);
            view.setBackgroundColor(Color.parseColor("#87CEFA"));
            btnAnswer[btnPos].setBackgroundColor(Color.parseColor("#EE2C2C"));

        }
        mtvScore.setText(String.valueOf(mScore));

            answerList.setQuestion(mlistWord.get(mCorrectId).getJword());
            answerList.setCorrectAnswer(mlistWord.get(mCorrectId).getVword());

            answerLists.add(answerList);
    }
    private void hidePopup(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
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

