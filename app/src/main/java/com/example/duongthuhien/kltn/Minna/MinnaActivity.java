package com.example.duongthuhien.kltn.Minna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.duongthuhien.kltn.R;

public class MinnaActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_BaiHocM;
    Button btn_TuMoiM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minna);
        addControls();
    }

    private void addControls() {
        btn_BaiHocM=findViewById(R.id.btn_BaihocM);
        btn_TuMoiM=findViewById(R.id.btn_TuMoiM);

        btn_TuMoiM.setOnClickListener(this);
        btn_BaiHocM.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         switch(view.getId()) {
             case R.id.btn_BaihocM:
                 break;
             case R.id.btn_TuMoiM:
                 break;

        }
    }
}
