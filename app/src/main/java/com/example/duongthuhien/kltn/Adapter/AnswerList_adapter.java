package com.example.duongthuhien.kltn.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.duongthuhien.kltn.R;
import com.example.duongthuhien.kltn.Model.AnswerList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong Thu Hien on 5/29/2018.
 */

public class AnswerList_adapter extends ArrayAdapter {
    TextView  tv_Question;
    TextView tv_WrongAnswer;
    TextView tv_CorrectAnswer;


    ArrayList<AnswerList> answerLists=new ArrayList<>();
    //Màn hình sử dụng giao diện này
    Activity context;
    //Layout cho từng dòng muốn  hiển thị
    int resource;
    //Danh sách nguồn dữ liệu muốn hiển thị lên giao diện
    List objects;

    public AnswerList_adapter(@NonNull Activity context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);

        tv_Question=row.findViewById(R.id.tv_Question);
        tv_WrongAnswer=row.findViewById(R.id.tv_WrongAnswer);
        tv_CorrectAnswer=row.findViewById(R.id.tv_CorrectAnswer);

        final AnswerList answerList=(AnswerList) this.objects.get(position);
        this.objects = (ArrayList<AnswerList>)objects;
        for (int i=0;i<this.objects.size();i++){
            tv_Question.setText(answerList.getQuestion());
            tv_WrongAnswer.setText(answerList.getWrongAnswer());
            tv_CorrectAnswer.setText(answerList.getCorrectAnswer());
        }
        if (position%2==0)
            row.setBackgroundColor(Color.parseColor("#ffffff"));
        else
            row.setBackgroundColor(Color.parseColor("#ffcccc"));

        return row;
    }
}
