package com.example.duongthuhien.kltn.MCCB;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.duongthuhien.kltn.Adapter.MCCB_adapter;
import com.example.duongthuhien.kltn.Model.BaiHocMCCB;
import com.example.duongthuhien.kltn.R;

import java.util.ArrayList;

public class MCCBActivity extends AppCompatActivity {
    ListView ll_MCCB;
    ArrayList<BaiHocMCCB> dsBaiHoc;
    MCCB_adapter mccb_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mccb);

        addControl();
        getBaiHocList();

        mccb_adapter=new MCCB_adapter(MCCBActivity.this,R.layout.item_mccb,dsBaiHoc);
        ll_MCCB.setAdapter(mccb_adapter);

        ll_MCCB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent=new Intent(MCCBActivity.this,Detail_MCCB_Activity.class);
                intent.putExtra("POS",pos);
                startActivity(intent);
            }
        });
    }

    private ArrayList getBaiHocList() {
        dsBaiHoc=new ArrayList<>();
        String[] nameArray=getResources().getStringArray(R.array.arrBaihoc_MCCB);
        String[] urlArray=getResources().getStringArray(R.array.arrImage_MCCB);
        for (int i = 0; i <=17; i++) {
            BaiHocMCCB baiHocMCCB=new BaiHocMCCB();
            baiHocMCCB.setTrName(nameArray[i]);
            baiHocMCCB.setUrlImage(urlArray[i]);
            dsBaiHoc.add(baiHocMCCB);
        }
        return dsBaiHoc;
    }


    private void addControl() {
        ll_MCCB = findViewById(R.id.ll_Maucaucoban);
    }
}
