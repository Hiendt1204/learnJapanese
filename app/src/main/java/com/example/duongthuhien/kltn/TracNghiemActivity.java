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

public class TracNghiemActivity extends AppCompatActivity implements View.OnClickListener {
    int lession_Id=-1;
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
        if (midAnswerSelected.size() >= kanji1List.size()) {
            isBlockClicked = true;
            Intent intent = new Intent(TracNghiemActivity.this, KetQuaActivity.class);
            intent.putExtra("answerLists",answerLists );
            startActivityForResult(intent,1);
            return;
        }
        isBlockClicked = false;

        Random rd = new Random();
        rd.setSeed(System.currentTimeMillis());
        int wordPos = -1;
        int wordId = -1;
        do {
            wordPos = rd.nextInt(kanji1List.size());
            wordId = kanji1List.get(wordPos).getId();

        } while (isIdAnswerSelected(wordId));


        // midAnswerSelected[mAnswerCount] = wordId;
        midAnswerSelected.add(wordId);
        tvWordSelected.setText(kanji1List.get(wordPos).getStr_JWord_K());
        tvWordSelected.setTag(wordPos);
        mCorrectId = wordPos;

        Random rd1 = new Random();
        btnPos = rd1.nextInt((3 - 0 + 1) + 0);
        btnAnswer[btnPos].setTag(wordPos);
        String vWordCorrect = kanji1List.get(wordPos).getStr_VWord_K();
        btnAnswer[btnPos].setText(vWordCorrect.replaceAll(",", ", "));
        mIdUsed[0] = wordPos;
        int pos = 1;
        for (int i = 0; i <= 3; i++) {
            int wordPos1 = 0;
            if (i != btnPos) {

                wordPos1 = rd.nextInt(9);
                if (!isUsed(wordPos1)) {
                    String vWord = kanji1List.get(wordPos1).getStr_VWord_K();
                    btnAnswer[i].setText(vWord.replaceAll(",", ", "));
                    btnAnswer[i].setTag(wordPos1);
                    mIdUsed[pos] = wordPos1;

                    pos++;
                } else {
                    i--;
                }

            }
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // requestCode la de phan biet la ket qua nay duoc tra ve tu thang AC nao
        //resultCode la ma ket qua tra ve
        //data la du lieu duoc tra ve
        if (resultCode==RESULT_OK){
            if (data.getIntExtra("HocLai",-1)==1){
                midAnswerSelected.clear();
                mListWordSelected.clear();
                mScore=0;
                mtvScore.setText(String.valueOf(mScore));
                fillData(lession_Id);
            }else if (data.getIntExtra("HocLai",-1)==2){
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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

    boolean isBlockClicked = false;
    @Override
    public void onClick(final View view) {
        if (isBlockClicked) {
            return;
        }
        isBlockClicked = true;
        AnswerList answerList =new AnswerList();

        int id = (int) view.getTag();

        if (checkAnswer(id)) {
            mScore = mScore + SCORE_MATCH;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundColor(Color.parseColor("#edb1ab"));
                    isBlockClicked = false;
                    fillData(lession_Id);
                }
            }, 500);
            view.setBackgroundColor(Color.parseColor("#EE2C2C"));


        } else if (!checkAnswer(id)) {
            answerList.setWrongAnswer(kanji1List.get(id).getStr_VWord_K());
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundColor(Color.parseColor("#edb1ab"));
                    btnAnswer[btnPos].setBackgroundColor(Color.parseColor("#edb1ab"));
                    Log.d("hiendt","btnpos  "+btnPos);
                    fillData(lession_Id);
                    isBlockClicked = false;
                }
            }, 500);
            view.setBackgroundColor(Color.parseColor("#87CEFA"));
            btnAnswer[btnPos].setBackgroundColor(Color.parseColor("#EE2C2C"));
            Log.d("hiendt","btnpos  "+btnPos);
        }


        mtvScore.setText(String.valueOf(mScore));

        answerList.setQuestion(kanji1List.get(mCorrectId).getStr_JWord_K());
        answerList.setCorrectAnswer(kanji1List.get(mCorrectId).getStr_VWord_K());

        answerLists.add(answerList);


    }

    private boolean checkAnswer(int id) {
        if (id == (Integer) tvWordSelected.getTag())

            return true;
        else
            return false;
    }
}
