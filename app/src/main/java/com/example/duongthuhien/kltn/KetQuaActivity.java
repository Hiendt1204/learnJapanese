package com.example.duongthuhien.kltn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.duongthuhien.kltn.Adapter.AnswerList_adapter;
import com.example.duongthuhien.kltn.Model.AnswerList;

import java.util.ArrayList;

public class KetQuaActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int HOCLAI = 1;
    public static final int DANHSACHBAIHOC = 2;
    Button btn_LessonList;
    Button btn_StudyAgain;
    ListView lv_AnswerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);
        Intent intent = getIntent();
        ArrayList<AnswerList> answerLists = (ArrayList<AnswerList>)intent.getSerializableExtra("answerLists");

        addControls();
        AnswerList_adapter answerList_adapter=new AnswerList_adapter
                (KetQuaActivity.this,R.layout.item_answerlist, answerLists);
        lv_AnswerList.setAdapter(answerList_adapter);
    }

    private void addControls() {
        btn_LessonList=findViewById(R.id.btn_LessonList);
        btn_StudyAgain=findViewById(R.id.btn_StudyAgain);
        lv_AnswerList=findViewById(R.id.lv_AnswerList);

        btn_StudyAgain.setOnClickListener(this);
        btn_LessonList.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
