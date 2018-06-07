package com.example.duongthuhien.kltn.hiragana;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Model.AnswerList;
import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.Word;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Duong Thu Hien on 5/14/2018.
 */

public class HBCCHiraganaActivity extends Activity implements View.OnClickListener {
    TextView mtvScore;
    TextView tvWordSelected;
    LinearLayout llAnswer;
    ImageView imvIconAnswer;
    TextView tvAnser;
    int trangthaibtn;

    ArrayList<AnswerList> answerLists=new ArrayList<>();
    ArrayList<Word> mlistWord = new ArrayList<Word>();
    int[] mIdUsed = {-1, -1, -1, -1};
    int[] midAnswerSelected = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    Button[] btnAnswer = new Button[4];

    int mposListview = -1;
    int mAnswerCount = 0;
    int mCorrectId = -1;
    //số điểm cộng vào tổng điểm khi chọn đúng đáp án
    private static final int SCORE_MATCH = 10;
    //số điểm trừ khi chọn đáp án sai
    private static final int SCORE_WRONG = 1;
    //time delay để chuyển câu
    private static final long DELAY_TIME = 1000;

    // arraylist để lưu những từ đã chọn
    private ArrayList<String> mListWordSelected = new ArrayList<>();

    //biến lưu tổng điểm
    private int mScore = 0;

    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hbcchiragana);

        Intent intent = getIntent();
        mposListview = intent.getIntExtra("positionListview", -1);
        trangthaibtn=intent.getIntExtra("A",-1);

//        Toast.makeText(this,"da an vi tri" +positionListview,
//                Toast.LENGTH_SHORT).show();

        getWordList(trangthaibtn);
        addControl();
        fillData(mposListview);


    }


    private void fillData(int posListview) {
        if (mAnswerCount > 9) {
            Intent intent = new Intent(HBCCHiraganaActivity.this, ResultWordActivity.class);
            intent.putExtra("answerLists", answerLists);
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
            wordPos = rd.nextInt(10) + posListview*10;
            Log.d("HBCCHiraganaActivity", "mlistWord" +mlistWord );
            wordId = mlistWord.get(wordPos).getId();

        } while (isIdAnswerSelected(wordId));
        Log.d("HBCCHiraganaActivity", "wordPos " + wordPos + " posListView "  + posListview);
        midAnswerSelected[mAnswerCount] = wordId;
        mAnswerCount++;
        tvWordSelected.setText(mlistWord.get(wordPos).getJword());
        tvWordSelected.setTag(mlistWord.get(wordPos).getId());
        mCorrectId = wordPos;

        Random rd1 = new Random();
        int btnPos = rd1.nextInt((3 - 0 + 1) + 0);
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
        llAnswer = findViewById(R.id.llDapAn);
        imvIconAnswer = findViewById(R.id.ImvIconAnswer);
        tvAnser = findViewById(R.id.tvAnser);
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
    public void onClick(View view) {
        AnswerList answerList =new AnswerList();

        int id = (int) view.getTag();
        if (checkAnswer(id)) {
            llAnswer.setVisibility(View.VISIBLE);
            tvAnser.setText("Bạn đã chọn đúng");
            imvIconAnswer.setImageResource(R.drawable.ic_tag_faces_black_24dp);
            mScore = mScore + SCORE_MATCH;

        } else if (!checkAnswer(id)) {
            llAnswer.setVisibility(View.VISIBLE);
            tvAnser.setText("Bạn đã chọn sai");
            imvIconAnswer.setImageResource(R.drawable.ic_mood_bad_black_24dp);
            mScore = mScore - SCORE_WRONG;
            answerList.setWrongAnswer(mlistWord.get(id).getVword());

        }
        mtvScore.setText(String.valueOf(mScore));

            answerList.setQuestion(mlistWord.get(mCorrectId).getJword());
            answerList.setCorrectAnswer(mlistWord.get(mCorrectId).getVword());

            answerLists.add(answerList);

        hidePopup();
        fillData(mposListview);
    }
    private void hidePopup(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                llAnswer.setVisibility(View.INVISIBLE);
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

