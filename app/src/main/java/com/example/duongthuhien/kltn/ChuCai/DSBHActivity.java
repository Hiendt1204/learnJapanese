package com.example.duongthuhien.kltn.ChuCai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duongthuhien.kltn.Adapter.BaiHocH_adapter;
import com.example.duongthuhien.kltn.Model.BaiHocH;
import com.example.duongthuhien.kltn.R;

import java.util.ArrayList;

public class DSBHActivity extends AppCompatActivity  {
    ListView lv_BaihocHiragana;
    ArrayList<BaiHocH> dsBaiHoc;
    BaiHocH_adapter baiHocH_adapter;
    int trangthaibtn;
    TextView tv_BCCV;
    TextView tv_BCCJ;
    ImageView img_BCC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiragana);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent=getIntent();
        trangthaibtn=intent.getIntExtra("trangthaibtn",-1);
        Log.d("hiendt","DSBHActivity trangthaibtn " + trangthaibtn);

        addControl();


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

    private void addControl() {
        img_BCC=findViewById(R.id.img_BCC);
        tv_BCCJ=findViewById(R.id.tv_BCCJ);
        tv_BCCV=findViewById(R.id.tv_BCCV);
        lv_BaihocHiragana = findViewById(R.id.lv_BaihocHiragana);
        dsBaiHoc = new ArrayList<>();
        if (trangthaibtn==2){
            img_BCC.setImageResource(R.drawable.iconhiragana);
            tv_BCCV.setText("Hiragana");
            tv_BCCJ.setText("ひらがな");

            String[] stringArray = getResources().getStringArray(R.array.arrBaihoc_Hiragana);

            for (int i = 0; i <= 7; i++) {

                dsBaiHoc.add(new BaiHocH(stringArray[i]));
            }

        }
        else if (trangthaibtn==1){
            img_BCC.setImageResource(R.drawable.katakana);
            tv_BCCV.setText("Katakana");
            tv_BCCJ.setText("カタカナ");
            String[] stringArray = getResources().getStringArray(R.array.arrBaihoc_Katakana);

            for (int i = 0; i <= 7; i++) {

                dsBaiHoc.add(new BaiHocH(stringArray[i]));
            }

        }

        baiHocH_adapter = new BaiHocH_adapter(DSBHActivity.this, R.layout.layout_listview, dsBaiHoc);

        lv_BaihocHiragana.setAdapter(baiHocH_adapter);
        lv_BaihocHiragana.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(DSBHActivity.this, HBCCActivity.class);
                intent.putExtra("positionListview",position);
                intent.putExtra("A",trangthaibtn);
                startActivityForResult(intent,0);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
