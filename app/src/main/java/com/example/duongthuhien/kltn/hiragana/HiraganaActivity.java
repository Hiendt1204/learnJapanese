package com.example.duongthuhien.kltn.hiragana;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.duongthuhien.kltn.Adapter.BaiHocH_adapter;
import com.example.duongthuhien.kltn.Model.BaiHocH;
import com.example.duongthuhien.kltn.R;

import java.util.ArrayList;

import javax.crypto.spec.RC5ParameterSpec;

public class HiraganaActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lv_BaihocHiragana;
    ArrayList<BaiHocH> dsBaiHoc;
    BaiHocH_adapter baiHocH_adapter;
    LinearLayout ll_BangChuCai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiragana);

        addControl();


    }

    private void addControl() {
        ll_BangChuCai = findViewById(R.id.ll_BangChuCai);
        lv_BaihocHiragana = findViewById(R.id.lv_BaihocHiragana);
        dsBaiHoc = new ArrayList<>();
        String[] stringArray = getResources().getStringArray(R.array.arrBaihoc_Hiragana);

        for (int i = 0; i <= 7; i++) {

            dsBaiHoc.add(new BaiHocH(stringArray[i]));
        }

        baiHocH_adapter = new BaiHocH_adapter(HiraganaActivity.this, R.layout.layout_listview, dsBaiHoc);

        lv_BaihocHiragana.setAdapter(baiHocH_adapter);

        ll_BangChuCai.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(HiraganaActivity.this, BCCHiraganaActivity.class);
        startActivity(intent);

    }

}
