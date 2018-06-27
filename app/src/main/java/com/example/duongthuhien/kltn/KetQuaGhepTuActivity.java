package com.example.duongthuhien.kltn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KetQuaGhepTuActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_LessonList;
    Button btn_StudyAgain;
    TextView tv_mScore;
    int mScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua_gheptu);

        addControls();
        Intent intent=getIntent();
        mScore=intent.getIntExtra("mScore",-1);
        Log.d("hiendt","mScoreKetQua    "+mScore);
        tv_mScore.setText(""+ mScore);

    }

    private void addControls() {
        tv_mScore=findViewById(R.id.tv_mScore);
        btn_LessonList=findViewById(R.id.btn_LessonList);
        btn_StudyAgain=findViewById(R.id.btn_StudyAgain);

        btn_StudyAgain.setOnClickListener(this);
        btn_LessonList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_LessonList:
                Intent intent = new Intent();
                intent.putExtra("HocLai",2);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.btn_StudyAgain:
                Intent intent1 = new Intent();
                intent1.putExtra("HocLai",1);
                setResult(RESULT_OK,intent1);
                finish();
                break;
        }

    }

}
