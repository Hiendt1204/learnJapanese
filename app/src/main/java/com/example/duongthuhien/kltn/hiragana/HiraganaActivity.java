package com.example.duongthuhien.kltn.hiragana;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Adapter.BaiHocH_adapter;
import com.example.duongthuhien.kltn.Model.BaiHocH;
import com.example.duongthuhien.kltn.R;

import java.util.ArrayList;

import javax.crypto.spec.RC5ParameterSpec;

public class HiraganaActivity extends AppCompatActivity implements View.OnClickListener {
    ListView lv_BaihocHiragana;
    ArrayList<BaiHocH> dsBaiHoc;
    BaiHocH_adapter baiHocH_adapter;
    int trangthaibtn;
    LinearLayout ll_BangChuCai;
    TextView tv_BCCV;
    TextView tv_BCCJ;
    ImageView img_BCC;
    ImageView img_BCCListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiragana);

        Intent intent=getIntent();
        trangthaibtn=intent.getIntExtra("A",-1);
        Log.d("hiendt","HiraganaActivity trangthaibtn " + trangthaibtn);

        addControl();


    }

    private void addControl() {
        img_BCC=findViewById(R.id.img_BCC);
        tv_BCCJ=findViewById(R.id.tv_BCCJ);
        tv_BCCV=findViewById(R.id.tv_BCCV);
        ll_BangChuCai = findViewById(R.id.ll_BangChuCai);
        lv_BaihocHiragana = findViewById(R.id.lv_BaihocHiragana);

        if (trangthaibtn==2){
            img_BCC.setImageResource(R.drawable.iconhiragana);
            tv_BCCV.setText("Hiragana");
            tv_BCCJ.setText("ひらがな");
            dsBaiHoc = new ArrayList<>();
            String[] stringArray = getResources().getStringArray(R.array.arrBaihoc_Hiragana);

            for (int i = 0; i <= 7; i++) {

                dsBaiHoc.add(new BaiHocH(stringArray[i]));
            }

        }
        else if (trangthaibtn==1){
            img_BCC.setImageResource(R.drawable.katakana);
            tv_BCCV.setText("Katakana");
            tv_BCCJ.setText("カタカナ");
            dsBaiHoc = new ArrayList<>();
            String[] stringArray = getResources().getStringArray(R.array.arrBaihoc_Katakana);

            for (int i = 0; i <= 7; i++) {

                dsBaiHoc.add(new BaiHocH(stringArray[i]));
            }

        }

        baiHocH_adapter = new BaiHocH_adapter(HiraganaActivity.this, R.layout.layout_listview, dsBaiHoc);

        lv_BaihocHiragana.setAdapter(baiHocH_adapter);
        lv_BaihocHiragana.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(HiraganaActivity.this, HBCCHiraganaActivity.class);
                intent.putExtra("positionListview",position);
                intent.putExtra("A",trangthaibtn);
                startActivityForResult(intent,0);
            }
        });

        ll_BangChuCai.setOnClickListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(HiraganaActivity.this, BCCHiraganaActivity.class);
        intent.putExtra("A",trangthaibtn);
        startActivity(intent);

    }

}
